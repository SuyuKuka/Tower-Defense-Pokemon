package main;

import graphics.MainGraphics;

import model.Player;

import java.io.FileNotFoundException;

import control.ControlTerminal;

public class Game {

    public Game() {
        Player p = new Player();
        new MainGraphics(p, this);

    }

    public static void main(String[] args) throws FileNotFoundException {

        if (args.length != 0 && args[0].equals("[termccccccccccinal]")) {

            // PLACER QQL CHOSE QUI LOAD LES COMPTES SAUVGARDEE
            ControlTerminal terminalControl = new ControlTerminal();
            terminalControl.initTerminal();

        } else {

            new Game();

        }

    }

}
