package yahoofinance.query1v7;

import org.junit.Test;
import yahoofinance.Stock;
import yahoofinance.YahooFinance;
import yahoofinance.mock.v7.MockedV7ServersTest;
import yahoofinance.quotes.stock.ExtendedHoursStockQuoteType;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.TimeZone;

import static org.junit.Assert.*;

public class SimpleQuoteV7RequestTest extends MockedV7ServersTest {
    @Test
    public void amznClosedStockTest() throws IOException {
        Stock stock = YahooFinance.get("amzn");
        assertStock(stock);
        assertEquals(new BigDecimal("1581.89"), stock.getQuote().getAsk());
        assertEquals(new Long(1), stock.getQuote().getAskSize());
        assertEquals(new BigDecimal("1580.95"), stock.getQuote().getBid());
        assertEquals(new Long(2), stock.getQuote().getBidSize());
        assertEquals(new BigDecimal("1580.95"), stock.getQuote().getPrice());
        assertEquals(null, stock.getQuote().getLastTradeSize());
        assertEquals(new BigDecimal("1562.45"), stock.getQuote().getOpen());
        assertEquals(new BigDecimal("1572.075"), stock.getQuote().getPreviousClose());

        assertNotNull(stock.getQuote().getExtendedHoursQuote());
        assertEquals(new BigDecimal("1580.95"), stock.getQuote().getExtendedHoursQuote().getPrice());
        assertEquals(new BigDecimal("0"), stock.getQuote().getExtendedHoursQuote().getChangePercent());
        assertEquals(new BigDecimal("0"), stock.getQuote().getExtendedHoursQuote().getPriceChange());
        assertEquals(ExtendedHoursStockQuoteType.CLOSED, stock.getQuote().getExtendedHoursQuote().getType());

        assertEquals(new BigDecimal("1562.19"), stock.getQuote().getDayLow());
        assertEquals(new BigDecimal("1584.9"), stock.getQuote().getDayHigh());
        assertEquals(new BigDecimal("927"), stock.getQuote().getYearLow());
        assertEquals(new BigDecimal("1638.1"), stock.getQuote().getYearHigh());
        assertEquals(new BigDecimal("1496.9923"), stock.getQuote().getPriceAvg50());
        assertEquals(new BigDecimal("1325.1632"), stock.getQuote().getPriceAvg200());

        assertEquals(new Long(3263940), stock.getQuote().getVolume());
        assertEquals(new Long(6261022), stock.getQuote().getAvgVolume());
        assertEquals(null, stock.getQuote().getLastTradeDateStr());
        assertEquals(null, stock.getQuote().getLastTradeTimeStr());
        assertEquals(TimeZone.getTimeZone("America/New_York"), stock.getQuote().getTimeZone());

        assertEquals(new BigDecimal("8.875"), stock.getQuote().getChange());
        assertEquals(new BigDecimal("0.56"), stock.getQuote().getChangeInPercent());
        assertEquals(new BigDecimal("83.9577"), stock.getQuote().getChangeFromAvg50());
        assertEquals(new BigDecimal("5.61"), stock.getQuote().getChangeFromAvg50InPercent());
        assertEquals(new BigDecimal("255.7868"), stock.getQuote().getChangeFromAvg200());
        assertEquals(new BigDecimal("19.30"), stock.getQuote().getChangeFromAvg200InPercent());
        assertEquals(new BigDecimal("-57.15"), stock.getQuote().getChangeFromYearHigh());
        assertEquals(new BigDecimal("-3.49"), stock.getQuote().getChangeFromYearHighInPercent());
        assertEquals(new BigDecimal("653.95"), stock.getQuote().getChangeFromYearLow());
        assertEquals(new BigDecimal("70.54"), stock.getQuote().getChangeFromYearLowInPercent());

        assertEquals(new BigDecimal("767119589376"), stock.getStats().getMarketCap());
        assertEquals(null, stock.getStats().getSharesFloat());
        assertEquals(new Long(485227008), stock.getStats().getSharesOutstanding());
        assertEquals(new BigDecimal("6.15"), stock.getStats().getEps());
        assertEquals(new BigDecimal("257.06503"), stock.getStats().getPe());
        assertEquals(null, stock.getStats().getPeg());
        assertEquals(new BigDecimal("19.83"), stock.getStats().getEpsEstimateCurrentYear());
        assertNull(stock.getStats().getEpsEstimateNextQuarter());
        assertNull(stock.getStats().getEpsEstimateNextYear());
        assertEquals(new BigDecimal("27.614847"), stock.getStats().getPriceBook());
        assertNull(stock.getStats().getPriceSales());
        assertEquals(new BigDecimal("57.25"), stock.getStats().getBookValuePerShare());
        assertNull(stock.getStats().getRevenue());
        assertNull(stock.getStats().getEBITDA());
        assertNull(stock.getStats().getOneYearTargetPrice());
        assertNull(stock.getStats().getShortRatio());

        assertNull(stock.getDividend().getPayDate());
        assertNull(stock.getDividend().getAnnualYield());
        assertNull(stock.getDividend().getAnnualYieldPercent());
        assertNull(stock.getDividend().getExDate());
    }

