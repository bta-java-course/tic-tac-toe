package com.buseduc.javacourse.tictactoe.artifacts;

import com.buseduc.javacourse.tictactoe.core.GameState;


public class Cell {
    String cellStr;
    Board board;
    int index = -1;
    public Cell(Board board, String cellStr) {
        this.cellStr = cellStr;
        this.board = board;
    }

    public void calculateIndex() {
        if (cellStr.length() < 2) {
            index = -1;
            return;
        }
        int i = -1;
        int j = (int)cellStr.getBytes()[0] - 97;
        try {
            i = Integer.parseInt(cellStr.substring(1)) - 1;
        } catch (NumberFormatException ne) {
            return;
        }
        if (j >= board.getSize() || i >= board.getSize()) {
            index = -1;
            return;
        }
        index = board.getSize() * i + j;
    }

    public boolean isValidCellForInput() {
        return index >= 0 && board.getGameState().getGameState()[index] == 0;
    }

    public int getCellIndexInState() {
        while (!isValidCellForInput()) {
            this.cellStr = board.getGameState().getCurrentPlayer().getCell();
            calculateIndex();
        }
        return index;
    }
}
