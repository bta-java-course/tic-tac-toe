package com.buseduc.javacourse.tictactoe;

import com.buseduc.javacourse.tictactoe.artifacts.Board;

import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;

public class Game {
    public static void main(String[] args) {
        Board gameBoard = new Board();
        String cell = getCell();
        gameBoard.setChip(cell);
        gameBoard.render();
    }
        public static String getCell(){
            Scanner scanner = new Scanner(System.in);
            String hint = "Введите адрес клетки";
            String cell = "";
            System.out.println(hint);
            if(scanner.hasNext()){
                cell = scanner.next();
            }
            scanner.close();
            return cell;


        }

        
    }

