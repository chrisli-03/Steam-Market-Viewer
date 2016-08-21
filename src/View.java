import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.*;

public class View extends JPanel implements IView {
	private Model model;
	private Controller controller;
	private JButton addButton;
	private JButton searchButton;
	
	View (Model model, Controller controller) {
		this.model = model;
		this.controller = controller;
		addButton = new JButton("add button");
		searchButton = new JButton("search button");
		addButton.addActionListener(controller);
		searchButton.addActionListener(controller);
		initView();
	}
	
	private void initView() {
		this.setBackground(Color.WHITE);
		this.setLayout(new BorderLayout());

		add(addButton);
		add(searchButton);
	}
	
	@Override
	public void updateView() {
		// TODO Auto-generated method stub
		
	}
}
