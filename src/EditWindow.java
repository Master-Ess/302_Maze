import java.awt.*;
import javax.swing.*;

import database.DBData;
import database.SaveFile;

import java.awt.event.*;
import java.io.IOException;

import util.Capture;
import util.MazeBlocks;
import util.MazeDataStructure;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

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
    
    private static MazeBlocks blocks;
    
    private boolean isInit = false;
    
    
    private MazeDataStructure data;
    private DBData dbdata;
    private String FileName;
    private Loadmaze loadMaze;
    private LaunchPage launchPage;

    EditWindow( DBData dbdata) {
    	this.dbdata = dbdata;
    	
    	addWindowListener(new ClosingListener());
	    getContentPane().addMouseListener(new MouseListener());
    }
    
    public void LoadMaze(MazeDataStructure inputData, String FileName) {
    	setVisible(true);
    	this.data = inputData;
    	this.FileName = FileName;
    	loadMaze.setVisible(false);
    	launchPage.setVisible(false);    	
    	createNewMaze();
    	setSpinnerVals();
    }
    
    private void initializeUI() {
        label.setBounds(0, 0, 100, 50);
        label.setFont(new Font(null, Font.PLAIN, 25)); 
    	setJMenuBar(menubar);
        menubar.add(file);
        file.add(New_itm);
        	New_itm.addActionListener(new ActionListener() {
	        	public void actionPerformed(ActionEvent e) {
	        		dispose();
	        		saveFile();
	        		Main.createAndShowGui();
	        	}
	        });	  

        file.add(Load);
	        Load.addActionListener(new ActionListener() {
	        	public void actionPerformed(ActionEvent e) {
	    	    	loadMaze.showWindow();
	    	    	saveFile();
	        	}
	        });	        
        file.add(Save);
	        randomise.addActionListener(new ActionListener() {
	        	public void actionPerformed(ActionEvent e) {
	        		saveFile();
	        	}
	        });
        file.add(Export);
	        Export.addActionListener(new ActionListener() {
	        	public void actionPerformed(ActionEvent e) {
	        		try {
						Capture.export(FileName, (data.getWidth() * data.getLength()) + data.getThickness() + 2, (data.getHeight() * data.getLength()) + data.getThickness() + 2);
					} catch (AWTException e1) {
						// TODO Auto-generated catch block -- File Error	
						e1.printStackTrace();
					} catch (InterruptedException e1) {
						// TODO Auto-generated catch block -- Timer Error
						e1.printStackTrace();
					}
	        	}
	        });
	    
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
        		
        		int mazeHeight = (int)y_size_sp.getValue();
        		if(mazeHeight != data.getHeight()) {
        			if(mazeHeight > data.getHeight()) {
        				data.addHeight(mazeHeight - data.getHeight());
        			}else{
        				data.removeHeight(data.getHeight() - mazeHeight);
        				
        			}
        		}
        		int mazeWidth = (int)x_size_sp.getValue();
        		if(data.getWidth() != mazeWidth) {
        			if(mazeWidth > data.getWidth()) {
        				data.addWidth(mazeWidth - data.getWidth());
        			}else if(mazeWidth < data.getWidth()){
        				data.removeWidth(data.getWidth() - mazeWidth);
        			}
        		}
        		
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
        
        getContentPane().add(blocks);

    }
        
    private void createNewMaze() {
    	if(isInit) {
    		getContentPane().remove(blocks);
    	}
    	blocks = new MazeBlocks(data);
    	if(!isInit) {
    		initializeUI();
    		isInit = true;
    	}else {
    		getContentPane().add(blocks);
    	}
    	
        pack();
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setTitle("M-ELK - " + FileName);
		repaint();
    }
    
    private void setSpinnerVals() {
    	x_size_sp.setValue((int)data.getWidth());
    	y_size_sp.setValue((int)data.getHeight());
    	length_sp.setValue((int)data.getLength());
    	thickness_sp.setValue((int)data.getThickness());
    	
    }
    
    public void setWindows(Loadmaze loadMaze, LaunchPage launchPage) {
    	this.loadMaze = loadMaze;
    	this.launchPage = launchPage;
    }
    
    private void exportImage() throws AWTException {
    	//Capture.export(FileName);
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
    
    
    public class MouseListener extends MouseAdapter {
    	
    	@Override
    	public void mouseClicked(MouseEvent e) {
    		int x_mouseClicked=e.getX();
    	    int y_mouseClicked=e.getY();
    		System.out.println(x_mouseClicked + " , " + y_mouseClicked);
    	}
    }

    
}