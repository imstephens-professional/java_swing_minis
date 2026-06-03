/* Author Name: Isabella Stephens
 * Created: 31 May 2026
 * Updated: 3 June 2026
 * 
 * Description: The player must guess a number between 1-1000. The code informs the player whether they guessed significantly less, 
 * lower, significantly higher, higher, or the exact number. The game ends when the player successfully guesses the number or the
 * player decides to give up.
 */

/*
 * Imports
 */
import java.awt.EventQueue;
import java.util.Random;
import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridBagLayout;
import javax.swing.JLabel;
import java.awt.GridBagConstraints;
import java.awt.Font;
import java.awt.Image;
import java.awt.Insets;
import javax.swing.ImageIcon;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class NumGuessGame {
	private JFrame frame;

	// global variables
	JLabel guessLabel;
	int numSelected;
	private JTextField userInput;
	private JButton enterButton;
	private JButton startButton;
	private JButton giveUpButton;
	ImageIcon playGame;
	ImageIcon giveUp;

	// main method
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					NumGuessGame window = new NumGuessGame();
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
	public NumGuessGame() {
		initialize();
	}

	/*
	 * Purpose: Initialize the contents of the frame.
	 */
	private void initialize() {
		// creates & sets the frame
		frame = new JFrame(); // creates new frame
		frame.setBounds(100, 100, 450, 300); // sets boundaries of frame (x, y, width, height)
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // closes window when hitting 'x' button

		// creates & sets the panel
		JPanel panel = new JPanel();
		frame.getContentPane().add(panel, BorderLayout.CENTER);
		GridBagLayout gbl_panel = new GridBagLayout();
		gbl_panel.columnWidths = new int[] { 0, 0, 0, 0, 0, 0, 0, 0, 0 };
		gbl_panel.rowHeights = new int[] { 0, 0, 0, 0, 0, 0, 0, 0, 0 };
		gbl_panel.columnWeights = new double[] { 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0, Double.MIN_VALUE };
		gbl_panel.rowWeights = new double[] { 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE };
		panel.setLayout(gbl_panel);

		// GUESS LABEL - Tells the player they guessed right or wrong
		guessLabel = new JLabel("\"\"");
		guessLabel.setFont(new Font("Tahoma", Font.BOLD, 13));
		GridBagConstraints gbc_guessLabel = new GridBagConstraints();
		gbc_guessLabel.insets = new Insets(0, 0, 5, 5);
		gbc_guessLabel.gridx = 4;
		gbc_guessLabel.gridy = 2;
		panel.add(guessLabel, gbc_guessLabel);
		
		// USER INPUT TEXT FIELD
		userInput = new JTextField();
		GridBagConstraints gbc_userInput = new GridBagConstraints();
		gbc_userInput.insets = new Insets(0, 0, 5, 5);
		gbc_userInput.fill = GridBagConstraints.HORIZONTAL;
		gbc_userInput.gridx = 4;
		gbc_userInput.gridy = 4;
		panel.add(userInput, gbc_userInput);
		userInput.setColumns(10);
		userInput.setEnabled(false);
		
		// ENTER BUTTON
		enterButton = new JButton("Enter");
		enterButton.setEnabled(false);
		// ActionListener here
		enterButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// NOT ERROR PROOF - DOES NOT CHECK TO SEE IF THE TEXT IS AN INT
				String userWord = userInput.getText();
				int result = Integer.parseInt(userWord);
				if(result == numSelected) { // if the user typed in the correct number
					JOptionPane.showMessageDialog(null, "Game Over!" + "\n" + "You guessed correctly!");
					userInput.setEnabled(false);
					enterButton.setEnabled(false);
					resetGame();
				} else {
					numCloseness(result);
					userInput.setText("");
					userInput.requestFocusInWindow();
				}
				
				//
			}
		});
		GridBagConstraints gbc_enterButton = new GridBagConstraints();
		gbc_enterButton.insets = new Insets(0, 0, 5, 5);
		gbc_enterButton.gridx = 4;
		gbc_enterButton.gridy = 5;
		panel.add(enterButton, gbc_enterButton);
		
		// START BUTTON
		startButton = new JButton("Start");
		playGame = new ImageIcon("Icons/playButton.png");
		Image image1 = playGame.getImage();
		Image newPlay = image1.getScaledInstance(20, 20, java.awt.Image.SCALE_SMOOTH);
		playGame = new ImageIcon(newPlay);
		startButton.setIcon(playGame);
		startButton.setBackground(Color.green);
		// ActionListener here
		startButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				enterButton.setEnabled(true);
				giveUpButton.setEnabled(true);
				userInput.setEnabled(true);
				startButton.setEnabled(false);
				frame.getRootPane().setDefaultButton(enterButton); // every time press enter, new word appears
				userInput.requestFocusInWindow();
				startButton.setBackground(null);
			}
		});
		GridBagConstraints gbc_startButton = new GridBagConstraints();
		gbc_startButton.insets = new Insets(0, 0, 5, 5);
		gbc_startButton.gridx = 4;
		gbc_startButton.gridy = 6;
		panel.add(startButton, gbc_startButton);
		
		// GIVE UP BUTTON
		giveUpButton = new JButton("Give Up");
		giveUp = new ImageIcon("Icons/giveUpButton.png");
		Image image2 = giveUp.getImage();
		Image givedUp = image2.getScaledInstance(20, 20, java.awt.Image.SCALE_SMOOTH);
		giveUp = new ImageIcon(givedUp);
		giveUpButton.setIcon(giveUp);
		giveUpButton.setEnabled(false);
		// ActionListener here
		giveUpButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(null, "Game Over!" + "\n" + "The number was " + numSelected + ".");
				resetGame();
			}
		});
		GridBagConstraints gbc_giveUpButton = new GridBagConstraints();
		gbc_giveUpButton.insets = new Insets(0, 0, 0, 5);
		gbc_giveUpButton.gridx = 4;
		gbc_giveUpButton.gridy = 7;
		panel.add(giveUpButton, gbc_giveUpButton);
		
		

		// starts the game
		firstGame();

	} // end of initialize()


	/*
	 * Method Purpose: Starts the game by asking the player if they can guess a
	 * whole number and then selecting a number for the player to guess.
	 */
	public void firstGame() {
		guessLabel.setText("Please guess a whole number.");
		randNum();
	}

	/*
	 * Method Purpose: To assign the int that the user will guess the value of. The
	 * int will be between 1 and 1000
	 */
	public void randNum() {
		Random r = new Random();
		numSelected = r.nextInt((1000 - 1) + 1) + 1;
	}
	
	/*
	 * Method Purpose: Tells the user how close they were to the number they were supposed to guess.
	 */
	public void numCloseness(int userGuess) {
		String closeness = "";
		
		if(userGuess > (numSelected + 300)) {
			closeness = "significantly higher";
		} else if(userGuess > (numSelected + 100)) {
			closeness = "much higher";
		} else if(userGuess < (numSelected - 300)) {
			closeness = "significantly lower";
		} else if(userGuess < (numSelected - 100)) {
			closeness = "much lower";
		} else if(userGuess > numSelected) {
			closeness = "higher";
		} else {
			closeness = "lower";
		}
		
		guessLabel.setText("Your guess is " + closeness + " than the number selected.");
	}

	
	/*
	 * Method Purpose: Completely resets the variables to their initial value,
	 * reselects a random number for the player to guess.
	 */
	public void resetGame() {
		guessLabel.setText("Please guess a whole number.");
		enterButton.setEnabled(false);
		startButton.setEnabled(true);
		giveUpButton.setEnabled(false);
		
		randNum();
	}

} // end of NumGuessGame
