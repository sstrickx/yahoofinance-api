package yahoofinance.histquotes2;

import yahoofinance.Utils;
import yahoofinance.YahooFinance;
import yahoofinance.util.RedirectableRequest;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.util.*;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author Stijn Strickx (modified by Randle McMurphy)
 */
public class HistSplitsRequest {


    private static final Logger log = LoggerFactory.getLogger(HistSplitsRequest.class);
    private final String symbol;

    private final Calendar from;
    private final Calendar to;

    public static final Calendar DEFAULT_FROM = Calendar.getInstance();

    static {
        DEFAULT_FROM.add(Calendar.YEAR, -1);
    }
    public static final Calendar DEFAULT_TO = Calendar.getInstance();

    // Interval has no meaning here and is not used here
    // But it's better to leave it because Yahoo's standard query URL still contains it
    public static final QueryInterval DEFAULT_INTERVAL = QueryInterval.DAILY;

    public HistSplitsRequest(String symbol) {
        this(symbol, DEFAULT_FROM, DEFAULT_TO);
    }

    public HistSplitsRequest(String symbol, Calendar from, Calendar to) {
        this.symbol = symbol;
        this.from = this.cleanHistCalendar(from);
        this.to = this.cleanHistCalendar(to);
    }

    public HistSplitsRequest(String symbol, Date from, Date to) {
        this(symbol);
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

    public List<HistoricalSplit> getResult() throws IOException {

        List<HistoricalSplit> result = new ArrayList<HistoricalSplit>();
        
        if(this.from.after(this.to)) {
            log.warn("Unable to retrieve historical splits. "
                    + "From-date should not be after to-date. From: "
                    + this.from.getTime() + ", to: " + this.to.getTime());
            return result;
        }

        Map<String, String> params = new LinkedHashMap<String, String>();
        params.put("period1", String.valueOf(this.from.getTimeInMillis() / 1000));
        params.put("period2", String.valueOf(this.to.getTimeInMillis() / 1000));

        // Interval has no meaning here and is not used here
        // But it's better to leave it because Yahoo's standard query URL still contains it
        params.put("interval", DEFAULT_INTERVAL.getTag());
        
        // This will instruct Yahoo to return splits
        params.put("events", "split");

        params.put("crumb", CrumbManager.getCrumb());

        String url = YahooFinance.HISTQUOTES2_BASE_URL + URLEncoder.encode(this.symbol , "UTF-8") + "?" + Utils.getURLParameters(params);

        // Get CSV from Yahoo
        log.info("Sending request: " + url);

        URL request = new URL(url);
        RedirectableRequest redirectableRequest = new RedirectableRequest(request, 5);
        redirectableRequest.setConnectTimeout(YahooFinance.CONNECTION_TIMEOUT);
        redirectableRequest.setReadTimeout(YahooFinance.CONNECTION_TIMEOUT);
        Map<String, String> requestProperties = new HashMap<String, String>();
        requestProperties.put("Cookie", CrumbManager.getCookie());
        URLConnection connection = redirectableRequest.openConnection(requestProperties);

        InputStreamReader is = new InputStreamReader(connection.getInputStream());
        BufferedReader br = new BufferedReader(is);
        br.readLine(); // skip the first line
        // Parse CSV
        for (String line = br.readLine(); line != null; line = br.readLine()) {

            log.info("Parsing CSV line: " + Utils.unescape(line));
            HistoricalSplit split = this.parseCSVLine(line);
            result.add(split);
        }
        return result;
    }

    private HistoricalSplit parseCSVLine(String line) {
        String[] data = line.split(YahooFinance.QUOTES_CSV_DELIMITER);
    	String[] parts = data[1].split(":");
        return new HistoricalSplit(this.symbol,
                Utils.parseHistDate(data[0]),
                Utils.getBigDecimal(parts[0]),
                Utils.getBigDecimal(parts[1])
        );
    }

}
