package calculator.util;

import java.util.StringTokenizer;

public class Calculator {

	public static interface CalculatorCallback {
		void addResult(String message);
	}

	public static final double LEFT_PARENTHESE = 1;
	public static final double RIGHT_PARENTHESE = 6;
	public static final double PLUS = 2;
	public static final double MINUS = 3;
	public static final double MULTIPLY = 4;
	public static final double DIVIDE = 5;
	public static final double EQUALS = 7;
	public static final String[] SYMBOLS = { " ", "(", " + ", " - ", " * ", " / ", ")", " =" };

	protected CalculatorCallback feedback;
	protected boolean isValid;

	/**
	 * 
	 * @param feedback
	 */
	public Calculator(CalculatorCallback feedback) {
		this.feedback = feedback;
	}

	/**
	 * 
	 * @param message
	 */
	protected void addResult(String message) {
		this.feedback.addResult(message);
	}

	/**
	 * 
	 * @param expression
	 */
	public void calculate(String expression) {
		this.isValid = false;

		Node head = null;
		Node tail = null;

		if (validateExpression(expression)) {
			head = new Node(null, null, null);
			tail = new Node(null, null, head);
			head.setNext(tail);

			this.isValid = parseExpression(expression, head, tail);
			if (this.isValid) {
				standardizeExpression(head, tail);

				if (this.isValid) {
					addResult("Interpreted as : ");

					Node currNode = head.getNext();
					ExpressionToken unit;
					while (currNode != tail) {
						unit = (ExpressionToken) currNode.getElement();
						if (unit.isOperator()) {
							addResult(SYMBOLS[(int) unit.getValue()]);
						} else {
							addResult(Double.toString(unit.getValue()));
						}
						currNode = currNode.getNext();
					}
					addResult("\n");
				}
			}
		}
		if (!this.isValid) {
			return;
		}

		Stack operators = new StackImpl();
		Stack operands = new StackImpl();
		// a ( is placed at the start, and the = is treated as the matching )
		operators.push(new Double(LEFT_PARENTHESE));

		// initial node
		Node currNode = head.getNext();

		// from head to tail
		ExpressionToken currToken;
		while (currNode != tail) {
			currToken = (ExpressionToken) currNode.getElement();

			if (currToken.isOperator()) {
				// operator
				calculate(currToken.getValue(), operators, operands);

			} else {
				// operand
				operands.push(new Double(currToken.getValue()));
			}

			currNode = currNode.getNext();
		}

		Double answer = (Double) operands.pop();
		if (answer.isNaN()) {
			// Cannot determine result due to invalid calculations.
			addResult("Error");
		} else {
			addResult("result is : " + (answer.toString()));
		}
	}

	/**
	 * 
	 * @param expression
	 */
	public boolean validateExpression(String expression) {
		StringTokenizer itor = new StringTokenizer(expression, " .0123456789()+-*/=");
		if (itor.countTokens() == 0) {
			return true;

		} else {
			String message = "Invalid data : ";
			while (itor.hasMoreTokens()) {
				message += "\"" + itor.nextToken() + "\"";
				if (itor.hasMoreTokens()) {
					message += ", ";
				}
			}
			setError(message);
			return false;
		}
	}

	/**
	 * Parse the input expression into operators and operands. Stores them in a double linked list,
	 * 
	 * @param input
	 * @param head
	 * @param tail
	 * @return Return true if successful. Return false if an invalid number is found.
	 */
	public boolean parseExpression(String input, Node head, Node tail) {
		StringTokenizer itor = new StringTokenizer(input, "(+-*/)= ", true);

		String currToken;
		while (itor.hasMoreTokens()) {
			currToken = itor.nextToken();

			switch (currToken.charAt(0)) {
			case ' ':
				break;

			case '(':
				insertExpressionToken(true, LEFT_PARENTHESE, tail);
				break;

			case '+':
				insertExpressionToken(true, PLUS, tail);
				break;

			case '-':
				insertExpressionToken(true, MINUS, tail);
				break;

			case '*':
				insertExpressionToken(true, MULTIPLY, tail);
				break;

			case '/':
				insertExpressionToken(true, DIVIDE, tail);
				break;

			case ')':
				insertExpressionToken(true, RIGHT_PARENTHESE, tail);
				break;

			case '=':
				insertExpressionToken(true, EQUALS, tail);
				break;

			default:
				try {
					insertExpressionToken(false, Double.valueOf(currToken).doubleValue(), tail);

				} catch (NumberFormatException e) {
					setError("Invalid number detected");
					return false;
				}
				break;
			}
		}
		return true;
	}

