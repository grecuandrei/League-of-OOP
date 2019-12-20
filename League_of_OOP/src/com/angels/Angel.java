package angels;

import heroes.*;

public abstract class Angel {
    private String name;
    private int X, Y, round;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getX() {
        return X;
    }

    public void setX(int x) {
        X = x;
    }

    public int getY() {
        return Y;
    }

    public void setY(int y) {
        Y = y;
    }

    public int getRound() {
        return round;
    }

    public void setRound(int round) {
        this.round = round;
    }

    public void apply(Knight k) { }

    public void apply(Pyromancer p) { }

    public void apply(Rogue r) { }

    public void apply(Wizard w) { }
}
