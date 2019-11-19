package com.heroes;

import com.map.Terrain;

public class Wizard extends Hero {
    public Wizard() {
        setHp(400);
        setMultiplier(30);
    }

    public float attackDrain() {
        return 0.2f + 0.05f * getLevel();
    }

    // todo TODO-IT
    public float attackDeflect(Hero h) {
        float dmg = 100 + 40 * getLevel();
        /// todo overtime dmg
//        overtimeDMG = 
//        if (getTerrainType() = Terrain.DESERT) {
//            dmg += dmg * 0.1;
//        }
        return dmg;
    }

    @Override
    public void attack() {};

    public void attack(Rogue r){
        if (!r.isDead()) {
            float percent = attackDrain();
            percent -= 0.2 * percent;
            float dmg = (float) (percent * Math.min(0.3 * r.getBaseHP(), r.getHp()));
            dmg = attackDeflect(r);
            /// todo adauga terrain modifier
//            if (getTerrainType() = Terrain.DESERT) {
//                dmg += dmg * 0.1;
//            }
            r.setHp(r.getHp() - Math.round(dmg));
            if (r.getHp() <= 0) {
                exchangeXP(r);
            }
        }
    }

    public void attack(Knight k){
        if (!k.isDead()) {
            float percent = attackDrain();
            percent += 0.2 * percent;
            float dmg = (float) (percent * Math.min(0.3 * k.getBaseHP(), k.getHp()));
            k.setHp(k.getHp() - Math.round(dmg));
            /// todo adauga terrain modifier
//            if (getTerrainType() = Terrain.DESERT) {
//                dmg += dmg * 0.1;
//            }
            dmg = attackDeflect(k);
            k.setHp(k.getHp() - Math.round(dmg + dmg * 0.2));
            if (k.getHp() <= 0) {
                exchangeXP(k);
            }
        }
    }

    public void attack(Pyromancer p){
        if (!p.isDead()) {
            float percent = attackDrain();
            percent -= 0.1 * percent;
            float dmg = (float) (percent * Math.min(0.3 * p.getBaseHP(), p.getHp()));
            p.setHp(p.getHp() - Math.round(dmg));
            /// todo adauga terrain modifier
//            if (getTerrainType() = Terrain.DESERT) {
//                dmg += dmg * 0.1;
//            }
            dmg = attackDeflect(p);
            p.setHp(p.getHp() - Math.round(dmg - dmg * 0.1));
            if (p.getHp() <= 0) {
                exchangeXP(p);
            }
        }
    }

    public void attack(Wizard w){
        if (!w.isDead()) {
            float percent = attackDrain();
            percent += 0.05 * percent;
            float dmg = (float) (percent * Math.min(0.3 * w.getBaseHP(), w.getHp()));
            w.setHp(w.getHp() - Math.round(dmg));
            /// todo adauga terrain modifier
//            if (getTerrainType() = Terrain.DESERT) {
//                dmg += dmg * 0.1;
//            }
            if (w.getHp() <= 0) {
                exchangeXP(w);
            }
        }
    }
}
