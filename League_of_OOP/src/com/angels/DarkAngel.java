package angels;

import heroes.Knight;
import heroes.Pyromancer;
import heroes.Rogue;
import heroes.Wizard;

public class DarkAngel extends Angel {
    public void apply(Knight k) {
        k.setHp(k.getHp() - 40);
    }

    public void apply(Pyromancer p) {
        p.setHp(p.getHp() - 30);
    }

    public void apply(Rogue r) {
        r.setHp(r.getHp() - 10);
    }

    public void apply(Wizard w) {
        w.setHp(w.getHp() - 20);
    }
}
