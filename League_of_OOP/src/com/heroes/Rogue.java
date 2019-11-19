package com.heroes;

import com.map.Terrain;

public class Rogue extends Hero {
    private int backstabNr;
    
    public Rogue() {
        setHp(600);
        setMultiplier(40);
        backstabNr = 0;
    }

    public float attackBackstab() {
        float dmg = 200 + 20 * getLevel();
        backstabNr++;
            /// todo uncomment this when map is ready
//        if (backstabNr == 3 && getTerrainType() == Terrain.LAND) {
//            dmg += dmg * 1.5;
//            dmg += dmg * 0.15;
//        } else if (backstabNr == 3 && getTerrainType() != Terrain.LAND) {
//            backstabNr = 0;
//        } else if (getTerrainType() == Terrain.LAND){
//            dmg += dmg * 0.15;
//        }
        return dmg;
    }

    public float attackParalysis(Hero h) {
        float dmg = 40 + 10 * getLevel();
        h.setIncap(false);
        h.setIgnited(false);
        h.setParalised(true);
        // todo Terrain type modificator
//        int roundsNr = getTerrainType() == Terrain.LAND ? 6 : 3;
//        if (getTerrainType() = Terrain.LAND) {
//            dmg += dmg * 0.15;
//        }
        return dmg;
    }

    @Override
    public void attack() {};

    public void attack(Rogue r){
        if (!r.isDead()) {
            float dmg = attackBackstab();
            r.setHp(r.getHp() - Math.round(dmg + dmg * 0.2));
            dmg = attackParalysis(r);
            r.setHp(r.getHp() - Math.round(dmg - dmg * 0.1));
            if (r.getHp() <= 0) {
                exchangeXP(r);
            }
        }
    }

    public void attack(Knight k){
        if (!k.isDead()) {
            float dmg = attackBackstab();
            k.setHp(k.getHp() - Math.round(dmg - dmg * 0.1));
            dmg = attackParalysis(k);
            k.setHp(k.getHp() - Math.round(dmg - dmg * 0.2));
            if (k.getHp() <= 0) {
                exchangeXP(k);
            }
        }
    }

    public void attack(Pyromancer p){
        if (!p.isDead()) {
            float dmg = attackBackstab();
            p.setHp(p.getHp() - Math.round(dmg + dmg * 0.25));
            dmg = attackParalysis(p);
            p.setHp(p.getHp() - Math.round(dmg + dmg * 0.2));
            if (p.getHp() <= 0) {
                exchangeXP(p);
            }
        }
    }

    public void attack(Wizard w){
        if (!w.isDead()) {
            float dmg = attackBackstab();
            w.setHp(w.getHp() - Math.round(dmg - dmg * 0.25));
            dmg = attackParalysis(w);
            w.setHp(w.getHp() - Math.round(dmg + dmg * 0.25));
            if (w.getHp() <= 0) {
                exchangeXP(w);
            }
        }
    }
}
