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

        //Possibly use simulator class? Like we did in the pathfinder to set up our prices for stocks in the year
        //then here we can just run everything
//
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
//
//    }

        Simulator simulator = new Simulator();
        String[] symbols = new String[] {"INTC", "BABA", "TSLA", "GOOG"};
        List<Stock>stockList = simulator.getStockInfo(symbols);





        //System.out.println(simulator.getHistoricalData(stockList));



        RuleBasedAgent agent = new RuleBasedAgent(simulator.getPortfolio(), simulator.getSensor());

        //System.out.println(agent.chooseStock(simulator.getSensor()).getSymbol());


        for(int i =0; i < simulator.getHistoricalData(stockList).size(); i++){

            simulator.getPortfolio().buyStock(simulator.getSensor(), agent.chooseStock(simulator.getSensor()).getSymbol());




        }

        simulator.getPortfolio();





    }

}

