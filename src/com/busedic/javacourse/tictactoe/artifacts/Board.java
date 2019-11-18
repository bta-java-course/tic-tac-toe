package com.busedic.javacourse.tictactoe.artifacts;

public class Board {
    static enum Chip {
        CROSS, ZERO
    }

    private int[][] gameState;
    private int[][] tempGameState = new int[3][3];
    private int boardSize;
    int forMove;

    public Board() {
        this(3);
    }

    public Board(int boardSize) {
        this.boardSize = boardSize;
        this.gameState = new int[3][3];
    }

    public void setChip(String move) {
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
        gameState[a-1][forMove] = 1;
    }

    public void render() {
        for (int i = boardSize - 1; i >= 0; i--) {
            String row = (i + 1) + " | ";
            for (int j = 0; j < boardSize; j++) {
                row += gameState[i][j] + " | ";
            }
            System.out.println(row);
        }
        System.out.println("    a   b   c");
    }
}