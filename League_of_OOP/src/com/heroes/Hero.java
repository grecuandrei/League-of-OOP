package heroes;

import static java.lang.Integer.max;

public abstract class Hero{
    private int xp = 0, level = 0, multiplier = 0, X, Y, roundsApply;
    private float hp, baseHP, dmgInflicted, dmgOvertime;
    private boolean dead = false, incap = false, ignited = false, paralyzed = false, attacked = false;
    private char terrainUnderFeet;
    private String name;

    public Hero(int x, int y) {
        X = x;
        Y = y;
    }

    public String getName() {
        return name;
    }

    public int getXp() {
        return xp;
    }

    public void setXp(int xp) {
        this.xp = xp;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public float getHp() {
        return hp;
    }

    public void setHp(float hp) {
        this.hp = hp;
    }

    public float getBaseHP() {
        return baseHP;
    }

    public void setBaseHP(float baseHP) {
        this.baseHP = baseHP;
    }

    public void setMultiplier(int multiplier) {
        this.multiplier = multiplier;
    }

    public void resetHp() {
//        System.out.println(hp + " " + baseHP + " " + multiplier + " " + level);
        hp = baseHP + multiplier * level;
//        System.out.println(hp + " " + baseHP + " " + multiplier + " " + level);
    }

    public boolean isDead() {
        return dead;
    }

    public void setDead(boolean dead) {
        this.dead = dead;
    }

    public boolean isIncap() {
        return incap;
    }

    public void setIncap(boolean incap) {
        this.incap = incap;
        this.dmgOvertime = 0;
        this.roundsApply = 0;
    }

    public void setIgnited(boolean ignited, float dmgOvertime, int roundsApply) {
//        System.out.println(dmgOvertime + this.getName());
        this.ignited = ignited;
        this.dmgOvertime = dmgOvertime;
        this.roundsApply = roundsApply;
//        System.out.println(dmgOvertime + this.dmgOvertime);
    }

    public boolean isParalised() {
        return paralyzed;
    }

    public void setParalised(boolean paralyzed, float dmgOvertime, int roundsApply) {
        this.paralyzed = paralyzed;
        this.dmgOvertime = dmgOvertime;
        this.roundsApply = roundsApply;
    }

    public int getX() {
        return X;
    }

    public void setX(int x) {
        X = x;
    }

    public int getY() {
        return Y;
    }

    public void setY(int y) {
        Y = y;
    }

    public boolean isAttacked() {
        return attacked;
    }

    public void setAttacked(boolean attacked) {
        this.attacked = attacked;
    }

    public char getTerrainUnderFeet() {
        return terrainUnderFeet;
    }

    public void setTerrainUnderFeet(char terrainUnderFeet) {
        this.terrainUnderFeet = terrainUnderFeet;
    }

    public float getDmgInflicted() {
        return dmgInflicted;
    }

    public void setDmgInflicted(float dmgInflicted) {
        this.dmgInflicted = dmgInflicted;
    }

    public int getRoundsApply() {
        return roundsApply;
    }

    public void setRoundsApply(int roundsApply) {
        this.roundsApply = roundsApply;
    }

    public float getDmgOvertime() {
        return dmgOvertime;
    }

    public void setDmgOvertime(float dmgOvertime) {
        this.dmgOvertime = dmgOvertime;
    }

    public float attack(Pyromancer p){
        return 0;
    };

    public float attack(Knight k){
        return 0;
    };
    
    public float attack(Wizard w){
        return 0;
    };

    public float attack(Rogue r){
        return 0;
    };
    
    public float acceptAttack(Hero h) {
        return 0;
    }

    public float calculateFlatDmg() {
        return 0;
    }
    
    public void dealDMG(Hero h, float dmg) {
        h.setHp(h.getHp() - Math.round(dmg));
        if (h.getHp() <= 0) {
            h.setDead(true);
            xpWinner(h.getLevel());
        }
    }

    public void xpWinner(int loserLvl) {
//        System.out.println("Levele " + getLevel() + " " + loserLvl + " " + xp);
        this.setXp(this.getXp() + max(0, 200 - (level - loserLvl) * 40));
//        System.out.println("Levele " + getLevel() + " " + loserLvl + " " + xp);
//        System.out.println(getHp());
    }

    public void levelUP() {
        while (xp >= 250 + level * 50) {
            int levelUp = level;
            levelUp++;
            setLevel(levelUp);
            resetHp();
        }
    }
}
