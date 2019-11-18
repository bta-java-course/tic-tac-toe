package com.buseduc.javacourse.tictactoe;

import com.buseduc.javacourse.tictactoe.artifacts.Board;

import java.util.Scanner;

public class Game {
    static Player[] players = new Player[2];
    public static void main(String[] args) {
        getPlayers();
        Board gameBoard = new Board(players[0]);
        gameBoard.start();
        gameBoard.render();

    }
    public static Player getAnotherPlayer(Player player) {
        return player.isX() ? players[1] : players[0];
    }
    public static void getPlayers() {
        Scanner scanner = new Scanner(System.in);
        String name = "";
        System.out.println("Пользователь 1: имя: ");
        if (scanner.hasNext()) {
            name = scanner.next();
        }
        boolean isAi = false;
        System.out.println("Пользователь 1: компьютер? (Y/N): ");
        if (scanner.hasNext()) {
            isAi = "y".equals(scanner.next().toLowerCase());
        }
        players[0] = new Player(name, true, isAi);
        System.out.println("Пользователь 2: имя: ");
        if (scanner.hasNext()) {
            name = scanner.next();
        }
        isAi = false;
        System.out.println("Пользователь 2: компьютер? (Y/N): ");
        if (scanner.hasNext()) {
            isAi = "y".equals(scanner.next().toLowerCase());
        }
        players[1] = new Player(name, false, isAi);
    }


}
