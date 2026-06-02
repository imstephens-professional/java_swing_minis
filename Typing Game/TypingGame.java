/* Author Name: Isabella Stephens
 * Created: 28 May 2026
 * Updated: 2 June 2026
 * 
 * Description: A Java typing game. The user must click start in order to begin. The window displays two words: the word the user 
 * must type, and the word the user must type next. The game increases in difficulty the more words the user types, going from 
 * difficulty 1 (3-letter words) to difficulty 4 (6-letter words). Each successful word typed is 1 point. The user has 90 seconds to 
 * accurately type as many words as possible. The user can reset the game at any time.
 */

/*
 * Imports
 */
import java.awt.EventQueue;
import java.util.ArrayList;
import java.util.Random;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.FileReader;
import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridBagLayout;
import javax.swing.JLabel;
import javax.swing.Timer;
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

/*
 * Beginning of public class TypingGame
 */
public class TypingGame {
	// global variables
	JLabel currentWord; 
	JLabel nextWord; 
	String ogWord;
	String ogWord2;
	int diffAmount = 0;
	int count = 0; // counts in difficulty
	int currDiff = 0;
	int userScore = 0;
	int timeSec = 90;
	Timer t; // javax.swing.Timer, NOT java.util.Timer
	private JFrame frame;
	private JTextField userInput;
	private JButton enterButton;
	private JButton startButton;
	private JButton resetButton;
	private JLabel scoreLabel;
	private JLabel timerLabel;
	ImageIcon playGame;
	ImageIcon playAgain;
	
	// stores the Strings from the .txt files
	public ArrayList<String> diffOne = new ArrayList<String>();
	public ArrayList<String> diffTwo = new ArrayList<String>();
	public ArrayList<String> diffThree = new ArrayList<String>();
	public ArrayList<String> diffFour = new ArrayList<String>();
	
