import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.Random;
import java.util.concurrent.TimeUnit;

/**********************************************************************
 * Description: Role-Playing Game simulating life as a pirate.
 **********************************************************************/

class plsSourceCode {
    public static void main(String[] args) throws InterruptedException {
        // adding scanner and generator
        Scanner s = new Scanner(System.in);
        Random rand = new Random();

        // variables
        int choice = 0;
        int chp = 1000;
        int shp = 1000;
        double vitality = 100;
        int missionFocus = 100;
        double atk = 100;
        int gold = 0;
        int day = 1;
        int response = 0;
        int nestedResponse = 0;
        int dayType;
        int enemy;
        int treasure;
        String name;
        String ship;
        String[] crew;
        crew = new String[8];

        System.out.println("Select an option:");

        // main menu
        while (choice < 1 | choice > 3) {
            System.out.println(" 1. Start\n 2. How to Play\n 3. Exit");
            try {
                choice = s.nextInt();
                s.nextLine();
            } catch(InputMismatchException e) {
                s.nextLine();
            }

            System.out.println();

            // introduction to premise
            if (choice == 1) {
                System.out.println("You wake up, sore and aching. When you finally manage to peel your eyelids open, an old man speaks to you.\n\n\"!! You're finally awake! You washed up on this shore and I've been taking care of you ever since...\nYou know, back in the day I had my fair share of shipwrecks. I always made it out.\""
                );
                System.out.println();
                while (response != 1 && response != 2) {
                    System.out.println("1> . . .\n2> I don't believe you.");
                    try {
                        response = s.nextInt();
                        s.nextLine();
                    } catch(InputMismatchException e) {
                        s.nextLine();
                    }
                    System.out.println();
                    TimeUnit.MILLISECONDS.sleep(500);

                    if (response == 2) {
                        System.out.println("\"Excuse me?!\"");
                        System.out.println();
                    }

                    if (response != 1 && response != 2) {
                        System.out.println("Please select a dialogue.");
                    }
                }


                System.out.println("\"I was once KING of the Pirates, that's how seaborne I was. I bet if you extracted my\nblood it would be 100% salt water! You don't look very impressed...\"");
                response = 0; // reset response
                System.out.println();
                while (response !=1 && response !=2) {
                    System.out.println("1> . . .\n2> Because I'm not.");
                    try {
                        response = s.nextInt();
                        s.nextLine();
                    } catch(InputMismatchException e) {
                        s.nextLine();
                    }
                    System.out.println();
                    TimeUnit.MILLISECONDS.sleep(500);

                    if (response != 1 && response != 2) {
                        System.out.println("Please select a dialogue.");
                    }
                }

                System.out.println("\"You try it if it's so easy then...I'll bet you won't even be able to collect your crewmates.\nYou know what, I'll even give you my old ship to make it as easy for you as possible. Oh... and this cutlass I still have from my glory days.\"\n\n\033[3mYou take him up on his challenge.\033[0m");
                System.out.println();
                TimeUnit.MILLISECONDS.sleep(500);

                // username and ship name
                System.out.println("You are now a warrior of the sea!\nGet ready to embark on your journey and encounter as many adventures as possible.\n\nWhat is your name?");
                name = s.nextLine();
                crew[0] = name;
                System.out.println();
                TimeUnit.MILLISECONDS.sleep(500);

                System.out.println("What is your ship's name?");
                ship = s.nextLine();
                System.out.println();
                TimeUnit.MILLISECONDS.sleep(500);

                atk = weaponStat(1, atk);

                // immediate option to recruit a crew mate so the game is not impossible
                System.out.println();
                System.out.println("Suddenly, you hear thunderous footsteps...The old man has run up to you\n\n\"Wait! There's something I forgot to tell you!\nMake sure to recruit someone before you leave this island...Sailing alone is bad luck.\n");
                System.out.println("You look around, nothing but sandy beaches surrounding you.\nHowever, you do spot a small island some way away. Would you like to sail there?");
                System.out.println();
                response = 0; // reset response
                while (response != 1 && response != 2) {
                    System.out.println("1> Yes\n2> No.\n");
                    try {
                        response = s.nextInt();
                        s.nextLine();
                    } catch(InputMismatchException e) {
                        s.nextLine();
                    }
                    System.out.println();
                    TimeUnit.MILLISECONDS.sleep(500);

                    if (response == 1) {
                        crew = islandRecruit(crew);
                    }

                    if (response != 1 && response != 2) {
                        System.out.println("Please select a dialogue.");
                    }
                }

                do {
                    // user stats
                    System.out.printf("%s %d:\n", "Day", day);
                    System.out.printf("----------------------------------------------------------------------------------------------------%n");
                    System.out.printf("| %-10s | %s | %s | %s | %s | %s |%n", "Crew Health (CHP)", "Ship Health (SHP)", "Vitality (%)", "Mission Focus (%)", "Strength (ATK)", "Gold");
                    System.out.printf("| %17d | %17d | %12.0f | %17d | %14.0f | %4d |%n", chp, shp, vitality, missionFocus, atk, gold);
                    System.out.printf("----------------------------------------------------------------------------------------------------%n");
                    System.out.println(); // blank line
                    System.out.println();
                    TimeUnit.SECONDS.sleep(3);

                    // formatted ship
                    System.out.printf("%21s\n", "/|_____");
                    System.out.printf("%13s%8s\n", "/", "|__X_|");
                    System.out.printf("%11s%5s\n", "/", "|");
                    System.out.printf("%9s%8s\n", "/", "|\\");
                    System.out.printf("%7s%9s%3s\n", "/", "|", "\\");
                    System.out.printf("%5s%11s%5s\n", "/", "|", "\\");
                    System.out.printf("%23s\n", "/___________|______\\");
                    System.out.printf("%23s\n", "________| |_______");
                    System.out.printf("%7s%15s\n", "\\", "/");
                    System.out.printf("%21s\n", "\\____________/");

                    System.out.println();
                    System.out.println();
                    TimeUnit.SECONDS.sleep(1);

                    // random chance for types of days/encounters
                    dayType = rand.nextInt(10);
                    if (dayType < 3) {
                        quietDay();
                    } else if (dayType < 6) {
                        // formatted output of a skull for danger
                        System.out.printf("%4s%20s\n", "_", "_");
                        System.out.printf("%5s%20s\n", "( )", "( )");
                        System.out.printf("%5s%11s%11s\n", "(_, |", "__ __", "| ,_)");
                        System.out.printf("%6s%5s%3s%3s%7s\n", "\\'\\", "/", "^", "\\", "/'/");
                        System.out.printf("%11s%12s\n", "'\\'\\,/", "\\,/'/'");
                        System.out.printf("%9s%3s%5s%4s\n", "'\\|", "[]", "[]", "|/'");
                        System.out.printf("%10s%5s%4s\n", "(_", "/^\\", "_)");
                        System.out.printf("%11s%3s%3s\n", "\\", "~", "/");
                        System.out.printf("%17s\n", "/HHHHH\\");
                        System.out.printf("%19s\n", "/'/{^^^}\\'\\");
                        System.out.printf("%10s%5s%8s\n", "_,/'/'", "^^^", "'\\'\\,_");
                        System.out.printf("%8s%16s\n", "(_, |", "| ,_)" );
                        System.out.printf("%8s%14s\n", "(_)", "(_)");
                        System.out.println();
                        enemy = rand.nextInt(6); // random chance for type of enemy
                        if (enemy > 2) {
                            // battle method
                            chp = battleHumanHealth(enemy, atk, chp, vitality, name);
                        } else {
                            // battle method
                            shp = battleMonsterHealth(enemy, atk, shp, vitality, name);
                        }
                        if (chp > 0 && shp > 0) {
                            // + vitality if the battle was won
                            System.out.println("Winning a battle increased vitality by 10%!");
                            vitality += 10;
                            gold = battleGold(enemy, gold); // earn gold from battle
                        }
                    } else if (dayType == 6 | dayType == 7) {
                        System.out.println();
                        // formatted output chest
                        System.out.printf("%13s\n", "___________");
                        System.out.printf("%2s%7s%5s\n", "(", "____", ")");
                        System.out.printf("%s\n", "{____| ? |____}");
                        System.out.printf("%2s%8s%4s\n", "\\", "|___|", "/");
                        System.out.printf("%13s\n", "\\_________/");
                        System.out.println();
                        System.out.println("Congratulations! You found a chest, would you like to open it? (Chests may contain either traps or prizes)");
                        response = 0; // reset response
                        while (response !=1 && response !=2) {
                            System.out.println("1> Yes\n2> No");
                            try {
                                response = s.nextInt();
                                s.nextLine();
                            } catch(InputMismatchException e) {
                                s.nextLine();
                            }
                            TimeUnit.SECONDS.sleep(1);
                            if (response == 1) {
                                System.out.println();
                                treasure = rand.nextInt(100);
                                // random chest contents
                                if (treasure < 40) {
                                    if (treasure < 5) {
                                        System.out.println("The chest contained 200 gold!");
                                        gold += 200;
                                    } else if (treasure < 15) {
                                        System.out.println("The chest contained 100 gold!");
                                        gold += 100;
                                    } else {
                                        System.out.println("The chest contained 50 gold!");
                                        gold += 50;
                                    }
                                } else if (treasure < 60) {
                                    if (treasure < 45) {
                                        System.out.println("The chest contained a loaded canon-- it fired at you! Your ship's health went down by 400 SHP.");
                                        shp -= 400;
                                    } else {
                                        System.out.println("The chest contained a bear trap-- one of your crew-mates was hurt by it! Your crew's health went down by 200 CHP.");
                                        chp -= 200;
                                    }
                                } else if (treasure < 70) {
                                    System.out.println("The chest contained a SuperFood! Your crew's health has been increased by 300!");
                                    chp += 300;
                                } else if (treasure < 80) {
                                    System.out.println("The chest contained an EZ Ship Repair Kit! The " + ship + "'s health has been increased by 300!");
                                    shp += 300;
                                } else if (treasure < 90) {
                                    System.out.println("The chest contained an enchanted iron halberd! Your strength went up by 100 ATK.");
                                    atk = weaponStat(3, atk);
                                } else {
                                    System.out.println("The chest contained a map to an island!");
                                    crew = islandRecruit(crew);
                                    System.out.println();
                                    // shop
                                    System.out.println("You have entered the island's shop! Browse the catalogue.");
                                    nestedResponse = 0; // reset response
                                    while (nestedResponse < 1 | nestedResponse > 4) {
                                        System.out.print("1> Steel Broadsword [+75 ATK] (100 gold)\n2> EZ Ship Repair Kit [+300 SHP] (200 gold)\n3> SuperFood [+300 CHP] (300 gold)\n4> Exit\n");
                                        try {
                                            nestedResponse = s.nextInt();
                                            s.nextLine();
                                        } catch(InputMismatchException e) {
                                            s.nextLine();
                                        }

                                        if (nestedResponse == 1) {
                                            if (gold >= 100) {
                                                System.out.println("You bought a steel broadsword for your crew. Your attack has gone up 75 again.");
                                                atk = weaponStat(2, atk);
                                                gold -= 100;
                                            } else {
                                                System.out.println();
                                                System.out.println("You don't have enough gold for this item!");
                                                System.out.println();
                                                nestedResponse = 0; // send back to shop menu
                                            }
                                        } else if (nestedResponse == 2) {
                                            if ( gold >= 200){
                                                System.out.println("You bought an EZ Repair Kit for the " + ship + ". The ship's health has gone up by 300.");
                                                shp += 300;
                                                gold -= 200;
                                            } else {
                                                System.out.println();
                                                System.out.println("You don't have enough gold for this item!");
                                                System.out.println();
                                                nestedResponse = 0; // send back to shop menu
                                            }
                                        } else if (nestedResponse == 3) {
                                            if ( gold >= 300){
                                                System.out.println("You bought SuperFood for your crew member's. They feel rejuvenated. Their health has gone up by 300.");
                                                chp += 300;
                                                gold -= 300;
                                            } else {
                                                System.out.println();
                                                System.out.println("You don't have enough gold for this item!");
                                                System.out.println();
                                                nestedResponse = 0; // send back to shop menu
                                            }
                                        }

                                        if (nestedResponse < 1 | nestedResponse > 4) {
                                            System.out.println("Please select an option.");
                                        }
                                    }
                                }
                                TimeUnit.MILLISECONDS.sleep(500);
                            }
                            if (response != 1 && response != 2) {
                                System.out.println("Please select a dialogue.");
                            }
                        }
                    } else {
                        // island encounter
                        crew = islandRecruit(crew);
                        // shop
                        System.out.println("You have entered the island's shop! Browse the catalogue.");
                        nestedResponse = 0; // reset response
                        while (nestedResponse < 1 | nestedResponse > 4) {
                            System.out.print("1> Steel Broadsword [+75 ATK] (100 gold)\n2> EZ Ship Repair Kit [+300 SHP] (200 gold)\n3> SuperFood [+300 CHP] (300 gold)\n4> Exit\n");
                            try {
                                nestedResponse = s.nextInt();
                                s.nextLine();
                            } catch(InputMismatchException e) {
                                s.nextLine();
                            }

                            if (nestedResponse == 1) {
                                if (gold >= 100) {
                                    System.out.println("You bought a steel broadsword for your crew. Your attack has gone up 75 again.");
                                    atk = weaponStat(2, atk);
                                    gold -= 100;
                                } else {
                                    System.out.println();
                                    System.out.println("You don't have enough gold for this item!");
                                    System.out.println();
                                    nestedResponse = 0; // send back to shop menu
                                }
                            } else if (nestedResponse == 2) {
                                if ( gold >= 200){
                                    System.out.println("You bought an EZ Repair Kit for the " + ship + ". The ship's health has gone up by 300.");
                                    shp += 300;
                                    gold -= 200;
                                } else {
                                    System.out.println();
                                    System.out.println("You don't have enough gold for this item!");
                                    System.out.println();
                                    nestedResponse = 0; // send back to shop menu
                                }
                            } else if (nestedResponse == 3) {
                                if (gold >= 300) {
                                    System.out.println("You bought SuperFood for your crew member's. They feel rejuvenated. Their health has gone up by 300.");
                                    chp += 300;
                                    gold -= 300;
                                } else {
                                    System.out.println();
                                    System.out.println("You don't have enough gold for this item!");
                                    System.out.println();
                                    nestedResponse = 0; // send back to shop menu
                                }
                            }
                            if (nestedResponse < 1 | nestedResponse > 4) {
                                System.out.println("Please select an option.");
                            }
                        }
                        TimeUnit.MILLISECONDS.sleep(500);
                    }

                    // accumulator for days passed
                    day ++;

                    // summary
                    System.out.println();
                    System.out.println("DAY SUMMARY:");
                    System.out.println();
                    // conditional print-outs
                    if (crew[1] != null) {
                        atk += 40;
                        System.out.println(crew[1] + " made your crew stronger today. Strength is increased by 40 ATK.");
                        System.out.println();
                        TimeUnit.MILLISECONDS.sleep(300);
                    }
                    if (crew[3] == null) {
                        chp -= 200;
                        System.out.println("Your crew had nothing to eat today. They starved a little closer to death. Crew health is decreased by 200 CHP.");
                    } else {
                        System.out.println(crew[3] + " cooked a delicious meal. Your crew went to sleep with full stomachs." );
                    }
                    System.out.println();
                    TimeUnit.MILLISECONDS.sleep(300);
                    if (crew[4] != null && chp < 1000) {
                        chp += 200;
                        System.out.println(crew[4] + " healed some of the afflictions ailing your crew mates. Crew health is increased by 200 CHP.");
                        System.out.println();
                        TimeUnit.MILLISECONDS.sleep(300);
                    }
                    if (crew[5] == null) {
                        shp -= 100;
                        System.out.println("Without a shipwright, wear-and-tear is damaging the " + ship + ". Ship health is decreased by 100 SHP.");
                        System.out.println();
                        TimeUnit.MILLISECONDS.sleep(300);
                    } else {
                        if (shp <1000) {
                            shp += 200;
                            System.out.println(crew[5] + " has been working hard to repair the damage done to the " + ship + ". Ship health is increased by 200 SHP.");
                            System.out.println();
                            TimeUnit.MILLISECONDS.sleep(300);
                        }
                    }
                    if (crew[2] == null) {
                        missionFocus -=5;
                        System.out.println("Without a navigator, your crew is slowly getting lost amongst the water. Mission focus is decreased by 5%.");
                        System.out.println();
                        TimeUnit.MILLISECONDS.sleep(300);
                    } else {
                        if (missionFocus <100) {
                            missionFocus += 5;
                            System.out.println(crew[2] + " is making sure you're on the right track. Mission focus is increased by 5%.");
                            System.out.println();
                            TimeUnit.MILLISECONDS.sleep(300);
                        }
                    }
                    if (crew[7] == null) {
                        missionFocus -=5;
                        System.out.println("Without a helmsman, your crew is unable to steer in the direction they want. Mission focus is decreased by 5%.");
                        System.out.println();
                        TimeUnit.MILLISECONDS.sleep(300);
                    } else {
                        if (missionFocus <100) {
                            missionFocus += 5;
                            System.out.println(crew[7] + " is righting your course by steering. Mission focus is increased by 5%.");
                            System.out.println();
                            TimeUnit.MILLISECONDS.sleep(300);
                        }
                    }
                    if (crew[6] == null) {
                        vitality -= 10;
                        System.out.println("Life at sea is hard. Without the entertainment of a musician the livelihood of your crew mates is being drained. Vitality is decreased by 10%");
                        System.out.println();
                        TimeUnit.MILLISECONDS.sleep(300);
                    } else {
                        if (vitality <100) {
                            vitality += 10;
                            System.out.println(crew[6] + " is energetically playing for the crew. Vitality is increased by 10%.");
                            System.out.println();
                            TimeUnit.MILLISECONDS.sleep(300);
                        }
                    }
                    System.out.println();
                    System.out.println("WARNING(S):");
                    System.out.println();
                    if (chp <= 300) {
                        System.out.println("Crew health is low!");
                        System.out.println();
                        TimeUnit.MILLISECONDS.sleep(300);
                    }
                    if (shp <= 300) {
                        System.out.println("Ship health is low!");
                        System.out.println();
                        TimeUnit.MILLISECONDS.sleep(300);
                    }
                    if (vitality <= 30) {
                        System.out.println("Vitality is low!");
                        System.out.println();
                        TimeUnit.MILLISECONDS.sleep(300);
                    }
                    if (missionFocus <= 30) {
                        System.out.println("Mission Focus is low!");
                        System.out.println();
                        TimeUnit.MILLISECONDS.sleep(300);
                    }
                    if (chp >300 && shp > 300 && vitality > 30 && missionFocus > 30) {
                        System.out.println("Stable stats, no warnings for now.");
                        System.out.println();
                        TimeUnit.MILLISECONDS.sleep(300);
                    }
                    System.out.println();
                    System.out.println();
                    TimeUnit.SECONDS.sleep(3);
                } while (!crewCheck(crew) && chp > 0 && shp > 0 && vitality > 0 && missionFocus > 0);

                // game ending statements
                if (crewCheck(crew)) {
                    System.out.println();
                    System.out.println("CONGRATULATIONS!\nYou won, looks like you were made for the pirate life.\nYou completed the game in " + day + " days and with " + gold + " gold.");
                } else {
                    System.out.println();
                    System.out.println("GAME OVER...\nOh no! You really aren't cut out to be a pirate. Maybe a fisherman?");
                }
            }

            // how to play
            if (choice == 2) {
                System.out.println("PLS is a typical simulator. You will go through days as a pirate. Your stats and a summary of any changes to your stats are given every day.\nTo win the game, collect as much gold as possible and collect all your crew members!\n");
                System.out.println();
                System.out.println("If you would like additional information on any of the following mechanics, please select them:");
                while (nestedResponse < 1 | nestedResponse > 4) {
                    System.out.print("1> Enemies\n2> Crew mates\n3> Important Notes\n4> Back to Main Menu\n");
                    try {
                        nestedResponse = s.nextInt();
                        s.nextLine();
                    } catch(InputMismatchException e) {
                        System.out.println("Please select a valid option.");
                        s.nextLine();
                    }
                    System.out.println();

                    // information on crew roles
                    if (nestedResponse == 1) {
                        System.out.println("When out at sea, you might encounter two types of enemies.\nMonster enemies (kraken, megalodon, giant squid) will damage your ship's health\nwhile human enemies (armada, enemy pirates, bandits) will damage your crew's health.");
                        nestedResponse = 0;
                    } else if (nestedResponse == 2) {
                        System.out.println("Make sure to prioritize who you recruit to your crew based on what your stats look like.\nDifferent crew mates offer different kinds of support.\nHere is a summary of their roles and specialities.\n");
                        TimeUnit.MILLISECONDS.sleep(300);
                        System.out.println("First Mate:\n\033[3mA first mate is the right hand of the captain; the vice captain that helps protect the crew.\033[0m\n> +40 ATK every day on crew");
                        System.out.println();
                        TimeUnit.MILLISECONDS.sleep(300);
                        System.out.println("Navigator:\n\033[3mA navigator makes sure the ship is sailing the right route.\033[0m\n> -5% Mission Focus every day NOT on crew\n> 5% Mission Focus recovered every day on crew");
                        System.out.println();
                        TimeUnit.MILLISECONDS.sleep(300);
                        System.out.println("Chef:\n\033[3mA chef keeps the crew well-fed.\033[0m\n> -200 CHP every day chef NOT on crew");
                        System.out.println();
                        TimeUnit.MILLISECONDS.sleep(300);
                        System.out.println("Doctor:\n\033[3mA doctor heals injuries to the crew.\033[0m\n> 200 CHP recovered every day on crew");
                        System.out.println();
                        TimeUnit.MILLISECONDS.sleep(300);
                        System.out.println("Shipwright:\n\033[3mA shipwright repairs any damages to the ship.\033[0m\n> -100 SHP every day NOT on crew\n> 200 SHP recovered every day on crew");
                        System.out.println();
                        TimeUnit.MILLISECONDS.sleep(300);
                        System.out.println("Musician:\n\033[3mA musician is essential in providing entertainment.\033[0m\n> -10% vitality every day NOT on crew\n> 10% vitality recovered every day on crew");
                        System.out.println();
                        TimeUnit.MILLISECONDS.sleep(300);
                        System.out.println("Helmsman:\n\033[3mA helmsman uses their strength to steer the ship.\033[0m\n> -5% Mission Focus every day NOT on crew\n> 5% Mission Focus recoveredevery day on crew");
                        System.out.println();
                        nestedResponse = 0;
                    } else if (nestedResponse == 3) {
                        // important information
                        System.out.println();
                        System.out.println("*Your vitality affects the strength of your attacks proportionately!\nThe vitality percentage of your strength is dealt as damage.");
                        System.out.println();
                        System.out.println("*Aside from a musician, your vitality goes up after winning battles.");
                        System.out.println();
                    }
                }
                choice = 0;
            } // end of How to Play
            if (choice < 1 | choice > 3) {
                System.out.println("Please select a valid option.");
                System.out.println();
            }
        }
    }

