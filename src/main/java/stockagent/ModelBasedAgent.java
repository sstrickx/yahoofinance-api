package stockagent;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.*;

import yahoofinance.Stock;
import yahoofinance.histquotes.HistoricalQuote;
import yahoofinance.histquotes.Interval;

public class ModelBasedAgent implements StockAgent {

    Random random = new Random();


    @Override
    public Stock chooseStock( MarketSensor sensor) throws IOException {
        // TODO Auto-generated method stub

        HashMap<String, Integer> portfolio = Portfolio.getPortfolio();

        HashMap<String, Double>priceBoughtAt = Portfolio.getPriceBoughtAt();



        Map<Stock, List<HistoricalQuote>> data = new HashMap<Stock, List<HistoricalQuote>>();


        Stock bestStock = new Stock(null);

        Map <String, Stock> stocks = sensor.getStocks();

        for (Map.Entry<String, Stock> entry : stocks.entrySet()) {

            data.put(entry.getValue(), sensor.getHistory(entry.getKey()));


        }


        for (Map.Entry<Stock, List<HistoricalQuote>> entry : data.entrySet()) {

            for (int i = 0; i < entry.getValue().size(); i++) {




                if (portfolio.containsKey(entry.getKey().getSymbol())) {

                    double currValue = entry.getValue().get(i).getClose().doubleValue();

                    double valueBrought = priceBoughtAt.get(entry.getKey().getSymbol());





                    //If value is more than we bought it at, sell stock
                    if (currValue > valueBrought) {
                        bestStock = entry.getKey();
                        return bestStock;
                    }



                }




                double currValue = entry.getValue().get(i).getClose().doubleValue();

                double min = entry.getKey().getQuote().getYearLow().doubleValue();

                double smallestDiff = Integer.MAX_VALUE;
                if(currValue-min < smallestDiff) {
                    smallestDiff = (currValue-min);
                    bestStock = entry.getKey();



                }
                //System.out.println(max);

//
//
//
//                List<String> key = new ArrayList<String>(sensor.getStocks().keySet());
//
//                String randomKey = key.get(random.nextInt(key.size()));
//
//                bestStock = sensor.getStocks().get(randomKey);
//
//
//









        }

        }





            //System.out.println(entry);



        return bestStock;
    }

    
}
