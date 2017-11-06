
package yahoofinance.quotes.stock;

import java.math.BigDecimal;
import java.util.Calendar;

import yahoofinance.Utils;

/**
 * All getters can return null in case the data is not available from Yahoo Finance.
 * 
 * @author Stijn Strickx
 */
public class StockStats {
    
    private final String symbol;
    
    private BigDecimal marketCap;
    private Long sharesFloat;
    private Long sharesOutstanding;
    private Long sharesOwned;
    
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
    
    private BigDecimal shortRatio;

    private Calendar earningsAnnouncement;
    
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

    public Long getSharesFloat() {
        return sharesFloat;
    }

    public void setSharesFloat(Long sharesFloat) {
        this.sharesFloat = sharesFloat;
    }

    public Long getSharesOutstanding() {
        return sharesOutstanding;
    }

    public void setSharesOutstanding(Long sharesOutstanding) {
        this.sharesOutstanding = sharesOutstanding;
    }

    public Long getSharesOwned() {
        return sharesOwned;
    }

    public void setSharesOwned(Long sharesOwned) {
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

    public BigDecimal getShortRatio() {
        return shortRatio;
    }

    public void setShortRatio(BigDecimal shortRatio) {
        this.shortRatio = shortRatio;
    }

    public Calendar getEarningsAnnouncement() {
        return earningsAnnouncement;
    }

    public void setEarningsAnnouncement(Calendar earningsAnnouncement) {
        this.earningsAnnouncement = earningsAnnouncement;
    }

    @Override
    public String toString() {
        String earningsStr = "/";
        if(this.earningsAnnouncement != null) {
            earningsStr = this.earningsAnnouncement.getTime().toString();
        }
        return "EPS: " + this.eps + ", PE: " + this.pe + ", Earnings announcement: " + earningsStr;
    }
    
}
