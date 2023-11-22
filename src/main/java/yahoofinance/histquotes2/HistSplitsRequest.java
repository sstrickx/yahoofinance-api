package yahoofinance.histquotes2;

import yahoofinance.utils.*;
import yahoofinance.YahooFinance;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
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
        BufferedReader br = getBufferedReader();
        br.readLine(); // skip the first line
        // Parse CSV
        for (String line = br.readLine(); line != null; line = br.readLine()) {
            log.info("Parsing CSV line: " + Utils.unescape(line));
            HistoricalSplit split = this.parseCSVLine(line);
            result.add(split);
        }
        return result;
    }

    private BufferedReader getBufferedReader() throws IOException {
        String url = createUrl();
        return RequestUtils.getBufferedReader(url, RequestUtils.getUrlConnection(url).getInputStream());
    }

    private String createUrl() throws IOException {
        Map<String, String> params = RequestUtils.configureParamsPeriodsAndInterval(from, to, DEFAULT_INTERVAL);
        // This will instruct Yahoo to return splits
        params.put("events", "split");
        params.put("crumb", CrumbManager.getCrumb());

        return YahooFinance.HISTQUOTES2_BASE_URL + URLEncoder.encode(this.symbol , "UTF-8") + "?" + Utils.getURLParameters(params);
    }

    private HistoricalSplit parseCSVLine(String line) {
        String[] data = line.split(YahooFinance.QUOTES_CSV_DELIMITER);
    	String[] parts = data[1].split(":");
        return new HistoricalSplit(this.symbol,
                CalendarUtil.parseHistDate(data[0]),
                BigDecimalUtil.getBigDecimal(parts[0]),
                BigDecimalUtil.getBigDecimal(parts[1])
        );
    }

}
