package stockagent;

import yahoofinance.Stock;
import yahoofinance.YahooFinance;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.HashMap;

public class PortfolioManager {

    private Portfolio portfolio;

    public PortfolioManager(Portfolio portfolio){
        this.portfolio = portfolio;

    }





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

    public void sellStock(MarketSensor sensor, String symbol) throws IOException {
        Stock stock = YahooFinance.get(symbol);
        BigDecimal currPrice = sensor.getStockPrice(symbol);



        double valueBoughtAt = portfolio.getPriceBoughtAt().get(stock);

        if(currPrice.doubleValue() > valueBoughtAt){
            portfolio.setBuyingPower(currPrice.doubleValue()*portfolio.getPorfolio().get(stock));
            portfolio.getPorfolio().remove(stock);
            portfolio.getPriceBoughtAt().remove(stock);


        }

        else{
            System.out.println("SELLING FOR LESS THAN BOUGHT AT ");

        }


    }


    public Portfolio getPorfolio(Portfolio portfolio) {

        return portfolio;

    }
}
