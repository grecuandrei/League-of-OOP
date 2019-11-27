package heroes;

import constants.Constants;

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
        dmg1 = Math.round(dmg1 * Constants.BACKSTAB_MULTIPLIER_ROGUE);
        float dmg2 = attackParalysis();
        dmg2 = Math.round(dmg2 * Constants.PARALYSIS_MULTIPLIER_ROGUE);
        modifyOvtDmg(r, dmg2);
        return dmg1 + dmg2;
    }
    // calculate dmg for Knight
    public final float attack(final Knight k) {
        float dmg1 = attackBackstab();
        dmg1 = Math.round(dmg1 * Constants.BACKSTAB_MULTIPLIER_KNIGHT);
        float dmg2 = attackParalysis();
        dmg2 = Math.round(dmg2 * Constants.PARALYSIS_MULTIPLIER_KNIGHT);
        modifyOvtDmg(k, dmg2);
        return dmg1 + dmg2;
    }
    // calculate dmg for Pyromancer
    public final float attack(final Pyromancer p) {
        float dmg1 = attackBackstab();
        dmg1 = Math.round(dmg1 * Constants.BACKSTAB_MULTIPLIER_PYRO);
        float dmg2 = attackParalysis();
        dmg2 = Math.round(dmg2 * Constants.PARALYSIS_MULTIPLIER_PYRO);
        modifyOvtDmg(p, dmg2);
        return dmg1 + dmg2;
    }
    // calculate dmg for Wizard
    public final float attack(final Wizard w) {
        float dmg1 = attackBackstab();
        dmg1 = Math.round(dmg1 * Constants.BACKSTAB_MULTIPLIER_WIZARD);
        float dmg2 = attackParalysis();
        dmg2 = Math.round(dmg2 * Constants.PARALYSIS_MULTIPLIER_WIZARD);
        modifyOvtDmg(w, dmg2);
        return dmg1 + dmg2;
    }
    // method that modifies the overtime parameters
    private void modifyOvtDmg(final Hero h, final float dmg) {
//        float dmg = Constants.PARALISED_BASE_DMG +
//                Constants.PARALISED_MULTIPLIER * getLevel();
//        System.out.println("-------------------------------Rogue damage: " + dmg);
        h.setIncap(false);
        h.setIgnited(false, 0, 0);
        // if it's on a terrain favorable for Rogue, the paralize will take 6 rounds, instead of 3
        h.setParalyzed(true, dmg, terrainUnderFeet == 'W'
                ? Constants.MAX_ROUNDS : Constants.MIN_ROUNDS);
    }
}
