package com.buseduc.javacourse.tictactoe;

import com.buseduc.javacourse.tictactoe.enums.Chip;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Font;
import java.util.ArrayList;

public class Cell extends Label {

    private static final int TEXT_SIZE = 25;
    private static final int CELL_SIZE = 50;
    private int[] arrayPos;
    private Chip chip;
    private boolean isClicked = false;


    public Cell(int[] arrayPos) {
        this.arrayPos = arrayPos;
        this.setFont(new Font(TEXT_SIZE));
        this.setMinWidth(CELL_SIZE);
        this.setMinHeight(CELL_SIZE);
        this.setAlignment(Pos.BASELINE_CENTER);
        this.setOnMousePressed(getEventHandler());
        this.setOnMouseReleased(getEventHandlerComp());
    }

    public Chip getChip() {
        return chip;
    }

    public void setChip(Chip chip) {
        this.chip = chip;
    }

    public boolean isClicked() {
        return isClicked;
    }

    public void setClicked(boolean clicked) {
        isClicked = clicked;
    }

    @Override
    public String toString() {
        return "(" + arrayPos[0] + ", " + arrayPos[1] + ")";
    }

    private EventHandler<MouseEvent> getEventHandler() {
        return e -> {
            if (!isClicked) {
                Cell.this.releasePlayersAction();
                if (Game.getGameState().isFinished()) {
                    System.out.println(Game.getGameState().getEndMsg());
                }
            }
        };
    }

    private EventHandler<MouseEvent> getEventHandlerComp() {
        return e -> {
                if (Game.getGameState().getActivePlayer().equals(Computer.getInstance())) {
                    new Thread(() -> {
                        try {
                            new ArrayList<>(Thread.getAllStackTraces().keySet()).get(0);
                            Thread.sleep(700);
                        } catch (InterruptedException ex) {
                            ex.printStackTrace();
                        }
                        Platform.runLater(Computer.getInstance()::actionALevel);
                    }).start();
                }
            };
    }

    private void releasePlayersAction() {
        GameState gs = Game.getGameState();
        if (gs.getActivePlayer().equals(gs.getPlayers().get(0))) {
            Cell.this.chip = gs.getActivePlayer().getChip();
            Cell.this.setText(chip.name());
            isClicked = true;
            gs.hasPlayerWinPoss(gs.getActivePlayer());
            gs.isDeadHeat();
            Player otherPlayer = gs.getPlayers().stream()
                    .filter(player -> !player.equals(gs.getActivePlayer()))
                    .findFirst().get();
            gs.setActivePlayer(otherPlayer);
        }
    }

    public void setArrayPos(int[] arrayPos) {
        this.arrayPos = arrayPos;
    }

    public int[] getArrayPos() {
        return arrayPos;
    }
}
