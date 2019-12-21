package main;

import angels.Angel;
import angels.AngelsFactory;
import heroes.Hero;
import heroes.HeroFactory;
import heroes.Knight;
import magician.GreatMagician;
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
    private AngelsFactory af = new AngelsFactory();
    private ArrayList<String> movess = new ArrayList<>();
    private ArrayList<Angel> angels = new ArrayList<>();
    private GreatMagician magician = new GreatMagician();
    public Game() { }
    public final void playGame(final File inputFile, final File outputFile) {
        try {
            Scanner sr = new Scanner(inputFile);
            // Input
            BufferedWriter writer = new BufferedWriter(new FileWriter(outputFile));
            magician.setWriter(writer);
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
                heroes.get(heroes.size() - 1).Attach(magician);
                heroes.get(heroes.size() - 1).setOrdLine(i);
            }
            // Do the rounds - play the game
            int nrRounds = sr.nextInt();
            sr.nextLine();

            for (int i = 0; i < nrRounds; ++i) {
                movess.add(sr.nextLine());
            }
            
            for (int i = 0; i < nrRounds; ++i) {
                int angelsNr;
                String angelsData;
                String[] angelsParts;
                angelsNr = sr.nextInt();
                if (angelsNr != 0) {
                    angelsData = sr.nextLine();
                    angelsParts = angelsData.split("\\s|,");
                    for (int j = 1; j <= angelsNr*3; j+=3) {
                        Angel angel = af.getAngel(angelsParts[j].replaceAll("\\s", ""));
                        angel.setName(angelsParts[j].replaceAll("\\s", ""));
                        angel.setRound(i);
                        angel.setX(Integer.parseInt(angelsParts[j+1]));
                        angel.setY(Integer.parseInt(angelsParts[j+2]));
                        angel.Attach(magician);
                        angels.add(angel);
                    }
                    
                }
            }
            
            // the start of the rounds
            for (int i = 0; i < nrRounds; ++i) {
                
                writeStartOfRound(writer, i+1);
                
                for (Hero h:heroes) {
                    System.out.println(h.getName() + " " + h.getHp() + " " +h.getX()+" "+h.getY());
                }
                System.out.println();

                // hero and the strategies
                for (Hero h:heroes) {
                    h.chooseStrategy();
                    h.doStrategy();
                }
                
                String moves = movess.get(i);
                System.out.println(moves);
                
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
                for (Hero h1:heroes) {
                    // if it is not dead or had a fight this round
                    if (!h1.isAttacked() && !h1.isDead()) {
                        // check if another hero is on the same terrain and play the fight
                        for (Hero h2 : heroes) {
                            if (!h1.equals(h2) && h1.getX() == h2.getX()
                                    && h1.getY() == h2.getY() && !h2.isDead()) {
                                // set that there are fighting
                                h1.setAttacked(true);
                                h2.setAttacked(true);
                                // calculate the damage if one of them is a wizard
                                h1.setDmgInflicted(Math.round(h2.calculateFlatDmg()));
                                h2.setDmgInflicted(Math.round(h1.calculateFlatDmg()));
                                // calculate the damage that will be dispersed on the other
                                // h1.accept(h2) -> h2.attack(h1)
                                float dmgPlayer1 = Math.round(h1.acceptAttack(h2));
                                // h2.accept(h1) -> h1.attack(h2)
                                float dmgPlayer2 = Math.round(h2.acceptAttack(h1));
                                
//                                if (h1.getX() == 3 && h1.getY() == 13) {
//                                    System.out.println(h1.getName() + " "+h1.getHp()+" "+dmgPlayer2+" "+h1.getDmgOvertime() + " "+h1.getLevel());
//                                    System.out.println(h2.getName() + " "+h2.getHp()+" "+dmgPlayer1+" "+h2.getDmgOvertime()+ " "+h2.getLevel());
//                                    System.out.println();
//                                }
                                
                                // deal the damage
                                h1.dealDMG(h2, dmgPlayer2);
                                h2.dealDMG(h1, dmgPlayer1);
                                // see if someone died
                                if (h2.isDead()) {
                                    h2.NotifyDeadInCombat(h1);
                                }
                                if (h1.isDead()) {
                                    h1.NotifyDeadInCombat(h2);
                                }
                                // give xp and if needed update the health
                                int lvlBefore1 = h1.getLevel();
                                int lvlBefore2 = h2.getLevel();
                                h1.levelUP();
                                h2.levelUP();
//                                if (lvlBefore1 != h1.getLevel()) {
//                                    h1.NotifyLevelUp();
//                                }
//                                if (lvlBefore2 != h2.getLevel()) {
//                                    h2.NotifyLevelUp();
//                                }
                            }
                        }
                    }
                }
                // uncheck the field that says they were fighting for this rounds\
                for (Hero h:heroes) {
                    h.setAttacked(false);
                    System.out.println(h.getName() + " " + h.getLevel() + " " + h.getXp() + " " + h.getHp());
                }
                // introduce the angels
                for (Angel a:angels) {
                    if (a.getRound() == i) {
                        a.Notify(); // notify is only for angels
                        for (Hero h : heroes) {
                            if (a.getX() == h.getX() && a.getY() == h.getY()) {
                                if (!h.isDead() && !a.getName().equalsIgnoreCase("Spawner")) {
                                    System.out.println(a.getName() + " " + h.getName());
                                    int lvlBefore = h.getLevel();
                                    h.acceptAngel(a);
                                    h.NotifyHero(a); // hero was hit/helped by a certain angel
                                    if (h.isDead()) {
                                        h.NotifyDeadHero(); // hero was killed by a angel
                                    }
                                    
//                                    if (h instanceof Knight)
//                                        System.out.println(h.getLevel() + " " + h.getXp() + " " + h.getHp());
                                    if (a.getName().equalsIgnoreCase("LevelUpAngel")) {
                                        h.levelUP();
                                    }
//                                    if (h instanceof Knight)
//                                        System.out.println(h.getLevel() + " " + h.getXp() + " " + h.getHp());
//                                    System.out.println();
                                    
                                    if (a.getName().equalsIgnoreCase("XPAngel")) {
                                        h.levelUP();
                                    }
                                } else if (h.isDead() && a.getName().equalsIgnoreCase("Spawner")) {
                                    h.acceptAngel(a);
                                    h.NotifyHero(a); // hero was helped by a certain angel (in this case Spawner)
                                    h.NotifyAliveHero(); // hero was resurrected by the Spawner
                                }
                            }
                        }
                    }
                }
            }
            sr.close();
            writeToFile(writer);
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
    private void writeToFile(final BufferedWriter writer)
    throws IOException {
//        BufferedWriter writer = new BufferedWriter(new FileWriter(outputFile));
        writer.newLine();
        writer.write("~~ Results ~~");
        writer.newLine();
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

    private void writeStartOfRound(final BufferedWriter writer, final int round)
            throws IOException {
        if (round > 1) {
            writer.newLine();
        }
        writer.write("~~ Round ");
        writer.append(Integer.toString(round));
        writer.append(" ~~");
        writer.newLine();
    }
}
