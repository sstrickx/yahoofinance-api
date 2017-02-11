
package yahoofinance.quotes.stock;

import java.math.BigDecimal;
import java.util.Calendar;
import java.util.TimeZone;
import yahoofinance.Utils;

/**
 * All getters can return null in case the data is not available from Yahoo Finance.
 * 
 * @author Stijn Strickx
 */
public class StockQuote {
    
    private final String symbol;
    
    private TimeZone timeZone;
    
    private BigDecimal ask;
    private Long askSize;
    private BigDecimal bid;
    private Long bidSize;
    private BigDecimal price;
    
    private Long lastTradeSize;
    private String lastTradeDateStr;
    private String lastTradeTimeStr;
    private Calendar lastTradeTime;
    
    private BigDecimal open;
    private BigDecimal previousClose;
    private BigDecimal dayLow;
    private BigDecimal dayHigh;
    
    private BigDecimal yearLow;
    private BigDecimal yearHigh;
    private BigDecimal priceAvg50;
    private BigDecimal priceAvg200;
    
    private Long volume;
    private Long avgVolume;
    
    public StockQuote(String symbol) {
        this.symbol = symbol;
    }
    
    /**
     * 
     * @return      difference between current price and previous close
     */
    public BigDecimal getChange() {
        if(this.price == null || this.previousClose == null) {
            return null;
        }
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
        if(this.price == null || this.yearLow == null) {
            return null;
        }
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
        if(this.price == null || this.yearHigh == null) {
            return null;
        }
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
        if(this.price == null || this.priceAvg50 == null) {
            return null;
        }
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
        if(this.price == null || this.priceAvg200 == null) {
            return null;
        }
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
    
    public Long getAskSize() {
        return askSize;
    }
    
    public void setAskSize(Long askSize) {
        this.askSize = askSize;
    }
    
    public BigDecimal getBid() {
        return bid;
    }
    
    public void setBid(BigDecimal bid) {
        this.bid = bid;
    }
    
    public Long getBidSize() {
        return bidSize;
    }
    
    public void setBidSize(Long bidSize) {
        this.bidSize = bidSize;
    }
    
    public BigDecimal getPrice() {
        return price;
    }
    
    public void setPrice(BigDecimal price) {
        this.price = price;
    }
    
    public Long getLastTradeSize() {
        return lastTradeSize;
    }
    
    public void setLastTradeSize(Long lastTradeSize) {
        this.lastTradeSize = lastTradeSize;
    }

    public String getLastTradeDateStr() {
        return lastTradeDateStr;
    }

    public void setLastTradeDateStr(String lastTradeDateStr) {
        this.lastTradeDateStr = lastTradeDateStr;
    }

    public String getLastTradeTimeStr() {
        return lastTradeTimeStr;
    }

    public void setLastTradeTimeStr(String lastTradeTimeStr) {
        this.lastTradeTimeStr = lastTradeTimeStr;
    }
    
    /**
     * Will derive the time zone from the exchange to parse the date time into a Calendar object.
     * This will not react to changes in the lastTradeDateStr and lastTradeTimeStr
     * 
     * @return last trade date time
     */
    public Calendar getLastTradeTime() {
        return lastTradeTime;
    }
    
    public void setLastTradeTime(Calendar lastTradeTime) {
        this.lastTradeTime = lastTradeTime;
    }
    
    /**
     * Will use the provided time zone to parse the date time into a Calendar object
     * Reacts to changes in the lastTradeDateStr and lastTradeTimeStr
     * 
     * @param timeZone time zone where the stock is traded
     * @return last trade date time
     */
    public Calendar getLastTradeTime(TimeZone timeZone) {
        return Utils.parseDateTime(this.lastTradeDateStr, this.lastTradeTimeStr, timeZone);
    }

    public TimeZone getTimeZone() {
        return timeZone;
    }

    public void setTimeZone(TimeZone timeZone) {
        this.timeZone = timeZone;
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
    
    public Long getVolume() {
        return volume;
    }
    
    public void setVolume(Long volume) {
        this.volume = volume;
    }
    
    public Long getAvgVolume() {
        return avgVolume;
    }
    
    public void setAvgVolume(Long avgVolume) {
        this.avgVolume = avgVolume;
    }
    
    @Override
    public String toString() {
        return "Ask: " + this.ask + ", Bid: " + this.bid + ", Price: " + this.price + ", Prev close: " + this.previousClose;
    }
    
}

