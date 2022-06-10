import java.awt.*;
import javax.swing.*;

import database.DBData;
import database.SaveFile;

import java.awt.event.*;
import java.io.IOException;

import util.MazeBlocks;
import util.MazeDataStructure;

public class EditWindow extends JFrame{

    private static final long serialVersionUID = 1L;

    JLabel label = new JLabel("Hello!");

    private static JMenuBar menubar = new JMenuBar();   //Only gonna have one so why make the name complex --> it autofills anyway

    private static JMenu file =         new JMenu("File");
    private static JMenuItem New_itm =  new JMenuItem("New...                            Ctrl + N"); //_itm because of syntax conflict 
    private static JMenuItem Load =     new JMenuItem("Load...                           Ctrl + L");
    private static JMenuItem Save =     new JMenuItem("Save                              Ctrl + S");
    private static JMenuItem Export =   new JMenuItem("Export...");
    private static JMenuItem Export_w = new JMenuItem("Export with Solution...");

    private static JMenu edit =         new JMenu("Edit");
    private static JMenuItem undo =     new JMenuItem("Undo                              Ctrl + Z"); 
    private static JMenuItem cut =      new JMenuItem("Cut                                 Ctrl + X"); 
    private static JMenuItem copy =     new JMenuItem("Copy                              Ctrl + C"); 
    private static JMenuItem paste =    new JMenuItem("Paste                            Ctrl + V");
    
    private static JButton update = 	new JButton("Update Maze");
    private static JButton randomise = 	new JButton("Randomise Maze");
    private static JButton rotateClockwise = 	new JButton("Rotate Clockwise");
    private static JButton rotateAnticlockwise = 	new JButton("Rotate Anticlockwise");
    
    private static JLabel x_size_lbl = new JLabel("Maze Width");
    private static JSpinner x_size_sp = new JSpinner();
    private static JLabel y_size_lbl = new JLabel("Maze Height");
    private static JSpinner y_size_sp = new JSpinner();
    private static JLabel thickness_lbl = new JLabel ("Block Thickness");
    private static JSpinner thickness_sp = new JSpinner();
    private static JLabel length_lbl = new JLabel ("Block Length");
    private static JSpinner length_sp = new JSpinner();
    
    
    private MazeDataStructure data =  new MazeDataStructure(0, 0, 0, 0, false);
    private DBData dbdata;
    private String FileName;
    private Loadmaze loadMaze;
    private LaunchPage launchPage;

    EditWindow( DBData dbdata) {
    	this.dbdata = dbdata;
    	
    	addWindowListener(new ClosingListener());
        
        //Build Menu
        initializeUI();
       
    }
    
    public void LoadMaze(MazeDataStructure inputData, String FileName) {
    	setVisible(true);
    	this.data = inputData;
    	this.FileName = FileName;
    	loadMaze.setVisible(false);
    	launchPage.setVisible(false);
    	createNewMaze();
    }
    
    private void initializeUI() {
        label.setBounds(0, 0, 100, 50);
        label.setFont(new Font(null, Font.PLAIN, 25)); 
    	setJMenuBar(menubar);
        menubar.add(file);
        file.add(New_itm);
        file.add(Load);
        file.add(Save);
        	randomise.addActionListener(new ActionListener() {
	        public void actionPerformed(ActionEvent e) {
	    		EditWindow.saveFile();
	    	}
	    });
        	
        file.add(Export);
        file.add(Export_w);

        menubar.add(edit);
        edit.add(undo);
        edit.add(cut);
        edit.add(copy);
        edit.add(paste);
        
        add(randomise);
        randomise.setBounds(825, 10, 150, 30);
        randomise.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		data.randomise();
        		repaint();
        	}
        });
        
        add(update);
        update.setBounds(650, 10, 150, 30);
        update.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		data.setLength((int)length_sp.getValue());
        		data.setThickness((int)thickness_sp.getValue());
        		repaint();
        	}
        });
        
        add(rotateClockwise);
        rotateClockwise.setBounds(1000, 10, 150, 30);
        rotateClockwise.addActionListener(new ActionListener() {
	        public void actionPerformed(ActionEvent e) {
	    		data.rotateClockwise();
	    		repaint();
	    	}
	    });
        
        add(rotateAnticlockwise);
        rotateAnticlockwise.setBounds(1175, 10, 170, 30);
        rotateAnticlockwise.addActionListener(new ActionListener() {
	        public void actionPerformed(ActionEvent e) {
	    		data.rotateAntiClockwise();
	    		repaint();
	    	}
	    });
        
      
        add(x_size_lbl);
        x_size_lbl.setBounds(5, 5, 80, 40);

        add(x_size_sp);
        x_size_sp.setBounds(80, 15, 60, 20);
        x_size_sp.setValue(1);

        add(y_size_lbl);
        y_size_lbl.setBounds(150, 5, 80, 40);

        add(y_size_sp);
        y_size_sp.setBounds(230, 15, 60, 20);
        y_size_sp.setValue(1);
        
        add(thickness_lbl);
        thickness_lbl.setBounds(300, 5, 120, 40);
        
        add(thickness_sp);
        thickness_sp.setBounds(400, 15, 60, 20);
        thickness_sp.setValue(data.getThickness());
        
        add(length_lbl);
        length_lbl.setBounds(470, 5, 120, 40);
        
        add(length_sp);
        length_sp.setBounds(550, 15, 60, 20);
        length_sp.setValue(data.getLength());
        
    }
        
    private void createNewMaze() {
    	initializeUI();
    	getContentPane().add(new MazeBlocks(data));
        pack();
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setTitle("M-ELK - " + FileName);
    }
    
    public void setWindows(Loadmaze loadMaze, LaunchPage launchPage) {
    	this.loadMaze = loadMaze;
    	this.launchPage = launchPage;
    }
    
    private void exportImage() {
    	
    }
    
    private void exportImageSolution() {
    	
    }
    
    private void saveFile() {
    	SaveFile file = new SaveFile();
    	file.setData(data);
    	file.setFileName(FileName);
    	try {
			dbdata.update(file);
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
    }
    
    private class ClosingListener extends WindowAdapter {
        public void windowClosing(WindowEvent e) {
        	saveFile();
        	dbdata.persist();
        	System.exit(0);
        }
     }
    
}