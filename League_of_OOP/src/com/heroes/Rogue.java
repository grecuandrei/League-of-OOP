package heroes;

public class Rogue extends Hero {
    private int backstabNr;
    
    public Rogue(int x, int y) {
        super(x, y);
        setHp(600);
        setBaseHP(600);
        setMultiplier(40);
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
        float dmg = 200f + 20 * getLevel();
        backstabNr++;
        if (backstabNr % 3 == 0 && getTerrainUnderFeet() == 'W') {
            dmg = dmg * 1.5f * 1.15f;
//        } else if (backstabNr == 3 && getTerrainUnderFeet() != 'W') {
//            backstabNr = 0;
        } else if (getTerrainUnderFeet() == 'W' && backstabNr % 3 != 0){
            dmg = dmg * 1.15f;
        }
        return Math.round(dmg);
    }

    public float attackParalysis() {
        float dmg = 40 + 10 * getLevel();
        if (getTerrainUnderFeet() == 'W') {
            dmg = dmg * 1.15f;
        }
        return Math.round(dmg);
    }

    public float calculateFlatDmg() {
//        float dmg1 = 200f + 20 * getLevel();
//        if (getTerrainUnderFeet() == 'W') {
//            dmg1 = dmg1 * 1.15f;
//        }
//        float dmg2 = 40f + 10 * getLevel();
//        if (getTerrainUnderFeet() == 'W') {
//            dmg2 = dmg2 * 1.15f;
//        }
        float dmg1 = attackBackstab();
        backstabNr--;
        float dmg2 = attackParalysis();
//        System.out.println("Flat dmg " + (dmg1 + dmg2));
        return Math.round(dmg1 + dmg2);
    }
    
    public float attack(Rogue r){
        float dmg1 = attackBackstab();
        dmg1 = Math.round(dmg1 * 1.2f);
        float dmg2 = attackParalysis();
        modifyOvtDmg(r);
        dmg2 = Math.round(dmg2 * 0.9f);
        r.setDmgOvertime(dmg2);
        return dmg1 + dmg2;
    }

    public float attack(Knight k){
        float dmg1 = attackBackstab();
        dmg1 = Math.round(dmg1 * 0.9f);
        float dmg2 = attackParalysis();
        dmg2 = Math.round(dmg2 * 0.8f);
        modifyOvtDmg(k);
        k.setDmgOvertime(dmg2);
        return dmg1 + dmg2;
    }

    public float attack(Pyromancer p){
        float dmg1 = attackBackstab();
        dmg1 = Math.round(dmg1 * 1.25f);
//        System.out.println("Backstab: " + dmg1);
        float dmg2 = attackParalysis();
        dmg2 = Math.round(dmg2 * 1.2f);
//        System.out.println("PARA: " + dmg2);
        modifyOvtDmg(p);
        p.setDmgOvertime(dmg2);
        return dmg1 + dmg2;
    }

    public float attack(Wizard w){
        float dmg1 = attackBackstab();
//        System.out.println("Backstab: " + dmg1);
        dmg1 = Math.round(dmg1 * 1.25f);
//        System.out.println("Backstab: " + dmg1);
        float dmg2 = attackParalysis();
        dmg2 = Math.round(dmg2 * 1.25f);
        modifyOvtDmg(w);
        w.setDmgOvertime(dmg2);
//        System.out.println("Paralysis: " + dmg2);
//        System.out.println("Total dmg " + (dmg1 + dmg2));
        return dmg1 + dmg2;
    }
    
    private void modifyOvtDmg(Hero h){
        float dmg = 40 + 10 * getLevel();
        h.setIncap(false);
        h.setIgnited(false, 0, 0);
        h.setParalised(true, dmg, getTerrainUnderFeet() == 'W' ? 6 : 3);
    }
}
