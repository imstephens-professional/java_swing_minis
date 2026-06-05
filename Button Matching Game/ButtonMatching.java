package looksHeres;
/*
 * Author Name: Isabella Stephens
 * Created: 3 June 2026
 * Updated: 5 June 2026
 * 
 * Description: A Java button-matching game. The frame has 8 buttons with 4 matches. The player must click each button to find the 
 * corresponding button that matches. The game ends when the player presses "New Game".
 */

/*
 * Imports
 */
import java.awt.EventQueue;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Random;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.Timer;


public class ButtonMatching implements ActionListener {
	private JFrame frame;
	private JButton startButton, resetButton;
	private JButton match1, match2, match3, match4, match5, match6, match7, match8;
	private JButton firstClicked, secondClicked;
	private JLabel scoreLabel;
	ArrayList<JButton> allButtons = new ArrayList<JButton>();
	private boolean isChecking = false; // Prevents spam clicking during delays
	int pairsFound = 0;
	int userScore = 0;
	
	// main method
		public static void main(String[] args) {
			EventQueue.invokeLater(new Runnable() {
				public void run() {
					try {
						ButtonMatching window = new ButtonMatching();
						window.frame.setVisible(true);
					} catch (Exception e) {
						e.printStackTrace();
					}
				} // end of run()
			});
		} // end of main method
		
		public ButtonMatching() {
			initialize();
		}
		
		public void initialize() {
			// creates & sets the frame
			frame = new JFrame(); // creates new frame
			frame.setTitle("Button-Matching Game");
			frame.setBounds(100, 100, 300, 250); // sets boundaries of frame (x, y, width, height)
			frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // closes window when hitting 'x' button
			
			// creates & sets the panel
			JPanel panel = new JPanel();
			frame.getContentPane().add(panel, BorderLayout.CENTER);
			GridBagLayout gbl_panel = new GridBagLayout();
			gbl_panel.columnWidths = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
			gbl_panel.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0};
			gbl_panel.columnWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
			gbl_panel.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
			panel.setLayout(gbl_panel);
			
			// SCORE LABEL
			scoreLabel = new JLabel("Score: " + userScore);
			GridBagConstraints gbc_scoreLabel = new GridBagConstraints();
			gbc_scoreLabel.insets = new Insets(0, 0, 5, 0);
			gbc_scoreLabel.gridx = 11;
			gbc_scoreLabel.gridy = 0;
			panel.add(scoreLabel, gbc_scoreLabel);
			
			// FIRST BUTTON
			match1 = new JButton("Click Here");
			match1.addActionListener(this);
			match1.setEnabled(false); 
			GridBagConstraints gbc_match1 = new GridBagConstraints();
			gbc_match1.insets = new Insets(0, 0, 5, 5);
			gbc_match1.gridx = 1;
			gbc_match1.gridy = 1;
			panel.add(match1, gbc_match1);
			
			// FIFTH BUTTON
			match5 = new JButton("Click Here");
			match5.addActionListener(this);
			match5.setEnabled(false);
			GridBagConstraints gbc_match5 = new GridBagConstraints();
			gbc_match5.insets = new Insets(0, 0, 5, 0);
			gbc_match5.gridx = 11;
			gbc_match5.gridy = 1;
			panel.add(match5, gbc_match5);
			
			// SECOND BUTTON
			match2 = new JButton("Click Here");
			match2.addActionListener(this);
			match2.setEnabled(false);
			GridBagConstraints gbc_match2 = new GridBagConstraints();
			gbc_match2.insets = new Insets(0, 0, 5, 5);
			gbc_match2.gridx = 1;
			gbc_match2.gridy = 3;
			panel.add(match2, gbc_match2);
			
			// SIXTH BUTTON
			match6 = new JButton("Click Here");
			match6.addActionListener(this);
			match6.setEnabled(false);
			GridBagConstraints gbc_match6 = new GridBagConstraints();
			gbc_match6.insets = new Insets(0, 0, 5, 0);
			gbc_match6.gridx = 11;
			gbc_match6.gridy = 3;
			panel.add(match6, gbc_match6);
			
