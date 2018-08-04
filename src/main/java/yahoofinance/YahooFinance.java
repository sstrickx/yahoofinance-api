package yahoofinance;

import java.io.IOException;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import yahoofinance.histquotes.HistQuotesRequest;
import yahoofinance.histquotes.Interval;
import yahoofinance.quotes.fx.FxQuote;
import yahoofinance.quotes.csv.FxQuotesRequest;
import yahoofinance.quotes.csv.StockQuotesData;
import yahoofinance.quotes.csv.StockQuotesRequest;
import yahoofinance.quotes.query1v7.FxQuotesQuery1V7Request;
import yahoofinance.quotes.query1v7.StockQuotesQuery1V7Request;

/**
 * YahooFinance can be used to retrieve quotes and some extra information on stocks.
 * There is also the possibility to include historical quotes on the requested stocks.
 * <p>
 * When trying to get information on multiple stocks at once, please use the provided
 * methods that accept a <code>String[]</code> of symbols to get the best performance.
 * To retrieve the basic quote, statistics and dividend data, a single request can be sent to 
 * Yahoo Finance for multiple stocks at once.
 * For the historical data however, a separate request has to be sent to Yahoo Finance
 * for each of the requested stocks. The provided methods will retrieve 
 * all of the required information in the least amount of 
 * requests possible towards Yahoo Finance.
 * <p>
 * You can change the default timeout of 10s for requests to Yahoo Finance by
 * setting the yahoofinance.connection.timeout system property.
 * <p>
 * Please be aware that the data received from Yahoo Finance is not always 
 * complete for every single stock. Stocks on the American stock exchanges
 * usually have a lot more data available than stocks on other exchanges.
 * <p>
 * This API can also be used to send requests for retrieving FX rates.
 * <p>
 * Since the data is provided by Yahoo, please check their Terms of Service
 * at https://info.yahoo.com/legal/us/yahoo/
 * 
 * @author      Stijn Strickx
 * @version     %I%, %G%
 */
public class YahooFinance {
    
    public static final String QUOTES_BASE_URL = System.getProperty("yahoofinance.baseurl.quotes", "http://download.finance.yahoo.com/d/quotes.csv");
    public static final String QUOTES_QUERY1V7_BASE_URL = System.getProperty("yahoofinance.baseurl.quotesquery1v7", "https://query1.finance.yahoo.com/v7/finance/quote");
    public static final String QUOTES_QUERY1V7_ENABLED = System.getProperty("yahoofinance.quotesquery1v7.enabled", "true");
    public static final String HISTQUOTES_BASE_URL = System.getProperty("yahoofinance.baseurl.histquotes", "https://ichart.yahoo.com/table.csv");
    public static final String HISTQUOTES2_ENABLED = System.getProperty("yahoofinance.histquotes2.enabled", "true");
    public static final String HISTQUOTES2_BASE_URL = System.getProperty("yahoofinance.baseurl.histquotes2", "https://query1.finance.yahoo.com/v7/finance/download/");
    public static final String HISTQUOTES_QUERY2V8_BASE_URL = System.getProperty("yahoofinance.baseurl.histquotesquery2v8", "https://query2.finance.yahoo.com/v8/finance/chart/");
    public static final String HISTQUOTES2_SCRAPE_URL = System.getProperty("yahoofinance.scrapeurl.histquotes2", "https://finance.yahoo.com/quote/%5EGSPC/options");
    public static final String HISTQUOTES2_CRUMB_URL = System.getProperty("yahoofinance.crumburl.histquotes2", "https://query1.finance.yahoo.com/v1/test/getcrumb");
    public static final String HISTQUOTES2_CRUMB = System.getProperty("yahoofinance.crumb", "");
    public static final String HISTQUOTES2_COOKIE = System.getProperty("yahoofinance.cookie", "");
    public static final String HISTQUOTES2_COOKIE_NAMESPACE = System.getProperty("yahoofinance.cookie.namespace", "yahoo");
    public static final String HISTQUOTES2_COOKIE_AGREE = System.getProperty("yahoofinance.cookie.agree", "agree");
    public static final String HISTQUOTES2_COOKIE_OATH_URL = System.getProperty("yahoofinance.cookie.oathurl", "https://guce.oath.com/consent");
    public static final String HISTQUOTES2_COOKIE_OATH_HOST = System.getProperty("yahoofinance.cookie.oathhost", "guce.oath.com");
    public static final String HISTQUOTES2_COOKIE_OATH_ORIGIN = System.getProperty("yahoofinance.cookie.oathorigin", "https://guce.oath.com");
    public static final String HISTQUOTES2_COOKIE_OATH_DONEURL = System.getProperty("yahoofinance.cookie.oathDoneUrl", "https://guce.yahoo.com/copyConsent?sessionId=");
    public static final String QUOTES_CSV_DELIMITER = ",";
    public static final String TIMEZONE = "America/New_York";
    
