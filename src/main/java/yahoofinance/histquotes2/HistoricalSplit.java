package yahoofinance.histquotes2;

import java.math.BigDecimal;
import java.math.RoundingMode;
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
    private BigDecimal denominator;
    
    public HistoricalSplit() {}

    public HistoricalSplit(String symbol, Calendar date, BigDecimal numerator, BigDecimal denominator) {
        this.symbol = symbol;
        this.date = date;
        this.numerator = numerator;
        this.denominator = denominator;
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

    public BigDecimal getDenominator() {
        return denominator;
    }

    public void setDenominator(BigDecimal denominator) {
        this.denominator = denominator;
    }

    /**
     * 
     * @return      a calculated split factor value which is equal to numerator divided by denominator
     */
    public BigDecimal getSplitFactor() {
        return numerator.divide(denominator, 10, RoundingMode.HALF_UP);  
    }

    @Override
    public String toString() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String dateStr = dateFormat.format(this.date.getTime());
        return "SPLIT: " + this.symbol + "@" + dateStr + ": " + this.numerator + " / " + this.denominator;
    }

}
