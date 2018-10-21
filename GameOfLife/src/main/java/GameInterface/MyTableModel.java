package GameInterface;
import Logic.Cell;
import Logic.CellsCreator;
import javax.swing.table.DefaultTableModel;
import java.awt.*;


public class MyTableModel extends DefaultTableModel{

	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	
	private Cell[][] cells;
	
	MyTableModel(Dimension displaySize) {
		// TODO Auto-generated constructor stub
		super();
        int pixelsInCell = 10;
		//int cellsInRow = (displaySize.height - 20) / pixelsInCell;
        int cellsInRow = 70;
        setColumnCount(cellsInRow);
		setRowCount(cellsInRow);
		cells = CellsCreator.createAndSet(cellsInRow, cellsInRow);
	}

	@Override
	public Cell getValueAt(int rowIndex, int columnIndex) {
		// TODO Auto-generated method stub
		return cells[rowIndex][columnIndex];
	}

	@Override
	public Class<?> getColumnClass(int column){
		return Cell.class;
	}

	public boolean getCellState(int rowIndex, int columnIndex){
		return cells[rowIndex][columnIndex].isAlive();
	}

	public void setCellState(boolean state, int rowIndex, int columnIndex){
		cells[rowIndex][columnIndex].setState(state);
	}
	
}
