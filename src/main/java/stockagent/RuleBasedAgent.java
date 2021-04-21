package stockagent;


import yahoofinance.Stock;
import yahoofinance.YahooFinance;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.*;

public class RuleBasedAgent {

    //private double buyingPower;


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






}
