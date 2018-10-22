package model;

import javax.swing.table.DefaultTableColumnModel;
import javax.swing.table.TableColumn;
import java.awt.*;

public class MyTableColumnModel extends DefaultTableColumnModel{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public MyTableColumnModel(Dimension displaySize) {
		// TODO Auto-generated constructor stub
		super();
		int pixelsInCell = 20;
		//int cellsInRow = (displaySize.height - 20) / pixelsInCell;
        int cellsInRow = 30;
		totalColumnWidth = pixelsInCell;
		for(int i = 0; i < cellsInRow; i++) {
			tableColumns.add(new TableColumn(i, getTotalColumnWidth()));
		}
	}
	
}
