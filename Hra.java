import java.util.Scanner;
import java.util.Arrays;
import java.util.Random;
import fight.Fight;

public class Hra{
    public static void main(String []args){
        try(Scanner idk = new Scanner(System.in)){
            Random huhu = new Random();

            Fight fight = new Fight(); 

            boolean gameOver = false;


            //hrdina staty
            int heroAktHealth = 20;
            int heroMaxHealth = 20;
            int heroDamage = 100;
            int heroDef = 3;
            int heroAgility = 3;

            int gold = 0;


            //hrdina pozice  X  Y
            int[] heroPos = {0, 0};

            //město
            int[][] mesto = {{5, 0}};
            int[][] dung = {{0, 5}, {}};


            if(gameOver == false){
                while(gameOver == false){

                    
                    //umřít
                    if(heroAktHealth <= 0){
                        gameOver = true;
                    }
                    System.out.println("Jsi na louce.Tvá pozice je X " + heroPos[0] + " a pozice Y je " + heroPos[1] + " Dostupné příkazy - pozice, rozhlednout, doleva, doprava, dopredu, dozadu");
                    String move = idk.nextLine();

                    //sebevražda
                    if(move.equals("kill")){
                        System.out.print("Zabil si se....gratuluji");
                        gameOver = true;
                    }
                    
                    //pohyb
                    if(move.equals("dopredu")){
                        heroPos[1] = heroPos[1] + 1;
                    }
                    else if(move.equals("doprava")){
                        heroPos[0] = heroPos[0] + 1;
                    }
                    else if(move.equals("dozadu")){
                        heroPos[1] = heroPos[1] - 1;
                    }
                    else if(move.equals("doleva")){
                        heroPos[1] = heroPos[0] - 1;
                    }

                    //možnost boje + boj
                    int boj = 0;
                    boj = huhu.nextInt(10);
                    if(boj > 10){
                        fight.fajtMetoda();
                    }
                
                    //prostě se podíváš kolem LOL 
                    else if(move.equals("rozhlednout")){
                        
                        //rozhled         X   Y
                        int[] rozhled = {-6, -6};   
                        rozhled[0] = heroPos[0] + rozhled[0];
                        rozhled[1] = heroPos[1] + rozhled[1];
                        for(int i = -5; i < 6; i++){
                            rozhled[1] = -6;
                            rozhled[0] = rozhled[0] + 1;
                            for(int o = 0; o < 11; o++){
                                rozhled[1] = rozhled[1] + 1;
                                for(int y = 0; y < mesto.length; y++){
                                    if(Arrays.equals(rozhled, mesto[y])){
                                        System.out.println("Vydíš město, které je od tebe vzdálené " + (rozhled[0] - heroPos[0]) + " Po ose X a " +  (rozhled[1] - heroPos[1]) + " Po ose Y" );


                                    }
                                }
                                for(int y = 0; y < dung.length; y++){
                                    if(Arrays.equals(rozhled, dung[y])){
                                        System.out.println("Vydíš dungeon, který je od tebe vzdálený " + (rozhled[0] - heroPos[0]) + " Po ose X a " +  (rozhled[1] - heroPos[1]) + " Po ose Y" );


                                    }
                                }
                            }
                        }    
                    }  
                }
            }
        }
    }
}