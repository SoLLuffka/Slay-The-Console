import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Player player = new Player(80, 80, 134, 3, 15);
        Enemy enemy = new Enemy(80, 80, 45, "Mask of legendary samurai", 25);

        // Dodajemy 6 kart, aby idealnie wypełnić Twoją nową planszę
        player.addCardToDeck(new AttackCard("Sword swing", 1, 4));
        player.addCardToDeck(new AttackCard("Sword thrust", 2, 6));
        player.addCardToDeck(new DefenseCard("Shield of Odin", 1, 6));
        player.addCardToDeck(new AttackCard("Medusa Head", 1, 12));
        player.addCardToDeck(new DefenseCard("Loki mirror", 3, 15));
        player.addCardToDeck(new AttackCard("Rusty Spear", 1, 5));

        Scanner scanner = new Scanner(System.in);
        int turnCounter = 1;

        while (player.getHp() > 0 && enemy.getHp() > 0) {

            player.setEnergy(3);
            boolean isPlayerTurn = true;

            while (isPlayerTurn && enemy.getHp() > 0) {
                // Generowanie ładnych napisów dla kart, dokładnie jak w Twoim planszaa.txt
                String c1 = player.getDeckSize() > 0 ? "1." + player.getCardFromDeck(0).getName() + " (-" + player.getCardFromDeck(0).getCost() + "E)" : "";
                String c2 = player.getDeckSize() > 1 ? "2." + player.getCardFromDeck(1).getName() + " (-" + player.getCardFromDeck(1).getCost() + "E)" : "";
                String c3 = player.getDeckSize() > 2 ? "3." + player.getCardFromDeck(2).getName() + " (-" + player.getCardFromDeck(2).getCost() + "E)" : "";
                String c4 = player.getDeckSize() > 3 ? "4." + player.getCardFromDeck(3).getName() + " (-" + player.getCardFromDeck(3).getCost() + "E)" : "";
                String c5 = player.getDeckSize() > 4 ? "5." + player.getCardFromDeck(4).getName() + " (-" + player.getCardFromDeck(4).getCost() + "E)" : "";
                String c6 = player.getDeckSize() > 5 ? "6." + player.getCardFromDeck(5).getName() + " (-" + player.getCardFromDeck(5).getCost() + "E)" : "";

                // === NOWA ZAAWANSOWANA PLANSZA (planszaa.txt) ===
                System.out.println("\n+---------------------------+--------------------------------------------------------------+---------------------------+");
                System.out.println("|   Player Status Window    |                                   Enemy Status Window                                    |");
                System.out.println("+---------------------------+--------------------------------------------------------------+------------+--------------+");
                System.out.printf("|  HP:%-8s |  Money:134$  | Enemy: %-53s |  HP:%-8s|   Block:%-4d |\n", player.getHp()+"/80", enemy.getName(), enemy.getHp()+"/80", enemy.getBlock());
                System.out.println("+------------+--------------+--------------------------------------------------------------+------------+--------------+");
                System.out.printf("|  Block:%-3d |  Energy:%-3s | Action menu:                                                 | Will: Attack -> %-4s  |\n", player.getBlock(), player.getEnergy()+"/3", enemy.getDamage()+"HP");
                System.out.println("+------------+--------------+--------------------------------------------------------------+---------------------------+");
                System.out.println("|                           |  1. Play card -> p <numer karty>                             |                           |");
                System.out.println("|                           |  2. Zbadaj karte -> h <numer karty>                          |                           |");
                System.out.println("|                           |  0. Zakoncz ture -> 0                                        |                           |");
                System.out.println("|                           |                                                              |                           |");
                System.out.println("|                           |                                                              |                           |");
                System.out.println("|                           +------------------------------------------------------------------------------------------+");
                System.out.println("|                           | Player cards:                                                                            |");
                System.out.println("+---------------------------+------------------------------------------------------------------------------------------+");

                System.out.printf("| Cards in hand:6           | %-43s %-40s |\n", c1, c4);
                System.out.println("| Cards to play:12          |                                                                                          |");
                System.out.printf("| Used cards:10             | %-43s %-40s |\n", c2, c5);
                System.out.println("+---------------------------|                                                                                          |");
                System.out.printf("| Magic command: ?help      | %-43s %-40s |\n", c3, c6);
                System.out.println("+---------------------------+------------------------------------------------------------------------------------------+");

                System.out.print("\nTwoja komenda (np. p 1, h 2, 0): ");

                try {
                    String input = scanner.nextLine().trim().toLowerCase(); // Pobranie tekstu, usunięcie spacji na końcach i zamiana na małe litery

                    if (input.equals("0")) {
                        isPlayerTurn = false;
                        System.out.println("---> Konczysz swoja ture.");
                    } else {
                        // Rozbijamy komendę na części używając spacji jako separatora (np. ["p", "1"])
                        String[] commandParts = input.split(" ");

                        // Sprawdzamy, czy gracz faktycznie podał dwa elementy (literę i cyfrę)
                        if (commandParts.length != 2) {
                            throw new IllegalArgumentException("Niepoprawny format komendy! Użyj formatu: litera spacja cyfra.");
                        }

                        String action = commandParts[0]; // To będzie nasze "p" lub "h"
                        int choice = Integer.parseInt(commandParts[1]); // To będzie numer karty

                        if (choice > 0 && choice <= player.getDeckSize()) {
                            Card selectedCard = player.getCardFromDeck(choice - 1);

                            // Logika obsługi akcji (Graj vs Zbadaj)
                            if (action.equals("p")) {
                                System.out.println("\n[ZAGRYWASZ KARTĘ: " + selectedCard.getName() + "]");
                                selectedCard.use(player, enemy);
                            } else if (action.equals("h")) {
                                selectedCard.printHelp();
                            } else {
                                System.out.println("\n[BŁĄD] Nieznana akcja! Użyj 'p' aby zagrać lub 'h' aby zbadać.");
                            }

                            // Zatrzymanie ekranu po akcji
                            System.out.println("\n(Wciśnij ENTER, aby kontynuować...)");
                            scanner.nextLine();
                        } else {
                            System.out.println("\n[BŁĄD] Nie ma karty o takim numerze!");
                            System.out.println("(Wciśnij ENTER...)");
                            scanner.nextLine();
                        }
                    }
                } catch (NumberFormatException e) {
                    System.out.println("\n[BŁĄD] Drugi człon komendy musi być cyfrą! (np. p 1)");
                    System.out.println("(Wciśnij ENTER...)");
                    scanner.nextLine();
                } catch (IllegalArgumentException e) {
                    System.out.println("\n[BŁĄD] " + e.getMessage());
                    System.out.println("(Wciśnij ENTER...)");
                    scanner.nextLine();
                } catch (Exception e) {
                    System.out.println("\n[BŁĄD NIEZNANY] " + e.getMessage());
                    scanner.nextLine();
                }
            }

            // Tura przeciwnika
            if (enemy.getHp() > 0) {
                System.out.println("\n=================================================");
                System.out.println("=== TURA PRZECIWNIKA: " + enemy.getName().toUpperCase() + " ===");
                System.out.println("=================================================");

                player.takeDamage(enemy.getDamage());
                player.resetBlockOnTurnEnd();

                turnCounter++;
                System.out.println("\n(Wcisnij ENTER, aby rozpoczac Runde " + turnCounter + "...)");
                scanner.nextLine();
            }
        }

        // Koniec gry
        System.out.println("\n=================================================");
        System.out.println("                KONIEC GRY                      ");
        System.out.println("=================================================");
        if (player.getHp() <= 0) {
            System.out.println("Przegrales! " + enemy.getName() + " okazal sie silniejszy.");
        } else {
            System.out.println("GRATULACJE! Pokonales bosa!");
        }
        scanner.close();
    }
}