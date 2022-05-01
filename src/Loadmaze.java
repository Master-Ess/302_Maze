import java.awt.Font;
import java.awt.event.*;
import java.util.jar.JarEntry;

import javax.swing.*;

import util.MazeDataStructure;

public class Loadmaze extends JFrame{
    JFrame lframe = new JFrame();
    private static JLabel Title = new JLabel("Load Maze");
    private static JLabel tbl_lbl_1 = new JLabel("Name:");
    private static JLabel tbl_lbl_2 = new JLabel("Creator:");
    private static JLabel tbl_lbl_3 = new JLabel("Last Edit:");

    private static JButton export = new JButton("Export");
    private static JButton exportws = new JButton("Export w/ Solution");

    //example data
    private static JLabel name_1 = new JLabel("Maze 1");
    private static JLabel creator_1 = new JLabel("Default Creator");
    private static JLabel date_1 = new JLabel("1/1/1970 - 00:00");
    

    Loadmaze(){
        lframe.setSize(700, 400);
        lframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        lframe.setLayout(null);
        lframe.setVisible(true);
        lframe.setTitle("M-ELK - Load File");

        lframe.add(Title);
        Title.setBounds(260, 5, 240, 30);
        Title.setFont(new Font(null, Font.PLAIN, 25));

        lframe.add(tbl_lbl_1);
        tbl_lbl_1.setBounds(10, 40, 60, 30);
        tbl_lbl_1.setFont(new Font(null, Font.PLAIN, 15));

        lframe.add(tbl_lbl_2);
        tbl_lbl_2.setBounds(130, 40, 60, 30);
        tbl_lbl_2.setFont(new Font(null, Font.PLAIN, 15));

        lframe.add(tbl_lbl_3);
        tbl_lbl_3.setBounds(270, 40, 120, 30);
        tbl_lbl_3.setFont(new Font(null, Font.PLAIN, 15));

        lframe.add(name_1);
        name_1.setBounds(10, 70, 60, 30);        

        lframe.add(creator_1);
        creator_1.setBounds(130, 70, 120, 30); 

        lframe.add(date_1);
        date_1.setBounds(270, 70, 120, 30); 
        
        lframe.add(export);
        export.setBounds(380, 70, 100, 30);

        lframe.add(exportws);
        exportws.setBounds(490, 70, 180, 30);
    }   
}
