package heroes;

import constants.Constants;

import static java.lang.Integer.max;

public abstract class Hero {
    private int xp = 0, level = 0, multiplier = 0, x, y, roundsApply;
    private float hp, baseHP, dmgInflicted, dmgOvertime;
    private boolean dead = false;
    private boolean incap = false;
    private boolean paralyzed = false;
    private boolean attacked = false;
    protected char terrainUnderFeet;
    private String name;
    public Hero() { }

    public String getName() {
        return name;
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

    public final float getHp() {
        return hp;
    }

    public final void setHp(final float hp) {
        this.hp = hp;
    }

    public final float getBaseHP() {
        return baseHP;
    }

    public final void setBaseHP(final float baseHP) {
        this.baseHP = baseHP;
    }

    public final void setMultiplier(final int multiplier) {
        this.multiplier = multiplier;
    }

    public final void resetHp() {
        System.out.println(hp + " " + baseHP + " " + multiplier + " " + level);
        hp = baseHP + multiplier * level;
        System.out.println(hp + " " + baseHP + " " + multiplier + " " + level);
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

    public final void setIncap(final boolean incap) {
        this.incap = incap;
        this.dmgOvertime = 0;
        this.roundsApply = 0;
    }

    public final void setIgnited(final float dmg_overtime, final int rounds_apply) {
        this.dmgOvertime = dmg_overtime;
        this.roundsApply = rounds_apply;
    }

    public final boolean isParalised() {
        return paralyzed;
    }

    public final void setParalised(final boolean paralyze, final float dmg_overtime,
                             final int rounds_apply) {
        this.paralyzed = paralyze;
        this.dmgOvertime = dmg_overtime;
        this.roundsApply = rounds_apply;
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

    public final void setDmgOvertime(final float dmgOvertime) {
        this.dmgOvertime = dmgOvertime;
    }

    public float attack(final Pyromancer p){
        return 0;
    };

    public float attack(final Knight k){
        return 0;
    };
    
    public float attack(final Wizard w){
        return 0;
    };

    public float attack(final Rogue r){
        return 0;
    };
    
    public float acceptAttack(final Hero h) {
        return 0;
    }

    public float calculateFlatDmg() {
        return 0;
    }
    
    public void dealDMG(final Hero h, final float dmg) {
        h.setHp(h.getHp() - Math.round(dmg));
        if (h.getHp() <= 0) {
            h.setDead(true);
            xpWinner(h.getLevel());
        }
    }

    public void xpWinner(final int loserLvl) {
        xp = xp + max(0, Constants.BASE_XP_XP_WINNER - (level - loserLvl) * Constants.MULTIPLIER_XP_WINNER);
    }

    public void levelUP() {
        while (xp >= Constants.BASE_XP_LEVEL_UP + level * Constants.MULTIPLIER_LEVEL_UP) {
            int levelUp = level;
            levelUp++;
            setLevel(levelUp);
            resetHp();
        }
    }
}
