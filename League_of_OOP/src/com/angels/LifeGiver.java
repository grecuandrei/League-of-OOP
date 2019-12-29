package angels;

import constants.Constants;
import heroes.Knight;
import heroes.Pyromancer;
import heroes.Rogue;
import heroes.Wizard;

public class LifeGiver extends Angel {
    public LifeGiver() {
        isGood = true;
    }

    public final void apply(final Knight k) {
        k.setHp(k.getHp() + Constants.LG_KNIGHT);
        if (k.getHp() > k.getBaseHP()) {
            k.setHp(k.getBaseHP());
        }
    }

    public final void apply(final Pyromancer p) {
        p.setHp(p.getHp() + Constants.LG_PYRO);
        if (p.getHp() > p.getBaseHP()) {
            p.setHp(p.getBaseHP());
        }
    }

    public final void apply(final Rogue r) {
        r.setHp(r.getHp() + Constants.LG_ROGUE);
        if (r.getHp() > r.getBaseHP()) {
            r.setHp(r.getBaseHP());
        }
    }

    public final void apply(final Wizard w) {
        w.setHp(w.getHp() + Constants.LG_WIZARD);
        if (w.getHp() > w.getBaseHP()) {
            w.setHp(w.getBaseHP());
        }
    }
}
