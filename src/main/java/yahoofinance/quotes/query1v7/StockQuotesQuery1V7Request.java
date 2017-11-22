package yahoofinance.quotes.query1v7;

import java.math.BigDecimal;
import java.util.TimeZone;

import com.fasterxml.jackson.databind.JsonNode;

import yahoofinance.Stock;
import yahoofinance.Utils;
import yahoofinance.exchanges.ExchangeTimeZone;
import yahoofinance.quotes.stock.StockDividend;
import yahoofinance.quotes.stock.StockQuote;
import yahoofinance.quotes.stock.StockStats;

/**
 *
 * @author Stijn Strickx
 */
public class StockQuotesQuery1V7Request extends QuotesRequest<Stock> {

	private static final BigDecimal ONE_HUNDRED = new BigDecimal(100);

	public StockQuotesQuery1V7Request(String symbols) {
		super(symbols);
	}

	@Override
	protected Stock parseJson(JsonNode node) {
		String symbol = node.get("symbol").asText();
		Stock stock = new Stock(symbol);

		if (node.has("longName")) {
			stock.setName(node.get("longName").asText());
		} else {
			stock.setName(this.getStringValue(node, "shortName"));
		}

		stock.setCurrency(this.getStringValue(node, "currency"));
		stock.setStockExchange(this.getStringValue(node, "fullExchangeName"));

		stock.setQuote(this.getQuote(node));
		stock.setStats(this.getStats(node));
		stock.setDividend(this.getDividend(node));

		return stock;
	}

	private String getStringValue(JsonNode node, String field) {
		if (node.has(field)) {
			return node.get(field).asText();
		}
		return null;
	}

	private StockQuote getQuote(JsonNode node) {
		String symbol = node.get("symbol").asText();
		StockQuote quote = new StockQuote(symbol);

		quote.setPrice(Utils.getBigDecimal(this.getStringValue(node, "regularMarketPrice")));
		// quote.setLastTradeSize(null);
		quote.setAsk(Utils.getBigDecimal(this.getStringValue(node, "ask")));
		quote.setAskSize(Utils.getLong(this.getStringValue(node, "askSize")));
		quote.setBid(Utils.getBigDecimal(this.getStringValue(node, "bid")));
		quote.setBidSize(Utils.getLong(this.getStringValue(node, "bidSize")));
		quote.setOpen(Utils.getBigDecimal(this.getStringValue(node, "regularMarketOpen")));
		quote.setPreviousClose(Utils.getBigDecimal(this.getStringValue(node, "regularMarketPreviousClose")));
		quote.setDayHigh(Utils.getBigDecimal(this.getStringValue(node, "regularMarketDayHigh")));
		quote.setDayLow(Utils.getBigDecimal(this.getStringValue(node, "regularMarketDayLow")));

		if (node.has("exchangeTimezoneName")) {
			quote.setTimeZone(TimeZone.getTimeZone(node.get("exchangeTimezoneName").asText()));
		} else {
			quote.setTimeZone(ExchangeTimeZone.getStockTimeZone(symbol));
		}

		if (node.has("regularMarketTime")) {
			quote.setLastTradeTime(Utils.unixToCalendar(node.get("regularMarketTime").asLong()));
		}

		quote.setYearHigh(Utils.getBigDecimal(this.getStringValue(node, "fiftyTwoWeekHigh")));
		quote.setYearLow(Utils.getBigDecimal(this.getStringValue(node, "fiftyTwoWeekLow")));
		quote.setPriceAvg50(Utils.getBigDecimal(this.getStringValue(node, "fiftyDayAverage")));
		quote.setPriceAvg200(Utils.getBigDecimal(this.getStringValue(node, "twoHundredDayAverage")));

		quote.setVolume(Utils.getLong(this.getStringValue(node, "regularMarketVolume")));
		quote.setAvgVolume(Utils.getLong(this.getStringValue(node, "averageDailyVolume3Month")));

		return quote;
	}

	private StockStats getStats(JsonNode node) {
		String symbol = this.getStringValue(node, "symbol");
		StockStats stats = new StockStats(symbol);

		stats.setMarketCap(Utils.getBigDecimal(this.getStringValue(node, "marketCap")));
		// stats.setSharesFloat(Utils.getLong(getStringValue(node,"sharesOutstanding")));
		stats.setSharesOutstanding(Utils.getLong(this.getStringValue(node, "sharesOutstanding")));
		// stats.setSharesOwned(Utils.getLong(getStringValue(node,"symbol")));

		stats.setEps(Utils.getBigDecimal(this.getStringValue(node, "epsTrailingTwelveMonths")));
		stats.setPe(Utils.getBigDecimal(this.getStringValue(node, "trailingPE")));
		// stats.setPeg(Utils.getBigDecimal(getStringValue(node,"symbol")));

		stats.setEpsEstimateCurrentYear(Utils.getBigDecimal(this.getStringValue(node, "epsForward")));
		// stats.setEpsEstimateNextQuarter(Utils.getBigDecimal(getStringValue(node,"symbol")));
		// stats.setEpsEstimateNextYear(Utils.getBigDecimal(getStringValue(node,"symbol")));

		stats.setPriceBook(Utils.getBigDecimal(this.getStringValue(node, "priceToBook")));
		// stats.setPriceSales(Utils.getBigDecimal(getStringValue(node,"symbol")));
		stats.setBookValuePerShare(Utils.getBigDecimal(this.getStringValue(node, "bookValue")));

		// stats.setOneYearTargetPrice(Utils.getBigDecimal(getStringValue(node,"symbol")));
		// stats.setEBITDA(Utils.getBigDecimal(getStringValue(node,"symbol")));
		// stats.setRevenue(Utils.getBigDecimal(getStringValue(node,"symbol")));

		// stats.setShortRatio(Utils.getBigDecimal(getStringValue(node,"symbol")));

		if (node.has("earningsTimestamp")) {
			stats.setEarningsAnnouncement(Utils.unixToCalendar(node.get("earningsTimestamp").asLong()));
		}

		return stats;
	}

	private StockDividend getDividend(JsonNode node) {
		String symbol = this.getStringValue(node, "symbol");
		StockDividend dividend = new StockDividend(symbol);

		if (node.has("dividendDate")) {
			long dividendTimestamp = node.get("dividendDate").asLong();
			dividend.setPayDate(Utils.unixToCalendar(dividendTimestamp));
			// dividend.setExDate(Utils.unixToCalendar(node.get("dividendDate").asLong()));
		}
		if (node.has("trailingAnnualDividendRate")) {
			dividend.setAnnualYield(Utils.getBigDecimal(this.getStringValue(node, "trailingAnnualDividendRate")));
		}
		if (node.has("trailingAnnualDividendYield")) {
			BigDecimal yield = Utils.getBigDecimal(this.getStringValue(node, "trailingAnnualDividendYield"));
			if (yield != null) {
				dividend.setAnnualYieldPercent(yield.multiply(ONE_HUNDRED));
			}
		}

		return dividend;
	}

}
