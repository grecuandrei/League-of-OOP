package main;

import heroes.Hero;
import heroes.HeroFactory;
import map.Map;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Game {
    private ArrayList<Hero> heroes = new ArrayList<>();
    private HeroFactory hf = new HeroFactory();
    public Game() { }
    public final void playGame(final File inputFile, final File outputFile) {
        try {
            Scanner sr = new Scanner(inputFile);
            // Input
            // Map instantiation
            int n = sr.nextInt();
            int m = sr.nextInt();
            Map map = Map.getInstance(n, m);
            sr.nextLine();
            for (int i = 0; i < m; ++i) {
                String s = sr.nextLine();
                for (int j = 0; j < n; ++j) {
                    map.addTerrain(s.charAt(j));
                }
            }
            // Build heroes
            int nrPlayers = sr.nextInt();
            sr.nextLine();
            for (int i = 0; i < nrPlayers; ++i) {
                char c = sr.next().charAt(0);
                int a = sr.nextInt();
                int b = sr.nextInt();
                sr.nextLine();
                // add the hero to the pool
                heroes.add(hf.getHero(c));
                // set the coordinates
                heroes.get(heroes.size() - 1).setX(a);
                heroes.get(heroes.size() - 1).setY(b);
                // and get the terrain that he is on
                heroes.get(heroes.size() - 1).setTerrainUnderFeet(
                        map.getTerrain(heroes.get(heroes.size() - 1).getX(),
                                heroes.get(heroes.size() - 1).getY()));
            }
            // Do the rounds - play the game
            int nrRounds = sr.nextInt();
            sr.nextLine();
            for (int i = 0; i < nrRounds; ++i) {
                String moves = sr.nextLine();
                int j = 0;
                for (Hero h:heroes) {
                    // if it is not dead, incapacitated or paralized, move the player
                    if (!h.isDead() && !h.isIncap() && !h.isParalyzed()) {
                        if (h.isIgnited()) {
                            h.setRoundsApply(h.getRoundsApply() - 1);
                        }
                        moveHero(j, moves.charAt(j), map);
                    } else if (!h.isDead()) {
                        // if it was incapacitated, "liberate" him
                        if (h.isIncap()) {
                            heroes.get(j).setIncap(false);
                        }
                        h.setRoundsApply(h.getRoundsApply() - 1);
                    }
                    j++;
                }
                // apply for every player the overtime damage
                for (Hero h:heroes) {
                    if (!h.isDead() && h.getRoundsApply() >= 0) {
                        applyOvertimeDmg(h);
                    }
                }
                // for every player
                for (Hero h:heroes) {
                    // if it is not dead or had a fight this round
                    if (!h.isAttacked() && !h.isDead()) {
                        // check if another hero is on the same terrain and play the fight
                        for (Hero h1 : heroes) {
                            if (!h.equals(h1) && h.getX() == h1.getX()
                                    && h.getY() == h1.getY() && !h1.isDead()) {
                                // set that there are fighting
                                h.setAttacked(true);
                                h1.setAttacked(true);
                                // calculate the damage if one of them is a wizard
                                h.setDmgInflicted(Math.round(h1.calculateFlatDmg()));
                                h1.setDmgInflicted(Math.round(h.calculateFlatDmg()));
                                // calculate the damage that will be dispersed on the other
                                // h.accept(h1) -> h1.attack(h)
                                float dmgPlayer1 = Math.round(h.acceptAttack(h1));
                                // h1.accept(h) -> h.attack(h1)
                                float dmgPlayer2 = Math.round(h1.acceptAttack(h));
                                // deal the damage
                                h.dealDMG(h1, dmgPlayer2);
                                h1.dealDMG(h, dmgPlayer1);
                                // give xp and if needed update the health
                                h.levelUP();
                                h1.levelUP();
                            }
                        }
                    }
                }
                // uncheck the field that says they were fighting for this rounds\
                for (Hero h:heroes) {
                    h.setAttacked(false);
                }
            }
            sr.close();
            writeToFile(outputFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    // function that moves the player, based on the input
    private void moveHero(final int i, final char c, final Map map) {
        switch (c) {
            case 'U':
                // update the x
                heroes.get(i).setX(heroes.get(i).getX() - 1);
                // update the y
                heroes.get(i).setY(heroes.get(i).getY());
                // update the terrain that he is standing on
                heroes.get(i).setTerrainUnderFeet(map.getTerrain(heroes.get(i).getX(),
                        heroes.get(i).getY()));
                break;
            case 'D':
                heroes.get(i).setX(heroes.get(i).getX() + 1);
                heroes.get(i).setY(heroes.get(i).getY());
                heroes.get(i).setTerrainUnderFeet(map.getTerrain(heroes.get(i).getX(),
                        heroes.get(i).getY()));
                break;
            case 'L':
                heroes.get(i).setX(heroes.get(i).getX());
                heroes.get(i).setY(heroes.get(i).getY() - 1);
                heroes.get(i).setTerrainUnderFeet(map.getTerrain(heroes.get(i).getX(),
                        heroes.get(i).getY()));
                break;
            case 'R':
                heroes.get(i).setX(heroes.get(i).getX());
                heroes.get(i).setY(heroes.get(i).getY() + 1);
                heroes.get(i).setTerrainUnderFeet(map.getTerrain(heroes.get(i).getX(),
                        heroes.get(i).getY()));
                break;
            default:
                break;
        }
    }
    // function that applies the overtime damage for every player
    private void applyOvertimeDmg(final Hero h) {
        h.setHp(h.getHp() - (int) h.getDmgOvertime());
        // if the player is paralyzed and the rounds of damage are over
        if (h.isParalyzed() && h.getRoundsApply() == 0) {
            h.setParalyzed(false, 0, 0);
        }
        // if the damage was too much, kill the player
        if (h.getHp() <= 0) {
            h.setDead(true);
        }
    }
    // function that prints in the file the output
    private void writeToFile(final File outputFile)
    throws IOException {
        BufferedWriter writer = new BufferedWriter(new FileWriter(outputFile));
        for (Hero h:heroes) {
            writer.append(h.getName());
            if (h.isDead()) {
                writer.append(" dead");
            } else {
                writer.write(" " + h.getLevel() + " " + h.getXp()
                        + " " + h.getHp() + " " + h.getX() + " " + h.getY());
            }
            writer.newLine();
        }
        writer.close();
    }
}
