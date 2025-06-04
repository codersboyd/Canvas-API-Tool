import java.util.Scanner;
import java.io.IOException;
import java.io.InputStream;
import java.io.File;
import java.io.BufferedWriter;
import java.io.FileWriter; //We want toedit file and such adndsuch

//I HATE NON OPEN SOURCE!


public class CanvasAPIRunner {
    public static void main(String[] args) {
        load();
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter your school's Canvas Link: ");
        String canvasLink = scanner.nextLine();
        System.out.println("Enter your API Token: ");
        String apiToken = scanner.nextLine();
        System.out.println("Would you like to save this? Just put in \"Y\" for yes and anything else for no.");
        char wantToSaveChar = scanner.next().charAt(0);
        if (Character.toLowerCase(wantToSaveChar) == 'y') {save(canvasLink, apiToken);}; // Unreadable I know
        if (Character.toLowerCase(wantToSaveChar) == 'y') System.out.println("Saved."); else System.out.println("Okay, not saving."); // Unreadable I know
        CanvasAPI test = new CanvasAPI();
        


    }

    private static void save(String apiLink, String token) {
        // no need for try catch since we already know settings.txt must have been made if load has already ran which will make one
        InputStream input = CanvasAPIRunner.class.getResourceAsStream("/settings.txt");
        Scanner file = new Scanner(input);
        System.out.println("Save is called");
        try {
            FileWriter writer = new FileWriter("settings.txt");
            writer.write(apiLink);
            System.out.println(apiLink);
            writer.write(token);
            System.out.println(token);
        }
        catch (IOException e) {System.out.println("Something went wrong.");}

    }

    private static void load() {
        if (checkIfFileExists()) {
            
        }
        else { // Create settings.txt
            File file = new File("settings.txt");
            try {file.createNewFile();} 
            catch (IOException e) {}

        }
    }

    private static boolean checkIfFileExists () {
        File file = new File("settings.txt");
        return file.exists();
    }
}
