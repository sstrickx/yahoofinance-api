package yahoofinance;

import java.io.IOException;
import java.lang.reflect.Field;
import java.util.Calendar;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import yahoofinance.histquotes.HistQuotesRequest;
import yahoofinance.histquotes.HistoricalQuote;
import yahoofinance.histquotes.Interval;
import yahoofinance.histquotes2.HistQuotes2Request;
import yahoofinance.quotes.stock.StockDividend;
import yahoofinance.quotes.stock.StockQuote;
import yahoofinance.quotes.stock.StockQuotesData;
import yahoofinance.quotes.stock.StockQuotesRequest;
import yahoofinance.quotes.stock.StockStats;

/**
 *
 * @author Stijn Strickx
 */
public class Stock {

    private final String symbol;
    private String name;
    private String currency;
    private String stockExchange;
    
    private StockQuote quote;
    private StockStats stats;
    private StockDividend dividend;
    
    private List<HistoricalQuote> history;
    
    public Stock(String symbol) {
        this.symbol = symbol;
    }
    
    private void update() throws IOException {
        StockQuotesRequest request = new StockQuotesRequest(this.symbol);
        StockQuotesData data = request.getSingleResult();
        if(data != null) {
            this.setQuote(data.getQuote());
            this.setStats(data.getStats());
            this.setDividend(data.getDividend());
            YahooFinance.logger.log(Level.INFO, "Updated Stock with symbol: {0}", this.symbol);
        } else {
            YahooFinance.logger.log(Level.SEVERE, "Failed to update Stock with symbol: {0}", this.symbol);
        }
    }

    /**
     * Checks if the returned name is null. This probably means that the symbol was not recognized by Yahoo Finance.
     * @return whether this stock's symbol is known by Yahoo Finance (true) or not (false)
     */
    public boolean isValid() {
        return this.name != null;
    }
    
    /**
     * Returns the basic quotes data available for this stock.
     * 
     * @return      basic quotes data available for this stock
     * @see         #getQuote(boolean) 
     */
    public StockQuote getQuote() {
        return this.quote;
    }
    
    /**
     * Returns the basic quotes data available for this stock.
     * This method will return null in the following situations:
     * <ul>
     * <li> the data hasn't been loaded yet
     *      in a previous request and refresh is set to false.
     * <li> refresh is true and the data cannot be retrieved from Yahoo Finance 
     *      for whatever reason (symbol not recognized, no network connection, ...)
     * </ul>
     * <p>
     * When the quote data gets refreshed, it will automatically also refresh
     * the statistics and dividend data of the stock from Yahoo Finance 
     * in the same request.
     * 
     * @param refresh   indicates whether the data should be requested again to Yahoo Finance
     * @return          basic quotes data available for this stock
     * @throws java.io.IOException when there's a connection problem
     */
    public StockQuote getQuote(boolean refresh) throws IOException {
        if(refresh) {
            this.update();
        }
        return this.quote;
    }
    
    public void setQuote(StockQuote quote) {
        this.quote = quote;
    }
    
    /**
     * Returns the statistics available for this stock.
     * 
     * @return      statistics available for this stock
     * @see         #getStats(boolean) 
     */
    public StockStats getStats() {
        return this.stats;
    }
    
    /**
     * Returns the statistics available for this stock.
     * This method will return null in the following situations:
     * <ul>
     * <li> the data hasn't been loaded yet
     *      in a previous request and refresh is set to false.
     * <li> refresh is true and the data cannot be retrieved from Yahoo Finance 
     *      for whatever reason (symbol not recognized, no network connection, ...)
     * </ul>
     * <p>
     * When the statistics get refreshed, it will automatically also refresh
     * the quote and dividend data of the stock from Yahoo Finance 
     * in the same request.
     * 
     * @param refresh   indicates whether the data should be requested again to Yahoo Finance
     * @return          statistics available for this stock
     * @throws java.io.IOException when there's a connection problem
     */
    public StockStats getStats(boolean refresh) throws IOException {
        if(refresh) {
            this.update();
        }
        return this.stats;
    }
    
    public void setStats(StockStats stats) {
        this.stats = stats;
    }
    
    /**
     * Returns the dividend data available for this stock.
     * 
     * @return      dividend data available for this stock
     * @see         #getDividend(boolean) 
     */
    public StockDividend getDividend() {
        return this.dividend;
    }
    
    /**
     * Returns the dividend data available for this stock.
     * 
     * This method will return null in the following situations:
     * <ul>
     * <li> the data hasn't been loaded yet
     *      in a previous request and refresh is set to false.
     * <li> refresh is true and the data cannot be retrieved from Yahoo Finance 
     *      for whatever reason (symbol not recognized, no network connection, ...)
     * </ul>
     * <p>
     * When the dividend data get refreshed, it will automatically also refresh
     * the quote and statistics data of the stock from Yahoo Finance 
     * in the same request.
     * 
     * @param refresh   indicates whether the data should be requested again to Yahoo Finance
     * @return          dividend data available for this stock
     * @throws java.io.IOException when there's a connection problem
     */
    public StockDividend getDividend(boolean refresh) throws IOException {
        if(refresh) {
            this.update();
        }
        return this.dividend;
    }
    
    public void setDividend(StockDividend dividend) {
        this.dividend = dividend;
    }
    
