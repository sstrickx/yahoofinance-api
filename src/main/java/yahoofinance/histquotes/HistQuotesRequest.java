package yahoofinance.histquotes;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import yahoofinance.Utils;
import yahoofinance.YahooFinance;

/**
 *
 * @author Stijn Strickx
 */
public class HistQuotesRequest {

    private final String symbol;

    private final Calendar from;
    private final Calendar to;

    private final Interval interval;

    public static final Calendar DEFAULT_FROM = Calendar.getInstance();

    static {
        DEFAULT_FROM.add(Calendar.YEAR, -1);
    }
    public static final Calendar DEFAULT_TO = Calendar.getInstance();
    public static final Interval DEFAULT_INTERVAL = Interval.MONTHLY;

    public HistQuotesRequest(String symbol) {
        this(symbol, DEFAULT_INTERVAL);
    }

    public HistQuotesRequest(String symbol, Interval interval) {
        this(symbol, DEFAULT_FROM, DEFAULT_TO, interval);
    }

    public HistQuotesRequest(String symbol, Calendar from, Calendar to) {
        this(symbol, from, to, DEFAULT_INTERVAL);
    }

    public HistQuotesRequest(String symbol, Calendar from, Calendar to, Interval interval) {
        this.symbol = symbol;
        this.from = this.cleanHistCalendar(from);
        this.to = this.cleanHistCalendar(to);
        this.interval = interval;
    }

    public HistQuotesRequest(String symbol, Date from, Date to) {
        this(symbol, from, to, DEFAULT_INTERVAL);
    }

    public HistQuotesRequest(String symbol, Date from, Date to, Interval interval) {
        this(symbol, interval);
        this.from.setTime(from);
        this.to.setTime(to);
        this.cleanHistCalendar(this.from);
        this.cleanHistCalendar(this.to);
    }
    
    /**
     * Put everything smaller than days at 0
     * @param cal calendar to be cleaned
     */
    private Calendar cleanHistCalendar(Calendar cal) {
        cal.set(Calendar.MILLISECOND, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.HOUR, 0);
        return cal;
    }

    public List<HistoricalQuote> getResult() throws IOException {

        List<HistoricalQuote> result = new ArrayList<HistoricalQuote>();
        
        /*
            It's not necessary, because I can want the quote for a specific date.
        
            if(this.from.equals(this.to) || this.from.after(this.to)) {
                YahooFinance.logger.log(Level.WARNING, "Unable to retrieve historical quotes. "
                        + "From-date should be at least 1 day before to-date. From: " 
                        + this.from.getTime() + ", to: " + this.to.getTime());
                return result;
            }
        */
        Map<String, String> params = new LinkedHashMap<String, String>();
        params.put("s", this.symbol);

        params.put("a", String.valueOf(this.from.get(Calendar.MONTH)));
        params.put("b", String.valueOf(this.from.get(Calendar.DAY_OF_MONTH)));
        params.put("c", String.valueOf(this.from.get(Calendar.YEAR)));

        params.put("d", String.valueOf(this.to.get(Calendar.MONTH)));
        params.put("e", String.valueOf(this.to.get(Calendar.DAY_OF_MONTH)));
        params.put("f", String.valueOf(this.to.get(Calendar.YEAR)));

        params.put("g", this.interval.getTag());

        params.put("ignore", ".csv");

        String url = YahooFinance.HISTQUOTES_BASE_URL + "?" + Utils.getURLParameters(params);

        // Get CSV from Yahoo
        YahooFinance.logger.log(Level.INFO, ("Sending request: " + url));

        URL request = new URL(url);
        URLConnection connection = request.openConnection();
        connection.setConnectTimeout(YahooFinance.CONNECTION_TIMEOUT);
        connection.setReadTimeout(YahooFinance.CONNECTION_TIMEOUT);
        InputStreamReader is = new InputStreamReader(connection.getInputStream());
        BufferedReader br = new BufferedReader(is);
        br.readLine(); // skip the first line
        // Parse CSV
        for (String line = br.readLine(); line != null; line = br.readLine()) {

            YahooFinance.logger.log(Level.INFO, ("Parsing CSV line: " + Utils.unescape(line)));
            HistoricalQuote quote = this.parseCSVLine(line);
            result.add(quote);
        }
        return result;
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

}
