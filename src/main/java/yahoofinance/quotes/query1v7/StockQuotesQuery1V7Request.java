package yahoofinance.quotes.query1v7;

import com.fasterxml.jackson.databind.JsonNode;
import yahoofinance.Stock;
import yahoofinance.exchanges.ExchangeTimeZone;
import yahoofinance.quotes.stock.StockDividend;
import yahoofinance.quotes.stock.StockQuote;
import yahoofinance.quotes.stock.StockStats;
import yahoofinance.utils.BigDecimalUtil;
import yahoofinance.utils.CalendarUtil;
import yahoofinance.utils.PrimitiveTypesConvertUtils;

import java.math.BigDecimal;
import java.util.TimeZone;

/**
 *
 * @author Stijn Strickx
 */
public class StockQuotesQuery1V7Request extends QuotesRequest<Stock> {

    private static final BigDecimal ONE_HUNDRED = new BigDecimal(100);
    
    public StockQuotesQuery1V7Request(String symbols) {
        super(symbols);
    }

    @Override
    protected Stock parseJson(JsonNode node) {
        String symbol = node.get("symbol").asText();
        Stock stock = new Stock(symbol);

        if(node.has("longName")) {
            stock.setName(node.get("longName").asText());
        } else {
            stock.setName(getStringValue(node, "shortName"));
        }

        stock.setCurrency(getStringValue(node, "currency"));
        stock.setStockExchange(getStringValue(node, "fullExchangeName"));

        stock.setQuote(this.getQuote(node));
        stock.setStats(this.getStats(node));
        stock.setDividend(this.getDividend(node));

        return stock;
    }

    private String getStringValue(JsonNode node, String field) {
        if(node.has(field)) {
            return node.get(field).asText();
        }
        return null;
    }



    private StockQuote getQuote(JsonNode node) {
        String symbol = node.get("symbol").asText();
        StockQuote quote = new StockQuote(symbol);

        quote.setPrice(BigDecimalUtil.getBigDecimal(getStringValue(node,"regularMarketPrice")));
        quote.setAsk(BigDecimalUtil.getBigDecimal(getStringValue(node,"ask")));
        quote.setAskSize(PrimitiveTypesConvertUtils.getLong(getStringValue(node,"askSize")));
        quote.setBid(BigDecimalUtil.getBigDecimal(getStringValue(node,"bid")));
        quote.setBidSize(PrimitiveTypesConvertUtils.getLong(getStringValue(node,"bidSize")));
        quote.setOpen(BigDecimalUtil.getBigDecimal(getStringValue(node,"regularMarketOpen")));
        quote.setPreviousClose(BigDecimalUtil.getBigDecimal(getStringValue(node,"regularMarketPreviousClose")));
        quote.setDayHigh(BigDecimalUtil.getBigDecimal(getStringValue(node,"regularMarketDayHigh")));
        quote.setDayLow(BigDecimalUtil.getBigDecimal(getStringValue(node,"regularMarketDayLow")));

        if(node.has("exchangeTimezoneName")) {
            quote.setTimeZone(TimeZone.getTimeZone(node.get("exchangeTimezoneName").asText()));
        } else {
            quote.setTimeZone(ExchangeTimeZone.getStockTimeZone(symbol));
        }

        if(node.has("regularMarketTime")) {
            quote.setLastTradeTime(CalendarUtil.unixToCalendar(node.get("regularMarketTime").asLong()));
        }

        quote.setYearHigh(BigDecimalUtil.getBigDecimal(getStringValue(node,"fiftyTwoWeekHigh")));
        quote.setYearLow(BigDecimalUtil.getBigDecimal(getStringValue(node,"fiftyTwoWeekLow")));
        quote.setPriceAvg50(BigDecimalUtil.getBigDecimal(getStringValue(node,"fiftyDayAverage")));
        quote.setPriceAvg200(BigDecimalUtil.getBigDecimal(getStringValue(node,"twoHundredDayAverage")));

        quote.setVolume(PrimitiveTypesConvertUtils.getLong(getStringValue(node,"regularMarketVolume")));
        quote.setAvgVolume(PrimitiveTypesConvertUtils.getLong(getStringValue(node,"averageDailyVolume3Month")));

        return quote;
    }

    private StockStats getStats(JsonNode node) {
        String symbol = getStringValue(node,"symbol");
        StockStats stats = new StockStats(symbol);

        stats.setMarketCap(BigDecimalUtil.getBigDecimal(getStringValue(node,"marketCap")));
        stats.setSharesOutstanding(PrimitiveTypesConvertUtils.getLong(getStringValue(node,"sharesOutstanding")));
        stats.setEps(BigDecimalUtil.getBigDecimal(getStringValue(node,"epsTrailingTwelveMonths")));
        stats.setPe(BigDecimalUtil.getBigDecimal(getStringValue(node,"trailingPE")));
        stats.setEpsEstimateCurrentYear(BigDecimalUtil.getBigDecimal(getStringValue(node,"epsForward")));
        stats.setPriceBook(BigDecimalUtil.getBigDecimal(getStringValue(node,"priceToBook")));
        stats.setBookValuePerShare(BigDecimalUtil.getBigDecimal(getStringValue(node,"bookValue")));

        if(node.has("earningsTimestamp")) {
            stats.setEarningsAnnouncement(CalendarUtil.unixToCalendar(node.get("earningsTimestamp").asLong()));
        }

        return stats;
    }

    private StockDividend getDividend(JsonNode node) {
        String symbol = this.getStringValue(node, "symbol");
        StockDividend dividend = new StockDividend(symbol);

        if (node.has("dividendDate")) {
            long dividendTimestamp = node.get("dividendDate").asLong();
            dividend.setPayDate(CalendarUtil.unixToCalendar(dividendTimestamp));
            // dividend.setExDate(Utils.unixToCalendar(node.get("dividendDate").asLong()));
        }
        if (node.has("trailingAnnualDividendRate")) {
            dividend.setAnnualYield(BigDecimalUtil.getBigDecimal(this.getStringValue(node, "trailingAnnualDividendRate")));
        }
        if (node.has("trailingAnnualDividendYield")) {
            BigDecimal yield = BigDecimalUtil.getBigDecimal(this.getStringValue(node, "trailingAnnualDividendYield"));
            if (yield != null) {
                dividend.setAnnualYieldPercent(yield.multiply(ONE_HUNDRED));
            }
        }

        return dividend;
    }

}
