package com.buseduc.javacourse.tictactoe.artifacts;

import com.buseduc.javacourse.tictactoe.core.GameState;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

public class Board {
    static enum Chip {
        CROSS, ZERO;
    }
    private GameState gameState;
    private int boardSize;

   public Board() {
       this(3);
   }
    public Board(int boardSize) {
        this.boardSize = boardSize;
        this.gameState = new GameState(new int[getGameStateSize()]);
    }

/*TODO:  MOve this metod to Game*/
    public void move(String cell) {
        Map<String, Integer> js = new HashMap<>();
        js.put("a", 0);
        js.put("b", 1);
        js.put("c", 2);
        int j = js.get(cell.substring(0, 1));
        int i = Integer.parseInt(cell.substring(1,2)) - 1;
        System.out.println(j + " " + i);
        int[] newState = Arrays.copyOf(gameState.getGameState(), getGameStateSize());
        newState[3 * i + j] = 1;
        GameState newGameState = new GameState(newState, this.gameState);
        this.gameState = newGameState;
    }

    public GameState getGameState() {
        return gameState;
    }

    public void setGameState(GameState gameState) {
        this.gameState = gameState;
    }

    public void render() {
        for(int i = boardSize - 1; i >= 0; i--) {
            String row = (i + 1) + " | ";
            for(int j = 0; j < boardSize; j++) {
                row += gameState.getGameState()[3 * i + j] + " | ";
            }
            System.out.println(row);
        }
        System.out.println("    a   b   c");
    }
    public int getGameStateSize() {
        return (int) Math.pow(boardSize, 2);
    }

}
