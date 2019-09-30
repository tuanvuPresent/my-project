package view;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import javax.swing.JTextField;

public class HintTextField extends JTextField {

	/**
	 * 
	 */
	Font gainFont = new Font("Tahoma", Font.PLAIN, 11);
	Font lostFont = new Font("Tahoma", Font.ITALIC, 11);

	public HintTextField(final String hint) {

		setText(hint);
		setFont(lostFont);

		this.addFocusListener(new FocusAdapter() {

			@Override
			public void focusGained(FocusEvent e) {
				if (getText().equals(hint)) {
					setText("");
					setFont(gainFont);
				}
			}

			@Override
			public void focusLost(FocusEvent e) {
				if (getText().equals("")) {
					setText(hint);
					setFont(lostFont);
				}
			}
		});

	}
}
