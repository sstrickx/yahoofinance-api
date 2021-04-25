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


    private MarketSensor sensor;


    Random random = new Random();

    public RuleBasedAgent() throws IOException {

        //DO WE NEED THIS??



    }


    public Stock chooseStock(MarketSensor sensor){
        List<String> key = new ArrayList<String>(sensor.getStocks().keySet());
        String randomKey = key.get(random.nextInt(key.size()));


        return sensor.getStocks().get(randomKey);

    }






}
