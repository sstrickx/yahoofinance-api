package yahoofinance.quotes.stock;

import yahoofinance.Stock;
import yahoofinance.Utils;
import yahoofinance.quotes.QuotesProperty;

/**
 *
 * @author Stijn Strickx
 */
public class StockQuotesData {
    
    private final String[] data;
    
    public StockQuotesData(String[] data) {
        this.data = data;
    }
    
    public String getValue(QuotesProperty property) {
        int i = StockQuotesRequest.DEFAULT_PROPERTIES.indexOf(property);
        if(i >= 0 && i < this.data.length) {
            return this.data[i];
        }
        return null;
    }
    
    public StockQuote getQuote() {
        String symbol = this.getValue(QuotesProperty.Symbol);
        StockQuote quote = new StockQuote(symbol);
        
        quote.setPrice(Utils.getDouble(this.getValue(QuotesProperty.LastTradePriceOnly)));
        quote.setLastTradeSize(Utils.getInt(this.getValue(QuotesProperty.LastTradeSize)));
        quote.setAsk(Utils.getDouble(this.getValue(QuotesProperty.AskRealtime)));
        quote.setAskSize(Utils.getInt(this.getValue(QuotesProperty.AskSize)));
        quote.setBid(Utils.getDouble(this.getValue(QuotesProperty.BidRealtime)));
        quote.setBidSize(Utils.getInt(this.getValue(QuotesProperty.BidSize)));
        quote.setOpen(Utils.getDouble(this.getValue(QuotesProperty.Open)));
        quote.setPreviousClose(Utils.getDouble(this.getValue(QuotesProperty.PreviousClose)));
        quote.setDayHigh(Utils.getDouble(this.getValue(QuotesProperty.DaysHigh)));
        quote.setDayLow(Utils.getDouble(this.getValue(QuotesProperty.DaysLow)));
        
        quote.setLastTradeTime(Utils.parseDateTime(this.getValue(QuotesProperty.LastTradeDate), this.getValue(QuotesProperty.LastTradeTime)));
        
        quote.setYearHigh(Utils.getDouble(this.getValue(QuotesProperty.YearHigh)));
        quote.setYearLow(Utils.getDouble(this.getValue(QuotesProperty.YearLow)));
        quote.setPriceAvg50(Utils.getDouble(this.getValue(QuotesProperty.FiftydayMovingAverage)));
        quote.setPriceAvg200(Utils.getDouble(this.getValue(QuotesProperty.TwoHundreddayMovingAverage)));
        
        quote.setVolume(Utils.getLong(this.getValue(QuotesProperty.Volume)));
        quote.setAvgVolume(Utils.getLong(this.getValue(QuotesProperty.AverageDailyVolume)));
        
        return quote;
    }
    
    public StockStats getStats() {
        String symbol = this.getValue(QuotesProperty.Symbol);
        StockStats stats = new StockStats(symbol);
        
        stats.setMarketCap(Utils.getDouble(this.getValue(QuotesProperty.MarketCapitalization)));
        stats.setSharesFloat(Utils.getLong(this.getValue(QuotesProperty.SharesFloat)));
        stats.setSharesOutstanding(Utils.getLong(this.getValue(QuotesProperty.SharesOutstanding)));
        stats.setSharesOwned(Utils.getLong(this.getValue(QuotesProperty.SharesOwned)));
        
        stats.setEps(Utils.getDouble(this.getValue(QuotesProperty.DilutedEPS)));
        stats.setPe(Utils.getDouble(this.getValue(QuotesProperty.PERatio)));
        stats.setPeg(Utils.getDouble(this.getValue(QuotesProperty.PEGRatio)));
        
        stats.setEpsEstimateCurrentYear(Utils.getDouble(this.getValue(QuotesProperty.EPSEstimateCurrentYear)));
        stats.setEpsEstimateNextQuarter(Utils.getDouble(this.getValue(QuotesProperty.EPSEstimateNextQuarter)));
        stats.setEpsEstimateNextYear(Utils.getDouble(this.getValue(QuotesProperty.EPSEstimateNextYear)));
        
        stats.setPriceBook(Utils.getDouble(this.getValue(QuotesProperty.PriceBook)));
        stats.setPriceSales(Utils.getDouble(this.getValue(QuotesProperty.PriceSales)));
        stats.setBookValuePerShare(Utils.getDouble(this.getValue(QuotesProperty.BookValuePerShare)));
        
        stats.setOneYearTargetPrice(Utils.getDouble(this.getValue(QuotesProperty.OneyrTargetPrice)));
        stats.setEBITDA(Utils.getDouble(this.getValue(QuotesProperty.EBITDA)));
        stats.setRevenue(Utils.getDouble(this.getValue(QuotesProperty.Revenue)));
        
        return stats;
    }
    
    public StockDividend getDividend() {
        String symbol = this.getValue(QuotesProperty.Symbol);
        StockDividend dividend = new StockDividend(symbol);
        
        dividend.setPayDate(Utils.parseDividendDate(this.getValue(QuotesProperty.DividendPayDate)));
        dividend.setExDate(Utils.parseDividendDate(this.getValue(QuotesProperty.ExDividendDate)));
        dividend.setAnnualYield(Utils.getDouble(this.getValue(QuotesProperty.TrailingAnnualDividendYield)));
        dividend.setAnnualYieldPercent(Utils.getDouble(this.getValue(QuotesProperty.TrailingAnnualDividendYieldInPercent)));
        
        return dividend;
    }
    
    public Stock getStock() {
        String symbol = this.getValue(QuotesProperty.Symbol);
        Stock stock = new Stock(symbol);
        
        stock.setName(this.getValue(QuotesProperty.Name));
        stock.setCurrency(this.getValue(QuotesProperty.Currency));
        stock.setStockExchange(this.getValue(QuotesProperty.StockExchange));
        
        stock.setQuote(this.getQuote());
        stock.setStats(this.getStats());
        stock.setDividend(this.getDividend());
        
        return stock;
    }
    
}
