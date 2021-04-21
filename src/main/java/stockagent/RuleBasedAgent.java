package stockagent;


import yahoofinance.Stock;
import yahoofinance.YahooFinance;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class RuleBasedAgent implements StockAgent, SensorInterface {

    private double buyingPower;
    private List<Stock> porfolio = new ArrayList<Stock>();



    public RuleBasedAgent(double buyingPower) throws IOException {
        this.buyingPower = buyingPower;
    }




    @Override
    public void buyStock(String symbol, int shares) {
        
    }

    @Override
    public void sellStock() {

    }

    @Override
    public List<Stock> getPorfolio() {
        return porfolio;
    }

    @Override
    public double getBuyingPower() {
        return buyingPower;
    }




    @Override
    public List<Stock> getStocks() {
        // TODO Auto-generated method stub
        return null;
    }




    @Override
    public double getStockPrice(Stock stock) {
        // TODO Auto-generated method stub
        return 0;
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