    /**
     * This method will return historical quotes from this stock.
     * If the historical quotes are not available yet, they will 
     * be requested first from Yahoo Finance.
     * <p>
     * If the historical quotes are not available yet, the
     * following characteristics will be used for the request:
     * <ul>
     * <li> from: 1 year ago (default)
     * <li> to: today (default)
     * <li> interval: MONTHLY (default)
     * </ul>
     * <p>
     * There are several more methods available that allow you
     * to define some characteristics of the historical data.
     * Calling one of those methods will result in a new request
     * being sent to Yahoo Finance.
     * 
     * @return      a list of historical quotes from this stock
     * @throws java.io.IOException when there's a connection problem
     * @see         #getHistory(yahoofinance.histquotes.Interval) 
     * @see         #getHistory(java.util.Calendar) 
     * @see         #getHistory(java.util.Calendar, java.util.Calendar) 
     * @see         #getHistory(java.util.Calendar, yahoofinance.histquotes.Interval) 
     * @see         #getHistory(java.util.Calendar, java.util.Calendar, yahoofinance.histquotes.Interval) 
     */
    public List<HistoricalQuote> getHistory() throws IOException {
        if(this.history != null) {
            return this.history;
        }
        return this.getHistory(HistQuotesRequest.DEFAULT_FROM);
    }
    
    /**
     * Requests the historical quotes for this stock with the following characteristics.
     * <ul>
     * <li> from: 1 year ago (default)
     * <li> to: today (default)
     * <li> interval: specified value
     * </ul>
     * 
     * @param interval      the interval of the historical data
     * @return              a list of historical quotes from this stock
     * @throws java.io.IOException when there's a connection problem
     * @see                 #getHistory() 
     */
    public List<HistoricalQuote> getHistory(Interval interval) throws IOException {
        return this.getHistory(HistQuotesRequest.DEFAULT_FROM, interval);
    }
    
    /**
     * Requests the historical quotes for this stock with the following characteristics.
     * <ul>
     * <li> from: specified value
     * <li> to: today (default)
     * <li> interval: MONTHLY (default)
     * </ul>
     * 
     * @param from          start date of the historical data
     * @return              a list of historical quotes from this stock
     * @throws java.io.IOException when there's a connection problem
     * @see                 #getHistory() 
     */
    public List<HistoricalQuote> getHistory(Calendar from) throws IOException {
        return this.getHistory(from, HistQuotesRequest.DEFAULT_TO);
    }
    
    /**
     * Requests the historical quotes for this stock with the following characteristics.
     * <ul>
     * <li> from: specified value
     * <li> to: today (default)
     * <li> interval: specified value
     * </ul>
     * 
     * @param from          start date of the historical data
     * @param interval      the interval of the historical data
     * @return              a list of historical quotes from this stock
     * @throws java.io.IOException when there's a connection problem
     * @see                 #getHistory() 
     */
    public List<HistoricalQuote> getHistory(Calendar from, Interval interval) throws IOException {
        return this.getHistory(from, HistQuotesRequest.DEFAULT_TO, interval);
    }
    
    /**
     * Requests the historical quotes for this stock with the following characteristics.
     * <ul>
     * <li> from: specified value
     * <li> to: specified value
     * <li> interval: MONTHLY (default)
     * </ul>
     * 
     * @param from          start date of the historical data
     * @param to            end date of the historical data
     * @return              a list of historical quotes from this stock
     * @throws java.io.IOException when there's a connection problem
     * @see                 #getHistory() 
     */
    public List<HistoricalQuote> getHistory(Calendar from, Calendar to) throws IOException {
        return this.getHistory(from, to, Interval.MONTHLY);
    }
    
    /**
     * Requests the historical quotes for this stock with the following characteristics.
     * <ul>
     * <li> from: specified value
     * <li> to: specified value
     * <li> interval: specified value
     * </ul>
     * 
     * @param from          start date of the historical data
     * @param to            end date of the historical data
     * @param interval      the interval of the historical data
     * @return              a list of historical quotes from this stock
     * @throws java.io.IOException when there's a connection problem
     * @see                 #getHistory() 
     */
    public List<HistoricalQuote> getHistory(Calendar from, Calendar to, Interval interval) throws IOException {
        if(YahooFinance.HISTQUOTES2_ENABLED.equalsIgnoreCase("true")) {
            HistQuotes2Request hist = new HistQuotes2Request(this.symbol, from, to, interval);
            this.setHistory(hist.getResult());
        } else {
            HistQuotesRequest hist = new HistQuotesRequest(this.symbol, from, to, interval);
            this.setHistory(hist.getResult());
        }
        return this.history;
    }
    
    public void setHistory(List<HistoricalQuote> history) {
        this.history = history;
    }
    
    public String getSymbol() {
        return symbol;
    }
    
    /**
     * Get the full name of the stock
     * 
     * @return the name or null if the data is not available
     */
    public String getName() {
        return name;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    
    /**
     * Get the currency of the stock
     * 
     * @return the currency or null if the data is not available
     */
    public String getCurrency() {
        return currency;
    }
    
    public void setCurrency(String currency) {
        this.currency = currency;
    }
    
    /**
     * Get the exchange on which the stock is traded
     * 
     * @return the exchange or null if the data is not available
     */
    public String getStockExchange() {
        return stockExchange;
    }
    
    public void setStockExchange(String stockExchange) {
        this.stockExchange = stockExchange;
    }
    
    @Override
    public String toString() {
        return this.symbol + ": " + this.quote.getPrice();
    }
    
    public void print() {
        System.out.println(this.symbol);
        System.out.println("--------------------------------");
        for (Field f : this.getClass().getDeclaredFields()) {
            try {
                System.out.println(f.getName() + ": " + f.get(this));
            } catch (IllegalArgumentException ex) {
                Logger.getLogger(Stock.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IllegalAccessException ex) {
                Logger.getLogger(Stock.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        System.out.println("--------------------------------");
    }
    
}
