import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.im.InputContext;


import javax.swing.*;

public class View extends JPanel implements IView {
	private Model model;
	private Controller controller;
	private JButton addButton;
	private JButton searchButton;
	private JPanel leftPanel;
	private JPanel centerPanel;
	private JPanel rightPanel;
	private JLabel itemNameLabel;
	private JLabel gameNameLabel;
	private JTextField itemName;
	private JTextField gameName;
	private JTable choosedItems;
	private JTextArea resultBox;
	private JScrollPane gameNameScroll;
	private JScrollPane resultBoxScroll;
	private String[][] tableData={};
	private String[] ColumnName = { "Item Name", "Game", "Delete" };

	View(Model model, Controller controller) {
		this.model = model;
		this.controller = controller;
		addButton = new JButton("add button");
		searchButton = new JButton("search button");
		itemNameLabel = new JLabel("Please enter your item name");
		gameNameLabel = new JLabel("Please enter your game name");
		itemName = new JTextField("Please enter your item name");
		gameName = new JTextField("Please enter your game name");
		choosedItems = new JTable(tableData, ColumnName);
		
		resultBox = new JTextArea();
		gameNameScroll = new JScrollPane(choosedItems);
		resultBoxScroll = new JScrollPane(resultBox);

		addButton.addActionListener(controller);
		searchButton.addActionListener(controller);

		initView();
	}

	private void initView() {
		this.setBackground(Color.WHITE);
		this.setLayout(new BorderLayout());

		leftPanel = new JPanel();
		leftPanel.setLayout(new BoxLayout(leftPanel, BoxLayout.Y_AXIS));

		centerPanel = new JPanel();
		centerPanel.setLayout(new GridLayout(1, 2, 1, 1));
		rightPanel = new JPanel();
		rightPanel.setLayout(new GridLayout());

		itemName.setMaximumSize(new Dimension(200, 20));
		gameName.setMaximumSize(new Dimension(200, 20));
		addButton.setMaximumSize(new Dimension(120, 30));
		searchButton.setMaximumSize(new Dimension(120, 30));
		gameNameScroll.setMaximumSize(new Dimension(360, 400));

		// itemName.setName("Enter Item Name");
		itemNameLabel.setLabelFor(itemName);

		// itemName.setAlignmentX(CENTER_ALIGNMENT);
		addButton.setAlignmentX(CENTER_ALIGNMENT);
		searchButton.setAlignmentX(CENTER_ALIGNMENT);

		// leftPanel.add(itemNameLabel);
		leftPanel.add(itemName);
		// leftPanel.add(gameNameLabel);
		leftPanel.add(gameName);
		leftPanel.add(addButton);
		leftPanel.add(gameNameScroll);
		leftPanel.add(searchButton);
		rightPanel.add(resultBoxScroll);

		centerPanel.add(leftPanel);
		centerPanel.add(rightPanel);

		add(centerPanel, BorderLayout.CENTER);
	}

	@Override
	public void updateView() {
		repaint();
	}

	public String getItemName() {
		String input = itemName.getText();
		if (input.equals(null) && !(input.equals("Please enter your item name"))) {			
			return input;
		} else {
			return null;
		}
	}
	
	public String getGameName() {
		String input = gameName.getText();
		if (input.equals(null) && !(input.equals("Please enter your game name"))) {
			return input;
		} else {
			return null;
		}
	}
	
	public void addTableValue(String[][]input){
		tableData=input;
		choosedItems.repaint();
	}
}