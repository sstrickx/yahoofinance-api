package yahoofinance.utils;

import yahoofinance.YahooFinance;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;
import java.util.TimeZone;

public class CalendarUtil {
    /**
     * Used to parse the dividend dates. Returns null if the date cannot be
     * parsed.
     *
     * @param date String received that represents the date
     * @return Calendar object representing the parsed date
     */
    public static Calendar parseDividendDate(String date) {
        if (!Utils.isParseable(date)) {
            return null;
        }
        date = date.trim();
        SimpleDateFormat format = new SimpleDateFormat(getDividendDateFormat(date), Locale.US);
        format.setTimeZone(TimeZone.getTimeZone(YahooFinance.TIMEZONE));
        try {
            return getParsedDate(date, format);
        } catch (ParseException ex) {
            Utils.log.warn("Failed to parse dividend date: " + date);
            Utils.log.debug("Failed to parse dividend date: " + date, ex);
            return null;
        }
    }

    /**
     * Used to parse the last trade date / time. Returns null if the date / time
     * cannot be parsed.
     *
     * @param date String received that represents the date
     * @param time String received that represents the time
     * @param timeZone time zone to use for parsing the date time
     * @return Calendar object with the parsed datetime
     */
    public static Calendar parseDateTime(String date, String time, TimeZone timeZone) {
        String datetime = date + " " + time;
        SimpleDateFormat format = new SimpleDateFormat("M/d/yyyy h:mma", Locale.US);

        format.setTimeZone(timeZone);
        try {
            if (Utils.isParseable(date) && Utils.isParseable(time)) {
                Calendar c = Calendar.getInstance();
                c.setTime(format.parse(datetime));
                return c;
            }
        } catch (ParseException ex) {
            Utils.log.warn("Failed to parse datetime: " + datetime);
            Utils.log.debug("Failed to parse datetime: " + datetime, ex);
        }
        return null;
    }

    public static Calendar parseHistDate(String date) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd", Locale.US);
        try {
            if (Utils.isParseable(date)) {
                Calendar c = Calendar.getInstance();
                c.setTime(format.parse(date));
                return c;
            }
        } catch (ParseException ex) {
            Utils.log.warn("Failed to parse hist date: " + date);
            Utils.log.debug("Failed to parse hist date: " + date, ex);
        }
        return null;
    }

    public static Calendar unixToCalendar(long timestamp) {
        Utils.log.debug("unixToCalendar " + timestamp);
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(timestamp * 1000);
        return calendar;
    }

    private static String getDividendDateFormat(String date) {
        if (date.matches("[0-9][0-9]-...-[0-9][0-9]")) {
            return "dd-MMM-yy";
        } else if (date.matches("[0-9]-...-[0-9][0-9]")) {
            return "d-MMM-yy";
        } else if (date.matches("...[ ]+[0-9]+")) {
            return "MMM d";
        } else {
            return "M/d/yy";
        }
    }

    private static Calendar getParsedDate(String date, SimpleDateFormat format) throws ParseException {
        Calendar today = Calendar.getInstance(TimeZone.getTimeZone(YahooFinance.TIMEZONE));
        Calendar parsedDate = Calendar.getInstance(TimeZone.getTimeZone(YahooFinance.TIMEZONE));
        parsedDate.setTime(format.parse(date));

        if (parsedDate.get(Calendar.YEAR) == 1970) {
            //                // Not really clear which year the dividend date is... making a reasonable guess.
            int monthDiff = parsedDate.get(Calendar.MONTH) - today.get(Calendar.MONTH);
            int year = today.get(Calendar.YEAR);
            if (monthDiff > 6) {
                year -= 1;
            } else if (monthDiff < -6) {
                year += 1;
            }
            parsedDate.set(Calendar.YEAR, year);
        }

        return parsedDate;
    }
}
