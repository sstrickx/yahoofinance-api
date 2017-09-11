package yahoofinance.histquotes2;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * At the time of this writing Yahoo returns ADJUSTED dividends. Which means that as soon as
 * split occurs, all past dividends are divided by split factor.
 * All getters can return null in case the data is not available from Yahoo Finance.
 * 
 * @author Randle McMurphy
 */
public class HistoricalDividend {
	
    private String symbol;
    
    private Calendar date;
    
    private BigDecimal adjDividend;
    
    public HistoricalDividend() {}

    public HistoricalDividend(String symbol, Calendar date, BigDecimal adjDividend) {
        this.symbol = symbol;
        this.date = date;
        this.adjDividend = adjDividend;
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

    /**
     * At the time of this writing Yahoo returns ADJUSTED dividends. Which means that as soon as
     * split occurs, all past dividends are divided by split factor.
     * 
     * @return      an adjusted dividend cash
     */
    public BigDecimal getAdjDividend() {
        return adjDividend;
    }

    public void setAdjDividend(BigDecimal adjDividend) {
        this.adjDividend = adjDividend;
    }

    @Override
    public String toString() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String dateStr = dateFormat.format(this.date.getTime());
        return "DIVIDEND: " + this.symbol + "@" + dateStr + ": " + this.adjDividend;
    }
}
