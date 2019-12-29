package angels;

import heroes.Knight;
import heroes.Pyromancer;
import heroes.Rogue;
import heroes.Wizard;

public class TheDoomer extends Angel {
    public TheDoomer() {
        isGood = false;
    }

    public final void apply(final Knight k) {
        k.setDead(true);
    }

    public final void apply(final Pyromancer p) {
        p.setDead(true);
    }

    public final void apply(final Rogue r) {
        r.setDead(true);
    }

    public final void apply(final Wizard w) {
        w.setDead(true);
    }
}
