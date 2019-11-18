package com.busedic.javacourse.tictactoe;

import com.busedic.javacourse.tictactoe.artifacts.Board;

import java.util.Scanner;

public class Game {
    public static void main(String[] args) {

        Board gameBoard = new Board();
        gameBoard.setChip(getCell());
        gameBoard.render();
    }
    public static String getCell() {
        Scanner scanner = new Scanner(System.in);
        String hint = "Make your move: ";
        String cell = "";
        System.out.println(hint);
        if (scanner.hasNext()) {
            cell = scanner.next();
        }
        scanner.close();
        return cell;
    }
}
