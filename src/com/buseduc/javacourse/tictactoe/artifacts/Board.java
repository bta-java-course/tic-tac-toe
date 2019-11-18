package com.buseduc.javacourse.tictactoe.artifacts;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

public class Board {
    static enum Chip {
        CROSS, ZERO;
    }
    private int[] gameState;
    private int boardSize;

   public Board() {
       this(3);
   }
    public Board(int boardSize) {
        this.boardSize = boardSize;
        this.gameState = new int[9];
    }

/*TODO:  MOve this metod to GameState*/
    public void setChip(String cell) {
        Map<String, Integer> js = new HashMap<>();
        js.put("a", 0);
        js.put("b", 1);
        js.put("c", 2);
        int j = js.get(cell.substring(0, 1));
        int i = Integer.parseInt(cell.substring(1,2)) - 1;
        System.out.println(j + " " + i);
        gameState[3 * i + j] = 1;
    }

    public int[] getGameState() {
        return gameState;
    }

    public void setGameState(int[] gameState) {
        this.gameState = gameState;
    }

    public void render() {
        String toRender = "[";
        toRender += Arrays.stream(this.gameState)
                .mapToObj(String::valueOf)
                .collect(Collectors.joining(","));
        toRender+= "]";
        System.out.println(toRender);

        for(int i = boardSize - 1; i >= 0; i--) {
            String row = (i + 1) + " | ";
            for(int j = 0; j < boardSize; j++) {
                row += gameState[3 * i + j] + " | ";
            }
            System.out.println(row);
        }
        System.out.println("    a   b   c");
    }
}
