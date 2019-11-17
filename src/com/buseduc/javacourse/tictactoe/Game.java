package com.buseduc.javacourse.tictactoe;

import com.buseduc.javacourse.tictactoe.artifacts.Board;
import com.buseduc.javacourse.tictactoe.artifacts.GameState;

public class Game {

    public static void main(String[] args) {
        GameState currentState = GameState.PLAYING;
        Board gameBoard = new Board(currentState);

        gameBoard.play();
    }
}