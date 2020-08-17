package minesweeper;

public class Field {
    private boolean isBomb;
    private int minesAround;
    private boolean isFlagged;
    private boolean isRevealed;

    public Field() {
        this.isBomb = false;
        this.minesAround = 0;
        this.isFlagged = false;
        this.isRevealed = false;
    }

    public boolean isBomb() {
        return isBomb;
    }

    public void setBomb(boolean bomb) {
        isBomb = bomb;
    }

    public int getMinesAround() {
        return minesAround;
    }

    public void setMinesAround(int minesAround) {
        this.minesAround = minesAround;
    }

    public boolean isFlagged() {
        return isFlagged;
    }

    public void setFlagged(boolean flagged) {
        isFlagged = flagged;
    }

    public boolean notRevealed() {
        return !isRevealed;
    }

    public void setRevealed(boolean revealed) {
        isRevealed = revealed;
    }

    @Override
    public String toString() {
        if (isRevealed) {
            if (isBomb) {
                return "X";
            } else {
                if (minesAround > 0) {
                    return String.valueOf(minesAround);
                } else {
                    return "/";
                }
            }

        } else if (isFlagged) {
            return "*";
        } else {
            return ".";
        }
    }


}