package com.buseduc.javacourse.tictactoe;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

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
        primary.initStyle(StageStyle.TRANSPARENT);
        primary.setResizable(false);
        primary.setTitle("XO - Gomoku");
        Scene scene = new Scene(board.render());
        scene.setFill(Color.TRANSPARENT);
        primary.setScene(scene);
        primary.show();
    }

}
