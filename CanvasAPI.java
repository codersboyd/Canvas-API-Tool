import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

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
            String constructLink = (canvasInstance + "/api/v1/users/?access_token=" + apiKey); // go to this and swap it lol
            try {
                // Enter code here
            }
            catch (Exception e) { e.printStackTrace();} // printStackTrace gives us the error
        }
        return output;
    }
}