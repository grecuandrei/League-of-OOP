package angels;

import constants.Constants;
import heroes.Knight;
import heroes.Pyromancer;
import heroes.Rogue;
import heroes.Wizard;

public class DarkAngel extends Angel {
    public DarkAngel() {
        isGood = false;
    }

    public final void apply(final Knight k) {
        k.setHp(k.getHp() - Constants.DARKA_KNIGHT);
    }

    public final void apply(final Pyromancer p) {
        p.setHp(p.getHp() - Constants.DARKA_PYRO);
    }

    public final void apply(final Rogue r) {
        r.setHp(r.getHp() - Constants.DARKA_ROGUE);
    }

    public final void apply(final Wizard w) {
        w.setHp(w.getHp() - Constants.DARKA_WIZARD);
    }
}
