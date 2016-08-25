import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Toolkit;

import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingWorker;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;

import ca.odell.glazedlists.GlazedLists;
import ca.odell.glazedlists.swing.AutoCompleteSupport;

public class View extends JPanel implements IView {
	private Model model;
	private Controller controller;
	
	private JButton addButton;
	private JButton searchButton;
	
	
	private JTable searchTable;
	private JTable resultTable;
	
	private JComboBox itemBox;
	private JComboBox gameBox;
	
	private JProgressBar searchProgressBar;
	
	private JScrollPane gameNameScroll;
	private JScrollPane resultBoxScroll;
	
	private Object[][] searchTableData={};
	private Object[][] resultTableData={};
	private DefaultTableModel searchTableModel;
	private DefaultTableModel resultTableModel;
	
	// TODO: retrieve game and item info from database
	Object[] exampleItems = new Object[] {"Platinum Baby Roshan", 
										"Platinum Baby Roshan 2", 
										"Platinum Baby Roshan 3", 
										"Test", 
										"Test 2", 
										"test"};
	Object[] exampleGames = new Object[] {"Dota2", 
										"CSGO"};
	

	
	View(Model model, Controller controller) {
		// store model and controller
		this.model = model;
		this.controller = controller;
		initView();
	}

	private void initView() {
		// set layout and bgc
		this.setBackground(Color.WHITE);
		this.setLayout(new BorderLayout());
		
		// setup add and search buttons
		addButton = new JButton("+");
		addButton.setName("add button");
		addButton.setFocusPainted(false);
		addButton.addActionListener(controller);
		
		searchButton = new JButton("search");
		searchButton.setName("search button");
		searchButton.setFocusPainted(false);
		searchButton.addActionListener(controller);
		
		// setup item name and game name boxes
		itemBox = new JComboBox();
		gameBox = new JComboBox();
		javax.swing.SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				AutoCompleteSupport.install(itemBox, GlazedLists.eventListOf(exampleItems));
				AutoCompleteSupport.install(gameBox, GlazedLists.eventListOf(exampleGames));
			}
		});
		
		// setup search table
		String[] searchColumn = { "Item", "Game", "Delete" };
		searchTable = new JTable(searchTableData, searchColumn);
		searchTable.setFocusable(false);
		searchTable.getTableHeader().setReorderingAllowed(false);
		searchTableModel = new DefaultTableModel() {
			public boolean isCellEditable(int rowIndex, int mColIndex) {
				if (mColIndex != 2) return false;
				else return true;
			}
		};
		searchTableModel.setColumnIdentifiers(searchColumn);
		searchTable.setModel(searchTableModel);
		searchTable.setRowSelectionAllowed(false);
		gameNameScroll= new JScrollPane(searchTable);
		
		// setup result table
		String[] resultColumn = { "Item", "Price", "Vol", "Median", "Date"};
		resultTable = new JTable(resultTableData, resultColumn);
		resultTable.setFocusable(false);
		resultTable.getTableHeader().setReorderingAllowed(false);
		resultTableModel = new DefaultTableModel() {
			public boolean isCellEditable(int rowIndex, int mColIndex) {
				return false;
			}
		};
		resultTableModel.setColumnIdentifiers(resultColumn);
		resultTable.setModel(resultTableModel);
		resultTable.setRowSelectionAllowed(false);
		resultBoxScroll = new JScrollPane(resultTable);
		
		// setup progress bar
		searchProgressBar = new JProgressBar(0, 100);
		searchProgressBar.setValue(0);
		searchProgressBar.setStringPainted(true);
		
		// setup table column width
	    setupSearchTableColumnWidth();
	    setupResultTableColumnWidth();
		
	    // setup left panel
	    JPanel leftPanel;
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
		leftPanel.add(itemBox, gbc);
		
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
		leftPanel.add(gameBox, gbc);
		
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
		
		// Search button and progress bar (0, 4)
		gbc.anchor = GridBagConstraints.LAST_LINE_END;
		gbc.weightx = 0;
		gbc.weighty = 0;
		gbc.gridx = 0;
		gbc.gridy = 4;
		JPanel searchAndProgressBar = new JPanel();
		searchAndProgressBar.add(searchButton);
		searchAndProgressBar.add(searchProgressBar);
		leftPanel.add(searchAndProgressBar, gbc);
		
		// setup right panel
		JPanel rightPanel;
		rightPanel = new JPanel();
		rightPanel.setLayout(new GridLayout());
		rightPanel.add(resultBoxScroll);
		
		// connect left and right panel
		JPanel centerPanel;
		centerPanel = new JPanel();
		centerPanel.setLayout(new GridLayout(1, 2, 1, 1));
		centerPanel.add(leftPanel);
		centerPanel.add(rightPanel);
		add(centerPanel, BorderLayout.CENTER);
	}
	
	private void setupSearchTableColumnWidth() {
		TableColumn column = null;
		for (int i = 0; i < 3; i++) {
	        column = searchTable.getColumnModel().getColumn(i);
	        if (i == 2) {
	            column.setMaxWidth(50);
	        }
	    }
	}
	
	private void setupResultTableColumnWidth() {
		TableColumn column = null;
		for (int i = 0; i < 5; i++) {
	        column = resultTable.getColumnModel().getColumn(i);
	        if ((i == 1)||(i == 3)) {
	        	column.setMaxWidth(50);
	        } else if (i == 2) {
	        	column.setMaxWidth(40);
	        } else if (i == 4) {
	        	column.setMaxWidth(75);
	        }
	    }
	}
	
	public String getItemName() {
		return (String) itemBox.getSelectedItem();
	}
	
	public String getGameName() {
		return (String) gameBox.getSelectedItem();
	}
	
	@Override
	public void updateView() {
		// clear search table
		ArrayList<Request> requestList = model.getRequestList();
		searchTableModel.setColumnCount(3);
		searchTableModel.setRowCount(0);
		
		setupSearchTableColumnWidth();
		
		// go through search list and add to table
		for (int i = 0; i < requestList.size(); i++) {
			// create object and add to table
			Object[] temp = new Object[] {requestList.get(i).getItemName(),
					requestList.get(i).getGameName(),
					"x"};
			searchTableModel.addRow(temp);
		}
		searchTableModel.fireTableDataChanged();
		
		// add delete buttons
		searchTable.getColumn("Delete").setCellRenderer(new ButtonRenderer());
		searchTable.getColumn("Delete").setCellEditor(new ButtonEditor(new JCheckBox(), model));
		revalidate();
		repaint();
	}
	
	public void updateResultView() {
		Task task = new Task();
		task.execute();
		revalidate();
		repaint();
	}
	
	class Task extends SwingWorker<Void, Void> {
        /*
         * Main task. Executed in background thread.
         */
        @Override
        public Void doInBackground() {
        	// empty the table
        	ArrayList<Result> resultList = model.getResultList();
    		resultTableModel.setColumnCount(5);
    		resultTableModel.setRowCount(0);
    		
    		setupResultTableColumnWidth();
    		
    		// go through result list and update result table
    		int progress = 0;
    		int increment = 100/resultList.size();
    		for (Result res : resultList) {
    			try {
    				Thread.sleep(500); // 500ms between adding result
    			} catch (InterruptedException ignore) {}
    			// create object and add to table
    			Object[] temp = new Object[] { res.getItemName(),
    											res.getPrice(),
    											res.getVolumn(),
    											res.getMedian(),
    											res.getDate() };
    			resultTableModel.addRow(temp);
    			// move progress bar
    			progress += increment;
    			searchProgressBar.setValue(Math.min(progress, 100));
    		}
    		searchProgressBar.setValue(100); // completely fill progress bar
    		resultTableModel.fireTableDataChanged();
    		return null;
        }
 
        /*
         * Executed in event dispatching thread
         */
        @Override
        public void done() {
            Toolkit.getDefaultToolkit().beep(); // not sure what this is
        }
    }
}