    @Test
    public void aaplClosedStockTest() throws IOException {
        Stock stock = YahooFinance.get("aapl");
        assertStock(stock);
        assertEquals(new BigDecimal("184.1"), stock.getQuote().getAsk());
        assertEquals(new Long(10), stock.getQuote().getAskSize());
        assertEquals(new BigDecimal("184.09"), stock.getQuote().getBid());
        assertEquals(new Long(35), stock.getQuote().getBidSize());
        assertEquals(new BigDecimal("183.83"), stock.getQuote().getPrice());
        assertEquals(null, stock.getQuote().getLastTradeSize());
        assertEquals(new BigDecimal("178.25"), stock.getQuote().getOpen());
        assertEquals(new BigDecimal("176.89"), stock.getQuote().getPreviousClose());

        assertNotNull(stock.getQuote().getExtendedHoursQuote());
        assertEquals(new BigDecimal("184.1"), stock.getQuote().getExtendedHoursQuote().getPrice());
        assertEquals(new BigDecimal("0.14687715"), stock.getQuote().getExtendedHoursQuote().getChangePercent());
        assertEquals(new BigDecimal("0.27000427"), stock.getQuote().getExtendedHoursQuote().getPriceChange());
        assertEquals(ExtendedHoursStockQuoteType.CLOSED, stock.getQuote().getExtendedHoursQuote().getType());

        assertEquals(new BigDecimal("178.17"), stock.getQuote().getDayLow());
        assertEquals(new BigDecimal("184.25"), stock.getQuote().getDayHigh());
        assertEquals(new BigDecimal("142.2"), stock.getQuote().getYearLow());
        assertEquals(new BigDecimal("184.25"), stock.getQuote().getYearHigh());
        assertEquals(new BigDecimal("170.91142"), stock.getQuote().getPriceAvg50());
        assertEquals(new BigDecimal("170.96594"), stock.getQuote().getPriceAvg200());

        assertEquals(new Long(53475941), stock.getQuote().getVolume());
        assertEquals(new Long(37842941), stock.getQuote().getAvgVolume());
        assertEquals(null, stock.getQuote().getLastTradeDateStr());
        assertEquals(null, stock.getQuote().getLastTradeTimeStr());
        assertEquals(TimeZone.getTimeZone("America/New_York"), stock.getQuote().getTimeZone());

        assertEquals(new BigDecimal("6.94"), stock.getQuote().getChange());
        assertEquals(new BigDecimal("3.92"), stock.getQuote().getChangeInPercent());
        assertEquals(new BigDecimal("12.91858"), stock.getQuote().getChangeFromAvg50());
        assertEquals(new BigDecimal("7.56"), stock.getQuote().getChangeFromAvg50InPercent());
        assertEquals(new BigDecimal("12.86406"), stock.getQuote().getChangeFromAvg200());
        assertEquals(new BigDecimal("7.52"), stock.getQuote().getChangeFromAvg200InPercent());
        assertEquals(new BigDecimal("-0.42"), stock.getQuote().getChangeFromYearHigh());
        assertEquals(new BigDecimal("-0.23"), stock.getQuote().getChangeFromYearHighInPercent());
        assertEquals(new BigDecimal("41.63"), stock.getQuote().getChangeFromYearLow());
        assertEquals(new BigDecimal("29.28"), stock.getQuote().getChangeFromYearLowInPercent());

        assertEquals(new BigDecimal("932755275776"), stock.getStats().getMarketCap());
        assertEquals(null, stock.getStats().getSharesFloat());
        assertEquals(new Long(5074010112L), stock.getStats().getSharesOutstanding());
        assertEquals(new BigDecimal("9.7"), stock.getStats().getEps());
        assertEquals(new BigDecimal("18.951548"), stock.getStats().getPe());
        assertEquals(null, stock.getStats().getPeg());
        assertEquals(new BigDecimal("13.14"), stock.getStats().getEpsEstimateCurrentYear());
        assertNull(stock.getStats().getEpsEstimateNextQuarter());
        assertNull(stock.getStats().getEpsEstimateNextYear());
        assertEquals(new BigDecimal("6.6631627"), stock.getStats().getPriceBook());
        assertNull(stock.getStats().getPriceSales());
        assertEquals(new BigDecimal("27.589"), stock.getStats().getBookValuePerShare());
        assertNull(stock.getStats().getRevenue());
        assertNull(stock.getStats().getEBITDA());
        assertNull(stock.getStats().getOneYearTargetPrice());
        assertNull(stock.getStats().getShortRatio());

        assertNotNull(stock.getDividend());
        assertEquals(new BigDecimal("2.46"), stock.getDividend().getAnnualYield());
        assertEquals(new BigDecimal("1.390694800"), stock.getDividend().getAnnualYieldPercent());
        assertNull(stock.getDividend().getExDate());
    }

