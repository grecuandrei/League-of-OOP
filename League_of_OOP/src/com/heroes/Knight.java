package heroes;

public class Knight extends Hero {

    public Knight(int x, int y) {
        super(x, y);
        setHp(900);
        setBaseHP(900);
        setMultiplier(30);
    }

    @Override
    public String getName() {
        return "K";
    }

    public float acceptAttack(Hero h) {
        return h.attack(this);
    }
    
    
    // todo re-check it Execute
    public float attackExecute(Hero h) {
        float dmg = 200 + 30 * getLevel(), hpLimit;
        if (getTerrainUnderFeet() == 'L') {
            dmg = dmg * 1.15f;
        }
        if (0.2f * h.getBaseHP() + 0.01f *h.getLevel() <= 0.4f) {
            hpLimit = 0.2f * h.getBaseHP() + 0.01f *h.getLevel();
        } else {
            hpLimit = 0.2f * h.getBaseHP() + 0.4f;
        }
        if (hpLimit - h.getHp() >= 0) {
            h.setDead(true);
        }
        return Math.round(dmg);
    }

    public float attackSlam() {
        float dmg = 100 + 40 * getLevel();
        if (getTerrainUnderFeet() == 'L') {
            dmg = dmg * 1.15f;
        }
        return Math.round(dmg);
    }

    public float calculateFlatDmg() {
        float dmg1 = 200 + 30 * getLevel();
        if (getTerrainUnderFeet() == 'L') {
            dmg1 = dmg1 * 1.15f;
        }
        float dmg2 = attackSlam();
        return dmg1 + dmg2;
    }

    public float attack(Rogue r){
        float dmg1 = attackExecute(r);
        dmg1 = Math.round(dmg1 * 1.15f);
        float dmg2 = attackSlam();
        dmg2 = Math.round(dmg2 * 0.8f);
        modifyOvtDmg(r);
        return dmg1 + dmg2;
    }

    public float attack(Knight k){
        float dmg1 = attackExecute(k);
        dmg1 = Math.round(dmg1);
        float dmg2 = attackSlam();
        dmg2 = Math.round(dmg2 * 1.2f);
        modifyOvtDmg(k);
        return dmg1 + dmg2;
    }

    public float attack(Pyromancer p){
        float dmg1 = attackExecute(p);
        dmg1 = Math.round(dmg1 * 1.1f);
        float dmg2 = attackSlam();
        dmg2 = Math.round(dmg2 * 0.9f);
        modifyOvtDmg(p);
        return dmg1 + dmg2;
    }

    public float attack(Wizard w){
        float dmg1 = attackExecute(w);
        dmg1 = Math.round(dmg1 * 0.8f);
//        System.out.println("Execute: " + dmg1 + getTerrainUnderFeet());
        float dmg2 = attackSlam();
        dmg2 = Math.round(dmg2 * 1.05f);
        modifyOvtDmg(w);
//        System.out.println("Slam: " + dmg2);
//        System.out.println("Total: " + (dmg1 + dmg2));
        return dmg1 + dmg2;
    }

    private void modifyOvtDmg(Hero h){
        h.setIncap(true);
        h.setIgnited(false, 0, 0);
        h.setParalised(false, 0, 0);
    }
}

