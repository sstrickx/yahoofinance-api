
package yahoofinance.quotes.stock;

import java.math.BigDecimal;

import yahoofinance.Utils;

/**
 *
 * @author Stijn Strickx
 */
public class StockStats {
    
    private final String symbol;
    
    private BigDecimal marketCap;
    private long sharesFloat;
    private long sharesOutstanding;
    private long sharesOwned;
    
    private BigDecimal eps;
    private BigDecimal pe;
    private BigDecimal peg;
    
    private BigDecimal epsEstimateCurrentYear;
    private BigDecimal epsEstimateNextQuarter;
    private BigDecimal epsEstimateNextYear;
    
    private BigDecimal priceBook;
    private BigDecimal priceSales;
    private BigDecimal bookValuePerShare;
    
    private BigDecimal revenue; // ttm
    private BigDecimal EBITDA; // ttm
    private BigDecimal oneYearTargetPrice;
    
    public StockStats(String symbol) {
        this.symbol = symbol;
    }
    
    public BigDecimal getROE() {
        return Utils.getPercent(this.EBITDA, this.marketCap);
    }

    public String getSymbol() {
        return symbol;
    }
    
    public BigDecimal getMarketCap() {
        return marketCap;
    }

    public void setMarketCap(BigDecimal marketCap) {
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

    public BigDecimal getEps() {
        return eps;
    }

    public void setEps(BigDecimal eps) {
        this.eps = eps;
    }

    public BigDecimal getPe() {
        return pe;
    }

    public void setPe(BigDecimal pe) {
        this.pe = pe;
    }

    public BigDecimal getPeg() {
        return peg;
    }

    public void setPeg(BigDecimal peg) {
        this.peg = peg;
    }

    public BigDecimal getEpsEstimateCurrentYear() {
        return epsEstimateCurrentYear;
    }

    public void setEpsEstimateCurrentYear(BigDecimal epsEstimateCurrentYear) {
        this.epsEstimateCurrentYear = epsEstimateCurrentYear;
    }

    public BigDecimal getEpsEstimateNextQuarter() {
        return epsEstimateNextQuarter;
    }

    public void setEpsEstimateNextQuarter(BigDecimal epsEstimateNextQuarter) {
        this.epsEstimateNextQuarter = epsEstimateNextQuarter;
    }

    public BigDecimal getEpsEstimateNextYear() {
        return epsEstimateNextYear;
    }

    public void setEpsEstimateNextYear(BigDecimal epsEstimateNextYear) {
        this.epsEstimateNextYear = epsEstimateNextYear;
    }

    public BigDecimal getPriceBook() {
        return priceBook;
    }

    public void setPriceBook(BigDecimal priceBook) {
        this.priceBook = priceBook;
    }

    public BigDecimal getPriceSales() {
        return priceSales;
    }

    public void setPriceSales(BigDecimal priceSales) {
        this.priceSales = priceSales;
    }

    public BigDecimal getBookValuePerShare() {
        return bookValuePerShare;
    }

    public void setBookValuePerShare(BigDecimal bookValuePerShare) {
        this.bookValuePerShare = bookValuePerShare;
    }

    public BigDecimal getRevenue() {
        return revenue;
    }

    public void setRevenue(BigDecimal revenue) {
        this.revenue = revenue;
    }

    public BigDecimal getEBITDA() {
        return EBITDA;
    }

    public void setEBITDA(BigDecimal EBITDA) {
        this.EBITDA = EBITDA;
    }

    public BigDecimal getOneYearTargetPrice() {
        return oneYearTargetPrice;
    }

    public void setOneYearTargetPrice(BigDecimal oneYearTargetPrice) {
        this.oneYearTargetPrice = oneYearTargetPrice;
    }
    
    @Override
    public String toString() {
        return "EPS: " + this.eps + ", PE: " + this.pe + ", PEG: " + this.peg;
    }
    
}
