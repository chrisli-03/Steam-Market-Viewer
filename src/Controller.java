import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

class Controller implements ActionListener {
	private Model model;
	public Controller(Model model) {
		this.model = model;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		switch (((JButton)e.getSource()).getName()) {
			case "add button":
				String itemName = model.getItemNameFromView();
				String gameName = model.getGameNameFromView();
				model.addRequest(itemName, gameName);
		}
	}

}
