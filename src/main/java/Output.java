//so essentially what this will be is a window that opens with all the text so yeah just to make it cleaner and simpler iykyk

import javax.swing.*;  

public class Output {
    private String data;
    
    public Output() {
        doEverything(); //ima put it in here since we alwyas doeverything when we consturct lmk if bad
    }   
    public Output (String dataToPrint) {
        data = dataToPrint;
        doEverything(); //ima put it in here since we alwyas doeverything when we consturct lmk if bad
    }
    
    public void doEverything () { //essentially doeverything will just call everything and if that makes sense then yeah
        JFrame frame = new JFrame("Output");
        frame.setSize(720, 500);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setLayout(new BoxLayout(frame.getContentPane(), BoxLayout.Y_AXIS));
        frame.setLocationRelativeTo(null);
        JTextArea output = new JTextArea(data);
        output.setEditable(false);

        // so by default the text wont auto collapse so heres code to make it auto collapse
        
        JScrollPane scrollPane = new JScrollPane(output);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_NEVER);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        output.setLineWrap(true);
        output.setWrapStyleWord(true);



        frame.add(scrollPane);
        frame.setVisible(true);
    }
}
