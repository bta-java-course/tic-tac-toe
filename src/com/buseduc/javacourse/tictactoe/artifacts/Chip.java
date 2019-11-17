package com.buseduc.javacourse.tictactoe.artifacts;

public enum Chip {
    ZERO {
        @Override
        public String toString() {
            return "O";
        }
    },
    CROSS {
        @Override
        public String toString() {
            return "X";
        }
    },
    EMPTY {
        @Override
        public String toString() {
            return " ";
        }
    }
}