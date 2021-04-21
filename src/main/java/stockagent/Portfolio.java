package stockagent;

import yahoofinance.Stock;
import yahoofinance.YahooFinance;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;


public class Portfolio implements TraderPortfolio {

        private double buyingPower;


        //better version of portfolio?
        private HashMap <Stock, Integer> portfolio = new HashMap<Stock,Integer>();



        //I think this implementation might make more sense with a map inside of a map to hold the stock along with a map for the amount of
        //shares bought and price bought at so it could make easier for sell implementation??

        //private Map<Stock, Map<Integer, Double>>portfolio = new HashMap<Stock,Map<Integer, Double>>();





        //need to figure out how to get specific pricing for a day instead of getting the entire list in getStockPrice
        @Override
        public void buyStock(MarketSensor sensor, String symbol) throws IOException {
            Stock stock = YahooFinance.get(symbol);
            BigDecimal pricing = sensor.getStockPrice(symbol);

            double currMoney = buyingPower*.10;


            if(currMoney > pricing.doubleValue()) {
                int shares = (int) (currMoney/pricing.doubleValue());

                //possibly change
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


        //change too if implementation changed
        @Override
        public HashMap<Stock, Integer> getPorfolio() {
            return portfolio;
        }

        @Override
        public double getBuyingPower() {
            return buyingPower;
        }
}
