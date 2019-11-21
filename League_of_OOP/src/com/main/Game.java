package main;

import heroes.*;
import map.Map;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class Game {
    private ArrayList<Hero> heroes = new ArrayList<>();
    
    public Game() {
    }
    
    public void playGame(File inputFile, File outputFile) {
        try {
            Scanner sr = new Scanner(inputFile);
            // Input
            int n = sr.nextInt();
            int m = sr.nextInt();
            Map map = Map.getInstance(n, m);
            sr.nextLine();
            
            for (int i = 0; i < m; ++i) {
                String s = sr.nextLine();
                for (int j = 0; j < n ; j++) {
                    map.addTerrain(s.charAt(j));
                }
            }
            
            map.printMap();
            // make heroes
            int nrPlayers = sr.nextInt();
            sr.nextLine();
            for (int i = 0; i < nrPlayers; ++i) {
                String s = sr.nextLine();
                makeHero(s, map);
            }
            // rounds
            int nrRounds = sr.nextInt();
            sr.nextLine();
            for (int i = 0; i < nrRounds; i++) {
//                System.out.println("Runda " + i);
                String moves = sr.nextLine();
                for (int j = 0; j < moves.length(); ++j) {
                    if (!heroes.get(j).isIncap()) {
                        moveHero(j, moves.charAt(j));
                    }
                }
                for (Hero h:heroes) {
                    applyOvertimeDmg(h);
                }
                // todo to add levelup() for every player
                for (Hero h:heroes) {
                    if (!h.isAttacked() && !h.isDead()) {
                        for (Hero h1 : heroes) {
                            if (!h.equals(h1) && h.getX() == h1.getX() && h.getY() == h1.getY() && !h1.isDead()) {
                                h.setAttacked(true);
                                h1.setAttacked(true);

//                                System.out.println(h.getHp() + " " + h1.getHp());
                                
                                h.setDmgInflicted(h1.calculateFlatDmg()); // W. (R)
                                h1.setDmgInflicted(h.calculateFlatDmg()); // R. (W)
                                
                                float dmgPlayer1 = h.acceptAttack(h1); // W.accept(R) -> R.attack(W)
                                float dmgPlayer2 = h1.acceptAttack(h); // R.accept(W) -> W.attack(R)

//                                System.out.println(h.getHp() + " " + h1.getHp());
                                
                                h.dealDMG(h1, dmgPlayer2);
                                h1.dealDMG(h, dmgPlayer1);

//                                System.out.println(h.getHp() + " " + h1.getHp());
//                                System.out.println();
                                
                            }
                        }
                    }
                }
                
                for (Hero h:heroes) {
                    h.setAttacked(false);
                }
                
            }
            sr.close();
            
            WriteToFile(outputFile);
            
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void makeHero(String s, Map map) {
        int x = s.charAt(2) - '0';
        int y = s.charAt(4) - '0';
        switch(s.charAt(0)) {
            case 'P':
                Pyromancer p = new Pyromancer(x, y);
                p.setTerrainUnderFeet(map.getTerrain(x, y));
                heroes.add(p);
                break;
            case 'K':
                Knight k = new Knight(x, y);
                k.setTerrainUnderFeet(map.getTerrain(x, y));
                heroes.add(k);
                break;
            case 'W':
                Wizard w = new Wizard(x, y);
                w.setTerrainUnderFeet(map.getTerrain(x, y));
                heroes.add(w);
                break;
            default:
                Rogue r = new Rogue(x, y);
                r.setTerrainUnderFeet(map.getTerrain(x, y));
                heroes.add(r);
                break;
        }
    }
    
    private void moveHero(int i, char c) {
        switch (c) {
            case 'U':
                heroes.get(i).setX(heroes.get(i).getX() - 1);
                heroes.get(i).setY(heroes.get(i).getY());
                break;
            case 'D':
                heroes.get(i).setX(heroes.get(i).getX() + 1);
                heroes.get(i).setY(heroes.get(i).getY());
                break;
            case 'L':
                heroes.get(i).setX(heroes.get(i).getX());
                heroes.get(i).setY(heroes.get(i).getY() - 1);
                break;
            case 'R':
                heroes.get(i).setX(heroes.get(i).getX());
                heroes.get(i).setY(heroes.get(i).getY() + 1);
                break;
            default:
                break;
        }
    }
    
    private void applyOvertimeDmg(Hero h) {
//        System.out.println("Damage overtime " + h.getDmgOvertime());
        if (h.getRoundsApply() > 0) {
            h.setHp(h.getHp() - h.getDmgOvertime());
            h.setRoundsApply(h.getRoundsApply() - 1);
        }
        if (h.getHp() <= 0) {
            h.setDead(true);
        }
    }
    
    public void WriteToFile(File outputFile)
    throws IOException {
            BufferedWriter writer = new BufferedWriter(new FileWriter(outputFile));
            for (Hero h:heroes) {
                writer.append(h.getName());
                if (h.isDead()) {
                    writer.append(" dead");
                } else {
                    writer.write(" " + h.getLevel() + " " + h.getXp() + " " + Math.round(h.getHp()) + " " + h.getX() + " " + h.getY());
                }
                writer.newLine();
            }

            writer.close();
    }
}
