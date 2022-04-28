import java.awt.Font;
import java.awt.event.*;
import javax.swing.*;

import util.MazeDataStructure;

public class LaunchPage implements ActionListener {

    JFrame frame = new JFrame();
    private static JButton newfile_btn = new JButton("Generate Blank Maze");
    private static JButton load_btn = new JButton("Load Maze");

    private static JLabel Title_lbl = new JLabel("Create New Maze");

    private static JLabel filename_lbl = new JLabel("File Name");
    private static JTextField filename_tf = new JTextField(35);
    
    
    private static JLabel makername_lbl = new JLabel("Maker Name");
    private static JLabel makercomp_lbl = new JLabel("Maker Company");
    private static JTextField makercomp_tf = new JTextField(35);
    private static JTextField makername_tf = new JTextField(35);

    private static JLabel x_size_lbl = new JLabel("Maze Width");
    private static JSpinner x_size_sp = new JSpinner();
    private static JLabel y_size_lbl = new JLabel("Maze Height");
    private static JSpinner y_size_sp = new JSpinner();
    
    private static ButtonGroup btn_group = new ButtonGroup();
    private static JRadioButton rand_maze = new JRadioButton("Random");
    private static JRadioButton blank_maze = new JRadioButton("Blank");
    private static JLabel rand_tag = new JLabel("Initial Maze State");

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
        load_btn.addActionListener(this);

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

        frame.add(x_size_lbl);
        x_size_lbl.setBounds(10, 130, 80, 40);

        frame.add(x_size_sp);
        x_size_sp.setBounds(125,140, 60, 20);

        frame.add(y_size_lbl);
        y_size_lbl.setBounds(205, 130, 80, 40);

        frame.add(y_size_sp);
        y_size_sp.setBounds(295, 140, 60, 20);
        
        //Random Group Box
        btn_group.add(blank_maze);
        btn_group.add(rand_maze);
        
        frame.add(rand_tag);
        frame.add(rand_maze);
        frame.add(blank_maze);
        
        rand_tag.setBounds(10, 160, 120, 40);
        rand_maze.setBounds(125, 160, 80, 40);
        blank_maze.setBounds(205, 160, 60, 40);
        blank_maze.doClick();
 
        //frame.add(os);

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(420, 400);
        frame.setLayout(null);
        frame.setVisible(true);

        frame.setTitle("M-ELK - Create New File");

    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getSource().equals(newfile_btn)) {
            frame.dispose();
            SwingUtilities.invokeLater(new Runnable() {
	   	         public void run() {
	   	        	EditWindow myframe = new EditWindow(new MazeDataStructure((int)x_size_sp.getValue(), (int)y_size_sp.getValue(), rand_maze.isSelected()));
	   	         }
   	      	});            
        }else if(e.getSource().equals(load_btn)) {
        	frame.dispose();
        	SwingUtilities.invokeLater(new Runnable() {
	   	         public void run() {
	   	        	EditWindow myframe = new EditWindow(new MazeDataStructure((int)x_size_sp.getValue(), (int)y_size_sp.getValue(), rand_maze.isSelected()));
	   	         }
  	      	});
        }
    }
}
