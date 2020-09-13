package yahoofinance.histquotes2;

import junit.framework.TestCase;

import java.math.BigDecimal;
import java.util.Calendar;
import java.util.GregorianCalendar;

public class HistSplitsRequestTest extends TestCase {

    public void testParseCSVLine() {
        String input = "2020-08-31,4:1";
        HistoricalSplit actual = new HistSplitsRequest("AAPL").parseCSVLine(input);
        HistoricalSplit expected = new HistoricalSplit("AAPL",
                new GregorianCalendar(2020, Calendar.AUGUST, 31),
                BigDecimal.valueOf(4),
                BigDecimal.valueOf(1));
        assertEquals(expected, actual);
    }

}