    public static final int CONNECTION_TIMEOUT = 
            Integer.parseInt(System.getProperty("yahoofinance.connection.timeout", "10000"));
    
    /**
    * Sends a basic quotes request to Yahoo Finance. This will return a {@link Stock} object
    * with its {@link yahoofinance.quotes.stock.StockQuote}, {@link yahoofinance.quotes.stock.StockStats}
    * and {@link yahoofinance.quotes.stock.StockDividend} member fields
    * filled in with the available data.
    * Returns null if the data can't be retrieved from Yahoo Finance.
    * 
    * @param symbol     the symbol of the stock for which you want to retrieve information
    * @return           a {@link Stock} object containing the requested information
     * @throws java.io.IOException when there's a connection problem
    */
    public static Stock get(String symbol) throws IOException {
        return YahooFinance.get(symbol, false);
    }
    
    /**
     * Same as the <code>get(String)</code> method, but with the option to include
     * historical stock quote data. Including historical data will cause the {@link Stock}
     * object's member field {@link yahoofinance.histquotes.HistoricalQuote} to be filled in
     * with the default past year term at monthly intervals.
     * Returns null if the data can't be retrieved from Yahoo Finance.
     * 
     * @param symbol                the symbol of the stock for which you want to retrieve information
     * @param includeHistorical     indicates if the historical quotes should be included.
     * @return                      a {@link Stock} object containing the requested information
     * @throws java.io.IOException when there's a connection problem
     */
    public static Stock get(String symbol, boolean includeHistorical) throws IOException {
        Map<String, Stock> result = YahooFinance.getQuotes(symbol, includeHistorical);
        return result.get(symbol.toUpperCase());
    }
    
    /**
     * Sends a request with the historical quotes included
     * at the specified interval (DAILY, WEEKLY, MONTHLY).
     * Returns null if the data can't be retrieved from Yahoo Finance.
     * 
     * @param symbol        the symbol of the stock for which you want to retrieve information
     * @param interval      the interval of the included historical data
     * @return              a {@link Stock} object containing the requested information
     * @throws java.io.IOException when there's a connection problem
     */
    public static Stock get(String symbol, Interval interval) throws IOException {
        return YahooFinance.get(symbol, HistQuotesRequest.DEFAULT_FROM, HistQuotesRequest.DEFAULT_TO, interval);
    }
    
    /**
     * Sends a request with the historical quotes included
     * starting from the specified {@link Calendar} date 
     * at the default interval (monthly).
     * Returns null if the data can't be retrieved from Yahoo Finance.
     * 
     * @param symbol        the symbol of the stock for which you want to retrieve information
     * @param from          start date of the historical data
     * @return              a {@link Stock} object containing the requested information
     * @throws java.io.IOException when there's a connection problem
     */
    public static Stock get(String symbol, Calendar from) throws IOException {
        return YahooFinance.get(symbol, from, HistQuotesRequest.DEFAULT_TO, HistQuotesRequest.DEFAULT_INTERVAL);
    }
    
