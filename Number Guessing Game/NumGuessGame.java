/* Author Name: Isabella Stephens
 * Created: 31 May 2026
 * Updated: 31 May 2026
 * 
 * Description: The player has 30 seconds to guess a random number. The code informs the player whether they guessed signficantly less, less, significantly higher, 
 * higer, or the exact number. There is a counter that keeps track of how many guesses the player got correct. The timer resets every time the user guesses a number
 * correctly. The game ends when the timer runs out on a guess.
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
 * Beginning of public class NumGame
 */

public class NumGame {
  private JFrame frame;

  public static void main(String[] args) {
		System.out.println("Testing");

    // figure out what this does
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					NumGame window = new NumGame();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				} 
			} // end of run()
		});
  } // end of main method

  /*
	 * Purpose: Create the Application
	 */
	public NumGame() {
		initialize();
	}

  /*
	 * Purpose: Initialize the contents of the frame. 
	 */
	private void initialize() {
		frame = new JFrame(); // creates new frame
		frame.setBounds(100, 100, 450, 300); // sets boundaries of frame (x, y, width, height)
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // closes window when hitting 'x' button
  
} // end of NumGame
