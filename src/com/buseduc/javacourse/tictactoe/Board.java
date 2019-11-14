package com.buseduc.javacourse.tictactoe;

import com.buseduc.javacourse.tictactoe.enums.LowerCaseLetters;
import javafx.geometry.HPos;
import javafx.geometry.Pos;
import javafx.geometry.VPos;
import javafx.scene.control.Label;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;
import javafx.scene.text.Font;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Board {

    private static final int CELL_SIZE = 50;
    private static List<String> abcList;

    static {
        abcList = Arrays.asList(LowerCaseLetters.values()).stream()
                .map(a -> a.name())
                .collect(Collectors.toList());
    }

    private int size;
    private Cell[][] boardCells;
    private int boardSize;

    public Board(int size) {
        this.size = size;
        boardCells = new Cell[size][size];
        boardSize = CELL_SIZE * size + 100;
    }

    public Cell[][] getBoardCells() {
        return boardCells;
    }

    public GridPane render() {
        GridPane outerPane = initOuterPane();
        GridPane deskPane = initDeskPane();
        outerPane.add(deskPane, 1, 0, size, size);
        fillBoardCells();
        for (int x = 0; x < size; x++) {
            for (int y = 0; y < size; y++) {
                boardCells[x][y].setText(boardCells[x][y].getAbcNumPos());
                deskPane.add(boardCells[x][y], y, x);
            }
        }
        return outerPane;
    }

    private void fillBoardCells() {
        for (int x = 0; x < size; x++) {
            for (int y = 0; y < size; y++) {
                String abcPos = abcList.get(y);
                String numPos = String.valueOf(size - x);
                Cell nextCell = new Cell(abcPos, numPos, new int[] {x, y});
                boardCells[x][y] = nextCell;
            }
        }
    }

    private GridPane initOuterPane() {
        GridPane outerPane = new GridPane();
        for(int i = 0; i < size+1; i++) {
            ColumnConstraints column = new ColumnConstraints(CELL_SIZE);
            column.setHalignment(HPos.CENTER);
            outerPane.getColumnConstraints().add(column);
            RowConstraints row = new RowConstraints(CELL_SIZE);
            row.setValignment(VPos.CENTER);
            outerPane.getRowConstraints().add(row);
        }
        fillAbcCells(outerPane);
        fillNumCells(outerPane);
        return outerPane;
    }

    private GridPane initDeskPane() {
        GridPane deskPane = new GridPane();
        deskPane.setAlignment(Pos.CENTER);
        for(int i = 0; i < size; i++) {
            ColumnConstraints column = new ColumnConstraints(CELL_SIZE);
            column.setHalignment(HPos.CENTER);
            deskPane.getColumnConstraints().add(column);
            RowConstraints row = new RowConstraints(CELL_SIZE);
            row.setValignment(VPos.CENTER);
            deskPane.getRowConstraints().add(row);
        }
        deskPane.setStyle("-fx-background-color: white; -fx-grid-lines-visible: true");
        return deskPane;
    }

    private void fillAbcCells(GridPane outerPane) {
        for (int x = 0; x < size; x++) {
            Label nextAbcLabel = getLabelForGrid();
            nextAbcLabel.setText(abcList.get(x));
            int columnIndex = x + 1;
            outerPane.add(nextAbcLabel,columnIndex, size);
        }
    }

    private void fillNumCells(GridPane outerPane) {
        for (int x = 0; x < size; x++) {
            Label nextAbcLabel = getLabelForGrid();
            nextAbcLabel.setText(String.valueOf(x+1));
            int rowIndex = size - 1 - x;
            outerPane.add(nextAbcLabel,0, rowIndex);
        }
    }

    private Label getLabelForGrid() {
        Label nextAbcLabel = new Label();
        nextAbcLabel.setFont(new Font(20));
        nextAbcLabel.setAlignment(Pos.BASELINE_CENTER);
        return nextAbcLabel;
    }

    public int getBoardSize() {
        return boardSize;
    }
}
