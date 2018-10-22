package model;

import entity.Cell;
import util.CellsBuilder;

import javax.swing.table.DefaultTableModel;
import java.awt.*;


public class MyTableModel extends DefaultTableModel{

	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	
	private Cell[][] cells;
	
	public MyTableModel(Dimension displaySize) {
		// TODO Auto-generated constructor stub
		super();
//        int pixelsInCell = 10;
		//int cellsInRow = (displaySize.height - 20) / pixelsInCell;
        int cellsInRow = 30;
        setColumnCount(cellsInRow);
		setRowCount(cellsInRow);
		cells = CellsBuilder.createAndSet(cellsInRow, cellsInRow);
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
