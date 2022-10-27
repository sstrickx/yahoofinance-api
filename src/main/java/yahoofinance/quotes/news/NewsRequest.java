package yahoofinance.quotes.news;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import yahoofinance.Utils;
import yahoofinance.YahooFinance;
import yahoofinance.quotes.query1v7.QuotesRequest;
import yahoofinance.quotes.stock.StockNews;
import yahoofinance.util.RedirectableRequest;

import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class NewsRequest {

    private static final Logger log = LoggerFactory.getLogger(QuotesRequest.class);
    private static final ObjectMapper objectMapper = new ObjectMapper();

    private String symbols;

    public NewsRequest(String symbols) {
        this.symbols = symbols;
    }

    public StockNews parseJson(JsonNode node) {
        StockNews news = new StockNews(symbols);
        news.setUuid(node.get("uuid").asText());
        news.setTitle(node.get("title").asText());
        news.setPublisher(node.get("publisher").asText());
        news.setLink(node.get("link").asText());
        news.setProviderPublishTime(node.get("providerPublishTime").asLong());
        news.setType(node.get("type").asText());

        if (node.get("thumbnail") != null) {
            node.get("thumbnail").get("resolutions").forEach(resolution -> {
                StockNews.Thumbnail.Resolution res = new StockNews.Thumbnail.Resolution();
                res.setUrl(resolution.get("url").asText());
                res.setWidth(resolution.get("width").asLong());
                res.setHeight(resolution.get("height").asLong());
                res.setTag(resolution.get("tag").asText());
                news.getThumbnail().getResolutions().add(res);
            });
        }

        if (node.get("relatedTickers") != null) {
            node.get("relatedTickers").forEach(ticker -> news.getRelatedTickers().add(ticker.asText()));
        }

        return news;
    }

    public StockNews getSingleResult() throws IOException {
        return this.getResult().get(0);
    }

    public List<StockNews> getResult() throws IOException {
        List<StockNews> result = new ArrayList<StockNews>();

        Map<String, String> params = new LinkedHashMap<String, String>();
        params.put("q", symbols);

        String url = YahooFinance.HISTQUOTES_NEWS_URL + "?" + Utils.getURLParameters(params);

        log.info("Sending request: {}", url);

        URL request = new URL(url);
        RedirectableRequest redirectableRequest = new RedirectableRequest(request, 5);
        redirectableRequest.setConnectTimeout(YahooFinance.CONNECTION_TIMEOUT);
        redirectableRequest.setReadTimeout(YahooFinance.CONNECTION_TIMEOUT);
        URLConnection connection = redirectableRequest.openConnection();

        InputStreamReader is = new InputStreamReader(connection.getInputStream());
        JsonNode node = objectMapper.readTree(is);
        if (node.has("news")) {
            node = node.get("news");
            for (int i = 0; i < node.size(); i++) {
                result.add(this.parseJson(node.get(i)));
            }
        } else {
            throw new IOException("Invalid response");
        }

        return result;
    }
}
