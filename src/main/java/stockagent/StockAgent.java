package stockagent;

import yahoofinance.Stock;
import yahoofinance.YahooFinance;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public interface StockAgent {

    //watch list of stocks
    //List of stocks it owns
    //value of the stocks
    //amount of stocks per company it owns

    //getPortfolio of stocks //returns list of stocks we own

    //start buying power "balance" (money it starts with) - cannot buy if balance is less than zero

    //function that buys stock - cannot buy stock if it can't afford it  // void
    //amount of shares bought is a percentage of our buying power "balance"

    //function that sells stock //void

    //if stock goes up to a certain percentage from bought date
    //if stock plumets pass buying price (certain percentage)


    //agent should only get a list of stock that it can buy/sell from sensor 
        //pass back out what stocks it bought and the change in price as actuator
    //have a default action (buy a standard amount of shares) (some percentage of your buying power)


    public void buyStock(String symbol, int shares);


    public void sellStock();


    public List<Stock> getPorfolio();


    public double getBuyingPower();







}
