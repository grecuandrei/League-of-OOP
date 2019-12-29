package angels;

import constants.Constants;
import heroes.Knight;
import heroes.Pyromancer;
import heroes.Rogue;
import heroes.Wizard;

public class SmallAngel extends Angel {
    public SmallAngel() {
        isGood = true;
    }

    public final void apply(final Knight k) {
        k.setAngelDmgModifier(k.getAngelDmgModifier() + Constants.SA_KNIGHT_MOD);
        k.setHp(k.getHp() + Constants.SA_KNIGHT);
    }

    public final void apply(final Pyromancer p) {
        p.setAngelDmgModifier(p.getAngelDmgModifier() + Constants.SA_PYRO_MOD);
        p.setHp(p.getHp() + Constants.SA_PYRO);
    }

    public final void apply(final Rogue r) {
        r.setAngelDmgModifier(r.getAngelDmgModifier() + Constants.SA_ROGUE_MOD);
        r.setHp(r.getHp() + Constants.SA_ROGUE);
    }

    public final void apply(final Wizard w) {
        w.setAngelDmgModifier(w.getAngelDmgModifier() + Constants.SA_WIZARD_MOD);
        w.setHp(w.getHp() + Constants.SA_WIZARD);
    }
}
