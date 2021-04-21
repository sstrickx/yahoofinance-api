package stockagent;


import yahoofinance.Stock;
import yahoofinance.YahooFinance;
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



    public RuleBasedAgent(double buyingPower) throws IOException {
        this.buyingPower = buyingPower;
    }







    //need to figure out how to get specific pricing for a day instead of getting the entire list in getStockPrice
    @Override
    public void buyStock(LocalSensor sensor, Stock symbol) throws IOException {
        BigDecimal pricing = sensor.getStockPrice(symbol);

        double currMoney = getBuyingPower()*.10;


        if(currMoney > pricing.doubleValue()) {
            int shares = (int) (currMoney/pricing.doubleValue());

            porfolio.put(symbol, shares);

            buyingPower-=currMoney;
        }

        else{
            System.out.println("COULDN'T BUY STOCK DUE TO INSUFFICIENT FUNDS" + symbol);

        }

    }

    @Override
    public void sellStock() {

    }

    @Override
    public HashMap<Stock, Integer> getPorfolio() {
        return porfolio;
    }

    @Override
    public double getBuyingPower() {
        return buyingPower;
    }







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

    //watch list of stocks 
    //List of stocks it owns
        //value of the stocks 
        //amount of stocks per company it owns 
    
    //getPortfolio of stocks 
   
    //start buying power "balance" (money it starts with) - cannot buy if balance is less than zero 
    
    //function that buys stock - cannot buy stock if it can't afford it 
        //amount of shares bought is a percentage of our buying power "balance" 
    
    //function that sells stock
        //if stock goes up to a certain percentage from bought date 
        //if stock plumets pass buying price (certain percentage) 

}
