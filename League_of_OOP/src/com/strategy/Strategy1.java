package strategy;

import heroes.Hero;

public class Strategy1 implements HeroStrategy {
    private float hpModifier, coefModifier;

    public Strategy1(final float hpModifier, final float coefModifier) {
        this.hpModifier = hpModifier;
        this.coefModifier = coefModifier;
    }

    @Override
    public final void modifyHero(final Hero h) {
        h.setHp(h.getHp() - (int) (h.getHp() * hpModifier)); // hp--
        h.setStrategyModifier(h.getStrategyModifier() + coefModifier); // coef++
    }
}
