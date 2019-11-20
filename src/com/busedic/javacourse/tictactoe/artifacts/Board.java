package com.busedic.javacourse.tictactoe.artifacts;

import com.busedic.javacourse.tictactoe.GameState;
import com.busedic.javacourse.tictactoe.Player;

public class Board {
    static enum Chip {
        CROSS, ZERO
    }

    private GameState gameState;
    private int boardSize;
    private int forMove;


    public Board() {
        this(3);
    }

    public Board(int boardSize) {
        this.boardSize = boardSize;
        this.gameState = new GameState(new char[3][3]);
    }

    public void setChip(String move) {
        Player player = gameState.getCurrentPlayer();
        String b = move.substring(0, 1);
        int a = Integer.parseInt(move.substring(1, 2));
        switch (b) {
            case "a":
                forMove = 0;
                break;
            case "b":
                forMove = 1;
                break;
            case "c":
                forMove = 2;
                break;
        }
        if (player.isX()) {
            gameState.getGameState()[a - 1][forMove] = 'x';
        } else {
            gameState.getGameState()[a - 1][forMove] = 'o';
        }
        gameState.changeCurrentPlayer(player);
        render();
    }

    public void render() {
        for (int i = boardSize - 1; i >= 0; i--) {
            String row = (i + 1) + " | ";
            for (int j = 0; j < boardSize; j++) {
                row += gameState.getGameState()[i][j] + " | ";
            }
            System.out.println(row);
        }
        System.out.println("    a   b   c");
    }
}