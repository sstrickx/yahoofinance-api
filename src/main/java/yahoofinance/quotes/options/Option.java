package yahoofinance.quotes.options;

import java.time.LocalDate;
import java.time.ZonedDateTime;
import java.util.StringJoiner;

public class Option {

    private final String contractSymbol;
    private final double strike;
    private final String currency;
    private final double lastPrice;
    private final double change;
    private final double percentChange;
    private final int volume;
    private final int openInterest;
    private final double bid;
    private final double ask;
    private final String contractSize;
    private final LocalDate expiration;
    private final ZonedDateTime lastTradeDate;
    private final double impliedVolatility;
    private final boolean inTheMoney;

    private Option(OptionBuilder optionBuilder) {
        this.currency = optionBuilder.currency;
        this.bid = optionBuilder.bid;
        this.openInterest = optionBuilder.openInterest;
        this.expiration = optionBuilder.expiration;
        this.volume = optionBuilder.volume;
        this.change = optionBuilder.change;
        this.strike = optionBuilder.strike;
        this.lastTradeDate = optionBuilder.lastTradeDate;
        this.percentChange = optionBuilder.percentChange;
        this.contractSize = optionBuilder.contractSize;
        this.impliedVolatility = optionBuilder.impliedVolatility;
        this.lastPrice = optionBuilder.lastPrice;
        this.contractSymbol = optionBuilder.contractSymbol;
        this.ask = optionBuilder.ask;
        this.inTheMoney = optionBuilder.inTheMoney;
    }

    public String getContractSymbol() {
        return contractSymbol;
    }

    public double getStrike() {
        return strike;
    }

    public String getCurrency() {
        return currency;
    }

    public double getLastPrice() {
        return lastPrice;
    }

    public double getChange() {
        return change;
    }

    public double getPercentChange() {
        return percentChange;
    }

    public int getVolume() {
        return volume;
    }

    public int getOpenInterest() {
        return openInterest;
    }

    public double getBid() {
        return bid;
    }

    public double getAsk() {
        return ask;
    }

    public String getContractSize() {
        return contractSize;
    }

    public LocalDate getExpiration() {
        return expiration;
    }

    public ZonedDateTime getLastTradeDate() {
        return lastTradeDate;
    }

    public double getImpliedVolatility() {
        return impliedVolatility;
    }

    public boolean isInTheMoney() {
        return inTheMoney;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Option option = (Option) o;

        if (Double.compare(option.strike, strike) != 0) return false;
        if (Double.compare(option.lastPrice, lastPrice) != 0) return false;
        if (Double.compare(option.change, change) != 0) return false;
        if (Double.compare(option.percentChange, percentChange) != 0) return false;
        if (volume != option.volume) return false;
        if (openInterest != option.openInterest) return false;
        if (Double.compare(option.bid, bid) != 0) return false;
        if (Double.compare(option.ask, ask) != 0) return false;
        if (Double.compare(option.impliedVolatility, impliedVolatility) != 0) return false;
        if (inTheMoney != option.inTheMoney) return false;
        if (!contractSymbol.equals(option.contractSymbol)) return false;
        if (!currency.equals(option.currency)) return false;
        if (!contractSize.equals(option.contractSize)) return false;
        if (!expiration.equals(option.expiration)) return false;
        return lastTradeDate.equals(option.lastTradeDate);
    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = contractSymbol.hashCode();
        temp = Double.doubleToLongBits(strike);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        result = 31 * result + currency.hashCode();
        temp = Double.doubleToLongBits(lastPrice);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(change);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(percentChange);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        result = 31 * result + volume;
        result = 31 * result + openInterest;
        temp = Double.doubleToLongBits(bid);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(ask);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        result = 31 * result + contractSize.hashCode();
        result = 31 * result + expiration.hashCode();
        result = 31 * result + lastTradeDate.hashCode();
        temp = Double.doubleToLongBits(impliedVolatility);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        result = 31 * result + (inTheMoney ? 1 : 0);
        return result;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", Option.class.getSimpleName() + "[", "]")
                .add("contractSymbol='" + contractSymbol + "'")
                .add("strike=" + strike)
                .add("currency='" + currency + "'")
                .add("lastPrice=" + lastPrice)
                .add("change=" + change)
                .add("percentChange=" + percentChange)
                .add("volume=" + volume)
                .add("openInterest=" + openInterest)
                .add("bid=" + bid)
                .add("ask=" + ask)
                .add("contractSize='" + contractSize + "'")
                .add("expiration=" + expiration)
                .add("lastTradeDate=" + lastTradeDate)
                .add("impliedVolatility=" + impliedVolatility)
                .add("inTheMoney=" + inTheMoney)
                .toString();
    }

    public static final class OptionBuilder {
        private String contractSymbol;
        private double strike;
        private String currency;
        private double lastPrice;
        private double change;
        private double percentChange;
        private int volume;
        private int openInterest;
        private double bid;
        private double ask;
        private String contractSize;
        private LocalDate expiration;
        private ZonedDateTime lastTradeDate;
        private double impliedVolatility;
        private boolean inTheMoney;

        private OptionBuilder() {
        }

        public static OptionBuilder anOption() {
            return new OptionBuilder();
        }

        public OptionBuilder withContractSymbol(String contractSymbol) {
            this.contractSymbol = contractSymbol;
            return this;
        }

        public OptionBuilder withStrike(double strike) {
            this.strike = strike;
            return this;
        }

        public OptionBuilder withCurrency(String currency) {
            this.currency = currency;
            return this;
        }

        public OptionBuilder withLastPrice(double lastPrice) {
            this.lastPrice = lastPrice;
            return this;
        }

        public OptionBuilder withChange(double change) {
            this.change = change;
            return this;
        }

        public OptionBuilder withPercentChange(double percentChange) {
            this.percentChange = percentChange;
            return this;
        }

        public OptionBuilder withVolume(int volume) {
            this.volume = volume;
            return this;
        }

        public OptionBuilder withOpenInterest(int openInterest) {
            this.openInterest = openInterest;
            return this;
        }

        public OptionBuilder withBid(double bid) {
            this.bid = bid;
            return this;
        }

        public OptionBuilder withAsk(double ask) {
            this.ask = ask;
            return this;
        }

        public OptionBuilder withContractSize(String contractSize) {
            this.contractSize = contractSize;
            return this;
        }

        public OptionBuilder withExpiration(LocalDate expiration) {
            this.expiration = expiration;
            return this;
        }

        public OptionBuilder withLastTradeDate(ZonedDateTime lastTradeDate) {
            this.lastTradeDate = lastTradeDate;
            return this;
        }

        public OptionBuilder withImpliedVolatility(double impliedVolatility) {
            this.impliedVolatility = impliedVolatility;
            return this;
        }

        public OptionBuilder withInTheMoney(boolean inTheMoney) {
            this.inTheMoney = inTheMoney;
            return this;
        }

        public Option build() {
            return new Option(this);
        }
    }
}
