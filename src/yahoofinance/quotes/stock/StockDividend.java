
package yahoofinance.quotes.stock;

import java.util.Calendar;

/**
 *
 * @author Stijn Strickx
 */
public class StockDividend {
    
    private final String symbol;
    
    private Calendar payDate;
    private Calendar exDate;
    private double annualYield;
    private double annualYieldPercent;
    
    public StockDividend(String symbol) {
        this.symbol = symbol;
    }
    
    public StockDividend(String symbol, Calendar payDate, Calendar exDate, double annualYield, double annualYieldPercent) {
        this(symbol);
        this.payDate = payDate;
        this.exDate = exDate;
        this.annualYield = annualYield;
        this.annualYieldPercent = annualYieldPercent;
    }

    public String getSymbol() {
        return symbol;
    }
    
    public Calendar getPayDate() {
        return payDate;
    }

    public void setPayDate(Calendar payDate) {
        this.payDate = payDate;
    }

    public Calendar getExDate() {
        return exDate;
    }

    public void setExDate(Calendar exDate) {
        this.exDate = exDate;
    }
    
    public double getAnnualYield() {
        return annualYield;
    }
    
    public void setAnnualYield(double annualYield) {
        this.annualYield = annualYield;
    }
    
    public double getAnnualYieldPercent() {
        return annualYieldPercent;
    }
    
    public void setAnnualYieldPercent(double annualYieldPercent) {
        this.annualYieldPercent = annualYieldPercent;
    }
    
    @Override
    public String toString() {
        String payDateStr = "/";
        String exDateStr = "/";
        if(this.payDate != null) {
            payDateStr = this.payDate.getTime().toString();
        }
        if(this.exDate != null) {
            exDateStr = this.exDate.getTime().toString();
        }
        return "Pay date: " + payDateStr + ", Ex date: " + exDateStr + ", Annual yield: " + this.getAnnualYieldPercent() + "%";
    }
    
}
