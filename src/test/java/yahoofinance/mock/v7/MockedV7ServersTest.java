package yahoofinance.mock.v7;

import okhttp3.mockwebserver.Dispatcher;
import okhttp3.mockwebserver.MockWebServer;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import yahoofinance.mock.MockedServersTest;
import yahoofinance.mock.YahooFinanceDispatcher;

import java.io.IOException;

import static yahoofinance.YahooFinance.QUOTES_QUERY1V7_BASE_URL;

public class MockedV7ServersTest {
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
            throw new RuntimeException(e);
        }
        String quotesBaseUrl = "http://localhost:" + quotesServer.getPort() + "/v7/finance/quote";
        System.setProperty("yahoofinance.baseurl.quotesquery1v7", quotesBaseUrl);

        final Dispatcher dispatcher = new YahooFinanceDispatcher("v7/requests.yml");
        quotesServer.setDispatcher(dispatcher);
        histQuotesServer.setDispatcher(dispatcher);
    }

    @AfterClass
    public static void stopServer() {
        if (started) {
            return;
        }
        safeShutdown(histQuotesServer, "historical quotes");
        safeShutdown(quotesServer, "quotes");
    }

    private static void safeShutdown(MockWebServer webServer, String name) {
        try {
            webServer.shutdown();
        } catch (IOException e) {
            log.warn("Unable to shutdown {} mock web server", name);
        }
    }
}
