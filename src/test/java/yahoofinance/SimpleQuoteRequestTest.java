package yahoofinance;

import org.junit.Test;

import yahoofinance.mock.MockedServersTest;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.Calendar;
import java.util.TimeZone;

import static org.junit.Assert.*;

/**
 *
 * @author Stijn Strickx
 */
public class SimpleQuoteRequestTest extends MockedServersTest {

    @Test
    public void europeStockQuoteTest() throws IOException {
        Stock stock = YahooFinance.get("AIR.PA");

        assertEquals("AIR.PA", stock.getSymbol());
        assertEquals("AIRBUS GROUP", stock.getName());
        assertEquals("EUR", stock.getCurrency());
        assertEquals("PAR", stock.getStockExchange());

        assertNotNull(stock.getQuote());
        assertNotNull(stock.getStats());
        assertNotNull(stock.getDividend());

        assertEquals(new BigDecimal("54.93"), stock.getQuote().getAsk());
        assertEquals(new Long(700), stock.getQuote().getAskSize());
        assertEquals(new BigDecimal("54.00"), stock.getQuote().getBid());
        assertEquals(new Long(42000), stock.getQuote().getBidSize());
        assertEquals(new BigDecimal("50.34"), stock.getQuote().getPrice());
        assertEquals(new Long(813), stock.getQuote().getLastTradeSize());
        assertEquals(new BigDecimal("50.58"), stock.getQuote().getOpen());
        assertEquals(new BigDecimal("51.00"), stock.getQuote().getPreviousClose());

        assertEquals(new BigDecimal("50.10"), stock.getQuote().getDayLow());
        assertEquals(new BigDecimal("50.85"), stock.getQuote().getDayHigh());
        assertEquals(new BigDecimal("48.07"), stock.getQuote().getYearLow());
        assertEquals(new BigDecimal("68.50"), stock.getQuote().getYearHigh());
        assertEquals(new BigDecimal("51.81"), stock.getQuote().getPriceAvg50());
        assertEquals(new BigDecimal("55.21"), stock.getQuote().getPriceAvg200());

        assertEquals(new Long(1460112), stock.getQuote().getVolume());
        assertEquals(new Long(2211770), stock.getQuote().getAvgVolume());
        assertEquals("8/8/2016", stock.getQuote().getLastTradeDateStr());
        assertEquals("5:35pm", stock.getQuote().getLastTradeTimeStr());
        assertEquals(TimeZone.getTimeZone("Europe/Paris"), stock.getQuote().getTimeZone());

        assertEquals(new BigDecimal("-0.66"), stock.getQuote().getChange());
        assertEquals(new BigDecimal("-1.29"), stock.getQuote().getChangeInPercent());
        assertEquals(new BigDecimal("-1.47"), stock.getQuote().getChangeFromAvg50());
        assertEquals(new BigDecimal("-2.84"), stock.getQuote().getChangeFromAvg50InPercent());
        assertEquals(new BigDecimal("-4.87"), stock.getQuote().getChangeFromAvg200());
        assertEquals(new BigDecimal("-8.82"), stock.getQuote().getChangeFromAvg200InPercent());
        assertEquals(new BigDecimal("-18.16"), stock.getQuote().getChangeFromYearHigh());
        assertEquals(new BigDecimal("-26.51"), stock.getQuote().getChangeFromYearHighInPercent());
        assertEquals(new BigDecimal("2.27"), stock.getQuote().getChangeFromYearLow());
        assertEquals(new BigDecimal("4.72"), stock.getQuote().getChangeFromYearLowInPercent());

        assertEquals(new BigDecimal("38880000000.00"), stock.getStats().getMarketCap());
        assertEquals(new Long(654166000), stock.getStats().getSharesFloat());
        assertEquals(new Long(772397000), stock.getStats().getSharesOutstanding());
        assertEquals(new BigDecimal("3.74"), stock.getStats().getEps());
        assertEquals(new BigDecimal("13.47"), stock.getStats().getPe());
        assertEquals(new BigDecimal("0.00"), stock.getStats().getPeg());
        assertEquals(new BigDecimal("0.83"), stock.getStats().getEpsEstimateCurrentYear());
        assertEquals(new BigDecimal("0.00"), stock.getStats().getEpsEstimateNextQuarter());
        assertNull(stock.getStats().getEpsEstimateNextYear());
        assertEquals(new BigDecimal("6.51"), stock.getStats().getPriceBook());
        assertEquals(new BigDecimal("0.61"), stock.getStats().getPriceSales());
        assertEquals(new BigDecimal("7.84"), stock.getStats().getBookValuePerShare());
        assertEquals(new BigDecimal("64310000000.00"), stock.getStats().getRevenue());
        assertEquals(new BigDecimal("4800000000.00"), stock.getStats().getEBITDA());
        assertNull(stock.getStats().getOneYearTargetPrice());
        assertEquals(new BigDecimal("0.00"), stock.getStats().getShortRatio());

        assertNull(stock.getDividend().getPayDate());
        assertNull(stock.getDividend().getAnnualYield());
        assertNull(stock.getDividend().getAnnualYieldPercent());
        assertEquals(4, stock.getDividend().getExDate().get(Calendar.MONTH));
        assertEquals(2, stock.getDividend().getExDate().get(Calendar.DAY_OF_MONTH));
    }

