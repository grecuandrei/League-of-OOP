package angels;

import heroes.Knight;
import heroes.Pyromancer;
import heroes.Rogue;
import heroes.Wizard;

public class TheDoomer extends Angel {
    public TheDoomer() {
        isGood = false;
    }

    public void apply(Knight k) {
        k.setDead(true);
    }

    public void apply(Pyromancer p) {
        p.setDead(true);
    }

    public void apply(Rogue r) {
        r.setDead(true);
    }

    public void apply(Wizard w) {
        w.setDead(true);
    }
}
