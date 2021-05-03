package stockagent;

import java.util.HashMap;


public class Portfolio implements TraderPortfolio {

        private static double buyingPower;

        //better version of portfolio?
        private static HashMap <String, Integer> portfolio = new HashMap<String, Integer>();


        private static HashMap<String, Double>priceBoughtAt = new HashMap<String, Double>();



        public Portfolio(double buyingPower){
            this.buyingPower = buyingPower;
        }



        public static HashMap<String, Integer> getPortfolio() {

            return portfolio;

        }



        public double getBuyingPower() {
            return buyingPower;
        }


        public void setBuyingPower(double num){
            this.buyingPower = num;
        }


        public static HashMap<String, Double>getPriceBoughtAt(){
            return priceBoughtAt;
        }
}