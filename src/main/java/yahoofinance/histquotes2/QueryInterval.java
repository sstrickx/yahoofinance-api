
package yahoofinance.histquotes2;

/**
 *
 * @author Stijn Strickx
 */
public enum QueryInterval {
    
    DAILY("1d"),
    WEEKLY("5d"),
    MONTHLY("1mo");
    
    private final String tag;
    
    QueryInterval(String tag) {
        this.tag = tag;
    }
    
    public String getTag() {
        return this.tag;
    }
    
}
