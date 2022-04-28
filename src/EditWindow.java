import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

import util.MazeBlock;
import util.MazeDataStructure;

public class EditWindow extends JPanel{

    private static final long serialVersionUID = 1L;
    JFrame frame = new JFrame();

    JLabel label = new JLabel("Hello!");

    private static JMenuBar menubar = new JMenuBar();   //Only gonna have one so why make the name complex --> it autofills anyway

    private static JMenu file = new JMenu("File");
    private static JMenuItem New_itm = new JMenuItem("New...                            Ctrl + N"); //_itm because of syntax conflict 
    private static JMenuItem Load = new JMenuItem("Load...                           Ctrl + L");
    private static JMenuItem Save = new JMenuItem("Save                              Ctrl + S");
    private static JMenuItem Save_as = new JMenuItem("Save As...        Shift + Ctrl + S");

    private static JMenu edit = new JMenu("Edit");
    private static JMenuItem undo = new JMenuItem("Undo                              Ctrl + Z"); 
    private static JMenuItem cut = new JMenuItem("Cut                                 Ctrl + X"); 
    private static JMenuItem copy = new JMenuItem("Copy                              Ctrl + C"); 
    private static JMenuItem paste = new JMenuItem("Paste                            Ctrl + V"); 
    
    private static int blockThickness = 10;
    private static int blockLength = 40;
    private MazeDataStructure data = null;

    EditWindow(MazeDataStructure inputData) {
    	data = inputData;

        label.setBounds(0, 0, 100, 50);
        label.setFont(new Font(null, Font.PLAIN, 25)); 

        //frame.add(label);

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);

        frame.setLayout(null);
        frame.setVisible(true);


        //Build Menu
        frame.setJMenuBar(menubar);
        menubar.add(file);
        file.add(New_itm);
        file.add(Load);
        file.add(Save);
        file.add(Save_as);

        menubar.add(edit);
        edit.add(undo);
        edit.add(cut);
        edit.add(copy);
        edit.add(paste);
        

        frame.getContentPane().add(new MazeBlock());
        
      

    }

    //Graphics stuff
    @Override
    protected void paintComponent(Graphics g) {
     super.paintComponent(g);
     
     Graphics2D g2 = (Graphics2D)g;
     
     g2.setStroke(new BasicStroke(5));
     g2.setColor(Color.BLACK);
     g2.drawLine(10, 20, 100, 200);
     //End Graphics Stuff
    }
    
    
    private void drawMaze(MazeDataStructure data) {
    	int blockNum = data.getWidth() * 2 + 1;
        //In each of these commented spots, add the builder for a block
    	//y is the row number, x is the column number
    	
        for(int y = 0; y < data.getHeight(); y++){
        	//Top row
            for(int x = 0; x < data.getWidth(); x++){
                //output.append(addChar(data.getBlockState(x * blockNum + i)));
            }
            //Vertical Lines
            for(int x = data.getWidth(); x < data.getWidth() * 2 + 1; x++){
                //output.append(addChar(data.getBlockState(x * blockNum + i)));
            }
        }
        //gets bottom line
        for(int x = 0; x < data.getWidth(); x++){
            //output.append(addChar(data.get(mazeHeight * blockNum + i)));
        }
        

    }

    
}