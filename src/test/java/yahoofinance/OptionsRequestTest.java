package yahoofinance;

import org.junit.Test;
import yahoofinance.mock.MockedServersTest;
import yahoofinance.quotes.options.Option;
import yahoofinance.quotes.options.OptionsResponse;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;

import static org.hamcrest.CoreMatchers.hasItems;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

public class OptionsRequestTest extends MockedServersTest {

    @Test
    public void name() throws IOException {
        final OptionsResponse ibm = YahooFinance.getOptionsChain("IBM");
        assertThat(ibm.getStockQuote().getSymbol(), is("IBM"));

        Option call1 = Option.OptionBuilder.anOption()
                .withContractSymbol("IBM190426C00129000")
                .withStrike(129)
                .withCurrency("USD")
                .withLastPrice(14.95)
                .withChange(0)
                .withPercentChange(0)
                .withVolume(4)
                .withOpenInterest(0)
                .withBid(0)
                .withAsk(0)
                .withContractSize("REGULAR")
                .withExpiration(LocalDate.of(2019, 4, 26))
                .withLastTradeDate(LocalDateTime.of(2019, 4, 8, 13, 52, 11).atZone(ZoneId.of("America/New_York")))
                .withImpliedVolatility(1.00)
                .withInTheMoney(true)
                .build();
        Option call2 = Option.OptionBuilder.anOption()
                .withContractSymbol("IBM190503C00137000")
                .withStrike(137)
                .withCurrency("USD")
                .withLastPrice(4.13)
                .withChange(0)
                .withPercentChange(0)
                .withVolume(10)
                .withOpenInterest(57)
                .withBid(0)
                .withAsk(0)
                .withContractSize("REGULAR")
                .withExpiration(LocalDate.of(2019, 5, 3))
                .withLastTradeDate(LocalDateTime.of(2019, 4, 23, 11, 47, 28).atZone(ZoneId.of("America/New_York")))
                .withImpliedVolatility(0.01)
                .withInTheMoney(true)
                .build();
        Option call3 = Option.OptionBuilder.anOption()
                .withContractSymbol("IBM190503C00138000")
                .withStrike(138)
                .withCurrency("USD")
                .withLastPrice(2.85)
                .withChange(0)
                .withPercentChange(0)
                .withVolume(106)
                .withOpenInterest(341)
                .withBid(0)
                .withAsk(0)
                .withContractSize("REGULAR")
                .withExpiration(LocalDate.of(2019, 5, 3))
                .withLastTradeDate(LocalDateTime.of(2019, 4, 24, 15, 42, 54).atZone(ZoneId.of("America/New_York")))
                .withImpliedVolatility(0.02)
                .withInTheMoney(true)
                .build();
        assertThat(ibm.getOptionsChain().getCalls().size(), is(3));
        assertThat(ibm.getOptionsChain().getCalls(), hasItems(call1, call2, call3));

        Option put1 = Option.OptionBuilder.anOption()
                .withContractSymbol("IBM190426P00115000")
                .withStrike(115)
                .withCurrency("USD")
                .withLastPrice(0.02)
                .withChange(0)
                .withPercentChange(0)
                .withVolume(2)
                .withOpenInterest(2)
                .withBid(0)
                .withAsk(0.05)
                .withContractSize("REGULAR")
                .withExpiration(LocalDate.of(2019, 4, 26))
                .withLastTradeDate(LocalDateTime.of(2019, 4, 17, 9, 31, 6).atZone(ZoneId.of("America/New_York")))
                .withImpliedVolatility(1.09)
                .withInTheMoney(false)
                .build();

        Option put2 = Option.OptionBuilder.anOption()
                .withContractSymbol("IBM190426P00120000")
                .withStrike(120)
                .withCurrency("USD")
                .withLastPrice(0.01)
                .withChange(0)
                .withPercentChange(0)
                .withVolume(2)
                .withOpenInterest(0)
                .withBid(0)
                .withAsk(0.0)
                .withContractSize("REGULAR")
                .withExpiration(LocalDate.of(2019, 4, 26))
                .withLastTradeDate(LocalDateTime.of(2019, 4, 18, 11, 55, 28).atZone(ZoneId.of("America/New_York")))
                .withImpliedVolatility(0.5)
                .withInTheMoney(false)
                .build();


        assertThat(ibm.getOptionsChain().getPuts().size(), is(2));
        assertThat(ibm.getOptionsChain().getPuts(), hasItems(put1, put2));
    }
}
