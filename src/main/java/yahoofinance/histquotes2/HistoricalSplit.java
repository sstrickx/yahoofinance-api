package yahoofinance.histquotes2;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * All getters can return null in case the data is not available from Yahoo Finance.
 * 
 * @author Randle McMurphy
 */
public class HistoricalSplit {
	
    private String symbol;
    
    private Calendar date;
    
    private BigDecimal numerator;
    private BigDecimal denumerator;
    
    public HistoricalSplit() {}

    public HistoricalSplit(String symbol, Calendar date, BigDecimal numerator, BigDecimal denumerator) {
        this.symbol = symbol;
        this.date = date;
        this.numerator = numerator;
        this.denumerator = denumerator;
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

    public BigDecimal getNumerator() {
        return numerator;
    }

    public void setNumerator(BigDecimal numerator) {
        this.numerator = numerator;
    }

    public BigDecimal getDenumerator() {
        return denumerator;
    }

    public void setDenumerator(BigDecimal denumerator) {
        this.denumerator = denumerator;
    }

    /**
     * 
     * @return      a calculated split factor value which is equal to numerator divided by denumerator
     */
    public BigDecimal getSplitFactor() {
        return numerator.divide(denumerator);
    }

    @Override
    public String toString() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String dateStr = dateFormat.format(this.date.getTime());
        return "SPLIT: " + this.symbol + "@" + dateStr + ": " + this.numerator + " / " + this.denumerator;
    }

}