    /**
     * Sends a request with the historical quotes included
     * starting from the specified {@link Calendar} date 
     * at the specified interval.
     * Returns null if the data can't be retrieved from Yahoo Finance.
     * 
     * @param symbol        the symbol of the stock for which you want to retrieve information
     * @param from          start date of the historical data
     * @param interval      the interval of the included historical data
     * @return              a {@link Stock} object containing the requested information
     * @throws java.io.IOException when there's a connection problem
     */
    public static Stock get(String symbol, Calendar from, Interval interval) throws IOException {
        return YahooFinance.get(symbol, from, HistQuotesRequest.DEFAULT_TO, interval);
    }
    
    /**
     * Sends a request with the historical quotes included
     * starting from the specified {@link Calendar} date 
     * until the specified Calendar date (to) 
     * at the default interval (monthly).
     * Returns null if the data can't be retrieved from Yahoo Finance.
     * 
     * @param symbol        the symbol of the stock for which you want to retrieve information
     * @param from          start date of the historical data
     * @param to            end date of the historical data
     * @return              a {@link Stock} object containing the requested information
     * @throws java.io.IOException when there's a connection problem
     */
    public static Stock get(String symbol, Calendar from, Calendar to) throws IOException {
        return YahooFinance.get(symbol, from, to, HistQuotesRequest.DEFAULT_INTERVAL);
    }
    
    /**
     * Sends a request with the historical quotes included
     * starting from the specified {@link Calendar} date 
     * until the specified Calendar date (to) 
     * at the specified interval.
     * Returns null if the data can't be retrieved from Yahoo Finance.
     * 
     * @param symbol        the symbol of the stock for which you want to retrieve information
     * @param from          start date of the historical data
     * @param to            end date of the historical data
     * @param interval      the interval of the included historical data
     * @return              a {@link Stock} object containing the requested information
     * @throws java.io.IOException when there's a connection problem
     */
    public static Stock get(String symbol, Calendar from, Calendar to, Interval interval) throws IOException {
        Map<String, Stock> result = YahooFinance.getQuotes(symbol, from, to, interval);
        return result.get(symbol.toUpperCase());
    }
    
    /**
    * Sends a basic quotes request to Yahoo Finance. This will return a {@link Map} object
    * that links the symbols to their respective {@link Stock} objects.
    * The Stock objects have their {@link yahoofinance.quotes.stock.StockQuote}, {@link yahoofinance.quotes.stock.StockStats}
    * and {@link yahoofinance.quotes.stock.StockDividend} member fields
    * filled in with the available data.
    * <p>
    * All the information is retrieved in a single request to Yahoo Finance.
    * The returned Map only includes the Stocks that could 
    * successfully be retrieved from Yahoo Finance.
    * 
    * @param symbols    the symbols of the stocks for which you want to retrieve information
    * @return           a Map that links the symbols to their respective Stock objects
     * @throws java.io.IOException when there's a connection problem
    */
    public static Map<String, Stock> get(String[] symbols) throws IOException {
        return YahooFinance.get(symbols, false);
    }
    
    /**
     * Same as the <code>get(String[])</code> method, but with the option to include
     * historical stock quote data. Including historical data will cause the {@link Stock}
     * objects their member field {@link yahoofinance.histquotes.HistoricalQuote} to be filled in
     * with the default past year term at monthly intervals.
     * <p>
     * The latest quotes will be retrieved in a single request to Yahoo Finance.
     * For the historical quotes (if includeHistorical), 
     * a separate request will be sent for each requested stock.
     * The returned Map only includes the Stocks that could 
     * successfully be retrieved from Yahoo Finance.
     * 
     * @param symbols               the symbols of the stocks for which you want to retrieve information
     * @param includeHistorical     indicates if the historical quotes should be included
     * @return                      a Map that links the symbols to their respective Stock objects
     * @throws java.io.IOException when there's a connection problem
     */
    public static Map<String, Stock> get(String[] symbols, boolean includeHistorical) throws IOException {
        return YahooFinance.getQuotes(Utils.join(symbols, ","), includeHistorical);
    }
    
