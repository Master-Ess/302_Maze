package util;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.Graphics;

public class MazeBlock extends JPanel{
	private static int thickness = 100;
	private static int length = 600;
	//public void changeThickness(int newThickness){thickness = newThickness;}
	//public void changeLength(int newLength) {length = newLength;}
	

	public void paint(Graphics g){
		super.paint(g);
		g.drawRect(10, 10, thickness, length);
		g.fillRect(10, 10, thickness, length);
	}

}
