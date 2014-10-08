
package yahoofinance.quotes.stock;

import java.util.Calendar;
import yahoofinance.Utils;

/**
 *
 * @author Stijn Strickx
 */
public class StockQuote {
    
    private final String symbol;
    
    private double ask;
    private int askSize;
    private double bid;
    private int bidSize;
    private double price;
    private int lastTradeSize;
    private Calendar lastTradeTime;
    private double open;
    private double previousClose;
    private double dayLow;
    private double dayHigh;
    
    private double yearLow;
    private double yearHigh;
    private double priceAvg50;
    private double priceAvg200;
    
    private long volume;
    private long avgVolume;
    
    public StockQuote(String symbol) {
        this.symbol = symbol;
    }
    
    /**
     * 
     * @return      difference between current price and previous close
     */
    public double getChange() {
        return this.price - this.previousClose;
    }
    
    /**
     * 
     * @return      change relative to previous close
     */
    public double getChangeInPercent() {
        return Utils.getPercent(this.getChange(), this.previousClose);
    }
    
    /**
     * 
     * @return      difference between current price and year low
     */
    public double getChangeFromYearLow() {
        return this.price - this.yearLow;
    }
    
    /**
     * 
     * @return      change from year low relative to year low
     */
    public double getChangeFromYearLowInPercent() {
        return Utils.getPercent(this.getChangeFromYearLow(), this.yearLow);
    }
    
    /**
     * 
     * @return      difference between current price and year high
     */
    public double getChangeFromYearHigh() {
        return this.price - this.yearHigh;
    }
    
    /**
     * 
     * @return      change from year high relative to year high
     */
    public double getChangeFromYearHighInPercent() {
        return Utils.getPercent(this.getChangeFromYearHigh(), this.yearHigh);
    }
    
    /**
     * 
     * @return      difference between current price and 50 day moving average
     */
    public double getChangeFromAvg50() {
        return this.price - this.priceAvg50;
    }
    
    /**
     * 
     * @return      change from 50 day moving average relative to 50 day moving average
     */
    public double getChangeFromAvg50InPercent() {
        return Utils.getPercent(this.getChangeFromAvg50(), this.priceAvg50);
    }
    
    /**
     * 
     * @return      difference between current price and 200 day moving average
     */
    public double getChangeFromAvg200() {
        return this.price - this.priceAvg200;
    }
    
    /**
     * 
     * @return      change from 200 day moving average relative to 200 day moving average
     */
    public double getChangeFromAvg200InPercent() {
        return Utils.getPercent(this.getChangeFromAvg200(), this.priceAvg200);
    }
    
    public String getSymbol() {
        return symbol;
    }
    
    public double getAsk() {
        return ask;
    }
    
    public void setAsk(double ask) {
        this.ask = ask;
    }
    
    public int getAskSize() {
        return askSize;
    }
    
    public void setAskSize(int askSize) {
        this.askSize = askSize;
    }
    
    public double getBid() {
        return bid;
    }
    
    public void setBid(double bid) {
        this.bid = bid;
    }
    
    public int getBidSize() {
        return bidSize;
    }
    
    public void setBidSize(int bidSize) {
        this.bidSize = bidSize;
    }
    
    public double getPrice() {
        return price;
    }
    
    public void setPrice(double price) {
        this.price = price;
    }
    
    public int getLastTradeSize() {
        return lastTradeSize;
    }
    
    public void setLastTradeSize(int lastTradeSize) {
        this.lastTradeSize = lastTradeSize;
    }
    
    public Calendar getLastTradeTime() {
        return lastTradeTime;
    }
    
    public void setLastTradeTime(Calendar lastTradeTime) {
        this.lastTradeTime = lastTradeTime;
    }
    
    public double getOpen() {
        return open;
    }
    
    public void setOpen(double open) {
        this.open = open;
    }
    
    public double getPreviousClose() {
        return previousClose;
    }
    
    public void setPreviousClose(double previousClose) {
        this.previousClose = previousClose;
    }
    
    public double getDayLow() {
        return dayLow;
    }
    
    public void setDayLow(double dayLow) {
        this.dayLow = dayLow;
    }
    
    public double getDayHigh() {
        return dayHigh;
    }
    
    public void setDayHigh(double dayHigh) {
        this.dayHigh = dayHigh;
    }
    
    public double getYearLow() {
        return yearLow;
    }
    
    public void setYearLow(double yearLow) {
        this.yearLow = yearLow;
    }
    
    public double getYearHigh() {
        return yearHigh;
    }
    
    public void setYearHigh(double yearHigh) {
        this.yearHigh = yearHigh;
    }
    
    /**
     * 
     * @return      50 day moving average
     */
    public double getPriceAvg50() {
        return priceAvg50;
    }
    
    public void setPriceAvg50(double priceAvg50) {
        this.priceAvg50 = priceAvg50;
    }
    
    /**
     * 
     * @return      200 day moving average
     */
    public double getPriceAvg200() {
        return priceAvg200;
    }
    
    public void setPriceAvg200(double priceAvg200) {
        this.priceAvg200 = priceAvg200;
    }
    
    public long getVolume() {
        return volume;
    }
    
    public void setVolume(long volume) {
        this.volume = volume;
    }
    
    public long getAvgVolume() {
        return avgVolume;
    }
    
    public void setAvgVolume(long avgVolume) {
        this.avgVolume = avgVolume;
    }
    
    @Override
    public String toString() {
        return "Ask: " + this.ask + ", Bid: " + this.bid + ", Price: " + this.price + ", Prev close: " + this.previousClose;
    }
    
}

