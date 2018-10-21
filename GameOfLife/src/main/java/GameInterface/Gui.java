package GameInterface;
import Logic.GameLogic;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class Gui extends JFrame{

	private static final long serialVersionUID = 1L;
	
	public Gui(){
		super("Game of life");

        GuiCreator guiCreator = new GuiCreator();
        Dimension gameWindowSize = new Dimension(900, 760);
        //Dimension displaySize = Toolkit.getDefaultToolkit().getScreenSize();
        JPanel buttonsPanel = guiCreator.initButtonsPanel();

        JCheckBox unlimitedBordersCheckBox = guiCreator.initUnlimitedBordersCheckBox();
        JCheckBox drawingModeCheckBox = guiCreator.initDrawingModeCheckBox();
        guiCreator.initCheckBoxesPanel(unlimitedBordersCheckBox, drawingModeCheckBox, buttonsPanel);

        JButton startGameButton = guiCreator.initStartGameButton(buttonsPanel);
        JButton stopGameButton = guiCreator.initStopGameButton(buttonsPanel);
        JButton oneStepButton = guiCreator.initOneStepButton(buttonsPanel);
        JButton cleanCellsButton = guiCreator.initCleanCellsButton(buttonsPanel);
        JLabel infoLabel = guiCreator.initInfoLabel(buttonsPanel);

		JPanel mainPanel = guiCreator.initMainPanel();
		MyTableModel gameFieldData = new MyTableModel(gameWindowSize);
		JTable gameFieldTable = guiCreator.initGameFieldTable(gameFieldData, mainPanel, gameWindowSize);

        getContentPane().add(BorderLayout.WEST, buttonsPanel);
		getContentPane().add(BorderLayout.CENTER, mainPanel);
		setPreferredSize(gameWindowSize);
		//setMinimumSize(new Dimension(900, 760)); //450, 250
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		pack();
        buttonsPanel.revalidate();
		setVisible(true);


        GameLogic gameLogic = new GameLogic(gameFieldTable, gameFieldData);

		drawingModeCheckBox.addItemListener(new ItemListener() {
			@Override
			public void itemStateChanged(ItemEvent e) {
				if(gameLogic.isDrawingMode()){
					gameLogic.setDrawingMode(false);
					drawingModeCheckBox.setBorderPaintedFlat(false);
				}
				else{
					gameLogic.setDrawingMode(true);
					drawingModeCheckBox.setBorderPaintedFlat(true);
				}
			}
		});

		unlimitedBordersCheckBox.addItemListener(new ItemListener() {
			@Override
			public void itemStateChanged(ItemEvent e) {
				if(gameLogic.isUnlimitedBorders()){
					gameLogic.setUnlimitedBorders(false);
					unlimitedBordersCheckBox.setBorderPaintedFlat(false);
				}
				else{
					gameLogic.setUnlimitedBorders(true);
					unlimitedBordersCheckBox.setBorderPaintedFlat(true);
				}
			}
		});

		gameFieldTable.addMouseListener(new MouseListener() {
			@Override
			public void mouseReleased(MouseEvent e) {
				gameLogic.setMouseReleased();
			}
			
			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub
				if(gameLogic.isGameRunning()){
					infoLabel.setText("Stop the game first!");
				}
				else{
					if(gameLogic.isDrawingMode()){
						gameLogic.press();
					}
					else{
						int row = e.getY() / gameFieldTable.getRowHeight();
						int column = e.getX() / gameFieldTable.getColumnModel().getTotalColumnWidth();
						gameLogic.changeCellState(row, column);
					}
				}
			}
			
			@Override
			public void mouseExited(MouseEvent e) { }
			
			@Override
			public void mouseEntered(MouseEvent e) { }
			
			@Override
			public void mouseClicked(MouseEvent e) { }
		});

		stopGameButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				gameLogic.stopGame();
				startGameButton.setEnabled(true);
				cleanCellsButton.setEnabled(true);
				oneStepButton.setEnabled(true);
				unlimitedBordersCheckBox.setEnabled(true);
				drawingModeCheckBox.setEnabled(true);
				stopGameButton.setEnabled(false);
				infoLabel.setText("Stopped!");
			}
		});

		startGameButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				if(gameLogic.isGameFieldEmpty()){
					infoLabel.setText("Cells are empty!");
				}
				else {
					infoLabel.setText("");
					gameLogic.startGame(infoLabel, startGameButton, stopGameButton, oneStepButton,
										cleanCellsButton, unlimitedBordersCheckBox, drawingModeCheckBox);
				}
			}
		});

		cleanCellsButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if(!gameLogic.isGameFieldEmpty()){
					gameLogic.cleanCells();
					infoLabel.setText("Cleaned!");
				}
			}
		});

		oneStepButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if(gameLogic.isGameFieldEmpty()){
					infoLabel.setText("Cells are empty!");
				}
				else {
					gameLogic.performOneStep();
					infoLabel.setText("Performed!");
				}
			}
		});
	}



}
