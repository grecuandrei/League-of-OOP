package heroes;

import angels.Angel;
import constants.Constants;
import strategy.DefaultStrategy;
import strategy.Strategy1;
import strategy.Strategy2;

import java.io.IOException;

public class Wizard extends Hero {
    // initialization of a Wizard
    Wizard() {
        setHp(Constants.INITIAL_HP_WIZARD);
        setBaseHP(Constants.INITIAL_HP_WIZARD);
        setMultiplier(Constants.LEVEL_HP_MULTIPLIER_WIZARD);
        setName("W");
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
    private float attackDrain() {
        return Constants.BASE_PERCENT_DRAIN_WIZARD
                + Constants.BASE_MULTIPLIER_DRAIN * getLevel();
    }
    // implementation of the second attack
    private float attackDeflect() {
        double percent = Constants.BASE_PERCENT_DEFLECT
                + Constants.BASE_MULTIPLIER_DEFLECT * getLevel();
        if (percent > Constants.MAX_PERCENTAGE) {
            percent = Constants.MAX_PERCENTAGE;
        }
        // it uses the damage dealt by others
        float dmgFin = (float) (getDmgInflicted() * percent);
        if (terrainUnderFeet == 'D') {
            dmgFin = dmgFin * Constants.LAND_MODIFIER_WIZARD;
        }
        return dmgFin;
    }
    // calculate dmg for Rogue
    public final float attack(final Rogue r) {
        float percent = attackDrain();
        percent = percent
                * (Constants.DRAIN_MULTIPLIER_ROGUE + angelDmgModifier + strategyModifier);
        float dmg1 = percent * Math.min(Constants.PERCENTAGE_BASE_HP * r.getBaseHP(), r.getHp());
        if (terrainUnderFeet == 'D') {
            dmg1 = dmg1 * Constants.LAND_MODIFIER_WIZARD;
        }
        dmg1 = Math.round(dmg1);
        float dmg2 = attackDeflect();
        dmg2 = Math.round(dmg2
                * (Constants.DEFLECT_MULTIPLIER_ROGUE + angelDmgModifier + strategyModifier));
        return dmg1 + dmg2;
    }
    // calculate dmg for Knight
    public final float attack(final Knight k) {
        float percent = attackDrain();
        percent = percent
                * (Constants.DRAIN_MULTIPLIER_KNIGHT + angelDmgModifier + strategyModifier);
        float dmg1 = percent * Math.min(Constants.PERCENTAGE_BASE_HP * k.getBaseHP(), k.getHp());
        if (terrainUnderFeet == 'D') {
            dmg1 = dmg1 * Constants.LAND_MODIFIER_WIZARD;
        }
        dmg1 = Math.round(dmg1);
        float dmg2 = attackDeflect();
        dmg2 = Math.round(dmg2
                * (Constants.DEFLECT_MULTIPLIER_KNIGHT + angelDmgModifier + strategyModifier));
        return dmg1 + dmg2;
    }
    // calculate dmg for Pyromancer
    public final float attack(final Pyromancer p) {
        float percent = attackDrain();
        percent = percent
                * (Constants.DRAIN_MULTIPLIER_PYRO + angelDmgModifier + strategyModifier);
        float dmg1 = percent * Math.min(Constants.PERCENTAGE_BASE_HP * p.getBaseHP(), p.getHp());
        if (terrainUnderFeet == 'D') {
            dmg1 = dmg1 * Constants.LAND_MODIFIER_WIZARD;
        }
        dmg1 = Math.round(dmg1);
        float dmg2 = attackDeflect();
        dmg2 = Math.round(dmg2
                * (Constants.DEFLECT_MULTIPLIER_PYRO + angelDmgModifier + strategyModifier));
        return dmg1 + dmg2;
    }
    // calculate dmg for Wizard
    public final float attack(final Wizard w) {
        float percent = attackDrain();
        percent = percent
                * (Constants.DRAIN_MULTIPLIER_WIZARD + angelDmgModifier + strategyModifier);
        float dmg = percent * Math.min(Constants.PERCENTAGE_BASE_HP * w.getBaseHP(), w.getHp());
        if (terrainUnderFeet == 'D') {
            dmg = dmg * Constants.LAND_MODIFIER_WIZARD;
        }
        return Math.round(dmg);
    }
    // method chooses right strategy
    public final void chooseStrategy() {
        // (1/4 * MAX_LEVEL_HP) < CURRENT_HP < (1/2 * MAX_LEVEL_HP)
        if (getBaseHP() / Constants.W_STRAT_LOW < getHp()
                && getBaseHP() / Constants.W_STRAT_HIGH > getHp()) {
            setStrategy(new Strategy1(Constants.MOD_01, Constants.MOD_06));
            // CURRENT_HP < (1/4 * MAX_LEVEL_HP)
        } else if (getHp() < getBaseHP() / Constants.W_STRAT_LOW) {
            setStrategy(new Strategy2(Constants.MOD_02, Constants.MOD_02));
        } else {
            setStrategy(new DefaultStrategy());
        }
    }
}
