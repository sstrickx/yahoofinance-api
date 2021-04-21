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
        @Override
        public void buyStock(MarketSensor sensor, String symbol) throws IOException {
            Stock stock = YahooFinance.get(symbol);
            BigDecimal pricing = sensor.getStockPrice(symbol);

            double currMoney = buyingPower*.10;


            if(currMoney > pricing.doubleValue()) {
                int shares = (int) (currMoney/pricing.doubleValue());

                //possibly change
                portfolio.put(stock, shares);
                priceBoughtAt.put(stock, (pricing.doubleValue()));
                buyingPower-=currMoney;

            }

            else{
                System.out.println("COULDN'T BUY STOCK DUE TO INSUFFICIENT FUNDS " + symbol);

            }

        }
        @Override
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

            for(int i=0; i < portfolio.size()-1; i++){
                System.out.println(portfolio.get(i));
            }


            return null;


        }



        @Override
        public double getBuyingPower() {
            return buyingPower;
        }
}
