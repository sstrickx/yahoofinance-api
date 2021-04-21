package stockagent;

import yahoofinance.Stock;
import yahoofinance.YahooFinance;
import yahoofinance.histquotes.HistoricalQuote;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Main {


    public static void main(String[] args) throws IOException {
<<<<<<< HEAD
        Stock stock = YahooFinance.get("INTC");
        BigDecimal price = stock.getQuote().getPrice();
        BigDecimal change = stock.getQuote().getChangeInPercent();
        BigDecimal peg = stock.getStats().getPeg();
        BigDecimal dividend = stock.getDividend().getAnnualYieldPercent();

       stock.print();



        Stock stock2 = YahooFinance.get("AAPL");
//
        BigDecimal price2 = stock.getQuote().getPrice();        
        BigDecimal change2 = stock.getQuote().getChangeInPercent();
        BigDecimal peg2 = stock.getStats().getPeg();
        BigDecimal dividend2 = stock.getDividend().getAnnualYieldPercent();
//
        stock2.print();
=======

        //Possibly use simulator class? Like we did in the pathfinder to set up our prices for stocks in the year
        //then here we can just run everything

//        String[] symbols = new String[] {"INTC", "BABA", "TSLA", "GOOG"};
//
//
//        List<Stock>stockList = new ArrayList<Stock>();
//        for(int i =0; i < symbols.length; i++){
//            stockList.add(YahooFinance.get(symbols[i]));
//        }
//        Portfolio portfolio = new Portfolio(10000);
//        MarketSensor sensor = new MarketSensor();
//
//        RuleBasedAgent agent = new RuleBasedAgent(portfolio, sensor);
//
//
//        //System.out.println(agent.chooseStock(sensor));
//
//
//
//        List<List<HistoricalQuote>>data = new ArrayList<List<HistoricalQuote>>();
//
//        for(int i =0; i < stockList.size(); i++){
//
//            data.add(sensor.getHistory(symbols[i]));
//
//        }
//
//
//        System.out.println(data);
//
>>>>>>> 7a54e3d38e488500f984602c38b03569fa420907
//
//    }

        Simulator simulator = new Simulator();
        String[] symbols = new String[] {"INTC", "BABA", "TSLA", "GOOG"};
        List<Stock>stockList = simulator.getStockInfo(symbols);




<<<<<<< HEAD
        System.out.println((stocks));
        Calendar from = Calendar.getInstance();
        Calendar to = Calendar.getInstance();
        from.add(Calendar.YEAR, -1); // from 1 year ago
//
        Stock google = YahooFinance.get(stocks.get(i));
        List<HistoricalQuote> googleHistQuotes = google.getHistory(from, to, Interval.DAILY);

//
        for(int i =0; i < googleHistQuotes.size()-1; i++) {
           System.out.println(googleHistQuotes.get(i).getClose());
        }
        System.out.println(googleHistQuotes);
        RuleBasedAgent agent = new RuleBasedAgent(10000);
=======

        //System.out.println(simulator.getHistoricalData(stockList));


>>>>>>> 7a54e3d38e488500f984602c38b03569fa420907

        RuleBasedAgent agent = new RuleBasedAgent(simulator.getPortfolio(), simulator.getSensor());

        System.out.println(agent.chooseStock(simulator.getSensor()).getSymbol());


        for(int i =0; i < simulator.getHistoricalData(stockList).size(); i++){

            agent.buyStock(simulator.getSensor(), agent.chooseStock(simulator.getSensor()).getSymbol());



        }

        System.out.println(simulator.getPortfolio().getBuyingPower());





    }

}

