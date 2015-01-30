
package yahoofinance.quotes.stock;

import java.math.BigDecimal;
import java.util.Calendar;

import yahoofinance.Utils;

/**
 *
 * @author Stijn Strickx
 */
public class StockQuote {
    
    private final String symbol;
    
    private BigDecimal ask;
    private int askSize;
    private BigDecimal bid;
    private int bidSize;
    private BigDecimal price;
    private int lastTradeSize;
    private Calendar lastTradeTime;
    private BigDecimal open;
    private BigDecimal previousClose;
    private BigDecimal dayLow;
    private BigDecimal dayHigh;
    
    private BigDecimal yearLow;
    private BigDecimal yearHigh;
    private BigDecimal priceAvg50;
    private BigDecimal priceAvg200;
    
    private long volume;
    private long avgVolume;
    
    public StockQuote(String symbol) {
        this.symbol = symbol;
    }
    
    /**
     * 
     * @return      difference between current price and previous close
     */
    public BigDecimal getChange() {
        return this.price.subtract(this.previousClose);
    }
    
    /**
     * 
     * @return      change relative to previous close
     */
    public BigDecimal getChangeInPercent() {
        return Utils.getPercent(this.getChange(), this.previousClose);
    }
    
    /**
     * 
     * @return      difference between current price and year low
     */
    public BigDecimal getChangeFromYearLow() {
        return this.price.subtract(this.yearLow);
    }
    
    /**
     * 
     * @return      change from year low relative to year low
     */
    public BigDecimal getChangeFromYearLowInPercent() {
        return Utils.getPercent(this.getChangeFromYearLow(), this.yearLow);
    }
    
    /**
     * 
     * @return      difference between current price and year high
     */
    public BigDecimal getChangeFromYearHigh() {
        return this.price.subtract(this.yearHigh);
    }
    
    /**
     * 
     * @return      change from year high relative to year high
     */
    public BigDecimal getChangeFromYearHighInPercent() {
        return Utils.getPercent(this.getChangeFromYearHigh(), this.yearHigh);
    }
    
    /**
     * 
     * @return      difference between current price and 50 day moving average
     */
    public BigDecimal getChangeFromAvg50() {
        return this.price.subtract(this.priceAvg50);
    }
    
    /**
     * 
     * @return      change from 50 day moving average relative to 50 day moving average
     */
    public BigDecimal getChangeFromAvg50InPercent() {
        return Utils.getPercent(this.getChangeFromAvg50(), this.priceAvg50);
    }
    
    /**
     * 
     * @return      difference between current price and 200 day moving average
     */
    public BigDecimal getChangeFromAvg200() {
        return this.price.subtract(this.priceAvg200);
    }
    
    /**
     * 
     * @return      change from 200 day moving average relative to 200 day moving average
     */
    public BigDecimal getChangeFromAvg200InPercent() {
        return Utils.getPercent(this.getChangeFromAvg200(), this.priceAvg200);
    }
    
    public String getSymbol() {
        return symbol;
    }
    
    public BigDecimal getAsk() {
        return ask;
    }
    
    public void setAsk(BigDecimal ask) {
        this.ask = ask;
    }
    
    public int getAskSize() {
        return askSize;
    }
    
    public void setAskSize(int askSize) {
        this.askSize = askSize;
    }
    
    public BigDecimal getBid() {
        return bid;
    }
    
    public void setBid(BigDecimal bid) {
        this.bid = bid;
    }
    
    public int getBidSize() {
        return bidSize;
    }
    
    public void setBidSize(int bidSize) {
        this.bidSize = bidSize;
    }
    
    public BigDecimal getPrice() {
        return price;
    }
    
    public void setPrice(BigDecimal price) {
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
    
    public BigDecimal getOpen() {
        return open;
    }
    
    public void setOpen(BigDecimal open) {
        this.open = open;
    }
    
    public BigDecimal getPreviousClose() {
        return previousClose;
    }
    
    public void setPreviousClose(BigDecimal previousClose) {
        this.previousClose = previousClose;
    }
    
    public BigDecimal getDayLow() {
        return dayLow;
    }
    
    public void setDayLow(BigDecimal dayLow) {
        this.dayLow = dayLow;
    }
    
    public BigDecimal getDayHigh() {
        return dayHigh;
    }
    
    public void setDayHigh(BigDecimal dayHigh) {
        this.dayHigh = dayHigh;
    }
    
    public BigDecimal getYearLow() {
        return yearLow;
    }
    
    public void setYearLow(BigDecimal yearLow) {
        this.yearLow = yearLow;
    }
    
    public BigDecimal getYearHigh() {
        return yearHigh;
    }
    
    public void setYearHigh(BigDecimal yearHigh) {
        this.yearHigh = yearHigh;
    }
    
    /**
     * 
     * @return      50 day moving average
     */
    public BigDecimal getPriceAvg50() {
        return priceAvg50;
    }
    
    public void setPriceAvg50(BigDecimal priceAvg50) {
        this.priceAvg50 = priceAvg50;
    }
    
    /**
     * 
     * @return      200 day moving average
     */
    public BigDecimal getPriceAvg200() {
        return priceAvg200;
    }
    
    public void setPriceAvg200(BigDecimal priceAvg200) {
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

