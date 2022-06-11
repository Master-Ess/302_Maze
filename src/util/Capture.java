package util;
 
import java.awt.AWTException;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import javax.imageio.ImageIO;
 
public class Capture {
 
  public static void export(String mazename,int widthin, int heightin) throws AWTException, InterruptedException {
	  
	  
	  Robot robot = new Robot();
	  
	    TimeUnit.MILLISECONDS.sleep(10);
	  
	    // Capture a particular area on the screen
	 
	    int x = 2;
	 
	    int y = 100;
	 
	    int width = widthin;
	    
	    System.out.print(width);
	 
	    int height = heightin;
	    
	    System.out.print(height);
	 
	    Rectangle area = new Rectangle(x, y, width, height);
	 
	    BufferedImage bufferedImage = robot.createScreenCapture(area);
	 
	    // Write generated image to a file
	 
	    try {
	 
	  // Save as PNG
	    File test = new File("MazeImages./" + mazename + ".png"); 	
	    
	    File file = new File("MazeImages./" + mazename + ".png");  
	    
	   if(test.exists()) {
	   		  int iterator = 2;
	   		  test = new File("MazeImages./" + mazename + iterator + ".png");
	   		  while (test.exists()) {
	   			iterator++;
	   			test = new File("MazeImages./" + mazename + iterator + ".png");
	   		  }
	   		file = new File("MazeImages./" + mazename + iterator + ".png");
	   		ImageIO.write(bufferedImage, "png", file);
	   	 }
	   else {
		   ImageIO.write(bufferedImage, "png", file);
	   }
	   	 
	   	 
	    	
	 
	 
	  
	    } catch (IOException e) {
	 
	  System.out.println("Could not save small screenshot " + e.getMessage());
	 
	    }
  }
}
