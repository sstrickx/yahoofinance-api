package yahoofinance.histquotes2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import yahoofinance.YahooFinance;
import yahoofinance.util.RedirectableRequest;

/**
 * Created by Stijn on 23/05/2017.
 */
public class CrumbManager {

    private static final Logger log = LoggerFactory.getLogger(CrumbManager.class);
  
    private static String crumb = "";
    private static String cookie = "";

    private static void setCookie() throws IOException {
        if(YahooFinance.HISTQUOTES2_COOKIE != null && !YahooFinance.HISTQUOTES2_COOKIE.isEmpty()) {
            cookie = YahooFinance.HISTQUOTES2_COOKIE;
            log.debug("Set cookie from system property: {}", cookie);
            return;
        }

        URL request = new URL(YahooFinance.HISTQUOTES2_SCRAPE_URL);
        RedirectableRequest redirectableRequest = new RedirectableRequest(request, 5);
        redirectableRequest.setConnectTimeout(YahooFinance.CONNECTION_TIMEOUT);
        redirectableRequest.setReadTimeout(YahooFinance.CONNECTION_TIMEOUT);
        URLConnection connection = redirectableRequest.openConnection();

        for(String headerKey : connection.getHeaderFields().keySet()) {
            if("Set-Cookie".equalsIgnoreCase(headerKey)) {
                for(String cookieField : connection.getHeaderFields().get(headerKey)) {
                    for(String cookieValue : cookieField.split(";")) {
                        if(cookieValue.matches("B=.*")) {
                            cookie = cookieValue;
                            log.debug("Set cookie from http request: {}", cookie);
                            return;
                        }
                    }
                }
            }
        }
        log.warn("Failed to set cookie from http request. Historical quote requests will most likely fail.");
    }

    private static void setCrumb() throws IOException {
        if(YahooFinance.HISTQUOTES2_CRUMB != null && !YahooFinance.HISTQUOTES2_CRUMB.isEmpty()) {
            crumb = YahooFinance.HISTQUOTES2_CRUMB;
            log.debug("Set crumb from system property: {}", crumb);
            return;
        }

        URL crumbRequest = new URL(YahooFinance.HISTQUOTES2_CRUMB_URL);
        RedirectableRequest redirectableCrumbRequest = new RedirectableRequest(crumbRequest, 5);
        redirectableCrumbRequest.setConnectTimeout(YahooFinance.CONNECTION_TIMEOUT);
        redirectableCrumbRequest.setReadTimeout(YahooFinance.CONNECTION_TIMEOUT);

        Map<String, String> requestProperties = new HashMap<String, String>();
        requestProperties.put("Cookie", cookie);

        URLConnection crumbConnection = redirectableCrumbRequest.openConnection(requestProperties);

        InputStreamReader is = new InputStreamReader(crumbConnection.getInputStream());
        BufferedReader br = new BufferedReader(is);
        String crumbResult = br.readLine();

        if(crumbResult != null && !crumbResult.isEmpty()) {
            crumb = crumbResult.trim();
            log.debug("Set crumb from http request: {}", crumb);
        } else {
            log.warn("Failed to set crumb from http request. Historical quote requests will most likely fail.");
        }
    }

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

}
