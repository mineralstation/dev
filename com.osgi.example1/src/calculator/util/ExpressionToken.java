package calculator.util;

public class ExpressionToken {

	protected boolean isOperator;
	protected double value;

	/**
	 * @param operator
	 * @param value
	 */
	public ExpressionToken(boolean operator, double value) {
		this.isOperator = operator;
		this.value = value;
	}

	public void setOperator(boolean isOperator) {
		this.isOperator = isOperator;
	}

	public boolean isOperator() {
		return this.isOperator;
	}

	public void setValue(double value) {
		this.value = value;
	}

	public double getValue() {
		return this.value;
	}

}