			// START BUTTON
			startButton = new JButton("Start");
			startButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					match1.setEnabled(true);
					match2.setEnabled(true);
					match3.setEnabled(true);
					match4.setEnabled(true);
					match5.setEnabled(true);
					match6.setEnabled(true);
					match7.setEnabled(true);
					match8.setEnabled(true);
					resetButton.setEnabled(true);
					startButton.setEnabled(false);
					startButton.setBackground(null);
				}
			});
			GridBagConstraints gbc_startButton = new GridBagConstraints();
			gbc_startButton.insets = new Insets(0, 0, 5, 5);
			gbc_startButton.gridx = 7;
			gbc_startButton.gridy = 4;
			panel.add(startButton, gbc_startButton);
			
			// THIRD BUTTON
			match3 = new JButton("Click Here");
			match3.addActionListener(this);
			match3.setEnabled(false);
			GridBagConstraints gbc_match3 = new GridBagConstraints();
			gbc_match3.insets = new Insets(0, 0, 5, 5);
			gbc_match3.gridx = 1;
			gbc_match3.gridy = 5;
			panel.add(match3, gbc_match3);
			
			// RESET BUTTON
			resetButton = new JButton("New Game");
			resetButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					JOptionPane.showMessageDialog(null, "Game Over!" + "\n" + "Final Score: " + userScore);
					resetGameCompletely();
				}
			});
			resetButton.setEnabled(false);
			GridBagConstraints gbc_resetButton = new GridBagConstraints();
			gbc_resetButton.insets = new Insets(0, 0, 5, 5);
			gbc_resetButton.gridx = 7;
			gbc_resetButton.gridy = 5;
			panel.add(resetButton, gbc_resetButton);
			
			// SEVENTH BUTTON
			match7 = new JButton("Click Here");
			match7.addActionListener(this);
			match7.setEnabled(false);
			GridBagConstraints gbc_match7 = new GridBagConstraints();
			gbc_match7.insets = new Insets(0, 0, 5, 0);
			gbc_match7.gridx = 11;
			gbc_match7.gridy = 5;
			panel.add(match7, gbc_match7);
			
			// FOURTH BUTTON
			match4 = new JButton("Click Here");
			match4.addActionListener(this);
			match4.setEnabled(false);
			GridBagConstraints gbc_match4 = new GridBagConstraints();
			gbc_match4.insets = new Insets(0, 0, 0, 5);
			gbc_match4.gridx = 1;
			gbc_match4.gridy = 7;
			panel.add(match4, gbc_match4);
			
			// EIGHTH BUTTON
			match8 = new JButton("Click Here");
			match8.addActionListener(this);
			match8.setEnabled(false);
			GridBagConstraints gbc_match8 = new GridBagConstraints();
			gbc_match8.gridx = 11;
			gbc_match8.gridy = 7;
			panel.add(match8, gbc_match8);
			
			// store Jbuttons in the ArrayList
			allButtons.add(match1);
			allButtons.add(match2);
			allButtons.add(match3);
			allButtons.add(match4);
			allButtons.add(match5);
			allButtons.add(match6);
			allButtons.add(match7);
			allButtons.add(match8);
			
			numAssigning();
		}
		
		/*
		 * Purpose: Produce 4 randomly-generated ints and randomly assigns them to buttons.
		 * 
		 * Note: Likely not efficiently-written. 
		 */
		public void numAssigning() {
			ArrayList<Integer> numbers = new ArrayList<Integer>();
			Random r = new Random();
			int temp = 0;		
			
			// puts int values into an array; does not check if they are unique
			for(int i = 0; i < 10; i++) {
				temp = r.nextInt(100);
				numbers.add(temp);
				numbers.add(temp);
			}
			
			// sorts the ArrayList with Int values
			numbers.sort(Comparator.naturalOrder());
			
			// shuffles the buttons
			Collections.shuffle(allButtons);
			 
			 // assigns num value to the buttons
			 for(int i = 0; i < allButtons.size(); i++) {
				 JButton temporary = allButtons.get(i);
				 temporary.putClientProperty("myValueKey", numbers.get(i));
			 }
		}
		
		/*
		 * Purpose: Press the "Click Here!" Buttons and an action will be performed.
		 */
		@Override
	    public void actionPerformed(ActionEvent e) {
			if (isChecking) return; // Ignore input if game is processing a mismatch
			
			JButton clickedButton = (JButton) e.getSource();
			int attachedValue = (int) clickedButton.getClientProperty("myValueKey");
			
			// Prevent clicking already matched or currently revealed buttons
	        if (attachedValue==-1 || clickedButton == firstClicked) {
	            return;
	        }
	        
	        // Set the text of the button to its hidden value
	        String valueString = String.valueOf(attachedValue);
	        clickedButton.setText(valueString);
	        
	        if (firstClicked == null) {
	            firstClicked = clickedButton; // First button selection of the pair
	        } else {
	            secondClicked = clickedButton; // Second button selection of the pair
	            checkMatch();
	        }
		}
		
		/*
		 * Purpose: Checks to see if the first and second button the user pressed have the same values.
		 */
		public void checkMatch() {
			int attachedValue1 = (int) firstClicked.getClientProperty("myValueKey");
			int attachedValue2 = (int) secondClicked.getClientProperty("myValueKey");
			
			// match found
			if(attachedValue1 == attachedValue2) {
				firstClicked.setEnabled(false);
				secondClicked.setEnabled(false);
				firstClicked.setText("");
	            secondClicked.setText("");
	            
	            resetSelection();
	            
	            pairsFound++;
	            updateScore();
	            
	            if (pairsFound == 4) {
	                resetGame();
	            }
			} else {
	            // MISMATCH: Hide values again after a brief 700ms delay
	            isChecking = true;
	            Timer timer = new Timer(700, new ActionListener() {
	                @Override
	                public void actionPerformed(ActionEvent e) {
	                    firstClicked.setText("Click Here");
	                    secondClicked.setText("Click Here");
	                    resetSelection();
	                    isChecking = false;   
	                }
	            });
	            timer.setRepeats(false); // Run only once
	            timer.start();
	        }
		}
		
		/*
		 * Purpose: Reset the first and second buttons the user clicked to nothing
		 */
		public void resetSelection() {
			firstClicked = null;
			secondClicked = null;
		}
		
		/*
		 * Purpose: Updates the score by 1 with every successful match
		 */
		public void updateScore() {
			userScore += 1;
			scoreLabel.setText("Score: " + userScore);
		} 
		
		
		/*
		 * Purpose: Completely resets the game. Happens when player clicks "New Game"
		 */
		public void resetGameCompletely() {
			isChecking = false; // Prevents spam clicking during delays
			pairsFound = 0;
			userScore = 0;
			scoreLabel.setText("Score: " + userScore);
			resetSelection();
			
			for(int i = 0; i < allButtons.size(); i++) {
				JButton temps = allButtons.get(i);
				temps.setEnabled(false);
				temps.setText("Click Here");
			}

			startButton.setEnabled(true);
			resetButton.setEnabled(false);
			numAssigning();
		}
		
		/*
		 * Purpose: Resets variables mid-game. Only called on when the player has matched all values in the current board.
		 */
		public void resetGame() {
			isChecking = false; // Prevents spam clicking during delays
			pairsFound = 0;
			resetSelection();
			
			for(int i = 0; i < allButtons.size(); i++) {
				JButton temps = allButtons.get(i);
				temps.setEnabled(true);
				temps.setText("Click Here");
			}
			numAssigning();
		}
		
} // end of ButtonMatching
