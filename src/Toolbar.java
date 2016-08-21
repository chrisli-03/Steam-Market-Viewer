import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JPanel;

public class Toolbar extends JPanel implements IView {
	private Model model;
	private Controller controller;
	
	private JButton filesButton;
	
	Toolbar(Model model, Controller controller) {
		this.model = model;
		this.controller = controller;
		this.setLayout(new FlowLayout(FlowLayout.LEFT));
		filesButton = new JButton("files button");
		filesButton.addActionListener(controller);
		add(filesButton);
	}

	@Override
	public void updateView() {
		// TODO Auto-generated method stub
		revalidate();
		repaint();
	}
}
