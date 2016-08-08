package yahoofinance.mock;

import com.google.common.base.Charsets;
import com.google.common.io.Resources;
import okhttp3.mockwebserver.MockResponse;

import java.io.IOException;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Stijn Strickx
 */
public class ResponseResource {

    public static final Logger LOG = Logger.getLogger(ResponseResource.class.getName());

    private int responseCode;
    private String resource;

    public ResponseResource(String resource) {
        this(resource, 200);
    }

    public ResponseResource(String resource, int responseCode) {
        this.resource = resource;
        this.responseCode = responseCode;
    }

    public MockResponse get() {
        URL url = Resources.getResource(this.resource);
        try {
            String response = Resources.toString(url, Charsets.UTF_8);
            return new MockResponse().setBody(response).setResponseCode(this.responseCode);
        } catch (IOException e) {
            LOG.log(Level.SEVERE, "Unable to read response from resource", e);
        }
        return null;
    }

}
