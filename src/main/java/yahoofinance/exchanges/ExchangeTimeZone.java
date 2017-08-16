
package yahoofinance.exchanges;

import java.util.HashMap;
import java.util.Map;
import java.util.TimeZone;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author Stijn Strickx
 */
public class ExchangeTimeZone {

    private static final Logger log = LoggerFactory.getLogger(ExchangeTimeZone.class);

    public static final Map<String, TimeZone> SUFFIX_TIMEZONES = new HashMap<String, TimeZone>();
    public static final Map<String, TimeZone> INDEX_TIMEZONES = new HashMap<String, TimeZone>();
    
    static {
        SUFFIX_TIMEZONES.put("", TimeZone.getTimeZone("America/New_York"));
        SUFFIX_TIMEZONES.put("CBT", TimeZone.getTimeZone("America/New_York"));
        SUFFIX_TIMEZONES.put("CME", TimeZone.getTimeZone("America/New_York"));
        SUFFIX_TIMEZONES.put("NYB", TimeZone.getTimeZone("America/New_York"));
        SUFFIX_TIMEZONES.put("CMX", TimeZone.getTimeZone("America/New_York"));
        SUFFIX_TIMEZONES.put("NYM", TimeZone.getTimeZone("America/New_York"));
        SUFFIX_TIMEZONES.put("OB", TimeZone.getTimeZone("America/New_York"));
        SUFFIX_TIMEZONES.put("PK", TimeZone.getTimeZone("America/New_York"));
        SUFFIX_TIMEZONES.put("BA", TimeZone.getTimeZone("America/Buenos_Aires"));
        SUFFIX_TIMEZONES.put("VI", TimeZone.getTimeZone("Europe/Vienna"));
        SUFFIX_TIMEZONES.put("AX", TimeZone.getTimeZone("Australia/ACT"));
        SUFFIX_TIMEZONES.put("SA", TimeZone.getTimeZone("America/Sao_Paulo"));
        SUFFIX_TIMEZONES.put("TO", TimeZone.getTimeZone("America/Toronto"));
        SUFFIX_TIMEZONES.put("V", TimeZone.getTimeZone("America/Toronto"));
        SUFFIX_TIMEZONES.put("SN", TimeZone.getTimeZone("America/Santiago"));
        SUFFIX_TIMEZONES.put("SS", TimeZone.getTimeZone("Asia/Shanghai"));
        SUFFIX_TIMEZONES.put("SZ", TimeZone.getTimeZone("Asia/Shanghai"));
        SUFFIX_TIMEZONES.put("CO", TimeZone.getTimeZone("Europe/Copenhagen"));
        SUFFIX_TIMEZONES.put("NX", TimeZone.getTimeZone("Europe/Paris"));
        SUFFIX_TIMEZONES.put("PA", TimeZone.getTimeZone("Europe/Paris"));
        SUFFIX_TIMEZONES.put("BE", TimeZone.getTimeZone("Europe/Berlin"));
        SUFFIX_TIMEZONES.put("BM", TimeZone.getTimeZone("Europe/Berlin"));
        SUFFIX_TIMEZONES.put("DU", TimeZone.getTimeZone("Europe/Berlin"));
        SUFFIX_TIMEZONES.put("F", TimeZone.getTimeZone("Europe/Berlin"));
        SUFFIX_TIMEZONES.put("HM", TimeZone.getTimeZone("Europe/Berlin"));
        SUFFIX_TIMEZONES.put("HA", TimeZone.getTimeZone("Europe/Berlin"));
        SUFFIX_TIMEZONES.put("MU", TimeZone.getTimeZone("Europe/Berlin"));
        SUFFIX_TIMEZONES.put("SG", TimeZone.getTimeZone("Europe/Berlin"));
        SUFFIX_TIMEZONES.put("DE", TimeZone.getTimeZone("Europe/Berlin"));
        SUFFIX_TIMEZONES.put("IR", TimeZone.getTimeZone("Europe/Dublin"));
        SUFFIX_TIMEZONES.put("BR", TimeZone.getTimeZone("Europe/Brussels"));
        SUFFIX_TIMEZONES.put("HE", TimeZone.getTimeZone("Europe/Helsinki"));
        SUFFIX_TIMEZONES.put("HK", TimeZone.getTimeZone("Asia/Hong_Kong"));
        SUFFIX_TIMEZONES.put("BO", TimeZone.getTimeZone("Asia/Kolkata"));
        SUFFIX_TIMEZONES.put("NS", TimeZone.getTimeZone("Asia/Kolkata"));
        SUFFIX_TIMEZONES.put("JK", TimeZone.getTimeZone("Asia/Jakarta"));
        SUFFIX_TIMEZONES.put("TA", TimeZone.getTimeZone("Asia/Tel_Aviv"));
        SUFFIX_TIMEZONES.put("MI", TimeZone.getTimeZone("Europe/Rome"));
        SUFFIX_TIMEZONES.put("MX", TimeZone.getTimeZone("America/Mexico_City"));
        SUFFIX_TIMEZONES.put("AS", TimeZone.getTimeZone("Europe/Amsterdam"));
        SUFFIX_TIMEZONES.put("NZ", TimeZone.getTimeZone("Pacific/Auckland"));
        SUFFIX_TIMEZONES.put("OL", TimeZone.getTimeZone("Europe/Oslo"));
        SUFFIX_TIMEZONES.put("SI", TimeZone.getTimeZone("Asia/Singapore"));
        SUFFIX_TIMEZONES.put("KS", TimeZone.getTimeZone("Asia/Seoul"));
        SUFFIX_TIMEZONES.put("KQ", TimeZone.getTimeZone("Asia/Seoul"));
        SUFFIX_TIMEZONES.put("KL", TimeZone.getTimeZone("Asia/Kuala_Lumpur"));
        SUFFIX_TIMEZONES.put("BC", TimeZone.getTimeZone("Europe/Madrid"));
        SUFFIX_TIMEZONES.put("BI", TimeZone.getTimeZone("Europe/Madrid"));
        SUFFIX_TIMEZONES.put("MF", TimeZone.getTimeZone("Europe/Madrid"));
        SUFFIX_TIMEZONES.put("MC", TimeZone.getTimeZone("Europe/Madrid"));
        SUFFIX_TIMEZONES.put("MA", TimeZone.getTimeZone("Europe/Madrid"));
        SUFFIX_TIMEZONES.put("ST", TimeZone.getTimeZone("Europe/Stockholm"));
        SUFFIX_TIMEZONES.put("SW", TimeZone.getTimeZone("Europe/Zurich"));
        SUFFIX_TIMEZONES.put("Z", TimeZone.getTimeZone("Europe/Zurich"));
        SUFFIX_TIMEZONES.put("VX", TimeZone.getTimeZone("Europe/Zurich"));
        SUFFIX_TIMEZONES.put("TWO", TimeZone.getTimeZone("Asia/Taipei"));
        SUFFIX_TIMEZONES.put("TW", TimeZone.getTimeZone("Asia/Taipei"));
        SUFFIX_TIMEZONES.put("L", TimeZone.getTimeZone("Europe/London"));
        SUFFIX_TIMEZONES.put("PR", TimeZone.getTimeZone("Europe/Prague"));
        SUFFIX_TIMEZONES.put("ME", TimeZone.getTimeZone("Europe/Moscow"));
        SUFFIX_TIMEZONES.put("AT", TimeZone.getTimeZone("Europe/Athens"));
        SUFFIX_TIMEZONES.put("LS", TimeZone.getTimeZone("Europe/Lisbon"));
        
        INDEX_TIMEZONES.put("^FTSE", TimeZone.getTimeZone("Europe/London"));
        INDEX_TIMEZONES.put("^GDAXI", TimeZone.getTimeZone("Europe/Berlin"));
        INDEX_TIMEZONES.put("^FCHI", TimeZone.getTimeZone("Europe/Paris"));
        INDEX_TIMEZONES.put("^IBEX", TimeZone.getTimeZone("Europe/Madrid"));
        INDEX_TIMEZONES.put("^OMX", TimeZone.getTimeZone("Europe/Stockholm"));
        INDEX_TIMEZONES.put("^OSEAX", TimeZone.getTimeZone("Europe/Oslo"));
        INDEX_TIMEZONES.put("ATX", TimeZone.getTimeZone("America/New_York"));
        INDEX_TIMEZONES.put("^SSMI", TimeZone.getTimeZone("Europe/Zurich"));
        INDEX_TIMEZONES.put("^BFX", TimeZone.getTimeZone("Europe/Brussels"));
        INDEX_TIMEZONES.put("^DJI", TimeZone.getTimeZone("America/New_York"));
        INDEX_TIMEZONES.put("^OEX", TimeZone.getTimeZone("America/New_York"));
        INDEX_TIMEZONES.put("^NDX", TimeZone.getTimeZone("America/New_York"));
        INDEX_TIMEZONES.put("^BATSK", TimeZone.getTimeZone("America/New_York"));
        INDEX_TIMEZONES.put("^N225", TimeZone.getTimeZone("Asia/Tokyo"));
        INDEX_TIMEZONES.put("^HSI", TimeZone.getTimeZone("Asia/Hong_Kong"));
        INDEX_TIMEZONES.put("^STI", TimeZone.getTimeZone("Asia/Singapore"));
        INDEX_TIMEZONES.put("^AORD", TimeZone.getTimeZone("Australia/ACT"));
        INDEX_TIMEZONES.put("^BSESN", TimeZone.getTimeZone("Asia/Kolkata"));
        INDEX_TIMEZONES.put("^JKSE", TimeZone.getTimeZone("Asia/Jakarta"));
        INDEX_TIMEZONES.put("^KLSE", TimeZone.getTimeZone("Asia/Kuala_Lumpur"));
        INDEX_TIMEZONES.put("^NZ50", TimeZone.getTimeZone("Pacific/Auckland"));
        INDEX_TIMEZONES.put("^NSEI", TimeZone.getTimeZone("Asia/Kolkata"));
        INDEX_TIMEZONES.put("^KS11", TimeZone.getTimeZone("Asia/Seoul"));
        INDEX_TIMEZONES.put("^TWII", TimeZone.getTimeZone("Asia/Taipei"));
        INDEX_TIMEZONES.put("^MERV", TimeZone.getTimeZone("America/Buenos_Aires"));
        INDEX_TIMEZONES.put("^BVSP", TimeZone.getTimeZone("America/Sao_Paulo"));
        INDEX_TIMEZONES.put("^GSPTSE", TimeZone.getTimeZone("America/Toronto"));
        INDEX_TIMEZONES.put("^MXX", TimeZone.getTimeZone("America/Mexico_City"));
        INDEX_TIMEZONES.put("^GSPC", TimeZone.getTimeZone("America/New_York"));
        INDEX_TIMEZONES.put("^CCSI", TimeZone.getTimeZone("Africa/Cairo"));
        INDEX_TIMEZONES.put("^TA100", TimeZone.getTimeZone("Asia/Tel_Aviv"));
        INDEX_TIMEZONES.put("^FTMC", TimeZone.getTimeZone("Europe/London"));
        INDEX_TIMEZONES.put("^FTLC", TimeZone.getTimeZone("Europe/London"));
        INDEX_TIMEZONES.put("^FTAI", TimeZone.getTimeZone("Europe/London"));
        INDEX_TIMEZONES.put("^FTAS", TimeZone.getTimeZone("Europe/London"));
        INDEX_TIMEZONES.put("^FTSC", TimeZone.getTimeZone("Europe/London"));
        INDEX_TIMEZONES.put("^FTT1X", TimeZone.getTimeZone("Europe/London"));
        INDEX_TIMEZONES.put("^MID", TimeZone.getTimeZone("America/New_York"));
        INDEX_TIMEZONES.put("^SP600", TimeZone.getTimeZone("America/New_York"));
        INDEX_TIMEZONES.put("^SPSUPX", TimeZone.getTimeZone("America/New_York"));
        INDEX_TIMEZONES.put("^VIX", TimeZone.getTimeZone("America/New_York"));
        INDEX_TIMEZONES.put("^DJC", TimeZone.getTimeZone("America/New_York"));
        INDEX_TIMEZONES.put("^XAU", TimeZone.getTimeZone("America/New_York"));
        INDEX_TIMEZONES.put("^DJT", TimeZone.getTimeZone("America/New_York"));
        INDEX_TIMEZONES.put("^DJU", TimeZone.getTimeZone("America/New_York"));
        INDEX_TIMEZONES.put("^DJA", TimeZone.getTimeZone("America/New_York"));
        INDEX_TIMEZONES.put("^DWCF", TimeZone.getTimeZone("America/New_York"));
        INDEX_TIMEZONES.put("^DJU", TimeZone.getTimeZone("America/New_York"));
        INDEX_TIMEZONES.put("^IXIC", TimeZone.getTimeZone("America/New_York"));
        INDEX_TIMEZONES.put("^BANK", TimeZone.getTimeZone("America/New_York"));
        INDEX_TIMEZONES.put("^NBI", TimeZone.getTimeZone("America/New_York"));
        INDEX_TIMEZONES.put("^IXCO", TimeZone.getTimeZone("America/New_York"));
        INDEX_TIMEZONES.put("^IXF", TimeZone.getTimeZone("America/New_York"));
        INDEX_TIMEZONES.put("^INDS", TimeZone.getTimeZone("America/New_York"));
        INDEX_TIMEZONES.put("^INSR", TimeZone.getTimeZone("America/New_York"));
        INDEX_TIMEZONES.put("^OFIN", TimeZone.getTimeZone("America/New_York"));
        INDEX_TIMEZONES.put("^IXTC", TimeZone.getTimeZone("America/New_York"));
        INDEX_TIMEZONES.put("^TRAN", TimeZone.getTimeZone("America/New_York"));
        INDEX_TIMEZONES.put("^NYA", TimeZone.getTimeZone("America/New_York"));
        INDEX_TIMEZONES.put("^NYE", TimeZone.getTimeZone("America/New_York"));
        INDEX_TIMEZONES.put("^NYK", TimeZone.getTimeZone("America/New_York"));
        INDEX_TIMEZONES.put("^NYP", TimeZone.getTimeZone("America/New_York"));
        INDEX_TIMEZONES.put("^NYY", TimeZone.getTimeZone("America/New_York"));
        INDEX_TIMEZONES.put("^NYI", TimeZone.getTimeZone("America/New_York"));
        INDEX_TIMEZONES.put("^NY", TimeZone.getTimeZone("America/New_York"));
        INDEX_TIMEZONES.put("^NYL", TimeZone.getTimeZone("America/New_York"));
        INDEX_TIMEZONES.put("^XMI", TimeZone.getTimeZone("America/New_York"));
        INDEX_TIMEZONES.put("^XAX", TimeZone.getTimeZone("America/New_York"));
        INDEX_TIMEZONES.put("^BATSK", TimeZone.getTimeZone("America/New_York"));
        INDEX_TIMEZONES.put("^RUI", TimeZone.getTimeZone("America/New_York"));
        INDEX_TIMEZONES.put("^RUT", TimeZone.getTimeZone("America/New_York"));
        INDEX_TIMEZONES.put("^RUA", TimeZone.getTimeZone("America/New_York"));
        INDEX_TIMEZONES.put("^SOX", TimeZone.getTimeZone("America/New_York"));
        INDEX_TIMEZONES.put("^BKX", TimeZone.getTimeZone("America/New_York"));
    }
    
    /**
     * Get the time zone for a specific exchange suffix
     * 
     * @param suffix suffix for the exchange in YahooFinance
     * @return time zone of the exchange
     */
    public static TimeZone get(String suffix) {
        if(SUFFIX_TIMEZONES.containsKey(suffix)) {
            return SUFFIX_TIMEZONES.get(suffix);
        }
        log.warn("Cannot find time zone for exchange suffix: '{}'. Using default: America/New_York", suffix);
        return SUFFIX_TIMEZONES.get("");
    }
    
    /**
     * Get the time zone for a specific stock or index.
     * For stocks, the exchange suffix is extracted from the stock symbol to retrieve the time zone.
     * 
     * @param symbol stock symbol in YahooFinance
     * @return time zone of the exchange on which this stock is traded
     */
    public static TimeZone getStockTimeZone(String symbol) {
        // First check if it's a known stock index
        if(INDEX_TIMEZONES.containsKey(symbol)) {
            return INDEX_TIMEZONES.get(symbol);
        }
        
        if(!symbol.contains(".")) {
            return ExchangeTimeZone.get("");
        }
        String[] split = symbol.split("\\.");
        return ExchangeTimeZone.get(split[split.length - 1]);
    }
}
