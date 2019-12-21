package angels;

import heroes.Knight;
import heroes.Pyromancer;
import heroes.Rogue;
import heroes.Wizard;

public class Dracula extends Angel {
    public Dracula() {
        isGood = false;
    }

    public void apply(Knight k) {
        k.setAngelDmgModifier(k.getAngelDmgModifier() - 0.2f);
        k.setHp(k.getHp() - 60);
    }

    public void apply(Pyromancer p) {
        p.setAngelDmgModifier(p.getAngelDmgModifier() - 0.3f);
        p.setHp(p.getHp() - 40);
    }

    public void apply(Rogue r) {
        r.setAngelDmgModifier(r.getAngelDmgModifier() - 0.1f);
        r.setHp(r.getHp() - 35);
    }

    public void apply(Wizard w) {
        w.setAngelDmgModifier(w.getAngelDmgModifier() - 0.4f);
        w.setHp(w.getHp() - 20);
    }
}
