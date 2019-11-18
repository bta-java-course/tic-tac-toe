package com.buseduc.javacourse.tictactoe.artifacts;

import com.buseduc.javacourse.tictactoe.Game;
import com.buseduc.javacourse.tictactoe.Player;
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

   public Board(Player player) {
       this(3, player);
   }
    public Board(int boardSize, Player player) {
        this.boardSize = boardSize;
        this.gameState = new GameState(new int[getGameStateSize()], player);
    }

    public void start() {
       move(gameState.getCurrentPlayer());
    }

/*TODO:  MOve this metod to Game*/
    public void move(Player player) {
        Map<String, Integer> js = new HashMap<>();
        js.put("a", 0);
        js.put("b", 1);
        js.put("c", 2);
        int[] newState = Arrays.copyOf(gameState.getGameState(), getGameStateSize());
        boolean isCellValid = false;
        int newIndex = 0;
        while (!isCellValid) {
            String cell = player.getCell();
            int j = js.get(cell.substring(0, 1));
            int i = Integer.parseInt(cell.substring(1,2)) - 1;
            newIndex = 3 * i + j;
            if (newState[newIndex] == 0) {
                isCellValid = true;
            }
        }
        newState[newIndex] = player.isX() ? 1 : 2;
        GameState newGameState = new GameState(newState, this.gameState, Game.getAnotherPlayer(player));
        this.gameState = newGameState;
        this.render();
        move(newGameState.getCurrentPlayer());
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
