import yahoofinance.Stock;
import yahoofinance.YahooFinance;

import java.math.BigDecimal;

/**
 * Created by admin on 3/21/16.
 */

public class Test
{

    public static void main(String[] args) throws Exception
    {
        Stock stock = YahooFinance.get("FAKECOMPANY");

        BigDecimal price = stock.getQuote().getPrice();
        BigDecimal change = stock.getQuote().getChangeInPercent();
        BigDecimal marketCap = stock.getStats().getMarketCap();
        BigDecimal dividend = stock.getDividend().getAnnualYieldPercent();

        System.out.println(marketCap);

    }

}
