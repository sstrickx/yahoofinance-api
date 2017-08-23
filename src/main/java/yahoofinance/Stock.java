package yahoofinance;

import java.util.ArrayList;
import java.util.List;

import yahoofinance.histquotes.HistoricalQuote;

/**
 * @author Stijn Strickx
 */
public class Stock extends AbstractStock<List<HistoricalQuote>> {

  public Stock(String symbol) {
    super(symbol);
  }

  @Override
  protected List<HistoricalQuote> transform(String csv) {
    String[] lines = csv.split("\\r?\\n");
    List<HistoricalQuote> quotes = new ArrayList<>();
    for (int i = 1; i < lines.length; i++) {
      quotes.add(YahooFinance.HISTQUOTES2_ENABLED.equalsIgnoreCase("true")
          ? parseCSVLine2(lines[i]) : parseCSVLine(lines[i]));
    }
    return quotes;
  }

  private HistoricalQuote parseCSVLine(String line) {
    String[] data = line.split(YahooFinance.QUOTES_CSV_DELIMITER);
    return new HistoricalQuote(this.symbol,
            Utils.parseHistDate(data[0]),
            Utils.getBigDecimal(data[1]),
            Utils.getBigDecimal(data[3]),
            Utils.getBigDecimal(data[2]),
            Utils.getBigDecimal(data[4]),
            Utils.getBigDecimal(data[6]),
            Utils.getLong(data[5])
    );
  }

  private HistoricalQuote parseCSVLine2(String line) {
    String[] data = line.split(YahooFinance.QUOTES_CSV_DELIMITER);
    return new HistoricalQuote(this.symbol,
          Utils.parseHistDate(data[0]),
          Utils.getBigDecimal(data[1]),
          Utils.getBigDecimal(data[3]),
          Utils.getBigDecimal(data[2]),
          Utils.getBigDecimal(data[4]),
          Utils.getBigDecimal(data[5]),
          Utils.getLong(data[6])
    );
  }
}
