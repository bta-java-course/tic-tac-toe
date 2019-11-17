package com.buseduc.javacourse.tictactoe;

import com.buseduc.javacourse.tictactoe.enums.Chip;

public class Computer extends Player{

    private static final String NAME = "Computer";

    public Computer() {
        super(getOtherChip());
    }

    //Gets other chip. If player has X, computer takes O.
    private static Chip getOtherChip() {
        Chip chip = Game.getActivePlayer().getChip();
        Chip otherChip;
        if (chip.equals(Chip.X))  otherChip = Chip.O;
        else otherChip = Chip.X;
        return otherChip;
    }

    @Override
    public String getName() {
        return NAME;
    }

}
