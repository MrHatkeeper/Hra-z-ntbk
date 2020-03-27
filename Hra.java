import fight.Fight;

import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

public class Hra {
    public static void main(String[] args) {
        Random huhu = new Random();
        Fight fight = new Fight();

        boolean gameOver = false;


        //hrdina staty
        int heroAktHealth = 20;

        int gold = fight.getGold();


        //hrdina pozice  X  Y
        int[] heroPos = {0, 0};

        //město
        int[][] mesto = {{5, 0}, {5, 6}};
        int[][] dung = {{0, 5}, {5, 15}, {6, 8}, {-8, 12}};


        if (!gameOver) {
            while (!gameOver) {


                //umřít
                if (heroAktHealth <= 0) {
                    gameOver = true;
                }

                System.out.println("Jsi na louce.Tvá pozice je X " + heroPos[0] + " a pozice Y je " + heroPos[1] + " Dostupné příkazy - rozhlednout, doleva, doprava, dopredu, dozadu");
                System.out.println("heroGetGold: " + gold);
                
                Scanner zalud = new Scanner(System.in);
                String move = zalud.nextLine();

                //sebevražda
                if (move.equals("kill")) {
                    System.out.print("Zabil si se....gratuluji");
                    gameOver = true;
                }

                //možnost boje + boj
                if (move.equals("dopredu") || move.equals("doprava") || move.equals("dozadu") || move.equals("doleva")) {
                    int boj = 0;
                    boj = huhu.nextInt(10);
                    if (boj < 6) {
                        Fight idk = new Fight();
                        idk.fajtMetoda();
                    }
                }

                //pohyb
                if (move.equals("dopredu")) {
                    heroPos[1] = heroPos[1] + 1;
                } else if (move.equals("doprava")) {
                    heroPos[0] = heroPos[0] + 1;
                } else if (move.equals("dozadu")) {
                    heroPos[1] = heroPos[1] - 1;
                } else if (move.equals("doleva")) {
                    heroPos[1] = heroPos[0] - 1;
                }

                //prostě se podíváš kolem LOL
                else if (move.equals("rozhlednout")) {

                    //rozhled         X   Y
                    int[] rozhled = {-6, -6};
                    rozhled[0] = heroPos[0] + rozhled[0];
                    rozhled[1] = heroPos[1] + rozhled[1];
                    for (int i = -5; i < 6; i++) {
                        rozhled[1] = -6;
                        rozhled[0] = rozhled[0] + 1;
                        for (int o = 0; o < 11; o++) {
                            rozhled[1] = rozhled[1] + 1;
                            for (int y = 0; y < mesto.length; y++) {
                                if (Arrays.equals(rozhled, mesto[y])) {
                                    System.out.println("Vydíš město, které je od tebe vzdálené " + (rozhled[0] - heroPos[0]) + " Po ose X a " + (rozhled[1] - heroPos[1]) + " Po ose Y");


                                }
                            }
                            for (int y = 0; y < dung.length; y++) {
                                if (Arrays.equals(rozhled, dung[y])) {
                                    System.out.println("Vydíš dungeon, který je od tebe vzdálený " + (rozhled[0] - heroPos[0]) + " Po ose X a " + (rozhled[1] - heroPos[1]) + " Po ose Y");


                                }
                            }
                        }
                    }
                }
            }
        }
    }
}