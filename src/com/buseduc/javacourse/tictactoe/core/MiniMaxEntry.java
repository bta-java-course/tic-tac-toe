package com.buseduc.javacourse.tictactoe.core;

public class MiniMaxEntry {
    private int miniMax;
    private GameState foundGameState;

    public MiniMaxEntry(int miniMax, GameState foundGameState) {
        this.miniMax = miniMax;
        this.foundGameState = foundGameState;
    }

    public int getMiniMax() {
        return miniMax;
    }

    public void setMiniMax(int miniMax) {
        this.miniMax = miniMax;
    }

    public GameState getFoundGameState() {
        return foundGameState;
    }

    public void setFoundGameState(GameState foundGameState) {
        this.foundGameState = foundGameState;
    }
}
