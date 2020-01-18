package calculator.ui;

import java.applet.Applet;
import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.TextArea;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.StringTokenizer;

import calculator.util.ExpressionToken;
import calculator.util.Node;
import calculator.util.StackImpl;

/**
 * Allows the user to input an expression containing numbers and + - * /(), and attempts to evaluate it.
 * 
 */
public class CalculatorApplet extends Applet implements ActionListener {

	private static final long serialVersionUID = 1863977032809026351L;

	TextFieldPanel userInput;
	TextArea results;
	Button calculate;
	boolean inputError; // flag to see if input is valid

	Node infixHead, infixTail; // sentinels for linked list of
	// infix expression's components

	final String[] SYMBOLS = { " ", "(", " + ", " - ", " * ", " / ", ")", " =" };

	final double OPENPAR = 1; // constants to make code more
	final double PLUS = 2; // readable :-)
	final double MINUS = 3;
	final double MULTIPLY = 4;
	final double DIVIDE = 5;
	final double CLOSEPAR = 6;
	final double EQUALS = 7;

	////////////////////////////////////// Action Listener Methods

	public void actionPerformed(ActionEvent e)
	// called whenever the button is hit...
	{
		calculateNow();
	}

	////////////////////////////////////// Calculation setup Methods
	///////// These take the user input and convert it to a doubly linked list
	///////// of EquationComponent's - one node for each operator and operand

	public boolean validCharacters(String dataIn)
	// Makes sure that only numbers, spaces and operators are entered.
	// Input : the expression to be checked.
	// Output: TRUE if everything's OK, FALSE if there is illegal input
	{
		StringTokenizer tester = new StringTokenizer(dataIn, " .0123456789()+-*/=");
		if (tester.countTokens() == 0) {
			return true;
		} else { // provide some visual feedback on the problem
			String s = "Invalid data : ";
			while (tester.hasMoreTokens()) {
				s += "\"" + tester.nextToken() + "\"";
				if (tester.hasMoreTokens())
					s += ", ";
			}
			setError(s);
			return false;
		}
	}

	public void insertComponent(boolean op, double value, Node node)
	// Creates a new EquationComponent object with parameters (op,value)
	// and inserts it in front of node.
	// Inputs : true if the component is an operator, component value,
	// node to insert in front of.
	{
		Node thisNode = new Node(new ExpressionToken(op, value), node, node.getPrev());
		node.getPrev().setNext(thisNode);
		node.setPrev(thisNode);
	}

	public boolean makeInfixList(String s, Node infixHead, Node infixTail)
	// Breaks up the input expression s into operators and operands, and
	// stores them in a double linked list, bounded by infixHead.
	// Return TRUE if successful, FALSE is an invalid number is found.
	{
		// EquationComponent comp = new EquationComponent();
		// Node currNode;
		String currToken;
		StringTokenizer tester = new StringTokenizer(s, "(+-*/)= ", true);
		while (tester.hasMoreTokens()) {
			currToken = tester.nextToken();
			switch (currToken.charAt(0)) {
			case ' ':
				break;
			case '(':
				insertComponent(true, OPENPAR, infixTail);
				break;
			case '+':
				insertComponent(true, PLUS, infixTail);
				break;
			case '-':
				insertComponent(true, MINUS, infixTail);
				break;
			case '*':
				insertComponent(true, MULTIPLY, infixTail);
				break;
			case '/':
				insertComponent(true, DIVIDE, infixTail);
				break;
			case ')':
				insertComponent(true, CLOSEPAR, infixTail);
				break;
			case '=':
				insertComponent(true, EQUALS, infixTail);
				break;
			default:
				try {
					insertComponent(false, Double.valueOf(currToken).doubleValue(), infixTail);
				} catch (NumberFormatException e) {
					setError("Invalid number detected");
					return false;
				}
				break;
			}

		}

		return true;
	}

	////////////////////////////////////// Visual Feedback

	public void setError(String S)
	// Displays the String S in the results box and sets the error flag
	{
		results.append(S + "\n");
		inputError = true;
	}

	public void setMessage(String S)
	// Displays the String S in the results box
	{
		results.append(S + "\n");
	}

