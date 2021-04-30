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


public class MarketSensor implements SensorInterface{

    private String[] symbols = new String[] {"INTC", "BABA", "TSLA", "GOOG"};
    private Map<String, Stock> stocks = YahooFinance.get(symbols);
    private Calendar from = Calendar.getInstance();
    private Calendar to = Calendar.getInstance();
    private Interval daily = Interval.DAILY;


    public MarketSensor() throws IOException {
        from.add(Calendar.YEAR, -1);

    }


    public Map<String, Stock>getStocks(){

        return stocks;

    }

    //GetStockPricing
    //Give us closing prices of the stock
    //Maybe better if we can getStockPrice for a specific day?

    public BigDecimal getStockPrice(String ticker) throws IOException {

        List<HistoricalQuote>historicalQuotes = getHistory(ticker);


        for(int i =0; i < historicalQuotes.size()-1; i++) {
            return (historicalQuotes.get(i).getClose());
        }

        return null;

    }



    public List<HistoricalQuote>getHistory(String ticker) throws IOException {
        List<HistoricalQuote>historicalQuotes = stocks.get(ticker).getHistory(from, to, daily);


        return historicalQuotes;
    }











}
