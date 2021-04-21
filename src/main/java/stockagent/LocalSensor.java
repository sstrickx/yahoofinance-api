package stockagent;

import yahoofinance.Stock;
import yahoofinance.YahooFinance;
import yahoofinance.histquotes.HistoricalQuote;
import yahoofinance.histquotes.Interval;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.Calendar;
import java.util.List;
import java.util.Map;


public class LocalSensor implements SensorInterface{
//
    private String[] symbols = new String[] {"INTC", "BABA", "TSLA", "AIR.PA", "YHOO"};
    private Map<String, Stock> stocks = YahooFinance.get(symbols);
    private Calendar from = Calendar.getInstance();
    private Calendar to = Calendar.getInstance();
    private int yearAgo = -1;

    public LocalSensor() throws IOException {
    }


    public Map<String, Stock>getStocks(){

        return stocks;

    }

    //GetStockPricing
    //Give us closing prices of the stock


    //Maybe better if we can getStockPrice for a specific day?

    public BigDecimal getStockPrice(Stock ticker) throws IOException {

        List<HistoricalQuote>historicalQuotes = getHistory(ticker);


        for(int i =0; i < historicalQuotes.size()-1; i++) {
            return (historicalQuotes.get(i).getClose());
        }

        return null;

    }



    public List<HistoricalQuote>getHistory(Stock ticker) throws IOException {
        from.add(Calendar.YEAR, yearAgo);
        List<HistoricalQuote>historicalQuotes = stocks.get(ticker).getHistory(from, to, Interval.DAILY);

        return historicalQuotes;
    }







}
