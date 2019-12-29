package angels;

import constants.Constants;
import heroes.Knight;
import heroes.Pyromancer;
import heroes.Rogue;
import heroes.Wizard;

public class GoodBoy extends Angel {
    public GoodBoy() {
        isGood = true;
    }

    public final void apply(final Knight k) {
        k.setAngelDmgModifier(k.getAngelDmgModifier() + Constants.GB_KNIGHT_MOD);
        k.setHp(k.getHp() + Constants.GB_KNIGHT);
    }

    public final void apply(final Pyromancer p) {
        p.setAngelDmgModifier(p.getAngelDmgModifier() + Constants.GB_PYRO_MOD);
        p.setHp(p.getHp() + Constants.GB_PYRO);
    }

    public final void apply(final Rogue r) {
        r.setAngelDmgModifier(r.getAngelDmgModifier() + Constants.GB_ROGUE_MOD);
        r.setHp(r.getHp() + Constants.GB_ROGUE);
    }

    public final void apply(final Wizard w) {
        w.setAngelDmgModifier(w.getAngelDmgModifier() + Constants.GB_WIZARD_MOD);
        w.setHp(w.getHp() + Constants.GB_WIZARD);
    }
}