	/**
	 * this does all the calling of the above methods... head and tail are the sentinel boundaries of the linked list containing the infix expression.
	 * 
	 * @param head
	 * @param tail
	 */
	public void standardizeExpression(Node head, Node tail) {
		int brackets = 0;
		Node currNode = head.getNext();

		ExpressionToken currUnit;
		ExpressionToken lastUnit;

		if (currNode == tail) {
			setError("Expression is empty.");

		} else {
			currUnit = (ExpressionToken) tail.getPrev().getElement();

			// put an = at the end if it doesn't have one...
			if (!((currUnit.isOperator()) && (currUnit.getValue() == EQUALS))) {
				insertExpressionToken(true, EQUALS, tail);
			}

			while (currNode != tail) {
				currUnit = (ExpressionToken) currNode.getElement();
				if (currNode.getPrev() != head) {
					lastUnit = (ExpressionToken) currNode.getPrev().getElement();

					checkConsecutiveNumbers(currUnit, lastUnit);
					checkMissingTimes(currUnit, lastUnit, currNode);

					brackets = checkConsecutiveOperators(currUnit, lastUnit, currNode, brackets);
					brackets = checkBrackets(currUnit, brackets);
					checkEquals(currUnit, currNode, tail);

				} else {
					brackets = checkStart(currUnit, currNode, brackets);
				}
				currNode = currNode.getNext();
			}

			if (brackets != 0) {
				setMessage("Warning : Missing )");
				for (int cnt = 0; cnt < brackets; cnt++) {
					insertExpressionToken(true, RIGHT_PARENTHESE, tail.getPrev());
				}
			}
		}
	}

	// Calculation Checker
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

	/**
	 * two consecutive numbers generates an error. e.g. 7+ 7 4 -5=
	 * 
	 * @param currComp
	 * @param lastComp
	 */
	public void checkConsecutiveNumbers(ExpressionToken currComp, ExpressionToken lastComp) {
		if ((!currComp.isOperator()) && (!lastComp.isOperator())) {
			setError("There are two numbers not seperated by an operator");
		}
	}

	/**
	 * convert an expression like 757(5+6) to 757*(5+6)
	 * 
	 * @param thisComp
	 * @param lastComp
	 * @param thisNode
	 */
	public void checkMissingTimes(ExpressionToken thisComp, ExpressionToken lastComp, Node thisNode) {
		if ((thisComp.isOperator()) && (thisComp.getValue() == LEFT_PARENTHESE) && (!lastComp.isOperator())) {
			setMessage("Missing operator before (");
			insertExpressionToken(true, MULTIPLY, thisNode);
		}
	}

	/*-
	 * * There can be no two consecutive operators, except for :
	 * > 5*-6 is assumed to mean 5*(0-6)
	 * > )( is converted to )*(
	 * > a ( following any other operator is allowed
	 * > any operator following a ) is allowed
	 */
	/**
	 * @param currUnit
	 * @param lastComp
	 * @param thisNode
	 * @param brackets
	 * @return
	 */
	public int checkConsecutiveOperators(ExpressionToken currUnit, ExpressionToken lastUnit, Node currNode, int brackets) {
		// we have two consecutive operators
		if (currUnit.isOperator() && lastUnit.isOperator()) {

			// and the second isn't a (
			if (currUnit.getValue() != LEFT_PARENTHESE) {

				// nor is the first a )
				if (lastUnit.getValue() != RIGHT_PARENTHESE) {

					if (currUnit.getValue() == EQUALS) {
						setError("Missing value before =");

					} else {
						ExpressionToken nextComp = (ExpressionToken) currNode.getNext().getElement();
						if ((!nextComp.isOperator()) //
								&& (currUnit.getValue() == MINUS) //
								&& ((lastUnit.getValue() == MULTIPLY) || (lastUnit.getValue() == DIVIDE) || (lastUnit.getValue() == LEFT_PARENTHESE))) {
							// the second was a -, the first a *, /, or (convert *-7, /-7, (-7 to *(0-7),/(0-7),((0-7)
							setMessage("Assuming " + SYMBOLS[(int) lastUnit.getValue()] + " - " + Double.toString(nextComp.getValue()) + "means" + SYMBOLS[(int) lastUnit.getValue()] + "(0 - " + Double.toString(nextComp.getValue()) + ")");

							// e.g. "Assuming * - 6 means *(0-6)"
							// now adjust the linked list
							insertExpressionToken(true, LEFT_PARENTHESE, currNode);
							brackets++;

							insertExpressionToken(false, 0, currNode);
							insertExpressionToken(true, RIGHT_PARENTHESE, currNode.getNext().getNext());

						} else {
							setError("Too many consecutive operators : " + SYMBOLS[(int) lastUnit.getValue()] + SYMBOLS[(int) currUnit.getValue()]);
						}
					}
				}

			} else if (lastUnit.getValue() == RIGHT_PARENTHESE) {
				// convert (7-8)(9-5) to (7-8)*(9-5)
				insertExpressionToken(true, MULTIPLY, currNode);
			}
		}
		return brackets;
	}

	/**
	 * keep track of () pairs. thisComp is the current equation component, and brackets is the number of open () sets. returns the new value of open () sets.
	 * 
	 * @param unit
	 * @param brackets
	 * @return
	 */
	public int checkBrackets(ExpressionToken unit, int brackets) {
		if (unit.isOperator() && (unit.getValue() == LEFT_PARENTHESE)) {
			brackets++;
		}
		if (unit.isOperator() && (unit.getValue() == RIGHT_PARENTHESE)) {
			brackets--;
			if (brackets == -1) {
				setError("Too many ) symbols");
			}
		}
		return brackets;
	}

