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
        App applol = new App();
        applol.callEverything();
        frame.dispose();
    }
}