	////////////////////////////////////// Calculation Checker
	///////// Ensures that the calculation is in a standard form.
	///////// Any deviations from the rule generate fatal errors or warnings,
	///////// but are corrected if possible
	///////// The rules are :
	///////// * There is exctly one = sign, and that is at the end
	///////// * All operands are seperated by at least one operator
	///////// * An expression like 6(7+5) is assumed to mean 6*(7+5)
	///////// * an expression like -7 at the start becomes (0-7)
	///////// * There are the same number of ('s as )'s
	///////// * There are no two consecutive operators, except for :
	///////// > 5*-6 is assumed to mean 5*(0-6)
	///////// > )( is converted to )*(
	///////// > a ( following any other operator is allowed
	///////// > any operator following a ) is allowed

	public void checkConsecutiveNumbers(ExpressionToken thisComp, ExpressionToken lastComp)
	// two consecutive numbers generates an error. e.g. 7+ 7 4 -5=
	{
		if ((!thisComp.isOperator()) && (!lastComp.isOperator())) {
			setError("There are two numbers not seperated by an operator");
		}
	}

	public void checkMissingTimes(ExpressionToken thisComp, ExpressionToken lastComp, Node thisNode)
	// convert an expression like 757(5+6) to 757*(5+6)
	{
		if ((thisComp.isOperator()) && (thisComp.getValue() == OPENPAR) && (!lastComp.isOperator())) {
			setMessage("Missing operator before (");
			insertComponent(true, MULTIPLY, thisNode);
		}
	}

	public int checkConsecutiveOperators(ExpressionToken thisComp, ExpressionToken lastComp, Node thisNode, int brackets) {
		// * There can be no two consecutive operators, except for :
		// > 5*-6 is assumed to mean 5*(0-6)
		// > )( is converted to )*(
		// > a ( following any other operator is allowed
		// > any operator following a ) is allowed
		if (thisComp.isOperator() && lastComp.isOperator()) { // we have two consecutive operators
			if (thisComp.getValue() != OPENPAR) { // and the second isn't a (
				if (lastComp.getValue() != CLOSEPAR) { // nor is the first a )
					if (thisComp.getValue() == EQUALS)
						setError("Missing value before =");
					else {
						ExpressionToken nextComp = (ExpressionToken) thisNode.getNext().getElement();
						if ((!nextComp.isOperator()) && (thisComp.getValue() == MINUS) && ((lastComp.getValue() == MULTIPLY) || (lastComp.getValue() == DIVIDE) || (lastComp.getValue() == OPENPAR))) { // the second was a -, the first a *, /, or (
																																																		// convert *-7, /-7, (-7 to *(0-7),/(0-7),((0-7)
							setMessage("Assuming " + SYMBOLS[(int) lastComp.getValue()] + " - " + Double.toString(nextComp.getValue()) + "means" + SYMBOLS[(int) lastComp.getValue()] + "(0 - " + Double.toString(nextComp.getValue()) + ")");
							// e.g. "Assuming * - 6 means *(0-6)"
							// now adjust the linked list
							insertComponent(true, OPENPAR, thisNode);
							brackets++;
							insertComponent(false, 0, thisNode);
							insertComponent(true, CLOSEPAR, thisNode.getNext().getNext());
						} else {
							setError("Too many consecutive operators : " + SYMBOLS[(int) lastComp.getValue()] + SYMBOLS[(int) thisComp.getValue()]);
						}
					}
				}
			} else
			// convert (7-8)(9-5) to (7-8)*(9-5)
			if (lastComp.getValue() == CLOSEPAR)
				insertComponent(true, MULTIPLY, thisNode);
		}
		return brackets;
	}

	public int checkBrackets(ExpressionToken thisComp, int brackets) {
		// keep track of () pairs. thisComp is the current equation
		// component, and brackets is the number of open () sets. returns
		// the new value of open () sets.
		if (thisComp.isOperator() && (thisComp.getValue() == OPENPAR))
			brackets++;
		if (thisComp.isOperator() && (thisComp.getValue() == CLOSEPAR)) {
			brackets--;
			if (brackets == -1) {
				setError("Too many ) symbols");
			}
		}
		return brackets;
	}

