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
        private HashMap <String, Integer> portfolio = new HashMap<String, Integer>();


        private HashMap<String, Double>priceBoughtAt = new HashMap<String, Double>();



        public Portfolio(double buyingPower){
            this.buyingPower = buyingPower;
        }


        @Override
        public HashMap<String, Integer> getPortfolio() {

            return portfolio;

        }


        @Override
        public double getBuyingPower() {
            return buyingPower;
        }


        public void setBuyingPower(double num){
            this.buyingPower = num;
        }


        public HashMap<String, Double>getPriceBoughtAt(){
            return priceBoughtAt;
        }
}