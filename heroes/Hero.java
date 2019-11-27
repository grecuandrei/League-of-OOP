package heroes;

import constants.Constants;

import static java.lang.Integer.max;

public abstract class Hero {
    private int xp = 0, level = 0, multiplier = 0, x, y, roundsApply = 0, hp, baseHP;
    private float dmgInflicted, dmgOvertime;
    private boolean dead = false, incap = false, ignited = false;
    private boolean paralyzed = false, attacked = false;
    protected char terrainUnderFeet;
    private String name;
    public Hero() { }

    public final String getName() {
        return name;
    }

    public final void setName(final String name) {
        this.name = name;
    }

    public final int getXp() {
        return xp;
    }

    public final int getLevel() {
        return level;
    }

    public final void setLevel(final int level) {
        this.level = level;
    }

    public final int getHp() {
        return hp;
    }

    public final void setHp(final int hp) {
        this.hp = hp;
    }

    public final int getBaseHP() {
        return baseHP;
    }
    // this is the maxhp that will be set after a level up
    public final void setBaseHP(final int baseHP) {
        this.baseHP = baseHP;
    }

    public final void setMultiplier(final int multiplier) {
        this.multiplier = multiplier;
    }
    // the method calculates the new hp
    public final void resetHp() {
        hp = baseHP + multiplier * level;
    }

    public final boolean isDead() {
        return dead;
    }

    public final void setDead(final boolean dead) {
        this.dead = dead;
    }

    public final boolean isIncap() {
        return incap;
    }
    // method that changes the parameters so that the hero is incapacitated
    public final void setIncap(final boolean incap) {
        this.incap = incap;
        this.dmgOvertime = 0;
        this.roundsApply = 0;
    }
    // method that changes the parameters so that the hero is ignited
    public final void setIgnited(final boolean ignite, final float dmgOverTime,
                                 final int roundsApplied) {
        this.ignited = ignite;
        this.dmgOvertime = dmgOverTime;
        this.roundsApply = roundsApplied;
    }

    public final boolean isIgnited() {
        return ignited;
    }

    public final boolean isParalyzed() {
        return paralyzed;
    }
    // method that changes the parameters so that the hero is paralyzed
    public final void setParalyzed(final boolean paralyze, final float dmgOverTime,
                             final int roundsApplied) {
        this.paralyzed = paralyze;
        this.dmgOvertime = dmgOverTime;
        this.roundsApply = roundsApplied;
    }

    public final int getX() {
        return x;
    }

    public final void setX(final int x) {
        this.x = x;
    }

    public final int getY() {
        return y;
    }

    public final void setY(final int y) {
        this.y = y;
    }

    public final boolean isAttacked() {
        return attacked;
    }

    public final void setAttacked(final boolean attacked) {
        this.attacked = attacked;
    }

    public final void setTerrainUnderFeet(final char terrainUnderFeet) {
        this.terrainUnderFeet = terrainUnderFeet;
    }

    public final float getDmgInflicted() {
        return dmgInflicted;
    }

    public final void setDmgInflicted(final float dmgInflicted) {
        this.dmgInflicted = dmgInflicted;
    }

    public final int getRoundsApply() {
        return roundsApply;
    }

    public final void setRoundsApply(final int roundsApply) {
        this.roundsApply = roundsApply;
    }

    public final float getDmgOvertime() {
        return dmgOvertime;
    }

    /**
     * A part of the double dispatch specialized for Pyromancer.
     * @param p = the hero the attack will be upon
     * @return will be modified in the class that implements it
     */
    public float attack(final Pyromancer p) {
        return 0;
    }
    /**
     * A part of the double dispatch specialized for Knight.
     * @param k = the hero the attack will be upon
     * @return will be modified in the class that implements it
     */
    public float attack(final Knight k) {
        return 0;
    }
    /**
     * A part of the double dispatch specialized for Wizard.
     * @param w = the hero the attack will be upon
     * @return will be modified in the class that implements it
     */
    public float attack(final Wizard w) {
        return 0;
    }
    /**
     * A part of the double dispatch specialized for Rogue.
     * @param r = the hero the attack will be upon
     * @return will be modified in the class that implements it
     */
    public float attack(final Rogue r) {
        return 0;
    }
    /**
     * The method that accepts the attack from another player.
     * The "other half" of the double dispatch implementation.
     * @param h = the hero that accepts the attack
     * @return will be modified in the class that implements it
     */
    public float acceptAttack(final Hero h) {
        return 0;
    }

    /**
     * Method that calculates the base damage to be more intuitive.
     * Easier to parse the damage that will be dealt for the wizard.
     * To take and process the damage, so he can apply it accordingly.
     * @return
     */
    public float calculateFlatDmg() {
        return 0;
    }
    // deals the damage
    public final void dealDMG(final Hero h, final float dmg) {
        h.setHp(h.getHp() - Math.round(dmg));
        if (h.getHp() <= 0) {
            h.setDead(true);
            xpWinner(h.getLevel());
        }
    }
    // calculates the xp for the winner
    public final void xpWinner(final int loserLvl) {
        xp = xp + max(0, Constants.BASE_XP_XP_WINNER - (level - loserLvl)
                * Constants.MULTIPLIER_XP_WINNER);
    }
    // does the level up and resets hp if needed
    public final void levelUP() {
        while (xp >= Constants.BASE_XP_LEVEL_UP + level * Constants.MULTIPLIER_LEVEL_UP) {
            int levelUp = level;
            levelUp++;
            setLevel(levelUp);
            if (!isDead()) {
                resetHp();
            }
        }
    }
}
