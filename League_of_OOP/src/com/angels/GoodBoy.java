package angels;

import heroes.Knight;
import heroes.Pyromancer;
import heroes.Rogue;
import heroes.Wizard;

public class GoodBoy extends Angel {
    public void apply(Knight k) {
        k.setAngelDmgModifier(k.getAngelDmgModifier() + 0.4f);
        k.setHp(k.getHp() + 20);
    }

    public void apply(Pyromancer p) {
        p.setAngelDmgModifier(p.getAngelDmgModifier() + 0.5f);
        p.setHp(p.getHp() + 30);
    }

    public void apply(Rogue r) {
        r.setAngelDmgModifier(r.getAngelDmgModifier() + 0.4f);
        r.setHp(r.getHp() + 40);
    }

    public void apply(Wizard w) {
        w.setAngelDmgModifier(w.getAngelDmgModifier() + 0.3f);
        w.setHp(w.getHp() + 50);
    }
}
