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
    private static JMenuItem Save_as =  new JMenuItem("Save As...        Shift + Ctrl + S");
    private static JMenuItem Export =   new JMenuItem("Export...");
    private static JMenuItem Export_w = new JMenuItem("Export with Solution...");

    private static JMenu edit =         new JMenu("Edit");
    private static JMenuItem undo =     new JMenuItem("Undo                              Ctrl + Z"); 
    private static JMenuItem cut =      new JMenuItem("Cut                                 Ctrl + X"); 
    private static JMenuItem copy =     new JMenuItem("Copy                              Ctrl + C"); 
    private static JMenuItem paste =    new JMenuItem("Paste                            Ctrl + V");
    
    private static JButton randomise = 	new JButton("Random");
    
    private static int blockThickness = 10;
    private static int blockLength = 40;
    private MazeDataStructure data =  new MazeDataStructure(0, 0, false);
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
        file.add(Save_as);
        file.add(Export);
        file.add(Export_w);

        menubar.add(edit);
        edit.add(undo);
        edit.add(cut);
        edit.add(copy);
        edit.add(paste);
        
        add(randomise);
        randomise.setBounds(1000, 5, 100, 30);
        randomise.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		data.randomise();
        		repaint();
        	}
        });
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