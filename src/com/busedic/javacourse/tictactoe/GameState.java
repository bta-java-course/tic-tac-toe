package com.busedic.javacourse.tictactoe;

import java.util.ArrayList;
import java.util.List;

public class GameState {
    private static char[][] theGameState;
    private static Player currentPlayer;
    private static List<GameState> nextMoves;
    private static GameOutCome curOutCome;
    private static List<Point> availablePoints;
    public static Point computersMove;

    public GameState(char[][] gameState) {
        theGameState = gameState;
    }

    public static char[][] getGameState() {
        return theGameState;
    }

    public static Player getCurrentPlayer() {
        return currentPlayer;
    }

    public static GameOutCome getCurOutCome() {
        return curOutCome;
    }

    public static void setCurrentPlayer(Player theCurrentPlayer) {
        currentPlayer = theCurrentPlayer;

    }

    public static void changeCurrentPlayer(Player theCurrentPlayer) {
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

    public static boolean isCellFree(int a, int b) {
        if (theGameState[a][b] == '\u0000') {
            return true;
        }
        return false;
    }

    public static List<Point> getAvailableStates() {
        availablePoints = new ArrayList<>();
        for (int i = 0; i < 3; ++i) {
            for (int j = 0; j < 3; ++j) {
                if (theGameState[i][j] == 0) {
                    availablePoints.add(new Point(i, j));
                }
            }
        }
        return availablePoints;
    }

    public static boolean isGameOn() {
            if (isXwon()) {
                curOutCome = GameOutCome.WIN_X;
                System.out.println("\n>>> X win");
                return false;
            } else if (isYwon()) {
                curOutCome = GameOutCome.WIN_Y;
                System.out.println("\n>>> O win");
                return false;
            }
        curOutCome = GameOutCome.NONE;
        return isItNotDraw();
    }

    public static boolean isXwon() {
        for (int a = 0; a < 8; a++) {
            String line = null;
            line = switchCasae(a, line);
            if (line.equals("xxx")) {
                return true;
            }
        }
        return false;
    }
    public static boolean isYwon() {
        for (int a = 0; a < 8; a++) {
            String line = null;
            line = switchCasae(a, line);
            if (line.equals("ooo")) {
                return true;
            }
        }
        return false;
    }

    private static String switchCasae(int a, String line) {
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
        return line;
    }

    private static boolean isItNotDraw() {
        for (int i = 0; i < theGameState.length; i++) {
            for (int j = 0; j < theGameState[i].length; j++) {
                if (theGameState[i][j] == '\u0000') {
                    return true;
                }
            }
        }
        curOutCome = GameOutCome.DRAW;
        System.out.println(">>>it's a draw");
        return false;
    }


    public static int minimax(int depth, int turn) {

            if (isXwon()) return +1;
            if (isYwon()) return -1;

        List<Point> pointsAvailable = getAvailableStates();

        if (pointsAvailable.isEmpty()) return 0;

        int min = Integer.MAX_VALUE, max = Integer.MIN_VALUE;

        for (int i = 0; i < pointsAvailable.size(); ++i) {
            Point point = pointsAvailable.get(i);
            if (turn == 1) {
                placeAMove(point, Game.players[1]);
                int currentScore = minimax(depth + 1, 2);
                max = Math.max(currentScore, max);

                if (depth == 0) System.out.println("Score for position " + (i + 1) + " = " + currentScore);
                if (currentScore >= 0) {
                    if (depth == 0) computersMove = point;
                }
                if (currentScore == 1) {
                    theGameState[point.x][point.y] = '\u0000';
                    break;
                }
                if (i == pointsAvailable.size() - 1 && max < 0) {
                    if (depth == 0) computersMove = point;
                }
            } else if (turn == 2) {
                placeAMove(point, Game.players[0]);
                int currentScore = minimax(depth + 1, 1);
                min = Math.min(currentScore, min);
                if (min == -1) {
                    theGameState[point.x][point.y] = '\u0000';
                    break;
                }
            }
            theGameState[point.x][point.y] = '\u0000'; //Reset this point
        }
        return turn == 1 ? max : min;
    }

    public static void placeAMove(Point point, Player player) {
        if (player.isX()) {
            theGameState[point.x][point.y] = 'x';
        } else if(!player.isX()) {
            theGameState[point.x][point.y] = 'o';
        }
    }

}