package yahoofinance;

import org.junit.Test;
import yahoofinance.mock.MockedServersTest;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Map;

import static org.junit.Assert.*;

/**
 *
 * @author Stijn Strickx
 */
public class QuoteRequestFlowTest extends MockedServersTest {

    @Test
    public void quoteRefreshTest() throws IOException {
        Stock stock = YahooFinance.get("TSLA");

        assertEquals(new BigDecimal("226.16"), stock.getQuote().getPrice());

        stock.getQuote().setPrice(new BigDecimal("276.34"));
        assertEquals(new BigDecimal("276.34"), stock.getQuote().getPrice());
        stock.getQuote(false);
        assertEquals(new BigDecimal("276.34"), stock.getQuote().getPrice());

        stock.getQuote(true);
        assertEquals(new BigDecimal("226.16"), stock.getQuote().getPrice());
    }

    @Test
    public void statsRefreshTest() throws IOException {
        Stock stock = YahooFinance.get("AIR.PA");

        assertEquals(new BigDecimal("13.47"), stock.getStats().getPe());

        stock.getStats().setPe(new BigDecimal("10.81"));
        assertEquals(new BigDecimal("10.81"), stock.getStats().getPe());
        stock.getStats(false);
        assertEquals(new BigDecimal("10.81"), stock.getStats().getPe());

        stock.getStats(true);
        assertEquals(new BigDecimal("13.47"), stock.getStats().getPe());
    }

    @Test
    public void dividendRefreshTest() throws IOException {
        Stock stock = YahooFinance.get("INTC");

        assertEquals(new BigDecimal("1.04"), stock.getDividend().getAnnualYield());

        stock.getDividend().setAnnualYield(new BigDecimal("1.32"));
        assertEquals(new BigDecimal("1.32"), stock.getDividend().getAnnualYield());
        stock.getDividend(false);
        assertEquals(new BigDecimal("1.32"), stock.getDividend().getAnnualYield());

        stock.getDividend(true);
        assertEquals(new BigDecimal("1.04"), stock.getDividend().getAnnualYield());
    }

    @Test
    public void multipleQuoteRequestTest() throws IOException {
        Map<String, Stock> stocks = YahooFinance.get(new String[]{"AIR.PA", "INTC", "C6L.SI"});
        assertTrue(stocks.containsKey("AIR.PA"));
        assertTrue(stocks.containsKey("INTC"));
        assertTrue(stocks.containsKey("C6L.SI"));

        Stock airbus = stocks.get("AIR.PA");
        Stock intel = stocks.get("INTC");
        Stock sia = stocks.get("C6L.SI");

        assertEquals(new BigDecimal("54.00"), airbus.getQuote().getBid());
        assertEquals(new BigDecimal("37.61"), intel.getStats().getOneYearTargetPrice());
        assertEquals(7, sia.getDividend().getExDate().get(Calendar.MONTH));
    }

}
