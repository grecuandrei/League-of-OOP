package heroes;

import angels.Angel;
import constants.Constants;
import strategy.DefaultStrategy;
import strategy.Strategy1;
import strategy.Strategy2;

import java.io.IOException;

public class Rogue extends Hero {
    private int backstabNr; // number of the basckstabs given
    // initialization of a Rogue
    Rogue() {
        setHp(Constants.INITIAL_HP_ROGUE);
        setBaseHP(Constants.INITIAL_HP_ROGUE);
        setMultiplier(Constants.LEVEL_HP_MULTIPLIER_ROGUE);
        backstabNr = -1;
        setName("R");
    }
    // accept visitor from the Double-Dispatch pattern
    public final float acceptAttack(final Hero h) {
        return h.attack(this);
    }
    // accept angel
    public final void acceptAngel(final Angel a) throws IOException {
        a.apply(this);
    }
    // implementation of first attack
    private float attackBackstab() {
        float dmg = Constants.BASE_DMG_BACKSTAB_ROGUE
                + Constants.BASE_MULTIPLIER_BACKSTAB * getLevel();
        backstabNr++; // number of backstabs increases each time is applied
        // and if it is a multiplier of 3, it will deal increased damage
        if (backstabNr % Constants.HITS_BACKSTAB == 0 && terrainUnderFeet == 'W') {
            dmg = dmg * Constants.CRIT_DMG * Constants.LAND_MODIFIER_ROGUE;
        } else if (terrainUnderFeet == 'W' && backstabNr % Constants.HITS_BACKSTAB != 0) {
            dmg = dmg * Constants.LAND_MODIFIER_ROGUE;
        }
        return dmg;
    }
    // implementation of the second attack
    private float attackParalysis() {
        float dmg = Constants.BASE_DMG_PARALYSIS_ROGUE
                + Constants.BASE_MULTIPLIER_PARALYSIS * getLevel();
        if (terrainUnderFeet == 'W') {
            dmg = dmg * Constants.LAND_MODIFIER_ROGUE;
        }
        return dmg;
    }
    // method that returns the base damage dealt for wizard
    public final float calculateFlatDmg() {
        float dmg1 = attackBackstab();
        backstabNr--;
        float dmg2 = attackParalysis();
        return Math.round(dmg1 + dmg2);
    }
    // calculate dmg for Rogue
    public final float attack(final Rogue r) {
        float dmg1 = attackBackstab();
        dmg1 = Math.round(dmg1
                * (Constants.BACKSTAB_MULTIPLIER_ROGUE + angelDmgModifier + strategyModifier));
        float dmg2 = attackParalysis();
        dmg2 = Math.round(dmg2
                * (Constants.PARALYSIS_MULTIPLIER_ROGUE + angelDmgModifier + strategyModifier));
        modifyOvtDmg(r, dmg2);
        return dmg1 + dmg2;
    }
    // calculate dmg for Knight
    public final float attack(final Knight k) {
        float dmg1 = attackBackstab();
        dmg1 = Math.round(dmg1
                * (Constants.BACKSTAB_MULTIPLIER_KNIGHT + angelDmgModifier + strategyModifier));
        float dmg2 = attackParalysis();
        dmg2 = Math.round(dmg2
                * (Constants.PARALYSIS_MULTIPLIER_KNIGHT + angelDmgModifier + strategyModifier));
        modifyOvtDmg(k, dmg2);
        return dmg1 + dmg2;
    }
    // calculate dmg for Pyromancer
    public final float attack(final Pyromancer p) {
        float dmg1 = attackBackstab();
        dmg1 = Math.round(dmg1
                * (Constants.BACKSTAB_MULTIPLIER_PYRO + angelDmgModifier + strategyModifier));
        float dmg2 = attackParalysis();
        dmg2 = Math.round(dmg2
                * (Constants.PARALYSIS_MULTIPLIER_PYRO + angelDmgModifier + strategyModifier));
        modifyOvtDmg(p, dmg2);
        return dmg1 + dmg2;
    }
    // calculate dmg for Wizard
    public final float attack(final Wizard w) {
        float dmg1 = attackBackstab();
        dmg1 = Math.round(dmg1
                * (Constants.BACKSTAB_MULTIPLIER_WIZARD + angelDmgModifier + strategyModifier));
        float dmg2 = attackParalysis();
        dmg2 = Math.round(dmg2
                * (Constants.PARALYSIS_MULTIPLIER_WIZARD + angelDmgModifier + strategyModifier));
        modifyOvtDmg(w, dmg2);
        return dmg1 + dmg2;
    }
    // method that modifies the overtime parameters
    private void modifyOvtDmg(final Hero h, final float dmg) {
        h.setIncap(false);
        h.setIgnited(false, 0, 0);
        // if it's on a terrain favorable for Rogue, the paralize will take 6 rounds, instead of 3
        h.setParalyzed(true, dmg, terrainUnderFeet == 'W'
                ? Constants.MAX_ROUNDS : Constants.MIN_ROUNDS);
    }
    // method chooses the right strategy
    public final void chooseStrategy() {
        // (1/7 * MAX_LEVEL_HP) < CURRENT_HP < (1/5 * MAX_LEVEL_HP)
        if (getBaseHP() / Constants.R_STRAT_LOW < getHp()
                && getBaseHP() / Constants.R_STRAT_HIGH > getHp()) {
            setStrategy(new Strategy1((float) (1 / Constants.R_STRAT_LOW), Constants.MOD_04));
            // CURRENT_HP < (1/7 * MAX_LEVEL_HP)
        } else if (getHp() < getBaseHP() / Constants.R_STRAT_LOW) {
            setStrategy(new Strategy2(Constants.MOD_05, Constants.MOD_01));
        } else { // nothing changes
            setStrategy(new DefaultStrategy());
        }
    }
}
