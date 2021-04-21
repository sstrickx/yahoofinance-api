package stockagent;

import yahoofinance.Stock;
import yahoofinance.YahooFinance;
import yahoofinance.histquotes.HistoricalQuote;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

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






    public List<List<HistoricalQuote>> getHistoricalData(List<Stock>stockList) throws IOException {

        List<List<HistoricalQuote>>data = new ArrayList<List<HistoricalQuote>>();

        for(int i =0; i < stockList.size(); i++){

            data.add(sensor.getHistory(stockList.get(i).getSymbol()));

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
