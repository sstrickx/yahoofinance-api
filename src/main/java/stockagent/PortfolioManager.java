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


            portfolio.getPortfolio().put(stock.getSymbol(), shares);
            portfolio.getPriceBoughtAt().put(stock.getSymbol(), (pricing.doubleValue()));
            portfolio.setBuyingPower(num-currMoney);




        }



    }

    public void sellStock(MarketSensor sensor, String symbol) throws IOException {
        Stock stock = YahooFinance.get(symbol);
        BigDecimal currPrice = sensor.getStockPrice(symbol);



        if(portfolio.getPriceBoughtAt().containsKey(stock.getSymbol())){
            double valueBoughtAt = portfolio.getPriceBoughtAt().get(stock.getSymbol());
            if(currPrice.doubleValue() > valueBoughtAt){
                portfolio.setBuyingPower(currPrice.doubleValue()*portfolio.getPortfolio().get(stock.getSymbol()));
                portfolio.getPortfolio().remove(stock.getSymbol());
                portfolio.getPriceBoughtAt().remove(stock.getSymbol());


            }

        }




    }


    public Portfolio getPortfolio(Portfolio portfolio) {

        return portfolio;

    }
}
