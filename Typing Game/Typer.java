// imports
import java.awt.EventQueue;
import java.util.ArrayList;
import javax.swing.JFrame;

// beginning of public class Typer
public class Typer {
	
	private JFrame frame;
	
	// stores the Strings from the .txt files
	public ArrayList<String> diffOne = new ArrayList<String>();
	public ArrayList<String> diffTwo = new ArrayList<String>();
	public ArrayList<String> diffThree = new ArrayList<String>();
	public ArrayList<String> diffFour = new ArrayList<String>();
	
	// stores the ArrayLists containing the Strings
	public ArrayList<ArrayList<String>> allDiff = new ArrayList<ArrayList<String>>();
	
	public static void main(String[] args) {
		//System.out.println("Testing");
		
		
		// figure out what this does
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Typer window = new Typer();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				} 
			} // end of run()
		});
	} // end of main 
	
	// Purpose: Create the Application
	public Typer() {
		initialize();
	}
	
	
	// Purpose: Initialize the contents of the frame. 
	private void initialize() {
		frame = new JFrame(); // creates new frame
		frame.setBounds(100, 100, 450, 300); // sets boundaries of frame (x, y, width, height)
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // closes window when hitting 'x' button
	}
	
	
} // end of public class Typer