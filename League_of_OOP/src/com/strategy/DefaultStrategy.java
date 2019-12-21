package strategy;

import heroes.Hero;
// no modification to the current/basic strategy
public class DefaultStrategy implements HeroStrategy{
//    private float hpModifier, coefModifier;

    public DefaultStrategy(float hpModifier, float coefModifier) {
//        this.hpModifier = hpModifier;
//        this.coefModifier = coefModifier;
    }

    @Override
    public void modifyHero(Hero h) {
        h.setHp(h.getHp()); // hp==
        h.setStrategyModifier(h.getStrategyModifier()); // coef==
    }
}
