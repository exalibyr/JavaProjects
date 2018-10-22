package logic;

import entity.Cell;
import model.MyTableModel;
import util.CellsBuilder;

import javax.swing.*;
import java.awt.*;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

public class Logic {

    private boolean oddSplitting;

    private Cell currentCell;
    private MyTableModel gameFieldData;
    private JTable gameFieldTable;
    private int[] randomNumbers;
    private int index;

    private int rowAmount;
    private int columnAmount;
    private Cell[][] currentGeneration;
    private int aliveCellsAmount;
    private int aliveAdjCellsAmount;
    private Timer gameExecutionTimer;

    private boolean adjGenerationsHaveDifference;
    private boolean isGameRunning;
    private boolean unlimitedBorders;
    private boolean drawingMode;
    private boolean isMousePressed;
    private boolean isFirstCell;
    private boolean firstCellState;

    public Logic(JTable gameFieldTable, MyTableModel gameFieldData)
    {
        this.oddSplitting = true;
        this.gameFieldData = gameFieldData;
        this.gameFieldTable = gameFieldTable;
        rowAmount = gameFieldData.getRowCount();
        columnAmount = gameFieldData.getColumnCount();
        currentGeneration = CellsBuilder.createEmpty(rowAmount, columnAmount);
        aliveCellsAmount = 0;
        isGameRunning = false;
        unlimitedBorders = false;
        drawingMode = false;
        this.randomNumbers = new int[rowAmount*columnAmount/4];
    }

    public void randomize(){
        this.index = 0;
        Random random = new Random();
        for (int i = 0; i < randomNumbers.length; i++) {
            randomNumbers[i] = random.nextInt(100) + 1;
        }
    }

    public boolean isDrawingMode() {
        return drawingMode;
    }

    public void setDrawingMode(boolean drawingMode) {
        this.drawingMode = drawingMode;
    }

    public boolean isUnlimitedBorders() {
        return unlimitedBorders;
    }

    public void setUnlimitedBorders(boolean unlimitedBorders) {
        this.unlimitedBorders = unlimitedBorders;
    }

    public void press(){
        isMousePressed = true;
        gameExecutionTimer = new Timer();
        isFirstCell = true;
        gameExecutionTimer.schedule(new TimerTask() {
            @Override
            public void run() {
                Point point = MouseInfo.getPointerInfo().getLocation();
                SwingUtilities.convertPointFromScreen(point, gameFieldTable);
                int rowIndex = point.y / gameFieldTable.getRowHeight();
                int columnIndex = point.x / gameFieldTable.getColumnModel().getTotalColumnWidth();
                if(isFirstCell){
                    firstCellState = gameFieldData.getCellState(rowIndex, columnIndex);
                    isFirstCell = false;

                }
                currentCell = gameFieldData.getValueAt(rowIndex, columnIndex);
                if(currentCell.isAlive() == firstCellState){
                    currentCell.setState(!firstCellState);
                    if(firstCellState){
                        aliveCellsAmount--;
                    }
                    else {
                        aliveCellsAmount++;
                    }
                    gameFieldData.setValueAt(currentCell, rowIndex, columnIndex);
                }
                if(!isMousePressed){
                    gameExecutionTimer.cancel();
                    gameExecutionTimer.purge();
                }
            }
        }, 0, 1);
    }

    public void setMouseReleased() {
        if(isMousePressed){
            isMousePressed = false;
        }
    }

    public void changeCellState(int rowIndex, int columnIndex){
        currentCell = gameFieldData.getValueAt(rowIndex, columnIndex);
        if(currentCell.isAlive()) {
            currentCell.setState(false);
            aliveCellsAmount--;
        }
        else {
            currentCell.setState(true);
            aliveCellsAmount++;
        }
        gameFieldData.setValueAt(currentCell, rowIndex, columnIndex);
    }

    private void evaluateOddSplitting(){
        for (int i = 0; i < rowAmount; i++) {
            for (int j = 0; j < columnAmount; j++) {

                if((rowAmount - i > 1) && (columnAmount - j > 1)){
                    if(((i + 1) % 2 != 0) && ((j + 1) % 2 != 0)){
                        checkBlock(i, j);
                    }
                }

            }
        }
    }

    private void evaluateEvenSplitting(){
        for (int i = 1; i < rowAmount; i++) {
            for (int j = 1; j < columnAmount; j++) {

                if((rowAmount - i > 1) && (columnAmount - j > 1)){
                    if(((i + 1) % 2 == 0) && ((j + 1) % 2 == 0)){
                        checkBlock(i, j);
                    }
                }

            }
        }
    }

    public int getRandomNumber(){
        return this.randomNumbers[index];
    }

    public int getIndex(){
        return this.index;
    }

    public String getSplitting(){
        if(oddSplitting){
            return "Odd";
        }
        else {
            return "Even";
        }
    }

    private void checkBlock(int rowIndex, int columnIndex){
//        if(aliveAdjCellsAmount != 2){
//            changeBlockCells(rowIndex, columnIndex);
//        }
//        if(aliveAdjCellsAmount == 3){
//            invertBlock(rowIndex, columnIndex);
//        }
//        aliveAdjCellsAmount = 0;
        if(randomNumbers[index] % 2 == 0){
            rotateClockwise(rowIndex, columnIndex);
        }
        else {
            rotateAnticlockwise(rowIndex, columnIndex);
        }
        index++;
    }

