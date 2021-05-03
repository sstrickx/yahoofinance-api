package stockagent;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import yahoofinance.Stock;
import yahoofinance.histquotes.HistoricalQuote;
import yahoofinance.histquotes.Interval;

public class ModelBasedAgent implements StockAgent {



    @Override
    public Stock chooseStock( MarketSensor sensor) throws IOException {
        // TODO Auto-generated method stub

        HashMap<String, Integer> portfolio = Portfolio.getPortfolio();

        HashMap<String, Double>priceBoughtAt = Portfolio.getPriceBoughtAt();








        //System.out.println(portfolio);

        Map<Stock, List<HistoricalQuote>> data = new HashMap<Stock, List<HistoricalQuote>>();



        double value = 0;
        double greatestChange = 0;
        Stock bestStock = new Stock(null);

        Map <String, Stock> stocks = sensor.getStocks();



        //System.out.println(stocks);
        
        for (Map.Entry<String, Stock> entry : stocks.entrySet()) {

            data.put(entry.getValue(), sensor.getHistory(entry.getKey()));


        }






        for (Map.Entry<Stock, List<HistoricalQuote>> entry : data.entrySet()) {
            for (int i = 0; i < entry.getValue().size(); i++) {


                if(portfolio.containsKey(entry.getKey().getSymbol())){
                    double currValue = entry.getValue().get(i).getClose().doubleValue();
                    double valueBrought = priceBoughtAt.get(entry.getKey().getSymbol());
                    //System.out.println(valueBrought);
                    if(currValue > valueBrought){
                        bestStock = entry.getKey();

                    }


                }

                double currValue = entry.getValue().get(i).getClose().doubleValue();


                if(currValue < entry.getValue().get(i).getLow().doubleValue()){
                    bestStock = entry.getKey();
                }

                else{
                    bestStock = entry.getKey();





                }





//
//
//                double currValue = entry.getValue().get(i).getClose().doubleValue();
//                //System.out.println(currValue);
//
//                //System.out.println(currValue);
//                //value = entry.getKey().getQuote().getChange().doubleValue();
//
//                double max = entry.getValue().get(i).getHigh().doubleValue();
//
//
//                double min = entry.getValue().get(i).getLow().doubleValue();
//                if(currValue >= max){
//                    bestStock = entry.getKey();
//                    return bestStock;
//                }
//
//                if(currValue <= min){
//                    bestStock = entry.getKey();
//                    return bestStock;
//                }






                //System.out.println(entry.getKey().getQuote().getPreviousClose());
//                System.out.println(value);

//
//                if (value > greatestChange){
//                    bestStock = entry.getKey();
//                }

        }

        }





            //System.out.println(entry);



        return bestStock;
    }

    
}