    /**
     Description: update the attack stat dependent on weapon
     Precondition: integer values are positive; x is between 1 and 3
     Postcondition: Return raised attack integer

     @param x - number 'type' of weapon the user obtained
     @param y - original attack stat
     @return y - updated attack stat
     */
    public static double weaponStat(int x, double y) {
        switch (x) {
            case 1 -> // rusty cutlass
                    y += 50;
            case 2 -> // steel broadsword
                    y += 75;
            case 3 -> // enchanted iron broadsword
                    y += 100;
        }
        return y;
    }

    /**
     Description: check whether all crew members have been gathered.
     Precondition: x is initialized
     Postcondition: boolean outcome on if all string elements have values

     @param x - list of crew members
     @return boolean - updated crew members
     */
    public static boolean crewCheck(String[] x) {
        for (int i = 0; i<x.length; i++) {
            if (x[i] == null) {
                return false;
            }
        }
        return true;
    }

    /**
     Description: print 'dots' for a quiet day
     Precondition: none
     Postcondition: formatted output is printed
     */
    public static void quietDay() {
        System.out.printf("%6s%17s%17s\n", "______", "______", "______" );
        System.out.printf("%s%6s%11s%6s%11s%6s\n", "|", "|", "|", "|", "|", "|");
        System.out.printf("%7s%17s%17s\n", "|_____|", "|_____|", "|_____|" );
        System.out.println();
        System.out.println("Nothing happened. It was a quiet day.");
        System.out.println();
    }

