package magician;

import java.io.IOException;

public interface SubjectAngel {
    void attach(Observer o);
    void notifyAngel() throws IOException;
}
