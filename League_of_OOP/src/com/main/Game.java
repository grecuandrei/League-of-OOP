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
    
    public void playGame(final File inputFile, final File outputFile) {
        try {
            Scanner sr = new Scanner(inputFile);
            // Input
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
            // make heroes
            int nrPlayers = sr.nextInt();
            sr.nextLine();
            for (int i = 0; i < nrPlayers; ++i) {
                char c = sr.next().charAt(0);
                int a = sr.nextInt();
                int b = sr.nextInt();
                sr.nextLine();
                heroes.add(hf.getHero(c));
                heroes.get(heroes.size() - 1).setX(a);
                heroes.get(heroes.size() - 1).setY(b);
                heroes.get(heroes.size() - 1).setTerrainUnderFeet(map.getTerrain(heroes.get(heroes.size() - 1).getX(), heroes.get(heroes.size() - 1).getY()));
            }
            
            // rounds
            int nrRounds = sr.nextInt();
            sr.nextLine();
            for (int i = 0; i < nrRounds; ++i) {
                System.out.println("-------------Runda " + i + "----------------");
                for (Hero h:heroes) {
                    System.out.println(h.getName() + " " + h.getHp() + " " + h.getX() + " " + h.getY());
                }
                
                String moves = sr.nextLine();
                
                System.out.println(moves);
                
                for (int j = 0; j < moves.length(); ++j) {
                    System.out.println(j + heroes.get(j).getName() + " " + heroes.get(j).isIncap() + " " + heroes.get(j).isParalised() + " " + heroes.get(j).getX() + " " + heroes.get(j).getY());
                    
                    if (!heroes.get(j).isIncap() && !heroes.get(j).isParalised()) {
                        moveHero(j, moves.charAt(j), map);
                    } else if (heroes.get(j).isIncap()) {
                        heroes.get(j).setIncap(false);
                    }
                    
                    System.out.println(heroes.get(j).getName() + " " + heroes.get(j).isIncap() + " " + heroes.get(j).isParalised());
                }
                
                System.out.println();
                System.out.println("Hp inainte de overtime");
                for (Hero h:heroes) {
                    System.out.println(h.getName() + " " + h.getHp() + " " + h.getX() + " " + h.getY());
                }
                
                for (Hero h:heroes) {
                    applyOvertimeDmg(h);
                }
                
                System.out.println("Hp dupa de overtime");
                for (Hero h:heroes) {
                    System.out.println(h.getName() + " " + h.getHp());
                }
                
                for (Hero h:heroes) {
                    if (!h.isAttacked() && !h.isDead()) {
                        for (Hero h1 : heroes) {
                            if (!h.equals(h1) && h.getX() == h1.getX()
                                    && h.getY() == h1.getY() && !h1.isDead()) {
                                h.setAttacked(true);
                                h1.setAttacked(true);

                                System.out.println("Hp inainte fight " + h.getHp() + " " + h1.getHp());
                                
                                h.setDmgInflicted(Math.round(h1.calculateFlatDmg())); // W. (R)
                                h1.setDmgInflicted(Math.round(h.calculateFlatDmg())); // R. (W)
                                
                                float dmgPlayer1 = Math.round(h.acceptAttack(h1)); // W.accept(R) -> R.attack(W)
                                float dmgPlayer2 = Math.round(h1.acceptAttack(h)); // R.accept(W) -> W.attack(R)

                                System.out.println(dmgPlayer1 + " " + dmgPlayer2);
                                
                                h.dealDMG(h1, dmgPlayer2);
                                h1.dealDMG(h, dmgPlayer1);
                                
                                h.levelUP();
                                h1.levelUP();
                                
                                if (h.isDead()) {
                                    System.out.println("Winner is " + h1.getName() + h1.getXp() + h1.getLevel());
                                } else if (h1.isDead()){
                                    System.out.println("Winner is " + h.getName() + h.getXp() + h.getLevel());
                                }
                                System.out.println("Hp dupa fight " + h.getHp() + " " + h.isIncap() + " " + h1.getHp() + " " + h1.isIncap());
                                System.out.println();
                            }
                        }
                    }
                }
                for (Hero h:heroes) {
                    h.setAttacked(false);
                    
                    System.out.println(h.getName() + " " + h.getHp()
                            + " " + h.getX() + " " + h.getY());
                }
            }
            sr.close();
            writeToFile(outputFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    private void moveHero(final int i, final char c, final Map map) {
        switch (c) {
            case 'U':
                heroes.get(i).setX(heroes.get(i).getX() - 1);
                heroes.get(i).setY(heroes.get(i).getY());
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
    private void applyOvertimeDmg(final Hero h) {
        System.out.println("Damage overtime " + h.getDmgOvertime());
        
        if (h.getRoundsApply() > 0) {
            h.setHp(h.getHp() - h.getDmgOvertime());
            h.setRoundsApply(h.getRoundsApply() - 1);
        }
        if (h.getHp() <= 0) {
            h.setDead(true);
        }
    }
    private void writeToFile(final File outputFile)
    throws IOException {
        BufferedWriter writer = new BufferedWriter(new FileWriter(outputFile));
        for (Hero h:heroes) {
            writer.append(h.getName());
            if (h.isDead()) {
                writer.append(" dead");
            } else {
                writer.write(" " + h.getLevel() + " " + h.getXp()
                        + " " + Math.round(h.getHp()) + " " + h.getX() + " " + h.getY());
            }
            writer.newLine();
        }
        writer.close();
    }
}
