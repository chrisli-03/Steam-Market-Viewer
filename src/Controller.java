import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;

import javax.swing.JButton;

class Controller implements ActionListener {
	private Model model;
	public Controller(Model model) {
		this.model = model;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
			if (e.getSource() instanceof JButton) {
				Object source = e.getSource();
				JButton button = (JButton) source;
				System.out.println(button.getText());
			}
	}

}
