package com.buseduc.javacourse.tictactoe;

import com.buseduc.javacourse.tictactoe.enums.Chip;

import java.util.Random;

public class Computer extends Player{

    private static Computer instance = new Computer();
    private static final String NAME = "Computer";
    private Board board;
    private GameState gameState;

    private Computer() {
        super(getOtherChip());
        board = Game.getBoard();
        gameState = Game.getGameState();
    }

    //Gets other chip. If player has X, computer takes O.
    private static Chip getOtherChip() {
        Chip chip = Game.getGameState().getActivePlayer().getChip();
        Chip otherChip;
        if (chip.equals(Chip.X))  otherChip = Chip.O;
        else otherChip = Chip.X;
        return otherChip;
    }

    @Override
    public String getName() {
        return NAME;
    }

    public static Computer getInstance() {
        return instance;
    }

    public void actionALevel() {
        int boardSize = board.getSize();
        while (gameState.getActivePlayer().equals(instance)) {
            Random randNum = new Random();
            int x = randNum.ints(0, boardSize).findFirst().getAsInt();
            int y = randNum.ints(0, boardSize).findFirst().getAsInt();
            if (!Game.getBoard().getBoardCells()[x][y].isClicked()) {
                board.getBoardCells()[x][y].setChip(instance.getChip());
                board.getBoardCells()[x][y].setClicked(true);
                board.getBoardCells()[x][y].setText(instance.getChip().name());
                gameState.hasPlayerWinPoss(gameState.getActivePlayer());
                gameState.isDeadHeat();
                if (gameState.isFinished()) {
                    System.out.println(gameState.getEndMsg());
                }
                gameState.setActivePlayer(gameState.getPlayers().get(0));
            }
        }
    }

}
