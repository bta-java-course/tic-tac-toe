package com.buseduc.javacourse.tictactoe.artifacts;

import com.buseduc.javacourse.tictactoe.Game;
import com.buseduc.javacourse.tictactoe.Player;
import com.buseduc.javacourse.tictactoe.core.GameOutcome;
import com.buseduc.javacourse.tictactoe.core.GameState;
import com.buseduc.javacourse.tictactoe.core.MiniMaxEntry;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Board {
    static enum Chip {
        CROSS, ZERO;
    }
    private List<List<Integer>> winIndexes = new ArrayList<>();
    private GameState gameState;
    private int size;

   public Board(Player player) {
       this(3, player);
   }
    public Board(int size, Player player) {
        this.size = size;
        this.gameState = new GameState(new int[getGameStateSize()], player,this);
        createWinIndexes();

    }

    public int getSize() {
       return size;
    }

    public GameState getGameState() {
        return gameState;
    }

    public void setGameState(GameState gameState) {
        this.gameState = gameState;
    }

    public void setWinIndexes(List<List<Integer>> winIndexes) {
        this.winIndexes = winIndexes;
    }

    public List<List<Integer>> getWinIndexes() {
        return winIndexes;
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

    public void moveAi() {
        MiniMaxEntry bestMove = gameState.maximize(gameState);
        this.gameState = bestMove.getFoundGameState();
        render();
        if(gameState.getCurrentPlayer().isAi()) {
            moveAi();
        } else {
            moveHuman();
        }
    }
    public void moveHuman() {
        Player player = gameState.getCurrentPlayer();
        int[] newState = Arrays.copyOf(gameState.getGameState(), getGameStateSize());
        Cell cell = new Cell(this, "");
        GameState newGameState = null;
        boolean isDangerousMove = true;
        while(isDangerousMove) {
            cell.index = -1;
            newState = Arrays.copyOf(gameState.getGameState(), getGameStateSize());
            newState[cell.getCellIndexInState()] = player.isX() ? 1 : 2;
            newGameState = new GameState(newState, gameState, Game.getAnotherPlayer(player), this);
            GameOutcome curOutCome = newGameState.detectOutcome(this);
            if (GameOutcome.NONE != curOutCome) {
                String message;
                switch (curOutCome) {
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
                return;
            }
            newGameState.detectPossibleStates();
            boolean nextMove = false;
            if (newGameState.minimize(newGameState).getMiniMax() == 1) {
                System.out.println("THIS MOVE IS DANGEROUS! new move? (Y/N)");
                Scanner scanner = new Scanner(System.in);
                String answer = scanner.next().toLowerCase();
                if (!"y".equals(answer)) {
                    nextMove = true;
                }
            } else {
                nextMove = true;
            }
            if (nextMove) {
                isDangerousMove = false;
                gameState = newGameState;
            }
        }
        render();
        if (gameState.getCurrentPlayer().isAi()) {
            moveAi();
        } else {
            moveHuman();
        }

    }


    private void createWinIndexes() {
        List<Integer> diag1 = new ArrayList<>();
        List<Integer> diag2 = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            List<Integer> horizontals = new ArrayList<>();
            List<Integer> verticals = new ArrayList<>();
            for (int j = 0; j < size; j++) {
                horizontals.add(size * i + j);
                verticals.add(size * j + i);
                if (i == j) {
                    diag1.add(size * i + j);
                }
                if (size - i - 1 == j) {
                    diag2.add(size * i + j);
                }
            }
            winIndexes.add(horizontals);
            winIndexes.add(verticals);
        }
        winIndexes.add(diag1);
        winIndexes.add(diag2);

    }


}
