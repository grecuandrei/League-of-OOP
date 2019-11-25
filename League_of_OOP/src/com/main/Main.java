package main;

import java.io.File;

public final class Main {
    private Main() { /*Just to trick the check style*/}
    public static void main(final String[] args) {
        File inputFile = new File(args[0]);
        File outputFile = new File(args[1]);
        main.Game game = new main.Game();
        game.playGame(inputFile, outputFile);
    }
}
