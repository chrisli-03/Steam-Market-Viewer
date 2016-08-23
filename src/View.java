import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;

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
	private JTable choosedItems;
	private JTable resultBox;
	private JScrollPane gameNameScroll;
	private JScrollPane resultBoxScroll;
	private Object[][] tableData={};
	private Object[][] searchResultData={};
	private DefaultTableModel tableModel;
	private DefaultTableModel resultTableModel;

	View(Model model, Controller controller) {
		this.model = model;
		this.controller = controller;
		
		addButton = new JButton("+");
		searchButton = new JButton("search");
		itemName = new JTextField();
		gameName = new JTextField();
		String[] searchColumn = { "Item", "Game", "Delete" };
		choosedItems = new JTable(tableData, searchColumn);
		String[] resultColumn = { "Item", "Price", "Vol", "Median", "Date"};
		resultBox = new JTable(searchResultData, resultColumn);
		gameNameScroll= new JScrollPane(choosedItems);
		resultBoxScroll= new JScrollPane(resultBox);
		tableModel = new DefaultTableModel() {
							public boolean isCellEditable(int rowIndex, int mColIndex) {
								if (mColIndex != 2) return false;
								else return true;
							}
						};
		resultTableModel = new DefaultTableModel() {
			public boolean isCellEditable(int rowIndex, int mColIndex) {
				return false;
			}
		};
		choosedItems.setFocusable(false);
		choosedItems.getTableHeader().setReorderingAllowed(false);
		tableModel.setColumnIdentifiers(searchColumn);
		choosedItems.setModel(tableModel);
		
		resultBox.setFocusable(false);
		resultBox.getTableHeader().setReorderingAllowed(false);
		resultTableModel.setColumnIdentifiers(resultColumn);
		resultBox.setModel(resultTableModel);
		
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
		resultBox.setRowSelectionAllowed(false);
		addButton.setName("add button");
		addButton.setFocusPainted(false);
		searchButton.setName("search button");
		choosedItems.setRowSelectionAllowed(false);
		resultBox.setRowSelectionAllowed(false);
	    TableColumn column = null;
	    for (int i = 0; i < 3; i++) {
	        column = choosedItems.getColumnModel().getColumn(i);
	        if (i == 2) {
	            column.setMaxWidth(50);
	        }
	    }
	    
	    for (int i = 0; i < 5; i++) {
	        column = resultBox.getColumnModel().getColumn(i);
	        if ((i == 1)||(i == 3)) {
	        	column.setMaxWidth(50);
	        } else if (i == 2) {
	        	column.setMaxWidth(40);
	        } else if (i == 4) {
	        	column.setMaxWidth(75);
	        }
	    }
		
		leftPanel = new JPanel();
		leftPanel.setLayout(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();
		
		// Enter item label (0, 0)
		JLabel itemLabel = new JLabel("Enter Item: ");
		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.fill = GridBagConstraints.HORIZONTAL;
		leftPanel.add(itemLabel, gbc);
		
		// Item name text box (0, 1)
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
		
		// Game name text box (1, 1)
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
		ArrayList<Request> requestList = model.getRequestList();
		tableModel.setColumnCount(3);
		tableModel.setRowCount(0);
		for (int i = 0; i < requestList.size(); i++) {
			Object[] temp = new Object[] {requestList.get(i).getItemName(),
					requestList.get(i).getGameName(),
					"x"};
			tableModel.addRow(temp);
		}
		tableModel.fireTableDataChanged();
		TableColumn column = null;
		for (int i = 0; i < 3; i++) {
	        column = choosedItems.getColumnModel().getColumn(i);
	        if (i == 2) {
	            column.setMaxWidth(50);
	        }
	    }
		choosedItems.getColumn("Delete").setCellRenderer(new ButtonRenderer());
		choosedItems.getColumn("Delete").setCellEditor(new ButtonEditor(new JCheckBox(), model));
		revalidate();
		repaint();
	}
	
	public String getItemName() {
		return itemName.getText();
	}
	
	public String getGameName() {
		return gameName.getText();
	}
	
	public void updateResultView() {
		ArrayList<Result> resultList = model.getResultList();
		resultTableModel.setColumnCount(5);
		resultTableModel.setRowCount(0);
		for (Result res : resultList) {
			Object[] temp = new Object[] { res.getItemName(),
											res.getPrice(),
											res.getVolumn(),
											res.getMedian(),
											res.getDate() };
			resultTableModel.addRow(temp);
		}
		
		resultTableModel.fireTableDataChanged();
		TableColumn column = null;
		for (int i = 0; i < 5; i++) {
	        column = resultBox.getColumnModel().getColumn(i);
	        if ((i == 1)||(i == 3)) {
	        	column.setMaxWidth(50);
	        } else if (i == 2) {
	        	column.setMaxWidth(40);
	        } else if (i == 4) {
	        	column.setMaxWidth(75);
	        }
	    }
		revalidate();
		repaint();
	}
}