    /**
     * Sends a request for multiple stocks with the historical quotes included
     * from the past year, 
     * at the specified interval. (DAILY, WEEKLY, MONTHLY)
     * <p>
     * The latest quotes will be retrieved in a single request to Yahoo Finance.
     * For the historical quotes, a separate request will be sent for each requested stock.
     * The returned Map only includes the Stocks that could 
     * successfully be retrieved from Yahoo Finance.
     * 
     * @param symbols               the symbols of the stocks for which you want to retrieve information
     * @param interval              the interval of the included historical data
     * @return                      a Map that links the symbols to their respective Stock objects.
     * @throws java.io.IOException when there's a connection problem
     */
    public static Map<String, Stock> get(String[] symbols, Interval interval) throws IOException {
        return YahooFinance.getQuotes(Utils.join(symbols, ","), HistQuotesRequest.DEFAULT_FROM, HistQuotesRequest.DEFAULT_TO, interval);
    }
    
    /**
     * Sends a request for multiple stocks with the historical quotes included
     * starting from the specified {@link Calendar} date until today, 
     * at the default interval (monthly).
     * <p>
     * The latest quotes will be retrieved in a single request to Yahoo Finance.
     * For the historical quotes, a separate request will be sent for each requested stock.
     * The returned Map only includes the Stocks that could 
     * successfully be retrieved from Yahoo Finance.
     * 
     * @param symbols               the symbols of the stocks for which you want to retrieve information
     * @param from                  start date of the historical data
     * @return                      a Map that links the symbols to their respective Stock objects.
     * @throws java.io.IOException when there's a connection problem
     */
    public static Map<String, Stock> get(String[] symbols, Calendar from) throws IOException {
        return YahooFinance.getQuotes(Utils.join(symbols, ","), from, HistQuotesRequest.DEFAULT_TO, HistQuotesRequest.DEFAULT_INTERVAL);
    }
    
    /**
     * Sends a request for multiple stocks with the historical quotes included
     * starting from the specified {@link Calendar} date until today, 
     * at the specified interval.
     * <p>
     * The latest quotes will be retrieved in a single request to Yahoo Finance.
     * For the historical quotes, a separate request will be sent for each requested stock.
     * The returned Map only includes the Stocks that could 
     * successfully be retrieved from Yahoo Finance.
     * 
     * @param symbols               the symbols of the stocks for which you want to retrieve information
     * @param from                  start date of the historical data
     * @param interval              the interval of the included historical data
     * @return                      a Map that links the symbols to their respective Stock objects.
     * @throws java.io.IOException when there's a connection problem
     */
    public static Map<String, Stock> get(String[] symbols, Calendar from, Interval interval) throws IOException {
        return YahooFinance.getQuotes(Utils.join(symbols, ","), from, HistQuotesRequest.DEFAULT_TO, interval);
    }
    
    /**
     * Sends a request for multiple stocks with the historical quotes included
     * starting from the specified {@link Calendar} date 
     * until the specified Calendar date (to) 
     * at the default interval (monthly).
     * <p>
     * The latest quotes will be retrieved in a single request to Yahoo Finance.
     * For the historical quotes, a separate request will be sent for each requested stock.
     * The returned Map only includes the Stocks that could 
     * successfully be retrieved from Yahoo Finance.
     * 
     * @param symbols               the symbols of the stocks for which you want to retrieve information
     * @param from                  start date of the historical data
     * @param to                    end date of the historical data
     * @return                      a Map that links the symbols to their respective Stock objects.
     * @throws java.io.IOException when there's a connection problem
     */
    public static Map<String, Stock> get(String[] symbols, Calendar from, Calendar to) throws IOException {
        return YahooFinance.getQuotes(Utils.join(symbols, ","), from, to, HistQuotesRequest.DEFAULT_INTERVAL);
    }
    
    /**
     * Sends a request for multiple stocks with the historical quotes included
     * starting from the specified {@link Calendar} date 
     * until the specified Calendar date (to) 
     * at the specified interval.
     * <p>
     * The latest quotes will be retrieved in a single request to Yahoo Finance.
     * For the historical quotes, a separate request will be sent for each requested stock.
     * The returned Map only includes the Stocks that could 
     * successfully be retrieved from Yahoo Finance.
     * 
     * @param symbols               the symbols of the stocks for which you want to retrieve information
     * @param from                  start date of the historical data
     * @param to                    end date of the historical data
     * @param interval              the interval of the included historical data
     * @return                      a Map that links the symbols to their respective Stock objects.
     * @throws java.io.IOException when there's a connection problem
     */
    public static Map<String, Stock> get(String[] symbols, Calendar from, Calendar to, Interval interval) throws IOException {
        return YahooFinance.getQuotes(Utils.join(symbols, ","), from, to, interval);
    }
    
