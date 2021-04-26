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


        Portfolio portfolio = new Portfolio(100000);
        PortfolioManager manager = new PortfolioManager(portfolio);
        Calendar from = Calendar.getInstance();
        Calendar to = Calendar.getInstance();

        String[] symbols = new String[]{"INTC", "BABA", "TSLA", "GOOG"};


        List<Stock> stockList = simulator.getStockInfo(symbols);


        Map<Stock, List<HistoricalQuote>> historicalData = new HashMap<Stock, List<HistoricalQuote>>();


        simulator.setFrom(from);


        Calendar start = Calendar.getInstance();
        start.setTime(from.getTime());
        Calendar end = Calendar.getInstance();
        end.setTime(to.getTime());


        historicalData = simulator.getHistoricalData(stockList);



        int i = 0;
        for(Stock stock : historicalData.keySet()){

            while(i < 365){


                stock = agent.chooseStock(simulator.getSensor());


                manager.buyStock(simulator.getSensor(), stock.getSymbol());

                stock = agent.chooseStock(simulator.getSensor());

                //manager.sellStock(simulator.getSensor(), stock.getSymbol());





                System.out.println(manager.getPortfolio(portfolio).getBuyingPower());
                System.out.println(portfolio.getPortfolio());
//                System.out.println(manager.getPortfolio(portfolio).getPriceBoughtAt());

                i+=1;
                }

            }





    }
}















