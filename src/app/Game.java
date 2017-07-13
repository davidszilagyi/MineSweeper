package app;

import app.Field;
import app.Mine;

import java.util.Scanner;

/**
 * Created by David Szilagyi on 2017. 07. 13..
 */
public class Game {
    private int score;
    private boolean boomed = false;
    private Mine mine = null;
    private Object[][] solution = null;
    private Object[][] solved = null;
    private boolean[][] checked;
    private int x;
    private int y;

    public Game(String file) {
        this.mine = new Mine(file);
        mine.start();
        this.x = mine.getHeight();
        this.y = mine.getWidth();
        this.solved = mine.getBoard();
        this.checked = new boolean[x][y];
    }

    public void gameStart() {
        createSolutionField();
        boolean game = true;
        while (game) {
            mine.showBoard(solution);
            System.out.printf("X Pos (1 - %d):", x);
            int xPos = new Scanner(System.in).nextInt();
            System.out.printf("Y Pos (1 - %d):", y);
            int yPos = new Scanner(System.in).nextInt();
            if (checked[xPos - 1][yPos - 1]) {
                System.out.println("Already checked!");
                continue;
            } else if (checkBomb(xPos - 1, yPos - 1)) {
                boomed = true;
                game = false;
                endGame();
            } else {
                score++;
            }
            if (checkAvailablePos()) {
                game = false;
                mine.showBoard(solved);
                endGame();
            }
        }
    }

    private void createSolutionField() {
        this.solution = new Object[x][y];
        for (int i = 0; i < x; i++) {
            for (int k = 0; k < y; k++) {
                solution[i][k] = Field.FIELD;
            }
        }
    }

    private boolean checkAvailablePos() {
        int available = 0;
        for (int i = 0; i < x; i++) {
            for (int k = 0; k < y; k++) {
                if (solution[i][k].equals(Field.FIELD)) {
                    available++;
                }
            }
        }
        return available == mine.getCurBombs() ? true : false;
    }

    private boolean checkBomb(int xPos, int yPos) {
        try {
            if (solved[xPos][yPos].equals(Field.BOMB)) {
                return true;
            } else {
                appendSolution(xPos, yPos);
                if (solution[xPos][yPos].equals(0)) {
                    checkZero(xPos, yPos);
                }
                return false;
            }
        } catch (IndexOutOfBoundsException e) {
            System.out.println("Wrong postions!");
            return false;
        }
    }

    private void checkZero(int xPos, int yPos) {
        for (int i = xPos - 1; i <= xPos + 1; i++) {
            for (int k = yPos - 1; k <= yPos + 1; k++) {
                try {
                    if (i == xPos && k == yPos) {
                        continue;
                    } else if (checked[i][k]) {
                        continue;
                    } else if (solved[i][k].equals(0)) {
                        appendSolution(i, k);
                        checkZero(i, k);
                    } else if (!solved[i][k].equals(Field.BOMB)) {
                        appendSolution(i, k);
                    }
                } catch (IndexOutOfBoundsException e) {
                    continue;
                }
            }
        }
    }

    private void appendSolution(int xPos, int yPos) {
        solution[xPos][yPos] = solved[xPos][yPos];
        checked[xPos][yPos] = true;
    }

    private void endGame() {
        if (boomed) {
            for (int i = 0; i < x; i++) {
                for (int k = 0; k < y; k++) {
                    if (solved[i][k].equals(Field.BOMB)) {
                        solution[i][k] = solved[i][k];
                    }
                }
            }
            mine.showBoard(solution);
            System.out.printf("\nBOOOOOOOM!\nYou lost :(\nScore: %d", score);
        } else {
            System.out.printf("\nYOU WIN!");
        }
    }
}
