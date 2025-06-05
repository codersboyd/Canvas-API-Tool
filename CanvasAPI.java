import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;

public class CanvasAPI {
    private String canvasInstance;    
    private String apiKey; // Lol No ENCRYPTION!!!!!
    public CanvasAPI() {

    }
    public CanvasAPI(String canvasurl, String api) {
        canvasInstance = canvasurl;
        apiKey = api;
    }
    public String sendOption(String optionlol) {
        String output = "";
        if (optionlol.equals("1")) {
            String constructLink = ("https://" + canvasInstance + "/api/v1/users/self?access_token=" + apiKey); // Essentially, if you do this in your browser by replacing it you will get data.
            try {
                HttpClient client = HttpClient.newHttpClient();
                HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(constructLink))
                .build();
                HttpResponse<String> response = client.send(request, BodyHandlers.ofString());
                output = response.body();
 
            }
            catch (Exception e) { e.printStackTrace();} // printStackTrace gives us the error
        }
        return output;
    }
}