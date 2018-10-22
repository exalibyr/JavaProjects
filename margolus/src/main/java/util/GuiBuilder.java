package util;
import entity.Cell;
import logic.MyTableCellRenderer;
import model.MyTableColumnModel;
import model.MyTableModel;
import model.VerticalLayout;

import javax.swing.*;
import java.awt.*;

public class GuiBuilder {

    private Dimension buttonDimension = new Dimension(100, 30);

    public JButton initStopGameButton(JPanel panel) {
        JButton stopGameButton = new JButton("Stop");
        stopGameButton.setMinimumSize(buttonDimension);
        stopGameButton.setPreferredSize(buttonDimension);
        stopGameButton.setEnabled(false);
        panel.add(stopGameButton);
        return stopGameButton;
    }

    public JButton initStartGameButton(JPanel panel){
        JButton startGameButton = new JButton("Start");
        startGameButton.setMinimumSize(buttonDimension);
        startGameButton.setPreferredSize(buttonDimension);
        panel.add(startGameButton);
        return startGameButton;
    }

    public JButton initOneStepButton(JPanel panel){
        JButton oneStepButton = new JButton("One step");
        oneStepButton.setMinimumSize(buttonDimension);
        oneStepButton.setPreferredSize(buttonDimension);
        panel.add(oneStepButton);
        return oneStepButton;
    }

    public JButton initCleanCellsButton(JPanel panel){
        JButton cleanCellsButton = new JButton("Clean cells");
        cleanCellsButton.setMinimumSize(buttonDimension);
        cleanCellsButton.setPreferredSize(buttonDimension);
        panel.add(cleanCellsButton);
        return cleanCellsButton;
    }

    public JLabel initInfoLabel(JPanel panel){
        JLabel infoLabel = new JLabel();
        infoLabel.setMinimumSize(new Dimension(150, 30));
        infoLabel.setPreferredSize(new Dimension(150, 30));
        infoLabel.setForeground(Color.RED);
        panel.add(infoLabel);
        return infoLabel;
    }

    public JTable initGameFieldTable(MyTableModel gameFieldData, JPanel mainPanel, Dimension displaySize){
        JTable gameFieldTable = new JTable(gameFieldData, new MyTableColumnModel(displaySize));
        gameFieldTable.setPreferredSize(new Dimension(600, 600));
        gameFieldTable.setDefaultRenderer(Cell.class, new MyTableCellRenderer());
        gameFieldTable.setCellSelectionEnabled(false);
        gameFieldTable.setFocusable(false);
        gameFieldTable.setGridColor(Color.RED);
        gameFieldTable.setRowHeight(700 / gameFieldTable.getModel().getRowCount());
        mainPanel.add(gameFieldTable);
        return gameFieldTable;
    }

    public void initCheckBoxesPanel(JCheckBox checkBox1, JPanel panel){
        JPanel checkBoxesPanel = new JPanel();
        checkBoxesPanel.setLayout(new BoxLayout(checkBoxesPanel, BoxLayout.Y_AXIS));
        checkBoxesPanel.setBorder(BorderFactory.createTitledBorder("Options"));
        checkBoxesPanel.setBackground(Color.ORANGE);
        checkBoxesPanel.add(checkBox1);
        panel.add(checkBoxesPanel);
    }

    public JPanel initMainPanel(){
        JPanel mainPanel = new JPanel();
        mainPanel.setBackground(Color.ORANGE);
        return mainPanel;
    }

    public JCheckBox initDrawingModeCheckBox(){
        JCheckBox drawingModeCheckBox = new JCheckBox("Drawing mode", false);
        drawingModeCheckBox.setBackground(Color.ORANGE);
        return drawingModeCheckBox;
    }

    public JPanel initButtonsPanel(){
        VerticalLayout layout = new VerticalLayout();
        JPanel buttonsPanel = new JPanel();
        buttonsPanel.setLayout(layout);
        buttonsPanel.setBackground(Color.ORANGE);
        return buttonsPanel;
    }
}
