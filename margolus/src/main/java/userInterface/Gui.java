package userInterface;

import logic.Logic;
import model.MyTableModel;
import util.GuiBuilder;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Gui extends JFrame{

	private static final long serialVersionUID = 1L;
	
	public Gui(){
		super("Margolus's cellular automaton");

        GuiBuilder guiBuilder = new GuiBuilder();
        Dimension gameWindowSize = new Dimension(800, 660);
        JPanel buttonsPanel = guiBuilder.initButtonsPanel();

        JCheckBox drawingModeCheckBox = guiBuilder.initDrawingModeCheckBox();
        guiBuilder.initCheckBoxesPanel(drawingModeCheckBox, buttonsPanel);

        JButton startGameButton = guiBuilder.initStartGameButton(buttonsPanel);
        JButton stopGameButton = guiBuilder.initStopGameButton(buttonsPanel);
        JButton oneStepButton = guiBuilder.initOneStepButton(buttonsPanel);
        JButton cleanCellsButton = guiBuilder.initCleanCellsButton(buttonsPanel);
        JLabel infoLabel = guiBuilder.initInfoLabel(buttonsPanel);
		JLabel infoLabel3 = guiBuilder.initInfoLabel(buttonsPanel);

		JPanel mainPanel = guiBuilder.initMainPanel();
		MyTableModel gameFieldData = new MyTableModel(gameWindowSize);
		JTable gameFieldTable = guiBuilder.initGameFieldTable(gameFieldData, mainPanel, gameWindowSize);
		gameFieldTable.setRowHeight(20);

        getContentPane().add(BorderLayout.WEST, buttonsPanel);
		getContentPane().add(BorderLayout.CENTER, mainPanel);
		setPreferredSize(gameWindowSize);
		setMinimumSize(gameWindowSize);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		pack();
        buttonsPanel.revalidate();
		setVisible(true);


        Logic logic = new Logic(gameFieldTable, gameFieldData);

		drawingModeCheckBox.addItemListener(new ItemListener() {
			@Override
			public void itemStateChanged(ItemEvent e) {
				if(logic.isDrawingMode()){
					logic.setDrawingMode(false);
					drawingModeCheckBox.setBorderPaintedFlat(false);
				}
				else{
					logic.setDrawingMode(true);
					drawingModeCheckBox.setBorderPaintedFlat(true);
				}
			}
		});

		gameFieldTable.addMouseListener(new MouseListener() {
			@Override
			public void mouseReleased(MouseEvent e) {
				logic.setMouseReleased();
			}
			
			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub
				if(logic.isGameRunning()){
					infoLabel.setText("Stop first!");
				}
				else{
					if(logic.isDrawingMode()){
						logic.press();
					}
					else{
						int row = e.getY() / gameFieldTable.getRowHeight();
						int column = e.getX() / gameFieldTable.getColumnModel().getTotalColumnWidth();
						logic.changeCellState(row, column);
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
				logic.stopGame();
				startGameButton.setEnabled(true);
				cleanCellsButton.setEnabled(true);
				oneStepButton.setEnabled(true);
				drawingModeCheckBox.setEnabled(true);
				stopGameButton.setEnabled(false);
				infoLabel.setText("Stopped!");
			}
		});

		startGameButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				if(logic.isGameFieldEmpty()){
					infoLabel.setText("Cells are empty!");
				}
				else {
					infoLabel.setText("");
					logic.startGame(infoLabel, startGameButton, stopGameButton, oneStepButton,
										cleanCellsButton, drawingModeCheckBox);
				}
			}
		});

		cleanCellsButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if(!logic.isGameFieldEmpty()){
					logic.cleanCells();
					infoLabel.setText("Cleaned!");
				}
			}
		});

		oneStepButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if(logic.isGameFieldEmpty()){
					infoLabel.setText("Cells are empty!");
				}
				else {
					infoLabel3.setText(logic.getSplitting());
					logic.performOneStep();
					infoLabel.setText("Performed!");
				}
			}
		});
	}



}
