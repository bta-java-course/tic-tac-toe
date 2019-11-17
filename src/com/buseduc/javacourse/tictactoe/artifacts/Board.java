package com.buseduc.javacourse.tictactoe.artifacts;

import java.util.Scanner;

public class Board {
    private final GameState currentState;
    private final String[] board = new String[9];
    private final Scanner input = new Scanner(System.in);
    private Chip currentPlayer = Chip.CROSS;

    public Board(GameState gameState) {
        this.currentState = gameState;

        System.out.println("Board is divided into 9 cells, enter cell number to make a move.");
        for (int i = 0; i < 9; i++) {
            board[i] = String.valueOf(i + 1);
        }

        this.render();
    }

    private void render() {
        System.out.println("_" + board[0] + "_|_" + board[1] + "_|_" + board[2] + "_");
        System.out.println("_" + board[3] + "_|_" + board[4] + "_|_" + board[5] + "_");
        System.out.println(" " + board[6] + " | " + board[7] + " | " + board[8] + " ");
    }

    public void play() {
        while (currentState == GameState.PLAYING) {
            if (currentPlayer == Chip.CROSS) {
                System.out.print("Player 'X', make a move: ");
            } else {
                System.out.print("Player 'O', make a move: ");
            }

            int inCellNumber = input.nextInt();
            if (!(inCellNumber > 0 && inCellNumber <= 9)) {
                System.out.println("Invalid cell number, re-enter the number: ");
                continue;
            }

            if (board[inCellNumber - 1].equals(String.valueOf(inCellNumber))) {
                board[inCellNumber - 1] = currentPlayer.toString();
                currentPlayer = (currentPlayer == Chip.CROSS) ? Chip.ZERO : Chip.CROSS;
                this.render();
            } else {
                System.out.println("Cell already taken, make a different move: ");
            }
        }
    }
}
