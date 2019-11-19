package com.buseduc.javacourse.tictactoe;

import com.buseduc.javacourse.tictactoe.enums.Chip;

public class Player {
    private Chip chip;
    private String name;
    private static Board board;
    private static GameState gameState;

    static {
        board = Game.getBoard();
        gameState = Game.getGameState();
    }

    public Player() {
        this.name = setName();
        this.chip = setChip();
    }

    public Player(Chip chip) {
        this();
        this.chip = chip;
    }

    public Chip getChip() {
        return chip;
    }

    public String getName() {
        return name;
    }

    private String setName() {
        return "Player " + (gameState.getPlayers().size() + 1);
    }

    private Chip setChip() {
        if (gameState.getPlayers().size() == 0)
            return Chip.X;
        else return Chip.O;
    }

}
