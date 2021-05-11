package stockagent;

import yahoofinance.Stock;
import yahoofinance.YahooFinance;
import yahoofinance.histquotes.HistoricalQuote;
import yahoofinance.histquotes.Interval;

import java.io.IOException;
import java.util.*;

public class Simulator {


    private List<Stock> stockList = new ArrayList<Stock>();
    private Portfolio portfolio = new Portfolio(1000000);
    private MarketSensor sensor = new MarketSensor();
    //private PortfolioManager portfolioManager = new PortfolioManager(portfolio, sensor);
    private Calendar from = Calendar.getInstance();
    private Calendar to = Calendar.getInstance();



    //takes stock agent
    //loop through each day and tell agent what agent to buy
    //portfolio manager buy and sell


    public Simulator(StockAgent Agent) throws IOException {
        setFrom(from);
    }

    public List<Stock> getStockInfo(String[] symbols) throws IOException {
        for (int i = 0; i < symbols.length; i++) {

            stockList.add(YahooFinance.get(symbols[i]));
        }

        return stockList;

    }


    public Map<Stock, List<HistoricalQuote>> getHistoricalData(List<Stock> stockList) throws IOException {

        Map<Stock, List<HistoricalQuote>> data = new HashMap<Stock, List<HistoricalQuote>>();

        for (int i = 0; i < stockList.size(); i++) {

            data.put(stockList.get(i), stockList.get(i).getHistory(from, to, Interval.DAILY));

        }

        return data;

    }








    public Portfolio getPortfolio() {
        return portfolio;
    }


    public MarketSensor getSensor() {
        return sensor;
    }


    public void setFrom(Calendar from) {
        from.add(Calendar.MONTH, -4);
    }


    public Calendar getFrom(){
        return from;
    }


    public Calendar getTo(){
        return to;
    }

}