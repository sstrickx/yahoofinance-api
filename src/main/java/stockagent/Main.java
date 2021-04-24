package stockagent;


import yahoofinance.Stock;
import yahoofinance.YahooFinance;
import yahoofinance.histquotes.HistoricalQuote;

import java.io.IOException;
import java.util.*;

public class Main {


    public static void main(String[] args) throws IOException {



        StockAgent agent = new RuleBasedAgent();

        Simulator simulator = new Simulator(agent);

        Calendar from = Calendar.getInstance();
        Calendar to = Calendar.getInstance();
        MarketSensor sensor = new MarketSensor(from, to);

        String[] symbols = new String[] {"INTC", "BABA", "TSLA", "GOOG"};


        List<Stock> stockList  = simulator.getStockInfo(symbols);


        System.out.println(stockList);

        Map<Stock, List<HistoricalQuote>>historicalData;









        //System.out.println(agent.chooseStock(simulator.getSensor()).getSymbol());









        historicalData = simulator.getHistoricalData(stockList);


        System.out.println(historicalData);


//        for(int i =0; i < historicalData.size(); i++){
//
//
//            //agent.buyStock(simulator.getSensor(), agent.chooseStock(simulator.getSensor()).getSymbol());
//
//            //System.out.println(simulator.getSensor().getStocks());
//
//
//        }

        //System.out.println(simulator.getPortfolio().getBuyingPower());





    }

}

