package stockagent;

import yahoofinance.Stock;
import yahoofinance.YahooFinance;
import yahoofinance.histquotes.HistoricalQuote;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Main {


    public static void main(String[] args) throws IOException {


        Simulator simulator = new Simulator();
        String[] symbols = new String[] {"INTC", "BABA", "TSLA", "GOOG"};
        List<Stock>stockList = simulator.getStockInfo(symbols);

        Map<Stock, List<HistoricalQuote>>historicalData = new HashMap<Stock, List<HistoricalQuote>>();


        RuleBasedAgent agent = new RuleBasedAgent(simulator.getPortfolio(), simulator.getSensor());

        //System.out.println(agent.chooseStock(simulator.getSensor()).getSymbol());


        historicalData = simulator.getHistoricalData(stockList);

        for(int i =0; i < historicalData.size(); i++){

            agent.buyStock(simulator.getSensor(), agent.chooseStock(simulator.getSensor()).getSymbol());

            System.out.println(simulator.getSensor().getStocks());


        }

        //System.out.println(simulator.getPortfolio().getBuyingPower());





    }

}

