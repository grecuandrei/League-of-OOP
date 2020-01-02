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
    // to see if the angel is good or not, to help the magician
    // decide whether he hit or helped a hero
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
    // methods that implement the Observer pattern
    // to attach a observer, in our case the magician
    public final void attach(final Observer o) {
        magician = o;
    }
    // to notify about the angel
    public final void notifyAngel() throws IOException {
        magician.update(this);
    }
}
