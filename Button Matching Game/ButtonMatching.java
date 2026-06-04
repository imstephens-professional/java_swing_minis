package looksHeres;
/*
 * Author Name: Isabella Stephens
 * Created: 3 June 2026
 * Updated: 4 June 2026
 * 
 * Description: A Java button-matching game. The frame has 8 buttons with 4 matches. The player must click each match as fast as they
 * can.
 */

/*
 * Imports
 */
import java.awt.EventQueue;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.JLabel;


public class ButtonMatching {
	private JFrame frame;
	private JButton startButton, resetButton;
	private JButton match1;
	private JButton match2;
	private JButton match3;
	private JButton match4;
	private JButton match5;
	private JButton match6;
	private JButton match7;
	private JButton match8;
	private JLabel timerLabel;
	private JLabel scoreLabel;
	
	
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
		
		/*
		 * Purpose: Create the application
		 */
		public ButtonMatching() {
			initialize();
		}
		
		/*
		 * Purpose: 
		 */
		public void initialize() {
			// creates & sets the frame
			frame = new JFrame(); // creates new frame
			frame.setBounds(100, 100, 450, 300); // sets boundaries of frame (x, y, width, height)
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
			
			timerLabel = new JLabel("Timer: ");
			GridBagConstraints gbc_timerLabel = new GridBagConstraints();
			gbc_timerLabel.insets = new Insets(0, 0, 5, 5);
			gbc_timerLabel.gridx = 0;
			gbc_timerLabel.gridy = 0;
			panel.add(timerLabel, gbc_timerLabel);
			
			scoreLabel = new JLabel("Score: ");
			GridBagConstraints gbc_scoreLabel = new GridBagConstraints();
			gbc_scoreLabel.insets = new Insets(0, 0, 5, 0);
			gbc_scoreLabel.gridx = 11;
			gbc_scoreLabel.gridy = 0;
			panel.add(scoreLabel, gbc_scoreLabel);
			
			match1 = new JButton("\"\"");
			GridBagConstraints gbc_match1 = new GridBagConstraints();
			gbc_match1.insets = new Insets(0, 0, 5, 5);
			gbc_match1.gridx = 1;
			gbc_match1.gridy = 1;
			panel.add(match1, gbc_match1);
			
			match5 = new JButton("\"\"");
			GridBagConstraints gbc_match5 = new GridBagConstraints();
			gbc_match5.insets = new Insets(0, 0, 5, 0);
			gbc_match5.gridx = 11;
			gbc_match5.gridy = 1;
			panel.add(match5, gbc_match5);
			
			match2 = new JButton("\"\"");
			GridBagConstraints gbc_match2 = new GridBagConstraints();
			gbc_match2.insets = new Insets(0, 0, 5, 5);
			gbc_match2.gridx = 1;
			gbc_match2.gridy = 3;
			panel.add(match2, gbc_match2);
			
			match6 = new JButton("\"\"");
			GridBagConstraints gbc_match6 = new GridBagConstraints();
			gbc_match6.insets = new Insets(0, 0, 5, 0);
			gbc_match6.gridx = 11;
			gbc_match6.gridy = 3;
			panel.add(match6, gbc_match6);
			
			startButton = new JButton("Start");
			GridBagConstraints gbc_startButton = new GridBagConstraints();
			gbc_startButton.insets = new Insets(0, 0, 5, 5);
			gbc_startButton.gridx = 7;
			gbc_startButton.gridy = 4;
			panel.add(startButton, gbc_startButton);
			
			match3 = new JButton("\"\"");
			GridBagConstraints gbc_match3 = new GridBagConstraints();
			gbc_match3.insets = new Insets(0, 0, 5, 5);
			gbc_match3.gridx = 1;
			gbc_match3.gridy = 5;
			panel.add(match3, gbc_match3);
			
			resetButton = new JButton("Restart");
			GridBagConstraints gbc_resetButton = new GridBagConstraints();
			gbc_resetButton.insets = new Insets(0, 0, 5, 5);
			gbc_resetButton.gridx = 7;
			gbc_resetButton.gridy = 5;
			panel.add(resetButton, gbc_resetButton);
			
			match7 = new JButton("\"\"");
			GridBagConstraints gbc_match7 = new GridBagConstraints();
			gbc_match7.insets = new Insets(0, 0, 5, 0);
			gbc_match7.gridx = 11;
			gbc_match7.gridy = 5;
			panel.add(match7, gbc_match7);
			
			match4 = new JButton("\"\"");
			GridBagConstraints gbc_match4 = new GridBagConstraints();
			gbc_match4.insets = new Insets(0, 0, 0, 5);
			gbc_match4.gridx = 1;
			gbc_match4.gridy = 7;
			panel.add(match4, gbc_match4);
			
			match8 = new JButton("\"\"");
			GridBagConstraints gbc_match8 = new GridBagConstraints();
			gbc_match8.gridx = 11;
			gbc_match8.gridy = 7;
			panel.add(match8, gbc_match8);
			
			
			
			firstGame();
		}
		
		public void firstGame() {
			//
		}
		
		public void resetGame() {
			//
		}
} // end of ButtonMatching
