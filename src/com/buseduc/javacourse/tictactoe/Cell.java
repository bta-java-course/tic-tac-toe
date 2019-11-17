package com.buseduc.javacourse.tictactoe;

import com.buseduc.javacourse.tictactoe.enums.Chip;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Font;

public class Cell extends Label {

    private final static int TEXT_SIZE = 25;
    private final static int CELL_SIZE = 49;
    //private String abcNumPos;
    private int[] arrayPos;
    private Chip chip;
    private boolean isClicked = false;

    public Cell(String abcPos, String numPos, int[] arrayPos) {
        this.arrayPos = arrayPos;
        this.setFont(new Font(TEXT_SIZE));
        this.setMinWidth(CELL_SIZE);
        this.setMinHeight(CELL_SIZE);
        this.setAlignment(Pos.BASELINE_CENTER);
        this.setOnMouseClicked(getEventHandler());
    }

    public Chip getChip() {
        return chip;
    }

    @Override
    public String toString() {
        return "(" + arrayPos[0] + ", " + arrayPos[1] + ")";
    }

    public EventHandler<MouseEvent> getEventHandler() {
        return e -> {
            if (!isClicked) {
                this.chip = Game.getActivePlayer().getChip();
                Cell.this.setText(chip.name());
                isClicked = true;
                Game.getGameState().hasPlayerWinPoss(Game.getActivePlayer());
                Game.getGameState().isDeadHeat();
                Player otherPlayer = Game.getPLAYERS().stream()
                        .filter(player -> !player.equals(Game.getActivePlayer()))
                        .findFirst().get();
                Game.setActivePlayer(otherPlayer);
                if (Game.getGameState().isFinished()) {
                    System.out.println(Game.getGameState().getEndMsg());
                }
            }
        };
    }

    public void setArrayPos(int[] arrayPos) {
        this.arrayPos = arrayPos;
    }

    public int[] getArrayPos() {
        return arrayPos;
    }
}
