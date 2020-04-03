import java.util.Random;
import java.util.Scanner;

public class Hra {
    public static void main(String[] args) {
        Random rand = new Random();
        Scanner input = new Scanner(System.in);

        boolean gameOver = false;
        

        int retard = 1;

        //hrdina staty
        int heroAktHealth = 20;
        int heroMaxHealth = 20;
        int heroDamage = 5000;
        int heroDef = 5000;
        int heroAgility = 5000;
        int gold = 1000;

        //enemáci staty
        int[] dmg = {2, 3, 4, 6};
        int[] def = {0, 1, 2, 4};
        int[] hp = {15, 10, 51, 20};
        int[] agi = {2, 7, 1, 5};


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

                if(heroPos[0] == mesto[0][0] && heroPos[1] == mesto[0][1]){
                    System.out.println("Vcházíš do města Mighty City. Vidíš po ulicíh procházet se stovky lidí. Zahlédneš i skupinu Barel gardy, která se pokouší zabránit kriminalitě, ale jak se o ulici vedle přesvědčíš, tak se jim to moc nedaří, jelikož v jedné temné uličce si koutkem oka zaregistroval zloděje, ovšem není to tvůj business, takže akorát pokrčíš rameny a jdeš dále. Dostupné pŕíkazy - medik, obchod, leave");
                    String cityCom = input.nextLine();
                    System.out.println("");

                    if(cityCom.equals("leave")){
                        heroPos[0] += 1;
                    }


                    //obchodník (nákup useless barelů)
                    else if(cityCom.equals("obchod")){
                        System.out.println("Vcházíš do zapadlého obchodu v rohu centra. Za pultem uvidíš staříka s dřevěnou rukou a páskou přes oko. Připadal ti trohu jako pirát... akorát naopak. Dostupné příkazy - buy, leave");
                        String obchod = input.nextLine();
                        System.out.println("");
                        
                        if(obchod.equals("buy")){
                            System.out.println("Přistoupíš ke staříkovi a zeptáš se, co má na skladě. On ti začne ukazovat různé barely za stejnou cenu. Cena je 30 goldů jeden. Dostupné pŕíkazy - agilita, maxŽivoty, poškození, obrana");
                            System.out.println("");
                            String barelChoose = input.nextLine();

                            if(gold >= 30){
                                //boost agility
                                if(barelChoose.equals("agilita")){
                                    heroAgility++;
                                    gold -= 30;
                                    System.out.println("Vybral jsi si barel agility. Zaplatíš a vstřebáš ho. Dostáváš 1 agilitu. Máš " + heroAgility + " agility");
                                    System.out.println("Máš " + gold + " goldů");
                                }

                                //boost životů
                                else if(barelChoose.equals("maxŽivoty")){
                                    heroMaxHealth++;
                                    gold -= 30;
                                    System.out.println("Vybral jsi si barel boostu životů. Zaplatíš a vstřebáš ho. Dostáváš 1 maximální život. Máš " + heroMaxHealth + " maximálné životů");
                                    System.out.println("Máš " + gold  + " goldů");
                                } 
                                
                                //boost damage
                                else if(barelChoose.equals("poškození")){
                                    heroDamage++;
                                    gold -= 30;
                                    System.out.println("Vybral jsi si barel poškození. Zaplatíš a vstřebáš ho. Dostáváš 1 poškození. Máš " + heroDamage + " poškození");
                                    System.out.println("Máš " + gold  + " goldů");
                                }
                                
                                //boost defu
                                else if(barelChoose.equals("obrana")){
                                    heroMaxHealth++;
                                    gold -= 30;
                                    System.out.println("Vybral jsi si barel obrany. Zaplatíš a vstřebáš ho. Dostáváš 1 obranu. Máš " + heroDamage + " obrany");
                                    System.out.println("Máš " + gold  + " goldů");
                                }

                            }   
                            
                            else{
                                retard += rand.nextInt(3) + 1;
                            System.out.println("Jelikož si ze sebe nebyl schopen vykoktat jediné slovo, jak si byl zaskočen jeho vzhledem, i když si viděl odost horší stvoŕení než on. Obchodník se rozřuzil, že je to tento týden už po " + retard + ". kdo nebyl schopen si u něj něco koupit a vyhnal tě na ulici");
                            }

                            System.out.println("Poděkuješ za jeho čas a odcházíš zpátky do ulice");
                            System.out.println("");
                        }

                        //normální odchod
                        else if(obchod.equals("leave")){
                            System.out.println("Zapoměl jsi, co si chtěl a odcházíš zpátky do ulice");
                            System.out.println("");
                        }

                        //retard
                        else{
                            retard += rand.nextInt(3) + 1;
                            System.out.println("Jelikož si ze sebe nebyl schopen vykoktat jediné slovo, jak si byl zaskočen jeho vzhledem, i když si viděl odost horší stvoŕení než on. Obchodník se rozřuzil, že je to tento týden už po " + retard + ". kdo nebyl schopen si u něj něco koupit a vyhnal tě na ulici");
                        }

                    }

                    //healer
                    else if(cityCom.equals("medik")){
                        System.out.println("Vcházíš k doktorovi. Hned co otevřeš dveře tvou pozornost upoutá lekářka, žena přesně podle tvých představ. Ta svým nádherným hlasem pronese: Léčení stojí 10 goldů. Dostupné příkazy - heal, leave");
                        String healer = input.nextLine();

                        // healing
                        if(healer.equals("heal")){

                            if(gold <= 10){
                                System.out.println("Nemáš dostatek goldů");
                            }

                            else{
                                for(int hapka = heroAktHealth; hapka < heroMaxHealth; hapka++){
                                    heroAktHealth++;
                                }
                                gold -= 10;
                                System.out.println("Jsi zcela uzdraven. Máš " + heroAktHealth + " životů");
                                System.out.println("Máš " + gold + " goldů");
                            }
                            System.out.println("");
                        }

                        //normální odchod
                        else if(healer.equals("leave")){
                            System.out.println("Prostě se otočíš a odejdeš");
                            System.out.println("");
                        }

                        //retard
                        else{
                            System.out.println("Lékařka se urazila a řekla. Poslyš hochu, jestli nechceš vyléčit a chceš se jenom dívat, tak se stav po desáté v hospodě. Těď můžeš vypadnout");
                            System.out.println("");
                        }

                    }
                }

                else{
                    System.out.println("Tvá pozice je X " + heroPos[0] + " a pozice Y je " + heroPos[1] + " Dostupné příkazy - mapa, doleva, doprava, dopredu, dozadu");
                    
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
                        if (boj < 5) {

                            boolean alive = true;

                            //enemák generátor
                            String[] race = {"kostlivci", "pavoukovi", "trollovi", "goblinovi"};
                     
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
                                        int ingold = rand.nextInt(9) + 1;
                                        gold = gold + ingold;
                                        System.out.println("Získal jsi " + ingold + " Máš " + gold);
                                        System.out.print("");

                                        int scailing = rand.nextInt(3) + 1;

                                        if(scailing == 1){
                                            dmg[enemyGen] = dmg[enemyGen] + 1;
                                        }
                                        
                                        else if(scailing == 2){
                                            def[enemyGen] = def[enemyGen] + 1;
                                        }

                                        else if(scailing == 3){
                                            hp[enemyGen] = hp[enemyGen] + 1;
                                        }

                                        else if(scailing == 4){
                                            agi[enemyGen] = agi[enemyGen] + 1;
                                        }

                                        
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
                    else if (move.equals("mapa")) {

                        //mapa
                        System.out.println("Mighty barel je od tebe vzdálený: " + (mesto[0][0] - heroPos[0]) + " po pozici X a " + (mesto[0][1] - heroPos[1]));
                        System.out.println("Dunegony jsou od tebe vzdálené: ");
                        for(int x = 0; x < dung.length; x++){
                            System.out.print((dung[x][0] - heroPos[0]) + " po pozici X a ");
                            System.out.println((dung[x][1] - heroPos[1]) + " po pozici Y ");
                        }
                    }
                }
            }
        }
    }
}
