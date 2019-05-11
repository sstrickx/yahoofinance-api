package yahoofinance.quotes.query1v7;

import com.fasterxml.jackson.databind.JsonNode;
import yahoofinance.Utils;
import yahoofinance.YahooFinance;

import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Stijn Strickx
 * @param <T> Type of object that can contain the retrieved information from a
 * quotes request
 */
public abstract class QuotesRequest<T> extends YahooRequest {

    protected final String symbols;

    public QuotesRequest(String symbols) {
        this.symbols = symbols;
    }

    public String getSymbols() {
        return symbols;
    }

    protected abstract T parseJson(JsonNode node);

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
     * @throws IOException when there's a connection problem or the request is incorrect
     */
    public List<T> getResult() throws IOException {
        List<T> result = new ArrayList<T>();

        Map<String, String> params = new LinkedHashMap<String, String>();
        params.put("symbols", this.symbols);

        String url = YahooFinance.QUOTES_QUERY1V7_BASE_URL + "?" + Utils.getURLParameters(params);

        JsonNode node = super.getResult(url);

        if(node.has("quoteResponse") && node.get("quoteResponse").has("result")) {
            node = node.get("quoteResponse").get("result");
            for(int i = 0; i < node.size(); i++) {
                result.add(this.parseJson(node.get(i)));
            }
        } else {
            throw new IOException("Invalid response");
        }

        return result;
    }

}
