package stockagent;


import yahoofinance.Stock;
import yahoofinance.YahooFinance;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RuleBasedAgent implements StockAgent {

    private double buyingPower;
    private HashMap <Stock, Integer> portfolio = new HashMap<Stock,Integer>();

    //I think this implementation might make more sense with a map inside of a map to hold the stock along with a map for the amount of
    //shares bought and price bought at so it could make easier for sell implementation??

    //private Map<Stock, Map<Integer, Double>>portfolio = new HashMap<Stock,Map<Integer, Double>>();


    public RuleBasedAgent(double buyingPower) throws IOException {
        this.buyingPower = buyingPower;
    }


    //need to figure out how to get specific pricing for a day instead of getting the entire list in getStockPrice
    @Override
    public void buyStock(LocalSensor sensor, String symbol) throws IOException {
        Stock stock = YahooFinance.get(symbol);
        BigDecimal pricing = sensor.getStockPrice(symbol);

        double currMoney = getBuyingPower()*.10;


        if(currMoney > pricing.doubleValue()) {
            int shares = (int) (currMoney/pricing.doubleValue());

            portfolio.put(stock, shares);

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
        return portfolio;
    }

    @Override
    public double getBuyingPower() {
        return buyingPower;
    }


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
