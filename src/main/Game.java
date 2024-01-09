package main;

import characters.*;
import graphics.*;
import model.*;

import java.io.FileNotFoundException;

import javax.swing.SwingUtilities;

import control.ControlTerminal;

public class Game {

    public Game() {
        initHero();

        MainGraphics graph = new MainGraphics(Player.INSTANCE, this);

    }

    public static void main(String[] args) throws FileNotFoundException {

        if (args.length != 0 && args[0].equals("[termccinal]")) {

            // PLACER QQL CHOSE QUI LOAD LES COMPTES SAUVGARDEE
            ControlTerminal terminalControl = new ControlTerminal();
            terminalControl.initTerminal();

        } else {
            SwingUtilities.invokeLater(new Runnable() {
                public void run() {
                    Game game = new Game();
                }
            });

        }

    }

    public void initHero(){
        Sacha sacha=new Sacha(Player.INSTANCE);
        Ondine ondine=new Ondine(Player.INSTANCE);
        Pierre pierre=new Pierre(Player.INSTANCE);
        Max max=new Max(Player.INSTANCE);
        Rocket rocket=new Rocket(Player.INSTANCE);
    }

}
