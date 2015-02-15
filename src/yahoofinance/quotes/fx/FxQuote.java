
package yahoofinance.quotes.fx;

import java.math.BigDecimal;

/**
 *
 * @author Stijn Strickx
 */
public class FxQuote {
    
    private String symbol;
    private BigDecimal price;
    
    public FxQuote(String symbol) {
        this.symbol = symbol;
        this.price = BigDecimal.ZERO;
    }
    
    public FxQuote(String symbol, BigDecimal price) {
        this.symbol = symbol;
        this.price = price;
    }

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    /**
     * Returns the requested FX rate.
     * If the data is not available yet, it will first be requested from Yahoo Finance.
     * 
     * @return  the requested FX rate
     */
    public BigDecimal getPrice() {
        if(price.equals(BigDecimal.ZERO)) {
            return this.getPrice(true);
        }
        return price;
    }
    
    /**
     * Returns the requested FX rate.
     * This method will return 0 in the following situations:
     * <ul>
     * <li> the data hasn't been loaded yet
     *      in a previous request and refresh is set to false.
     * <li> refresh is true and the data cannot be retrieved from Yahoo Finance 
     *      for whatever reason (symbol not recognized, no network connection, ...)
     * </ul>
     * 
     * @param refresh   indicates whether the data should be requested again to Yahoo Finance
     * @return          the requested FX rate
     */
    public BigDecimal getPrice(boolean refresh) {
        if(refresh) {
            FxQuotesRequest request = new FxQuotesRequest(this.symbol);
            this.price = request.getSingleResult().getPrice();
        }
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }
    
    @Override
    public String toString() {
        return this.symbol + ": " + this.price;
    }
    
}
