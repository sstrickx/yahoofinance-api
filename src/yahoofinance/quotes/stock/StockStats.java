
package yahoofinance.quotes.stock;

import yahoofinance.Utils;

/**
 *
 * @author Stijn Strickx
 */
public class StockStats {
    
    private final String symbol;
    
    private double marketCap;
    private long sharesFloat;
    private long sharesOutstanding;
    private long sharesOwned;
    
    private double eps;
    private double pe;
    private double peg;
    
    private double epsEstimateCurrentYear;
    private double epsEstimateNextQuarter;
    private double epsEstimateNextYear;
    
    private double priceBook;
    private double priceSales;
    private double bookValuePerShare;
    
    private double revenue; // ttm
    private double EBITDA; // ttm
    private double oneYearTargetPrice;
    
    public StockStats(String symbol) {
        this.symbol = symbol;
    }
    
    public double getROE() {
        return Utils.getPercent(this.EBITDA, this.marketCap);
    }

    public String getSymbol() {
        return symbol;
    }
    
    public double getMarketCap() {
        return marketCap;
    }

    public void setMarketCap(double marketCap) {
        this.marketCap = marketCap;
    }

    public long getSharesFloat() {
        return sharesFloat;
    }

    public void setSharesFloat(long sharesFloat) {
        this.sharesFloat = sharesFloat;
    }

    public long getSharesOutstanding() {
        return sharesOutstanding;
    }

    public void setSharesOutstanding(long sharesOutstanding) {
        this.sharesOutstanding = sharesOutstanding;
    }

    public long getSharesOwned() {
        return sharesOwned;
    }

    public void setSharesOwned(long sharesOwned) {
        this.sharesOwned = sharesOwned;
    }

    public double getEps() {
        return eps;
    }

    public void setEps(double eps) {
        this.eps = eps;
    }

    public double getPe() {
        return pe;
    }

    public void setPe(double pe) {
        this.pe = pe;
    }

    public double getPeg() {
        return peg;
    }

    public void setPeg(double peg) {
        this.peg = peg;
    }

    public double getEpsEstimateCurrentYear() {
        return epsEstimateCurrentYear;
    }

    public void setEpsEstimateCurrentYear(double epsEstimateCurrentYear) {
        this.epsEstimateCurrentYear = epsEstimateCurrentYear;
    }

    public double getEpsEstimateNextQuarter() {
        return epsEstimateNextQuarter;
    }

    public void setEpsEstimateNextQuarter(double epsEstimateNextQuarter) {
        this.epsEstimateNextQuarter = epsEstimateNextQuarter;
    }

    public double getEpsEstimateNextYear() {
        return epsEstimateNextYear;
    }

    public void setEpsEstimateNextYear(double epsEstimateNextYear) {
        this.epsEstimateNextYear = epsEstimateNextYear;
    }

    public double getPriceBook() {
        return priceBook;
    }

    public void setPriceBook(double priceBook) {
        this.priceBook = priceBook;
    }

    public double getPriceSales() {
        return priceSales;
    }

    public void setPriceSales(double priceSales) {
        this.priceSales = priceSales;
    }

    public double getBookValuePerShare() {
        return bookValuePerShare;
    }

    public void setBookValuePerShare(double bookValuePerShare) {
        this.bookValuePerShare = bookValuePerShare;
    }

    public double getRevenue() {
        return revenue;
    }

    public void setRevenue(double revenue) {
        this.revenue = revenue;
    }

    public double getEBITDA() {
        return EBITDA;
    }

    public void setEBITDA(double EBITDA) {
        this.EBITDA = EBITDA;
    }

    public double getOneYearTargetPrice() {
        return oneYearTargetPrice;
    }

    public void setOneYearTargetPrice(double oneYearTargetPrice) {
        this.oneYearTargetPrice = oneYearTargetPrice;
    }
    
    @Override
    public String toString() {
        return "EPS: " + this.eps + ", PE: " + this.pe + ", PEG: " + this.peg;
    }
    
}
