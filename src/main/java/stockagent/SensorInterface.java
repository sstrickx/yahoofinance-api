package stockagent;

import yahoofinance.Stock;
import yahoofinance.YahooFinance;

import java.util.List;
import java.util.Map;

public interface SensorInterface {

    String[] symbols = new String[] {"INTC", "BABA", "TSLA", "AIR.PA", "YHOO", "GOOG"};
    //Map<String, Stock> stocks = YahooFinance.get(symbols, true);



    //GetStockFunction
    //All the stocks we are going to look at and its information
        //return a list of all the stocks we will look at, hard coded?

    public List<Stock>getStocks();


    //GetStockPricing
        //Give us closing price of the stock
    public double getStockPrice(Stock stock);










}
