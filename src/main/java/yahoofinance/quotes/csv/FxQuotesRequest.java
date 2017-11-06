
package yahoofinance.quotes.csv;

import java.util.ArrayList;
import java.util.List;
import yahoofinance.Utils;
import yahoofinance.YahooFinance;
import yahoofinance.quotes.fx.FxQuote;

/**
 *
 * @author Stijn Strickx
 */
public class FxQuotesRequest extends QuotesRequest<FxQuote> {
    
    public static final List<QuotesProperty> DEFAULT_PROPERTIES = new ArrayList<QuotesProperty>();
    static {
        DEFAULT_PROPERTIES.add(QuotesProperty.Symbol);
        DEFAULT_PROPERTIES.add(QuotesProperty.LastTradePriceOnly);
    }
    
    public FxQuotesRequest(String query) {
        super(query, FxQuotesRequest.DEFAULT_PROPERTIES);
    }

    @Override
    protected FxQuote parseCSVLine(String line) {
        String[] split = Utils.stripOverhead(line).split(YahooFinance.QUOTES_CSV_DELIMITER);
        if(split.length >= 2) {
            return new FxQuote(split[0], Utils.getBigDecimal(split[1]));
        }
        return null;
    }
    
}
