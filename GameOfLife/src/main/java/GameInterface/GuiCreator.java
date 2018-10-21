package GameInterface;
import Logic.Cell;

import javax.swing.*;
import java.awt.*;

class GuiCreator {

    private Dimension buttonDimension = new Dimension(100, 30);

    JButton initStopGameButton(JPanel panel) {
        JButton stopGameButton = new JButton("Stop");
        stopGameButton.setMinimumSize(buttonDimension);
        stopGameButton.setPreferredSize(buttonDimension);
        stopGameButton.setEnabled(false);
        panel.add(stopGameButton);
        return stopGameButton;
    }

    JButton initStartGameButton(JPanel panel){
        JButton startGameButton = new JButton("Start game");
        startGameButton.setMinimumSize(buttonDimension);
        startGameButton.setPreferredSize(buttonDimension);
        panel.add(startGameButton);
        return startGameButton;
    }

    JButton initOneStepButton(JPanel panel){
        JButton oneStepButton = new JButton("One step");
        oneStepButton.setMinimumSize(buttonDimension);
        oneStepButton.setPreferredSize(buttonDimension);
        panel.add(oneStepButton);
        return oneStepButton;
    }

    JButton initCleanCellsButton(JPanel panel){
        JButton cleanCellsButton = new JButton("Clean cells");
        cleanCellsButton.setMinimumSize(buttonDimension);
        cleanCellsButton.setPreferredSize(buttonDimension);
        panel.add(cleanCellsButton);
        return cleanCellsButton;
    }

    JLabel initInfoLabel(JPanel panel){
        JLabel infoLabel = new JLabel();
        infoLabel.setMinimumSize(new Dimension(150, 30));
        infoLabel.setPreferredSize(new Dimension(150, 30));
        infoLabel.setForeground(Color.RED);
        panel.add(infoLabel);
        return infoLabel;
    }

    JTable initGameFieldTable(MyTableModel gameFieldData, JPanel mainPanel, Dimension displaySize){
        JTable gameFieldTable = new JTable(gameFieldData, new MyTableColumnModel(displaySize));
        gameFieldTable.setPreferredSize(new Dimension(700, 700));
        gameFieldTable.setDefaultRenderer(Cell.class, new MyTableCellRenderer());
        gameFieldTable.setCellSelectionEnabled(false);
        gameFieldTable.setFocusable(false);
        gameFieldTable.setGridColor(Color.RED);
        gameFieldTable.setRowHeight(700 / gameFieldTable.getModel().getRowCount());
        mainPanel.add(gameFieldTable);
        return gameFieldTable;
    }

    void initCheckBoxesPanel(JCheckBox checkBox1, JCheckBox checkBox2, JPanel panel){
        JPanel checkBoxesPanel = new JPanel();
        checkBoxesPanel.setLayout(new BoxLayout(checkBoxesPanel, BoxLayout.Y_AXIS));
        checkBoxesPanel.setBorder(BorderFactory.createTitledBorder("Options"));
        checkBoxesPanel.setBackground(Color.ORANGE);
        checkBoxesPanel.add(checkBox1);
        checkBoxesPanel.add(checkBox2);
        panel.add(checkBoxesPanel);
    }

    JPanel initMainPanel(){
        JPanel mainPanel = new JPanel();
        mainPanel.setBackground(Color.ORANGE);
        return mainPanel;
    }

    JCheckBox initUnlimitedBordersCheckBox(){
        JCheckBox unlimitedBordersCheckBox = new JCheckBox("Unlimited borders", false);
        unlimitedBordersCheckBox.setBackground(Color.ORANGE);
        return unlimitedBordersCheckBox;
    }

    JCheckBox initDrawingModeCheckBox(){
        JCheckBox drawingModeCheckBox = new JCheckBox("Drawing mode", false);
        drawingModeCheckBox.setBackground(Color.ORANGE);
        return drawingModeCheckBox;
    }

    JPanel initButtonsPanel(){
        VerticalLayout layout = new VerticalLayout();
        JPanel buttonsPanel = new JPanel();
        buttonsPanel.setLayout(layout);
        buttonsPanel.setBackground(Color.ORANGE);
        return buttonsPanel;
    }
}
