package stockagent;

import yahoofinance.Stock;

import java.io.IOException;
import java.util.HashMap;

public interface TraderPortfolio {

    public void buyStock(MarketSensor sensor, String symbol) throws IOException;

    public void sellStock(MarketSensor sensor, String symbol) throws IOException;


    public double getBuyingPower();


    public HashMap<Stock, Integer> getPorfolio();

}
