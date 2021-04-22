package stockagent;

import yahoofinance.Stock;

public interface StockAgent {


    public Stock chooseStock(MarketSensor sensor);
}
