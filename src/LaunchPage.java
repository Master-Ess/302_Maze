import java.awt.Font;
import java.awt.event.*;
import java.awt.Color;
import java.text.Collator;

import javax.swing.*;

import database.DBData;
import database.SaveFile;
import util.MazeDataStructure;

public class LaunchPage implements ActionListener {
	DBData dbdata;
	
    JFrame frame = new JFrame();
    
    private static JButton newfile_btn = new JButton("Generate Maze");
    private static JButton load_btn = new JButton("Load Maze");

    private static JLabel Title_lbl = new JLabel("Create New Maze");

    private static JLabel filename_lbl = new JLabel("File Name");
    private static JTextField filename_tf = new JTextField(35);
    private static JLabel file_exists = new JLabel("This File Name already Exists");
    
    
    private static JLabel makername_lbl = new JLabel("Maker Name");
    private static JLabel makercomp_lbl = new JLabel("Maker Company");
    private static JTextField makercomp_tf = new JTextField(35);
    private static JTextField makername_tf = new JTextField(35);

    private static JLabel x_size_lbl = new JLabel("Maze Width");
    private static JSpinner x_size_sp = new JSpinner();
    private static JLabel y_size_lbl = new JLabel("Maze Height");
    private static JSpinner y_size_sp = new JSpinner();
    private static JLabel thickness_lbl = new JLabel ("Maze Thickness");
    private static JSpinner thickness_sp = new JSpinner();
    private static JLabel length_lbl = new JLabel ("Block Length");
    private static JSpinner length_sp = new JSpinner();
    
    private static ButtonGroup btn_group = new ButtonGroup();
    private static JRadioButton rand_maze = new JRadioButton("Random");
    private static JRadioButton blank_maze = new JRadioButton("Blank");
    private static JLabel rand_tag = new JLabel("Initial Maze State");
    
    private Loadmaze loadMaze;
    private EditWindow editWindow;

    LaunchPage(DBData data) {
    	dbdata = data;
    	loadMaze = new Loadmaze(dbdata);
    	editWindow = new EditWindow(dbdata);
    	loadMaze.setWindows(editWindow, this);
    	editWindow.setWindows(loadMaze, this);
    	loadMaze.setVisible(false);
    	editWindow.setVisible(false);
    	
        frame.add(Title_lbl);
        frame.addWindowListener(new ClosingListener());
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
        x_size_sp.setValue(1);

        frame.add(y_size_lbl);
        y_size_lbl.setBounds(205, 130, 80, 40);

        frame.add(y_size_sp);
        y_size_sp.setBounds(295, 140, 60, 20);
        y_size_sp.setValue(1);
        
        frame.add(thickness_lbl);
        thickness_lbl.setBounds(10, 160, 120, 40);
        
        frame.add(thickness_sp);
        thickness_sp.setBounds(125, 170, 60, 20);
        thickness_sp.setValue(20);
        
        frame.add(length_lbl);
        length_lbl.setBounds(205, 160, 120, 40);
        
        frame.add(length_sp);
        length_sp.setBounds(295, 170, 60, 20);
        length_sp.setValue(50);

        //Random Group Box
        btn_group.add(blank_maze);
        btn_group.add(rand_maze);
        
        frame.add(rand_tag);
        frame.add(rand_maze);
        frame.add(blank_maze);
        
        rand_tag.setBounds(10, 190, 120, 40);
        rand_maze.setBounds(125, 190, 80, 40);
        blank_maze.setBounds(205, 190, 60, 40);
        blank_maze.doClick();
        
        frame.add(file_exists);
        file_exists.setBounds(10, 220, 180, 40);
        file_exists.setForeground(Color.RED);
        file_exists.setVisible(false);
 

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(420, 400);
        frame.setLayout(null);
        frame.setVisible(true);

        frame.setTitle("M-ELK - Create New File");
        
        // make new maze datastructure - ref line 160 set values to 3, 3 and false
        // to make walls set bit 0 and set bit 23 - flip bit
        //put that object into solve camd and print sovle values
        
        
    }
    
    public void showWindow() {
    	frame.setVisible(true);
    	loadMaze.setVisible(false);
    	editWindow.setVisible(false);
    	reset();
    }
    
    private void reset() {
    	file_exists.setVisible(false);
    	y_size_sp.setValue(1);
    	x_size_sp.setValue(1);
    	filename_tf.setText("");
    	makername_tf.setText("");
    	makercomp_tf.setText("");
    	blank_maze.doClick();
    }
    public void setVisible(Boolean bool) {
    	frame.setVisible(bool);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getSource().equals(newfile_btn)) {
        	if(dbdata.get(filename_tf.getText()).getFileName() == null && !filename_tf.getText().equals("")) {
        		//MazeDataStructure data = new MazeDataStructure(Math.abs((int)x_size_sp.getValue()), Math.abs((int)y_size_sp.getValue()), rand_maze.isSelected());
	        	MazeDataStructure data = new MazeDataStructure(Math.abs((int)x_size_sp.getValue()), Math.abs((int)y_size_sp.getValue()), Math.abs((int)thickness_sp.getValue()), Math.abs((int)length_sp.getValue()), rand_maze.isSelected());
	        	SaveFile file = new SaveFile();
	        	file.setAuthor(makername_tf.getText());
	        	file.setData(data);
	        	file.setCompany(makercomp_tf.getText());
	        	file.setFileName(filename_tf.getText());
	        	dbdata.add(file);
	           	editWindow.LoadMaze(data, file.getFileName());
        	}else {
        		file_exists.setVisible(true);
        	}
        }else if(e.getSource().equals(load_btn)) {
        	frame.dispose();
        	SwingUtilities.invokeLater(new Runnable() {
	   	         public void run() {
	   	        	loadMaze.setVisible(true);
	   	         }
  	      	});
        }
    }
    
    private class ClosingListener extends WindowAdapter {
        public void windowClosing(WindowEvent e) {
           dbdata.persist();
           System.exit(0);
        }
     }
}
