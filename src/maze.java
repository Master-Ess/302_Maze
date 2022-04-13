import java.awt.*;
import javax.swing.*;

import util.MazeDataStructure;

public class maze{

    private static void createWindow() {
        JFrame frame = new JFrame("mELK");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JLabel textLabel = new JLabel("Im contents or something", SwingConstants.CENTER);
        textLabel.setPreferredSize(new Dimension(300, 100));
        frame.getContentPane().add(textLabel, BorderLayout.CENTER);

        frame.setLocationRelativeTo(null);
        frame.pack();
        frame.setVisible(true);

    }

    public static void main(String[] args){
        createWindow();
    }


}