package com.main;

import com.heroes.Hero;
import com.heroes.Pyromancer;
import com.heroes.Wizard;

public class Main {

    public static void main(String[] args) {
        Pyromancer p = new Pyromancer();
        Wizard w = new Wizard();
        System.out.println(p.getHp() + " " + w.getHp());
        p.attack(w);
        System.out.println(p.getHp() + " " + w.getHp() + w.isDead() + p.getLevel());
    }
}
