import java.awt.Font;
import java.awt.event.*;
import javax.swing.*;

public class LaunchPage implements ActionListener {

    JFrame frame = new JFrame();
    private static JButton newfile_btn = new JButton("Create New File");
    private static JButton load_btn = new JButton("Load File");

    private static JLabel Title_lbl = new JLabel("Create New File");

    private static JLabel filename_lbl = new JLabel("File Name");
    private static JTextField filename_tf = new JTextField(35);
    
    
    private static JLabel makername_lbl = new JLabel("Maker Name");
    private static JLabel makercomp_lbl = new JLabel("Maker Company");
    private static JTextField makercomp_tf = new JTextField(35);
    private static JTextField makername_tf = new JTextField(35);

    // Testing objects - Not currently Implemented

    private static JMenu test_mn = new JMenu("Hello");
    private static JMenuItem test_itm = new JMenuItem("ROXY!");
    private static JMenuItem test_itm2 = new JMenuItem("NICE!");

    LaunchPage() {

        frame.add(Title_lbl);
        Title_lbl.setBounds(100, 5, 240, 30);
        Title_lbl.setFont(new Font(null, Font.PLAIN, 25));

        frame.add(newfile_btn);
        newfile_btn.setBounds(205, 300, 185, 40);
        newfile_btn.setFocusable(false);
        newfile_btn.addActionListener(this);

        frame.add(load_btn);
        load_btn.setBounds(10, 300, 185, 40);
        load_btn.setFocusable(false);

        frame.add(filename_lbl);
        filename_lbl.setBounds(10, 40, 60, 40);

        frame.add(filename_tf);
        filename_tf.setBounds(125, 50, 250, 20);

        frame.add(makername_lbl);
        makername_lbl.setBounds(10, 70, 120, 40);

        frame.add(makername_tf);
        makername_tf.setBounds(125, 80, 250, 20);

        frame.add(makercomp_lbl);
        makercomp_lbl.setBounds(10, 100, 120, 40);

        frame.add(makercomp_tf);
        makercomp_tf.setBounds(125, 110, 250, 20);

 
        //frame.add(os);

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(420, 400);
        frame.setLayout(null);
        frame.setVisible(true);

        frame.setTitle("M-ELK - Create New File");

    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == newfile_btn) {
            frame.dispose();
            EditWindow myframe = new EditWindow();
        }
    }
}
