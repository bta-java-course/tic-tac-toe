package com.busedic.javacourse.tictactoe;

import java.util.Arrays;
import java.util.List;

public class GameState {
    private static char[][] theGameState;
    private static Player currentPlayer;
    private static List<GameState> nextMoves;

    public GameState(char[][] gameState) {
        theGameState = gameState;
    }

    public char[][] getGameState() {
        return theGameState;
    }

    public Player getCurrentPlayer() {
        return currentPlayer;
    }

    public static void setCurrentPlayer(Player theCurrentPlayer) {
        currentPlayer = theCurrentPlayer;

    }

    public void changeCurrentPlayer(Player theCurrentPlayer) {
        if (theCurrentPlayer.isX()) {
            for (Player p : Game.players) {
                if (!p.isX()) {
                    currentPlayer = p;
                }
            }
        } else {
            for (Player p : Game.players) {
                if (p.isX()) {
                    currentPlayer = p;
                }
            }
        }
    }


    public static boolean isGameOn() {
        for (int a = 0; a < 8; a++) {
            String line = null;
            switch (a) {
                case 0:
                    line = String.valueOf(theGameState[0][0]) + theGameState[1][0] + theGameState[2][0];
                    break;
                case 1:
                    line = String.valueOf(theGameState[0][1]) + theGameState[1][1] + theGameState[2][1];
                    break;
                case 2:
                    line = String.valueOf(theGameState[0][2]) + theGameState[1][2] + theGameState[2][2];
                    break;
                case 3:
                    line = String.valueOf(theGameState[0][0]) + theGameState[0][1] + theGameState[0][2];
                    break;
                case 4:
                    line = String.valueOf(theGameState[1][0]) + theGameState[1][1] + theGameState[1][2];
                    break;
                case 5:
                    line = String.valueOf(theGameState[2][0]) + theGameState[2][1] + theGameState[2][2];
                    break;
                case 6:
                    line = String.valueOf(theGameState[0][0]) + theGameState[1][1] + theGameState[2][2];
                    break;
                case 7:
                    line = String.valueOf(theGameState[2][0]) + theGameState[1][1] + theGameState[0][2];
                    break;
            }
            if (line.equals("xxx")) {
                System.out.println("\n>>> X win");
                return false;
            } else if (line.equals("ooo")) {
                System.out.println("\n>>> O win");
                return false;
            }
        }
        return isItDraw();
    }

    private static boolean isItDraw() {
        for (int i = 0; i < theGameState.length; i++) {
            for (int j = 0; j < theGameState[i].length; j++) {
                if (theGameState[i][j] == '\u0000') {
                    return true;
                }
            }
        }
        System.out.println(">>>it's a draw");
        return false;
    }


}