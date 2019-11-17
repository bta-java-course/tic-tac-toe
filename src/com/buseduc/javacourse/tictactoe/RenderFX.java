package com.buseduc.javacourse.tictactoe;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class RenderFX extends Application {

    private static Board board;

    static {
        board = Game.getBoard();
    }

    public RenderFX() {
    }

    @Override
    public void start(Stage primaryStage) {
        Stage primary = primaryStage;
        primary.setWidth(board.getBoardSize());
        primary.setHeight(board.getBoardSize());
        primary.setTitle("XO - Gomoku");
        primary.setScene(new Scene(board.render()));
        primary.show();
    }

}
