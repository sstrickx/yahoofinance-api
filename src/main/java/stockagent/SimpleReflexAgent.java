package stockagent;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.*;

import yahoofinance.Stock;
import yahoofinance.histquotes.HistoricalQuote;
import yahoofinance.histquotes.Interval;

public class SimpleReflexAgent implements StockAgent {

    Random random = new Random();
    private Portfolio p = new Portfolio(100000);

    private Map<Stock, List<HistoricalQuote>> data = new HashMap<Stock, List<HistoricalQuote>>();




    public void setData(MarketSensor sensor) throws IOException {

            Map<String, Stock> stocks = sensor.getStocks();

            for (Map.Entry<String, Stock> entry : stocks.entrySet()) {

                data.put(entry.getValue(), sensor.getHistory(entry.getKey()));
            }


    }


    @Override
    public Stock chooseStock( MarketSensor sensor) throws IOException {
        // TODO Auto-generated method stub


        Stock bestStock = new Stock(null);

        setData(sensor);

        HashMap<String, Integer> portfolio = p.getPortfolio();

        HashMap<String, Double> priceBoughtAt = p.getPriceBoughtAt();






        double smallestDiff = Integer.MAX_VALUE;


        for (Map.Entry<Stock, List<HistoricalQuote>> entry : data.entrySet()) {


            for (int i = 0; i < entry.getValue().size(); i++) {


                if (portfolio.containsKey(entry.getKey().getSymbol())) {

                    double currValue = entry.getValue().get(i).getClose().doubleValue();


                    double valueBrought = priceBoughtAt.get(entry.getKey().getSymbol());

                    //If value is more than we bought it at, sell stock
                    if (currValue > valueBrought) {
                        bestStock = entry.getKey();

                    }

                }

                else {

                    List<String> key = new ArrayList<String>(sensor.getStocks().keySet());

                    String randomKey = key.get(random.nextInt(key.size()));

                    bestStock = sensor.getStocks().get(randomKey);

                    //Possibly find smallest difference for stocks for the last
                    //7 days lowest price and default to buy that stock?





                }




            }

        }

            return bestStock;
        }

    
}
