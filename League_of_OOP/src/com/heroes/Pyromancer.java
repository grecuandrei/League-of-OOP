package heroes;

public class Pyromancer extends Hero {
    private float overtimeDMG;

    public Pyromancer(int x, int y) {
        super(x, y);
        setHp(500);
        setBaseHP(500);
        setMultiplier(50);
    }

    @Override
    public String getName() {
        return "P";
    }

    public float acceptAttack(Hero h) {
        return h.attack(this);
    }
    
    private float attackFireblast() {
        float dmg = 350 + 50 * getLevel();
        if (getTerrainUnderFeet() == 'V') {
            dmg = dmg * 1.25f;
        }
        return Math.round(dmg);
    }
    
    private float attackIgnite() {
        float dmg = 150 + 20 * getLevel();
        if (getTerrainUnderFeet() == 'V') {
            dmg = dmg * 1.25f;
        }
        return Math.round(dmg);
    }

    public float calculateFlatDmg() {
        float dmg1 = attackFireblast();
        float dmg2 = attackIgnite();
        return dmg1 + dmg2;
    }
    
    public float attack(Rogue r){
        float dmg1 = attackFireblast();
        dmg1 = Math.round(dmg1 * 0.8f);
        float dmg2 = attackIgnite();
        dmg2 = Math.round(dmg2 * 0.8f);
        modifyOvtDmg(r, 0.8f);
        return dmg1 + dmg2;
    }

    public float attack(Knight k){
        float dmg1 = attackFireblast();
        dmg1 = Math.round(dmg1 * 1.2f);
        float dmg2 = attackIgnite();
        dmg2 = Math.round(dmg2 * 1.2f);
        modifyOvtDmg(k, 1.2f);
        return dmg1 + dmg2;
    }

    public float attack(Pyromancer p){
        float dmg1 = attackFireblast();
        dmg1 = Math.round(dmg1 * 0.9f);
        float dmg2 = attackIgnite();
        dmg2 = Math.round(dmg2 * 0.9f);
        modifyOvtDmg(p, 0.9f);
        return dmg1 + dmg2;
    }

    public float attack(Wizard w){
        float dmg1 = attackFireblast();
        dmg1 = Math.round(dmg1 * 1.05f);
        float dmg2 = attackIgnite();
        dmg2 = Math.round(dmg2 * 1.05f);
        modifyOvtDmg(w, 1.05f);
        return dmg1 + dmg2;
    }

    private void modifyOvtDmg(Hero h, float percent){
        float igniteDmg = 50 + 30 * getLevel();
        igniteDmg = igniteDmg * percent;
        if (getTerrainUnderFeet() == 'V') {
            igniteDmg = igniteDmg * 1.25f;
        }
//        System.out.println(igniteDmg + h.getName());
        h.setIncap(false);
        h.setParalised(false, 0, 0);
        h.setIgnited(true, igniteDmg, 2);
//        System.out.println(h.getDmgOvertime());
    }
}
