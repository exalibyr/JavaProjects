package GameInterface;
import Logic.Cell;

import java.awt.Color;
import java.awt.Component;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;

public class MyTableCellRenderer extends DefaultTableCellRenderer{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static final Color DEAD = Color.WHITE;
	private static final Color ALIVE = Color.BLACK;
	
	
	@Override
	public Component getTableCellRendererComponent(JTable gameFieldTable, Object value, boolean isSelected, boolean hasFocus,
			int row, int column) {
		// TODO Auto-generated method stub
		Cell cell = (Cell)value;
		JLabel cellField = (JLabel)super.getTableCellRendererComponent(gameFieldTable, value, isSelected, hasFocus, row, column);
		if(!cell.isAlive()) {
			cellField.setBackground(DEAD);
			cellField.setForeground(DEAD);
		}
		else {
			cellField.setBackground(ALIVE);
			cellField.setForeground(ALIVE);
		}
		
		return cellField;
		
	}
	
}
