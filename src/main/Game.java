package main;

import graphics.Boutique;
import graphics.Classique;

import graphics.MainMenu;
import graphics.Marathon;
import graphics.Options;
import graphics.GameScreen;
import graphics.MainGraphics;
import graphics.UpdateMap;
import model.MapState;
import model.Player;
import model.Pokemon;
import model.Tower;
import model.InitWaves.Difficulty;
import model.Pokemon.State;

import java.awt.*;
import java.awt.image.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.swing.*;

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
