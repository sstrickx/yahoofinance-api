package yahoofinance.quotes.query1v7;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import yahoofinance.YahooFinance;
import yahoofinance.util.RedirectableRequest;

import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

public abstract class YahooRequest {

    private static final Logger log = LoggerFactory.getLogger(QuotesRequest.class);
    private static final ObjectMapper objectMapper = new ObjectMapper();

    /**
     * Sends the request to Yahoo Finance and parses the result
     *
     * @return List of parsed objects resulting from the Yahoo Finance request
     * @throws IOException when there's a connection problem or the request is incorrect
     */
    public JsonNode getResult(String url) throws IOException {
        // Get JSON from Yahoo
        log.info("Sending request: " + url);

        URL request = new URL(url);
        RedirectableRequest redirectableRequest = new RedirectableRequest(request, 5);
        redirectableRequest.setConnectTimeout(YahooFinance.CONNECTION_TIMEOUT);
        redirectableRequest.setReadTimeout(YahooFinance.CONNECTION_TIMEOUT);
        URLConnection connection = redirectableRequest.openConnection();

        InputStreamReader is = new InputStreamReader(connection.getInputStream());
        return objectMapper.readTree(is);
    }

}
