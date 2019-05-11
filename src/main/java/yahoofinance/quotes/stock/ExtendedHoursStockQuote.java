package yahoofinance.quotes.stock;

import java.math.BigDecimal;
import java.util.Calendar;

public class ExtendedHoursStockQuote {

    private BigDecimal price;
    private BigDecimal priceChange;
    private BigDecimal changePercent;
    private Calendar time;
    private ExtendedHoursStockQuoteType type;

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public BigDecimal getPriceChange() {
        return priceChange;
    }

    public void setPriceChange(BigDecimal priceChange) {
        this.priceChange = priceChange;
    }

    public BigDecimal getChangePercent() {
        return changePercent;
    }

    public void setChangePercent(BigDecimal changePercent) {
        this.changePercent = changePercent;
    }

    public Calendar getTime() {
        return time;
    }

    public void setTime(Calendar time) {
        this.time = time;
    }

    public ExtendedHoursStockQuoteType getType() {
        return type;
    }

    public void setType(ExtendedHoursStockQuoteType type) {
        this.type = type;
    }
}
