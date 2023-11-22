
package yahoofinance.histquotes;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * All getters can return null in case the data is not available from Yahoo Finance.
 * 
 * @author Stijn Strickx
 */
public class HistoricalQuote {
    private String symbol;
    private Calendar date;
    private BigDecimal open;
    private BigDecimal low;
    private BigDecimal high;
    private BigDecimal close;
    private BigDecimal adjClose;
    private Long volume;

    public HistoricalQuote(String symbol, Calendar date) {
        this.symbol = symbol;
        this.date = date;
    }

    public String getSymbol() {
        return symbol;
    }

    public HistoricalQuote setSymbol(String symbol) {
        this.symbol = symbol;
        return this;
    }
    
    public Calendar getDate() {
        return date;
    }

    public HistoricalQuote setDate(Calendar date) {
        this.date = date;
        return this;
    }

    public BigDecimal getOpen() {
        return open;
    }

    public HistoricalQuote setOpen(BigDecimal open) {
        this.open = open;
        return this;
    }

    /**
     * 
     * @return      the intra-day low
     */
    public BigDecimal getLow() {
        return low;
    }

    public HistoricalQuote setLow(BigDecimal low) {
        this.low = low;
        return this;
    }

    public BigDecimal getHigh() {
        return high;
    }

    public HistoricalQuote setHigh(BigDecimal high) {
        this.high = high;
        return this;
    }

    public BigDecimal getClose() {
        return close;
    }

    public HistoricalQuote setClose(BigDecimal close) {
        this.close = close;
        return this;
    }

    /**
     * The adjusted closing price on a specific date 
     * reflects all of the dividends and splits since that day.
     * The adjusted closing price from a date in history can be used to 
     * calculate a close estimate of the total return, including dividends, 
     * that an investor earned if shares were purchased on that date.
     * @return      the adjusted close price
     */
    public BigDecimal getAdjClose() {
        return adjClose;
    }

    public HistoricalQuote setAdjClose(BigDecimal adjClose) {
        this.adjClose = adjClose;
        return this;
    }

    public Long getVolume() {
        return volume;
    }

    public HistoricalQuote setVolume(Long volume) {
        this.volume = volume;
        return this;
    }
    
    @Override
    public String toString() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String dateStr = dateFormat.format(this.date.getTime());
        return this.symbol + "@" + dateStr + ": " + this.low + "-" + this.high + ", " + 
                this.open + "->" + this.close + " (" + this.adjClose + ")";
    }
}
