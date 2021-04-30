package stockagent;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import yahoofinance.Stock;
import yahoofinance.histquotes.HistoricalQuote;

public class ModelBasedAgent implements StockAgent {


    @Override
    public Stock chooseStock( MarketSensor sensor) throws IOException {
        // TODO Auto-generated method stub

        double value = 0;
        double greatestChange = 0;
        Stock bestStock = new Stock(null);

        Map <String, Stock> stocks = sensor.getStocks();
        //System.out.println(stocks);
        
        for (Map.Entry<String, Stock> entry : stocks.entrySet()){
            value = entry.getValue().getQuote().getChange().doubleValue();
            double stockprice = sensor.getStockPrice(entry.getKey()).doubleValue();
            System.out.println(stockprice);
            if (value > greatestChange){
                bestStock = entry.getValue();
            }
            //System.out.println(entry);
        }
        return bestStock;
    }

    
}