	/**
	 * equals can only be the last node... unit is the current EquationComponent, thisNode is the node in the Linked list which holds thisComp, and tail is the end sentinel of the linked list.
	 * 
	 * @param unit
	 * @param node
	 * @param tail
	 */
	public void checkEquals(ExpressionToken unit, Node node, Node tail) {
		if (unit.isOperator() && (unit.getValue() == EQUALS) && (node.getNext() != tail)) {
			setError("= may only appear at the end");
		}
	}

	/*-
	 * an operator may only appear at the start if
	 * > it is a (
	 * > it is a - followed by a number or a (
	 */
	/**
	 * @param thisComp
	 * @param thisNode
	 * @param brackets
	 * @return
	 */
	public int checkStart(ExpressionToken unit, Node node, int brackets) {
		ExpressionToken nextUnit = (ExpressionToken) node.getNext().getElement();
		if (unit.isOperator()) {
			if (unit.getValue() == MINUS) {
				if (!nextUnit.isOperator()) {
					setMessage("Assuming  - " + Double.toString(nextUnit.getValue()) + " means (0 - " + Double.toString(nextUnit.getValue()) + ")");
					insertExpressionToken(true, LEFT_PARENTHESE, node);
					brackets++;
					insertExpressionToken(false, 0, node);
					insertExpressionToken(true, RIGHT_PARENTHESE, node.getNext().getNext());

				} else if (nextUnit.getValue() == LEFT_PARENTHESE) {
					insertExpressionToken(false, 0, node);

				} else {
					setError("Too many consecutive operators : -" + SYMBOLS[(int) nextUnit.getValue()]);
				}
			} else if (unit.getValue() == LEFT_PARENTHESE) {
				brackets++;
			} else {
				setError("a number must appear at the start.");
			}
		}
		return brackets;
	}

	/**
	 * Creates a new ExpressionToken and inserts it in front a node.
	 * 
	 * @param isOperator
	 *            true if the token is an operator
	 * @param value
	 *            component value
	 * @param node
	 *            node to insert in front of
	 */
	public void insertExpressionToken(boolean isOperator, double value, Node node) {
		Node newNode = new Node(new ExpressionToken(isOperator, value), node, node.getPrev());
		node.getPrev().setNext(newNode);
		node.setPrev(newNode);
	}

	/*-
	 * Calculation Rules
	 * -----------------
	 * a ( is placed at the start
	 * ( is always pushed
	 * * and / cause a calculation if another * or / precedes
	 * + and - cause a calculations until a ( precedes
	 * ) forces a calculation and removes the ( 
	 * equals is interpreted as a )
	 */

	/**
	 * Called when an operator is encountered in the list and decides what to do, based on the rules.
	 * 
	 * @param operator
	 * @param operators
	 * @param operands
	 */
	public void calculate(double operator, Stack operators, Stack operands) {
		switch ((int) operator) {
		case (int) LEFT_PARENTHESE:
			operators.push(new Double(LEFT_PARENTHESE));
			break;
		case (int) MULTIPLY:
			;
		case (int) DIVIDE:
			if ((top(operators) == MULTIPLY) || (top(operators) == DIVIDE)) {
				topCalculation(operators, operands);
			}
			operators.push(new Double(operator));
			break;
		case (int) PLUS:
			;
		case (int) MINUS:
			while (top(operators) != LEFT_PARENTHESE) {
				topCalculation(operators, operands);
			}
			operators.push(new Double(operator));
			break;
		case (int) RIGHT_PARENTHESE:
			;
		case (int) EQUALS:
			while (top(operators) != LEFT_PARENTHESE) {
				topCalculation(operators, operands);
			}
			operators.pop();
			break;
		}
	}

	/**
	 * Pops off the top two operands, and the top operator. Performs the calculation and pushes the answer as a new operand.
	 * 
	 * @param operators
	 *            the stack of operators
	 * @param operands
	 *            the stack of operands.
	 */
	public void topCalculation(Stack operators, Stack operands) {
		double value2 = pop(operands);
		double value1 = pop(operands);
		double operator = pop(operators);

		double result = 1;
		switch ((int) operator) {
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

	/**
	 * 
	 * @param stack
	 * @return
	 */
	public double pop(Stack stack) {
		Double result = (Double) stack.pop();
		return result.doubleValue();
	}

	/**
	 * 
	 * @param stack
	 * @return
	 */
	public double top(Stack stack) {
		Double result = (Double) stack.top();
		return result.doubleValue();
	}

	/**
	 * @param message
	 */
	public void setError(String message) {
		addResult(message + "\n");
		this.isValid = false;
	}

	/**
	 * @param message
	 */
	public void setMessage(String message) {
		addResult(message + "\n");
	}

}
