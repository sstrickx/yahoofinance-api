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


        //System.out.println(stockList);

        Map<Stock, List<HistoricalQuote>>historicalData= new HashMap<Stock, List<HistoricalQuote>>();





        //System.out.println(agent.chooseStock(simulator.getSensor()).getSymbol());






        int j =0;


        simulator.setFrom(from);


        Calendar start = Calendar.getInstance();
        start.setTime(from.getTime());
        Calendar end = Calendar.getInstance();
        end.setTime(to.getTime());




//        for (Date date = start.getTime(); start.before(end); start.add(Calendar.DATE, 1), date = start.getTime()) {


        while(j < 365){
            historicalData = simulator.getHistoricalData(stockList);


            System.out.println(historicalData);


            //GETS ALL DATA BUT WON'T BREAK OUT OF THE LOOP
            j+=1;

        }


        //System.out.println(historicalData);
        System.out.println("DONE");







//            for(int i =0; i < stockList.size(); i++){
//                historicalData.put(stockList.get(i), sensor.getHistory(stockList.get(i).getSymbol()));
//            }


            //System.out.println(historicalData.get(agent.chooseStock(stockList)));
            //System.out.println(historicalData);


        }

        //System.out.println(historicalData);





//        while(j < 365) {
//
//
//
//
//
//
//
//
//            historicalData = simulator.getHistoricalData(stockList);
//            System.out.println(historicalData);
//
//
//
//
//
//
//            j+=1;

//        }






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



