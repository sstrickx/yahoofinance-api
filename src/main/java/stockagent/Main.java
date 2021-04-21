package stockagent;

import yahoofinance.Stock;
import yahoofinance.YahooFinance;
import yahoofinance.histquotes.HistoricalQuote;
import yahoofinance.histquotes.Interval;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Map;

public class Main {


    public static void main(String[] args) throws IOException {

        //Possibly use simulator class? Like we did in the pathfinder to set up our prices for stocks in the year
        //then here we can just run everything



        String[] symbols = new String[] {"INTC", "BABA", "TSLA"};


        List<Stock>stockList = new ArrayList<Stock>();
        for(int i =0; i < symbols.length; i++){
            stockList.add(YahooFinance.get(symbols[i]));
        }

        //System.out.println(stockList);


        RuleBasedAgent agent = new RuleBasedAgent(10000);
        LocalSensor sensor = new LocalSensor();
        List<List<HistoricalQuote>>prices = new ArrayList<List<HistoricalQuote>>();
        for(int i =0; i < stockList.size(); i++){
            prices.add(sensor.getHistory(symbols[i]));

        }



        for(int i =0; i < prices.size(); i++) {
            System.out.println(prices.get(i).get(i));

        }
        //System.out.println(prices);





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
