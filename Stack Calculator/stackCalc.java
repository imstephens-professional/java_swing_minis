package lookhereNow;
/* Author Name: Isabella Stephens
 * Created: 3 June 2026
 * Updated: 6 June 2026
 * 
 * Description: Add later.
 */

/*
 * Imports
 */
import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.Box;
import java.awt.EventQueue;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import javax.swing.BoxLayout;

public class stackCalc implements ActionListener {
	// global variables
	private JFrame frame;
	private JButton submitButton, clearButton;
	private JButton pLeftBtn, pRightBtn, bLeftBtn, bRightBtn; // p=parentheses, b=brackets
	private JButton subBtn, addBtn, multBtn, divBtn, expBtn, remBtn, eqlBtn, varBtn;
	private JButton num1, num2, num3, num4, num5, num6, num7, num8, num9, per;
	String userCalculation = ""; // what will hold the calculation the user wants
	String userCompCalculation = "= "; // the output of the user's calculation
	String temp;
	private JLabel desiredCalculation, calculationOutput;
	private JPanel panelSub1, panelSub2, panelSub3, panelSub4, panelSub5, panelSub6, panelSub7, panelSub8;
	
	// calling class
	errorChecker calcBegin = new errorChecker();

	// main method
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					stackCalc window = new stackCalc();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			} // end of run()
		});
	} // end of main method

	public stackCalc() {
		initialize();
	}

	public void initialize() {
		// creates & sets the frame
		frame = new JFrame(); // creates new frame
		frame.setTitle("Stack Calculator");
		frame.setBounds(100, 100, 300, 400); // sets boundaries of frame (x, y, width, height)
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(new BorderLayout(0, 0));
		
		// Create & format the panel
		JPanel panel = new JPanel();
		frame.getContentPane().add(panel);
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
		panelSub1 = new JPanel(new FlowLayout());
		panelSub2 = new JPanel(new FlowLayout());
		panelSub3 = new JPanel(new FlowLayout());
		panelSub4 = new JPanel(new FlowLayout());
		panelSub5 = new JPanel(new FlowLayout());
		panelSub6 = new JPanel(new FlowLayout());
		panelSub7 = new JPanel(new FlowLayout());
		panelSub8 = new JPanel(new FlowLayout());
	     
		// where what the user types will appear
		desiredCalculation = new JLabel(userCalculation);
		calculationOutput = new JLabel(userCompCalculation);
		
		// add stringOutput to the first subpanel
		panel.add(Box.createHorizontalStrut(100));
	    panelSub1.add(desiredCalculation);
	    panel.add(panelSub1);
	    
	    panel.add(Box.createHorizontalStrut(100));
	    panelSub2.add(calculationOutput);
	    panel.add(panelSub2);
		
		/*
		 * OPERATORS
		 */
		addBtn = new JButton("+");
		addBtn.putClientProperty("myValueKey", "+");
		addBtn.addActionListener(this);
		subBtn = new JButton("-");
		subBtn.putClientProperty("myValueKey", "-");
		subBtn.addActionListener(this);
		multBtn = new JButton("*");
		multBtn.putClientProperty("myValueKey", "*");
		multBtn.addActionListener(this);
		divBtn = new JButton("/");
		divBtn.putClientProperty("myValueKey", "/");
		divBtn.addActionListener(this);
		remBtn = new JButton("%");
		remBtn.putClientProperty("myValueKey", "%");
		remBtn.addActionListener(this);
		expBtn = new JButton("^");
		expBtn.putClientProperty("myValueKey", "^");
		expBtn.addActionListener(this);
		eqlBtn = new JButton("=");
		eqlBtn.putClientProperty("myValueKey", "=");
		eqlBtn.addActionListener(this);
		varBtn = new JButton("Var");
		varBtn.putClientProperty("myValueKey", "x");
		varBtn.addActionListener(this);
		
		panel.add(Box.createHorizontalStrut(50));
	    panelSub3.add(addBtn);
	    panelSub3.add(subBtn);
	    panelSub3.add(multBtn);
	    panelSub3.add(divBtn);
	    panel.add(panelSub3);
	    
	    panel.add(Box.createHorizontalStrut(50));
	    panelSub4.add(remBtn);
	    panelSub4.add(expBtn);
	    panelSub4.add(eqlBtn);
	    panelSub4.add(varBtn);
	    panel.add(panelSub4);

		/*
		 * PARENTHESES & BRACKETS
		 */
		pLeftBtn = new JButton("(");
		pLeftBtn.putClientProperty("myValueKey", "(");
		pLeftBtn.addActionListener(this);
		pRightBtn = new JButton(")");
		pRightBtn.putClientProperty("myValueKey", ")");
		pRightBtn.addActionListener(this);
		bLeftBtn = new JButton("[");
		bLeftBtn.putClientProperty("myValueKey", "[");
		bLeftBtn.addActionListener(this);
		bRightBtn = new JButton("]");
		bRightBtn.putClientProperty("myValueKey", "]");
		bRightBtn.addActionListener(this);
		
		panel.add(Box.createHorizontalStrut(50));
		panelSub5.add(pLeftBtn);
		panelSub5.add(pRightBtn);
		panelSub5.add(bLeftBtn);
		panelSub5.add(bRightBtn);
		panel.add(panelSub5);

		/*
		 * 	NUMBERS & VARIABLE
		 */
		num1 = new JButton("1");
		num1.putClientProperty("myValueKey", "1");
		num1.addActionListener(this);
		num2 = new JButton("2");
		num2.putClientProperty("myValueKey", "2");
		num2.addActionListener(this);
		num3 = new JButton("3");
		num3.putClientProperty("myValueKey", "3");
		num3.addActionListener(this);
		num4 = new JButton("4");
		num4.putClientProperty("myValueKey", "4");
		num4.addActionListener(this);
		num5 = new JButton("5");
		num5.putClientProperty("myValueKey", "5");
		num5.addActionListener(this);
		num6 = new JButton("6");
		num6.putClientProperty("myValueKey", "6");
		num6.addActionListener(this);
		num7 = new JButton("7");
		num7.putClientProperty("myValueKey", "7");
		num7.addActionListener(this);
		num8 = new JButton("8");
		num8.putClientProperty("myValueKey", "8");
		num8.addActionListener(this);
		num9 = new JButton("9");
		num9.putClientProperty("myValueKey", "9");
		num9.addActionListener(this);
		per = new JButton("Period");
		per.putClientProperty("myValueKey", ".");
		per.addActionListener(this);

		panel.add(Box.createHorizontalStrut(100));
		panelSub6.add(num1);
		panelSub6.add(num2);
		panelSub6.add(num3);
		panelSub6.add(per);
		panel.add(panelSub6);
		
		panel.add(Box.createHorizontalStrut(100));
		panelSub7.add(num4);
		panelSub7.add(num5);
		panelSub7.add(num6);
		panel.add(panelSub7);
		
		panel.add(Box.createHorizontalStrut(100));
		panelSub8.add(num7);
		panelSub8.add(num8);
		panelSub8.add(num9);
		panel.add(panelSub8);
		
		/*
		 * OTHER BUTTONS
		 */
		clearButton = new JButton("Clear");
		clearButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// enable all buttons if they were previously enabled
				pLeftBtn.setEnabled(true); pRightBtn.setEnabled(true); bLeftBtn.setEnabled(true); bRightBtn.setEnabled(true);
				subBtn.setEnabled(true); addBtn.setEnabled(true); multBtn.setEnabled(true); divBtn.setEnabled(true); 
				expBtn.setEnabled(true); remBtn.setEnabled(true); eqlBtn.setEnabled(true); varBtn.setEnabled(true);
				num1.setEnabled(true); num2.setEnabled(true); num3.setEnabled(true); num4.setEnabled(true); num5.setEnabled(true); 
				num6.setEnabled(true); num7.setEnabled(true); num8.setEnabled(true); num9.setEnabled(true); per.setEnabled(true);
				submitButton.setEnabled(true);
				
				// 
				userCalculation = "";
				userCompCalculation = "= ";
				desiredCalculation.setText(userCalculation);
				calculationOutput.setText(userCompCalculation);
			}
		});

		submitButton = new JButton("Submit");
		submitButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// shutdown all the buttons so the user must click the clearButton before making another calculation
				pLeftBtn.setEnabled(false); pRightBtn.setEnabled(false); bLeftBtn.setEnabled(false); bRightBtn.setEnabled(false);
				subBtn.setEnabled(false); addBtn.setEnabled(false); multBtn.setEnabled(false); divBtn.setEnabled(false); 
				expBtn.setEnabled(false); remBtn.setEnabled(false); eqlBtn.setEnabled(false); varBtn.setEnabled(false);
				num1.setEnabled(false); num2.setEnabled(false); num3.setEnabled(false); num4.setEnabled(false); num5.setEnabled(false); 
				num6.setEnabled(false); num7.setEnabled(false); num8.setEnabled(false); num9.setEnabled(false); per.setEnabled(false);
				submitButton.setEnabled(false);
				
				// this is where the user calls their calculations.
				// starts with error checking
				userCompCalculation = calcBegin.processInput(userCalculation);
				
				// concat the userCompCalculation with the 
				calculationOutput.setText(userCompCalculation);
			}
		});
		
		panelSub7.add(clearButton);
		panelSub8.add(submitButton);

	}
	
	/*
	 * ActionListener for the operators, pare./brackets, period, numbers, & variables
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		JComponent source = (JComponent) e.getSource();
		temp = (String) source.getClientProperty("myValueKey");
		userCalculation = userCalculation.concat(temp);
		desiredCalculation.setText(userCalculation);
	}

} // end of stackCalc
