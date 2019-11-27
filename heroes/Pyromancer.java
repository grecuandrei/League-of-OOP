package heroes;

import constants.Constants;

public class Pyromancer extends Hero {
    // initialization of a Pyromancer
    Pyromancer() {
        setHp(Constants.INITIAL_HP_PYRO);
        setBaseHP(Constants.INITIAL_HP_PYRO);
        setMultiplier(Constants.LEVEL_HP_MULTIPLIER_PYRO);
        setName("P");
    }

    // accept visitor from the Double-Dispatch pattern
    public final float acceptAttack(final Hero h) {
        return h.attack(this);
    }
    // implementation of first attack
    private float attackFireblast() {
        float dmg = Constants.BASE_DMG_FIREBLAST
                + Constants.BASE_MULTIPLIER_FIREBLAST * getLevel();
        if (terrainUnderFeet == 'V') {
            dmg = dmg * Constants.LAND_MODIFIER_PYRO;
        }
        return Math.round(dmg);
    }
    // implementation of the second attack
    private float attackIgnite() {
        float dmg = Constants.BASE_DMG_IGNITE
                + Constants.BASE_MULTIPLIER_IGNITE * getLevel();
        if (terrainUnderFeet == 'V') {
            dmg = dmg * Constants.LAND_MODIFIER_PYRO;
        }
        return Math.round(dmg);
    }
    // method that returns the base damage dealt for wizard
    public final float calculateFlatDmg() {
        float dmg1 = attackFireblast();
        float dmg2 = attackIgnite();
        return dmg1 + dmg2;
    }
    // calculate dmg for Rogue
    public final float attack(final Rogue r) {
        float dmg1 = attackFireblast();
        dmg1 = Math.round(dmg1 * Constants.FIREBLAST_MULTIPLIER_ROGUE);
        float dmg2 = attackIgnite();
        dmg2 = Math.round(dmg2 * Constants.IGNITE_MULTIPLIER_ROGUE);
        modifyOvtDmg(r, Constants.IGNITE_MULTIPLIER_ROGUE);
        return dmg1 + dmg2;
    }
    // calculate dmg for Knight
    public final float attack(final Knight k) {
        float dmg1 = attackFireblast();
        dmg1 = Math.round(dmg1 * Constants.FIREBLAST_MULTIPLIER_KNIGHT);
        float dmg2 = attackIgnite();
        dmg2 = Math.round(dmg2 * Constants.IGNITE_MULTIPLIER_KNIGHT);
        modifyOvtDmg(k, Constants.IGNITE_MULTIPLIER_KNIGHT);
        return dmg1 + dmg2;
    }
    // calculate dmg for Pyromancer
    public final float attack(final Pyromancer p) {
        float dmg1 = attackFireblast();
        dmg1 = Math.round(dmg1 * Constants.FIREBLAST_MULTIPLIER_PYRO);
        float dmg2 = attackIgnite();
        dmg2 = Math.round(dmg2 * Constants.IGNITE_MULTIPLIER_PYRO);
        modifyOvtDmg(p, Constants.IGNITE_MULTIPLIER_PYRO);
        return dmg1 + dmg2;
    }
    // calculate dmg for Wizard
    public final float attack(final Wizard w) {
        float dmg1 = attackFireblast();
        dmg1 = Math.round(dmg1 * Constants.FIREBLAST_MULTIPLIER_WIZARD);
        float dmg2 = attackIgnite();
        dmg2 = Math.round(dmg2 * Constants.IGNITE_MULTIPLIER_WIZARD);
        modifyOvtDmg(w, Constants.IGNITE_MULTIPLIER_WIZARD);
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
}
