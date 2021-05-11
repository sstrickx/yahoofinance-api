package stockagent;

import java.io.IOException;

import yahoofinance.Stock;

public interface StockAgent {


    public Stock chooseStock(MarketSensor sensor) throws IOException;
}
