package yahoofinance.utils;

import yahoofinance.YahooFinance;
import yahoofinance.histquotes.HistoricalQuote;
import yahoofinance.histquotes2.CrumbManager;
import yahoofinance.histquotes2.QueryInterval;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.util.Calendar;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

import static yahoofinance.utils.Utils.log;

public class RequestUtils {
    public static URLConnection getUrlConnection(String url) throws IOException {
        URL request = new URL(url);
        RedirectableRequest redirectableRequest = new RedirectableRequest(request, 5);
        redirectableRequest.setConnectTimeout(YahooFinance.CONNECTION_TIMEOUT);
        redirectableRequest.setReadTimeout(YahooFinance.CONNECTION_TIMEOUT);
        Map<String, String> requestProperties = new HashMap<String, String>();
        requestProperties.put("Cookie", CrumbManager.getCookie());
        return redirectableRequest.openConnection(requestProperties);
    }

    public static BufferedReader getBufferedReader(String url, InputStream inputStream) {
        log.info("Sending request: " + url);
        InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
        return new BufferedReader(inputStreamReader);
    }

    public static HistoricalQuote parseCSVLine(String line, String symbol) {
        String[] data = line.split(YahooFinance.QUOTES_CSV_DELIMITER);
        return new HistoricalQuote(symbol, CalendarUtil.parseHistDate(data[0]))
                .setOpen(BigDecimalUtil.getBigDecimal(data[1]))
                .setLow(BigDecimalUtil.getBigDecimal(data[3]))
                .setHigh(BigDecimalUtil.getBigDecimal(data[2]))
                .setClose(BigDecimalUtil.getBigDecimal(data[4]))
                .setAdjClose(BigDecimalUtil.getBigDecimal(data[6]))
                .setVolume(PrimitiveTypesConvertUtils.getLong(data[5]));
    }

    public static Map<String, String> configureParamsPeriodsAndInterval(Calendar from, Calendar to, QueryInterval defaultInterval) throws IOException {
        Map<String, String> params = new LinkedHashMap<>();
        params.put("period1", String.valueOf(from.getTimeInMillis() / 1000));
        params.put("period2", String.valueOf(to.getTimeInMillis() / 1000));
        params.put("interval", defaultInterval.getTag());
        return params;
    }

    public static BufferedReader getBufferedReaderByParams(Map<String, String> params, String symbol) throws IOException {
        String url = YahooFinance.HISTQUOTES2_BASE_URL + URLEncoder.encode(symbol, "UTF-8") + "?" + Utils.getURLParameters(params);
        // Get CSV from Yahoo
        log.info("Sending request: " + url);
        URLConnection connection = RequestUtils.getUrlConnection(url);

        return RequestUtils.getBufferedReader(url, connection.getInputStream());
    }
}
