package main;

import java.io.File;

public class Main {

    public static void main(String[] args) {
        File inputFile = new File(args[0]);
        File outputFile = new File(args[1]);
        
        main.Game game = new main.Game();
        game.playGame(inputFile, outputFile);
    }
}
