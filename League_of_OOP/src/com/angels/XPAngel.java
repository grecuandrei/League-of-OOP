package angels;

import constants.Constants;
import heroes.Knight;
import heroes.Pyromancer;
import heroes.Rogue;
import heroes.Wizard;

public class XPAngel extends Angel {
    public XPAngel() {
        isGood = true;
    }

    public final void apply(final Knight k) {
        k.setXp(k.getXp() + Constants.XP_KNIGHT);
    }

    public final void apply(final Pyromancer p) {
        p.setXp(p.getXp() + Constants.XP_PYRO);
    }

    public final void apply(final Rogue r) {
        r.setXp(r.getXp() + Constants.XP_ROGUE);
    }

    public final void apply(final Wizard w) {
        w.setXp(w.getXp() + Constants.XP_WIZARD);
    }
}
