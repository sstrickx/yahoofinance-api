package stockagent;

import yahoofinance.Stock;
import yahoofinance.YahooFinance;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.HashMap;
//import java.util.Map;


public class Portfolio implements TraderPortfolio {

    private double buyingPower;


    //better version of portfolio?
    private HashMap <Stock, Integer> portfolio = new HashMap<Stock,Integer>();


    private HashMap<Stock, Double>priceBoughtAt = new HashMap<Stock, Double>();



    public Portfolio(double buyingPower){
        this.buyingPower = buyingPower;
    }


            return portfolio;

        }

        if(cost > pricing.doubleValue()) {
            //int shares = (int) (cost/pricing.doubleValue());

            //possibly change
            portfolio.put(stock, shares);
            priceBoughtAt.put(stock, (pricing.doubleValue()));
            buyingPower-=cost;

        }

        @Override
        public double getBuyingPower() {
            return buyingPower;
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



    @Override
    public HashMap<Stock, Integer> getPortfolio() {
        // TODO Auto-generated method stub
        return null;
    }
}
