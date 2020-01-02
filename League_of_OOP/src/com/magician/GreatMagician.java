package magician;

import angels.Angel;
import heroes.Hero;
import java.io.BufferedWriter;
import java.io.IOException;
// Implementation of pattern Singleton for the Magician
public final class GreatMagician implements Observer {
    private BufferedWriter writer;
    private static GreatMagician instance = null;

    private GreatMagician(final BufferedWriter writer) {
        this.writer = writer;
    }
    public static GreatMagician getInstance(final BufferedWriter writer) {
        if (instance == null) {
            instance = new GreatMagician(writer);
        }
        return instance;
    }
    /**
     * This methods are called whenever the observed object is changed. An
     * application calls an {@code Observable} object's
     * {@code notifyObservers} method to have all the object's
     * observers notified of the change.
     *
     * @param arg an argument passed to the {@code notifyObservers}
     */
    @Override
    public void update(final Object arg) throws IOException {
        writer.write("Angel " + ((Angel) arg).getName() + " was spawned at "
                + ((Angel) arg).getX() + " " + ((Angel) arg).getY());
        writer.newLine();
    }

    @Override
    public void updateHero(final Object o, final Angel a) throws IOException {
        String name;
        switch (((Hero) o).getName()) {
            case "K":
                name = "Knight";
                break;
            case "P":
                name = "Pyromancer";
                break;
            case "R":
                name = "Rogue";
                break;
            default:
                name = "Wizard";
        }
        if (a.isGood()) {
            writer.write(a.getName() + " helped " + name + " " + ((Hero) o).getOrdLine());
        } else {
            writer.write(a.getName() + " hit " + name + " " + ((Hero) o).getOrdLine());
        }
        writer.newLine();
    }

    @Override
    public void updateDead(final Object o) throws IOException {
        String name;
        switch (((Hero) o).getName()) {
            case "K":
                name = "Knight";
                break;
            case "P":
                name = "Pyromancer";
                break;
            case "R":
                name = "Rogue";
                break;
            default:
                name = "Wizard";
        }
        writer.write("Player " + name + " " + ((Hero) o).getOrdLine()
                + " was killed by an angel");
        writer.newLine();
    }

    @Override
    public void updateAlive(final Object o) throws IOException {
        String name;
        switch (((Hero) o).getName()) {
            case "K":
                name = "Knight";
                break;
            case "P":
                name = "Pyromancer";
                break;
            case "R":
                name = "Rogue";
                break;
            default:
                name = "Wizard";
        }
        writer.write("Player " + name + " " + ((Hero) o).getOrdLine()
                + " was brought to life by an angel");
        writer.newLine();
    }

    @Override
    public void updateFight(final Object o, final Hero h) throws IOException {
        String name1, name2;
        switch (((Hero) o).getName()) {
            case "K":
                name1 = "Knight";
                break;
            case "P":
                name1 = "Pyromancer";
                break;
            case "R":
                name1 = "Rogue";
                break;
            default:
                name1 = "Wizard";
        }
        switch (h.getName()) {
            case "K":
                name2 = "Knight";
                break;
            case "P":
                name2 = "Pyromancer";
                break;
            case "R":
                name2 = "Rogue";
                break;
            default:
                name2 = "Wizard";
        }
        writer.write("Player " + name1 + " " + ((Hero) o).getOrdLine()
                + " was killed by " + name2 + " " + h.getOrdLine());
        writer.newLine();
    }

    @Override
    public void updateLevelUp(final Object o) throws IOException {
        String name;
        switch (((Hero) o).getName()) {
            case "K":
                name = "Knight";
                break;
            case "P":
                name = "Pyromancer";
                break;
            case "R":
                name = "Rogue";
                break;
            default:
                name = "Wizard";
        }
        writer.write(name + " " + ((Hero) o).getOrdLine()
                + " reached level " + ((Hero) o).getLevel());
        writer.newLine();
    }
}