    /**
     Description: battle against a human type enemy
     Precondition: int values are positive; x is initialized
     Postcondition: returns altered c value, prints outcome of battle

     @param a - type of enemy
     @param b - attack stat
     @param c - crew health stat
     @param d - vitality stat
     @param x - username
     @return c - updated crew health
     battleHumanHealth @throws - InterruptedException
     */
    public static int battleHumanHealth(int a, double b, int c, double d, String x) throws InterruptedException {
        // enemy, atk, chp, vitality, name
        Random rand = new Random();
        // variables
        double monsterHealth = 0;
        double monsterAtk = 0;
        int num;
        double efficiency;
        switch (a) {
            case 3 -> {
                monsterHealth = 1200;
                monsterAtk = 60;
                System.out.println("Captain " + x + "'s crew is engaging in battle against the armada!");
                System.out.println();
            }
            case 4 -> {
                monsterHealth = 800;
                monsterAtk = 70;
                System.out.println("Captain " + x + "'s crew is engaging in battle against enemy pirates!");
                System.out.println();
            }
            case 5 -> {
                monsterHealth = 1000;
                monsterAtk = 80;
                System.out.println("Captain " + x + "'s crew is engaging in battle against bandits!");
                System.out.println();
            }
        }
        TimeUnit.SECONDS.sleep(1);
        while (monsterHealth > 0 && c > 0) {
            monsterHealth -= ((d/100)*(b));
            System.out.println("You attacked! The enemy's health went down by " + ((d/100)*(b)) + ".");
            System.out.println();
            TimeUnit.MILLISECONDS.sleep(200);

            num = rand.nextInt(5);
            if (num == 0) {
                efficiency = 0.6;
            } else if (num ==1) {
                efficiency = 0.7;
            } else if ( num == 2) {
                efficiency = 0.8;
            } else if (num == 3) {
                efficiency = 0.9;
            } else {
                efficiency = 1;
            }
            c -= efficiency*(monsterAtk);
            System.out.println("The enemy attacked! Your health went down by " + (efficiency*(monsterAtk)) + ".");
            System.out.println();
            TimeUnit.MILLISECONDS.sleep(200);
        }
        TimeUnit.SECONDS.sleep(1);
        if (c <= 0) {
            System.out.println("The enemy has defeated you! Your game will end today...");
        } else {
            System.out.println("Congratulations on defeating the enemy! Your crew decides to pillage the enemy for gold.");
        }
        return c;
    }

