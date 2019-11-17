package com.buseduc.javacourse.tictactoe;

import javafx.application.Application;
import java.util.ArrayList;
import java.util.List;

public class Game {

    private static final List<Player> PLAYERS = new ArrayList<>();
    private static Player activePlayer = new Player();
    private static Board board;
    private static GameState gameState;
    private static int winLine = 10;

    static {
        PLAYERS.add(activePlayer);
    }

    public Game() {

    }

    public static void main(String[] args) {
        board = new Board(10);
        gameState = new GameState(board, winLine);
        PLAYERS.add(new Computer());
        activePlayer = PLAYERS.get(0);
        Application.launch(RenderFX.class, args);
    }

    public static Player getActivePlayer() {
        return activePlayer;
    }

    public static void setActivePlayer(Player activePlayer) {
        Game.activePlayer = activePlayer;
    }

    public static Board getBoard() {
        return board;
    }

    public static GameState getGameState() {
        return gameState;
    }

    public static List<Player> getPLAYERS() {
        return PLAYERS;
    }
}
