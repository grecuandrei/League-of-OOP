package angels;

import heroes.Knight;
import heroes.Rogue;
import heroes.Pyromancer;
import heroes.Wizard;
import magician.Observer;
import magician.SubjectAngel;
import java.io.IOException;

public abstract class Angel implements SubjectAngel {
    private String name;
    private int x, y, round;
    private Observer magician;
    protected boolean isGood;

    public final String getName() {
        return name;
    }

    public final void setName(final String name) {
        this.name = name;
    }

    public final int getX() {
        return x;
    }

    public final void setX(final int xi) {
        x = xi;
    }

    public final int getY() {
        return y;
    }

    public final void setY(final int yi) {
        y = yi;
    }

    public final int getRound() {
        return round;
    }

    public final void setRound(final int round) {
        this.round = round;
    }

    public final boolean isGood() {
        return isGood;
    }

    public void apply(final Knight k) { }

    public void apply(final Pyromancer p) { }

    public void apply(final Rogue r) { }

    public void apply(final Wizard w) { }
    public final void attach(final Observer o) {
        magician = o;
    }

    public final void notifyAngel() throws IOException {
        magician.update(this);
    }
}
