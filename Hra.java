import java.util.Scanner;
import java.util.Arrays;
import fight.Fight;

public class Hra{
    public static void main(String []args){
        Scanner idk = new Scanner(System.in);

        Fight fight = new Fight(); 

        boolean gameOver = false;


        //hrdina staty
        int heroAktHealth = 20;
        int heroMaxHealth = 20;
        int heroDamage = 5;
        int heroAgility = 10;


        //hrdina pozice  X  Y
        int[] heroPos = {0, 0};

        //město
        int[][] mesto = {{5, 0}};
        int[][] dung = {{0, 5}, {}};

        //fight.fajtMetoda();


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
        
            //prostě se podíváš kolem LOL 
            if(move.equals("rozhlednout")){
                
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