	// stores the ArrayLists containing the Strings
	public ArrayList<ArrayList<String>> allDiff = new ArrayList<ArrayList<String>>();

	
	/* 
	 * Method Purpose: Main method; launches application
	 */
	public static void main(String[] args) {
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
	 * Method Purpose: Initialize the application.
	 */
	public TypingGame() throws IOException {
		initialize();
	}
	
	
	/*
	 * Method Purpose: Initialize the contents of the frame. This includes the view of the word the user is requested to type, the
	 * next word the user will type, the text box where the user will type the word, the enter button, the start button, and the
	 * reset button.
	 */
	private void initialize() throws IOException {
		// creates & sets the frame
		frame = new JFrame(); 
		frame.setBounds(100, 100, 450, 300); // (x, y, width, height)
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // closes window when hitting 'x' button
		
		// creates & sets the panel
		JPanel panel = new JPanel();
		frame.getContentPane().add(panel, BorderLayout.CENTER);
		GridBagLayout gbl_panel = new GridBagLayout();
		gbl_panel.columnWidths = new int[]{0, 0, 0, 158, 60, 49, 0, 0};
		gbl_panel.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
		gbl_panel.columnWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0, Double.MIN_VALUE};
		gbl_panel.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		panel.setLayout(gbl_panel);
		
		// TIMER LABEL
		timerLabel = new JLabel("Timer: " + timeSec);
		GridBagConstraints gbc_timerLabel = new GridBagConstraints();
		gbc_timerLabel.insets = new Insets(0, 0, 5, 5);
		gbc_timerLabel.gridx = 0;
		gbc_timerLabel.gridy = 1;
		panel.add(timerLabel, gbc_timerLabel);
		
		// SCORE LABEL
		scoreLabel = new JLabel("\"\"");
		GridBagConstraints gbc_scoreLabel = new GridBagConstraints();
		gbc_scoreLabel.insets = new Insets(0, 0, 5, 0);
		gbc_scoreLabel.gridx = 6;
		gbc_scoreLabel.gridy = 1;
		panel.add(scoreLabel, gbc_scoreLabel);
		scoreLabel.setText("Score: " + userScore);
		
		// CURRENT WORD LABEL
		currentWord = new JLabel("\"\"");
		currentWord.setFont(new Font("Tahoma", Font.BOLD, 16));
		GridBagConstraints gbc_currentWord = new GridBagConstraints();
		gbc_currentWord.insets = new Insets(0, 0, 5, 5);
		gbc_currentWord.gridx = 3;
		gbc_currentWord.gridy = 2;
		panel.add(currentWord, gbc_currentWord);
		
		// NEXT WORD LABEL
		nextWord = new JLabel("\"\"");
		nextWord.setFont(new Font("Tahoma", Font.PLAIN, 13));
		GridBagConstraints gbc_nextWord = new GridBagConstraints();
		gbc_nextWord.insets = new Insets(0, 0, 5, 5);
		gbc_nextWord.gridx = 3;
		gbc_nextWord.gridy = 3;
		panel.add(nextWord, gbc_nextWord);
		
		// USER INPUT TEXT FIELD
		userInput = new JTextField();
		GridBagConstraints gbc_userInput = new GridBagConstraints();
		gbc_userInput.insets = new Insets(0, 0, 5, 5);
		gbc_userInput.fill = GridBagConstraints.HORIZONTAL;
		gbc_userInput.gridx = 3;
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
				String userWord = userInput.getText();
				if(userWord.contentEquals(ogWord)) { // if the user typed the word correctly
					updateScore();
					game();
					userInput.setText("");
					userInput.requestFocusInWindow(); // focuses the user's 
				}
			}
		});
		GridBagConstraints gbc_enterButton = new GridBagConstraints();
		gbc_enterButton.insets = new Insets(0, 0, 5, 5);
		gbc_enterButton.gridx = 3;
		gbc_enterButton.gridy = 5;
		panel.add(enterButton, gbc_enterButton);
		
		// START BUTTON
		startButton = new JButton("Start Game");
		// important to put image before ActionListener
		playGame = new ImageIcon("Icons/playButton.png");
		Image image1 = playGame.getImage();
		Image newPlay = image1.getScaledInstance(20, 20, java.awt.Image.SCALE_SMOOTH);
		playGame = new ImageIcon(newPlay);
		startButton.setIcon(playGame);
		startButton.setBackground(Color.blue);
		// ActionListener here
		startButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				updateTimer();
				enterButton.setEnabled(true);
				userInput.setEnabled(true);
				startButton.setEnabled(false);
				frame.getRootPane().setDefaultButton(enterButton); // every time press enter, new word appears
				userInput.requestFocusInWindow();
				startButton.setBackground(null);
			}
		});
		GridBagConstraints gbc_startButton = new GridBagConstraints();
		gbc_startButton.insets = new Insets(0, 0, 5, 5);
		gbc_startButton.gridx = 3;
		gbc_startButton.gridy = 7;
		panel.add(startButton, gbc_startButton);
		
		// RESET BUTTON
		resetButton = new JButton("Reset Game");
		// important to put image before ActionListener
		playAgain = new ImageIcon("Icons/replayButton.png");
		Image image2 = playAgain.getImage();
		Image renewPlay = image2.getScaledInstance(20, 20, java.awt.Image.SCALE_SMOOTH);
		playAgain = new ImageIcon(renewPlay);
		resetButton.setIcon(playAgain);
		startButton.setBackground(Color.gray);
		// ActionListener here
		resetButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(null, "Game Over!" + "\n" + "Final Score: " + userScore);
				
				// try-catch statement bc IOException cannot be used here
				try {
					resetGame();
				} catch (IOException e1) {
					e1.printStackTrace();
				}
				
			}
		});
		GridBagConstraints gbc_resetButton = new GridBagConstraints();
		gbc_resetButton.insets = new Insets(0, 0, 0, 5);
		gbc_resetButton.gridx = 3;
		gbc_resetButton.gridy = 9;
		panel.add(resetButton, gbc_resetButton);
		
		// adds the ArrayList Strings into one ArrayList & save variable for later
		allDiff.add(diffOne);
		allDiff.add(diffTwo);
		allDiff.add(diffThree);
		allDiff.add(diffFour);
		diffAmount = allDiff.size();
		
		// gets the Strings from the .txt files
		for(int i=0; i<allDiff.size(); i++) {
			readText("Words/difficulty" + (i+1) + ".txt", allDiff.get(i));
		}
		
		// starts the game
		firstGame();
		
	} // end of initialize()
	
	
	
	/* 
	 * Method Purpose: Reads Strings from .txt files and puts them in an ArrayList
	 */ 
	public void readText(String path, ArrayList<String> currArr) throws IOException {
		BufferedReader buff = new BufferedReader(new FileReader(path));
		String word = buff.readLine();
		
		while(word != null) {
			currArr.add(word);
			word = buff.readLine();
		}
		
		buff.close(); 
	} 
	
	
	
	/* 
	 * Method Purpose: This changes the difficulty based on how many words the user completed.
	 */
	public void diffCounter() {
		count++;
		
		ArrayList<String> tempStore = allDiff.get(currDiff);
		int arraySize = tempStore.size();
		
		if(arraySize == count) {
			count+=1;
			currDiff+=1;
		}
		
	} 
	
	
	
	/*
	 * Method Purpose: Selects random word from the ArrayList & removes it so the player does not keep
	 * repeating it.
	 */
	public String randWord() {
		ArrayList <String> input = allDiff.get(currDiff); // get the ArrayList from the current difficulty
		Random num = new Random();
		int randomNum = num.nextInt(input.size()); // get a random num between 0 and the size of the ArrayList
		
		String output = input.get(randomNum);
		diffCounter();
		
		return output;
	} 
	
	
	/*
	 * Method Purpose: Sets the words up for the first game
	 */
	public void firstGame() {
		ogWord = randWord();
		currentWord.setText(ogWord);
		ogWord2 = randWord();
		nextWord.setText(ogWord2);
	} 
	
	
	/*
	 * Method Purpose: Sets up the words for every consequent game after the first one.
	 */
	public void game() {
		ogWord2 = randWord();
		ogWord = nextWord.getText();
		currentWord.setText(ogWord);
		nextWord.setText(ogWord2);
	} 
	
	/*
	 * Method Purpose: Completely resets the variables to their initial value, stops the timer if 
	 */
	public void resetGame() throws IOException {
		// clear the .txt files from the ArrayList
		for(int i = 0; i < allDiff.size(); i++) {
			allDiff.get(i).clear();
		}
		// add back all the Strings from the .txt files to the ArrayList
		for(int i = 0; i < diffAmount; i++) {
			readText("Words/difficulty" + (i+1) + ".txt", allDiff.get(i));
		}
		
		// reset variables
		count = 0;
		currDiff = 1;
		userScore = 0;
		scoreLabel.setText("Score: " + userScore);
		ogWord = "";
		ogWord2 = "";
		if(timeSec != 90) {
			t.stop();
		}
		timeSec = 90;
		timerLabel.setText("Timer: " + timeSec);
		userInput.setEnabled(false);
		startButton.setEnabled(true);
		startButton.setBackground(Color.blue);
		enterButton.setEnabled(false);
		
		frame.getRootPane().setDefaultButton(startButton);
		frame.repaint();
		frame.revalidate();
		userInput.setText("");
		userInput.requestFocusInWindow();
		
		firstGame();
	}
	
	
	/*
	 * Method Purpose: Called on when the user typed in a String accurately. Adds 1 to the userScore and updates information in the
	 * frame.
	 */
	public void updateScore() {
		userScore += 1;
		scoreLabel.setText("Score: " + userScore);
	} 
	
	
	/*
	 * Method Purpose: Updates the timer if the game ends (timer runs out of time or the game resets).
	 */
	public void updateTimer() {
		t = new Timer(1000, new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if(timeSec==0) {
					t.stop();
					JOptionPane.showMessageDialog(null, "Game Over!" + "\n" + "Final Score: " + userScore);
					userInput.setEnabled(false);
					enterButton.setEnabled(false);
					timeSec=90;
					
					try {
						resetGame();
					} catch (IOException e1) {
						e1.printStackTrace();
					}
				}	
				timeSec -= 1;
				timerLabel.setText("Time: " + timeSec);
			}	
		});
		
		t.start();
		
	} // end of updateTimer()
	
	
} // end of public class TypingGame
