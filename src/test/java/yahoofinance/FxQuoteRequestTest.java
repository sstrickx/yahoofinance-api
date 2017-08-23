package yahoofinance;

import org.junit.Test;
import yahoofinance.mock.MockedServersTest;
import yahoofinance.quotes.fx.FxQuote;
import yahoofinance.quotes.fx.FxSymbols;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.Map;

import static org.junit.Assert.*;

/**
 *
 * @author Stijn Strickx
 */
public class FxQuoteRequestTest extends MockedServersTest {

    @Test
    public void fxQuoteTest() throws IOException {
        FxQuote gbpeur = YahooFinance.getFx(FxSymbols.GBPEUR);
        assertEquals(new BigDecimal("1.1806"), gbpeur.getPrice());
        assertEquals(FxSymbols.GBPEUR, gbpeur.getSymbol());

        FxQuote usdeur = YahooFinance.getFx(FxSymbols.USDEUR);
        assertEquals(new BigDecimal("0.8898"), usdeur.getPrice());

        Map<String, FxQuote> fxQuotes = YahooFinance.getFx(new String[]{FxSymbols.EURUSD, FxSymbols.EURCHF});
        FxQuote eurusd = fxQuotes.get(FxSymbols.EURUSD);
        FxQuote eurchf = fxQuotes.get(FxSymbols.EURCHF);
        assertEquals(new BigDecimal("1.1235"), eurusd.getPrice());
        assertEquals(FxSymbols.EURUSD, eurusd.getSymbol());
        assertEquals(new BigDecimal("1.0954"), eurchf.getPrice());
        assertEquals(FxSymbols.EURCHF, eurchf.getSymbol());
    }

    @Test
    public void fxFlowTest() throws IOException {
        int requestCount = MockedServersTest.quotesServer.getRequestCount();
        FxQuote gbpeur = YahooFinance.getFx(FxSymbols.GBPEUR);
        requestCount += 1;
        assertEquals(requestCount, MockedServersTest.quotesServer.getRequestCount());
        gbpeur.getPrice();
        assertEquals(requestCount, MockedServersTest.quotesServer.getRequestCount());
        gbpeur.getPrice(true);
        requestCount += 1;
        assertEquals(requestCount, MockedServersTest.quotesServer.getRequestCount());
    }
}
