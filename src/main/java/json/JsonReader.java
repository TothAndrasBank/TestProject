package json;

import utils.Log;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.logging.Logger;

public class JsonReader {
    private final Logger logger = Log.getLogger();

    public String getJson(final String url) throws IOException, InterruptedException {
        final HttpResponse<String> response
                = HttpClient.newHttpClient()
                .send(HttpRequest.newBuilder()
                                .GET()
                                .uri(URI.create(url))
                                .build(),
                        HttpResponse.BodyHandlers.ofString());
        logger.info("status code: " + response.statusCode());
        return response.body();
    }


}
