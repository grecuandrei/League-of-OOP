package angels;

import constants.Constants;
import heroes.Knight;
import heroes.Pyromancer;
import heroes.Rogue;
import heroes.Wizard;

public class LevelUpAngel extends Angel {
    public void apply(Knight k) {
        int currXp = Constants.BASE_XP_LEVEL_UP + k.getLevel() * Constants.MULTIPLIER_LEVEL_UP;
        if (k.getXp() <= currXp) {
            k.setXp(currXp);
        }
        k.setAngelDmgModifier(k.getAngelDmgModifier() + 0.1f);
    }

    public void apply(Pyromancer p) {
        int currXp = Constants.BASE_XP_LEVEL_UP + p.getLevel() * Constants.MULTIPLIER_LEVEL_UP;
        if (p.getXp() <= currXp) {
            p.setXp(currXp);
        }
        p.setAngelDmgModifier(p.getAngelDmgModifier() + 0.2f);
    }

    public void apply(Rogue r) {
        int currXp = Constants.BASE_XP_LEVEL_UP + r.getLevel() * Constants.MULTIPLIER_LEVEL_UP;
        if (r.getXp() <= currXp) {
            r.setXp(currXp);
        }
        r.setAngelDmgModifier(r.getAngelDmgModifier() + 0.15f);
    }

    public void apply(Wizard w) {
        int currXp = Constants.BASE_XP_LEVEL_UP + w.getLevel() * Constants.MULTIPLIER_LEVEL_UP;
        if (w.getXp() <= currXp) {
            w.setXp(currXp);
        }
        w.setAngelDmgModifier(w.getAngelDmgModifier() + 0.25f);
    }
}
