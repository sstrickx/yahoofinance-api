package yahoofinance.quotes.csv;

import yahoofinance.Stock;
import yahoofinance.exchanges.ExchangeTimeZone;
import yahoofinance.quotes.stock.StockDividend;
import yahoofinance.quotes.stock.StockQuote;
import yahoofinance.quotes.stock.StockStats;
import yahoofinance.utils.BigDecimalUtil;
import yahoofinance.utils.CalendarUtil;
import yahoofinance.utils.PrimitiveTypesConvertUtils;

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
        
        quote.setPrice(BigDecimalUtil.getBigDecimal(this.getValue(QuotesProperty.LastTradePriceOnly)));
        quote.setLastTradeSize(PrimitiveTypesConvertUtils.getLong(this.getValue(QuotesProperty.LastTradeSize)));
        quote.setAsk(BigDecimalUtil.getBigDecimal(this.getValue(QuotesProperty.AskRealtime), this.getValue(QuotesProperty.Ask)));
        quote.setAskSize(PrimitiveTypesConvertUtils.getLong(this.getValue(QuotesProperty.AskSize)));
        quote.setBid(BigDecimalUtil.getBigDecimal(this.getValue(QuotesProperty.BidRealtime), this.getValue(QuotesProperty.Bid)));
        quote.setBidSize(PrimitiveTypesConvertUtils.getLong(this.getValue(QuotesProperty.BidSize)));
        quote.setOpen(BigDecimalUtil.getBigDecimal(this.getValue(QuotesProperty.Open)));
        quote.setPreviousClose(BigDecimalUtil.getBigDecimal(this.getValue(QuotesProperty.PreviousClose)));
        quote.setDayHigh(BigDecimalUtil.getBigDecimal(this.getValue(QuotesProperty.DaysHigh)));
        quote.setDayLow(BigDecimalUtil.getBigDecimal(this.getValue(QuotesProperty.DaysLow)));
        
        quote.setTimeZone(ExchangeTimeZone.getStockTimeZone(symbol));
        quote.setLastTradeDateStr(this.getValue(QuotesProperty.LastTradeDate));
        quote.setLastTradeTimeStr(this.getValue(QuotesProperty.LastTradeTime));
        quote.setLastTradeTime(CalendarUtil.parseDateTime(this.getValue(QuotesProperty.LastTradeDate), this.getValue(QuotesProperty.LastTradeTime), quote.getTimeZone()));
        
        quote.setYearHigh(BigDecimalUtil.getBigDecimal(this.getValue(QuotesProperty.YearHigh)));
        quote.setYearLow(BigDecimalUtil.getBigDecimal(this.getValue(QuotesProperty.YearLow)));
        quote.setPriceAvg50(BigDecimalUtil.getBigDecimal(this.getValue(QuotesProperty.FiftydayMovingAverage)));
        quote.setPriceAvg200(BigDecimalUtil.getBigDecimal(this.getValue(QuotesProperty.TwoHundreddayMovingAverage)));
        
        quote.setVolume(PrimitiveTypesConvertUtils.getLong(this.getValue(QuotesProperty.Volume)));
        quote.setAvgVolume(PrimitiveTypesConvertUtils.getLong(this.getValue(QuotesProperty.AverageDailyVolume)));
        
        return quote;
    }
    
    public StockStats getStats() {
        String symbol = this.getValue(QuotesProperty.Symbol);
        StockStats stats = new StockStats(symbol);
        
        stats.setMarketCap(BigDecimalUtil.getBigDecimal(this.getValue(QuotesProperty.MarketCapitalization)));
        stats.setSharesFloat(PrimitiveTypesConvertUtils.getLong(this.getValue(QuotesProperty.SharesFloat)));
        stats.setSharesOutstanding(PrimitiveTypesConvertUtils.getLong(this.getValue(QuotesProperty.SharesOutstanding)));
        stats.setSharesOwned(PrimitiveTypesConvertUtils.getLong(this.getValue(QuotesProperty.SharesOwned)));
        
        stats.setEps(BigDecimalUtil.getBigDecimal(this.getValue(QuotesProperty.DilutedEPS)));
        stats.setPe(BigDecimalUtil.getBigDecimal(this.getValue(QuotesProperty.PERatio)));
        stats.setPeg(BigDecimalUtil.getBigDecimal(this.getValue(QuotesProperty.PEGRatio)));
        
        stats.setEpsEstimateCurrentYear(BigDecimalUtil.getBigDecimal(this.getValue(QuotesProperty.EPSEstimateCurrentYear)));
        stats.setEpsEstimateNextQuarter(BigDecimalUtil.getBigDecimal(this.getValue(QuotesProperty.EPSEstimateNextQuarter)));
        stats.setEpsEstimateNextYear(BigDecimalUtil.getBigDecimal(this.getValue(QuotesProperty.EPSEstimateNextYear)));
        
        stats.setPriceBook(BigDecimalUtil.getBigDecimal(this.getValue(QuotesProperty.PriceBook)));
        stats.setPriceSales(BigDecimalUtil.getBigDecimal(this.getValue(QuotesProperty.PriceSales)));
        stats.setBookValuePerShare(BigDecimalUtil.getBigDecimal(this.getValue(QuotesProperty.BookValuePerShare)));
        
        stats.setOneYearTargetPrice(BigDecimalUtil.getBigDecimal(this.getValue(QuotesProperty.OneyrTargetPrice)));
        stats.setEBITDA(BigDecimalUtil.getBigDecimal(this.getValue(QuotesProperty.EBITDA)));
        stats.setRevenue(BigDecimalUtil.getBigDecimal(this.getValue(QuotesProperty.Revenue)));
        
        stats.setShortRatio(BigDecimalUtil.getBigDecimal(this.getValue(QuotesProperty.ShortRatio)));
        
        return stats;
    }
    
    public StockDividend getDividend() {
        String symbol = this.getValue(QuotesProperty.Symbol);
        StockDividend dividend = new StockDividend(symbol);
        
        dividend.setPayDate(CalendarUtil.parseDividendDate(this.getValue(QuotesProperty.DividendPayDate)));
        dividend.setExDate(CalendarUtil.parseDividendDate(this.getValue(QuotesProperty.ExDividendDate)));
        dividend.setAnnualYield(BigDecimalUtil.getBigDecimal(this.getValue(QuotesProperty.TrailingAnnualDividendYield)));
        dividend.setAnnualYieldPercent(BigDecimalUtil.getBigDecimal(this.getValue(QuotesProperty.TrailingAnnualDividendYieldInPercent)));
        
        return dividend;
    }
    
    public Stock getStock() {
        String symbol = this.getValue(QuotesProperty.Symbol);
        Stock stock = new Stock(symbol);
        
        stock.setName(PrimitiveTypesConvertUtils.getString(this.getValue(QuotesProperty.Name)));
        stock.setCurrency(PrimitiveTypesConvertUtils.getString(this.getValue(QuotesProperty.Currency)));
        stock.setStockExchange(PrimitiveTypesConvertUtils.getString(this.getValue(QuotesProperty.StockExchange)));
        
        stock.setQuote(this.getQuote());
        stock.setStats(this.getStats());
        stock.setDividend(this.getDividend());
        
        return stock;
    }
    
}
