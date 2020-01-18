package calculator.ui;

////////////////////////////////////////////////////
//Calculator.java
//
//Written By Geoff Knagge (9806135)
//last modified 14/8/1999
//
//Allows the user to input an expression containing
//numbers and +-*/(), and attempts to evaluate it.
////////////////////////////////////////////////////
import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Frame;
import java.awt.TextArea;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import calculator.util.Calculator;
import calculator.util.Calculator.CalculatorCallback;

/**
 * http://www.geoffknagge.com/java/calc/Calculator.java
 */
public class CalculatorWindow extends Frame implements WindowListener {

	private static final long serialVersionUID = 1014443655126087838L;

	protected TextFieldPanel inputTextField;
	protected Button button;
	protected TextArea resultTextarea;

	public CalculatorWindow() {
		setLayout(new BorderLayout());

		this.inputTextField = new TextFieldPanel("Type your calculation here :", 30);
		this.button = new Button("Calculate");
		this.button.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				onClick();
			}
		});
		this.resultTextarea = new TextArea("", 5, 40, TextArea.SCROLLBARS_VERTICAL_ONLY);
		this.resultTextarea.setEditable(false);

		add(this.inputTextField, "North");
		add(this.button, "Center");
		add(this.resultTextarea, "South");

		addWindowListener(this);
	}

	public void onClick() {
		String input = this.inputTextField.field.getText();
		this.resultTextarea.setText("You entered : " + input + "\n");

		Calculator calc = new Calculator(new CalculatorCallback() {
			@Override
			public void addResult(String message) {
				resultTextarea.append(message);
			}
		});
		calc.calculate(input);

		this.inputTextField.field.requestFocus();
		this.inputTextField.field.selectAll();
	}

	/** implement WindowListener */
	public void windowClosing(WindowEvent e) {
		setVisible(false);
		System.exit(0);
	}

	@Override
	public void windowActivated(WindowEvent e) {
	}

	@Override
	public void windowClosed(WindowEvent e) {
	}

	@Override
	public void windowDeactivated(WindowEvent e) {
	}

	@Override
	public void windowDeiconified(WindowEvent e) {
	}

	@Override
	public void windowIconified(WindowEvent e) {
	}

	@Override
	public void windowOpened(WindowEvent e) {
	}

	public static void main(String[] args) {
		CalculatorWindow calcWindow = new CalculatorWindow();
		calcWindow.setTitle("Calculator");
		calcWindow.setSize(500, 200);
		calcWindow.setVisible(true);
	}

}
