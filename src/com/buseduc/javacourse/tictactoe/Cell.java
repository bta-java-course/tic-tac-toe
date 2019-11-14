package com.buseduc.javacourse.tictactoe;

import com.buseduc.javacourse.tictactoe.enums.Chip;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.text.Font;

public class Cell extends Label {

    private final static int TEXT_SIZE = 25;
    private final static int CELL_SIZE = 49;
    private String abcNumPos;
    private int[] arrayPos;
    private Chip chip;

    public Cell(String abcPos, String numPos, int[] arrayPos) {
        this.abcNumPos = abcPos.concat(numPos);
        this.arrayPos = arrayPos;
        this.setFont(new Font(TEXT_SIZE));
        this.setMinWidth(CELL_SIZE);
        this.setMinHeight(CELL_SIZE);
        this.setAlignment(Pos.BASELINE_CENTER);
        this.chip = Chip.X; // for test
        this.setOnMouseClicked(e -> Cell.this.setText(chip.name()));
    }

    public String getAbcNumPos() {
        return abcNumPos;
    }

    public void setChip(Chip chip) {
        this.chip = chip;
        this.setText(chip.name());
    }

    @Override
    public String toString() {
        return abcNumPos;
    }
}
