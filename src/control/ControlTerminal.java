package control;

import java.io.FileNotFoundException;
import java.util.Scanner;

import model.MapState;
import model.InitWaves.Difficulty;

public class ControlTerminal {
    MapState state;

    private void mainMenuPrint() {
        System.out.println("------POKEREVOLUTE------");
        System.out.println("CLASSIC");
        System.out.println("MARATHON");
        System.out.println("BOUTIQUE");
        System.out.println("TEAM");
        System.out.println();
        System.out.print("Tu veux aller ou? : ");
    }

    private void classicMenuPrint() {
        System.out.println("------CLASSIC------");
        System.out.println("FACILE");
        System.out.println("NORMAL");
        System.out.println("DIFFICILE");
        System.out.println("RETOUR");
        System.out.println();
        System.out.print("Choisis la difficulté: ");
    }

    private void marathonMenuPrint() {
        System.out.println("------MARATHON------");
        System.out.println("FACILE");
        System.out.println("NORMAL");
        System.out.println("DIFFICILE");
        System.out.println("RETOUR");
        System.out.println();
        System.out.print("Choisis la difficulté: ");
    }

    private void classicMenu() throws FileNotFoundException {
        classicMenuPrint();
        boolean ready = false;
        Scanner sc = new Scanner(System.in);
        while (!ready) {
            String st = sc.nextLine();
            switch (st) {
                case "FACILE":
                    ready = true;
                    state = new MapState(Difficulty.EASY, 0);
                    break;
                case "NORMAL":
                    ready = true;
                    state = new MapState(Difficulty.NORMAL, 0);
                    break;
                case "DIFFICILE":
                    ready = true;
                    state = new MapState(Difficulty.HARD, 0);
                    break;
                case "RETOUR":
                    ready = true;
                    initTerminal();
                    break;
                default:
                    break;
            }
            state.startTerminal();
        }
        sc.close();
    }

    private void marathonMenu() throws FileNotFoundException {
        marathonMenuPrint();
        boolean ready = false;
        Scanner sc = new Scanner(System.in);
        while (!ready) {
            String st = sc.nextLine();
            switch (st) {
                case "FACILE":
                    ready = true;
                    state = new MapState(Difficulty.EASY, 1);
                    break;
                case "NORMAL":
                    ready = true;
                    state = new MapState(Difficulty.NORMAL, 1);
                    break;
                case "DIFFICILE":
                    ready = true;
                    state = new MapState(Difficulty.HARD, 1);
                    break;
                case "RETOUR":
                    ready = true;
                    initTerminal();
                    break;
                default:
                    break;
            }
            state.startTerminal();
        }
        sc.close();
    }

    public void initTerminal() throws FileNotFoundException {
        boolean ready = false;
        Scanner sc = new Scanner(System.in);
        while (!ready) {
            mainMenuPrint();
            String st = sc.nextLine();
            switch (st) {
                case "CLASSIC":
                    ready = true;
                    classicMenu();
                    break;
                case "MARATHON":
                    ready = true;
                    marathonMenu();
                    break;
                case "BOUTIQUE":
                    ready = true;
                    break;
                case "TEAM":
                    ready = true;
                    break;
                default:
                    break;
            }
        }
        sc.close();
    }
}