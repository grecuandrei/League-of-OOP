package magician;

import java.io.IOException;

public interface SubjectAngel {
    void Attach(Observer o);
    void Notify() throws IOException;
}
