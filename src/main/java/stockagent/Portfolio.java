package stockagent;

import yahoofinance.Stock;
import yahoofinance.YahooFinance;

import javax.sound.sampled.Port;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;


public class Portfolio implements TraderPortfolio {

        private double buyingPower;


        //better version of portfolio?
        private HashMap <Stock, Integer> portfolio = new HashMap<Stock,Integer>();


        private HashMap<Stock, Double>priceBoughtAt = new HashMap<Stock, Double>();



        public Portfolio(double buyingPower){
            this.buyingPower = buyingPower;
        }



        //need to figure out how to get specific pricing for a day instead of getting the entire list in getStockPrice
        //@Override
        public void buyStock(MarketSensor sensor, String symbol, int shares) throws IOException {
            Stock stock = YahooFinance.get(symbol);
            BigDecimal pricing = sensor.getStockPrice(symbol);


            double cost = pricing.doubleValue() * shares;


            if(cost > pricing.doubleValue()) {
                //int shares = (int) (cost/pricing.doubleValue());

                //possibly change
                portfolio.put(stock, shares);
                priceBoughtAt.put(stock, (pricing.doubleValue()));
                buyingPower-=cost;

            }

            else{
                System.out.println("COULDN'T BUY STOCK DUE TO INSUFFICIENT FUNDS " + symbol);

            }

        }
        //@Override
        public void sellStock(MarketSensor sensor, String symbol) throws IOException {
            Stock stock = YahooFinance.get(symbol);
            BigDecimal currPrice = sensor.getStockPrice(symbol);

            double valueBoughtAt = priceBoughtAt.get(stock);

            if(currPrice.doubleValue() > valueBoughtAt){
                buyingPower += currPrice.doubleValue()*portfolio.get(stock);
                portfolio.remove(stock);
                priceBoughtAt.remove(stock);


            }

            else{
                System.out.println("SELLING FOR LESS THAN BOUGHT AT " + symbol);

            }



        }
        @Override
        public HashMap<Stock, Integer> getPorfolio() {

            return portfolio;


        }

    public HashMap<Stock, Double> getPriceBoughtAt() {
        return priceBoughtAt;
    }

    public void setBuyingPower(double num){
            this.buyingPower = num;
        }



        @Override
        public double getBuyingPower() {
            return buyingPower;
        }
}
