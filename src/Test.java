import yahoofinance.Stock;
import yahoofinance.YahooFinance;

/**
 * Created by admin on 3/21/16.
 */

public class Test
{

    public static void main(String[] args) throws Exception
    {
        Stock stock = YahooFinance.get("AAPL");

        System.out.println(stock.getName());

    }

}
