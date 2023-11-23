package yahoofinance.histquotes2;

import java.io.*;
import java.net.CookieHandler;
import java.net.CookieManager;
import java.net.CookieStore;
import java.net.HttpCookie;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import yahoofinance.YahooFinance;
import yahoofinance.utils.RedirectableRequest;

/**
 * Created by Stijn on 23/05/2017.
 */
public class CrumbManager {

    private static final Logger log = LoggerFactory.getLogger(CrumbManager.class);
  
    private static String crumb = "";
    private static String cookie = "";

    public static void refresh() throws IOException {
        setCookie();
        setCrumb();
    }

    public static synchronized String getCrumb() throws IOException {
        if(crumb == null || crumb.isEmpty()) {
            refresh();
        }
        return crumb;
    }

    public static String getCookie() throws IOException {
        if(cookie == null || cookie.isEmpty()) {
            refresh();
        }
        return cookie;
    }

    private static void setCookie() throws IOException {
        if(YahooFinance.HISTQUOTES2_COOKIE != null && !YahooFinance.HISTQUOTES2_COOKIE.isEmpty()) {
            cookie = YahooFinance.HISTQUOTES2_COOKIE;
            log.debug("Set cookie from system property: {}", cookie);
            return;
        }

        URLConnection connection = getUrlConnection();
        if (connection == null) return;

        Map<String, String> datas = consentToActivateCookie(connection);
        // If params are not empty, send the post request
        if(datas.size()>0) sendPostRequest(connection, datas);

        // Then Set the cookie with the cookieJar
        if (setCookieWithCookieJar()) return;

        log.debug("Failed to set cookie from http request. Historical quote requests will most likely fail.");
    }

    private static boolean setCookieWithCookieJar() {
        CookieStore cookieJar =  ((CookieManager)CookieHandler.getDefault()).getCookieStore();
        List <HttpCookie> cookies = cookieJar.getCookies();
        for (HttpCookie hcookie: cookies) {
        	if(hcookie.toString().matches("B=.*")) {
                 cookie = hcookie.toString();
                 log.debug("Set cookie from http request: {}", cookie);
                return true;
             }
        }
        return false;
    }

    private static URLConnection getUrlConnection() throws IOException {
        URLConnection connection = buildConnection();

        for(String headerKey : connection.getHeaderFields().keySet()) {
            if("Set-Cookie".equalsIgnoreCase(headerKey)) {
                for(String cookieField : connection.getHeaderFields().get(headerKey)) {
                    for(String cookieValue : cookieField.split(";")) {
                        if(cookieValue.matches("B=.*")) {
                            cookie = cookieValue;
                            log.debug("Set cookie from http request: {}", cookie);
                            return null;
                        }
                    }
                }
            }
        }
        return connection;
    }

    private static URLConnection buildConnection() throws IOException {
        URL request = new URL(YahooFinance.HISTQUOTES2_SCRAPE_URL);
        RedirectableRequest redirectableRequest = buildRedirectableRequest(request);

        URLConnection connection = redirectableRequest.openConnection();
        return connection;
    }

    private static void sendPostRequest(URLConnection connection, Map<String, String> datas) throws IOException {
        datas.put("namespace",YahooFinance.HISTQUOTES2_COOKIE_NAMESPACE);
        datas.put("agree",YahooFinance.HISTQUOTES2_COOKIE_AGREE);
        datas.put("originalDoneUrl",YahooFinance.HISTQUOTES2_SCRAPE_URL);
        datas.put("doneUrl",YahooFinance.HISTQUOTES2_COOKIE_OATH_DONEURL+ datas.get("sessionId")+"&inline="+ datas.get("inline")+"&lang="+ datas.get("locale"));

        URL requestOath = new URL(YahooFinance.HISTQUOTES2_COOKIE_OATH_URL);
        HttpURLConnection connectionOath = getHttpURLConnection(connection, requestOath);

        StringBuilder params = buildParams(datas);

        log.debug("Params = "+ params.toString());
        connectionOath.setRequestProperty("Content-Length",Integer.toString(params.toString().length()));
        OutputStreamWriter outputStreamWriter = new OutputStreamWriter(connectionOath.getOutputStream());
        outputStreamWriter.write(params.toString());
        outputStreamWriter.flush();
        connectionOath.setInstanceFollowRedirects(true);
        connectionOath.getResponseCode();
    }

