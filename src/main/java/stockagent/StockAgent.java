package stockagent;

import yahoofinance.Stock;

import java.util.List;

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








    public void buyStock();



    public void sellStock();


    public List<Stock> getPorfolio();





    public int getBuyingPower();







}
