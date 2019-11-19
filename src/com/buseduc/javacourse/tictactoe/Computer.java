package com.buseduc.javacourse.tictactoe;

import com.buseduc.javacourse.tictactoe.enums.Chip;

import java.util.*;

public class Computer extends Player{

    private static Computer instance;
    private static final String NAME = "Computer";
    private static Board board;
    private static GameState gameState;
    private static List<List<Cell>> winLinesList;
    private static int boardSize;
    private static int winLineSize;
    private static Cell[][] cell;

    private Computer() {
        super(getOtherChip());
    }

    //Gets other chip. If player has X, computer takes O.
    private static Chip getOtherChip() {
        Chip chip = Game.getGameState().getActivePlayer().getChip();
        Chip otherChip;
        if (chip.equals(Chip.X))  otherChip = Chip.O;
        else otherChip = Chip.X;
        return otherChip;
    }

    @Override
    public String getName() {
        return NAME;
    }

    public static Computer getInstance() {
        if (instance == null) {
            instance = new Computer();
            board = Game.getBoard();
            gameState = Game.getGameState();
            boardSize = Game.getBoardSize();
            winLineSize = Game.getWinLine();
            cell = Game.getBoard().getBoardCells();
        }
        return instance;
    }

    public void actionALevel() {
        int boardSize = board.getSize();
        while (gameState.getActivePlayer().equals(this)) {
            Random randNum = new Random();
            int x = randNum.ints(0, boardSize).findFirst().getAsInt();
            int y = randNum.ints(0, boardSize).findFirst().getAsInt();
            actionsWhenChipChosen(x, y);
        }
    }

    public void actionBLevel() {
        int boardSize = board.getSize();
        while (gameState.getActivePlayer().equals(this)) {
            Random randNum = new Random();
            int x = randNum.ints(0, boardSize).findFirst().getAsInt();
            int y = randNum.ints(0, boardSize).findFirst().getAsInt();
            actionsWhenChipChosen(x, y);
        }
    }

    private void actionsWhenChipChosen(int x, int y) {
        if (!Game.getBoard().getBoardCells()[x][y].isClicked()) {
            board.getBoardCells()[x][y].setChip(this.getChip());
            board.getBoardCells()[x][y].setClicked(true);
            board.getBoardCells()[x][y].setText(this.getChip().name());
            gameState.hasPlayerWinPoss(gameState.getActivePlayer());
            gameState.isDeadHeat();
            if (gameState.isFinished()) {
                System.out.println(gameState.getEndMsg());
            }
            gameState.setActivePlayer(gameState.getPlayers().get(0));
        }
    }

    public void fillWinLines() {
        for (int x = 0; x < boardSize; x++) {
            for (int y = 0; y < boardSize; y++) {
                if ((boardSize - x) >= winLineSize && boardSize - y >= winLineSize) {
                    for (int a = 0; a < winLineSize; a++) {
                        List<Cell> line = new ArrayList<>();
                        for (int b = 0; b < winLineSize; b++) line.add(board.getBoardCells()[b + x][a + y]);
                        if (winLineNotExistInWinLinesList(line)) winLinesList.add(new ArrayList<>(line));
                        line.clear();
                        for (int b = 0; b < winLineSize; b++) line.add(board.getBoardCells()[a + x][b + y]);
                        if (winLineNotExistInWinLinesList(line)) winLinesList.add(new ArrayList<>(line));
                        line.clear();
                        for (int b = 0; b < winLineSize; b++) line.add(board.getBoardCells()[b + x][b + y]);
                        if (winLineNotExistInWinLinesList(line)) winLinesList.add(new ArrayList<>(line));
                        line.clear();
                        for (int b = 0; b < winLineSize; b++) line.add(board.getBoardCells()[winLineSize - b + x - 1][b + y]);
                        if (winLineNotExistInWinLinesList(line)) winLinesList.add(new ArrayList<>(line));
                        line.clear();
                    }
                }
            }
        }
    }

    private static boolean winLineNotExistInWinLinesList(List<Cell> line) {
        if (winLinesList == null) {
            winLinesList = new ArrayList<>();
            return true;
        }
        List<Boolean> outList = new ArrayList<>();
        for (List<Cell> list : winLinesList) {
            List<Boolean> outComeList = new ArrayList<>();
            for (int i = 0; i < list.size(); i++) {
                outComeList.add(Arrays.equals(list.get(i).getArrayPos(), line.get(i).getArrayPos()));
            }
            if (outComeList.stream().allMatch(c -> c.equals(true))) {
                outList.add(true);
            } else outList.add(false);
        }
        return !outList.contains(true);
    }

}
