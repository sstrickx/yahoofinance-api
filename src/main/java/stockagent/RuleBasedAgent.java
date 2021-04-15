package stockagent;


import yahoofinance.Stock;
import yahoofinance.YahooFinance;

import java.io.IOException;
import java.util.List;
import java.util.Map;

public class RuleBasedAgent implements StockAgent {

    private double buyingPower;

    String[] symbols = new String[] {"INTC", "BABA", "TSLA", "AIR.PA", "YHOO"};
    Map<String, Stock> stocks = YahooFinance.get(symbols, true);




    public RuleBasedAgent() throws IOException {
    }




    @Override
    public void buyStock() {

    }

    @Override
    public void sellStock() {

    }

    @Override

    //Return stocks owned and amount of each we own
    public List<Stock> getPorfolio() {
        return null;
    }


    //buying power left
    @Override
    public int getBuyingPower() {
        return 0;
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
