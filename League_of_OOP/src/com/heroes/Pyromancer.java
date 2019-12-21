package heroes;

import angels.Angel;
import constants.Constants;
import strategy.DefaultStrategy;
import strategy.Strategy_1;
import strategy.Strategy_2;

import java.io.IOException;

public class Pyromancer extends Hero {
    // initialization of a Pyromancer
    Pyromancer() {
        setHp(Constants.INITIAL_HP_PYRO);
        setBaseHP(Constants.INITIAL_HP_PYRO);
        setMultiplier(Constants.LEVEL_HP_MULTIPLIER_PYRO);
        setName("P");
    }
    
    public void usageAngel(Angel angel){
        
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
    private float attackFireblast() {
        float dmg = Constants.BASE_DMG_FIREBLAST
                + Constants.BASE_MULTIPLIER_FIREBLAST * getLevel();
        if (terrainUnderFeet == 'V') {
            dmg = dmg * Constants.LAND_MODIFIER_PYRO;
        }
        return dmg;
    }
    // implementation of the second attack
    private float attackIgnite() {
        float dmg = Constants.BASE_DMG_IGNITE
                + Constants.BASE_MULTIPLIER_IGNITE * getLevel();
        if (terrainUnderFeet == 'V') {
            dmg = dmg * Constants.LAND_MODIFIER_PYRO;
        }
        return dmg;
    }
    // method that returns the base damage dealt for wizard
    public final float calculateFlatDmg() {
        float dmg1 = Math.round(attackFireblast());
        float dmg2 = Math.round(attackIgnite());
        return dmg1 + dmg2;
    }
    // calculate dmg for Rogue
    public final float attack(final Rogue r) {
        float dmg1 = attackFireblast();
        dmg1 = Math.round(dmg1 * (Constants.FIREBLAST_MULTIPLIER_ROGUE + angelDmgModifier + strategyModifier));
        float dmg2 = attackIgnite();
        dmg2 = Math.round(dmg2 * (Constants.IGNITE_MULTIPLIER_ROGUE + angelDmgModifier + strategyModifier));
        modifyOvtDmg(r, (Constants.IGNITE_MULTIPLIER_ROGUE + angelDmgModifier + strategyModifier));
        return dmg1 + dmg2;
    }
    // calculate dmg for Knight
    public final float attack(final Knight k) {
        float dmg1 = attackFireblast();
        dmg1 = Math.round(dmg1 * (Constants.FIREBLAST_MULTIPLIER_KNIGHT + angelDmgModifier + strategyModifier));
        float dmg2 = attackIgnite();
        dmg2 = Math.round(dmg2 * (Constants.IGNITE_MULTIPLIER_KNIGHT + angelDmgModifier + strategyModifier));
        modifyOvtDmg(k, (Constants.IGNITE_MULTIPLIER_KNIGHT + angelDmgModifier + strategyModifier));
        return dmg1 + dmg2;
    }
    // calculate dmg for Pyromancer
    public final float attack(final Pyromancer p) {
        float dmg1 = attackFireblast();
        dmg1 = Math.round(dmg1 * (Constants.FIREBLAST_MULTIPLIER_PYRO + angelDmgModifier + strategyModifier));
        float dmg2 = attackIgnite();
        dmg2 = Math.round(dmg2 * (Constants.IGNITE_MULTIPLIER_PYRO + angelDmgModifier + strategyModifier));
        modifyOvtDmg(p, (Constants.IGNITE_MULTIPLIER_PYRO + angelDmgModifier + strategyModifier));
        return dmg1 + dmg2;
    }
    // calculate dmg for Wizard
    public final float attack(final Wizard w) {
        float dmg1 = attackFireblast();
        dmg1 = Math.round(dmg1 * (Constants.FIREBLAST_MULTIPLIER_WIZARD + angelDmgModifier + strategyModifier));
        float dmg2 = attackIgnite();
        dmg2 = Math.round(dmg2 * (Constants.IGNITE_MULTIPLIER_WIZARD + angelDmgModifier + strategyModifier));
        modifyOvtDmg(w, (Constants.IGNITE_MULTIPLIER_WIZARD + angelDmgModifier + strategyModifier));
        return dmg1 + dmg2;
    }
    // method that modifies the overtime parameters
    private void modifyOvtDmg(final Hero h, final float percent) {
        float igniteDmg = Constants.IGNITE_OVT_DMG + Constants.IGNITE_OVT_MULTIPLIER * getLevel();
        igniteDmg = igniteDmg * percent;
        if (terrainUnderFeet == 'V') {
            igniteDmg = igniteDmg * Constants.LAND_MODIFIER_PYRO;
        }
        h.setIncap(false);
        h.setParalyzed(false, 0, 0);
        h.setIgnited(true, Math.round(igniteDmg), Constants.IGNITE_ROUNDS);
    }

    public void chooseStrategy() {
        if (getBaseHP() / 4 < getHp() && getBaseHP() / 3 > getHp()) { // (1/4 * MAX_LEVEL_HP) < CURRENT_HP < (1/3 * MAX_LEVEL_HP)
            setStrategy(new Strategy_1((float)(1/4),0.7f));
        } else if (getHp() < getBaseHP() / 4) { // CURRENT_HP < (1/4 * MAX_LEVEL_HP)
            setStrategy(new Strategy_2((float)(1/3),0.3f));
        } else {
            setStrategy(new DefaultStrategy(0,0));
        }
    }
}
