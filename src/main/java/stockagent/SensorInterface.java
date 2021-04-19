package stockagent;

import yahoofinance.Stock;
import yahoofinance.YahooFinance;

import java.util.List;
import java.util.Map;

public interface SensorInterface {

    String[] symbols = new String[] {"INTC", "BABA", "TSLA", "AIR.PA", "YHOO"};
    Map<String, Stock> stocks = YahooFinance.get(symbols, true);

}