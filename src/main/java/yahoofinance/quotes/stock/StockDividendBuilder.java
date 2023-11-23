package yahoofinance.quotes.stock;

import java.math.BigDecimal;
import java.util.Calendar;

public class StockDividendBuilder {
    private final StockDividend stockDividend;

    private StockDividendBuilder(String symbol) {
        stockDividend = new StockDividend(symbol);
    }

    public static StockDividendBuilder builder(String symbol) {
        return new StockDividendBuilder(symbol);
    }

    public StockDividendBuilder setPayDate(Calendar payDate) {
        stockDividend.setPayDate(payDate);
        return this;
    }

    public StockDividendBuilder setExDate(Calendar exDate) {
        stockDividend.setExDate(exDate);
        return this;
    }

    public StockDividendBuilder setAnnualYield(BigDecimal annualYield) {
        stockDividend.setAnnualYield(annualYield);
        return this;
    }

    public StockDividendBuilder setAnnualYieldPercent(BigDecimal annualYieldPercent) {
        stockDividend.setAnnualYieldPercent(annualYieldPercent);
        return this;
    }

    public StockDividend build() {
        return this.stockDividend;
    }
}
