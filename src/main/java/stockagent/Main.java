package stockagent;

import yahoofinance.Stock;
import yahoofinance.YahooFinance;
import yahoofinance.histquotes.HistoricalQuote;
import yahoofinance.histquotes.Interval;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.Calendar;
import java.util.List;
import java.util.Map;

public class Main {


    public static void main(String[] args) throws IOException {
//        Stock stock = YahooFinance.get("INTC");
//
//        BigDecimal price = stock.getQuote().getPrice();
//        BigDecimal change = stock.getQuote().getChangeInPercent();
//        BigDecimal peg = stock.getStats().getPeg();
//        BigDecimal dividend = stock.getDividend().getAnnualYieldPercent();
//
//       // stock.print();
//
//
//
//        Stock stock2 = YahooFinance.get("AAPL");
//
//        BigDecimal price2 = stock.getQuote().getPrice();
//        BigDecimal change2 = stock.getQuote().getChangeInPercent();
//        BigDecimal peg2 = stock.getStats().getPeg();
//        BigDecimal dividend2 = stock.getDividend().getAnnualYieldPercent();
//
//        //stock2.print();
//

        //simulate some dates and environment through some time with
        //List of stocks and quotes


        String[] symbols = new String[] {"INTC", "BABA", "TSLA", "AIR.PA", "YHOO"};
        Map<String, Stock> stocks = YahooFinance.get(symbols);


//        System.out.println((stocks));
//        Calendar from = Calendar.getInstance();
//        Calendar to = Calendar.getInstance();
//        from.add(Calendar.YEAR, -1); // from 1 year ago
//
//        Stock google = YahooFinance.get(stocks.get(i));
//        List<HistoricalQuote> googleHistQuotes = google.getHistory(from, to, Interval.DAILY);

//
//        for(int i =0; i < googleHistQuotes.size()-1; i++) {
//            System.out.println(googleHistQuotes.get(i).getClose());
//        }
        //System.out.println(googleHistQuotes);
        //RuleBasedAgent agent = new RuleBasedAgent(10000);


    //system can ask agent what to buy and sell rn
        //or agent to be able to have access to information and it buys and sells now and has to be inside some bigger thing
        //to make those decisions









    }
}
