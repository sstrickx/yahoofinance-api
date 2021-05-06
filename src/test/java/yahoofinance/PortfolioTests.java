package yahoofinance;
import static org.junit.Assert.assertEquals;

import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map.Entry;

import org.junit.Test;
import org.junit.Assert.*;

import stockagent.Portfolio;
import stockagent.PortfolioManager;
import stockagent.RandomAgent;
import stockagent.Simulator;
import yahoofinance.histquotes.HistoricalQuote;



public class PortfolioTests {
    
    @Test
    public void buyTests() throws IOException{

        RandomAgent agent = new RandomAgent();
        Simulator simulator = new Simulator(agent);
        String[] symbols = new String[] {"INTC", "BABA", "TSLA", "GOOG"};
        List<Stock>stockList = simulator.getStockInfo(symbols);

        Portfolio testPortfolio = new Portfolio(100000);

        PortfolioManager testManager = new PortfolioManager(testPortfolio);

        //empty portfolio
        assertEquals(true, testPortfolio.getPortfolio().isEmpty());

        //buying power
        assertEquals(100000, testPortfolio.getBuyingPower(), 0.001);

        //stock to buy
        Stock baba = stockList.get(1);
        assertEquals("BABA", baba.getSymbol());

        //getting price
        double currPrice = simulator.getSensor().getHistory(baba.getSymbol()).get(0).getClose().doubleValue();

        //buy stock
        testManager.buyStock(simulator.getSensor(), baba.getSymbol(), 0);

        //checking portfolio
        assertEquals(1, testPortfolio.getPortfolio().size());
        assertEquals("BABA", testPortfolio.getPortfolio().keySet().iterator().next());
        assertEquals(3, testPortfolio.getPortfolio().entrySet().iterator().next().getValue().intValue());

        //buying power
        assertEquals(100000-currPrice*3, testPortfolio.getBuyingPower(), 0.001);
        

        //adding a different stock
        Stock tesla = stockList.get(2);
        currPrice = tesla.getQuote().getPrice().doubleValue();

        //adding asset
        double oldBP = testPortfolio.getBuyingPower();
        testManager.addAssets(tesla.getSymbol(), 1, currPrice);

        //checking portfolio
        Iterator<Entry<String,Integer>> iterator = testPortfolio.getPortfolio().entrySet().iterator();
        iterator.next();
        assertEquals(2, testPortfolio.getPortfolio().size());
        assertEquals(1, iterator.next().getValue().intValue());

        //buying power
        assertEquals(oldBP-currPrice, testPortfolio.getBuyingPower(), 0.001);

    }


    



}