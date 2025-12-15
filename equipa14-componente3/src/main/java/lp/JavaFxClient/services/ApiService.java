package lp.JavaFxClient.services;

	import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URL;
import java.net.http.HttpClient;
	import java.net.http.HttpRequest;
	import java.net.http.HttpResponse;

	public class ApiService {

	    private static final String BASE_URL = "http://localhost:8080";

	    private final HttpClient client = HttpClient.newHttpClient();

	    public String get(String path) {
	        try {
	            HttpRequest request = HttpRequest.newBuilder()
	                    .uri(URI.create(BASE_URL + path))
	                    .GET()
	                    .build();

	            return client.send(request, HttpResponse.BodyHandlers.ofString()).body();

	        } catch (Exception e) {
	            return "ERROR: " + e.getMessage();
	        }
	    }

	    public String post(String path, String json) {
	        try {
	            HttpRequest.BodyPublisher body =
	                    (json == null || json.isEmpty())
	                            ? HttpRequest.BodyPublishers.noBody()
	                            : HttpRequest.BodyPublishers.ofString(json);

	            HttpRequest request = HttpRequest.newBuilder()
	                    .uri(URI.create(BASE_URL + path))
	                    .header("Content-Type", "application/json")
	                    .POST(body)
	                    .build();

	            return client.send(request, HttpResponse.BodyHandlers.ofString()).body();

	        } catch (Exception e) {
	            return "ERROR: " + e.getMessage();
	        }
	    }
	    public String put(String path, String body) {
	        try {
	            URL url = new URL(BASE_URL + path);
	            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
	            conn.setRequestMethod("PUT");
	            conn.setRequestProperty("Content-Type", "application/json");
	            conn.setDoOutput(true);

	            if (body != null && !body.isBlank()) {
	                try (OutputStream os = conn.getOutputStream()) {
	                    os.write(body.getBytes());
	                }
	            }

	            InputStream is = conn.getResponseCode() >= 400
	                    ? conn.getErrorStream()
	                    : conn.getInputStream();

	            return new String(is.readAllBytes());

	        } catch (Exception e) {
	            return "ERROR: " + e.getMessage();
	        }
	    }


	    public String delete(String path) {
	        try {
	            HttpRequest request = HttpRequest.newBuilder()
	                    .uri(URI.create(BASE_URL + path))
	                    .DELETE()
	                    .build();

	            String body = client.send(request, HttpResponse.BodyHandlers.ofString()).body();
	            return body == null ? "" : body;

	        } catch (Exception e) {
	            return "ERROR: " + e.getMessage();
	        }
	    }
}

