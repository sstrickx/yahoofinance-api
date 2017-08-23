package yahoofinance;

/**
 * @author Stijn Strickx
 */
public class CsvStock extends AbstractStock<String> {

    public CsvStock(String symbol) {
        super(symbol);
    }

    @Override
    protected String transform(String csv) {
      return csv;
    }

}
