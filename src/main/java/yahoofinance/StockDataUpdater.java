package yahoofinance;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import yahoofinance.quotes.csv.StockQuotesData;
import yahoofinance.quotes.csv.StockQuotesRequest;
import yahoofinance.quotes.query1v7.StockQuotesQuery1V7Request;
import yahoofinance.quotes.stock.StockDividend;
import yahoofinance.quotes.stock.StockQuote;
import yahoofinance.quotes.stock.StockStats;

import java.io.IOException;

public class StockDataUpdater {
    private static final Logger log = LoggerFactory.getLogger(StockDataUpdater.class);

    private Stock stock;
    private String symbol;

    public StockDataUpdater(Stock stock, String symbol) {
        this.stock = stock;
        this.symbol = symbol;
    }

    public void update() throws IOException {
        if(YahooFinance.QUOTES_QUERY1V7_ENABLED.equalsIgnoreCase("true")) {
            StockQuotesQuery1V7Request request = new StockQuotesQuery1V7Request(this.symbol);
            Stock stockResponse = request.getSingleResult();
            if (stockResponse != null) {
                stock.setName(stockResponse.getName());
                stock.setCurrency(stockResponse.getCurrency());
                stock.setStockExchange(stockResponse.getStockExchange());
                stock.setQuote(stockResponse.getQuote());
                stock.setStats(stockResponse.getStats());
                stock.setDividend(stockResponse.getDividend());
                log.info("Updated Stock with symbol: {}", this.symbol);
            } else {
                log.error("Failed to update Stock with symbol: {}", this.symbol);
            }
        } else {
            StockQuotesRequest request = new StockQuotesRequest(this.symbol);
            StockQuotesData data = request.getSingleResult();
            if (data != null) {
                stock.setQuote(data.getQuote());
                stock.setStats(data.getStats());
                stock.setDividend(data.getDividend());
                log.info("Updated Stock with symbol: {}", this.symbol);
            } else {
                log.error("Failed to update Stock with symbol: {}", this.symbol);
            }
        }
    }

    public StockQuote getQuote() {
        return stock.getQuote();
    }

    public StockQuote getQuote(boolean refresh) throws IOException {
        if(refresh) {
            update();
        }
        return getQuote();
    }

    public StockStats getStats() {
        return stock.getStats();
    }

    public StockStats getStats(boolean refresh) throws IOException {
        if(refresh) {
            update();
        }
        return getStats();
    }

    public StockDividend getDividend() {
        return stock.getDividend();
    }

    public StockDividend getDividend(boolean refresh) throws IOException {
        if(refresh) {
            update();
        }

        return getDividend();
    }
}
