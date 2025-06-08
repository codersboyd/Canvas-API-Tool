import javax.swing.*;
import java.awt.event.*;
import java.awt.*;  
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class App implements ActionListener {
    private static JFrame frame = new JFrame("Canvas API Tool");
    private static JLabel welcomeThing = new JLabel("What would you like to do?");
    private static JButton allAPIInformation = new JButton("Get All API Information");
    private static JButton cleanallAPIInformation = new JButton("Get a cleaner ALL API information");
    private static JButton listOfEnrolled = new JButton("Get a list of all classes currently enrolled in");
    private static JButton listOfAllTimeEnrolled = new JButton("Get a list of all time classes");
    public App() {
        
    }
    public static void callEverything() {
        App app = new App(); // create one instance
        frame.setSize(360, 200);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BoxLayout(frame.getContentPane(), BoxLayout.Y_AXIS));
        frame.setLocationRelativeTo(null);

        allAPIInformation.addActionListener(app);
        cleanallAPIInformation.addActionListener(app);
        listOfEnrolled.addActionListener(app);
        listOfAllTimeEnrolled.addActionListener(app);


        frame.add(welcomeThing);
        frame.add(allAPIInformation);
        frame.add(cleanallAPIInformation);
        frame.add(listOfEnrolled);
        frame.add(listOfAllTimeEnrolled);
        frame.setVisible(true); // should be last?


    }
    @Override
    public void actionPerformed(ActionEvent e) {
        String[] load = CanvasAPIRunner.returnLoad();
        CanvasAPI WOAH = new CanvasAPI(load[0], load[1]);
        if (e.getSource() == allAPIInformation) {
            WOAH.sendOption("1");
        }
        else if (e.getSource() == cleanallAPIInformation) {
            WOAH.sendOption("2");
        }
        else if (e.getSource() == listOfEnrolled) {
            WOAH.sendOption("3");
        }
        else if (e.getSource() == listOfAllTimeEnrolled) {
            WOAH.sendOption("4");
        }
    }
}
