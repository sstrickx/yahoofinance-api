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

        HashMap<String, Double> priceBoughtAt = Portfolio.getPriceBoughtAt();


        Map<Stock, List<HistoricalQuote>> data = new HashMap<Stock, List<HistoricalQuote>>();


        Stock bestStock = new Stock(null);

        Map<String, Stock> stocks = sensor.getStocks();

        for (Map.Entry<String, Stock> entry : stocks.entrySet()) {

            data.put(entry.getValue(), sensor.getHistory(entry.getKey()));


        }

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

                double currValue = entry.getValue().get(i).getClose().doubleValue();
                System.out.println(currValue);

                double min = entry.getKey().getQuote().getYearLow().doubleValue();

                if (currValue - min < smallestDiff) {
                    smallestDiff = (currValue - min);
                    bestStock = entry.getKey();

                }


            }

        }

            return bestStock;
        }

    
}
