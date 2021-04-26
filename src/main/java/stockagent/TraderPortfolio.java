package stockagent;

import yahoofinance.Stock;

import java.io.IOException;
import java.util.HashMap;

public interface TraderPortfolio {


    public double getBuyingPower();


    public HashMap<String, Integer> getPortfolio();

}
