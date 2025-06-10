import javax.swing.*;
import java.awt.event.*;
import java.awt.*;  
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class Setup implements ActionListener {
    private static JFrame frame = new JFrame("Initial Setup");
    private static JLabel apiKeyLabel = new JLabel("Enter API Key");
    private static JTextField apiKey = new JTextField();
    private static JLabel districtUrlLabel = new JLabel("Enter your District URL for Canvas (NO HTTPS://)");
    private static JTextField districtUrl = new JTextField();
    private static JButton confirm = new JButton("Confirm");
    public Setup() {
        //callEverything(); //feels right to me, lmk if bad, same thing to output I did
        //Honestly, I would go for static or nonstatic but rn were a little half and half, I think going nonstatic makes sense since we have all these instanec vars and stuff, like all methods go non static and all that, I think that makes more sense than what we got going on here and in app.java too
    }
    public static void callEverything() {
        frame.setSize(360, 180);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BoxLayout(frame.getContentPane(), BoxLayout.Y_AXIS));
        frame.setLocationRelativeTo(null);
        apiKey.setSize(40,40);
        districtUrl.setSize(40,40);
        confirm.setSize(100,30);
        confirm.addActionListener(new Setup());
        frame.add(apiKeyLabel);
        frame.add(apiKey);
        frame.add(districtUrlLabel);
        frame.add(districtUrl);
        frame.add(confirm);
        frame.setVisible(true); // should be last?


    }
    @Override
    public void actionPerformed(ActionEvent e) {
        CanvasAPIRunner.save(districtUrl.getText(), apiKey.getText());
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
        if (canRun) {
            App app = new App();
            App.callEverything();
            frame.dispose();
        }
        else {
            Output tellError = new Output("Either this canvas instance is down, or the information is incorrect. Please doublecheck what you put in.");
        }
    }
}
