import javax.swing.*;
import java.awt.event.*;
import java.awt.*;  
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

//make dynamically adding buttons and options and stuff, make icons cool, for loop for part of dynamic, beautify i guess

public class App implements ActionListener {
    private static JFrame frame = new JFrame("Canvas API Tool");
    private static JLabel welcomeThing = new JLabel("What would you like to do?");
    private static ArrayList<JButton> butts = new ArrayList<JButton>();
    private static JButton allAPIInformation = new JButton("Get All API Information");
    private static JButton cleanallAPIInformation = new JButton("Get a cleaner ALL API information");
    private static JButton listOfEnrolled = new JButton("Get a list of all classes currently enrolled in");
    private static JButton listOfAllTimeEnrolled = new JButton("Get a list of all time classes");
    private static JButton changeSettings = new JButton("Change settings");
    private static JButton exitButt = new JButton("Exit");

    
    public App() { //why exist?
        
    }
    public static void closeIt() {
        frame.dispose();
    }
    public static void callEverything() {
        App app = new App(); // create one instance
        frame.setSize(360, 250);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BoxLayout(frame.getContentPane(), BoxLayout.Y_AXIS));
        frame.setLocationRelativeTo(null);

        // allAPIInformation.addActionListener(app);
        // cleanallAPIInformation.addActionListener(app);
        // listOfEnrolled.addActionListener(app);
        // listOfAllTimeEnrolled.addActionListener(app);

        frame.add(welcomeThing);

        butts.add(allAPIInformation);
        butts.add(cleanallAPIInformation);
        butts.add(listOfEnrolled);
        butts.add(listOfAllTimeEnrolled);
        butts.add(changeSettings);
        butts.add(exitButt);
        
        for (JButton b: butts) {
            b.addActionListener(app);
            frame.add(b);
        }
        
        frame.setVisible(true); // should be last?


    }
    @Override
    public void actionPerformed(ActionEvent e) { //addactionlistener takes in something that implements actionlistener and therefore has action performed method, can be done with lambda expression
        String[] load = CanvasAPIRunner.returnLoad();
        CanvasAPI WOAH = new CanvasAPI(load[0], load[1]);

        WOAH.sendOption(butts.indexOf(e.getSource())+1); 


        // if (e.getSource() == allAPIInformation) {
        //     WOAH.sendOption(1); 
        // }
        // else if (e.getSource() == cleanallAPIInformation) {
        //     WOAH.sendOption(2);
        // }
        // else if (e.getSource() == listOfEnrolled) {
        //     WOAH.sendOption(3);
        // }
        // else if (e.getSource() == listOfAllTimeEnrolled) {
        //     WOAH.sendOption(4);
        // }
        // else if (e.getSource() == changeSettings) {
        //     WOAH.sendOption(5);
        // }
        // else if (e.getSource() == changeSettings) {
        //     WOAH.sendOption(6)
        // }
    }
}
