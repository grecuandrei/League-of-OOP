package heroes;

public class Wizard extends Hero {

    public Wizard(int x, int y) {
        super(x, y);
        setHp(400);
        setBaseHP(400);
        setMultiplier(30);
    }

    @Override
    public String getName() {
        return "W";
    }

    public float acceptAttack(Hero h) {
        return h.attack(this);
    }
    
    public float attackDrain() {
        return 0.2f + 0.05f * getLevel();
    }

    public float attackDeflect(Hero h) {
        double percent = 0.35 + 0.02 * getLevel();
        if (percent > 0.7) {
            percent = 0.7;
        }
        System.out.println(getDmgInflicted());
        float dmgFin = (float) (getDmgInflicted() * percent);
        if (getTerrainUnderFeet() == 'D') {
            dmgFin = dmgFin * 1.1f;
        }
        return dmgFin;
//        return Math.round(dmgFin);
    }
    
    public float attack(Rogue r){
        double percent = attackDrain();
        percent = 0.8 * percent;
//        System.out.println("Drain min: " + Math.min(0.3 * r.getBaseHP(), r.getHp()) + " " +r.getBaseHP() + " " + r.getHp());
        float dmg1 = (float) (percent * Math.min(0.3 * r.getBaseHP(), r.getHp()));
        if (getTerrainUnderFeet() == 'D') {
            dmg1 = dmg1 * 1.1f;
        }
        dmg1 = Math.round(dmg1);
//        System.out.println("Drain: " + Math.round(dmg1));
        float dmg2 = attackDeflect(r);
        dmg2 = Math.round(dmg2 * 1.2f);
//        System.out.println("Deflect " + Math.round(dmg2));
//        System.out.println("Total dmg " + (dmg1 + dmg2));
        return dmg1 + dmg2;
    }

    public float attack(Knight k){
        float percent = attackDrain();
        percent = 1.2f * percent;
        float dmg1 = percent * Math.min(0.3f * k.getBaseHP(), k.getHp());
        if (getTerrainUnderFeet() == 'D') {
            dmg1 = dmg1 * 1.1f;
        }
        dmg1 = Math.round(dmg1);
//        System.out.println("Drain: " + dmg1);
        float dmg2 = attackDeflect(k);
        dmg2 = Math.round(dmg2 * 1.4f);
//        System.out.println("Deflect " + Math.round(dmg2));
//        System.out.println("Total dmg " + (dmg1 + dmg2));
        return dmg1 + dmg2;
    }

    public float attack(Pyromancer p){
        float percent = attackDrain();
        percent = 0.9f * percent;
        float dmg1 = (float) (percent * Math.min(0.3 * p.getBaseHP(), p.getHp()));
        if (getTerrainUnderFeet() == 'D') {
            dmg1 = dmg1 * 1.1f;
        }
        dmg1 = Math.round(dmg1);
        float dmg2 = attackDeflect(p);
        dmg2 = Math.round(dmg2 * 1.3f);
        return dmg1 + dmg2;
    }

    public float attack(Wizard w){
        float percent = attackDrain();
        percent = 1.05f * percent;
//        System.out.println(w.getBaseHP() + " " + percent * (0.3 * w.getBaseHP()) + " " + w.getHp());
        float dmg = (float) (percent * Math.min(0.3 * w.getBaseHP(), w.getHp()));
        if (getTerrainUnderFeet() == 'D') {
            dmg = dmg * 1.1f;
        }
        return Math.round(dmg);
    }
}