    @Test
    public void airPaStockTestShouldNotHaveExtendedHours() throws IOException {
        Stock stock = YahooFinance.get("air.pa");
        assertStock(stock);
        assertNull(stock.getQuote().getExtendedHoursQuote());
    }

    @Test
    public void intcAfterHoursStockTest() throws IOException {
        Stock stock = YahooFinance.get("intc");
        assertStock(stock);
        assertNotNull(stock.getQuote().getExtendedHoursQuote());
        assertEquals(new BigDecimal("54.8"), stock.getQuote().getExtendedHoursQuote().getPrice());
        assertEquals(new BigDecimal("-0.32739234"), stock.getQuote().getExtendedHoursQuote().getChangePercent());
        assertEquals(new BigDecimal("-0.1800003"), stock.getQuote().getExtendedHoursQuote().getPriceChange());
        assertEquals(ExtendedHoursStockQuoteType.POST, stock.getQuote().getExtendedHoursQuote().getType());
    }

    @Test
    public void msftPreHoursStockTest() throws IOException {
        Stock stock = YahooFinance.get("msft");
        assertStock(stock);
        assertNotNull(stock.getQuote().getExtendedHoursQuote());
        assertEquals(new BigDecimal("95.55"), stock.getQuote().getExtendedHoursQuote().getPrice());
        assertEquals(new BigDecimal("0.4098354"), stock.getQuote().getExtendedHoursQuote().getChangePercent());
        assertEquals(new BigDecimal("0.3899994"), stock.getQuote().getExtendedHoursQuote().getPriceChange());
        assertEquals(ExtendedHoursStockQuoteType.PRE, stock.getQuote().getExtendedHoursQuote().getType());
    }

    private void assertStock(Stock stock) {
        assertNotNull(stock);
        assertNotNull(stock.getQuote());
        assertNotNull(stock.getStats());
        assertNotNull(stock.getDividend());
    }
}
