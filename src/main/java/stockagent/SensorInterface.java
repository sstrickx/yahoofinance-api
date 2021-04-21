package stockagent;

import yahoofinance.Stock;
import yahoofinance.YahooFinance;
import yahoofinance.histquotes.HistoricalQuote;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

public interface SensorInterface {

    String[] symbols = new String[] {"INTC", "BABA", "TSLA", "AIR.PA", "YHOO", "GOOG"};
    //Map<String, Stock> stocks = YahooFinance.get(symbols, true);



    //GetStockFunction
    //All the stocks we are going to look at and its information
        //return a list of all the stocks we will look at, hard coded?
    public Map<String, Stock>getStocks();

    //GetStockPricing
        //Give us closing price of the stock
    public BigDecimal getStockPrice(String stock) throws IOException;


    public List<HistoricalQuote>getHistory(String ticker) throws IOException;










}
