package yahoofinance.mock;

import okhttp3.mockwebserver.Dispatcher;
import okhttp3.mockwebserver.MockWebServer;

import org.junit.BeforeClass;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

/**
 *
 * @author Stijn Strickx
 */
public class MockedServersTest {

    private static final Logger log = LoggerFactory.getLogger(MockedServersTest.class);

    private static boolean started = false;

    public static MockWebServer quotesServer;
    public static MockWebServer histQuotesServer;

    @BeforeClass
    public static void startServers() {
        if(started) {
            return;
        }
        started = true;
        quotesServer = new MockWebServer();
        histQuotesServer = new MockWebServer();
        try {
            quotesServer.start();
            histQuotesServer.start();
        } catch (IOException e) {
            log.error("Unable to start mock web server", e);
        }
        String quotesBaseUrl = "http://localhost:" + quotesServer.getPort() + "/d/quotes.csv";
        String histQuotesBaseUrl = "http://localhost:" + histQuotesServer.getPort() + "/table.csv";

        System.setProperty("yahoofinance.baseurl.quotes", quotesBaseUrl);
        System.setProperty("yahoofinance.baseurl.histquotes", histQuotesBaseUrl);
        System.setProperty("yahoofinance.histquotes2.enabled", "false");

        final Dispatcher dispatcher = new YahooFinanceDispatcher();
        quotesServer.setDispatcher(dispatcher);
        histQuotesServer.setDispatcher(dispatcher);
    }

}
