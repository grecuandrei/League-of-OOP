package heroes;

import constants.Constants;

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
        percent = Constants.DRAIN_MULTIPLIER_ROGUE * percent;
        float dmg1 = percent * Math.min(Constants.PERCENTAGE_BASE_HP * r.getBaseHP(), r.getHp());
        if (terrainUnderFeet == 'D') {
            dmg1 = dmg1 * Constants.LAND_MODIFIER_WIZARD;
        }
        dmg1 = Math.round(dmg1);
        float dmg2 = attackDeflect();
        dmg2 = Math.round(dmg2 * Constants.DEFLECT_MULTIPLIER_ROGUE);
        return dmg1 + dmg2;
    }
    // calculate dmg for Knight
    public final float attack(final Knight k) {
        float percent = attackDrain();
        percent = Constants.DRAIN_MULTIPLIER_KNIGHT * percent;
        float dmg1 = percent * Math.min(Constants.PERCENTAGE_BASE_HP * k.getBaseHP(), k.getHp());
        if (terrainUnderFeet == 'D') {
            dmg1 = dmg1 * Constants.LAND_MODIFIER_WIZARD;
        }
        dmg1 = Math.round(dmg1);
        float dmg2 = attackDeflect();
        dmg2 = Math.round(dmg2 * Constants.DEFLECT_MULTIPLIER_KNIGHT);
        return dmg1 + dmg2;
    }
    // calculate dmg for Pyromancer
    public final float attack(final Pyromancer p) {
        float percent = attackDrain();
        percent = Constants.DRAIN_MULTIPLIER_PYRO * percent;
        float dmg1 = percent * Math.min(Constants.PERCENTAGE_BASE_HP * p.getBaseHP(), p.getHp());
        if (terrainUnderFeet == 'D') {
            dmg1 = dmg1 * Constants.LAND_MODIFIER_WIZARD;
        }
        dmg1 = Math.round(dmg1);
        float dmg2 = attackDeflect();
        dmg2 = Math.round(dmg2 * Constants.DEFLECT_MULTIPLIER_PYRO);
        return dmg1 + dmg2;
    }
    // calculate dmg for Wizard
    public final float attack(final Wizard w) {
        float percent = attackDrain();
        percent = Constants.DRAIN_MULTIPLIER_WIZARD * percent;
        float dmg = percent * Math.min(Constants.PERCENTAGE_BASE_HP * w.getBaseHP(), w.getHp());
        if (terrainUnderFeet == 'D') {
            dmg = dmg * Constants.LAND_MODIFIER_WIZARD;
        }
        return Math.round(dmg);
    }
}
