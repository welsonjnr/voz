package repository;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;

public class Request {

    private static String API_KEY = "<api_key>";

    public static HttpResponse<String> requestGetAdvice(String URL_GET) throws IOException, InterruptedException{
        HttpClient client = HttpClient.newHttpClient();

        HttpRequest request = HttpRequest.newBuilder()
                .GET()
                .timeout(Duration.ofSeconds(10))
                .uri(URI.create(URL_GET))
                .build();

        HttpResponse<String> response = client.send(request,HttpResponse.BodyHandlers.ofString());
        return response;
    }

    public static HttpResponse<String> requestPostTranslate(String URL_POST, String textToTranslate) throws IOException, InterruptedException, URISyntaxException {
        HttpClient client = HttpClient.newHttpClient();

        HttpRequest request = HttpRequest.newBuilder()
                .uri(new URI(URL_POST))
                .header("Content-Type", "application/x-www-form-urlencoded")
                .header("X-Funtranslations-Api-Secret", API_KEY)
                .POST(HttpRequest.BodyPublishers.ofString("text="+textToTranslate))
                .build();

        HttpResponse<String> response = client.send(request,HttpResponse.BodyHandlers.ofString());
        return response;
    }


}
