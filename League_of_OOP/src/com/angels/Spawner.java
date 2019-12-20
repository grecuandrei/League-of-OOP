package angels;

import heroes.Knight;
import heroes.Pyromancer;
import heroes.Rogue;
import heroes.Wizard;

public class Spawner extends Angel {
    public void apply(Knight k) {
        k.setDead(false);
        k.setHp(200);
    }

    public void apply(Pyromancer p) {
        p.setDead(false);
        p.setHp(150);
    }

    public void apply(Rogue r) {
        r.setDead(false);
        r.setHp(180);
    }

    public void apply(Wizard w) {
        w.setDead(false);
        w.setHp(120);
    }
}
