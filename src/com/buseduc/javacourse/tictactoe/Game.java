package com.buseduc.javacourse.tictactoe;

import javafx.application.Application;

public class Game {

    private static Board board;
    private static GameState gameState;
    private static int winLine = 4;
    private static int boardSize = 4;

    static {
        board = new Board(boardSize);
        gameState = new GameState(board, winLine);
    }

    public Game() {

    }

    public static void main(String[] args) {
        gameState.setActivePlayer(new Player());
        gameState.getPlayers().add(gameState.getActivePlayer());
        gameState.getPlayers().add(Computer.getInstance());
        Application.launch(RenderFX.class, args);
    }

    public static Board getBoard() {
        return board;
    }

    public static GameState getGameState() {
        return gameState;
    }

}
