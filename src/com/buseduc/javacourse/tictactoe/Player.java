package com.buseduc.javacourse.tictactoe;

import java.util.Scanner;

public class Player {
    private String name;
    private boolean isX;
    private boolean isAi;

    public Player(String name, boolean isX, boolean isAi) {
        this.name = name;
        this.isX = isX;
        this.isAi = isAi;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isX() {
        return isX;
    }

    public void setX(boolean x) {
        isX = x;
    }

    public boolean isAi() {
        return isAi;
    }

    public void setAi(boolean ai) {
        isAi = ai;
    }

    public String getCell() {
        Scanner scanner = new Scanner(System.in);
        String hint = name + ", Введите адрес клетки: ";
        String cell = "";
        System.out.println(hint);
        if (scanner.hasNext()) {
            cell = scanner.next();
        }
//        scanner.close();
        return cell;
    }

}
