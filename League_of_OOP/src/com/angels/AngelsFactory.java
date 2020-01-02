package angels;
// Implementation of the factory to create the angels
public class AngelsFactory {
    public final Angel getAngel(final String angelName) {
        switch (angelName) {
            case "DamageAngel":
                return new DamageAngel();
            case "DarkAngel":
                return new DarkAngel();
            case "Dracula":
                return new Dracula();
            case "GoodBoy":
                return new GoodBoy();
            case "LevelUpAngel":
                return new LevelUpAngel();
            case "LifeGiver":
                return new LifeGiver();
            case "SmallAngel":
                return new SmallAngel();
            case "Spawner":
                return new Spawner();
            case "TheDoomer":
                return new TheDoomer();
            default:
                return new XPAngel();
        }
    }
}
