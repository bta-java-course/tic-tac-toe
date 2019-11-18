package com.buseduc.javacourse.tictactoe;

import com.buseduc.javacourse.tictactoe.artifacts.Board;

import java.util.Scanner;

public class Game {
    static Player[] players = new Player[2];
    public static final Scanner SCANNER = new Scanner(System.in);
    public static void main(String[] args) {
        getPlayers();
        Board gameBoard = new Board(players[0]);
        gameBoard.move();
        gameBoard.render();

    }
    public static Player getAnotherPlayer(Player player) {
        return player.isX() ? players[1] : players[0];
    }
    public static void getPlayers() {
        String name = "";
        System.out.println("Пользователь 1: имя: ");
        if (SCANNER.hasNext()) {
            name = SCANNER.next();
        }
        boolean isAi = false;
        System.out.println("Пользователь 1: компьютер? (Y/N): ");
        if (SCANNER.hasNext()) {
            isAi = "y".equals(SCANNER.next().toLowerCase());
        }
        players[0] = new Player(name, true, isAi);
        System.out.println("Пользователь 2: имя: ");
        if (SCANNER.hasNext()) {
            name = SCANNER.next();
        }
        isAi = false;
        System.out.println("Пользователь 2: компьютер? (Y/N): ");
        if (SCANNER.hasNext()) {
            isAi = "y".equals(SCANNER.next().toLowerCase());
        }
        players[1] = new Player(name, false, isAi);
    }


}
