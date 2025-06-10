import java.util.Scanner;
import java.io.IOException;
import java.io.InputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.BufferedWriter;
import java.io.FileWriter; //We want toedit file and such adndsuch

//I HATE NON OPEN SOURCE!


public class CanvasAPIRunner {
    private static String canvlink;
    private static String apitken;
    public static void main(String[] args) throws FileNotFoundException {
        if (checkIfFileExists() == false || doesFileHaveValidData() == false) {
            if (checkIfFileExists()) {
                File file = new File("settings.txt");
                try {file.createNewFile();} 
                catch (IOException e) {}
            }
            Setup test = new Setup();
            test.callEverything();
        }
        load();
        // System.out.println("Data Test: " + "\n" + canvlink + "\n" + apitken);
        CanvasAPI test = new CanvasAPI(canvlink, apitken);
        Scanner scannerr = new Scanner(System.in);
        int newExitVal = 5; // streamlines adding new options
        // old logic for system.out.print below
        /* while (true) {
            System.out.println("What would you like to do?");
            System.out.println("Options (and only type the number into the line)");
            System.out.println("All API Information (1), Clean All API Information (2), List of Current All Classes (3), List of All Time Classes (4), Exit ("+newExitVal+"))");
            String optionn = scannerr.nextLine();
            String option = optionn.toLowerCase();
            if (option.equals(Integer.toString(newExitVal))) System.exit(0); // lets exit if they say exit. else, we just send the option
            System.out.println(test.sendOption(option));
        } */

        App applol = new App();
        applol.callEverything();

    }

    public static void save(String apiLink, String token) {
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
        File file = new File("settings.txt");
        Scanner fileScanner = new Scanner(file); // assumes file exists, or wrap in try-catch

        if (fileScanner.hasNextLine())
            canvlink = fileScanner.nextLine();

        if (fileScanner.hasNextLine())
            apitken = fileScanner.nextLine();

        fileScanner.close();
    }
    public static String[] returnLoad() {
        String[] canvasInfo = {canvlink,apitken};
        return (canvasInfo);
    }
    public static boolean doesFileHaveValidData() {
        boolean canRun = false;
        try {
            String[] load = CanvasAPIRunner.returnLoad();
            CanvasAPI WOAH = new CanvasAPI(load[0], load[1]);
            String lol = WOAH.allAPIInformation();
            if (lol.contains("name")) {
                canRun = true;
            }
        }
        catch (Exception ce) {System.out.println(ce);}
        return canRun;
    }
    private static boolean checkIfFileExists () {
        File file = new File("settings.txt");
        return file.exists();
    }
}