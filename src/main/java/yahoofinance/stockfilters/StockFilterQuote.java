package yahoofinance.stockfilters;

import java.util.Calendar;

import yahoofinance.histquotes.HistoricalQuote;

/***
 * 
 * @author Austin McElroy
 *
 */

public class StockFilterQuote {
	Calendar _date;
	
	float _filteredResult;
	
	public StockFilterQuote(HistoricalQuote hq) {
		_date = hq.getDate();
	}
	
	public void setResult(float result) {
		_filteredResult = result;
	}
	
	public float getResult() {
		return _filteredResult;
	}
}
