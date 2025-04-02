package _01_StringBuilder;

import java.util.*;

public class FullTextRPG {
    private static final Scanner scanner = new Scanner(System.in);
    private static int playerHealth = 100;
    private static int playerDamage = 10;
    private static int playerGold = 50;
    private static List<String> inventory = new ArrayList<>();
    private static String currentLocation = "Town";
    private static boolean isCheatMenuActive = false;
    private static String equippedWeapon = "Fists";

    // Weapons and their damage
    private static Map<String, Integer> weapons = new HashMap<>();

    public static void main(String[] args) {
        System.out.println("Welcome to the Full RPG Game!");

        // Initial weapons and items
        weapons.put("Fists", 10);
        weapons.put("Sword", 20);
        weapons.put("Axe", 30);

        while (true) {
            System.out.println("\nYour Stats - Health: " + playerHealth + " | Damage: " + playerDamage + " | Gold: " + playerGold);
            System.out.println("Equipped Weapon: " + equippedWeapon);
            System.out.println("Current Location: " + currentLocation);
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
            System.out.println("7. Cheat Menu");
            System.out.println("8. Quit Game");
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
                    isCheatMenuActive = true;
                    break;
                case 8:
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
        int event = rand.nextInt(4);  // 4 possible events (enemies, items, boss, change location)

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
                    findGold();
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

        // Random loot drop
        Random rand = new Random();
        int lootChance = rand.nextInt(100);

        // 50% chance of health potion
        if (lootChance < 50) {
            String loot = "Health Potion";
            inventory.add(loot);
            System.out.println("You found a " + loot + "!");
        }

        // 30% chance of gold
        lootChance = rand.nextInt(100);
        if (lootChance < 30) {
            int gold = rand.nextInt(100) + 50;  // Random gold between 50-150
            playerGold += gold;
            System.out.println("You found " + gold + " gold!");
        }

        // 15% chance of new powerful weapon (Legendary)
        lootChance = rand.nextInt(100);
        if (lootChance < 15) {
            String[] weaponsList = {"Legendary Sword", "Legendary Axe"};
            String newWeapon = weaponsList[rand.nextInt(weaponsList.length)];
            weapons.put(newWeapon, newWeapon.equals("Legendary Sword") ? 50 : 60);
            System.out.println("You found a powerful new weapon: " + newWeapon + "!");
        }

        // 5% chance of a rare item (Magic Stone)
        lootChance = rand.nextInt(100);
        if (lootChance < 5) {
            String rareItem = "Magic Stone";
            inventory.add(rareItem);
            System.out.println("You found a rare item: " + rareItem + "!");
        }
    }

    private static void findItem() {
        System.out.println("\nYou found an item!");
        String[] items = {"Health Potion", "Magic Stone", "Sword"};
        Random rand = new Random();
        String item = items[rand.nextInt(items.length)];
        inventory.add(item);
        System.out.println("You picked up a " + item + ".");
    }

    private static void findGold() {
        System.out.println("\nYou found some gold!");
        Random rand = new Random();
        int gold = rand.nextInt(50) + 10;
        playerGold += gold;
        System.out.println("You found " + gold + " gold. You now have " + playerGold + " gold.");
    }

    private static void changeLocation() {
        System.out.println("\nWhere would you like to go?");
        System.out.println("1. Forest");
        System.out.println("2. Dungeon");
        System.out.println("3. Castle");
        System.out.print("> ");
        int choice = scanner.nextInt();
        scanner.nextLine(); // consume newline

        switch (choice) {
            case 1:
                currentLocation = "Forest";
                break;
            case 2:
                currentLocation = "Dungeon";
                break;
            case 3:
                currentLocation = "Castle";
                break;
            default:
                System.out.println("Invalid location. Staying in the current location.");
        }
    }

    private static void checkInventory() {
        System.out.println("\nYour Inventory:");
        if (inventory.isEmpty()) {
            System.out.println("Your inventory is empty.");
        } else {
            System.out.println("Items: " + String.join(", ", inventory));
        }
    }

    private static void rest() {
        System.out.println("\nYou take a rest to regain health...");
        int healthRestored = new Random().nextInt(20) + 10;
        playerHealth += healthRestored;
        System.out.println("You restored " + healthRestored + " health. You now have " + playerHealth + " health.");
    }

    private static void changeWeapon() {
        System.out.println("\nChoose a weapon to equip:");
        weapons.forEach((weapon, damage) -> {
            System.out.println(weapon + " (Damage: " + damage + ")");
        });
        System.out.print("> ");
        String weaponChoice = scanner.nextLine();

        if (weapons.containsKey(weaponChoice)) {
            equippedWeapon = weaponChoice;
            System.out.println("You have equipped the " + weaponChoice + ".");
        } else {
            System.out.println("Invalid weapon choice.");
        }
    }

    private static void visitShop() {
        System.out.println("\nWelcome to the Shop!");
        System.out.println("1. Health Potion (50 Gold)");
        System.out.println("2. Legendary Sword (500 Gold)");
        System.out.println("3. Legendary Axe (600 Gold)");
        System.out.println("4. Back to Main Game");
        System.out.print("> ");
        int choice = scanner.nextInt();
        scanner.nextLine(); // consume newline

        switch (choice) {
            case 1:
                if (playerGold >= 50) {
                    playerGold -= 50;
                    inventory.add("Health Potion");
                    System.out.println("You bought a Health Potion!");
                } else {
                    System.out.println("You don't have enough gold.");
                }
                break;
            case 2:
                if (playerGold >= 500) {
                    playerGold -= 500;
                    weapons.put("Legendary Sword", 50);
                    System.out.println("You bought a Legendary Sword!");
                } else {
                    System.out.println("You don't have enough gold.");
                }
                break;
            case 3:
                if (playerGold >= 600) {
                    playerGold -= 600;
                    weapons.put("Legendary Axe", 60);
                    System.out.println("You bought a Legendary Axe!");
                } else {
                    System.out.println("You don't have enough gold.");
                }
                break;
            case 4:
                System.out.println("Returning to main game...");
                break;
            default:
                System.out.println("Invalid choice.");
        }
    }

    private static void showCheatMenu() {
        System.out.println("\nCheat Menu:");
        System.out.println("1. Add 1000 Gold");
        System.out.println("2. Set Health to 999");
        System.out.println("3. Set Damage to 999");
        System.out.println("4. Exit Cheat Menu");
        System.out.print("> ");
        int cheatChoice = scanner.nextInt();
        scanner.nextLine(); // consume newline

        switch (cheatChoice) {
            case 1:
                playerGold += 1000;
                System.out.println("You now have 1000 extra gold.");
                break;
            case 2:
                playerHealth = 999;
                System.out.println("You have full health now!");
                break;
            case 3:
                playerDamage = 999;
                System.out.println("You now deal maximum damage!");
                break;
            case 4:
                isCheatMenuActive = false;
                break;
            default:
                System.out.println("Invalid choice.");
        }
    }

    private static void playGambling() {
        System.out.println("\nWelcome to the Gambling Games!");
        System.out.println("1. Heads or Tails");
        System.out.println("2. Blackjack");
        System.out.println("3. Back to Main Menu");
        System.out.print("> ");
        int gamblingChoice = scanner.nextInt();
        scanner.nextLine(); // consume newline

        switch (gamblingChoice) {
            case 1:
                headsOrTails();
                break;
            case 2:
                blackjack();
                break;
            case 3:
                System.out.println("Returning to main menu...");
                break;
            default:
                System.out.println("Invalid choice. Try again.");
        }
    }

    private static void headsOrTails() {
        System.out.print("\nEnter the amount of gold you want to wager: ");
        int wager = scanner.nextInt();
        scanner.nextLine(); // consume newline

        if (wager > playerGold) {
            System.out.println("You don't have enough gold!");
            return;
        }

        System.out.println("Heads or Tails? Type 'Heads' or 'Tails'");
        String choice = scanner.nextLine().toLowerCase();

        if (!choice.equals("heads") && !choice.equals("tails")) {
            System.out.println("Invalid choice. You must choose 'Heads' or 'Tails'.");
            return;
        }

        Random rand = new Random();
        String result = rand.nextInt(2) == 0 ? "heads" : "tails";
        System.out.println("The coin landed on " + result + ".");

        if (choice.equals(result)) {
            playerGold += wager;
            System.out.println("You won! You now have " + playerGold + " gold.");
        } else {
            playerGold -= wager;
            System.out.println("You lost! You now have " + playerGold + " gold.");
        }
    }

    private static void blackjack() {
        System.out.print("\nEnter the amount of gold you want to wager: ");
        int wager = scanner.nextInt();
        scanner.nextLine(); // consume newline

        if (wager > playerGold) {
            System.out.println("You don't have enough gold!");
            return;
        }

        // Simplified Blackjack Logic
        Random rand = new Random();
        int playerScore = rand.nextInt(10) + 12; // Player score is between 12 and 21
        int dealerScore = rand.nextInt(10) + 12; // Dealer score is between 12 and 21

        System.out.println("Your score: " + playerScore);
        System.out.println("Dealer's score: " + dealerScore);

        if (playerScore > 21) {
            System.out.println("You busted! You lost your wager.");
            playerGold -= wager;
        } else if (dealerScore > 21 || playerScore > dealerScore) {
            System.out.println("You win! You gain " + wager + " gold.");
            playerGold += wager;
        } else if (playerScore < dealerScore) {
            System.out.println("You lose! You lost your wager.");
            playerGold -= wager;
        } else {
            System.out.println("It's a tie! No gold exchanged.");
        }
    }
}
