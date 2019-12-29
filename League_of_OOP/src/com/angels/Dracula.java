package angels;

import constants.Constants;
import heroes.Knight;
import heroes.Pyromancer;
import heroes.Rogue;
import heroes.Wizard;

public class Dracula extends Angel {
    public Dracula() {
        isGood = false;
    }

    public final void apply(final Knight k) {
        k.setAngelDmgModifier(k.getAngelDmgModifier() - Constants.DRAC_KNIGHT_MOD);
        k.setHp(k.getHp() - Constants.DRAC_KNIGHT);
    }

    public final void apply(final Pyromancer p) {
        p.setAngelDmgModifier(p.getAngelDmgModifier() - Constants.DRAC_PYRO_MOD);
        p.setHp(p.getHp() - Constants.DRAC_PYRO);
    }

    public final void apply(final Rogue r) {
        r.setAngelDmgModifier(r.getAngelDmgModifier() - Constants.DRAC_ROGUE_MOD);
        r.setHp(r.getHp() - Constants.DRAC_ROGUE);
    }

    public final void apply(final Wizard w) {
        w.setAngelDmgModifier(w.getAngelDmgModifier() - Constants.DRAC_WIZARD_MOD);
        w.setHp(w.getHp() - Constants.DRAC_WIZARD);
    }
}
