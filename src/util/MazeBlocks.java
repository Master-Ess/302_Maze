package util;

import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.*;
import java.awt.Color;
import java.awt.Container;

public class MazeBlocks extends JPanel{
	private int thickness;
	private int length;
	public boolean solution;
	private MazeDataStructure data = null;
	private static int xPadding = 5;
	private static int yPadding = 50;
	private Solve solver;
	
	public MazeBlocks(MazeDataStructure data, Container container) {
    	this.data = data;
    	thickness = data.getThickness();
    	length = data.getLength();
    	solution = false;
	    container.addMouseListener(new MouseListener());
    	//solver = new Solve(data);
    	
    	repaint();
    }
	
	@Override
	protected void paintComponent(Graphics g) {
		int rowNum = data.getWidth() * 2 + 1;
		super.paintComponent(g);
		this.thickness = data.getThickness();
		this.length = data.getLength();
        //In each of these commented spots, add the builder for a block
    	//y is the row number, x is the column number
		
		int[][] tester = {{0,1},{0,2},{1,2}};
		if(true && solution) {
			for(int[] cell : tester) {
				drawSolveBlock(g, cell[0] * length + xPadding, cell[1] * length + yPadding);
			}
		}
    	
        for(int y = 0; y < data.getHeight(); y++){
        	//Top row
            for(int x = 0; x < data.getWidth(); x++){
            	drawHoriBlock(g, x * length + xPadding, y * length + yPadding, data.getBlockState(y * rowNum + x));
            }
            //Vertical Lines
            for(int x = data.getWidth(); x < data.getWidth() * 2 + 1; x++){
                drawVertBlock(g, x * length - length*data.getWidth() + xPadding, y * length + yPadding, data.getBlockState(y * rowNum + x));
            }
        }
        //gets bottom line
        for(int x = 0; x < data.getWidth(); x++){
        	drawHoriBlock(g, x * length + xPadding, data.getHeight() * length + yPadding, data.getBlockState(data.getHeight() * rowNum + x));
        }

	}
	
	private void drawVertBlock(Graphics g, int x, int y, boolean on){
		if(on) {
			g.setColor(Color.BLACK);
			g.drawRect(x, y, thickness, length + thickness);
			g.fillRect(x, y, thickness, length + thickness);
		}
	}
	
	private void drawHoriBlock(Graphics g, int x, int y, boolean on){
		if(on) {
			g.setColor(Color.BLACK);
			g.drawRect(x, y, length + thickness, thickness);
			g.fillRect(x, y, length + thickness, thickness);
		}
	}
	
	private void drawSolveBlock(Graphics g, int x, int y) {
		g.setColor(Color.RED);
		int internalSize = (length + thickness);
		g.drawRect(x, y, internalSize, internalSize);
		g.fillRect(x, y, internalSize, internalSize);
	}

	public class MouseListener extends MouseAdapter {
    	
		@Override
    	public void mouseClicked(MouseEvent e) {
    		int rowNum = data.getWidth() * 2 + 1;
    		int x_mouseClicked=e.getX();
    	    int y_mouseClicked=e.getY();  		
			for(int celly = 0; celly < data.getHeight(); celly++){
	        	//Top row
	            for(int cellx = 0; cellx < data.getWidth(); cellx++){
	            	if(x_mouseClicked > cellx * length + xPadding &&
	            			x_mouseClicked < cellx * length + xPadding + thickness + length &&
	            			y_mouseClicked > celly * length + yPadding &&
	            			y_mouseClicked < celly * length + yPadding + thickness){
	            		data.flipBlock(celly * rowNum + cellx);
	            		repaint();
	            	}
	            }
	            //Vertical Lines
	            for(int cellx = data.getWidth(); cellx < data.getWidth() * 2 + 1; cellx++){
	            	if(x_mouseClicked > cellx * length - length*data.getWidth() + xPadding &&
	            			x_mouseClicked < cellx * length - length*data.getWidth() + xPadding + thickness &&
	            			y_mouseClicked > celly * length + yPadding &&
	            			y_mouseClicked < celly * length + yPadding + length + thickness){
	            		data.flipBlock(celly * rowNum + cellx);
	            		repaint();
	                }
	            }
	        }
	        //gets bottom line
	        for(int cellx = 0; cellx < data.getWidth(); cellx++){
	        	if(x_mouseClicked > cellx * length + xPadding  &&
            			x_mouseClicked < cellx * length + xPadding + xPadding + thickness + length &&
            			y_mouseClicked > data.getHeight() * length + yPadding &&
            			y_mouseClicked < data.getHeight() * length + yPadding + thickness){
            		data.flipBlock(data.getHeight() * rowNum + cellx);
            		repaint();
	        	}
	        }
    		
    	}
    }
	
	public void toggleSolution() {
		if(solution) {
			solution = false;
		}else {
			solution = true;
		}
	}
}
