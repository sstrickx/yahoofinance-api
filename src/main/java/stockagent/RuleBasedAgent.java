package stockagent;


import yahoofinance.Stock;
import yahoofinance.YahooFinance;
<<<<<<< HEAD
import yahoofinance.histquotes.HistoricalQuote;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RuleBasedAgent implements StockAgent, SensorInterface {

    private double buyingPower;
    private HashMap<Stock, Integer> porfolio = new HashMap<Stock,Integer>();
=======

import java.io.IOException;
import java.math.BigDecimal;
import java.util.*;

public class RuleBasedAgent {

    //private double buyingPower;
>>>>>>> 7a54e3d38e488500f984602c38b03569fa420907


    //better version of portfolio?
    //private HashMap <Stock, Integer> portfolio = new HashMap<Stock,Integer>();


    private Portfolio portfolio;
    private MarketSensor sensor;


    Random random = new Random();

    public RuleBasedAgent(Portfolio portfolio, MarketSensor sensor) throws IOException {
        this.portfolio = portfolio;
        this.sensor = sensor;

    }



    //need to figure out how to get specific pricing for a day instead of getting the entire list in getStockPrice

    public void buyStock(MarketSensor sensor, String symbol) throws IOException {
        Stock stock = YahooFinance.get(symbol);
        BigDecimal pricing = sensor.getStockPrice(symbol);

        double currMoney = (portfolio.getBuyingPower())*.10;

        double num = portfolio.getBuyingPower();

        if(currMoney > pricing.doubleValue()) {
            int shares = (int) (currMoney/pricing.doubleValue());

            //possibly change


            portfolio.getPorfolio().put(stock, shares);
            portfolio.getPriceBoughtAt().put(stock, (pricing.doubleValue()));
            portfolio.setBuyingPower(num-currMoney);




        }

        else{
            System.out.println("COULDN'T BUY STOCK DUE TO INSUFFICIENT FUNDS " + symbol);

        }

    }

//    public void sellStock(MarketSensor sensor, String symbol) throws IOException {
//        Stock stock = YahooFinance.get(symbol);
//        BigDecimal currPrice = sensor.getStockPrice(symbol);
//
//        double valueBoughtAt = portfolio.getPriceBoughtAt().get(stock);
//
//        if(currPrice.doubleValue() > valueBoughtAt){
//            buyingPower += currPrice.doubleValue()*portfolio.getPorfolio().get(stock);
//            portfolio.getPorfolio().remove(stock);
//            portfolio.getPriceBoughtAt().remove(stock);
//
//
//        }
//
//        else{
//            System.out.println("SELLING FOR LESS THAN BOUGHT AT ");
//
//        }
//
//
//    }
    public Stock chooseStock(MarketSensor sensor){
        List<String> key = new ArrayList<String>(sensor.getStocks().keySet());
        String randomKey = key.get(random.nextInt(key.size()));
        Stock value = sensor.getStocks().get(randomKey);


        return value;

    }



<<<<<<< HEAD





    @Override
    public Map<String, Stock> getStocks() {
        // TODO Auto-generated method stub
        return null;
    }







    @Override
    public BigDecimal getStockPrice(Stock stock) throws IOException {
        // TODO Auto-generated method stub
        return null;
    }







    @Override
    public List<HistoricalQuote> getHistory(Stock ticker) throws IOException {
        // TODO Auto-generated method stub
        return null;
    }


    //Return stocks owned and amount of each we own
//    public HashMap<Stock, Integer> getPorfolio() {
//        return porfolio;
//    }
//
//
//    //buying power left
//    @Override
//    public double getBuyingPower() {
//        return buyingPower;
//    }
=======
>>>>>>> 7a54e3d38e488500f984602c38b03569fa420907



}
