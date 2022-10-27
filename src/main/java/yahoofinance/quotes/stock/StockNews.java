package yahoofinance.quotes.stock;

import java.util.ArrayList;
import java.util.List;

public class StockNews {

    protected final String symbols;

    private String uuid;

    private String title;

    private String publisher;

    private String link;

    private Long providerPublishTime;

    private String type;

    private Thumbnail thumbnail;

    private List<String> relatedTickers;

    public static class Thumbnail {

        private List<Resolution> resolutions;

        public List<Resolution> getResolutions() {
            if (resolutions == null) {
                resolutions = new ArrayList<>();
            }
            return resolutions;
        }

        public static class Resolution {
            private String url;
            private Long width;
            private Long height;
            private String tag;

            public Resolution() {
            }

            public String getUrl() {
                return url;
            }

            public void setUrl(String url) {
                this.url = url;
            }

            public Long getWidth() {
                return width;
            }

            public void setWidth(Long width) {
                this.width = width;
            }

            public Long getHeight() {
                return height;
            }

            public void setHeight(Long height) {
                this.height = height;
            }

            public String getTag() {
                return tag;
            }

            public void setTag(String tag) {
                this.tag = tag;
            }
        }
    }

    public StockNews(String symbols) {
        this.symbols = symbols;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public void setProviderPublishTime(Long providerPublishTime) {
        this.providerPublishTime = providerPublishTime;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getSymbols() {
        return symbols;
    }

    public String getUuid() {
        return uuid;
    }

    public String getTitle() {
        return title;
    }

    public String getPublisher() {
        return publisher;
    }

    public String getLink() {
        return link;
    }

    public Long getProviderPublishTime() {
        return providerPublishTime;
    }

    public String getType() {
        return type;
    }

    public Thumbnail getThumbnail() {
        if (thumbnail == null) {
            thumbnail = new Thumbnail();
        }
        return thumbnail;
    }

    public List<String> getRelatedTickers() {
        if (relatedTickers == null) {
            relatedTickers = new ArrayList<>();
        }
        return relatedTickers;
    }

    @Override
    public String toString() {
        return "StockNews{" +
                "symbols='" + symbols + '\'' +
                ", uuid='" + uuid + '\'' +
                ", title='" + title + '\'' +
                ", publisher='" + publisher + '\'' +
                ", link='" + link + '\'' +
                ", providerPublishTime=" + providerPublishTime +
                ", type='" + type + '\'' +
                ", thumbnail=" + thumbnail +
                ", relatedTickers=" + relatedTickers +
                '}';
    }
}