    /**
     Description: battle against a monster type enemy
     Precondition: int values are positive; x is initialized
     Postcondition: returns altered c value, prints outcome of battle

     @param a - type of enemy
     @param b - attack stat
     @param c - ship health stat
     @param d - vitality stat
     @param x - username
     @return c - updated ship health
     battleHumanHealth @throws - InterruptedException
     */
    public static int battleMonsterHealth(int a, double b, int c, double d, String x) throws InterruptedException {
        //enemy, atk, shp, vitality, name
        Random rand = new Random();
        // variables
        double monsterHealth = 0;
        int monsterAtk = 0;
        int num;
        double efficiency;
        switch (a) {
            case 0 -> {
                monsterHealth = 1200;
                monsterAtk = 60;
                System.out.println("Captain " + x + "'s crew is engaging in battle against a giant squid!");
                System.out.println();
            }
            case 1 -> {
                monsterHealth = 800;
                monsterAtk = 70;
                System.out.println("Captain " + x + "'s crew is engaging in battle against a megalodon!");
                System.out.println();
            }
            case 2 -> {
                monsterHealth = 1200;
                monsterAtk = 80;
                System.out.println("Captain " + x + "'s crew is engaging in battle against the kraken!");
                System.out.println();
            }
        }
        TimeUnit.SECONDS.sleep(1);
        while (monsterHealth > 0 && c > 0) {
            monsterHealth -= ((d/100)*(b));
            System.out.println("You attacked! The enemy's health went down by " + ((d/100)*(b)) + ".");
            System.out.println();
            TimeUnit.MILLISECONDS.sleep(200);

            num = rand.nextInt(5);
            if (num == 0) {
                efficiency = 0.6;
            } else if (num ==1) {
                efficiency = 0.7;
            } else if ( num == 2) {
                efficiency = 0.8;
            } else if (num == 3) {
                efficiency = 0.9;
            } else {
                efficiency = 1;
            }
            c -= efficiency*(monsterAtk);
            System.out.println("The enemy attacked! Your ship's health went down by " + (efficiency*(monsterAtk)) + ".");
            System.out.println();
            TimeUnit.MILLISECONDS.sleep(200);
        }
        TimeUnit.SECONDS.sleep(1);
        if (c <= 0) {
            System.out.println("The enemy has defeated you! Your game will end today...");
        } else {
            System.out.println("Congratulations on defeating the enemy! Your crew decides to pillage the enemy for gold.");
        }
        return c;
    }

