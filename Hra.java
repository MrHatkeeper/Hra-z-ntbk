
import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

public class Hra {
    public static void main(String[] args) {
        Random rand = new Random();
        Scanner input = new Scanner(System.in);

        boolean gameOver = false;
        


        //hrdina staty
        int heroAktHealth = 20;
        int heroMaxHealth = 20;
        int heroDamage = 5000;
        int heroDef = 3;
        int heroAgility = 3;
        int gold = 0;


        //hrdina pozice  X  Y
        int[] heroPos = {0, 0};

        //město
        int[][] mesto = {{1, 0}};
        int[][] dung = {{0, 5}, {5, 15}, {6, 8}, {-8, 12}};


        if (!gameOver) {
            while (!gameOver) {


                //umřít
                if (heroAktHealth <= 0) {
                    gameOver = true;
                }

                System.out.println("Jsi na louce.Tvá pozice je X " + heroPos[0] + " a pozice Y je " + heroPos[1] + " Dostupné příkazy - rozhlednout, doleva, doprava, dopredu, dozadu");
                
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
                    boj = rand.nextInt(10);
                    if (boj < 6) {

                        boolean alive = true;

                        //enemák generátor
                        String[] race = {"kostlivci", "pavoukovi", "trollovi", "goblinovi"};
                        int[] dmg = {2, 3, 4, 6};
                        int[] def = {0, 1, 2, 4};
                        int[] hp = {15, 10, 51, 20};
                        int[] agi = {2, 7, 1, 5};
                
                
                
                        //je boj ??
                        boolean flee = true;
                
                        //žije enemák ??
                        boolean enemyAlive = true;
                
                
                        //enemy gen
                        int enemyGen = rand.nextInt(3);
                        int[] enemy = {dmg[enemyGen], def[enemyGen], hp[enemyGen], agi[enemyGen]};
                
                        System.out.println("Bojuješ proti " + race[enemyGen]);
                        System.out.println("Jeho staty jsou dmg - " + enemy[0] + " def - " + enemy[1] + " hp - " + enemy[2] + " agi - " + enemy[3]);
                        System.out.println("Co chceš dělat ? Dostupné příkazy - boj, utek");
                        String fajt = input.nextLine();
                
                
                        //útěk
                        if (fajt.equals("utek")) {
                            int esc = rand.nextInt(9) + 1;
                            if (esc <= 7) {
                                System.out.println("Zakopl si a nepodařilo se ti utéct ");
                                flee = false;
                            } else if (esc > 7) {
                                System.out.println("Možná si tímto útěkem nezachováš čest, ale alespoň život ano");
                            }
                        }
                
                        //boj
                        if (fajt.equals("boj") || flee == false) {
                
                
                            while (alive == true && enemyAlive == true) {
                                System.out.println("Na jakou část těla utočíš ? - hlava, telo, nohy");
                                String kombatPos = input.nextLine();
                
                                //útok hlava
                                if (kombatPos.equals("hlava")) {
                                    int dodge = rand.nextInt(enemy[3] * 2) + 1;
                                    if (heroAgility > dodge) {
                                        int aktDmg = heroDamage - enemy[1];
                                        enemy[2] = enemy[2] - aktDmg;
                                        enemy[1]--;
                                        System.out.println("Dáváš poškození za " + aktDmg);
                                        System.out.println("Oponent má " + enemy[2] + " hp a snížil se mu def na " + enemy[1]);
                                    } else {
                                        System.out.println("Minul jsi");
                                    }
                                }
                
                                //útok tělo
                                else if (kombatPos.equals("telo")) {
                                    int dodge = rand.nextInt(enemy[3]) + 1;
                                    if (heroAgility > dodge) {
                                        int aktDmg = heroDamage - enemy[1];
                                        enemy[2] = enemy[2] - aktDmg;
                                        System.out.println("Dáváš poškození za " + aktDmg);
                                        System.out.println("Oponent má " + enemy[2] + " hp");
                                    } else {
                                        System.out.println("Minul jsi");
                                    }
                                }
                
                                //útok nohy
                                else if (kombatPos.equals("nohy")) {
                                    if (enemy[3] <= 0) {
                
                                    }
                                    int dodge = rand.nextInt(enemy[3]) + 1;
                                    if (heroAgility > dodge) {
                                        if (enemy[3] <= 0) {
                                            int aktDmg = heroDamage - enemy[1];
                                            enemy[2] = enemy[2] - aktDmg;
                                            System.out.println("Oponent má " + enemy[2] + " hp");
                                        } else {
                                            int aktDmg = heroDamage - enemy[1];
                                            enemy[2] = enemy[2] - aktDmg;
                                            enemy[3]--;
                                            System.out.println("Dáváš poškození za " + aktDmg);
                                            System.out.println("Oponent má " + enemy[2] + " hp a snížila se mu agilita na " + enemy[3]);
                                        }
                                    } else {
                                        System.out.println("Minul jsi");
                                    }
                                }
                
                                // enemáka smrt
                                if (enemy[2] <= 0) {
                                    enemyAlive = false;
                                    System.out.println("Zabíšjíš oponenta");
                                }
                                
                
                                //enemák útočí
                                //generátor útoku
                                int enemyAtack = 3;
                
                                //enemy útok na hlavu
                                if (enemyAtack == 3) {
                                    int dodge = rand.nextInt(heroAgility * 2) + 1;
                                    if (heroAgility > dodge) {
                                        int aktDmg = enemy[0] - heroDef;
                                        heroAktHealth = heroAktHealth - aktDmg;
                                        heroDef--;
                                        System.out.println("Oponent ti útočí na hlavu a dostáváš poškození poškození za " + aktDmg);
                                        System.out.println("Máš " + heroAktHealth + " hp a byla ti snížena def na " + heroDef);
                                    } else {
                                        System.out.println("Oponent minul");
                                    }
                
                                }
                
                                //enemy útok na tělo
                                else if (enemyAtack == 2) {
                                    int dodge = rand.nextInt(heroAgility) + 1;
                                    if (heroAgility > dodge) {
                                        int aktDmg = enemy[0] - heroDef;
                                        heroAktHealth = heroAktHealth - aktDmg;
                                        System.out.println("Oponent ti útočí na hlavu a dostáváš poškození poškození za " + aktDmg);
                                        System.out.println("Máš " + heroAktHealth + " hp");
                                    } else {
                                        System.out.println("Oponent tě minul");
                                    }
                                }
                
                                //enemy útok na nohy
                                else if (enemyAtack == 1) {
                                    int dodge = rand.nextInt(heroAgility) + 1;
                                    if (heroAgility > dodge) {
                                        int aktDmg = enemy[0] - heroDef;
                                        heroAktHealth = heroAktHealth - aktDmg;
                                        heroAgility--;
                                        System.out.println("Oponent ti útočí na hlavu a dostáváš poškození poškození za " + aktDmg);
                                        System.out.println("Máš " + heroAktHealth + " hp a snižuje ti agi na " + heroAgility);
                                    } else {
                                        System.out.println("Oponent tě minul");
                                    }
                
                                }
                
                                //zabití hráče
                                if (heroAktHealth <= 0) {
                                    alive = false;
                                    System.out.println("Byl jsi zabit");
                                    break;
                                }
                            }
                        }
                        int ingold = rand.nextInt(9) + 1;
                        gold = gold + ingold;
                        System.out.println("Získal jsi " + ingold + " Máš " + gold);
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
