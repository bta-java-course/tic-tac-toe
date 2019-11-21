package com.buseduc.javacourse.tictactoe.core;

import com.buseduc.javacourse.tictactoe.Player;
import com.buseduc.javacourse.tictactoe.artifacts.Board;

import java.util.*;
import java.util.stream.Collectors;

public class GameState {
    private int[] gameState;
    private GameState previous;
    private Player currentPlayer;

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

    public GameState(int[] gameState, Player currentPlayer) {
        this.gameState = gameState;
        this.currentPlayer = currentPlayer;
    }

    public GameState(int[] gameState, GameState previous, Player currentPlayer) {
        this.gameState = gameState;
        this.previous = previous;
        this.currentPlayer = currentPlayer;
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
