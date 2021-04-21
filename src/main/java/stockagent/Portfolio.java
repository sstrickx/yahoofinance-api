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


        @Override
        public HashMap<Stock, Integer> getPorfolio() {




            return portfolio;


        }



        @Override
        public double getBuyingPower() {
            return buyingPower;
        }


        public HashMap<Stock, Double>getPriceBoughtAt(){
            return priceBoughtAt;
        }
}
