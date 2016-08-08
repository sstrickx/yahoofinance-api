package yahoofinance.mock;

import okhttp3.mockwebserver.Dispatcher;
import okhttp3.mockwebserver.MockResponse;
import okhttp3.mockwebserver.RecordedRequest;

import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Stijn Strickx
 */
public class YahooFinanceDispatcher extends Dispatcher {

    public static final Logger LOG = Logger.getLogger(YahooFinanceDispatcher.class.getName());

    private Map<String, ResponseResource> pathToResponseResource;

    public YahooFinanceDispatcher() {
        this.pathToResponseResource = new HashMap<String, ResponseResource>();
        this.pathToResponseResource.put(
                "/d/quotes.csv?s=AIR.PA&f=nsc4xab2sa5sbb3sb6sl1sk3sd1t1opghva2kjm3m4sj2sss1sj1sf6sr1qdyee7e9e8rr5p6p5b4s6j4t8s7&e=.csv",
                new ResponseResource("simpleQuoteRequest/AIR.PA.csv")
        );
        this.pathToResponseResource.put(
                "/d/quotes.csv?s=TSLA&f=nsc4xab2sa5sbb3sb6sl1sk3sd1t1opghva2kjm3m4sj2sss1sj1sf6sr1qdyee7e9e8rr5p6p5b4s6j4t8s7&e=.csv",
                new ResponseResource("simpleQuoteRequest/TSLA.csv")
        );
        this.pathToResponseResource.put(
                "/d/quotes.csv?s=INTC&f=nsc4xab2sa5sbb3sb6sl1sk3sd1t1opghva2kjm3m4sj2sss1sj1sf6sr1qdyee7e9e8rr5p6p5b4s6j4t8s7&e=.csv",
                new ResponseResource("simpleQuoteRequest/INTC.csv")
        );
    }

    @Override
    public MockResponse dispatch(RecordedRequest request) throws InterruptedException {
        LOG.log(Level.INFO, "Getting MockResponse for path: " + request.getPath());
        if(this.pathToResponseResource.containsKey(request.getPath())) {
            return this.pathToResponseResource.get(request.getPath()).get();
        } else {
            LOG.log(Level.WARNING, "Requested path not configured. Cannot provide MockResponse");
        }
        return null;
    }


}
