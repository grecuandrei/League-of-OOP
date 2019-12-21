package angels;

import heroes.Knight;
import heroes.Pyromancer;
import heroes.Rogue;
import heroes.Wizard;

public class SmallAngel extends Angel {
    public SmallAngel() {
        isGood = true;
    }

    public void apply(Knight k) {
        k.setAngelDmgModifier(k.getAngelDmgModifier() + 0.1f);
        k.setHp(k.getHp() + 10);
    }

    public void apply(Pyromancer p) {
        p.setAngelDmgModifier(p.getAngelDmgModifier() + 0.15f);
        p.setHp(p.getHp() + 15);
    }

    public void apply(Rogue r) {
        r.setAngelDmgModifier(r.getAngelDmgModifier() + 0.05f);
        r.setHp(r.getHp() + 20);
    }

    public void apply(Wizard w) {
        w.setAngelDmgModifier(w.getAngelDmgModifier() + 0.1f);
        w.setHp(w.getHp() + 25);
    }
}
