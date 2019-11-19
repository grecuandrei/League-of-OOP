package com.heroes;

import static java.lang.Integer.max;

public abstract class Hero{
    private int xp = 250, level = 0, multiplier = 0;
    private float hp, baseHP;
    private boolean dead = false, incap = false, ignited = false, paralised = false;

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
        this.baseHP = hp;
    }

    public float getBaseHP() {
        return baseHP;
    }

    public void setMultiplier(int multiplier) {
        this.multiplier = multiplier;
    }

    public void resetHp() {
        hp = baseHP + multiplier * level;
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
    }

    public boolean isIgnited() {
        return ignited;
    }

    public void setIgnited(boolean ignited) {
        this.ignited = ignited;
    }

    public boolean isParalised() {
        return paralised;
    }

    public void setParalised(boolean paralised) {
        this.paralised = paralised;
    }

    public void xpWinner(int loserLvl) {
        xp = xp + max(0, 200 - (level - loserLvl) * 40);
        levelUP();
    }
    
    public void levelUP() {
        while (this.xp >= 250 + this.getLevel() * 50) {
            int levelUp = this.getLevel();
            levelUp++;
            this.setLevel(levelUp);
            resetHp();
        }
    }
    
    public void attack(){};
    
    public void exchangeXP(Hero h) {
        h.setDead(true);
        this.xpWinner(h.getLevel());
        if (this.getHp() <= 0) {
            this.setDead(true);
            h.xpWinner(this.getLevel());
        }
    }
}
