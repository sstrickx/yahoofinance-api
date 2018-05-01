package yahoofinance.util;

import java.io.IOException;
import java.net.*;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author Stijn Strickx
 */
public class RedirectableRequest {

    private URL request;
    private int protocolRedirectLimit;

    private int connectTimeout = 10000;
    private int readTimeout = 10000;

    static {
        CookieHandler.setDefault(new CookieManager(null, CookiePolicy.ACCEPT_ALL));
    }

    public RedirectableRequest(URL request) {
        this(request, 2);
    }

    public RedirectableRequest(URL request, int protocolRedirectLimit) {
        this.request = request;
        this.protocolRedirectLimit = protocolRedirectLimit;
    }

    public URLConnection openConnection() throws IOException {
        return openConnection(new HashMap<String, String>());
    }

    public URLConnection openConnection(Map<String, String> requestProperties) throws IOException {
        int redirectCount = 0;
        boolean hasResponse = false;
        HttpURLConnection connection = null;
        URL currentRequest = this.request;
        while(!hasResponse && (redirectCount <= this.protocolRedirectLimit)) {
            connection = (HttpURLConnection) currentRequest.openConnection();
            connection.setConnectTimeout(this.connectTimeout);
            connection.setReadTimeout(this.readTimeout);

            for(String requestProperty : requestProperties.keySet()) {
                connection.addRequestProperty(requestProperty, requestProperties.get(requestProperty));
            }

            // only handle protocol redirects manually...
            connection.setInstanceFollowRedirects(true);

            switch (connection.getResponseCode()) {
                case HttpURLConnection.HTTP_MOVED_PERM:
                case HttpURLConnection.HTTP_MOVED_TEMP:
                    redirectCount++;
                    String location = connection.getHeaderField("Location");
                    currentRequest = new URL(request, location);
                    break;
                default:
                    hasResponse = true;
            }
        }

        if(redirectCount > this.protocolRedirectLimit) {
            throw new IOException("Protocol redirect count exceeded for url: " + this.request.toExternalForm());
        } else if(connection == null) {
            throw new IOException("Unexpected error while opening connection");
        } else {
            return connection;
        }
    }

    public URL getRequest() {
        return request;
    }

    public void setRequest(URL request) {
        this.request = request;
    }

    public int getProtocolRedirectLimit() {
        return protocolRedirectLimit;
    }

    public void setProtocolRedirectLimit(int protocolRedirectLimit) {
        if(protocolRedirectLimit >= 0) {
            this.protocolRedirectLimit = protocolRedirectLimit;
        }
    }

    public int getConnectTimeout() {
        return connectTimeout;
    }

    public void setConnectTimeout(int connectTimeout) {
        this.connectTimeout = connectTimeout;
    }

    public int getReadTimeout() {
        return readTimeout;
    }

    public void setReadTimeout(int readTimeout) {
        this.readTimeout = readTimeout;
    }
}
