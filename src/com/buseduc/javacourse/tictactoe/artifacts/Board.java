package com.buseduc.javacourse.tictactoe.artifacts;

import com.buseduc.javacourse.tictactoe.Game;
import com.buseduc.javacourse.tictactoe.Player;
import com.buseduc.javacourse.tictactoe.core.GameState;

import java.util.Arrays;

public class Board {
    static enum Chip {
        CROSS, ZERO;
    }
    private GameState gameState;
    private int size;

   public Board(Player player) {
       this(3, player);
   }
    public Board(int size, Player player) {
        this.size = size;
        this.gameState = new GameState(new int[getGameStateSize()], player);
    }

    public int getSize() {
       return size;
    }
    public void move() {
       Player player = gameState.getCurrentPlayer();
       int[] newState = Arrays.copyOf(gameState.getGameState(), getGameStateSize());
       Cell cell = new Cell(this, "");
       newState[cell.getCellIndexInState()] = player.isX() ? 1 : 2;
       GameState newGameState = new GameState(newState, gameState, Game.getAnotherPlayer(player));
       gameState = newGameState;
       render();
       move();
    }

    public int getCellFromHuman() {
        return 0;
    }

    public GameState getGameState() {
        return gameState;
    }

    public void setGameState(GameState gameState) {
        this.gameState = gameState;
    }

    public void render() {
        for(int i = size - 1; i >= 0; i--) {
            String row = (i + 1) + " | ";
            for(int j = 0; j < size; j++) {
                row += gameState.getGameState()[3 * i + j] + " | ";
            }
            System.out.println(row);
        }
        System.out.println("    a   b   c");
    }
    public int getGameStateSize() {
        return (int) Math.pow(size, 2);
    }

}
