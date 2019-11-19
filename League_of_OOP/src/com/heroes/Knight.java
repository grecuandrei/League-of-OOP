package com.heroes;

import com.map.Terrain;

public class Knight extends Hero {
    public Knight() {
        setHp(900);
        setMultiplier(30);
    }

    public float attackExecute(Hero h) {
        float dmg = 200 + 30 * getLevel(), hpLimit;
        if (h.getLevel() <= 40) {
            hpLimit = 0.2f * h.getBaseHP() + h.getLevel() / 100f;
        } else {
            hpLimit = 0.2f * h.getBaseHP() + 40f / 100f;
        }
        if (hpLimit - h.getHp() >= 0) {
            h.setDead(true);
        }
        
//        if (getTerrainType() = Terrain.LAND) {
//            dmg += dmg * 0.15;
//        }
        return dmg;
    }

    public float attackSlam(Hero h) {
        float dmg = 100 + 40 * getLevel();
        h.setIncap(true);
        h.setIgnited(false);
        h.setParalised(false);
        /// todo overtime dmg
//        overtimeDMG = 
//        if (getTerrainType() = Terrain.LAND) {
//            dmg += dmg * 0.15;
//        }
        return dmg;
    }

    @Override
    public void attack() {};

    public void attack(Rogue r){
        if (!r.isDead()) {
            float dmg = attackExecute(r);
            r.setHp(r.getHp() - Math.round(dmg + dmg * 0.15));
            dmg = attackSlam(r);
            r.setHp(r.getHp() - Math.round(dmg - dmg * 0.2));
            if (r.getHp() <= 0) {
                exchangeXP(r);
            }
        }
    }

    public void attack(Knight k){
        if (!k.isDead()) {
            float dmg = attackExecute(k);
            k.setHp(k.getHp() - Math.round(dmg));
            dmg = attackSlam(k);
            k.setHp(k.getHp() - Math.round(dmg + dmg * 0.2));
            if (k.getHp() <= 0) {
                exchangeXP(k);
            }
        }
    }

    public void attack(Pyromancer p){
        if (!p.isDead()) {
            float dmg = attackExecute(p);
            p.setHp(p.getHp() - Math.round(dmg + dmg * 0.1));
            dmg = attackSlam(p);
            p.setHp(p.getHp() - Math.round(dmg - dmg * 0.1));
            if (p.getHp() <= 0) {
                exchangeXP(p);
            }
        }
    }

    public void attack(Wizard w){
        if (!w.isDead()) {
            float dmg = attackExecute(w);
            w.setHp(w.getHp() - Math.round(dmg - dmg * 0.2));
            dmg = attackSlam(w);
            w.setHp(w.getHp() - Math.round(dmg + dmg * 0.05));
            if (w.getHp() <= 0) {
                exchangeXP(w);
            }
        }
    }
}