    /**
     * Sends a request for a single FX rate.
     * Some common symbols can easily be found in the ENUM {@link yahoofinance.quotes.fx.FxSymbols}
     * Some examples of accepted symbols:
     * <ul>
     * <li> EURUSD=X
     * <li> USDEUR=X
     * <li> USDGBP=X
     * <li> AUDGBP=X
     * <li> CADUSD=X
     * </ul>
     * 
     * @param symbol    symbol for the FX rate you want to request
     * @return          a quote for the requested FX rate
     * @throws java.io.IOException when there's a connection problem
     */
    public static FxQuote getFx(String symbol) throws IOException {
        if(YahooFinance.QUOTES_QUERY1V7_ENABLED.equalsIgnoreCase("true")) {
            FxQuotesQuery1V7Request request = new FxQuotesQuery1V7Request(symbol);
            return request.getSingleResult();
        } else {
            FxQuotesRequest request = new FxQuotesRequest(symbol);
            return request.getSingleResult();
        }
    }
    
    /**
     * Sends a single request to Yahoo Finance to retrieve a quote 
     * for all the requested FX symbols.
     * See <code>getFx(String)</code> for more information on the
     * accepted FX symbols.
     * 
     * @param symbols   an array of FX symbols
     * @return          the requested FX symbols mapped to their respective quotes
     * @throws java.io.IOException when there's a connection problem or the request is incorrect
     * @see             #getFx(java.lang.String) 
     */
    public static Map<String, FxQuote> getFx(String[] symbols) throws IOException {
        List<FxQuote> quotes;
        if(YahooFinance.QUOTES_QUERY1V7_ENABLED.equalsIgnoreCase("true")) {
            FxQuotesQuery1V7Request request = new FxQuotesQuery1V7Request(Utils.join(symbols, ","));
            quotes = request.getResult();
        } else {
            FxQuotesRequest request = new FxQuotesRequest(Utils.join(symbols, ","));
            quotes = request.getResult();
        }
        Map<String, FxQuote> result = new HashMap<String, FxQuote>();
        for(FxQuote quote : quotes) {
            result.put(quote.getSymbol(), quote);
        }
        return result;
    }
    
    private static Map<String, Stock> getQuotes(String query, boolean includeHistorical) throws IOException {
        Map<String, Stock> result = new HashMap<String, Stock>();
        if(YahooFinance.QUOTES_QUERY1V7_ENABLED.equalsIgnoreCase("true")) {
            StockQuotesQuery1V7Request request = new StockQuotesQuery1V7Request(query);
            List<Stock> stocks = request.getResult();
            for(Stock stock : stocks) {
                result.put(stock.getSymbol(), stock);
            }
        } else {
            StockQuotesRequest request = new StockQuotesRequest(query);
            List<StockQuotesData> quotes = request.getResult();
            for(StockQuotesData data : quotes) {
                Stock s = data.getStock();
                result.put(s.getSymbol(), s);
            }
        }

        if(includeHistorical) {
            for(Stock s : result.values()) {
                s.getHistory();
            }
        }

        return result;
    }
    
    private static Map<String, Stock> getQuotes(String query, Calendar from, Calendar to, Interval interval) throws IOException {
        Map<String, Stock> stocks = YahooFinance.getQuotes(query, false);
        stocks = YahooFinance.fetchHistoricalQuotes(stocks, from, to, interval);
        return stocks;
    }
    
    private static Map<String, Stock> fetchHistoricalQuotes(Map<String, Stock> stocks, Calendar from, Calendar to, Interval interval) throws IOException {
        for(Stock s : stocks.values()) {
            s.getHistory(from, to, interval);
        }
        return stocks;
    }
    
}
