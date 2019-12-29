package angels;

import constants.Constants;
import heroes.Knight;
import heroes.Pyromancer;
import heroes.Rogue;
import heroes.Wizard;

public class LevelUpAngel extends Angel {
    public LevelUpAngel() {
        isGood = true;
    }

    public final void apply(final Knight k) {
        int currXp = Constants.BASE_XP_LEVEL_UP + k.getLevel() * Constants.MULTIPLIER_LEVEL_UP;
        if (k.getXp() <= currXp) {
            k.setXp(currXp);
        }
        k.setAngelDmgModifier(k.getAngelDmgModifier() + Constants.LVL_KNIGHT_MOD);
    }

    public final void apply(final Pyromancer p) {
        int currXp = Constants.BASE_XP_LEVEL_UP + p.getLevel() * Constants.MULTIPLIER_LEVEL_UP;
        if (p.getXp() <= currXp) {
            p.setXp(currXp);
        }
        p.setAngelDmgModifier(p.getAngelDmgModifier() + Constants.LVL_PYRO_MOD);
    }

    public final void apply(final Rogue r) {
        int currXp = Constants.BASE_XP_LEVEL_UP + r.getLevel() * Constants.MULTIPLIER_LEVEL_UP;
        if (r.getXp() <= currXp) {
            r.setXp(currXp);
        }
        r.setAngelDmgModifier(r.getAngelDmgModifier() + Constants.LVL_ROGUE_MOD);
    }

    public final void apply(final Wizard w) {
        int currXp = Constants.BASE_XP_LEVEL_UP + w.getLevel() * Constants.MULTIPLIER_LEVEL_UP;
        if (w.getXp() <= currXp) {
            w.setXp(currXp);
        }
        w.setAngelDmgModifier(w.getAngelDmgModifier() + Constants.LVL_WIZARD_MOD);
    }
}
