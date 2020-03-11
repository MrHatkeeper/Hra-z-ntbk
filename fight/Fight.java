package fight;
import java.util.Random;
import java.util.Scanner;


public class Fight{
    public static void fajtMetoda(){
        Random rand = new Random();
        try(Scanner idk = new Scanner(System.in)){

            boolean alive = true;

            //kdo je na tahu ??
            boolean heroT = true;
            boolean enemyT = true;
        
            //enemák generátor
            String[] race = {"kostlivci", "pavoukovi", "trollovi", "goblinovi"}; 
            int[] dmg = {2, 3, 4, 102};
            int[] def = {0, 1, 2, 4};
            int[] hp = {15, 10, 51, 20};
            int[] agi = {2, 7, 1, 3};

            //je boj ??
            boolean flee = true;

            //žije enemák ??
            boolean enemyAlive = true;

            //hero Stats
            int heroAktHealth = 20;
            int heroMaxHealth = 20;
            int heroDamage = 5;
            int heroDef = 3;
            int heroAgility = 3;

            //enemy gen
            int enemyGen = rand.nextInt(3);
            int[] enemy = {dmg[enemyGen], def[enemyGen], hp[enemyGen], agi[enemyGen]}; 
                
            System.out.println("Bojuješ proti " + race[enemyGen]);
            System.out.println("Jeho staty jsou dmg - " + enemy[0] + " def - " + enemy[1] + " hp - " + enemy[2] + " agi - "+ enemy[3] );  
            System.out.println("Co chceš dělat ? Dostupné příkazy - boj, utek");
            String fight = idk.nextLine();


            //útěk
            if(fight.equals("utek")){
                int esc = rand.nextInt(9) + 1; 
                if(esc <= 7){
                    System.out.println("Zakopl si a nepodařilo se ti utéct ");
                    flee = false;
                }
                else if(esc > 7){
                    System.out.println("Možná si tímto útěkem nezachováš čest, ale alespoň život ano");
                }
            }

            //boj
            else if(fight.equals("boj") || flee == false){
                while(alive == true && heroT == true){
                    System.out.println("Na jakou část těla utočíš ? - hlava, telo, nohy");
                    String kombatPos = idk.nextLine();

                    //útok hlava
                    if(kombatPos.equals("hlava")){
                        int dodge = rand.nextInt(enemy[3] * 2) + 1;
                        if(heroAgility > dodge){
                            int aktDmg = heroDamage - enemy[1];
                            enemy[2] = enemy[2] - aktDmg;
                            enemy[1]--;
                            System.out.println("Dáváš poškození za " + aktDmg);
                            System.out.println("Oponent má " + enemy[2] + " hp a snížil se mu def na " + enemy[1]);
                        }
                        else{
                            System.out.println("Minul jsi");
                        }
                    }

                    //útok tělo
                    else if(kombatPos.equals("telo")){
                        int dodge = rand.nextInt(enemy[3])+ 1;
                        if(heroAgility > dodge){
                            int aktDmg = heroDamage - enemy[1];
                            enemy[2] = enemy[2] - aktDmg;
                            System.out.println("Dáváš poškození za " + aktDmg);
                            System.out.println("Oponent má " + enemy[2] + " hp");
                        }
                        else{
                            System.out.println("Minul jsi");
                        }
                    }

                    //útok nohy
                    else if(kombatPos.equals("nohy")){
                        int dodge = rand.nextInt(enemy[3]) + 1;
                        if(heroAgility > dodge){
                            int aktDmg = heroDamage - enemy[1];
                            enemy[2] = enemy[2] - aktDmg;
                            enemy[3]--;
                            System.out.println("Dáváš poškození za " + aktDmg);
                            System.out.println("Oponent má " + enemy[2] + " hp a snížila se mu agilita na " + enemy[3]);
                        }
                        else{
                            System.out.println("Minul jsi");
                        }
                    }

                    // enemáka smrt
                    else if(enemy[2] <= 0){
                        enemyAlive = false;
                    }
                    heroT = false;
                    enemyT = true;
                }

                //enemák útočí
                while(enemyAlive == true && enemyT == true){
                    
                    //generátor útoku
                    int enemyAtack = 3;

                    //enemy útok na hlavu
                    if(enemyAtack == 3){
                        int dodge = rand.nextInt(heroAgility * 2) + 1;
                        if(heroAgility > dodge){
                            int aktDmg = enemy[0] - heroDef;
                            heroAktHealth = heroAktHealth - aktDmg;
                            heroDef--;
                            System.out.println("Oponent ti útočí na hlavu a dostáváš poškození poškození za " + aktDmg);
                            System.out.println("Máš " + heroAktHealth + " hp a byla ti snížena def na " + heroDef);
                        }
                        else{
                            System.out.println("Oponent minul jsi");
                        }

                    }
                    
                    //enemy útok na tělo
                    else if(enemyAtack == 2){
                        int dodge = rand.nextInt(heroAgility) + 1;
                        if(heroAgility > dodge){
                            int aktDmg = enemy[0] - heroDef;
                            heroAktHealth = heroAktHealth - aktDmg;
                            System.out.println("Oponent ti útočí na hlavu a dostáváš poškození poškození za " + aktDmg);
                            System.out.println("Máš " + heroAktHealth + " hp");
                        }

                        else{
                            System.out.println("Oponent tě minul");
                        }
                    }

                    //enemy útok na nohy
                    else if(enemyAtack == 1){
                        int dodge = rand.nextInt(heroAgility) + 1;
                        if(heroAgility > dodge){
                            int aktDmg = enemy[0] - heroDef;
                            heroAktHealth = heroAktHealth - aktDmg;
                            heroAgility--;
                            System.out.println("Oponent ti útočí na hlavu a dostáváš poškození poškození za " + aktDmg);
                            System.out.println("Máš " + heroAktHealth + " hp a snižuje ti agi na " + heroAgility);
                        }

                        else{
                            System.out.println("Oponent tě minul");
                        }

                    }

                    enemyT = false;
                    heroT = true;

                    //zabití hráče
                    if(heroAktHealth <= 0){
                        heroT = false;
                        alive = false;
                    }  
                }    
            }
        }
    }
}
