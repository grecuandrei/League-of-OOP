package heroes;

public class HeroFactory {
    public Hero getHero(final char heroType){
        switch(heroType) {
            case 'P':
                return new Pyromancer();
            case 'K':
                return new Knight();
            case 'R':
                return new Rogue();
            default:
                return new Wizard();
        }
    }
}
