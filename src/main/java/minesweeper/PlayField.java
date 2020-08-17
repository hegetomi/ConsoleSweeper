package minesweeper;

public class PlayField {

    private final Field[][] playField;
    private final int numOfMines;
    private boolean firstRevealed;
    private final int numOfBombsOnField;

    public PlayField(int size, int numOfMines) {
        this.playField = new Field[size][size];
        this.numOfMines = numOfMines;
        this.firstRevealed = false;
        this.numOfBombsOnField = 0;
        initPlayField();
    }

    private void initPlayField() {
        for (int i = 0; i < playField.length; i++) {
            for (int j = 0; j < playField[i].length; j++) {
                playField[i][j] = new Field();
            }
        }
    }

    private void checkFieldsForNearbyBombs() {
        for (int i = 0; i < playField.length; i++) {
            for (int j = 0; j < playField[i].length; j++) {
                int currentBombs = playField[i][j].getMinesAround();
                //TopLeftCorner
                if (i == 0 && j == 0) {
                    if (playField[0][1].isBomb()) {
                        currentBombs++;
                    }
                    if (playField[1][0].isBomb()) {
                        currentBombs++;
                    }
                    if (playField[1][1].isBomb()) {
                        currentBombs++;
                    }
                }
                //TopRightCorner
                else if (i == 0 && j == playField.length - 1) {
                    if (playField[0][j - 1].isBomb()) {
                        currentBombs++;
                    }
                    if (playField[1][j].isBomb()) {
                        currentBombs++;
                    }
                    if (playField[1][j - 1].isBomb()) {
                        currentBombs++;
                    }
                }
                //BotLeftCorner
                else if (i == playField.length - 1 && j == 0) {
                    if (playField[i][1].isBomb()) {
                        currentBombs++;
                    }
                    if (playField[i - 1][0].isBomb()) {
                        currentBombs++;
                    }
                    if (playField[i - 1][1].isBomb()) {
                        currentBombs++;
                    }
                }
                //BotRightCorner
                else if (i == playField.length - 1 && j == playField.length - 1) {
                    if (playField[i - 1][j].isBomb()) {
                        currentBombs++;
                    }
                    if (playField[i - 1][j - 1].isBomb()) {
                        currentBombs++;
                    }
                    if (playField[i][j - 1].isBomb()) {
                        currentBombs++;
                    }
                }
                //TopRow
                else if (i == 0) {
                    if (playField[i][j - 1].isBomb()) {
                        currentBombs++;
                    }
                    if (playField[i][j + 1].isBomb()) {
                        currentBombs++;
                    }
                    if (playField[i + 1][j - 1].isBomb()) {
                        currentBombs++;
                    }
                    if (playField[i + 1][j + 1].isBomb()) {
                        currentBombs++;
                    }
                    if (playField[i + 1][j].isBomb()) {
                        currentBombs++;
                    }

                }
                //LeftColumn
                else if (j == 0) {
                    if (playField[i - 1][j].isBomb()) {
                        currentBombs++;
                    }
                    if (playField[i + 1][j].isBomb()) {
                        currentBombs++;
                    }
                    if (playField[i - 1][j + 1].isBomb()) {
                        currentBombs++;
                    }
                    if (playField[i + 1][j + 1].isBomb()) {
                        currentBombs++;
                    }
                    if (playField[i][j + 1].isBomb()) {
                        currentBombs++;
                    }
                }
                //RightColumn
                else if (j == playField.length - 1) {
                    if (playField[i - 1][j].isBomb()) {
                        currentBombs++;
                    }
                    if (playField[i + 1][j].isBomb()) {
                        currentBombs++;
                    }
                    if (playField[i - 1][j - 1].isBomb()) {
                        currentBombs++;
                    }
                    if (playField[i][j - 1].isBomb()) {
                        currentBombs++;
                    }
                    if (playField[i + 1][j - 1].isBomb()) {
                        currentBombs++;
                    }
                }
                //BottomRow
                else if (i == playField.length - 1) {
                    if (playField[i][j - 1].isBomb()) {
                        currentBombs++;
                    }
                    if (playField[i][j + 1].isBomb()) {
                        currentBombs++;
                    }
                    if (playField[i - 1][j - 1].isBomb()) {
                        currentBombs++;
                    }
                    if (playField[i - 1][j + 1].isBomb()) {
                        currentBombs++;
                    }
                    if (playField[i - 1][j].isBomb()) {
                        currentBombs++;
                    }
                }
                //Inner fields
                else {
                    if (playField[i - 1][j - 1].isBomb()) {
                        currentBombs++;
                    }
                    if (playField[i - 1][j].isBomb()) {
                        currentBombs++;
                    }
                    if (playField[i - 1][j + 1].isBomb()) {
                        currentBombs++;
                    }
                    if (playField[i][j + 1].isBomb()) {
                        currentBombs++;
                    }
                    if (playField[i + 1][j + 1].isBomb()) {
                        currentBombs++;
                    }
                    if (playField[i + 1][j].isBomb()) {
                        currentBombs++;
                    }
                    if (playField[i + 1][j - 1].isBomb()) {
                        currentBombs++;
                    }
                    if (playField[i][j - 1].isBomb()) {
                        currentBombs++;
                    }
                }

                playField[i][j].setMinesAround(currentBombs);

            }
        }
    }

