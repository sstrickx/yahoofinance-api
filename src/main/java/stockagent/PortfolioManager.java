package stockagent;


import yahoofinance.Stock;
import yahoofinance.YahooFinance;
import yahoofinance.histquotes.HistoricalQuote;
import yahoofinance.histquotes.Interval;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PortfolioManager {

    private Portfolio portfolio;

    private MarketSensor sensor;

    public PortfolioManager(Portfolio portfolio, MarketSensor sensor){
        this.portfolio = portfolio;
        this.sensor = sensor;

    }





    public void buyStock(MarketSensor sensor, String symbol, int i) throws IOException {
        //Stock stock = YahooFinance.get(symbol);
        List<HistoricalQuote>history = sensor.getHistory(symbol);

        //System.out.println(history.get(i));



        BigDecimal pricing = history.get(i).getClose();


        //System.out.println(pricing);

        double currMoney = (portfolio.getBuyingPower()) * .10;

        double num = portfolio.getBuyingPower();

        if (currMoney > pricing.doubleValue()) {
            int shares = (int) (currMoney / pricing.doubleValue());



            if (portfolio.getPortfolio().containsKey(symbol)) {

                double sharesValue = portfolio.getPortfolio().get((symbol));
                double currValue = portfolio.getPriceBoughtAt().get(symbol);
                double currTotal = sharesValue*currValue;
                sharesValue += shares;
                portfolio.getPortfolio().put((symbol), (int) sharesValue);
                double amount = pricing.doubleValue()*shares;

                double newTotal = ((currTotal+amount)/sharesValue);
                portfolio.getPriceBoughtAt().put((symbol), newTotal);

                portfolio.setBuyingPower(num - amount);

            } else {

                portfolio.getPortfolio().put((symbol), shares);
                portfolio.getPriceBoughtAt().put((symbol), (pricing.doubleValue()));
                portfolio.setBuyingPower(num - (pricing.doubleValue()*shares));


            }

        }
    }

    public void sellStock(MarketSensor sensor, String symbol, int i) throws IOException {
//        Stock stock = YahooFinance.get(symbol);
//        BigDecimal currPrice = sensor.getStockPrice(symbol);

        List<HistoricalQuote>history = sensor.getHistory(symbol);

        BigDecimal currPrice = history.get(i).getClose();





        if(portfolio.getPortfolio().containsKey(symbol)){
            double valueBoughtAt = portfolio.getPriceBoughtAt().get(symbol);
            int shares = portfolio.getPortfolio().get(symbol);
            if(currPrice.doubleValue() > valueBoughtAt){
                portfolio.setBuyingPower((portfolio.getBuyingPower())+((currPrice.doubleValue()-valueBoughtAt)+valueBoughtAt)*shares);
                portfolio.getPortfolio().remove(symbol);
                portfolio.getPriceBoughtAt().remove(symbol);

            }

        }




    }

    public Portfolio getPortfolio(Portfolio portfolio) {

        return portfolio;

    }


    public double getAssets(Portfolio portfolio, MarketSensor sensor, int i) throws IOException {


        Map<String, List<HistoricalQuote>> data = new HashMap<String, List<HistoricalQuote>>();



        for(String symbol : sensor.getSymbols()){
            data.put(symbol, sensor.getHistory(symbol));
        }


        double currBuyingPower = portfolio.getBuyingPower();

        for(String stock : portfolio.getPortfolio().keySet()){


            currBuyingPower += (portfolio.getPortfolio().get(stock)* data.get(stock).get(i).getClose().doubleValue());
            //currBuyingPower += (portfolio.getPortfolio().get(stock)* pricing.doubleValue());

        }


        return currBuyingPower;
        }



}
