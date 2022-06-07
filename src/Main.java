import javax.swing.SwingUtilities;

import database.DBData;

public class Main {
	public static void createAndShowGui() {
		new LaunchPage(new DBData());
	}
	
    public static void main(String[] args) {

    	SwingUtilities.invokeLater(new Runnable() {
	         public void run() {
	            createAndShowGui();
	         }
	      });

    }
}
