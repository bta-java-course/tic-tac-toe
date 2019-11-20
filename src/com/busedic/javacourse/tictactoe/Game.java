package com.busedic.javacourse.tictactoe;

import com.busedic.javacourse.tictactoe.artifacts.Board;

import java.util.Arrays;
import java.util.Scanner;

public class Game {
    public static Player[] players = new Player[2];
    public static Board gameBoard = new Board();
    public static int i = 0;


    public static void main(String[] args) {
        getPlayers();
        gameBoard.render();
        while (GameState.isGameOn()) {
            gameBoard.setChip(getCell());
        }
    }

    public static String getCell() {
        Scanner scanner = new Scanner(System.in);
        String hint = "Make your move: ";
        String cell = "";
        System.out.println(hint);
        if (scanner.hasNext()) {
            cell = scanner.next();
        }
        return cell;
    }

    public static void getPlayers() {
        Scanner scanner = new Scanner(System.in);
        Player newPlayer = new Player();
        while (i < 2) {
            String hint = "Set your name: ";
            String ifAi = "Check who is playing. You? (Y/N): ";
            String aiCheck = "";
            String name = "";
            System.out.println(hint);
            if (scanner.hasNext()) {
                name = scanner.next();
            }
            System.out.println(ifAi);
            if (scanner.hasNext()) {
                aiCheck = scanner.next();
            }
            newPlayer.setName(name);

            if (aiCheck.contains("Y")) {
                newPlayer.setAI(false);
            } else {
                newPlayer.setAI(true);
            }
            players[i] = newPlayer;
            if (Arrays.asList(players).indexOf(newPlayer) == 0) {
                newPlayer.setX(true);
                GameState.setCurrentPlayer(newPlayer);
            }
            i++;
            getPlayers();
        }
    }
}
