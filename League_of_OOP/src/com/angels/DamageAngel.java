package angels;

import constants.Constants;
import heroes.Knight;
import heroes.Pyromancer;
import heroes.Rogue;
import heroes.Wizard;

public class DamageAngel extends Angel {
    public DamageAngel() {
        isGood = true;
    }

    public final void apply(final Knight k) {
        k.setAngelDmgModifier(k.getAngelDmgModifier() + Constants.DMGA_KNIGHT_MOD);
    }

    public final void apply(final Pyromancer p) {
        p.setAngelDmgModifier(p.getAngelDmgModifier() + Constants.DMGA_PYRO_MOD);
    }

    public final void apply(final Rogue r) {
        r.setAngelDmgModifier(r.getAngelDmgModifier() + Constants.DMGA_ROGUE_MOD);
    }

    public final void apply(final Wizard w) {
        w.setAngelDmgModifier(w.getAngelDmgModifier() + Constants.DMGA_WIZARD_MOD);
    }
}
