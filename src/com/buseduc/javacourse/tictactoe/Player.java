package com.buseduc.javacourse.tictactoe;

import com.buseduc.javacourse.tictactoe.enums.Chip;

public class Player {
    private Chip chip;
    private String name;

    public Player() {
        this.name = setName();
        this.chip = setChip();
    }

    public Player(Chip chip) {
        this();
        this.chip = chip;
    }

    public Chip getChip() {
        return chip;
    }

    public String getName() {
        return name;
    }

    private String setName() {
        return "Player " + (Game.getPLAYERS().size() + 1);
    }

    private Chip setChip() {
        if (Game.getPLAYERS().size() == 0)
            return Chip.X;
        else return Chip.O;
    }

}
