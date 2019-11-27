package com.buseduc.javacourse.tictactoe.core;

import com.buseduc.javacourse.tictactoe.Game;
import com.buseduc.javacourse.tictactoe.Player;
import com.buseduc.javacourse.tictactoe.artifacts.Board;

import java.util.*;
import java.util.stream.Collectors;

public class GameState {
    private int[] gameState;
    private GameState previous;
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

    public boolean isEndOfGame(GameOutcome outcome){
        if (GameOutcome.NONE == outcome) {
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
            return false;
    }

    public MiniMaxEntry maximize(GameState gameState) {
        GameOutcome curOutCome = gameState.detectOutcome(this.board);
        if (GameOutcome.NONE != curOutCome) {
            if(GameOutcome.WIN_X == curOutCome) {
                int minimax = currentPlayer.isX() ? 4 : 1;
                return new MiniMaxEntry(minimax, gameState);
            }
        }
        MiniMaxEntry maxUtility = null ;
        for (GameState child: possibleStates) {
            GameOutcome curOutcome = child.detectOutcome(board);
            GameOutcome enemyWins = currentPlayer.isX() ? GameOutcome.WIN_X : GameOutcome.WIN_Y;
            GameOutcome playerWins = currentPlayer.isX() ? GameOutcome.WIN_Y : GameOutcome.WIN_X;
            int weight = 0;
            if (enemyWins == curOutCome) {
                weight = 1;
            } else if(playerWins == curOutCome) {
                weight = 4;
            } else if (GameOutcome.RAW == curOutCome) {
                weight = 2;
            } else {
                weight = 3;
            }
            if (maxUtility == null || weight > maxUtility.getMiniMax()) {
                maxUtility = new MiniMaxEntry(weight, child);
            }
        }
        return maxUtility;
    }
    public MiniMaxEntry minimize(GameState gameState) {
        GameOutcome curOutCome = gameState.detectOutcome(this.board);
        if (GameOutcome.NONE != curOutCome) {
            if(GameOutcome.WIN_X == curOutCome) {
                int minimax = currentPlayer.isX() ? 4 : 1;
                return new MiniMaxEntry(minimax, gameState);
            }
        }
        MiniMaxEntry maxUtility = new MiniMaxEntry(3, gameState) ;
        for (GameState child: possibleStates) {
            GameOutcome enemyWins = currentPlayer.isX() ? GameOutcome.WIN_X : GameOutcome.WIN_Y;
            if (enemyWins == child.detectOutcome(board)) {
                maxUtility = new MiniMaxEntry(1, child);
            }
        }
        return maxUtility;
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
