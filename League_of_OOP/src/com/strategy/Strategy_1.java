package strategy;

import heroes.Hero;

public class Strategy_1 implements HeroStrategy {
    private float hpModifier, coefModifier;

    public Strategy_1(float hpModifier, float coefModifier) {
        this.hpModifier = hpModifier;
        this.coefModifier = coefModifier;
    }

    @Override
    public void modifyHero(Hero h) {
        h.setHp(h.getHp() - (int)(h.getHp() * hpModifier)); // hp--
        h.setStrategyModifier(h.getStrategyModifier() + coefModifier); // coef++
    }
}