    /**
     Description: gold earned through battle
     Precondition: int values are positive
     Postcondition: returns increased y value

     @param x - strength type of enemy defeated
     @param y - original gold amount
     @return y - updated gold amount
     */
    public static int battleGold(int x, int y) {
        // enemy, gold
        switch (x) {
            case 0, 3 -> {
                y += 100;
                System.out.println("You earned 100 gold from defeating the enemy.");
            }
            case 1, 4 -> {
                y += 300;
                System.out.println("You earned 300 gold from defeating the enemy.");
            }
            case 2, 5 -> {
                y += 200;
                System.out.println("You earned 200 gold from defeating the enemy.");
            }
        }
        return y;
    }

    /**
     Description: recruitment of crew member
     Precondition: int values are positive; x is initialized
     Postcondition: returns x with a new value for one of the elements

     @param x - list of crew members
     @return c - new named crew mate
     */
    public static String [] islandRecruit(String [] x) {
        Scanner sc = new Scanner(System.in);
        int option = 0;
        System.out.printf("%13s\n", "_ ^ _");
        System.out.printf("%14s\n", "'_\\V/ `");
        System.out.printf("%12s\n", "' oX`");
        System.out.printf("%11s\n", "X");
        System.out.printf("%11s\n", "X");
        System.out.printf("%11s\n", "X");
        System.out.printf("%11s%11s\n", "X", "\\O/");
        System.out.printf("%17s%4s\n", "X.a##a.", "M");
        System.out.printf("%22s\n", ".aa########a.>>");
        System.out.printf("%25s\n", ".a################aa.");
        System.out.printf("%s\n", "~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");


        System.out.println();
        System.out.println("You have arrived at a new island full of potential crew mates! Who would you like to ask to join your crew?");
        while (option < 1 | option > 7 | x[option] != null ) {
            System.out.print("\n1> First Mate\n2> Navigator\n3> Chef\n4> Doctor\n5> Shipwright\n6> Musician\n7> Helmsman\n");
            try {
                option = sc.nextInt();
                sc.nextLine();
            } catch(InputMismatchException e) {
                sc.nextLine();
            }
            System.out.println();

            if (option < 1 | option > 7) {
                System.out.println("Please select an option.");
            } else if (x[option] != null) {
                System.out.println("Cannot recruit this crew mate. You have either tried recruiting someone you already have on your crew, or tried recruiting another captain (0) onto your crew.");
            }
        }
        System.out.println("Crew mate recruited! Please name your new crew mate.");
        x[option] = sc.next();
        System.out.println();
        return x;
    }
}