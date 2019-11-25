package heroes;

import constants.Constants;

public class Rogue extends Hero {
    private int backstabNr;
    
    Rogue() {
        setHp(Constants.INITIAL_HP_ROGUE);
        setBaseHP(Constants.INITIAL_HP_ROGUE);
        setMultiplier(Constants.LEVEL_HP_MULTIPLIER);
        backstabNr = -1;
    }

    @Override
    public String getName() {
        return "R";
    }

    public float acceptAttack(Hero h) {
        return h.attack(this);
    }
    
    public float attackBackstab() {
        float dmg = Constants.BASE_DMG_BACKSTAB_ROGUE + Constants.BASE_MULTIPLIER_BACKSTAB * getLevel();
        backstabNr++;
        if (backstabNr % Constants.HITS_BACKSTAB == 0 && terrainUnderFeet == 'W') {
            dmg = dmg * Constants.CRIT_DMG * Constants.LAND_MODIFIER_ROGUE;
        } else if (terrainUnderFeet == 'W' && backstabNr % Constants.HITS_BACKSTAB != 0){
            dmg = dmg * Constants.LAND_MODIFIER_ROGUE;
        }
        return Math.round(dmg);
    }

    public float attackParalysis() {
        float dmg = Constants.BASE_DMG_PARALYSIS_ROGUE + Constants.BASE_MULTIPLIER_PARALYSIS * getLevel();
        if (terrainUnderFeet == 'W') {
            dmg = dmg * Constants.LAND_MODIFIER_ROGUE;
        }
        return Math.round(dmg);
    }

    public float calculateFlatDmg() {
        float dmg1 = attackBackstab();
        backstabNr--;
        float dmg2 = attackParalysis();
        return Math.round(dmg1 + dmg2);
    }
    
    public float attack(Rogue r){
        float dmg1 = attackBackstab();
        dmg1 = Math.round(dmg1 * Constants.BACKSTAB_MULTIPLIER_ROGUE);
        float dmg2 = attackParalysis();
        modifyOvtDmg(r);
        dmg2 = Math.round(dmg2 * Constants.PARALYSIS_MULTIPLIER_ROGUE);
        r.setDmgOvertime(dmg2);
        return dmg1 + dmg2;
    }

    public float attack(Knight k){
        float dmg1 = attackBackstab();
        dmg1 = Math.round(dmg1 * Constants.BACKSTAB_MULTIPLIER_KNIGHT);
        float dmg2 = attackParalysis();
        dmg2 = Math.round(dmg2 * Constants.PARALYSIS_MULTIPLIER_KNIGHT);
        modifyOvtDmg(k);
        k.setDmgOvertime(dmg2);
        return dmg1 + dmg2;
    }

    public float attack(Pyromancer p){
        float dmg1 = attackBackstab();
        dmg1 = Math.round(dmg1 * Constants.BACKSTAB_MULTIPLIER_PYRO);
        float dmg2 = attackParalysis();
        dmg2 = Math.round(dmg2 * Constants.PARALYSIS_MULTIPLIER_PYRO);
        modifyOvtDmg(p);
        p.setDmgOvertime(dmg2);
        return dmg1 + dmg2;
    }

    public float attack(Wizard w){
        float dmg1 = attackBackstab();
        dmg1 = Math.round(dmg1 * Constants.BACKSTAB_MULTIPLIER_WIZARD);
        float dmg2 = attackParalysis();
        dmg2 = Math.round(dmg2 * Constants.PARALYSIS_MULTIPLIER_WIZARD);
        modifyOvtDmg(w);
        w.setDmgOvertime(dmg2);
        return dmg1 + dmg2;
    }
    
    private void modifyOvtDmg(Hero h){
        float dmg = Constants.PARALISED_BASE_DMG + Constants.PARALISED_MULTIPLIER * getLevel();
        h.setIncap(false);
        h.setIgnited(0, 0);
        h.setParalised(true, dmg, terrainUnderFeet == 'W' ? 6 : 3);
    }
}
