package strategy;

import heroes.Hero;
// no modification to the current/basic strategy - nothing changes
public class DefaultStrategy implements HeroStrategy {
    @Override
    public final void modifyHero(final Hero h) {
        h.setHp(h.getHp()); // hp==
        h.setStrategyModifier(h.getStrategyModifier()); // coef==
    }
}
