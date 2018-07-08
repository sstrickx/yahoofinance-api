package yahoofinance.stockfilters;

import java.util.List;

import yahoofinance.histquotes.HistoricalQuote;

/***
 * 
 * @author Austin McElroy
 *
 */

public interface StockFilter {

	List<StockFilterQuote> filter(List<HistoricalQuote> input);
}
