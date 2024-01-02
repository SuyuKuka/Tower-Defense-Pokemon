package main;

import graphics.*;
import model.*;


import java.io.FileNotFoundException;

import javax.swing.SwingUtilities;

import control.ControlTerminal;

public class Game {

    public Game() {
        Player p = new Player();
        MainGraphics graph = new MainGraphics(p, this);
    }

    public static void main(String[] args) throws FileNotFoundException {

        if (args.length != 0 && args[0].equals("[termccccccccccinal]")) {

            // PLACER QQL CHOSE QUI LOAD LES COMPTES SAUVGARDEE
            ControlTerminal terminalControl = new ControlTerminal();
            terminalControl.initTerminal();

        } else {
            SwingUtilities.invokeLater(new Runnable() {
                public void run(){
                    Game game = new Game();
                }
            });
            
            
        }

    }

}
