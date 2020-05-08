import java.util.Random;
import java.util.Scanner;

public class Hra {
    public static void main(String[] args) {
        Random rand = new Random();
        Scanner input = new Scanner(System.in);

        boolean gameOver = false;
        

        int retard = 1;

        int lore = 1;   

        //hrdina staty
        int heroAktHealth = 20;
        int heroMaxHealth = 20;
        int heroDamage = 3;
        int heroDef = 5;
        int heroMaxDef = 5;
        int heroAgility = 5;
        int heroMaxAgility = 5;
        int gold = 0;

        //enemáci staty
        int[] dmg = {7, 8, 6, 11};
        int[] def = {0, 1, 2, 4};
        int[] hp = {15, 10, 51, 20};
        int[] agi = {6, 7, 3, 5};


        //hrdina pozice  X  Y
        int[] heroPos = {0, 0};

        //město
        int[][] mesto = {{1, 0}};
        int[][] dung = {{0, 5}, {5, 15}, {6, 8}, {-8, 12}};


        if (!gameOver) {
            while (!gameOver) {

                for(int x = 0; x < dung.length; x++){
                    if(heroPos[0] == dung[x][0] && heroPos[1] == dung[x][1]){
                        int level = rand.nextInt(3) + 5;
                        int aktLevel = 0;
                        System.out.println("Procházíš zkrze zarostlou kamenou bránu do sklepení");
                        for(int y = aktLevel; y < level; y++){

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
                    
                    
                                while (gameOver == false && enemyAlive == true) {
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
                                            enemy[3] = 1;
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
                                        
                                        heroAgility = heroMaxAgility;
                                        heroDef = heroMaxDef; 

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
                                            System.out.println("Oponent ti útočí na tělo a dostáváš poškození poškození za " + aktDmg);
                                            System.out.println("Máš " + heroAktHealth + " hp");
                                        } else {
                                            System.out.println("Oponent tě minul");
                                        }
                                    }
                    
                                    //enemy útok na nohy
                                    else if (enemyAtack == 1) {
                                        if (heroAgility <= 0) {
                                            heroAgility = 1;
                                        }
                                        int dodge = rand.nextInt(heroAgility) + 1;
                                        if (heroAgility > dodge) {
                                            int aktDmg = enemy[0] - heroDef;
                                            heroAktHealth = heroAktHealth - aktDmg;
                                            heroAgility--;
                                            System.out.println("Oponent ti útočí na nohy a dostáváš poškození poškození za " + aktDmg);
                                            System.out.println("Máš " + heroAktHealth + " hp a snižuje ti agi na " + heroAgility);
                                        } else {
                                            System.out.println("Oponent tě minul");
                                        }
                    
                                    }
                    
                                    //zabití hráče
                                    if (heroAktHealth <= 0) {
                                        gameOver = true;
                                        System.out.println("Byl jsi zabit");
                                        break;
                                    }
                                }
                                System.out.println("");
                                System.out.println("Vstupuješ do dalšího patra.");
                        }
                        System.out.println("");
                        System.out.println("Vcházíš po schodech do posledního patra, kde zářivý barel. Následně ho ihned po dodupnutí na podloahu vstřebáš a začínáš vidět svou minulost");
                        System.out.println("");

                        //todo##
                        if(lore == 0){
                            System.out.println("Mám tě! Zabíjení pavouků je pro mě taková nostalgie. Jednalo se o úplně první zrůdu, kterou jsem kdy zabil. Taky jsem z toho ve městě za naší vesnicí dost vydělal. Více než táta za týden v lese s dřevorubci. Tehdy jsem mě poprvé napadlo, že bych díky tomu mohl dostat naši rodinu z dluhů, které jsme měli. Vzhledem k tomu, že se těch monster báli i lovčí, povedlo se mi na tom vydělat dost peněz. Otce to ale od alkoholu nezachránilo. Tak jsem zůstal jen já a několik desítek sudů piv, které jsem musel splatit.");
                        }
                        else if(lore == 1){
                            System.out.println("Mám tě! Zabíjení pavouků je pro mě taková nostalgie. Jednalo se o úplně první zrůdu, kterou jsem kdy zabil. Taky jsem z toho ve městě za naší vesnicí dost vydělal. Více než táta za týden v lese s dřevorubci. Tehdy jsem mě poprvé napadlo, že bych díky tomu mohl dostat naši rodinu z dluhů, které jsme měli. Vzhledem k tomu, že se těch monster báli i lovčí, povedlo se mi na tom vydělat dost peněz. Otce to ale od alkoholu nezachránilo. Tak jsem zůstal jen já a několik desítek sudů piv, které jsem musel splatit.");
                            System.out.println("");
                            System.out.println("Zvuk klepajících se kostí mi dodnes budí hrůzu. Nemůžu zapomenout na to, jak jsme odvedli mého nemocného bratra k té proklaté čarodějnici. Že prý ho dokáže zachránit, když pořádně zaplatíme. Otec jí dal všechny naše peníze. Je mi zle jen na to pomyslím. Všude byly pověšeny kostry lidí, ona ho položila na stůl a ještě při vědomí mu rozřízla ruku až ke kosti a začala tu kost rvát z jeho těla. Já jsem začal brečet a otec se to pokusil zastavit, ale všechny ty kostry se začaly hýbat. Jako by ožily. Ti kostlivci nepustili otce ke stolu a můj bratr Rafard mezitím začal hrozně křičet. Museli jsme se jen dívat. Můj bratr to nepřežil.");
                            System.out.print("Ten večer jsme to řekli ve vesnici, sebrali vidle a pochodně a vyrazili tu babiznu upálit. Chatrč i se všemi kostlivci na mýtině už ale nebyla. Když jsem unavený a skoro mrtvý, o pár let později, po souboji s Trollem na chatrč náhodou narazil, stará čarodějka mi nabídla pomoc. Vysvětlila mi taky, že ona tehdy ty kostlivce neovládala, že to bylo prokletí těch lidí, kteří se jimi stali. Ona se ty lidi opravdu snažila zachránit. Víc jsem z ní nedostal. Byla už stará a nesmyslně blábolila a opakovala pořád stejná slova. Nevím, proč jsem jí tehdy věřil. Snad proto, že mi ošetřila ránu a nechala najíst. Snad proto, že už jsem tehdy dostal pár odměn za zabití kostlivců a nikdy nebyla poblíž žádná čarodějnice.");
                            System.out.println("");
                        }
                        else if(lore == 2){
                            System.out.println("Mám tě! Zabíjení pavouků je pro mě taková nostalgie. Jednalo se o úplně první zrůdu, kterou jsem kdy zabil. Taky jsem z toho ve městě za naší vesnicí dost vydělal. Více než táta za týden v lese s dřevorubci. Tehdy jsem mě poprvé napadlo, že bych díky tomu mohl dostat naši rodinu z dluhů, které jsme měli. Vzhledem k tomu, že se těch monster báli i lovčí, povedlo se mi na tom vydělat dost peněz. Otce to ale od alkoholu nezachránilo. Tak jsem zůstal jen já a několik desítek sudů piv, které jsem musel splatit.");
                            System.out.println("");
                            System.out.println("Zvuk klepajících se kostí mi dodnes budí hrůzu. Nemůžu zapomenout na to, jak jsme odvedli mého nemocného bratra k té proklaté čarodějnici. Že prý ho dokáže zachránit, když pořádně zaplatíme. Otec jí dal všechny naše peníze. Je mi zle jen na to pomyslím. Všude byly pověšeny kostry lidí, ona ho položila na stůl a ještě při vědomí mu rozřízla ruku až ke kosti a začala tu kost rvát z jeho těla. Já jsem začal brečet a otec se to pokusil zastavit, ale všechny ty kostry se začaly hýbat. Jako by ožily. Ti kostlivci nepustili otce ke stolu a můj bratr Rafard mezitím začal hrozně křičet. Museli jsme se jen dívat. Můj bratr to nepřežil.");
                            System.out.print("Ten večer jsme to řekli ve vesnici, sebrali vidle a pochodně a vyrazili tu babiznu upálit. Chatrč i se všemi kostlivci na mýtině už ale nebyla. Když jsem unavený a skoro mrtvý, o pár let později, po souboji s Trollem na chatrč náhodou narazil, stará čarodějka mi nabídla pomoc. Vysvětlila mi taky, že ona tehdy ty kostlivce neovládala, že to bylo prokletí těch lidí, kteří se jimi stali. Ona se ty lidi opravdu snažila zachránit. Víc jsem z ní nedostal. Byla už stará a nesmyslně blábolila a opakovala pořád stejná slova. Nevím, proč jsem jí tehdy věřil. Snad proto, že mi ošetřila ránu a nechala najíst. Snad proto, že už jsem tehdy dostal pár odměn za zabití kostlivců a nikdy nebyla poblíž žádná čarodějnice.");
                            System.out.println("");
                            System.out.println("Troll. Pamatuji si na den, kdy jsem ulovil prvního. Tehdy to byl můj největší úlovek. Taky mě to málem stálo život. Té jeskyně u Žlutých skal se všichni báli. Popravdě já se bál taky. Ale ty peníze, které mi nabízeli, to se nedalo odmítnout. Mám kvůli nim sice obrovskou jizvu na břiše ale ten Troll dopadl hůř. Můj život ale zachránila ta čarodějka, kterou jsem náhodou potkal.");
                            System.out.print("Díky těm penězům a té čarodějce jsem se ostatně dostal sem.");
                            System.out.println("");
                        }
                        else if(lore <= 3){
                            System.out.println("Mám tě! Zabíjení pavouků je pro mě taková nostalgie. Jednalo se o úplně první zrůdu, kterou jsem kdy zabil. Taky jsem z toho ve městě za naší vesnicí dost vydělal. Více než táta za týden v lese s dřevorubci. Tehdy jsem mě poprvé napadlo, že bych díky tomu mohl dostat naši rodinu z dluhů, které jsme měli. Vzhledem k tomu, že se těch monster báli i lovčí, povedlo se mi na tom vydělat dost peněz. Otce to ale od alkoholu nezachránilo. Tak jsem zůstal jen já a několik desítek sudů piv, které jsem musel splatit.");
                            System.out.println("");
                            System.out.println("Zvuk klepajících se kostí mi dodnes budí hrůzu. Nemůžu zapomenout na to, jak jsme odvedli mého nemocného bratra k té proklaté čarodějnici. Že prý ho dokáže zachránit, když pořádně zaplatíme. Otec jí dal všechny naše peníze. Je mi zle jen na to pomyslím. Všude byly pověšeny kostry lidí, ona ho položila na stůl a ještě při vědomí mu rozřízla ruku až ke kosti a začala tu kost rvát z jeho těla. Já jsem začal brečet a otec se to pokusil zastavit, ale všechny ty kostry se začaly hýbat. Jako by ožily. Ti kostlivci nepustili otce ke stolu a můj bratr Rafard mezitím začal hrozně křičet. Museli jsme se jen dívat. Můj bratr to nepřežil.");
                            System.out.print("Ten večer jsme to řekli ve vesnici, sebrali vidle a pochodně a vyrazili tu babiznu upálit. Chatrč i se všemi kostlivci na mýtině už ale nebyla. Když jsem unavený a skoro mrtvý, o pár let později, po souboji s Trollem na chatrč náhodou narazil, stará čarodějka mi nabídla pomoc. Vysvětlila mi taky, že ona tehdy ty kostlivce neovládala, že to bylo prokletí těch lidí, kteří se jimi stali. Ona se ty lidi opravdu snažila zachránit. Víc jsem z ní nedostal. Byla už stará a nesmyslně blábolila a opakovala pořád stejná slova. Nevím, proč jsem jí tehdy věřil. Snad proto, že mi ošetřila ránu a nechala najíst. Snad proto, že už jsem tehdy dostal pár odměn za zabití kostlivců a nikdy nebyla poblíž žádná čarodějnice.");
                            System.out.println("");
                            System.out.println("Troll. Pamatuji si na den, kdy jsem ulovil prvního. Tehdy to byl můj největší úlovek. Taky mě to málem stálo život. Té jeskyně u Žlutých skal se všichni báli. Popravdě já se bál taky. Ale ty peníze, které mi nabízeli, to se nedalo odmítnout. Mám kvůli nim sice obrovskou jizvu na břiše ale ten Troll dopadl hůř. Můj život ale zachránila ta čarodějka, kterou jsem náhodou potkal.");
                            System.out.print("Díky těm penězům a té čarodějce jsem se ostatně dostal sem.");
                            System.out.println("");
                            System.out.println("Můj první goblin. Než jsem si zvykl na ty pohyby chvíli to trvalo. Ale nakonec se ukázalo, že to jsou stejně blbá monstra, jako u nás doma. Jak se lidi můžou bát něčeho co má mozek o velikosti hrášku. Navíc je musí cítit na míle daleko. Je mi z nich zle. Z toho puchu se mi motá hlava. Takhle smradlavé a slizké monstra jsme u nás doma neměli. Za ty peníze to ale stojí. Víc takových a budu si moci dovolit koupit dům tady v MightyBarelu.");
                        }
                        System.out.println("");
                        int specBoost = rand.nextInt(3) + 1;
                        if(specBoost == 1){
                            heroMaxAgility += 3;
                            System.out.println("Cítíš, jak se rychlejí pohybuješ. Dostáváš 3 do agility. Máš " + heroAgility + " agilitu");
                        }
                        else if(specBoost == 2){
                            heroDamage += 3;
                            System.out.println("Upadneš na podlahu a uděláš do ni díru. Dostáváš 3 body do poškození. Máš " + heroDamage + " poškození");
                        }
                        else if(specBoost == 3){
                            heroMaxDef += 3;
                            System.out.print("Šáhneš si na kůži a ucítíš, jako by so šahal na kamínky.Dostáváš 3 body do ocharny. Máš " + heroDef + " ochranu");
                        }
                        else if(specBoost == 4 ){
                            heroMaxHealth += 3;
                            for(int w = heroAktHealth; x < heroMaxHealth; x++){
                                
                            }
                            System.out.println("Ucítíš, jak ti teplá krev a vidíš, jak mění barvu. Dostáváš 3 body do maximálních životů a byl jsi vyléčen. Máš " + heroMaxHealth + "bodů maximalních životů");
                            lore++;
                            System.out.println("");
                            System.out.println("Vycházíš zpátky na povrch a uslyšíš, jak se za tebou celé sklepení propadlo do země.");
                            dung[x][0] = -2000;
                            dung[x][1] = -2000;
                        }
                    }
                }
                
                //umřít
                if (heroAktHealth <= 0) {
                    gameOver = true;
                }                

                //město
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
                            System.out.println("Přistoupíš ke staříkovi a zeptáš se, co má na skladě. On ti začne ukazovat různé barely za stejnou cenu. Cena je 30 goldů jeden. Dostupné pŕíkazy - agilita, maxZivoty, poskozeni, obrana");
                            System.out.println("");
                            String barelChoose = input.nextLine();

                            if(gold >= 30){
                                //boost agility
                                if(barelChoose.equals("agilita")){
                                    heroMaxAgility++;
                                    gold -= 30;
                                    System.out.println("Vybral jsi si barel agility. Zaplatíš a vstřebáš ho. Dostáváš 1 agilitu. Máš " + heroAgility + " agility");
                                    System.out.println("Máš " + gold + " goldů");
                                }

                                //boost životů
                                else if(barelChoose.equals("maxZivoty")){
                                    heroMaxHealth++;
                                    gold -= 30;
                                    System.out.println("Vybral jsi si barel boostu životů. Zaplatíš a vstřebáš ho. Dostáváš 1 maximální život. Máš " + heroMaxHealth + " maximálné životů");
                                    System.out.println("Máš " + gold  + " goldů");
                                } 
                                
                                //boost damage
                                else if(barelChoose.equals("poskozeni")){
                                    heroDamage++;
                                    gold -= 30;
                                    System.out.println("Vybral jsi si barel poškození. Zaplatíš a vstřebáš ho. Dostáváš 1 poškození. Máš " + heroDamage + " poškození");
                                    System.out.println("Máš " + gold  + " goldů");
                                }
                                
                                //boost defu
                                else if(barelChoose.equals("obrana")){
                                    heroMaxDef++;
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
                        if (boj < 4) {

                            heroAgility = heroMaxAgility;
                            heroDef = heroMaxDef; 

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
                    
                    
                                while (gameOver == false && enemyAlive == true) {
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
                                            enemy[3] = 1;
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
                                        heroAgility = heroMaxAgility;
                                        heroDef = heroMaxDef; 

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
                                    int enemyAtack = rand.nextInt(2) + 1;
                    
                                    //enemy útok na hlavu
                                    if (enemyAtack == 3) {
                                        int dodge = rand.nextInt(heroAgility * 2) + 1;
                                        if (enemy[3] > dodge) {
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
                                        if (enemy[3] > dodge) {
                                            int aktDmg = enemy[0] - heroDef;
                                            heroAktHealth = heroAktHealth - aktDmg;
                                            System.out.println("Oponent ti útočí na tělo a dostáváš poškození poškození za " + aktDmg);
                                            System.out.println("Máš " + heroAktHealth + " hp");
                                        } else {
                                            System.out.println("Oponent tě minul");
                                        }
                                    }
                    
                                    //enemy útok na nohy
                                    else if (enemyAtack == 1) {
                                        if (heroAgility <= 0) {
                                            heroAgility = 1;
                                        }
                                        int dodge = rand.nextInt(heroAgility) + 1;
                                        if (enemy[3] > dodge) {
                                            int aktDmg = enemy[0] - heroDef;
                                            heroAktHealth = heroAktHealth - aktDmg;
                                            heroAgility--;
                                            System.out.println("Oponent ti útočí na nohy a dostáváš poškození poškození za " + aktDmg);
                                            System.out.println("Máš " + heroAktHealth + " hp a snižuje ti agi na " + heroAgility);
                                        } else {
                                            System.out.println("Oponent tě minul");
                                        }
                    
                                    }
                    
                                    //zabití hráče
                                    if (heroAktHealth <= 0) {
                                        gameOver = true;
                                        System.out.println("Byl jsi zabit");
                                        break;
                                    }
                                }
                            }

                        
                            else{
                                System.out.println("Nereaguješ dostatečně rychle a příšera té zabíjí");
                                gameOver = true;
                                break;
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
                        heroPos[0] = heroPos[0] - 1;
                    }

                    //prostě se podíváš kolem LOL
                    else if (move.equals("mapa")) {

                        //mapa
                        System.out.println("Mighty barel je od tebe vzdálený: " + (mesto[0][0] - heroPos[0]) + " po ose X a " + (mesto[0][1] - heroPos[1]) + " po ose Y");
                        System.out.println("Dunegony jsou od tebe vzdálené: ");
                        for(int x = 0; x < dung.length; x++){
                            System.out.print((dung[x][0] - heroPos[0]) + " po ose X a ");
                            System.out.println((dung[x][1] - heroPos[1]) + " po ose Y ");
                        }
                    }
                }
            }
        }
        System.out.println("Umíráš po nezdařeném boji v kaluži krve");
        System.out.print("Konec hry");
    }
}
