import java.awt.Color;
import java.awt.Component;

import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.table.TableCellRenderer;

class ButtonRenderer extends JButton implements TableCellRenderer {
	
	public ButtonRenderer() {
		setOpaque(true);
		setFocusPainted(false);
		setFocusable(false);
	}
	
	@Override
	public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row,
			int column) {
		if (isSelected) {
			setForeground(table.getSelectionForeground());
			setBackground(table.getSelectionBackground());
		} else {
			setForeground(table.getSelectionForeground());
			setBackground(Color.RED);
			setOpaque(true);
			setFocusPainted(false);
			setFocusable(false);
		}
		setText((value == null) ? "" : value.toString());
		return this;
	}
}