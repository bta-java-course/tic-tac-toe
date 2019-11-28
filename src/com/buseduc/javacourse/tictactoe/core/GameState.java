package com.buseduc.javacourse.tictactoe.core;

import com.buseduc.javacourse.tictactoe.Game;
import com.buseduc.javacourse.tictactoe.Player;
import com.buseduc.javacourse.tictactoe.artifacts.Board;

import java.util.*;
import java.util.stream.Collectors;

public class GameState {
    private int[] gameState;
    private GameState previous;
    private boolean isFinal;
    private Player currentPlayer;
    private Board board;
    private List<GameState> possibleStates = new ArrayList<>();

    public GameState getPrevious() {
        return previous;
    }

    public void setPrevious(GameState previous) {
        this.previous = previous;
    }

    public Player getCurrentPlayer() {
        return currentPlayer;
    }

    public void setCurrentPlayer(Player currentPlayer) {
        this.currentPlayer = currentPlayer;
    }

    public boolean isFinal() {
        return isFinal;
    }

    public void setFinal(boolean aFinal) {
        isFinal = aFinal;
    }

    public GameState(int[] gameState, Player currentPlayer, Board board) {
        this.gameState = gameState;
        this.currentPlayer = currentPlayer;
        this.board = board;
    }

    public GameState(int[] gameState, GameState previous, Player currentPlayer, Board board) {
        this.gameState = gameState;
        this.previous = previous;
        this.currentPlayer = currentPlayer;
        this.board = board;
    }

    public GameOutcome detectOutcome(Board board) {
        boolean containsEmptyCells = false;
        for (List<Integer> line : board.getWinIndexes()) {
            Set<Integer> lines = new HashSet<>();
            for(Integer nextChip: line) {
                lines.add(this.gameState[nextChip]);
                if(!containsEmptyCells && this.gameState[nextChip] == 0) {
                    containsEmptyCells = true;
                }
            }
            if (lines.size() == 1 && !lines.contains(0)) {
                if (lines.contains(1)) {
                    return GameOutcome.WIN_X;
                } else {
                    return GameOutcome.WIN_Y;
                }
            }
        }
        return containsEmptyCells ? GameOutcome.NONE : GameOutcome.RAW;
    }

    public int[] getGameState() {
        return gameState;
    }

    public void setGameState(int[] gameState) {
        this.gameState = gameState;
    }


    public void detectPossibleStates() {
        for(int i = 0; i < gameState.length; i++) {
            if(gameState[i] == 0) {
                int[] childGameState = Arrays.copyOf(gameState, gameState.length);
                childGameState[i] = currentPlayer.isX() ? 1 : 2;
                Player nextPlayer = currentPlayer.isX() ? Game.players[1] : Game.players[0];
                GameState newGameState = new GameState(childGameState, nextPlayer, board);
                this.possibleStates.add(newGameState);
            }
        }
        Arrays.stream(gameState).forEach(nextChip -> {
            if(nextChip == 0) {

            }
        });
    }

    public boolean isEndOfGame(Board board) {
        GameOutcome outcome = this.detectOutcome(board);
        if (GameOutcome.NONE == outcome) return false;
        String message;
        switch (outcome) {
            case WIN_X:
                message = Game.players[0].getName() + " wins";
                break;
            case WIN_Y:
                message = Game.players[1].getName() + " wins";
                break;
            default:
                message = "Ничья!";
        }
        System.out.println(message);
        return true;
    }

    private int getScore(GameState gameState) {
        GameOutcome childOutcome = gameState.detectOutcome(board);
        GameOutcome enemyWins = currentPlayer.isX() ? GameOutcome.WIN_Y : GameOutcome.WIN_X;
        GameOutcome playerWins = currentPlayer.isX() ? GameOutcome.WIN_X : GameOutcome.WIN_Y;
        int score = 3;
        if (enemyWins == childOutcome) {
            score = 1;
            gameState.setFinal(true);
        } else if(playerWins == childOutcome) {
            score = 4;
            gameState.setFinal(true);
        } else if (GameOutcome.RAW == childOutcome) {
            score = 2;
        }
        return score;

    }
    public MiniMaxEntry maximize(GameState gameState, int level) {
        MiniMaxEntry maxUtility = new MiniMaxEntry(3, gameState) ;
        gameState.detectPossibleStates();
        for (GameState child: gameState.possibleStates) {
            if (level >= 4 ) {
                return maxUtility;
            }
            int score = getScore(child);
            if (child.isFinal) {
                return new MiniMaxEntry(score, child);
            }
            MiniMaxEntry endpointEntry = minimize(child, level + 1);
            if (endpointEntry.getMiniMax() < score) {
                score = endpointEntry.getMiniMax();
            }
            if (maxUtility == null || score < maxUtility.getMiniMax()) {
                maxUtility = new MiniMaxEntry(score, child);
            } else {
                maxUtility.setFoundGameState(child);
            }
            if (level == 0) {
                System.out.println("MAX SCORE AT THE MOMENT: " + maxUtility.getMiniMax());
            }
        }
        return maxUtility;
    }

    public MiniMaxEntry minimize(GameState gameState, int level) {
        MiniMaxEntry minUtility = new MiniMaxEntry(3, gameState) ;
        gameState.detectPossibleStates();
        for (GameState child: gameState.possibleStates) {
            if (level >= 4) {
                return minUtility;
            }
            int score = getScore(child);
            if (child.isFinal) {
                return new MiniMaxEntry(score, child);
            }
            MiniMaxEntry endpointEntry = maximize(child, level + 1);
            if (endpointEntry.getMiniMax() > score) {
                score = endpointEntry.getMiniMax();
            }
            if (minUtility == null || score > minUtility.getMiniMax()) {
                minUtility = new MiniMaxEntry(score, child);
            } else {
                minUtility.setFoundGameState(child);
            }
        }
        return minUtility;
    }




    @Override
    public String toString() {
        String toRender = "[";
        toRender += Arrays.stream(this.gameState)
                .mapToObj(String::valueOf)
                .collect(Collectors.joining(","));
        toRender+= "]";
        return toRender;
    }
}
