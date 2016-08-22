import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
<<<<<<< HEAD
import javax.swing.JOptionPane;
=======
>>>>>>> a9346966dbffad2cfb4c412d3d181dcbae4ae18b

class Controller implements ActionListener {
	private Model model;

	public Controller(Model model) {
		this.model = model;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
<<<<<<< HEAD
		Component frame = null;
		if (e.getSource() instanceof JButton) {
			Object source = e.getSource();
			JButton button = (JButton) source;
			if (button.getText() == "add button") {
				String item = model.getItemName();
				String game = model.getGameName();
				if (item != null && game != null) {
					System.out.println("add");
					model.addSteamItem(item, game);
				} else if(item == null ){
					JOptionPane.showMessageDialog(frame, "Please enter the correct item name.");
					
				}else if(game == null ){
					JOptionPane.showMessageDialog(frame, "Please enter the correct game name.");
					
				}else{
					JOptionPane.showMessageDialog(frame, "Error.");
				}
			}
		}
=======
		switch (((JButton)e.getSource()).getName()) {
			case "add button":
				String itemName = model.getItemNameFromView();
				String gameName = model.getGameNameFromView();
				model.addRequest(itemName, gameName);
				System.out.println(itemName + " " + gameName);
		}
	}
>>>>>>> a9346966dbffad2cfb4c412d3d181dcbae4ae18b

	}
}