    private static HttpURLConnection getHttpURLConnection(URLConnection connection, URL requestOath) throws IOException {
        HttpURLConnection connectionOath = null;
        connectionOath = (HttpURLConnection) requestOath.openConnection();
        connectionOath.setConnectTimeout(YahooFinance.CONNECTION_TIMEOUT);
        connectionOath.setReadTimeout(YahooFinance.CONNECTION_TIMEOUT);
        connectionOath.setRequestMethod( "POST" );
        connectionOath.setDoOutput( true );
        connectionOath.setRequestProperty("Referer", connection.getURL().toString());
        connectionOath.setRequestProperty("Host",YahooFinance.HISTQUOTES2_COOKIE_OATH_HOST);
        connectionOath.setRequestProperty("Origin",YahooFinance.HISTQUOTES2_COOKIE_OATH_ORIGIN);
        connectionOath.setRequestProperty("Content-Type","application/x-www-form-urlencoded");
        return connectionOath;
    }

    private static StringBuilder buildParams(Map<String, String> datas) throws UnsupportedEncodingException {
        StringBuilder params= new StringBuilder("");

        for ( String key : datas.keySet() ) {
            if(params.length() == 0 ){
                params.append(key);
                params.append("=");
                params.append(URLEncoder.encode(datas.get(key),"UTF-8"));
            }else{
                params.append("&");
                params.append(key);
                params.append("=");
                params.append(URLEncoder.encode(datas.get(key),"UTF-8"));

            }
         }
        return params;
    }

    private static Map<String, String> consentToActivateCookie(URLConnection connection) throws IOException {
        //  If cookie is not set, we should consent to activate cookie
        InputStreamReader is = new InputStreamReader(connection.getInputStream());
        BufferedReader br = new BufferedReader(is);
        Pattern patternPostForm = Pattern.compile("action=\"/consent\"");
        Pattern patternInput = Pattern.compile("(<input type=\"hidden\" name=\")(.*?)(\" value=\")(.*?)(\">)");
        Map<String, String> datas = buildData(br, patternPostForm, patternInput);
        return datas;
    }

    private static Map<String, String> buildData(BufferedReader br, Pattern patternPostForm, Pattern patternInput) throws IOException {
        String line;
        Matcher matcher;
        Map<String,String> datas = new HashMap<String,String>();
        boolean postFind = false;
        // Read source to get params data for post request
        while( (line = br.readLine())!=null ) {
        	matcher = patternPostForm.matcher(line);
        	if(matcher.find()) postFind = true;
        	if(postFind){
        		matcher = patternInput.matcher(line);
        		if(matcher.find()){
        			String name = matcher.group(2);
        			String value = matcher.group(4);
        			datas.put(name, value);
        		}
        	}
        }
        return datas;
    }

    private static void setCrumb() throws IOException {
        if(YahooFinance.HISTQUOTES2_CRUMB != null && !YahooFinance.HISTQUOTES2_CRUMB.isEmpty()) {
            crumb = YahooFinance.HISTQUOTES2_CRUMB;
            log.debug("Set crumb from system property: {}", crumb);
            return;
        }

        URL crumbRequest = new URL(YahooFinance.HISTQUOTES2_CRUMB_URL);
        RedirectableRequest redirectableCrumbRequest = buildRedirectableRequest(crumbRequest);
        Map<String, String> requestProperties = new HashMap<String, String>();
        requestProperties.put("Cookie", cookie);

        String crumbResult = buildCrumbResult(redirectableCrumbRequest, requestProperties);
        if(crumbResult != null && !crumbResult.isEmpty()) {
            crumb = crumbResult.trim();
            log.debug("Set crumb from http request: {}", crumb);
        } else {
            log.debug("Failed to set crumb from http request. Historical quote requests will most likely fail.");
        }
    }

    private static RedirectableRequest buildRedirectableRequest(URL crumbRequest) {
        RedirectableRequest redirectableCrumbRequest = new RedirectableRequest(crumbRequest, 5);
        redirectableCrumbRequest.setConnectTimeout(YahooFinance.CONNECTION_TIMEOUT);
        redirectableCrumbRequest.setReadTimeout(YahooFinance.CONNECTION_TIMEOUT);
        return redirectableCrumbRequest;
    }

    private static String buildCrumbResult(RedirectableRequest redirectableCrumbRequest, Map<String, String> requestProperties) throws IOException {
        URLConnection crumbConnection = redirectableCrumbRequest.openConnection(requestProperties);
        InputStreamReader is = new InputStreamReader(crumbConnection.getInputStream());
        BufferedReader br = new BufferedReader(is);
        String crumbResult = br.readLine();
        return crumbResult;
    }
}
