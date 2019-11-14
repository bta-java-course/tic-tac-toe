package com.buseduc.javacourse.tictactoe;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Screen;
import javafx.stage.Stage;

import java.util.Arrays;

public class Render extends Application {

    Board mainBoard;

    public Render() {
        this.mainBoard = new Board(10);
    }

    @Override
    public void start(Stage primaryStage) {
        Screen screen = Screen.getPrimary();
        Stage primary = primaryStage;
        primary.setWidth(mainBoard.getBoardSize());
        primary.setHeight(mainBoard.getBoardSize());
        primary.setTitle("Krestiki Noliki");
        primary.setScene(new Scene(mainBoard.render()));
        primary.show();
        Arrays.stream(mainBoard.getBoardCells()).forEach(a -> Arrays.stream(a).forEach(System.out::print));
    }

}
