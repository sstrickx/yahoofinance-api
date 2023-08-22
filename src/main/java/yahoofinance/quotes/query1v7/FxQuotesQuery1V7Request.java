package yahoofinance.quotes.query1v7;

import com.fasterxml.jackson.databind.JsonNode;
import yahoofinance.Utils;
import yahoofinance.quotes.fx.FxQuote;

import java.math.BigDecimal;

/**
 *
 * @author Stijn Strickx
 */
public class FxQuotesQuery1V7Request extends QuotesRequest<FxQuote> {

    public FxQuotesQuery1V7Request(String symbols) {
        super(symbols);
    }

    @Override
    protected FxQuote parseJson(JsonNode node) {
        if (node.has("symbol")) {
            String symbol = getStringValue(node, "symbol");
            BigDecimal price = Utils.getBigDecimal(getStringValue(node, "regularMarketPrice"));

            return new FxQuote(symbol, price);
        }
        return null;
    }
}
