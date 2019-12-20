package angels;

import heroes.Knight;
import heroes.Pyromancer;
import heroes.Rogue;
import heroes.Wizard;

public class XPAngel extends Angel {
    public void apply(Knight k) {
        k.setXp(k.getXp() + 45);
        k.levelUP();
    }

    public void apply(Pyromancer p) {
        p.setXp(p.getXp() + 50);
        p.levelUP();
    }

    public void apply(Rogue r) {
        r.setXp(r.getXp() + 40);
        r.levelUP();
    }

    public void apply(Wizard w) {
        w.setXp(w.getXp() + 60);
        w.levelUP();
    }
}
