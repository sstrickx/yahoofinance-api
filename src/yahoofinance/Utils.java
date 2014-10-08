package yahoofinance;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Map;
import java.util.Map.Entry;
import java.util.TimeZone;
import java.util.logging.Level;

/**
 *
 * @author Stijn Strickx
 */
public class Utils {

    public static String join(String[] data, String d) {
        if (data.length == 0) {
            return "";
        }
        StringBuilder sb = new StringBuilder();
        int i;

        for (i = 0; i < (data.length - 1); i++) {
            sb.append(data[i]).append(d);
        }
        return sb.append(data[i]).toString();
    }
    
    private static String cleanNumberString(String data) {
        return Utils.join(data.trim().split(","), "");
    }
    
    private static boolean isParseable(String data) {
        return !(data == null || data.equals("N/A") || data.equals("-") || data.equals(""));
    }
    
    public static double getDouble(String data) {
        double result = 0.00;
        if (!Utils.isParseable(data)) {
            return result;
        }
        try {
            data = Utils.cleanNumberString(data);
            char lastChar = data.charAt(data.length() - 1);
            int multiplier = 1;
            switch (lastChar) {
                case 'B':
                    data = data.substring(0, data.length() - 1);
                    multiplier = 1000000000;
                    break;
                case 'M':
                    data = data.substring(0, data.length() - 1);
                    multiplier = 1000000;
                    break;
            }
            result = Double.parseDouble(data) * multiplier;
        } catch (NumberFormatException e) {
            YahooFinance.logger.log(Level.INFO, "Failed to parse: " + data, e);
        }
        return result;
    }

    public static int getInt(String data) {
        int result = 0;
        if (!Utils.isParseable(data)) {
            return result;
        }
        try {
            data = Utils.cleanNumberString(data);
            result = Integer.parseInt(data);
        } catch (NumberFormatException e) {
            YahooFinance.logger.log(Level.INFO, "Failed to parse: " + data, e);
        }
        return result;
    }
    
    public static long getLong(String data) {
        long result = 0;
        if (!Utils.isParseable(data)) {
            return result;
        }
        try {
            data  = Utils.cleanNumberString(data);
            result = Long.parseLong(data);
        } catch (NumberFormatException e) {
            YahooFinance.logger.log(Level.INFO, "Failed to parse: " + data, e);
        }
        return result;
    }
    
    public static double getPercent(double numerator, double denominator) {
        if(denominator == 0) {
            return 0;
        }
        return numerator / denominator;
    }

    private static String getDividendDateFormat(String date) {
        if(date.matches("[0-9][0-9]-...-[0-9][0-9]")) {
            return "dd-MMM-yy";
        } else {
            return "MMM d";
        }
    }
    /**
     * Used to parse the dividend dates.
     * Returns null if the date cannot be parsed.
     * 
     * @param date      String received that represents the date
     * @return          Calendar object representing the parsed date
     */
    public static Calendar parseDividendDate(String date) {
        if (!Utils.isParseable(date)) {
            return null;
        }
        SimpleDateFormat format = new SimpleDateFormat(Utils.getDividendDateFormat(date));
        format.setTimeZone(TimeZone.getTimeZone(YahooFinance.TIMEZONE));
        try {
            Calendar today = Calendar.getInstance(TimeZone.getTimeZone(YahooFinance.TIMEZONE));
            Calendar parsedDate = Calendar.getInstance(TimeZone.getTimeZone(YahooFinance.TIMEZONE));
            parsedDate.setTime(format.parse(date));
            
            if(parsedDate.get(Calendar.YEAR) == 1970) {
                parsedDate.set(Calendar.YEAR, today.get(Calendar.YEAR));
            }
            
            return parsedDate;
        } catch(ParseException ex) {
            YahooFinance.logger.log(Level.SEVERE, ex.getMessage(), ex);
            return null;
        }
    }
    
    /**
     * Used to parse the last trade date / time.
     * Returns null if the date / time cannot be parsed.
     * 
     * @param date      String received that represents the date
     * @param time      String received that represents the time
     * @return          Calendar object with the parsed datetime
     */
    public static Calendar parseDateTime(String date, String time) {
        String datetime = date + " " + time;
        SimpleDateFormat format = new SimpleDateFormat("M/d/yyyy h:mma");
        format.setTimeZone(TimeZone.getTimeZone(YahooFinance.TIMEZONE));
        try {
            if(Utils.isParseable(date) && Utils.isParseable(time)) {
                Calendar c = Calendar.getInstance();
                c.setTime(format.parse(datetime));
                return c;
            }
        } catch (ParseException ex) {
            YahooFinance.logger.log(Level.SEVERE, ex.getMessage(), ex);
        }
        return null;
    }
    
    public static Calendar parseHistDate(String date) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        format.setTimeZone(TimeZone.getTimeZone(YahooFinance.TIMEZONE));
        try {
            if(Utils.isParseable(date)) {
                Calendar c = Calendar.getInstance();
                c.setTime(format.parse(date));
                return c;
            }
        } catch (ParseException ex) {
            YahooFinance.logger.log(Level.SEVERE, ex.getMessage(), ex);
        }
        return null;
    }
    
    public static String getURLParameters(Map<String, String> params) {
        StringBuilder sb = new StringBuilder();
        
        for(Entry<String, String> entry : params.entrySet()) {
            if (sb.length() > 0) {
                sb.append("&");
            }
            String key = entry.getKey();
            String value = entry.getValue();
            try {
                key = URLEncoder.encode(key, "UTF-8");
                value = URLEncoder.encode(value, "UTF-8");
            } catch (UnsupportedEncodingException ex) {
                YahooFinance.logger.log(Level.SEVERE, ex.getMessage(), ex);
                // Still try to continue with unencoded values
            }
            sb.append(String.format("%s=%s", key, value));
        }
        return sb.toString();     
    }
    
    /**
     * Strips the unwanted chars from a line returned in the CSV
     * Used for parsing the FX CSV lines
     * 
     * @param line      the original CSV line
     * @return          the stripped line
     */
    public static String stripOverhead(String line) {
        return line.replaceAll("\"", "");
    }
    
    public static String unescape(String data) {
        StringBuilder buffer = new StringBuilder(data.length());
        for (int i = 0; i < data.length(); i++) {
            if ((int) data.charAt(i) > 256) {
                buffer.append("\\u").append(Integer.toHexString((int) data.charAt(i)));
            } else {
                if (data.charAt(i) == '\n') {
                    buffer.append("\\n");
                } else if (data.charAt(i) == '\t') {
                    buffer.append("\\t");
                } else if (data.charAt(i) == '\r') {
                    buffer.append("\\r");
                } else if (data.charAt(i) == '\b') {
                    buffer.append("\\b");
                } else if (data.charAt(i) == '\f') {
                    buffer.append("\\f");
                } else if (data.charAt(i) == '\'') {
                    buffer.append("\\'");
                } else if (data.charAt(i) == '\"') {
                    buffer.append("\\\"");
                } else if (data.charAt(i) == '\\') {
                    buffer.append("\\\\");
                } else {
                    buffer.append(data.charAt(i));
                }
            }
        }
        return buffer.toString();
    }

}
