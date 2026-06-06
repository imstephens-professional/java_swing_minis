package lookhereNow;
/* Author Name: Isabella Stephens
 * Created: 3 June 2026
 * Updated: 5 June 2026
 * 
 * Description: Add later.
 */

/*
 * Imports
 */
import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.Box;
import java.awt.EventQueue;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import javax.swing.BoxLayout;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.FormSpecs;
import com.jgoodies.forms.layout.RowSpec;
import java.awt.GridLayout;

public class stackCalc implements ActionListener {
	// global variables
	private JFrame frame;
	private JButton submitButton, clearButton;
	private JButton pLeftBtn, pRightBtn, bLeftBtn, bRightBtn; // p=parentheses, b=brackets
	private JButton subBtn, addBtn, multBtn, divBtn, expBtn, remBtn, eqlBtn, varBtn;
	private JButton num1, num2, num3, num4, num5, num6, num7, num8, num9;
	String userCalculation; // what will hold the calculation the user wants
	private JLabel stringOutput;
	private JPanel panelSub1, panelSub2, panelSub3, panelSub4, panelSub5, panelSub6, panelSub7, panelSub8;

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
		stringOutput = new JLabel("HI TEST");
		panel.add(stringOutput);
		
		// add stringOutput to the first subpanel
		panel.add(Box.createHorizontalStrut(100));
	    panelSub1.add(stringOutput);
	    panel.add(panelSub1);
		
		/*
		 * OPERATORS
		 */
		addBtn = new JButton("+");
		addBtn.addActionListener(this);

		subBtn = new JButton("-");
		subBtn.addActionListener(this);

		multBtn = new JButton("*");
		multBtn.addActionListener(this);

		divBtn = new JButton("/");
		divBtn.addActionListener(this);

		remBtn = new JButton("%");
		remBtn.addActionListener(this);

		expBtn = new JButton("^");
		expBtn.addActionListener(this);

		eqlBtn = new JButton("=");
		eqlBtn.addActionListener(this);
		
		varBtn = new JButton("Var");
		varBtn.addActionListener(this);
		
		panel.add(Box.createHorizontalStrut(50));
	    panelSub2.add(addBtn);
	    panelSub2.add(subBtn);
	    panelSub2.add(multBtn);
	    panelSub2.add(divBtn);
	    panel.add(panelSub2);
	    
	    panel.add(Box.createHorizontalStrut(50));
	    panelSub3.add(remBtn);
	    panelSub3.add(expBtn);
	    panelSub3.add(eqlBtn);
	    panelSub3.add(varBtn);
	    panel.add(panelSub3);

		/*
		 * PARENTHESES & BRACKETS
		 */
		pLeftBtn = new JButton("(");
		pLeftBtn.addActionListener(this);

		pRightBtn = new JButton(")");
		pRightBtn.addActionListener(this);

		bLeftBtn = new JButton("[");
		bLeftBtn.addActionListener(this);

		bRightBtn = new JButton("]");
		bRightBtn.addActionListener(this);
		
		panel.add(Box.createHorizontalStrut(50));
		panelSub4.add(pLeftBtn);
		panelSub4.add(pRightBtn);
		panelSub4.add(bLeftBtn);
		panelSub4.add(bRightBtn);
		panel.add(panelSub4);

		/*
		 * 	NUMBERS & VARIABLE
		 */
		num1 = new JButton("1");
		num1.addActionListener(this);

		num2 = new JButton("2");
		num2.addActionListener(this);

		num3 = new JButton("3");
		num3.addActionListener(this);

		num4 = new JButton("4");
		num4.addActionListener(this);

		num5 = new JButton("5");
		num5.addActionListener(this);

		num6 = new JButton("6");
		num6.addActionListener(this);

		num7 = new JButton("7");
		num7.addActionListener(this);

		num8 = new JButton("8");
		num8.addActionListener(this);

		num9 = new JButton("9");
		num9.addActionListener(this);

		panel.add(Box.createHorizontalStrut(100));
		panelSub5.add(num1);
		panelSub5.add(num2);
		panelSub5.add(num3);
		panel.add(panelSub5);
		
		panel.add(Box.createHorizontalStrut(100));
		panelSub6.add(num4);
		panelSub6.add(num5);
		panelSub6.add(num6);
		panel.add(panelSub6);
		
		panel.add(Box.createHorizontalStrut(100));
		panelSub7.add(num7);
		panelSub7.add(num8);
		panelSub7.add(num9);
		panel.add(panelSub7);
		
		/*
		 * OTHER BUTTONS
		 */

		clearButton = new JButton("Clear");
		clearButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});

		submitButton = new JButton("Submit");
		submitButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		
		panel.add(Box.createHorizontalStrut(50));
		panelSub8.add(clearButton);
		panelSub8.add(submitButton);
		panel.add(panelSub8);

	}
	
	/*
	 * ActionListener for the operators, pare./brackets, numbers, & variables
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		//
	}

} // end of stackCalc
