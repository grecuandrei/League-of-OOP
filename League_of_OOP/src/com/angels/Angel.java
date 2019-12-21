package angels;

import heroes.*;
import magician.Observer;
import magician.SubjectAngel;

import java.io.IOException;

public abstract class Angel implements SubjectAngel{
    private String name;
    private int X, Y, round;
    private Observer magician;
    protected boolean isGood;

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

    public boolean isGood() {
        return isGood;
    }

    public void apply(Knight k) throws IOException { }

    public void apply(Pyromancer p) throws IOException { }

    public void apply(Rogue r) throws IOException { }

    public void apply(Wizard w) throws IOException { }
    
    public void Attach(Observer o) {
        magician = o;
    }

    public void Notify() throws IOException {
        magician.update(this);
    }
}
