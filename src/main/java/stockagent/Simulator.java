package stockagent;

import yahoofinance.Stock;
import yahoofinance.YahooFinance;
import yahoofinance.histquotes.HistoricalQuote;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Simulator {



    private List<Stock> stockList = new ArrayList<Stock>();

    private Portfolio portfolio = new Portfolio(1000000);
    private MarketSensor sensor = new MarketSensor();




    public Simulator() throws IOException {
        this.portfolio = portfolio;
    }

    public List<Stock> getStockInfo(String [] symbols) throws IOException {
        for(int i =0; i < symbols.length; i++){

            stockList.add(YahooFinance.get(symbols[i]));
        }

        return stockList;

    }






    public Map<Stock,List<HistoricalQuote>> getHistoricalData(List<Stock>stockList) throws IOException {

        Map<Stock, List<HistoricalQuote>>data = new HashMap<Stock, List<HistoricalQuote>>();

        for(int i =0; i < stockList.size(); i++){

            data.put(stockList.get(i), sensor.getHistory(stockList.get(i).getSymbol()));

        }

        return data;

    }


    public Portfolio getPortfolio() {
        return portfolio;
    }


    public MarketSensor getSensor() {
        return sensor;
    }
}
