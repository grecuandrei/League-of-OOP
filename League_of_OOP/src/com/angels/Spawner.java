package angels;

import constants.Constants;
import heroes.Knight;
import heroes.Pyromancer;
import heroes.Rogue;
import heroes.Wizard;

public class Spawner extends Angel {
    public Spawner() {
        isGood = true;
    }

    public final void apply(final Knight k) {
        k.setDead(false);
        k.setHp(Constants.SPAWNER_KNIGHT);
    }

    public final void apply(final Pyromancer p) {
        p.setDead(false);
        p.setHp(Constants.SPAWNER_PYRO);
    }

    public final void apply(final Rogue r) {
        r.setDead(false);
        r.setHp(Constants.SPAWNER_ROGUE);
    }

    public final void apply(final Wizard w) {
        w.setDead(false);
        w.setHp(Constants.SPAWNER_WIZARD);
    }
}
