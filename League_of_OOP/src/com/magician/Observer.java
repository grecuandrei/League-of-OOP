package magician;

import angels.Angel;
import heroes.Hero;
import java.io.IOException;

public interface Observer {
    void update(Object o) throws IOException;
    void updateHero(Object o, Angel a) throws IOException;
    void updateDead(Object o) throws IOException;
    void updateAlive(Object o) throws IOException;
    void updateFight(Object o, Hero h) throws IOException;
    void updateLevelUp(Object o) throws IOException;
}
