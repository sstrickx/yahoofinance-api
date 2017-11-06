package yahoofinance.quotes.csv;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import yahoofinance.Utils;
import yahoofinance.YahooFinance;
import yahoofinance.util.RedirectableRequest;

/**
 *
 * @author Stijn Strickx
 * @param <T> Type of object that can contain the retrieved information from a
 * quotes request
 */
public abstract class QuotesRequest<T> {

    private static final Logger log = LoggerFactory.getLogger(QuotesRequest.class);

    protected final String query;
    protected List<QuotesProperty> properties;

    public QuotesRequest(String query, List<QuotesProperty> properties) {
        this.query = query;
        this.properties = properties;
    }

    public String getQuery() {
        return query;
    }

    public List<QuotesProperty> getProperties() {
        return properties;
    }

    public void setProperties(List<QuotesProperty> properties) {
        this.properties = properties;
    }

    protected abstract T parseCSVLine(String line);

    private String getFieldsString() {
        StringBuilder result = new StringBuilder();
        for (QuotesProperty property : this.properties) {
            result.append(property.getTag());
        }
        return result.toString();
    }

    public T getSingleResult() throws IOException {
        List<T> results = this.getResult();
        if (results.size() > 0) {
            return results.get(0);
        }
        return null;
    }

    /**
     * Sends the request to Yahoo Finance and parses the result
     *
     * @return List of parsed objects resulting from the Yahoo Finance request
     * @throws java.io.IOException when there's a connection problem or the request is incorrect
     */
    public List<T> getResult() throws IOException {
        List<T> result = new ArrayList<T>();

        Map<String, String> params = new LinkedHashMap<String, String>();
        params.put("s", this.query);
        params.put("f", this.getFieldsString());
        params.put("e", ".csv");

        String url = YahooFinance.QUOTES_BASE_URL + "?" + Utils.getURLParameters(params);

        // Get CSV from Yahoo
        log.info("Sending request: " + url);

        URL request = new URL(url);
        RedirectableRequest redirectableRequest = new RedirectableRequest(request, 5);
        redirectableRequest.setConnectTimeout(YahooFinance.CONNECTION_TIMEOUT);
        redirectableRequest.setReadTimeout(YahooFinance.CONNECTION_TIMEOUT);
        URLConnection connection = redirectableRequest.openConnection();

        InputStreamReader is = new InputStreamReader(connection.getInputStream());
        BufferedReader br = new BufferedReader(is);

        // Parse CSV
        for (String line = br.readLine(); line != null; line = br.readLine()) {
            if (line.equals("Missing Symbols List.")) {
                log.error("The requested symbol was not recognized by Yahoo Finance");
            } else {
                log.info("Parsing CSV line: " + Utils.unescape(line));

                T data = this.parseCSVLine(line);
                result.add(data);
            }
        }

        return result;
    }

}
