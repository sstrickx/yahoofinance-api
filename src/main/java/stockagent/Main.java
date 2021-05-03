package stockagent;


import yahoofinance.Stock;
import yahoofinance.histquotes.HistoricalQuote;

import java.io.IOException;
import java.util.*;

public class Main {


    public static void main(String[] args) throws IOException {


        StockAgent agent = new ModelBasedAgent();


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
            int size = historicalData.get(stock).size();
            
            while(i < 100){


                stock = agent.chooseStock(simulator.getSensor());



                manager.buyStock(simulator.getSensor(), stock.getSymbol(), i);

                stock = agent.chooseStock(simulator.getSensor());


                if(portfolio.getPortfolio().size() != 0) {
                    manager.sellStock(simulator.getSensor(), stock.getSymbol(), i);
                }
                System.out.println("\n");
                System.out.println("BuyingPower: ");
                System.out.println(portfolio.getBuyingPower() + "\n");
                System.out.println("Total Assest Value: ");
                System.out.println(manager.getAssets(portfolio) + "\n");
                System.out.println("Stocks/shares owned/sold: ");
                System.out.println(portfolio.getPortfolio() + "\n");
                System.out.println("Stock Price Bought At/sold at: ");
                System.out.println(portfolio.getPriceBoughtAt() + "\n");

                i+=1;
                }

            }





    }
}















