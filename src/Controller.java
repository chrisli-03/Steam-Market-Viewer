import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;

import javax.swing.JButton;

class Controller implements ActionListener, ComponentListener {
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

	@Override
	public void componentHidden(ComponentEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void componentMoved(ComponentEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void componentResized(ComponentEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void componentShown(ComponentEvent e) {
		// TODO Auto-generated method stub
		
	}

}