	public void checkEquals(ExpressionToken thisComp, Node thisNode, Node tail) {
		// equals can only be the last node... thisComp is the current
		// EquationComponent, thisNode is the node in the Linked list which
		// holds thisComp, and tail is the end sentinel of the linked list.
		if (thisComp.isOperator() && (thisComp.getValue() == EQUALS) && (thisNode.getNext() != tail)) {
			setError("= may only appear at the end");
		}
	}

	public int checkStart(ExpressionToken thisComp, Node thisNode, int brackets)
	// an operator may only appear at the start if
	// > it is a (
	// > it is a - followed by a number or a (
	{
		ExpressionToken nextComp = (ExpressionToken) thisNode.getNext().getElement();
		if (thisComp.isOperator())
			if (thisComp.getValue() == MINUS) {
				if (!nextComp.isOperator()) {
					setMessage("Assuming  - " + Double.toString(nextComp.getValue()) + " means (0 - " + Double.toString(nextComp.getValue()) + ")");
					insertComponent(true, OPENPAR, thisNode);
					brackets++;
					insertComponent(false, 0, thisNode);
					insertComponent(true, CLOSEPAR, thisNode.getNext().getNext());
				} else if (nextComp.getValue() == OPENPAR)
					insertComponent(false, 0, thisNode);
				else
					setError("Too many consecutive operators : -" + SYMBOLS[(int) nextComp.getValue()]);
			} else if (thisComp.getValue() == OPENPAR)
				brackets++;
			else {
				setError("a number must appear at the start.");
			}
		return brackets;
	}

	public void standardiseEquation(Node head, Node tail)
	// this does all the calling of the above methods... head and tail
	// are the sentinel boundaries of the linked list containing the
	// infix expression.
	{
		int brackets = 0;
		Node thisNode = head.getNext();

		ExpressionToken thisComp;
		ExpressionToken lastComp;

		if (thisNode == tail)
			setError("Nothing to Calculate!");
		else {
			thisComp = (ExpressionToken) tail.getPrev().getElement();

			// put an = at the end if it doesn't have one...
			if (!((thisComp.isOperator()) && (thisComp.getValue() == EQUALS)))
				insertComponent(true, EQUALS, tail);

			while (thisNode != tail) {
				thisComp = (ExpressionToken) thisNode.getElement();
				if (thisNode.getPrev() != head) {
					lastComp = (ExpressionToken) thisNode.getPrev().getElement();
					checkConsecutiveNumbers(thisComp, lastComp);
					checkMissingTimes(thisComp, lastComp, thisNode);
					brackets = checkConsecutiveOperators(thisComp, lastComp, thisNode, brackets);
					brackets = checkBrackets(thisComp, brackets);
					checkEquals(thisComp, thisNode, tail);
				} else {
					brackets = checkStart(thisComp, thisNode, brackets);
				}
				thisNode = thisNode.getNext();
			}
			if (brackets != 0) {
				setMessage("Warning : Missing )");
				for (int cnt = 0; cnt < brackets; cnt++)
					insertComponent(true, CLOSEPAR, tail.getPrev());
			}
		}
	}

	////////////////////////////////////// Calculation Set Up

	public boolean setupCalculation() {
		inputError = false;
		String dataIn = userInput.field.getText();
		results.setText("You entered : " + dataIn + "\n");
		if (validCharacters(dataIn)) {
			infixHead = new Node(null, null, null);
			infixTail = new Node(null, null, infixHead);
			infixHead.setNext(infixTail);
			if (makeInfixList(dataIn, infixHead, infixTail))
				standardiseEquation(infixHead, infixTail);
			else
				setError("Invalid number entered");

			if (inputError)
				setMessage("Could not perform Calculation.");
			else {
				results.append("Interpreted as : ");
				Node thisNode = infixHead.getNext();
				ExpressionToken eq;
				while (thisNode != infixTail) {
					eq = (ExpressionToken) thisNode.getElement();
					if (eq.isOperator())
						results.append(SYMBOLS[(int) eq.getValue()]);
					else
						results.append(Double.toString(eq.getValue()));
					thisNode = thisNode.getNext();
				}
				results.append("\n");
			}
		}
		return (!inputError);
	}

