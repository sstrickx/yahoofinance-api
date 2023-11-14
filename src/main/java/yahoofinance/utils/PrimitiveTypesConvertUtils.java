package yahoofinance.utils;

public final class PrimitiveTypesConvertUtils {
    public static Long getLong(String data) {
        Long result = null;
        if (!Utils.isParseable(data)) {
            return result;
        }
        try {
            data = cleanNumberString(data);
            result = Long.parseLong(data);
        } catch (NumberFormatException e) {
            Utils.log.warn("Failed to parse: " + data);
            Utils.log.debug("Failed to parse: " + data, e);
        }
        return result;
    }

    public static double getDouble(String data) {
        double result = Double.NaN;
        if (!Utils.isParseable(data)) {
            return result;
        }
        try {
            data = cleanNumberString(data);
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
                case 'K':
                    data = data.substring(0, data.length() - 1);
                    multiplier = 1000;
                    break;
            }
            result = Double.parseDouble(data) * multiplier;
        } catch (NumberFormatException e) {
            Utils.log.warn("Failed to parse: " + data);
            Utils.log.debug("Failed to parse: " + data, e);
        }
        return result;
    }

    public static Integer getInt(String data) {
        Integer result = null;
        if (!Utils.isParseable(data)) {
            return result;
        }
        try {
            data = cleanNumberString(data);
            result = Integer.parseInt(data);
        } catch (NumberFormatException e) {
            Utils.log.warn("Failed to parse: " + data);
            Utils.log.debug("Failed to parse: " + data, e);
        }
        return result;
    }

    public static String cleanNumberString(String data) {
        return Utils.join(data.trim().split(","), "");
    }

    public static String getString(String data) {
        if(!Utils.isParseable(data)) {
            return null;
        }
        return data;
    }
}
