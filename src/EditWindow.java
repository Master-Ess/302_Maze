import java.awt.*;
import javax.swing.*;

public class EditWindow {

    JFrame frame = new JFrame();
    JLabel label = new JLabel("Hello!");

    EditWindow() {

        label.setBounds(0, 0, 100, 50);
        label.setFont(new Font(null, Font.PLAIN, 25));

        frame.add(label);

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        frame.setLayout(null);
        frame.setVisible(true);
    }
}