    private void rotateClockwise(int rowIndex, int columnIndex ){
        boolean firstCellState, secondCellState, thirdCellState, fourthCellState;
        firstCellState = currentGeneration[rowIndex][columnIndex].isAlive();
        secondCellState = currentGeneration[rowIndex][columnIndex + 1].isAlive();
        thirdCellState = currentGeneration[rowIndex + 1][columnIndex + 1].isAlive();
        fourthCellState = currentGeneration[rowIndex + 1][columnIndex].isAlive();

        currentCell = gameFieldData.getValueAt(rowIndex, columnIndex);
        currentCell.setState(fourthCellState);
        gameFieldData.setValueAt(currentCell, rowIndex, columnIndex);

        currentCell = gameFieldData.getValueAt(rowIndex, columnIndex + 1);
        currentCell.setState(firstCellState);
        gameFieldData.setValueAt(currentCell, rowIndex, columnIndex + 1);

        currentCell = gameFieldData.getValueAt(rowIndex + 1, columnIndex + 1);
        currentCell.setState(secondCellState);
        gameFieldData.setValueAt(currentCell, rowIndex + 1, columnIndex + 1);

        currentCell = gameFieldData.getValueAt(rowIndex + 1, columnIndex);
        currentCell.setState(thirdCellState);
        gameFieldData.setValueAt(currentCell, rowIndex + 1, columnIndex);
    }

    private void rotateAnticlockwise(int rowIndex, int columnIndex){
        boolean firstCellState, secondCellState, thirdCellState, fourthCellState;
        firstCellState = currentGeneration[rowIndex][columnIndex].isAlive();
        secondCellState = currentGeneration[rowIndex][columnIndex + 1].isAlive();
        thirdCellState = currentGeneration[rowIndex + 1][columnIndex + 1].isAlive();
        fourthCellState = currentGeneration[rowIndex + 1][columnIndex].isAlive();

        currentCell = gameFieldData.getValueAt(rowIndex, columnIndex);
        currentCell.setState(secondCellState);
        gameFieldData.setValueAt(currentCell, rowIndex, columnIndex);

        currentCell = gameFieldData.getValueAt(rowIndex, columnIndex + 1);
        currentCell.setState(thirdCellState);
        gameFieldData.setValueAt(currentCell, rowIndex, columnIndex + 1);

        currentCell = gameFieldData.getValueAt(rowIndex + 1, columnIndex + 1);
        currentCell.setState(fourthCellState);
        gameFieldData.setValueAt(currentCell, rowIndex + 1, columnIndex + 1);

        currentCell = gameFieldData.getValueAt(rowIndex + 1, columnIndex);
        currentCell.setState(firstCellState);
        gameFieldData.setValueAt(currentCell, rowIndex + 1, columnIndex);
    }

    private void makeOneStep(){
        int i, j;
        aliveAdjCellsAmount = 0;
        for (i = 0; i < rowAmount; i++) {
            for (j = 0; j < columnAmount; j++) {
                currentGeneration[i][j].setState(gameFieldData.getCellState(i, j));
            }
        }

        randomize();

        if(oddSplitting){
            evaluateOddSplitting();
            oddSplitting = false;
        }
        else {
            evaluateEvenSplitting();
            oddSplitting = true;
        }
    }


    public boolean isGameFieldEmpty(){
        if(aliveCellsAmount == 0){
            return true;
        }
        else{
            return false;
        }
    }

    public boolean isGameRunning(){
        if(isGameRunning){
            return true;
        }
        else{
            return false;
        }
    }

    public void performOneStep(){
        makeOneStep();
    }

    public void startGame(final JLabel infoLabel, final JButton startGameButton, final JButton stopGameButton,
                          final JButton oneStepButton, final JButton cleanCellsButton,
                          final JCheckBox drawingModeCheckBox)
    {
        startGameButton.setEnabled(false);
        cleanCellsButton.setEnabled(false);
        oneStepButton.setEnabled(false);
        drawingModeCheckBox.setEnabled(false);
        stopGameButton.setEnabled(true);

        isGameRunning = true;
        gameExecutionTimer = new Timer();
        gameExecutionTimer.schedule(new TimerTask() {
            @Override
            public void run() {
                makeOneStep();
                adjGenerationsHaveDifference = false;
                for (int i = 0; i < rowAmount; i++) {
                    for (int j = 0; j < columnAmount; j++) {
                        if (currentGeneration[i][j].isAlive() != gameFieldData.getCellState(i, j)) {
                            adjGenerationsHaveDifference = true;
                        }
                    }
                }
                if(!isGameRunning){
                    gameExecutionTimer.cancel();
                    gameExecutionTimer.purge();
                    return;
                }
                if ((!adjGenerationsHaveDifference) || (aliveCellsAmount == 0)) {
                    gameExecutionTimer.cancel();
                    gameExecutionTimer.purge();
                    isGameRunning = false;

                    startGameButton.setEnabled(true);
                    cleanCellsButton.setEnabled(true);
                    oneStepButton.setEnabled(true);
                    drawingModeCheckBox.setEnabled(true);
                    stopGameButton.setEnabled(false);
                    infoLabel.setText("Finished!");
                }
            }
        }, 0, 50);
    }

    public void stopGame(){
        isGameRunning = false;
    }

    public void cleanCells(){
        for (int i = 0; i < rowAmount; i++) {
            for (int j = 0; j < columnAmount; j++) {
                currentCell = gameFieldData.getValueAt(i, j);
                currentCell.setState(false);
                gameFieldData.setValueAt(currentCell, i, j);
            }
        }
        aliveCellsAmount = 0;
    }
}
