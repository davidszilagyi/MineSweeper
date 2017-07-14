package app;

import java.util.Scanner;

/**
 * Created by David Szilagyi on 2017. 07. 13..
 */
public class Game extends Mine{
    private int score;
    private boolean boomed = false;
    private Object[][] playerSolution = null;
    private boolean[][] checked;
    private int boomZeros;

    public Game(String file) {
        super(file);
        start();
        this.checked = new boolean[height][width];
        this.boomZeros = 1;
        this.playerSolution = new Object[height][width];
    }

    public void gameStart() {
        createSolutionField();
        boomZeros();
        boolean game = true;
        while (game) {
            Field.showBoard(playerSolution, height, width);
            System.out.printf("X Pos (1 - %d):", height);
            int xPos = new Scanner(System.in).nextInt();
            System.out.printf("Y Pos (1 - %d):", width);
            int yPos = new Scanner(System.in).nextInt();
            if (checked[xPos - 1][yPos - 1]) {
                System.out.println("Already checked!");
                continue;
            } else if (checkBomb(xPos - 1, yPos - 1)) {
                boomed = true;
                game = false;
                endGame(xPos - 1, yPos - 1);
            } else {
                score++;
            }
            if (checkAvailablePos()) {
                game = false;
                endGame();
            }
        }
    }

    private void boomZeros() {
        System.out.print("Do you want to destroy zero fields if you hit one? (Y/N): ");
        boolean correct = false;
        while (!correct) {
            String answer = new Scanner(System.in).nextLine();
            switch (answer.toUpperCase().charAt(0)) {
                case ('Y'):
                    boomZeros = 1;
                    correct = true;
                    break;
                case ('N'):
                    boomZeros = 0;
                    correct = true;
                    break;
                default:
                    System.out.print("Please enter 'Y' or 'N': ");
                    break;
            }
        }
    }

    private void createSolutionField() {
        for (int i = 0; i < height; i++) {
            for (int k = 0; k < width; k++) {
                playerSolution[i][k] = Field.FIELD;
            }
        }
    }

    private boolean checkAvailablePos() {
        int available = 0;
        for (int i = 0; i < height; i++) {
            for (int k = 0; k < width; k++) {
                if (playerSolution[i][k].equals(Field.FIELD)) {
                    available++;
                }
            }
        }
        return available == curBombs ? true : false;
    }

    private boolean checkBomb(int xPos, int yPos) {
        try {
            if (board[xPos][yPos].equals(Field.BOMB)) {
                return true;
            } else {
                appendSolution(xPos, yPos);
                if (boomZeros == 1) {
                    if (playerSolution[xPos][yPos].equals(0)) {
                        checkZero(xPos, yPos);
                    }
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
                    } else if (board[i][k].equals(0)) {
                        appendSolution(i, k);
                        checkZero(i, k);
                    } else if (!board[i][k].equals(Field.BOMB)) {
                        appendSolution(i, k);
                    }
                } catch (IndexOutOfBoundsException e) {
                    continue;
                }
            }
        }
    }

    private void appendSolution(int xPos, int yPos) {
        playerSolution[xPos][yPos] = board[xPos][yPos];
        checked[xPos][yPos] = true;
    }

    private void endGame() {
        Field.showBoard(board, height, width);
        System.out.printf("\nYOU WIN!");
    }

    private void endGame(int lastXPos, int lastYPos) {
        if (boomed) {
            for (int i = 0; i < height; i++) {
                for (int k = 0; k < width; k++) {
                    if (board[i][k].equals(Field.BOMB)) {
                        if (i == lastXPos && k == lastYPos) {
                            playerSolution[i][k] = Field.BOOM;
                        } else {
                            playerSolution[i][k] = Field.BOMB;
                        }
                    }
                }
            }
            Field.showBoard(playerSolution, height, width);
            System.out.printf("\nBOOOOOOOM!\nYou lost :(\nScore: %d", score);
        }
    }
}
