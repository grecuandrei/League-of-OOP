package magician;

import angels.Angel;
import heroes.Hero;

import java.io.IOException;

public interface SubjectHero {
    void Attach(Observer o);
    void NotifyHero(Angel a) throws IOException;
    void NotifyDeadHero() throws IOException;
    void NotifyAliveHero() throws IOException;
    void NotifyDeadInCombat(Hero h) throws IOException;
    void NotifyLevelUp() throws IOException;
}
