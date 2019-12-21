package angels;

import heroes.Knight;
import heroes.Pyromancer;
import heroes.Rogue;
import heroes.Wizard;

public class LifeGiver extends Angel {
    public LifeGiver() {
        isGood = true;
    }

    public void apply(Knight k) {
        k.setHp(k.getHp() + 100);
    }

    public void apply(Pyromancer p) {
        p.setHp(p.getHp() + 80);
    }

    public void apply(Rogue r) {
        r.setHp(r.getHp() + 90);
    }

    public void apply(Wizard w) {
        w.setHp(w.getHp() + 120);
    }
}
