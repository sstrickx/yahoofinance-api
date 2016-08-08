package yahoofinance;

import org.junit.Test;
import yahoofinance.mock.MockedServersTest;

import java.io.IOException;

/**
 *
 * @author Stijn Strickx
 */
public class SimpleQuoteRequestTest extends MockedServersTest {

    @Test
    public void europeanStockQuoteTest() throws IOException {
        Stock stock = YahooFinance.get("AIR.PA");
        stock.print();
    }

}
