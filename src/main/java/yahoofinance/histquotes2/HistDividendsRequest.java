package yahoofinance.histquotes2;

import yahoofinance.utils.*;
import yahoofinance.YahooFinance;

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
 * @author Stijn Strickx (modified by Randle McMurphy)
 */
public class HistDividendsRequest {
    private static final Logger log = LoggerFactory.getLogger(HistDividendsRequest.class);
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

    public HistDividendsRequest(String symbol) {
        this(symbol, DEFAULT_FROM, DEFAULT_TO);
    }

    public HistDividendsRequest(String symbol, Calendar from, Calendar to) {
        this.symbol = symbol;
        this.from = this.cleanHistCalendar(from);
        this.to = this.cleanHistCalendar(to);
    }

    public HistDividendsRequest(String symbol, Date from, Date to) {
        this(symbol);
        this.from.setTime(from);
        this.to.setTime(to);
        this.cleanHistCalendar(this.from);
        this.cleanHistCalendar(this.to);
    }

    /**
     * Put everything smaller than days at 0
     *
     * @param cal calendar to be cleaned
     */
    private Calendar cleanHistCalendar(Calendar cal) {
        cal.set(Calendar.MILLISECOND, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.HOUR, 0);
        return cal;
    }

    public List<HistoricalDividend> getResult() throws IOException {
        List<HistoricalDividend> result = new ArrayList<HistoricalDividend>();
        if (this.from.after(this.to)) {
            log.warn("Unable to retrieve historical dividends. From-date should not be after to-date. From: "
                    + this.from.getTime() + ", to: " + this.to.getTime());
            return result;
        }
        Map<String, String> params = configureParams();
        BufferedReader br = RequestUtils.getBufferedReaderByParams(params, symbol);
        br.readLine(); // skip the first line
        // Parse CSV
        for (String line = br.readLine(); line != null; line = br.readLine()) {
            log.info("Parsing CSV line: " + Utils.unescape(line));
            HistoricalDividend dividend = this.parseCSVLine(line);
            result.add(dividend);
        }
        return result;
    }

    private Map<String, String> configureParams() throws IOException {
        Map<String, String> params = RequestUtils.configureParamsPeriodsAndInterval(from, to, DEFAULT_INTERVAL);
        // This will instruct Yahoo to return dividends
        params.put("events", "div");
        params.put("crumb", CrumbManager.getCrumb());
        return params;
    }

    private HistoricalDividend parseCSVLine(String line) {
        String[] data = line.split(YahooFinance.QUOTES_CSV_DELIMITER);
        return new HistoricalDividend(this.symbol,
                CalendarUtil.parseHistDate(data[0]),
                BigDecimalUtil.getBigDecimal(data[1])
        );
    }
}
