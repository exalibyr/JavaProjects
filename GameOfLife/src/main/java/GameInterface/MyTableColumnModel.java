package GameInterface;

import javax.swing.table.DefaultTableColumnModel;
import javax.swing.table.TableColumn;
import java.awt.*;

class MyTableColumnModel extends DefaultTableColumnModel{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	MyTableColumnModel(Dimension displaySize) {
		// TODO Auto-generated constructor stub
		super();
		int pixelsInCell = 10;
		//int cellsInRow = (displaySize.height - 20) / pixelsInCell;
        int cellsInRow = 70;
		totalColumnWidth = pixelsInCell;
		for(int i = 0; i < cellsInRow; i++) {
			tableColumns.add(new TableColumn(i, getTotalColumnWidth()));
		}
	}
	
}
