package yahoofinance.stockfilters;

import java.util.ArrayList;
import java.util.List;

import yahoofinance.histquotes.HistoricalQuote;

/***
 * 
 * @author Austin McElroy
 *
 */

public class MovingAverage implements StockFilter{

	int length = 200;
	
	public MovingAverage(int length){
		this.length = length;
	}
	
	@Override
	public List<StockFilterQuote> filter(List<HistoricalQuote> input) {
		if(input.size() < length) {
			return null;
		}
		
		ArrayList<StockFilterQuote> results = new ArrayList<StockFilterQuote>();
		
		for(int i = length; i < input.size(); i++) {
			float mvg_avg = 0;
			for(int j = 0; j < length; j++) {
				mvg_avg += input.get(i - j).getClose().floatValue();
			}
			mvg_avg /= length;
			
			StockFilterQuote sfq = new StockFilterQuote(input.get(i));
			sfq.setResult(mvg_avg);	
			
			results.add(sfq);
		}
		
		
		return results;
	}

}
