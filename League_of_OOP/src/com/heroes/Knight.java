package heroes;

import angels.Angel;
import constants.Constants;
import strategy.DefaultStrategy;
import strategy.Strategy_1;
import strategy.Strategy_2;

import java.io.IOException;

public class Knight extends Hero {
    // initialization of a Knight
    Knight() {
        setHp(Constants.INITIAL_HP_KNIGHT);
        setBaseHP(Constants.INITIAL_HP_KNIGHT);
        setMultiplier(Constants.LEVEL_HP_MULTIPLIER_KNIGHT);
        setName("K");
    }

    // accept visitor from the Double-Dispatch pattern
    public final float acceptAttack(final Hero h) {
        return h.attack(this);
    }
    
    public final void acceptAngel(final Angel a) throws IOException {
        if (!this.isDead()) {
            a.apply(this);
        }
    }
    // implementation of first attack
    private float attackExecute(final Hero h) {
        float dmg = Constants.BASE_DMG_EXECUTE_KNIGHT
                + Constants.BASE_MULTIPLIER_EXECUTE * getLevel(), hpLimit;
        if (terrainUnderFeet == 'L') {
            dmg = dmg * Constants.LAND_MODIFIER_KNIGHT;
        }
        // check if the hp is under a certain limit
        if (Constants.HP_PERCENT * h.getBaseHP()
                + Constants.LEVEL_PERCENT * h.getLevel() <= Constants.HP_LIMIT) {
            hpLimit = Constants.HP_PERCENT * h.getBaseHP()
                    + Constants.LEVEL_PERCENT * h.getLevel();
        } else {
            hpLimit = Constants.HP_PERCENT * h.getBaseHP() + Constants.HP_LIMIT;
        }
        // and see if it kills him
        if (h.getHp() - hpLimit <= 0) {
            h.setDead(true);
            dmg = h.getHp();
        }
//        return Math.round(dmg);
        return dmg;
    }
    // implementation of the second attack
    private float attackSlam() {
        float dmg = Constants.BASE_DMG_SLAM + Constants.BASE_MULTIPLIER_SLAM * getLevel();
        if (terrainUnderFeet == 'L') {
            dmg = dmg * Constants.LAND_MODIFIER_KNIGHT;
        }
//        return Math.round(dmg);
        return dmg;
    }
    // method that returns the base damage dealt for wizard
    public final float calculateFlatDmg() {
        float dmg1 = Constants.BASE_DMG_EXECUTE_KNIGHT
                + Constants.BASE_MULTIPLIER_EXECUTE * getLevel();
        if (terrainUnderFeet == 'L') {
            dmg1 = dmg1 * Constants.LAND_MODIFIER_KNIGHT;
        }
        float dmg2 = attackSlam();
        return dmg1 + dmg2;
    }
    // calculate dmg for Rogue
    public final float attack(final Rogue r) {
        float dmg1 = attackExecute(r);
        if (!r.isDead()) {
            dmg1 = Math.round(dmg1 * (Constants.EXECUTE_MULTIPLIER_ROGUE + angelDmgModifier + strategyModifier));
        }
        float dmg2 = attackSlam();
        dmg2 = Math.round(dmg2 * (Constants.SLAM_MULTIPLIER_ROGUE + angelDmgModifier + strategyModifier));
        modifyOvtDmg(r);
        return dmg1 + dmg2;
    }
    // calculate dmg for Knight
    public final float attack(final Knight k) {
        float dmg1 = attackExecute(k);
        if (!k.isDead()) {
            dmg1 = Math.round(dmg1);
        }
        float dmg2 = attackSlam();
        dmg2 = Math.round(dmg2 * (Constants.SLAM_MULTIPLIER_KNIGHT + angelDmgModifier + strategyModifier));
        modifyOvtDmg(k);
        System.out.println(Constants.SLAM_MULTIPLIER_KNIGHT + " " + angelDmgModifier + " " + strategyModifier);
        System.out.println((Constants.SLAM_MULTIPLIER_KNIGHT + angelDmgModifier + strategyModifier));;
        return dmg1 + dmg2;
    }
    // calculate dmg for Pyromancer
    public final float attack(final Pyromancer p) {
        float dmg1 = attackExecute(p);
        if (!p.isDead()) {
            dmg1 = Math.round(dmg1 * (Constants.EXECUTE_MULTIPLIER_PYRO + angelDmgModifier + strategyModifier));
        }
        float dmg2 = attackSlam();
        dmg2 = Math.round(dmg2 * (Constants.SLAM_MULTIPLIER_PYRO + angelDmgModifier + strategyModifier));
        modifyOvtDmg(p);
        return dmg1 + dmg2;
    }
    // calculate dmg for Wizard
    public final float attack(final Wizard w) {
        float dmg1 = attackExecute(w);
        if (!w.isDead()) {
            dmg1 = Math.round(dmg1 * (Constants.EXECUTE_MULTIPLIER_WIZARD + angelDmgModifier + strategyModifier));
        }
        float dmg2 = attackSlam();
        dmg2 = Math.round(dmg2 * (Constants.SLAM_MULTIPLIER_WIZARD + angelDmgModifier + strategyModifier));
        modifyOvtDmg(w);
        return dmg1 + dmg2;
    }
    // method that modifies the overtime parameters
    private void modifyOvtDmg(final Hero h) {
        h.setIgnited(false, 0, 0);
        h.setParalyzed(false, 0, 0);
        h.setIncap(true);
    }
    
    public void chooseStrategy() {
        if (getBaseHP() / 3 < getHp() && getBaseHP() / 2 > getHp()) { // (1/3 * MAX_LEVEL_HP) < CURRENT_HP < (1/2 * MAX_LEVEL_HP)
            setStrategy(new Strategy_1(0.2f,0.5f));
        } else if (getHp() < getBaseHP() / 3) { // CURRENT_HP < (1/3 * MAX_LEVEL_HP)
            setStrategy(new Strategy_2(0.25f,0.2f));
        } else {
            setStrategy(new DefaultStrategy(0,0));
        }
    }
}

