package angels;

import heroes.Knight;
import heroes.Pyromancer;
import heroes.Rogue;
import heroes.Wizard;

public class DamageAngel extends Angel {
    public DamageAngel() {
        isGood = true;
    }

    public void apply(Knight k) {
        k.setAngelDmgModifier(k.getAngelDmgModifier() + 0.15f);
    }

    public void apply(Pyromancer p) {
        p.setAngelDmgModifier(p.getAngelDmgModifier() + 0.2f);
    }

    public void apply(Rogue r) {
        r.setAngelDmgModifier(r.getAngelDmgModifier() + 0.3f);
    }

    public void apply(Wizard w) {
        w.setAngelDmgModifier(w.getAngelDmgModifier() + 0.4f);
    }
}