    @Test
    public void usStockQuoteTest() throws IOException {
        Stock stock = YahooFinance.get("INTC");

        assertEquals("INTC", stock.getSymbol());
        assertEquals("Intel Corporation", stock.getName());
        assertEquals("USD", stock.getCurrency());
        assertEquals("NMS", stock.getStockExchange());

        assertNotNull(stock.getQuote());
        assertNotNull(stock.getStats());
        assertNotNull(stock.getDividend());

        /*
         * Just check a few to make sure everything is fine
         * Most things already tested by europeanStockQuoteTest
         */
        assertEquals(new BigDecimal("35.03"), stock.getQuote().getAsk());
        assertEquals(new Long(1699919), stock.getQuote().getLastTradeSize());
        assertEquals(new BigDecimal("34.98"), stock.getQuote().getPreviousClose());
        assertEquals(new BigDecimal("35.93"), stock.getQuote().getYearHigh());

        assertEquals(new BigDecimal("2.80"), stock.getStats().getPeg());
        assertEquals(new BigDecimal("37.61"), stock.getStats().getOneYearTargetPrice());
        assertEquals(new BigDecimal("2.94"), stock.getStats().getShortRatio());

        assertEquals(5, stock.getDividend().getPayDate().get(Calendar.MONTH));
        assertEquals(1, stock.getDividend().getPayDate().get(Calendar.DAY_OF_MONTH));
        assertEquals(7, stock.getDividend().getExDate().get(Calendar.MONTH));
        assertEquals(3, stock.getDividend().getExDate().get(Calendar.DAY_OF_MONTH));
        assertEquals(new BigDecimal("1.04"), stock.getDividend().getAnnualYield());
        assertEquals(new BigDecimal("2.97"), stock.getDividend().getAnnualYieldPercent());
    }

    @Test
    public void singaporeStockQuoteTest() throws IOException {
        Stock stock = YahooFinance.get("C6L.SI");

        assertEquals("C6L.SI", stock.getSymbol());
        assertEquals("SIA", stock.getName());
        assertEquals("SGD", stock.getCurrency());
        assertEquals("SES", stock.getStockExchange());

        assertNotNull(stock.getQuote());
        assertNotNull(stock.getStats());
        assertNotNull(stock.getDividend());

        assertEquals(new BigDecimal("10.89"), stock.getQuote().getAsk());
        assertEquals(new Long(300), stock.getQuote().getLastTradeSize());
        assertEquals(new BigDecimal("10.84"), stock.getQuote().getPreviousClose());
        assertEquals(new BigDecimal("9.57"), stock.getQuote().getYearLow());

        assertEquals(new BigDecimal("0.00"), stock.getStats().getPeg());
        assertEquals(new BigDecimal("0.82"), stock.getStats().getEps());
        assertEquals(new BigDecimal("11.44"), stock.getStats().getBookValuePerShare());
        assertNull(stock.getStats().getOneYearTargetPrice());
        assertEquals(new BigDecimal("0.00"), stock.getStats().getShortRatio());

        assertEquals(7, stock.getDividend().getExDate().get(Calendar.MONTH));
        assertEquals(2, stock.getDividend().getExDate().get(Calendar.DAY_OF_MONTH));
        assertNull(stock.getDividend().getAnnualYield());
        assertNull(stock.getDividend().getAnnualYieldPercent());
    }

}
