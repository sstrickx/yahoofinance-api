package stockagent;

import yahoofinance.Stock;
import yahoofinance.YahooFinance;

import java.io.IOException;
import java.math.BigDecimal;

public class Main {


    public static void main(String[] args) throws IOException {
        Stock stock = YahooFinance.get("INTC");

        BigDecimal price = stock.getQuote().getPrice();
        BigDecimal change = stock.getQuote().getChangeInPercent();
        BigDecimal peg = stock.getStats().getPeg();
        BigDecimal dividend = stock.getDividend().getAnnualYieldPercent();

        stock.print();



        Stock stock2 = YahooFinance.get("AAPL");

        BigDecimal price2 = stock.getQuote().getPrice();
        BigDecimal change2 = stock.getQuote().getChangeInPercent();
        BigDecimal peg2 = stock.getStats().getPeg();
        BigDecimal dividend2 = stock.getDividend().getAnnualYieldPercent();

        stock2.print();


        //simulate some dates and environment through some time with
        //List of stocks and quotes

    }
}
