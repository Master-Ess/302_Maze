import javax.swing.*;
import java.awt.*;
import java.awt.event.*;




public class App extends JFrame {
    private static JLabel filename_lbl = new JLabel("File Name");
    private static JTextField filename_tf = new JTextField(35);
    private static JLabel os = new JLabel();
    private static JButton newdoc_bt = new JButton("New Document");
    private static JLabel makername_lbl = new JLabel("Maker Name");
    private static JTextField makername_tf = new JTextField(35);

public static void main(String[] args) throws Exception{
    App Window = new App();
    Window.setSize(500,500);
    Window.setVisible(true);
    Window.setTitle("M-ELK");

    Window.setLayout(new FlowLayout());
    Window.getContentPane().add(filename_lbl);
    Window.getContentPane().add(filename_tf);
    Window.getContentPane().add(makername_lbl);
    Window.getContentPane().add(makername_tf);
    Window.getContentPane().add(newdoc_bt);
    Window.getContentPane().add(os);

    // Swing Objects here
    Window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
}


}