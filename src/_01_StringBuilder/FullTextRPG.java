package _01_StringBuilder;

import java.util.*;

public class FullTextRPG {
    private static final Scanner scanner = new Scanner(System.in);
    private static int playerHealth = 100;
    private static int playerDamage = 10;
    private static int playerGold = 50;
    private static int playerLevel = 1;
    private static int playerXP = 0;
    private static int playerMana = 100;
    private static int playerClass = 0; // 0 - Warrior, 1 - Mage, 2 - Archer
    private static int factionReputation = 0;
    private static String equippedWeapon = "Fists";
    private static String petName = "Shadow Wolf";
    private static int petHealth = 50;
    private static int petDamage = 10;
    private static int prestigeLevel = 0;
    private static List<String> inventory = new ArrayList<>();
    private static Map<String, Integer> weapons = new HashMap<>();
    private static boolean isCheatMenuActive = false;
    private static String currentLocation = "Town";
    
    // Skill tree and class abilities
    private static int[] skillPoints = {0, 0, 0}; // Skill points for Warrior, Mage, Archer
    private static boolean hasPet = true;

    public static void main(String[] args) {
        System.out.println("Welcome to the Full RPG Game!");

        // Initial weapons and items
        weapons.put("Fists", 10);
        weapons.put("Sword", 20);
        weapons.put("Axe", 30);

        while (true) {
            System.out.println("\nYour Stats - Health: " + playerHealth + " | Damage: " + playerDamage + " | Gold: " + playerGold);
            System.out.println("Level: " + playerLevel + " | XP: " + playerXP + " | Mana: " + playerMana);
            System.out.println("Equipped Weapon: " + equippedWeapon);
            System.out.println("Current Location: " + currentLocation);
            System.out.println("Reputation: " + factionReputation);
            System.out.println("Pet: " + petName + " (Health: " + petHealth + " | Damage: " + petDamage + ")");
            System.out.println("Inventory: " + (inventory.isEmpty() ? "Empty" : String.join(", ", inventory)));

            if (isCheatMenuActive) {
                showCheatMenu();
                continue;
            }

            System.out.println("\nWhat would you like to do?");
            System.out.println("1. Explore");
            System.out.println("2. Check Inventory");
            System.out.println("3. Rest");
            System.out.println("4. Change Weapon");
            System.out.println("5. Visit Shop");
            System.out.println("6. Play Gambling Games");
            System.out.println("7. Quest Log");
            System.out.println("8. Prestige");
            System.out.println("9. Cheat Menu");
            System.out.println("10. Quit Game");
            System.out.print("> ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // consume newline

            switch (choice) {
                case 1:
                    explore();
                    break;
                case 2:
                    checkInventory();
                    break;
                case 3:
                    rest();
                    break;
                case 4:
                    changeWeapon();
                    break;
                case 5:
                    visitShop();
                    break;
                case 6:
                    playGambling();
                    break;
                case 7:
                    acceptQuest();
                    break;
                case 8:
                    prestige();
                    break;
                case 9:
                    isCheatMenuActive = true;
                    break;
                case 10:
                    System.out.println("Thanks for playing! Goodbye!");
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }

            if (playerHealth <= 0) {
                System.out.println("You have died. Game over!");
                return;
            }
        }
    }

    private static void explore() {
        System.out.println("\nYou are exploring the " + currentLocation + "...");
        Random rand = new Random();
        int event = rand.nextInt(5); // 5 possible events (enemies, items, boss, change location, dungeon)

        switch (event) {
            case 0:
                encounterEnemy();
                break;
            case 1:
                findItem();
                break;
            case 2:
                encounterBoss();
                break;
            case 3:
                changeLocation();
                break;
            case 4:
                enterDungeon();
                break;
            default:
                System.out.println("Nothing happened.");
        }
    }

    private static void encounterEnemy() {
        System.out.println("\nYou have encountered a monster!");
        String[] enemies = {"Goblin", "Orc", "Wolf"};
        Random rand = new Random();
        String enemy = enemies[rand.nextInt(enemies.length)];
        int enemyHealth = rand.nextInt(30) + 20;
        int enemyDamage = rand.nextInt(10) + 5;
        System.out.println("It's a " + enemy + "! Health: " + enemyHealth + " | Damage: " + enemyDamage);

        while (enemyHealth > 0 && playerHealth > 0) {
            System.out.println("\n1. Attack");
            System.out.println("2. Flee");
            System.out.print("> ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // consume newline

            if (choice == 1) {
                int damageDealt = playerDamage + weapons.get(equippedWeapon);
                enemyHealth -= damageDealt;
                System.out.println("You dealt " + damageDealt + " damage to the " + enemy + ". It has " + enemyHealth + " health left.");

                if (enemyHealth <= 0) {
                    System.out.println("You defeated the " + enemy + "!");
                   
                    break;
                }

                int damageTaken = enemyDamage;
                playerHealth -= damageTaken;
                System.out.println("The " + enemy + " attacked you for " + damageTaken + " damage. You have " + playerHealth + " health left.");
            } else if (choice == 2) {
                System.out.println("You fled from the battle.");
                break;
            } else {
                System.out.println("Invalid choice. Try again.");
            }
        }
    }

    private static void encounterBoss() {
        System.out.println("\nYou have encountered a BOSS!");
        String boss = "Dragon";
        int bossHealth = 100;
        int bossDamage = 30;
        System.out.println("It's a " + boss + "! Health: " + bossHealth + " | Damage: " + bossDamage);

        while (bossHealth > 0 && playerHealth > 0) {
            System.out.println("\n1. Attack");
            System.out.println("2. Flee");
            System.out.print("> ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // consume newline

            if (choice == 1) {
                int damageDealt = playerDamage + weapons.get(equippedWeapon);
                bossHealth -= damageDealt;
                System.out.println("You dealt " + damageDealt + " damage to the " + boss + ". It has " + bossHealth + " health left.");

                if (bossHealth <= 0) {
                    System.out.println("You defeated the " + boss + "!");
                    dropLoot();
                    break;
                }

                int damageTaken = bossDamage;
                playerHealth -= damageTaken;
                System.out.println("The " + boss + " attacked you for " + damageTaken + " damage. You have " + playerHealth + " health left.");
            } else if (choice == 2) {
                System.out.println("You fled from the boss battle.");
                break;
            } else {
                System.out.println("Invalid choice. Try again.");
            }
        }
    }

    private static void dropLoot() {
        System.out.println("\nThe boss has dropped loot!");
        Random rand = new Random();
        int lootChance = rand.nextInt(100);

        if (lootChance < 50) {
            String loot = "Health Potion";
            inventory.add(loot);
            System.out.println("You found a " + loot + "!");
        }

        lootChance = rand.nextInt(100);
        if (lootChance < 30) {
            int gold = rand.nextInt(100) + 50;
            playerGold += gold;
            System.out.println("You found " + gold + " gold!");
        }

        lootChance = rand.nextInt(100);
        if (lootChance < 15) {
            String[] weaponsList = {"Legendary Sword", "Legendary Axe"};
            String newWeapon = weaponsList[rand.nextInt(weaponsList.length)];
            weapons.put(newWeapon, newWeapon.equals("Legendary Sword") ? 50 : 60);
            System.out.println("You found a powerful new weapon: " + newWeapon + "!");
        }

        lootChance = rand.nextInt(100);
        if (lootChance < 5) {
            String rareItem = "Magic Stone";
            inventory.add(rareItem);
            System.out.println("You found a rare item: " + rareItem + "!");
        }
    }

    private static void enterDungeon() {
        System.out.println("\nYou are entering a dungeon...");
        int dungeonLevel = 1 + playerLevel / 5;  // Harder dungeons as the player progresses
        System.out.println("Dungeon Level: " + dungeonLevel);
        encounterDungeonBoss(dungeonLevel);
    }

    private static void encounterDungeonBoss(int dungeonLevel) {
        System.out.println("You have encountered a Dungeon Boss!");
        String bossName = "Dungeon Boss (Level " + dungeonLevel + ")";
        int bossHealth = 50 + (dungeonLevel * 10);
        int bossDamage = 10 + (dungeonLevel * 2);

        while (bossHealth > 0 && playerHealth > 0) {
            System.out.println("\n1. Attack");
            System.out.println("2. Flee");
            System.out.print("> ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // consume newline

            if (choice == 1) {
                int damageDealt = playerDamage + weapons.get(equippedWeapon);
                bossHealth -= damageDealt;
                System.out.println("You dealt " + damageDealt + " damage to the boss. It has " + bossHealth + " health left.");

                if (bossHealth <= 0) {
                    System.out.println("You defeated the " + bossName + "!");
                    dropLoot();
                    break;
                }

                int damageTaken = bossDamage;
                playerHealth -= damageTaken;
                System.out.println("The boss attacked you for " + damageTaken + " damage. You have " + playerHealth + " health left.");
            } else if (choice == 2) {
                System.out.println("You fled from the dungeon boss.");
                break;
            } else {
                System.out.println("Invalid choice. Try again.");
            }
        }
    }

    private static void findItem() {
        System.out.println("\nYou find a useful item!");
        Random rand = new Random();
        int itemChance = rand.nextInt(100);

        if (itemChance < 40) {
            String item = "Health Potion";
            inventory.add(item);
            System.out.println("You found a " + item + "!");
        } else if (itemChance < 70) {
            String weapon = "Sword";
            weapons.put(weapon, 20);
            System.out.println("You found a " + weapon + "!");
        } else {
            String rareItem = "Magic Scroll";
            inventory.add(rareItem);
            System.out.println("You found a rare item: " + rareItem + "!");
        }
    }

    private static void changeLocation() {
        System.out.println("\nYou are traveling to a new location...");
        String[] locations = {"Forest", "Cave", "Desert", "Mountain"};
        Random rand = new Random();
        currentLocation = locations[rand.nextInt(locations.length)];
        System.out.println("You have arrived at the " + currentLocation);
    }

    private static void checkInventory() {
        System.out.println("\nYour Inventory:");
        if (inventory.isEmpty()) {
            System.out.println("Your inventory is empty.");
        } else {
            for (String item : inventory) {
                System.out.println("- " + item);
            }
        }
    }

    private static void rest() {
        System.out.println("\nYou rest for a while, recovering your health.");
        playerHealth = Math.min(playerHealth + 20, 100);
        System.out.println("You have recovered 20 health. Current Health: " + playerHealth);
    }

    private static void changeWeapon() {
        System.out.println("\nChoose a weapon to equip:");
        List<String> weaponList = new ArrayList<>(weapons.keySet());
        for (int i = 0; i < weaponList.size(); i++) {
            System.out.println((i + 1) + ". " + weaponList.get(i));
        }
        System.out.print("> ");
        int choice = scanner.nextInt();
        scanner.nextLine(); // consume newline
        if (choice >= 1 && choice <= weaponList.size()) {
            equippedWeapon = weaponList.get(choice - 1);
            playerDamage = weapons.get(equippedWeapon);
            System.out.println("You equipped " + equippedWeapon + "!");
        } else {
            System.out.println("Invalid choice.");
        }
    }

    private static void visitShop() {
        System.out.println("\nYou visit the shop.");
        System.out.println("1. Buy Health Potion (10 gold)");
        System.out.println("2. Buy Sword (30 gold)");
        System.out.println("3. Leave Shop");
        System.out.print("> ");
        int choice = scanner.nextInt();
        scanner.nextLine(); // consume newline

        switch (choice) {
            case 1:
                if (playerGold >= 10) {
                    playerGold -= 10;
                    inventory.add("Health Potion");
                    System.out.println("You bought a Health Potion.");
                } else {
                    System.out.println("Not enough gold.");
                }
                break;
            case 2:
                if (playerGold >= 30) {
                    playerGold -= 30;
                    weapons.put("Sword", 20);
                    System.out.println("You bought a Sword.");
                } else {
                    System.out.println("Not enough gold.");
                }
                break;
            case 3:
                System.out.println("You leave the shop.");
                break;
            default:
                System.out.println("Invalid choice.");
        }
    }

    private static void playGambling() {
        System.out.println("\nYou play a gambling game.");
        Random rand = new Random();
        int gambleResult = rand.nextInt(100);
        if (gambleResult < 50) {
            System.out.println("You lost your bet.");
            playerGold -= 20;
        } else {
            System.out.println("You won the bet!");
            playerGold += 50;
        }
        System.out.println("Current Gold: " + playerGold);
    }

    private static void acceptQuest() {
        System.out.println("\nYou accept a new quest: Defeat the Goblin King!");
        // More complex quests can be added here.
    }

    private static void prestige() {
        if (playerLevel >= 100) {
            prestigeLevel++;
            playerLevel = 1;
            playerXP = 0;
            playerHealth = 100;
            playerMana = 100;
            playerDamage = 10;
            playerGold = 50;
            System.out.println("You have reached Prestige Level " + prestigeLevel + "! All stats have reset.");
        } else {
            System.out.println("You need to be level 100 to prestige.");
        }
    }

    private static void showCheatMenu() {
        System.out.println("\nCheat Menu:");
        System.out.println("1. Add 1000 gold");
        System.out.println("2. Max out player level");
        System.out.println("3. Back to main menu");
        System.out.print("> ");
        int choice = scanner.nextInt();
        scanner.nextLine(); // consume newline

        switch (choice) {
            case 1:
                playerGold += 1000;
                System.out.println("You added 1000 gold. Current Gold: " + playerGold);
                break;
            case 2:
                playerLevel = 100;
                playerXP = 0;
                playerHealth = 100;
                playerMana = 100;
                playerDamage = 100;
                System.out.println("Player level set to 100.");
                break;
            case 3:
                isCheatMenuActive = false;
                break;
            default:
                System.out.println("Invalid choice.");
        }
    }
}
