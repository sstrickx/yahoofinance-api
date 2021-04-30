package stockagent;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import yahoofinance.Stock;
import yahoofinance.histquotes.HistoricalQuote;

public class ModelBasedAgent implements StockAgent {


    @Override
    public Stock chooseStock( MarketSensor sensor) {
        // TODO Auto-generated method stub
        Map <String, Stock> stocks = sensor.getStocks();
        
        return null;
    }

    
}
