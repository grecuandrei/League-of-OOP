package angels;

import heroes.Knight;
import heroes.Pyromancer;
import heroes.Rogue;
import heroes.Wizard;

import java.io.IOException;

public class XPAngel extends Angel {
    public XPAngel() {
        isGood = true;
    }

    public void apply(Knight k) throws IOException {
        k.setXp(k.getXp() + 45);
//        k.levelUP();
    }

    public void apply(Pyromancer p) throws IOException {
        p.setXp(p.getXp() + 50);
//        p.levelUP();
    }

    public void apply(Rogue r) throws IOException {
        r.setXp(r.getXp() + 40);
//        r.levelUP();
    }

    public void apply(Wizard w) throws IOException {
        w.setXp(w.getXp() + 60);
//        w.levelUP();
    }
}
