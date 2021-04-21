package stockagent;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Random;
import java.util.Map.Entry;

import yahoofinance.Stock;

public class RandomAgent {
    
    private Portfolio portfolio;
    private MarketSensor sensor;
    private Random randnum;

    public RandomAgent(Portfolio portfolio, MarketSensor sensor){
        this.portfolio = portfolio;
        this.sensor = sensor;
    }

    public void buyStock() throws IOException{

        while(portfolio.getBuyingPower() > 0){

            int i = 0;
            HashMap<Stock, Integer> purchasableStocks = portfolio.getPorfolio();

            for(Entry<Stock, Integer> entry: purchasableStocks.entrySet()) {
                Stock stock = entry.getKey();
                double price = stock.getHistory().get(i).getClose().doubleValue();

                int shares = randnum.nextInt(10);

                double cost = price * shares;

                if(cost <= portfolio.getBuyingPower()){
                    portfolio.buyStock(sensor, stock.getSymbol(), shares);
                }
                i++;
            }

        }
    }
}
