package yahoofinance.utils;

import java.math.BigDecimal;
import java.math.RoundingMode;

public final class BigDecimalUtil {
    public static final BigDecimal HUNDRED = new BigDecimal(100);
    public static final BigDecimal THOUSAND = new BigDecimal(1000);
    public static final BigDecimal MILLION = new BigDecimal(1000000);
    public static final BigDecimal BILLION = new BigDecimal(1000000000);

    public static BigDecimal getPercent(BigDecimal numerator, BigDecimal denominator) {
        if (denominator == null || numerator == null || denominator.compareTo(BigDecimal.ZERO) == 0) {
            return BigDecimal.ZERO;
        }
        return numerator.divide(denominator, 4, RoundingMode.HALF_EVEN)
                .multiply(HUNDRED).setScale(2, RoundingMode.HALF_EVEN);
    }

    public static BigDecimal getBigDecimal(String dataMain, String dataSub) {
        BigDecimal main = getBigDecimal(dataMain);
        BigDecimal sub = getBigDecimal(dataSub);
        if(main == null || main.compareTo(BigDecimal.ZERO) == 0) {
            return sub;
        }
        return main;
    }

    public static BigDecimal getBigDecimal(String data) {
        BigDecimal result = null;
        if (!Utils.isParseable(data)) {
            return result;
        }
        try {
            data = PrimitiveTypesConvertUtils.cleanNumberString(data);
            char lastChar = data.charAt(data.length() - 1);
            BigDecimal multiplier = BigDecimal.ONE;
            switch (lastChar) {
                case 'B':
                    data = data.substring(0, data.length() - 1);
                    multiplier = BILLION;
                    break;
                case 'M':
                    data = data.substring(0, data.length() - 1);
                    multiplier = MILLION;
                    break;
                case 'K':
                    data = data.substring(0, data.length() - 1);
                    multiplier = THOUSAND;
                    break;
            }
            result = new BigDecimal(data).multiply(multiplier);
        } catch (NumberFormatException e) {
            Utils.log.warn("Failed to parse: " + data);
            Utils.log.debug("Failed to parse: " + data, e);
        }
        return result;
    }
}
