package yahoofinance.mock;

import com.google.common.base.Charsets;
import com.google.common.io.Resources;
import okhttp3.mockwebserver.Dispatcher;
import okhttp3.mockwebserver.MockResponse;
import okhttp3.mockwebserver.RecordedRequest;
import org.yaml.snakeyaml.Yaml;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
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
        this.loadRequests();
    }

    @Override
    public MockResponse dispatch(RecordedRequest request) throws InterruptedException {
        if(this.pathToResponseResource.containsKey(request.getPath())) {
            return this.pathToResponseResource.get(request.getPath()).get();
        } else {
            LOG.log(Level.WARNING, "Requested path not configured. Cannot provide MockResponse for " + request.getPath());
        }
        return null;
    }

    private void loadRequests() {
        Yaml yaml = new Yaml();
        Map<String, List<Map<String, Object>>> requests;
        try {
            String requestsYaml = Resources.toString(Resources.getResource("requests.yml"), Charsets.UTF_8);
            requests = (Map<String, List<Map<String, Object>>>) yaml.load(requestsYaml);
        } catch (IOException e) {
            LOG.log(Level.WARNING, "Unable to process requests.yml. No requests mocked.", e);
            return;
        }
        for(Map<String, Object> request : requests.get("requests")) {
            this.pathToResponseResource.put(
                    (String) request.get("url"),
                    new ResponseResource(
                            (String) request.get("responseResource"),
                            (Integer) request.get("responseCode")
                    )
            );
        }
    }


}
