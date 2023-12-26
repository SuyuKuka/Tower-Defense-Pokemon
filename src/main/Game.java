package main;

import graphics.MainGraphics;
import model.Ennemi;
import model.InitPokemon;
import model.InitWaves;
import model.Map;
import model.MapState;
import model.Player;
import model.InitWaves.Difficulty;

import java.io.FileNotFoundException;

import control.ControlTerminal;

public class Game {

    public Game() {
        Player p = new Player();
        MainGraphics graph = new MainGraphics(p, this);

    }

    public static void main(String[] args) throws FileNotFoundException {

        if (args.length != 0 && args[0].equals("[termiqsdsqdqsnal]")) {

            // PLACER QQL CHOSE QUI LOAD LES COMPTES SAUVGARDEE
            ControlTerminal terminalControl = new ControlTerminal();
            terminalControl.initTerminal();

        } else {

            Game game = new Game();

        }

    }

}
