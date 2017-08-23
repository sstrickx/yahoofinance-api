package yahoofinance;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

import tech.tablesaw.api.Table;

/**
 * @author Stijn Strickx
 */
public class TablesawStock extends AbstractStock<Table> {

  public TablesawStock(String symbol) {
    super(symbol);
  }

  @Override
  protected Table transform(String csv) {
    try {
      return Table.read().csv(new ByteArrayInputStream(csv.getBytes(StandardCharsets.UTF_8)), symbol);
    } catch (IOException e) {
      throw new IllegalStateException("Could not transform CSV to Table", e);
    }
  }

}
