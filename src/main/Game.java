package main;

import graphics.MainGraphics;
import model.MapState;
import model.Player;
import model.InitWaves.Difficulty;

import java.io.FileNotFoundException;

public class Game {

    public Game() {
        Player p = new Player();
        MainGraphics graph = new MainGraphics(p, this);

    }

    public static void main(String[] args) throws FileNotFoundException {
        if (args.length != 0 && args[0].equals("[terqsdminal]")) {
            MapState terminal = new MapState(Difficulty.EASY);
            terminal.startTerminal();
        } else {

            Game game = new Game();

        }
    }

}
