package yahoofinance.mock;

import okhttp3.mockwebserver.Dispatcher;
import okhttp3.mockwebserver.MockWebServer;
import org.junit.Before;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Stijn Strickx
 */
public class MockedServersTest {

    public static final Logger LOG = Logger.getLogger(MockedServersTest.class.getName());

    private MockWebServer quotesServer;
    private MockWebServer histQuotesServer;

    @Before
    public void setup() {
        quotesServer = new MockWebServer();
        histQuotesServer = new MockWebServer();
        try {
            quotesServer.start();
            histQuotesServer.start();
        } catch (IOException e) {
            LOG.log(Level.SEVERE, "Unable to start mock web server", e);
        }

        String quotesBaseUrl = "http://localhost:" + quotesServer.getPort() + "/d/quotes.csv";
        String histQuotesBaseUrl = "http://localhost:" + histQuotesServer.getPort() + "/table.csv";

        System.setProperty("yahoofinance.baseurl.quotes", quotesBaseUrl);
        System.setProperty("yahoofinance.baseurl.histquotes", histQuotesBaseUrl);

        final Dispatcher dispatcher = new YahooFinanceDispatcher();
        quotesServer.setDispatcher(dispatcher);
        histQuotesServer.setDispatcher(dispatcher);
    }

}
