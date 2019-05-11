package yahoofinance.quotes.options;

import yahoofinance.Stock;

public class OptionsResponse {

    private final Stock stockQuote;
    private final OptionsChain optionsChain;

    public OptionsResponse(Stock stockQuote, OptionsChain optionsChain) {
        this.stockQuote = stockQuote;
        this.optionsChain = optionsChain;
    }

    public Stock getStockQuote() {
        return stockQuote;
    }

    public OptionsChain getOptionsChain() {
        return optionsChain;
    }
}