	////////////////////////////////////// Stack Popping
	//// these stacks only pop objects... we want to use primitive doubles
	//// These methods pop the object, assumed to be a Double, and convert it.

	public double popDouble(StackImpl S) {
		Double result = (Double) S.pop();
		return result.doubleValue();
	}

	public double topDouble(StackImpl S) {
		Double result = (Double) S.top();
		return result.doubleValue();
	}

	////////////////////////////////////// Calculating Methods
	////////// Does the actual calculating

	public void DoTopCalculation(StackImpl operators, StackImpl operands)
	// Inputs: the stack of operators, and the stack of operands.
	// Pops off the top two operands, and the top operator. Performs
	// the required calculation and pushes the answer as a new operand.
	{
		double value2 = popDouble(operands);
		double value1 = popDouble(operands);
		double op = popDouble(operators);
		double result = 1;
		switch ((int) op) {
		case (int) PLUS:
			result = value1 + value2;
			break;
		case (int) MINUS:
			result = value1 - value2;
			break;
		case (int) MULTIPLY:
			result = value1 * value2;
			break;
		case (int) DIVIDE:
			result = value1 / value2;
			break;
		}
		operands.push(new Double(result));
	}

	public void handleOperator(double op, StackImpl operators, StackImpl operands)
	// called when an operator, op, is encountered in the list and
	// decides what to do, based on the...
	//
	// Calculation Rules
	// -----------------
	// a ( is placed at the start
	// ( is always pushed
	// * and / cause a calculation if another * or / precedes
	// + and - cause a calculations until a ( precedes
	// ) forces a calculation and removes the (
	// equals is interpreted as a )
	{
		switch ((int) op) {
		case (int) OPENPAR:
			operators.push(new Double(OPENPAR));
			break;
		case (int) MULTIPLY:
			;
		case (int) DIVIDE:
			if ((topDouble(operators) == MULTIPLY) || (topDouble(operators) == DIVIDE))
				DoTopCalculation(operators, operands);
			operators.push(new Double(op));
			break;
		case (int) PLUS:
			;
		case (int) MINUS:
			while (topDouble(operators) != OPENPAR)
				DoTopCalculation(operators, operands);
			operators.push(new Double(op));
			break;
		case (int) CLOSEPAR:
			;
		case (int) EQUALS:
			while (topDouble(operators) != OPENPAR)
				DoTopCalculation(operators, operands);
			operators.pop();
			break;
		}

	}

	public void calculateNow()
	// called whenever the button is hit. Causes an attempt to evaluate
	// the input field, with feedback being in the textarea box.
	{
		if (setupCalculation()) { // a valid expression was entered into the list
			StackImpl operators = new StackImpl();
			StackImpl operands = new StackImpl();
			Node thisNode;
			ExpressionToken thisComp;

			// For simplicity, a ( is placed at the start, and
			// the = is treated as the matching )
			operators.push(new Double(OPENPAR));

			thisNode = infixHead.getNext(); // initial position

			while (thisNode != infixTail) // from start to end...
			{
				thisComp = (ExpressionToken) thisNode.getElement();
				if (thisComp.isOperator()) { // is was an operator
					handleOperator(thisComp.getValue(), operators, operands);
				} else { // this one was an operand
					operands.push(new Double(thisComp.getValue()));
				}
				thisNode = thisNode.getNext(); // next item
			}
			Double answer = (Double) operands.pop();
			if (answer.isNaN())
				results.append("Cannot determine result due to invalid calculations.");
			else
				results.append("result is : " + (answer.toString()));
		}
		userInput.field.requestFocus();
		userInput.field.selectAll();
	}

	////////////////////////////////////// Constructor
	public void init() {
		userInput = new TextFieldPanel("Type your calculation here :", 30);
		calculate = new Button(" Calculate Now ");
		setLayout(new BorderLayout());
		add(userInput, "North");
		results = new TextArea("", 5, 40, TextArea.SCROLLBARS_VERTICAL_ONLY);
		add(calculate, "Center");
		add(results, "South");
		calculate.addActionListener(this);
		results.setEditable(false);
	}

	////////////////////////////////////// Called when the class is run...

}
