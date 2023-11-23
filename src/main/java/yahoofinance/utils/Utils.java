package yahoofinance.utils;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Map;
import java.util.Map.Entry;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author Stijn Strickx
 */
public class Utils {
    public static final Logger log = LoggerFactory.getLogger(Utils.class);

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

    public static String getURLParameters(Map<String, String> params) {
        StringBuilder sb = new StringBuilder();

        for (Entry<String, String> entry : params.entrySet()) {
            if (sb.length() > 0) {
                sb.append("&");
            }
            String key = entry.getKey();
            String value = entry.getValue();
            try {
                key = URLEncoder.encode(key, "UTF-8");
                value = URLEncoder.encode(value, "UTF-8");
            } catch (UnsupportedEncodingException ex) {
                log.error(ex.getMessage(), ex);
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
     * @param line the original CSV line
     * @return the stripped line
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

    public static boolean isParseable(String data) {
        return !(data == null || data.equals("N/A") || data.equals("-")
                || data.equals("") || data.equals("nan"));
    }
}