    private void setupBombs(int numOfBombsNeeded, int numOfBombsOnField, int firstX, int firstY) {
        while (numOfBombsOnField != numOfBombsNeeded) {
            int genX = (int) (Math.random() * playField.length);
            int genY = (int) (Math.random() * playField.length);
            if ((genX != firstX || genY != firstY)) {
                if (!playField[genX][genY].isBomb()) {
                    playField[genX][genY].setBomb(true);
                    numOfBombsOnField++;
                }
            }
        }
    }

    public void printField() {
        System.out.println(" │123456789│");
        System.out.println("—│—————————│");
        for (int i = 0; i < playField.length; i++) {
            System.out.print(i + 1 + "|");
            for (int j = 0; j < playField[i].length; j++) {
                System.out.print(playField[i][j] + "");
            }
            System.out.println("|");

        }
        System.out.println("—│—————————│");
    }

    public int flagAsBomb(int X, int Y) {

        if (playField[X][Y].isBomb() && !playField[X][Y].isFlagged()) {
            playField[X][Y].setFlagged(true);
            return 1;
        } else if (!playField[X][Y].isFlagged() && playField[X][Y].notRevealed()) {
            playField[X][Y].setFlagged(true);
            return 1;
        } else if (playField[X][Y].isBomb() && playField[X][Y].isFlagged()) {
            playField[X][Y].setFlagged(false);
            return 1;
        } else if (playField[X][Y].isFlagged() && playField[X][Y].notRevealed()) {
            playField[X][Y].setFlagged(false);
            return 1;
        } else {
            return 4;
        }
    }

    public int reveal(int X, int Y) {

        if (!firstRevealed) {
            setupBombs(numOfMines, numOfBombsOnField, X, Y);
            checkFieldsForNearbyBombs();
            firstRevealed = true;
        }
        if (!playField[X][Y].isBomb()) {
            playField[X][Y].setRevealed(true);
            if (playField[X][Y].getMinesAround() == 0) {
                revealEmptiesRecursive(X, Y);
            }
            return 1;
        } else {
            revealAllBombs();
            return -1;
        }
    }

    private void revealEmptiesRecursive(int X, int Y) {
        if (X + 1 < playField.length) {
            if (playField[X + 1][Y].notRevealed()) {
                reveal(X + 1, Y);
            }
        }
        if (X - 1 >= 0) {
            if (playField[X - 1][Y].notRevealed()) {
                reveal(X - 1, Y);
            }
        }
        if (Y + 1 < playField.length) {
            if (playField[X][Y + 1].notRevealed()) {
                reveal(X, Y + 1);
            }
        }
        if (Y - 1 >= 0) {
            if (playField[X][Y - 1].notRevealed()) {
                reveal(X, Y - 1);
            }
        }
        if (X + 1 < playField.length && Y + 1 < playField.length) {
            if (playField[X + 1][Y + 1].notRevealed()) {
                reveal(X + 1, Y + 1);
            }
        }
        if (X + 1 < playField.length && Y - 1 >= 0) {
            if (playField[X + 1][Y - 1].notRevealed()) {
                reveal(X + 1, Y - 1);
            }
        }
        if (X - 1 >= 0 && Y - 1 >= 0) {
            if (playField[X - 1][Y - 1].notRevealed()) {
                reveal(X - 1, Y - 1);
            }
        }
        if (X - 1 >= 0 && Y + 1 < playField.length) {
            if (playField[X - 1][Y + 1].notRevealed()) {
                reveal(X - 1, Y + 1);
            }
        }
    }


    private void revealAllBombs() {
        for (Field[] fields : playField) {
            for (Field field : fields) {
                if (field.isBomb()) {
                    field.setRevealed(true);
                }
            }
        }
    }

    public int translateCommand(String command, int X, int Y) {
        if (X < playField.length && Y < playField.length) {
            switch (command) {
                case "free":
                    return reveal(X, Y);
                case "mine":
                    return flagAsBomb(X, Y);
                default:
                    return 6;

            }
        } else {
            return 6;
        }
    }

    public boolean checkIfWon() {

        int bombsToFind = numOfMines;
        boolean shouldContinue = false;
        for (Field[] fields : playField) {
            for (Field field : fields) {
                if (!field.isBomb() && field.notRevealed()) {
                    shouldContinue = true;
                }
                if (field.isBomb() && field.isFlagged()) {
                    bombsToFind--;
                }
            }
        }
        if (bombsToFind == 0) {
            return false;
        }
        return shouldContinue;
    }

}