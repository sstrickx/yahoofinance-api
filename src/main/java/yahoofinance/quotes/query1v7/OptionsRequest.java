package yahoofinance.quotes.query1v7;

import com.fasterxml.jackson.databind.JsonNode;
import yahoofinance.Stock;
import yahoofinance.YahooFinance;
import yahoofinance.quotes.options.Option;
import yahoofinance.quotes.options.OptionsChain;
import yahoofinance.quotes.options.OptionsResponse;

import java.io.IOException;
import java.time.Instant;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class OptionsRequest extends YahooRequest {

    private final String symbol;

    public OptionsRequest(String symbol) {
        this.symbol = symbol;
    }

    /**
     * Sends the request to Yahoo Finance and parses the result
     *
     * @return List of parsed objects resulting from the Yahoo Finance request
     * @throws IOException when there's a connection problem or the request is incorrect
     */
    public OptionsResponse getResult() throws IOException {
        String mainUrl = YahooFinance.QUOTES_OPTIONS_BASE_URL + "/" + symbol;
        JsonNode mainRequestNode = super.getResult(mainUrl).get("optionChain").get("result").get(0);
        List<String> dates = parseDates(mainRequestNode);
        final Stock stockData = new StockQuotesQuery1V7Request(symbol).parseJson(mainRequestNode.get("quote"));

        List<OptionsChain> optionsChainList = new ArrayList<>();
        for (String date : dates) {
            String url = YahooFinance.QUOTES_OPTIONS_BASE_URL + "/" + symbol + "?date=" + date;
            JsonNode node = super.getResult(url);

            if(node.has("optionChain") && node.get("optionChain").has("result")) {
                node = node.get("optionChain").get("result").get(0);
                final OptionsChain optionsChain = parseJson(node);
                optionsChainList.add(optionsChain);
            } else {
                throw new IOException("Invalid response");
            }
        }

        final List<Option> calls = optionsChainList.stream().flatMap(x -> x.getCalls().stream()).collect(Collectors.toList());
        final List<Option> puts = optionsChainList.stream().flatMap(x -> x.getPuts().stream()).collect(Collectors.toList());
        return new OptionsResponse(stockData, new OptionsChain(calls, puts));
    }

    private List<String> parseDates(JsonNode node) {
        final JsonNode expirationDates = node.get("expirationDates");
        List<String> dates = new ArrayList<>();
        for (int i = 0; i < expirationDates.size(); i++) {
            dates.add(expirationDates.get(i).asText());
        }
        return Collections.unmodifiableList(dates);
    }

    private OptionsChain parseJson(JsonNode node) {
        return parseOptionsChain(node.get("options").get(0));
    }

    private OptionsChain parseOptionsChain(JsonNode node) {
        return new OptionsChain(parseOptionsList(node, "calls"), parseOptionsList(node, "puts"));
    }

    private List<Option> parseOptionsList(JsonNode node, String type) {
        ArrayList<Option> optionList = new ArrayList<>();
        final JsonNode callsNode = node.get(type);
        for (int i = 0; i < callsNode.size(); i++) {
            final Option option = parseOption(callsNode.get(i));
            optionList.add(option);
        }
        return Collections.unmodifiableList(optionList);
    }

    private Option parseOption(JsonNode node) {
        return Option.OptionBuilder.anOption()
                .withContractSymbol(node.get("contractSymbol").asText())
                .withStrike(node.get("strike").asDouble())
                .withCurrency(node.get("currency").asText())
                .withLastPrice(node.get("lastPrice").asDouble())
                .withChange(node.get("change").asDouble())
                .withPercentChange(node.get("percentChange").asDouble())
                .withVolume(node.get("volume").asInt())
                .withOpenInterest(node.get("openInterest").asInt())
                .withBid(node.get("bid").asDouble())
                .withAsk(node.get("ask").asDouble())
                .withContractSize(node.get("contractSize").asText())
                .withExpiration(Instant.ofEpochSecond(node.get("expiration").asLong()).atZone(ZoneId.of("UTC")).toLocalDate())
                .withLastTradeDate(Instant.ofEpochSecond(node.get("lastTradeDate").asLong()).atZone(ZoneId.of("America/New_York")))
                .withImpliedVolatility(node.get("impliedVolatility").asDouble())
                .withInTheMoney(node.get("inTheMoney").asBoolean())
                .build();
    }

}
