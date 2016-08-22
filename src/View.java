import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.util.ArrayList;

import javax.swing.*;

public class View extends JPanel implements IView {
	private Model model;
	private Controller controller;
	private JButton addButton;
	private JButton searchButton;
	private JPanel leftPanel;
	private JPanel centerPanel;
	private JPanel rightPanel;
	private JTextField itemName;
	private JTextField gameName;
	private JTextArea choosedItems;
	private JTextArea resultBox;
	private JScrollPane gameNameScroll;
	private JScrollPane resultBoxScroll;

	View(Model model, Controller controller) {
		this.model = model;
		this.controller = controller;
		
		addButton = new JButton("+");
		searchButton = new JButton("search");
		itemName = new JTextField();
		gameName = new JTextField();
		choosedItems = new JTextArea();
		resultBox = new JTextArea();
		gameNameScroll= new JScrollPane(choosedItems);
		resultBoxScroll= new JScrollPane(resultBox);
		
		addButton.addActionListener(controller);
		searchButton.addActionListener(controller);
		
		initView();
	}

	private void initView() {
		this.setBackground(Color.WHITE);
		this.setLayout(new BorderLayout());
		
		centerPanel = new JPanel();
		centerPanel.setLayout(new GridLayout(1, 2, 1, 1));
		rightPanel = new JPanel();
		rightPanel.setLayout(new GridLayout());
		choosedItems.setEditable(false);
		resultBox.setEditable(false);
		addButton.setName("add button");
		searchButton.setName("search button");
		
		leftPanel = new JPanel();
		leftPanel.setLayout(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();
		
		// Enter item label (0, 0)
		JLabel itemLabel = new JLabel("Enter Item: ");
		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.fill = GridBagConstraints.HORIZONTAL;
		leftPanel.add(itemLabel, gbc);
		
		// Item name textbox (0, 1)
		gbc.gridx = 1;
		gbc.gridy = 0;
		gbc.weightx = 1.0;
		leftPanel.add(itemName, gbc);
		
		// Enter game label (1, 0)
		JLabel gameLabel = new JLabel("Enter Game: ");
		gbc.gridx = 0;
		gbc.gridy = 1;
		gbc.weightx = 0;
		leftPanel.add(gameLabel, gbc);
		
		// Game name textbox (1, 1)
		gbc.gridx = 1;
		gbc.gridy = 1;
		gbc.weightx = 1.0;
		leftPanel.add(gameName, gbc);
		
		// Add button (2, 0)
		gbc.gridx = 2;
		gbc.gridy = 0;
		gbc.weightx = 0;
		gbc.fill = GridBagConstraints.NONE;
		gbc.gridheight = 2;
		leftPanel.add(addButton, gbc);
		
		// Search list label (0, 2)
		JLabel searchListLabel = new JLabel("items to search: ");
		gbc.gridheight = 1;
		gbc.gridx = 0;
		gbc.gridy = 2;
		leftPanel.add(searchListLabel, gbc);
		
		// Search list (0, 3)
		gbc.gridx = 0;
		gbc.gridy = 3;
		gbc.fill = GridBagConstraints.BOTH;
		gbc.gridheight = GridBagConstraints.RELATIVE;
		gbc.gridwidth = GridBagConstraints.REMAINDER;
		gbc.weightx = 1.0;
		gbc.weighty = 1.0;
		leftPanel.add(gameNameScroll, gbc);
		
		// Search button (0, 4)
		gbc.anchor = GridBagConstraints.LAST_LINE_END;
		gbc.weightx = 0;
		gbc.weighty = 0;
		gbc.gridx = 0;
		gbc.gridy = 4;
		leftPanel.add(searchButton, gbc);
		
		rightPanel.add(resultBoxScroll);
		
		centerPanel.add(leftPanel);
		centerPanel.add(rightPanel);
		
		add(centerPanel, BorderLayout.CENTER);
	}

	@Override
	public void updateView() {
		choosedItems.setText(null);
		ArrayList<Request> requestList = model.getRequestList();
		for (Request r : requestList) {
			choosedItems.append(r.getItemName()+"\n");
		}
		repaint();
	}
	
	public String getItemName() {
		return itemName.getText();
	}
	
	public String getGameName() {
		return gameName.getText();
	}
}