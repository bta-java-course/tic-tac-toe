package com.buseduc.javacourse.tictactoe;

import com.buseduc.javacourse.tictactoe.enums.LowerCaseLetters;
import javafx.geometry.HPos;
import javafx.geometry.Pos;
import javafx.geometry.VPos;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Board {

    private static final int CELL_SIZE = 50;
    private static List<String> abcList;

    static {
        abcList = Arrays.asList(LowerCaseLetters.values()).stream()
                .map(a -> a.name())
                .collect(Collectors.toList());
    }

    private int size;
    private Cell[][] boardCells;
    private int boardSize;

    public Board(int size) {
        this.size = size;
        boardCells = new Cell[size][size];
        boardSize = CELL_SIZE * size + 150;
    }

    public Cell[][] getBoardCells() {
        return boardCells;
    }

    public GridPane render() {
        GridPane outerPane = initOuterPane();
        GridPane deskPane = initDeskPane();
        outerPane.add(deskPane, 1, 1, size, size);
        fillBoardCells();
        Computer.getInstance().fillWinLines();
        for (int x = 0; x < size; x++) {
            for (int y = 0; y < size; y++) {
                deskPane.add(boardCells[x][y], y, x);
            }
        }
        return outerPane;
    }

    private void fillBoardCells() {
        String path = System.getProperty("user.dir").concat("\\src\\com\\buseduc\\javacourse\\tictactoe");
        Image cellBack = null;
        Image cellBackRight = null;
        Image cellBackBot = null;
        BackgroundPosition backPos = BackgroundPosition.CENTER;
        BackgroundSize backSize = new BackgroundSize(
                50,
                50,
                false,
                false,
                true,
                true);
        try {
            cellBack = new Image(new FileInputStream(path.concat("\\img\\cell_50x50.jpg")));
            cellBackRight = new Image(new FileInputStream(path.concat("\\img\\cell_right_50x50.jpg")));
            cellBackBot = new Image(new FileInputStream(path.concat("\\img\\cell_bot_50x50.jpg")));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        for (int x = 0; x < size; x++) {
            for (int y = 0; y < size; y++) {
                String abcPos = abcList.get(y);
                String numPos = String.valueOf(size - x);
                Cell nextCell = new Cell(new int[] {x, y});
                if (y == size - 1 && x != size - 1) {
                    nextCell.setBackground(new Background(new BackgroundImage(cellBackRight, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, backPos, backSize)));
                }
                else if (x == size - 1 && y != size - 1) {
                    nextCell.setBackground(new Background(new BackgroundImage(cellBackBot, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, backPos, backSize)));
                }
                else if (x != size - 1 && y != size - 1)
                    nextCell.setBackground(new Background(new BackgroundImage(cellBack, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, backPos, backSize)));
                boardCells[x][y] = nextCell;
                nextCell.setArrayPos(new int[] {x, y});
            }
        }
    }

    private GridPane initOuterPane() {
        GridPane outerPane = new GridPane();
        for(int i = 0; i < size+2; i++) {
            ColumnConstraints column = new ColumnConstraints(CELL_SIZE);
            column.setHalignment(HPos.CENTER);
            RowConstraints row = new RowConstraints(CELL_SIZE);
            row.setValignment(VPos.CENTER);
            if (i == 0 || i == size + 1) {
                column.setMinWidth(35);
                column.setMaxWidth(35);
                row.setMinHeight(35);
                row.setMaxHeight(35);
            }
            outerPane.getColumnConstraints().add(column);
            outerPane.getRowConstraints().add(row);
        }
        try {
            fillVHLines(outerPane);
            fillCorners(outerPane);
        } catch (IOException e) {
            e.printStackTrace();
        }
        outerPane.setStyle("-fx-background-color: transparent; -fx-border-width:0; -fx-border-insets: 0; ");
        return outerPane;
    }

    private GridPane initDeskPane() {
        GridPane deskPane = new GridPane();
        deskPane.setAlignment(Pos.CENTER);
        for(int i = 0; i < size; i++) {
            ColumnConstraints column = new ColumnConstraints(CELL_SIZE);
            column.setHalignment(HPos.CENTER);
            deskPane.getColumnConstraints().add(column);
            RowConstraints row = new RowConstraints(CELL_SIZE);
            row.setValignment(VPos.CENTER);
            deskPane.getRowConstraints().add(row);
        }
        deskPane.setStyle("-fx-background-color: #dedede; -fx-border-width:0; -fx-border-insets: 0; ");
        return deskPane;
    }

    private void fillCorners(GridPane outerPane) throws IOException {
        Image image;
        String path = System.getProperty("user.dir").concat("\\src\\com\\buseduc\\javacourse\\tictactoe");
        image = new Image(Files.newInputStream(Paths.get(path.concat("/img/top_left_35x35.png"))));
        outerPane.add(new ImageView(image),0, 0);
        image = new Image(Files.newInputStream(Paths.get(path.concat("/img/top_right_35x35.png"))));
        outerPane.add(new ImageView(image),size + 1, 0);
        image = new Image(Files.newInputStream(Paths.get(path.concat("/img/bot_left_35x35.png"))));
        outerPane.add(new ImageView(image),0, size + 1);
        image = new Image(Files.newInputStream(Paths.get(path.concat("/img/bot_right_35x35.png"))));
        outerPane.add(new ImageView(image),size + 1, size + 1);
    }

    private void fillVHLines(GridPane outerPane) throws IOException {
        for (int x = 1; x <= size; x++) {
            fillOutLineImg(outerPane, x, 0,"-fx-background-image: url(\"com/buseduc/javacourse/tictactoe/img/top_50x35.png\");", 50, 35);
            fillOutLineImg(outerPane, x, size+1,"-fx-background-image: url(\"com/buseduc/javacourse/tictactoe/img/bot_50x35.png\");", 50, 35);
            fillOutLineImg(outerPane, size+1, x,"-fx-background-image: url(\"com/buseduc/javacourse/tictactoe/img/right_35x50.jpg\");", 35, 50);
            fillOutLineImg(outerPane, 0, x,"-fx-background-image: url(\"com/buseduc/javacourse/tictactoe/img/left_35x50.jpg\");", 35, 50);
        }
    }

    private void fillOutLineImg(GridPane outerPane, int x, int y, String url, int w, int h) {
            Label label = new Label();
            label.setStyle(url);
            label.setMinWidth(w);
            label.setMinHeight(h);
            outerPane.add(label, x, y);
    }

    public int getBoardSize() {
        return boardSize;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }
}
