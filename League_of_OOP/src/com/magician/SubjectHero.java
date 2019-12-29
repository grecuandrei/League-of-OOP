package magician;

import angels.Angel;
import heroes.Hero;

import java.io.IOException;

public interface SubjectHero {
    void attach(Observer o);
    void notifyHero(Angel a) throws IOException;
    void notifyDeadHero() throws IOException;
    void notifyAliveHero() throws IOException;
    void notifyDeadInCombat(Hero h) throws IOException;
    void notifyLevelUp() throws IOException;
}
