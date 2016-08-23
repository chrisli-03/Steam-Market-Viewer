import java.awt.Color;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.DefaultCellEditor;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JTable;

class ButtonEditor extends DefaultCellEditor {
	protected JButton button;
	private String label;
	private boolean isPushed;
	private int row;
	private Model model;

	public ButtonEditor(JCheckBox checkBox, Model model) {
		super(checkBox);
		this.model = model;
		button = new JButton();
		button.setOpaque(true);
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				fireEditingStopped();
			}
		});
	}

	public Component getTableCellEditorComponent(JTable table, Object value,
			boolean isSelected, int row, int column) {
		this.row = row;
		if (isSelected) {
			button.setForeground(table.getSelectionForeground());
			button.setBackground(table.getSelectionBackground());
		} else {
			button.setBackground(Color.RED);
			button.setOpaque(true);
			button.setFocusPainted(false);
			button.setFocusable(false);
		}
		label = (value == null) ? "" : value.toString();
		button.setText(label);
		isPushed = true;
		return button;
	}
	
	// causes ArrayIndexOutOfBoundsException for some reason
	public Object getCellEditorValue() throws ArrayIndexOutOfBoundsException {
		if (isPushed) {
			model.deleteRequestAtN(row);
		}
		isPushed = false;
		return new String(label);
	}

	public boolean stopCellEditing() {
		isPushed = false;
		return super.stopCellEditing();
	}

	protected void fireEditingStopped() {
		super.fireEditingStopped();
	}
}