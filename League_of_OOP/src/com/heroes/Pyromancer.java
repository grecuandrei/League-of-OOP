package com.heroes;

import com.map.Terrain;

public class Pyromancer extends Hero {
    private float overtimeDMG;
    public Pyromancer() {
        setHp(500);
        setMultiplier(50);
    }

    public float attackFireblast() {
        float dmg = 350 + 50 * getLevel();
//        if (getTerrainType() = Terrain.VOLCANIC) {
//            dmg += dmg * 0.25;
//        }
        return dmg;
    }
    
    public float attackIgnite(Hero h) {
        float dmg = 150 + 20 * getLevel();
        h.setIgnited(true);
        h.setIncap(false);
        h.setParalised(false);
        /// todo evoretime dmg
//        overtimeDMG = 
//        if (getTerrainType() = Terrain.VOLCANIC) {
//            dmg += dmg * 0.25;
//        }
        return dmg;
    }

    @Override
    public void attack() {};
    
    public void attack(Rogue r){
        float dmg = attackFireblast();
        r.setHp(r.getHp() - Math.round(dmg - dmg * 0.2));
        dmg = attackIgnite(r);
        r.setIgnited(true);
        r.setHp(r.getHp() - Math.round(dmg - dmg * 0.2));
        if (r.getHp() <= 0) {
            exchangeXP(r);
        }
    }

    public void attack(Knight k){
        float dmg = attackFireblast();
        k.setHp(k.getHp() - Math.round(dmg + dmg * 0.2));
        dmg = attackIgnite(k);
        k.setIgnited(true);
        k.setHp(k.getHp() - Math.round(dmg + dmg * 0.2));
        if (k.getHp() <= 0) {
            exchangeXP(k);
        }
    }

    public void attack(Pyromancer p){
        float dmg = attackFireblast();
        p.setHp(p.getHp() - Math.round(dmg - dmg * 0.1));
        p.setIgnited(true);
        dmg = attackIgnite(p);
        p.setHp(p.getHp() - Math.round(dmg - dmg * 0.1));
        if (p.getHp() <= 0) {
            exchangeXP(p);
        }
    }

    public void attack(Wizard w){
        float dmg = attackFireblast();
        w.setHp(w.getHp() - Math.round(dmg + dmg * 0.05));
        dmg = attackIgnite(w);
        w.setIgnited(true);
        w.setHp(w.getHp() - Math.round(dmg + dmg * 0.05));
        if (w.getHp() <= 0) {
            exchangeXP(w);
        }
    }
}
