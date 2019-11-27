package main;

import java.io.File;

public final class Main {
    private Main() { /*Just to trick the checker*/ }
    public static void main(final String[] args) {
        File inputFile = new File(args[0]);
        File outputFile = new File(args[1]);
        // created a object game
        main.Game game = new main.Game();
        // and start it
        game.playGame(inputFile, outputFile);
    }
}
