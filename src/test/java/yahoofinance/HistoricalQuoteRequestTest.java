package yahoofinance;

import org.junit.Before;
import org.junit.Test;
import yahoofinance.histquotes.HistoricalQuote;
import yahoofinance.histquotes.Interval;
import yahoofinance.mock.MockedServersTest;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.Calendar;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.*;

/**
 *
 * @author Stijn Strickx
 */
public class HistoricalQuoteRequestTest extends MockedServersTest {

    private Calendar today;
    private Calendar from;

    @Before
    public void setup() {
        today = Calendar.getInstance();
        today.set(Calendar.YEAR, 2016);
        today.set(Calendar.MONTH, 8);
        today.set(Calendar.DATE, 11);

        from = (Calendar) today.clone();
        from.add(Calendar.YEAR, -1);
    }

    @Test
    public void historicalQuoteTest() throws IOException {
        Stock goog = YahooFinance.get("GOOG", from, today);

        assertNotNull(goog.getHistory());
        assertEquals(13, goog.getHistory().size());

        for(HistoricalQuote histQuote : goog.getHistory()) {
            assertEquals("GOOG", histQuote.getSymbol());
            assertTrue(histQuote.getAdjClose().compareTo(BigDecimal.ZERO) > 0);
            assertTrue(histQuote.getClose().compareTo(BigDecimal.ZERO) > 0);
            assertTrue(histQuote.getHigh().compareTo(BigDecimal.ZERO) > 0);
            assertTrue(histQuote.getLow().compareTo(BigDecimal.ZERO) > 0);
            assertTrue(histQuote.getOpen().compareTo(BigDecimal.ZERO) > 0);
            assertTrue(histQuote.getVolume() > 0);
            assertNotNull(histQuote.getDate());
        }

        HistoricalQuote histQuote = goog.getHistory().get(5);

        assertEquals(new BigDecimal("693.01001"), histQuote.getAdjClose());
        assertEquals(new BigDecimal("693.01001"), histQuote.getClose());
        assertEquals(new BigDecimal("769.900024"), histQuote.getHigh());
        assertEquals(new BigDecimal("689.00"), histQuote.getLow());
        assertEquals(new BigDecimal("738.599976"), histQuote.getOpen());
        assertEquals(new Long(2125700), histQuote.getVolume());
        assertEquals(3, histQuote.getDate().get(Calendar.MONTH));
        assertEquals(1, histQuote.getDate().get(Calendar.DATE));
        assertEquals(2016, histQuote.getDate().get(Calendar.YEAR));

    }

    @Test
    public void intervalTest() throws IOException {
        Stock tsla = YahooFinance.get("TSLA", from, today, Interval.DAILY);
        Stock scty = YahooFinance.get("SCTY", from, today, Interval.WEEKLY);
        Stock goog = YahooFinance.get("GOOG", from, today, Interval.MONTHLY);

        assertEquals(252, tsla.getHistory().size());
        assertEquals(53, scty.getHistory().size());
        assertEquals(13, goog.getHistory().size());
    }

    @Test
    public void multiYearTest() throws IOException {
        Calendar from = (Calendar) today.clone();
        Calendar to = (Calendar) today.clone();
        from.add(Calendar.YEAR, -5); // from 5 years ago

        Stock goog = YahooFinance.get("GOOG", from, to, Interval.WEEKLY);

        assertEquals(261, goog.getHistory().size());

        HistoricalQuote histQuote = goog.getHistory().get(0);
        assertEquals(8, histQuote.getDate().get(Calendar.MONTH));
        assertEquals(6, histQuote.getDate().get(Calendar.DATE));
        assertEquals(2016, histQuote.getDate().get(Calendar.YEAR));

        histQuote = goog.getHistory().get(260);
        assertEquals(8, histQuote.getDate().get(Calendar.MONTH));
        assertEquals(12, histQuote.getDate().get(Calendar.DATE));
        assertEquals(2011, histQuote.getDate().get(Calendar.YEAR));

    }

    @Test
    public void multiStockTest() throws IOException {
        String[] symbols = new String[] {"INTC", "AIR.PA"};
        Map<String, Stock> stocks = YahooFinance.get(symbols, from, today);
        Stock intel = stocks.get("INTC");
        Stock airbus = stocks.get("AIR.PA");

        assertEquals(13, intel.getHistory().size());
        assertEquals(13, airbus.getHistory().size());
        assertEquals("INTC", intel.getHistory().get(3).getSymbol());
        assertEquals("AIR.PA", airbus.getHistory().get(5).getSymbol());
    }

    @Test
    public void historicalFlowTest() throws IOException {
        Stock goog = YahooFinance.get("GOOG");
        int requestCount = MockedServersTest.histQuotesServer.getRequestCount();
        assertNotNull(goog.getHistory(from, today));
        requestCount += 1;
        assertEquals(requestCount, MockedServersTest.histQuotesServer.getRequestCount());
        assertEquals(13, goog.getHistory().size());
        assertEquals(requestCount, MockedServersTest.histQuotesServer.getRequestCount());

        Calendar from = (Calendar) today.clone();
        Calendar to = (Calendar) today.clone();
        from.add(Calendar.YEAR, -5); // from 5 years ago
        assertNotNull(goog.getHistory(from, to, Interval.WEEKLY));
        requestCount += 1;
        assertEquals(requestCount, MockedServersTest.histQuotesServer.getRequestCount());
        assertEquals(261, goog.getHistory().size());
    }

    @Test
    public void impossibleRequestTest() throws IOException {
        Calendar from = Calendar.getInstance();
        Calendar to = Calendar.getInstance();
        from.add(Calendar.DATE, 2); // from > to
        Exception reqEx = null;

        Stock goog = YahooFinance.get("GOOG");
        List<HistoricalQuote> histQuotes = null;
        int requestCount = MockedServersTest.histQuotesServer.getRequestCount();
        try {
            histQuotes = goog.getHistory(from, to);
        } catch (IOException ex) {
            reqEx = ex;
        }
        // Didn't send any requests since the problem was detected
        assertEquals(requestCount, MockedServersTest.histQuotesServer.getRequestCount());
        assertNull(reqEx);
        assertEquals(0, histQuotes.size());
    }

}
