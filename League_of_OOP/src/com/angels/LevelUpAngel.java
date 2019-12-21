package angels;

import constants.Constants;
import heroes.Knight;
import heroes.Pyromancer;
import heroes.Rogue;
import heroes.Wizard;

import java.io.IOException;

public class LevelUpAngel extends Angel {
    public LevelUpAngel() {
        isGood = true;
    }

    public void apply(Knight k) throws IOException {
        int currXp = Constants.BASE_XP_LEVEL_UP + k.getLevel() * Constants.MULTIPLIER_LEVEL_UP;
        if (k.getXp() <= currXp) {
            k.setXp(currXp);
//            k.levelUP();
        }
        k.setAngelDmgModifier(k.getAngelDmgModifier() + 0.1f);
    }

    public void apply(Pyromancer p) throws IOException {
        int currXp = Constants.BASE_XP_LEVEL_UP + p.getLevel() * Constants.MULTIPLIER_LEVEL_UP;
        if (p.getXp() <= currXp) {
            p.setXp(currXp);
//            p.levelUP();
        }
        p.setAngelDmgModifier(p.getAngelDmgModifier() + 0.2f);
    }

    public void apply(Rogue r) throws IOException {
        int currXp = Constants.BASE_XP_LEVEL_UP + r.getLevel() * Constants.MULTIPLIER_LEVEL_UP;
        if (r.getXp() <= currXp) {
            r.setXp(currXp);
//            r.levelUP();
        }
        r.setAngelDmgModifier(r.getAngelDmgModifier() + 0.15f);
    }

    public void apply(Wizard w) throws IOException {
        int currXp = Constants.BASE_XP_LEVEL_UP + w.getLevel() * Constants.MULTIPLIER_LEVEL_UP;
        if (w.getXp() <= currXp) {
            w.setXp(currXp);
//            w.levelUP();
        }
        w.setAngelDmgModifier(w.getAngelDmgModifier() + 0.25f);
    }
}
