package util;

import java.awt.Graphics;

import javax.swing.*;

public class MazeBlocks extends JPanel{
	static int thickness = 20;
	static int length = 100;
	public static void changeThickness(int newThickness){thickness = newThickness;}
	public static void changeLength(int newLength) {length = newLength;}
	private static MazeDataStructure data = null;
	private static int padding = 5;
	
	public MazeBlocks(MazeDataStructure dataIn) {
    	this.data = dataIn;
    	repaint();
    }
	
	@Override
	protected void paintComponent(Graphics g) {
		int rowNum = data.getWidth() * 2 + 1;
		super.paintComponent(g);
        //In each of these commented spots, add the builder for a block
    	//y is the row number, x is the column number
    	
        for(int y = 0; y < data.getHeight(); y++){
        	//Top row
            for(int x = 0; x < data.getWidth(); x++){
            	drawHoriBlock(g, x * length + padding, y * length + padding, data.getBlockState(y * rowNum + x));
            }
            //Vertical Lines
            for(int x = data.getWidth(); x < data.getWidth() * 2 + 1; x++){
                drawVertBlock(g, x * length - length*data.getWidth() + padding, y * length + padding, data.getBlockState(y * rowNum + x));
            }
        }
        //gets bottom line
        for(int x = 0; x < data.getWidth(); x++){
        	drawHoriBlock(g, x * length + padding, data.getHeight() * length, data.getBlockState(data.getHeight() * rowNum + x));
        }
	}
	
	private void drawVertBlock(Graphics g, int x, int y, boolean on){
		if(on) {
			g.drawRect(x, y, thickness, length + thickness);
			g.fillRect(x, y, thickness, length + thickness);
		}
	}
	
	private void drawHoriBlock(Graphics g, int x, int y, boolean on){
		if(on) {
			g.drawRect(x, y, length + thickness, thickness);
			g.fillRect(x, y, length + thickness, thickness);
		}
	}
}
