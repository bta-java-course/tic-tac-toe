package com.buseduc.javacourse.tictactoe.core;

import com.buseduc.javacourse.tictactoe.Player;

import java.util.Arrays;
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
        System.out.println(this);
        System.out.println(this.previous);
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
