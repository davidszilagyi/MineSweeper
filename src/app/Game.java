package app;

import util.InputUtil;

import java.util.Scanner;

/**
 * Created by David Szilagyi on 2017. 07. 13..
 */
public class Game extends Mine {
    private int score;
    private boolean boomed = false;
    private Object[][] playerSolution = null;
    private boolean[][] checked;
    private boolean boomZeros;
    private boolean hint;
    private int playerXPos;
    private int playerYPos;

    public Game(String file) {
        super(file);
        start();
        this.checked = new boolean[height][width];
        this.boomZeros = true;
        this.playerSolution = new Object[height][width];
        this.hint = false;
    }

    public void gameStart() {
        createSolutionField();
        boolean game = true;
        while (game) {
            Field.showBoard(playerSolution, height, width);
            System.out.print("Options -> 'Hit', 'Hint', 'Zero', 'End': ");
            String answer = new Scanner(System.in).nextLine().toLowerCase();
            switch (answer) {
                case "hit":
                    hint = false;
                    input();
                    if (checkField(playerXPos, playerYPos)) {
                        game = false;
                    }
                    break;
                case "hint":
                    input();
                    hint = true;
                    appendSolution(playerXPos - 1, playerYPos - 1);
                    break;
                case "zero":
                    boomZeros = !boomZeros;
                    if (boomZeros) {
                        System.out.println("Zeros will be destroyed!");
                    } else {
                        System.out.println("Zeros will NOT be destroyed!");
                    }
                    break;
                case "end":
                    game = false;
                    endGame("End Game. Bye!");
                    break;
                default:
                    System.out.println("Incorrect option!");
                    break;
            }
        }
    }

    private void input() {
        boolean correctX = false;
        while (!correctX) {
            playerXPos = InputUtil.checkInput(String.format("X Pos (1 - %d):", height), Integer.class);
            if (!InputUtil.checkPosition(playerXPos, height + 1) || !InputUtil.checkPosition(0, playerXPos)) {
                System.out.println("Wrong position!");
            } else {
                boolean correctY = false;
                while(!correctY) {
                    playerYPos = InputUtil.checkInput(String.format("Y Pos (1 - %d):", width), Integer.class);
                    if (!InputUtil.checkPosition(playerYPos, width + 1) || !InputUtil.checkPosition(0, playerYPos)) {
                        System.out.println("Wrong position!");
                    } else {
                        correctX = true;
                        correctY = true;
                    }

                }
            }
        }
    }

    private boolean checkField(int xPos, int yPos) {
        if (checked[xPos - 1][yPos - 1]) {
            System.out.println("Already checked!");
        } else if (checkBomb(xPos - 1, yPos - 1)) {
            boomed = true;
            endGame(xPos - 1, yPos - 1);
            return true;
        } else {
            score++;
        }
        if (checkAvailablePos()) {
            endGame("\nYOU WIN!");
            return true;
        }
        return false;
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
        if (board[xPos][yPos].equals(Field.BOMB)) {
            return true;
        } else {
            appendSolution(xPos, yPos);
            if (boomZeros) {
                if (playerSolution[xPos][yPos].equals(0)) {
                    checkZero(xPos, yPos);
                }
            }
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
        if (hint) {
            playerSolution[xPos][yPos] = Field.HINT;
        } else {
            playerSolution[xPos][yPos] = board[xPos][yPos];
            checked[xPos][yPos] = true;
        }
    }

    private void endGame(String text) {
        Field.showBoard(board, height, width);
        System.out.printf(text);
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
