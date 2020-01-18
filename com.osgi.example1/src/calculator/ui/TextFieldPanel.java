package calculator.ui;

import java.awt.BorderLayout;
import java.awt.Label;
import java.awt.Panel;
import java.awt.TextField;

public class TextFieldPanel extends Panel {

	private static final long serialVersionUID = -2812857170342034856L;

	protected Label label;
	protected TextField field;

	/**
	 * 
	 * @param prompt
	 * @param width
	 */
	public TextFieldPanel(String prompt, int width) {
		setLayout(new BorderLayout());

		this.label = new Label(prompt);
		this.field = new TextField(width);

		add(label, "West");
		add(field, "Center");
	}

}
