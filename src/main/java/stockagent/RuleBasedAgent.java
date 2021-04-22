package stockagent;


import yahoofinance.Stock;
import yahoofinance.YahooFinance;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.*;

public class RuleBasedAgent implements StockAgent{

    //private double buyingPower;


    //better version of portfolio?
    //private HashMap <Stock, Integer> portfolio = new HashMap<Stock,Integer>();


    private Portfolio portfolio;
    private MarketSensor sensor;


    Random random = new Random();

    public RuleBasedAgent(Portfolio portfolio, MarketSensor sensor) throws IOException {

        //DO WE NEED THIS??
        this.portfolio = portfolio;
        this.sensor = sensor;

    }


    public Stock chooseStock(MarketSensor sensor){
        List<String> key = new ArrayList<String>(sensor.getStocks().keySet());
        String randomKey = key.get(random.nextInt(key.size()));
        Stock value = sensor.getStocks().get(randomKey);


        return value;

    }






}
