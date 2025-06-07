import java.util.Scanner;
import java.io.IOException;
import java.io.InputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.BufferedWriter;
import java.io.FileWriter; //We want toedit file and such adndsuch

//I HATE NON OPEN SOURCE!


public class CanvasAPIRunner {
    private static boolean isSettingsTxtReal = false;
    private static String canvlink;
    private static String apitken;
    public static void main(String[] args) throws FileNotFoundException {
        load();
        if (isSettingsTxtReal == false) {
            Scanner scanner = new Scanner(System.in);
            System.out.println("Enter your school's Canvas Link without \"HTTPS://\": ");
            String canvasLink = scanner.nextLine();
            System.out.println("Enter your API Token: ");
            String apiToken = scanner.nextLine();
            System.out.println("Saved. To delete, just delete settings.txt.");
            save(canvasLink, apiToken);
            load();
            scanner.close();
        }
        // System.out.println("Data Test: " + "\n" + canvlink + "\n" + apitken);
        CanvasAPI test = new CanvasAPI(canvlink, apitken);
        Scanner scannerr = new Scanner(System.in);
        int newExitVal = 5; // streamlines adding new options
        while (true) {
            System.out.println("What would you like to do?");
            System.out.println("Options (and only type the number into the line)");
            System.out.println("All API Information (1), Clean All API Information (2), List of Current All Classes (3), List of All Time Classes (4), Exit ("+newExitVal+"))");
            String optionn = scannerr.nextLine();
            String option = optionn.toLowerCase();
            if (option.equals(Integer.toString(newExitVal))) System.exit(0); // lets exit if they say exit. else, we just send the option
            System.out.println(test.sendOption(option));
        }

    }

    private static void save(String apiLink, String token) {
        // no need for try catch since we already know settings.txt must have been made if load has already ran which will make one
        // ^ Ignore above statement, these things will error if there is no handler
        try {
            FileWriter writer = new FileWriter("settings.txt");
            writer.write(apiLink);
            writer.write("\n" + token);
            writer.close(); // Apparently, these IO things such as writer and scanner need to be closed
        }
        catch (IOException e) {System.out.println("Something went wrong.");}

    }

    private static void load() throws FileNotFoundException {
        if (checkIfFileExists()) { // ts is needed cuz we need something to settings.txt
            isSettingsTxtReal = true;
            File file = new File("settings.txt");
            Scanner fileScanner = new Scanner(file); // assumes file exists, or wrap in try-catch
            canvlink = fileScanner.nextLine();
            apitken = fileScanner.nextLine();
            fileScanner.close();
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