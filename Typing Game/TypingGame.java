/* Author Name: Isabella Stephens
 * Created: 28 May 2026
 * Updated: 29 May 2026
 * 
 * Description: (insert here)
 */

/*
 * Imports
 */
import java.awt.EventQueue;
import java.util.ArrayList;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.FileReader;
import javax.swing.JFrame;

/*
 * Beginning of public class TypingGame
 */
public class TypingGame {
	private JFrame frame;
	
	// stores the Strings from the .txt files
	public ArrayList<String> diffOne = new ArrayList<String>();
	public ArrayList<String> diffTwo = new ArrayList<String>();
	public ArrayList<String> diffThree = new ArrayList<String>();
	public ArrayList<String> diffFour = new ArrayList<String>();
	
	// stores the ArrayLists containing the Strings
	public ArrayList<ArrayList<String>> allDiff = new ArrayList<ArrayList<String>>();
	
	/* Purpose: Main method; launches application
	 */
	public static void main(String[] args) {
		//System.out.println("Testing");
		
		
		// figure out what this does
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TypingGame window = new TypingGame();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				} 
			} // end of run()
		});
	} // end of main 
	
	/*
	 * Purpose: Create the Application
	 */
	public TypingGame() {
		initialize();
	}
	
	/*
	 * Purpose: Initialize the contents of the frame. 
	 */
	private void initialize() {
		frame = new JFrame(); // creates new frame
		frame.setBounds(100, 100, 450, 300); // sets boundaries of frame (x, y, width, height)
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // closes window when hitting 'x' button
		
		allDiff.add(diffOne);
		allDiff.add(diffTwo);
		allDiff.add(diffThree);
		allDiff.add(diffFour);
		
	} // end of initialize()
	
	
	/* Purpose: Reads Strings from .txt files and puts them in an ArrayList
	 * 
	 * Note1: change from "throws" to "try-catch" if you want to do smthg else when catching the error
	 * Note2: Keep the ArrayList w/o parameters
	 */ 
	public void readText(String path, ArrayList currArr) throws IOException {
		BufferedReader buff = new BufferedReader(new FileReader(path));
		String word = buff.readLine();
		
		while(word != null) {
			currArr.add(word);
			word = buff.readLine();
		}
	} // end of readText()
	
	
} // end of public class TypingGame