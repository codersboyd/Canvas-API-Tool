import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;
import org.json.JSONObject;
import org.json.JSONArray;


public class CanvasAPI {
   private String canvasInstance;   
   private String apiKey; // Lol No ENCRYPTION!!!!!
   public CanvasAPI() {


   }
   public CanvasAPI(String canvasurl, String api) {
       canvasInstance = canvasurl;
       apiKey = api;
   }
   public void sendOption(String optionlol) {
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
       else if (optionlol.equals("4")) {
           output = allTimeListOfClassesEnrolledIn();
       }
       Output haha = new Output(output);
       haha.doEverything();
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
       //System.out.println("fun print!");
       try {
           String dataString = allAPIInformation();
           JSONObject data = new JSONObject(dataString);
           int id = data.getInt("id"); // Yes
           String name = data.getString("name"); // Yes
           String createdDate = data.getString("created_at"); // Yes
           String sortableName = data.getString("sortable_name"); // Yes
           String shortName = data.getString("short_name");// Yes
           String avatarUrl = data.getString("avatar_url");// Yes
           String lastName = data.getString("last_name");// Yes
           String firstName = data.getString("first_name");// Yes
           String effectiveLocale = data.getString("effective_locale");
           JSONObject permissions = data.getJSONObject("permissions");
           boolean canUpdateProfilePicture = permissions.getBoolean("can_update_avatar");
           boolean limitParentWebAccess = permissions.getBoolean("limit_parent_app_web_access");
           String output = (
               "ID: " + id + "\n" +
               "Name: " + name + "\n" +
               "Date Created: " + createdDate + "\n" +
               "Sorted Name: " +sortableName + "\n" +
               "First Name: " + firstName + "\n" +
               "Last Name: " + lastName + "\n" +
               "Short Name: " + shortName + "\n" +
               "Profile Picture Link: " + avatarUrl + "\n" +
               "Effective Locale: " + effectiveLocale + "\n" +
               "Can Update Profile Picture: " + canUpdateProfilePicture + "\n" +
               "Can Parents Access Web: " + limitParentWebAccess + "\n"
           );
           return output;
       }
       catch (Exception e) {}


       return "Something went wrong.";
   }


   private String listOfClassesEnrolledIn() { // #3
        String output = "Classes Currently Enrolled in: ";
        String dataOfJson = fetchAPI("/api/v1/courses?access_token=" + apiKey + "&enrollment_state=active&per_page=100");
        JSONArray courses = new JSONArray(dataOfJson); // makes a json array of all the instances of courses
        int numberOfCourses = courses.length(); // gets array length of that
        for (int i = 0; i < numberOfCourses; i++) {
            JSONObject course = courses.getJSONObject(i);
            String courseName = course.getString("name");
            output += ("\n" + courseName);
        }

        // Also use JSON to clean up. Maybe for allAPIInformationClean and this one we make a dedicated helper class that will return it in a clean way?
        return output;
   }

   private String allTimeListOfClassesEnrolledIn() {
        String output = "Classes Currently Enrolled in: ";
        String dataOfJson = fetchAPI("/api/v1/courses?access_token=" + apiKey + "&per_page=100");
        JSONArray courses = new JSONArray(dataOfJson); // makes a json array of all the instances of courses
        int numberOfCourses = courses.length(); // gets array length of that
        for (int i = 0; i < numberOfCourses; i++) {
            JSONObject course = courses.getJSONObject(i);
            String courseName = course.optString("name", "(no name)"); // ig some classes dont have a name so fix that
            if (!courseName.equals("(no name)")) {
                output += ("\n" + courseName);
            } 
        }
        return output;
   }
  
}
