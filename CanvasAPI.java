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
            output = allAPIInformation();
        }
        else if (optionlol.equals("2")) {
            output = allAPIInformationClean();
        }
        else if (optionlol.equals("3")) {
            output = listOfClassesEnrolledIn();
        }
        return output;
    }

    private String fetchAPI(String constructLinkThing) {
        String output = "";
        String constructLink = ("https://" + canvasInstance + constructLinkThing); // Essentially, if you do this in your browser by replacing it you will get data.
        try {
            HttpClient client = HttpClient.newHttpClient(); // makes a client
            HttpRequest request = HttpRequest.newBuilder() // makes a request object
            .uri(URI.create(constructLink)) // creating a proper link?
            .build(); // builds this all together? i got no clue
            HttpResponse<String> response = client.send(request, BodyHandlers.ofString()); // Response from the URL
            output = response.body(); // response.body is the text of json.
        } 
        catch (Exception e) { e.printStackTrace();} // printStackTrace gives us the error
        return output;
    }
    private String allAPIInformation () { // #1
        return fetchAPI("/api/v1/users/self?access_token=" + apiKey);
    }
    private String allAPIInformationClean() { // #2
        String output = allAPIInformation();
        // Use JSON libraries to clean up the raw data
        return output;
    }

    private String listOfClassesEnrolledIn() { // #3
        return fetchAPI("/api/v1/courses?access_token=" + apiKey + "&enrollment_state=active&per_page=100");
        // Also use JSON to clean up. Maybe for allAPIInformationClean and this one we make a dedicated helper class that will return it in a clean way?
    }
}