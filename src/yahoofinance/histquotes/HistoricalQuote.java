
package yahoofinance.histquotes;

import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 *
 * @author Stijn Strickx
 */
public class HistoricalQuote {
    
    private String symbol;
    
    private Calendar date;
    
    private double open;
    private double low;
    private double high;
    private double close;
    
    private double adjClose;
    
    private long volume;
    
    public HistoricalQuote() {}

    public HistoricalQuote(String symbol, Calendar date, double open, double low, double high, double close, double adjClose, long volume) {
        this.symbol = symbol;
        this.date = date;
        this.open = open;
        this.low = low;
        this.high = high;
        this.close = close;
        this.adjClose = adjClose;
        this.volume = volume;
    }

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }
    
    public Calendar getDate() {
        return date;
    }

    public void setDate(Calendar date) {
        this.date = date;
    }

    public double getOpen() {
        return open;
    }

    public void setOpen(double open) {
        this.open = open;
    }

    /**
     * 
     * @return      the intra-day low
     */
    public double getLow() {
        return low;
    }

    public void setLow(double low) {
        this.low = low;
    }
    
    /**
     * 
     * @return      the intra-day high
     */
    public double getHigh() {
        return high;
    }

    public void setHigh(double high) {
        this.high = high;
    }

    public double getClose() {
        return close;
    }

    public void setClose(double close) {
        this.close = close;
    }

    /**
     * The adjusted closing price on a specific date 
     * reflects all of the dividends and splits since that day.
     * The adjusted closing price from a date in history can be used to 
     * calculate a close estimate of the total return, including dividends, 
     * that an investor earned if shares were purchased on that date.
     * @return      the adjusted close price
     */
    public double getAdjClose() {
        return adjClose;
    }

    public void setAdjClose(double adjClose) {
        this.adjClose = adjClose;
    }

    public long getVolume() {
        return volume;
    }

    public void setVolume(long volume) {
        this.volume = volume;
    }
    
    @Override
    public String toString() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String dateStr = dateFormat.format(this.date.getTime());
        return this.symbol + "@" + dateStr + ": " + this.low + "-" + this.high + ", " + 
                this.open + "->" + this.close + " (" + this.adjClose + ")";
    }
}
