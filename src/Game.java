import java.util.Scanner;
public class Game {
    public void start(Scanner scanner) {
        Player player = new Player(80, 80, 134, 3, 15);
        Enemy enemy = new Enemy(80, 80, 15, "Mask of legendary samurai", 25);

        // Inicjalizacja talii 12 kart
        player.addCardToDeck(new AttackCard("Sword swing", 1, 4));
        player.addCardToDeck(new AttackCard("Sword thrust", 2, 6));
        player.addCardToDeck(new DefenseCard("Shield of Odin", 1, 6));
        player.addCardToDeck(new AttackCard("Medusa Head", 1, 12));
        player.addCardToDeck(new DefenseCard("Loki mirror", 3, 15));
        player.addCardToDeck(new AttackCard("Rusty Spear", 1, 5));
        player.addCardToDeck(new AttackCard("Dagger Throw", 0, 3));
        player.addCardToDeck(new DefenseCard("Iron Armor", 1, 5));
        player.addCardToDeck(new AttackCard("Heavy Strike", 3, 20));
        player.addCardToDeck(new DefenseCard("Dodge", 1, 4));
        player.addCardToDeck(new AttackCard("Quick Slash", 0, 2));
        player.addCardToDeck(new DefenseCard("Wall of Stone", 2, 10));

        player.shuffleDeck();
        int turnCounter = 1;

        // GŁÓWNA PĘTLA ROZGRYWKI (przeniesiona z Main)
        while (player.getHp() > 0 && enemy.getHp() > 0) {
            player.setEnergy(3);
            boolean isPlayerTurn = true;
            player.drawCards(6);

            while (isPlayerTurn && enemy.getHp() > 0) {
                String c1 = player.getHandSize() > 0 ? "1." + player.getCardFromHand(0).getName() + " (-" + player.getCardFromHand(0).getCost() + "E)" : "";
                String c2 = player.getHandSize() > 1 ? "2." + player.getCardFromHand(1).getName() + " (-" + player.getCardFromHand(1).getCost() + "E)" : "";
                String c3 = player.getHandSize() > 2 ? "3." + player.getCardFromHand(2).getName() + " (-" + player.getCardFromHand(2).getCost() + "E)" : "";
                String c4 = player.getHandSize() > 3 ? "4." + player.getCardFromHand(3).getName() + " (-" + player.getCardFromHand(3).getCost() + "E)" : "";
                String c5 = player.getHandSize() > 4 ? "5." + player.getCardFromHand(4).getName() + " (-" + player.getCardFromHand(4).getCost() + "E)" : "";
                String c6 = player.getHandSize() > 5 ? "6." + player.getCardFromHand(5).getName() + " (-" + player.getCardFromHand(5).getCost() + "E)" : "";

                System.out.println("\n+---------------------------+--------------------------------------------------------------+---------------------------+");
                System.out.println("|   Player Status Window    |                                   Enemy Status Window                                    |");
                System.out.println("+---------------------------+--------------------------------------------------------------+------------+--------------+");
                System.out.printf("|  HP:%-8s |  Money:00$ | Enemy: %-53s | HP:%-8s|   Block:%-4d |\n", player.getHp()+"/80", enemy.getName(), enemy.getHp()+"/80", enemy.getBlock());
                System.out.println("+------------+--------------+--------------------------------------------------------------+------------+--------------+");
                System.out.printf("|  Block:%-3d |  Energy:%-3s  | Action menu:                                                 | Will: %-18s  |\n", player.getBlock(), player.getEnergy()+"/3", enemy.getIntention());
                System.out.println("+------------+--------------+--------------------------------------------------------------+---------------------------+");
                System.out.println("|                           |  1. Play card -> p <numer karty>                             |                           |");
                System.out.println("|                           |  2. Zbadaj karte -> h <numer karty>                          |                           |");
                System.out.println("|                           |  0. Zakoncz ture -> 0                                        |                           |");
                System.out.println("|                           |                                                              |                           |");
                System.out.println("|                           |                                                              |                           |");
                System.out.println("|                           +------------------------------------------------------------------------------------------+");
                System.out.println("|                           | Player cards:                                                                            |");
                System.out.println("+---------------------------+------------------------------------------------------------------------------------------+");

                System.out.printf("| Cards in hand:%-12d| %-43s %-40s |\n", player.getHandSize(), c1, c4);
                System.out.printf("| Cards to play:%-12d|                                                                                          |\n", player.getDrawPileSize());
                System.out.printf("| Used cards:%-15d| %-43s %-40s |\n", player.getDiscardPileSize(), c2, c5);
                System.out.println("+---------------------------|                                                                                          |");
                System.out.printf("| Magic commands: p & h     | %-43s %-40s |\n", c3, c6);
                System.out.println("+---------------------------+------------------------------------------------------------------------------------------+");

                System.out.print("\nTwoja komenda (np. p 1, h 2, 0): ");

                try {
                    String input = scanner.nextLine().trim().toLowerCase();

                    if (input.equals("0")) {
                        isPlayerTurn = false;
                        System.out.println("---> Konczysz swoja ture.");
                        player.discardHand();
                    } else {
                        String[] commandParts = input.split(" ");

                        if (commandParts.length != 2) {
                            throw new IllegalArgumentException("Niepoprawny format komendy! Uzyj formatu: litera spacja cyfra.");
                        }

                        String action = commandParts[0];
                        int choice = Integer.parseInt(commandParts[1]);

                        if (choice > 0 && choice <= player.getHandSize()) {
                            Card selectedCard = player.getCardFromHand(choice - 1);

                            if (action.equals("p")) {
                                if (player.getEnergy() >= selectedCard.getCost()) {
                                    System.out.println("\n[ZAGRYWASZ KARTE: " + selectedCard.getName() + "]");
                                    selectedCard.use(player, enemy);
                                    player.moveCardToDiscard(selectedCard);
                                } else {
                                    System.out.println("\n[BLAD] Za malo energii na te karte!");
                                }
                            } else if (action.equals("h")) {
                                selectedCard.printHelp();
                            } else {
                                System.out.println("\n[BLAD] Nieznana akcja! Uzyj 'p' aby zagrac lub 'h' aby zbadac.");
                            }

                            System.out.println("\n(Wcisnij ENTER, aby kontynuowac...)");
                            scanner.nextLine();
                        } else {
                            System.out.println("\n[BLAD] Nie ma karty o takim numerze na rece!");
                            System.out.println("(Wcisnij ENTER...)");
                            scanner.nextLine();
                        }
                    }
                } catch (NumberFormatException e) {
                    System.out.println("\n[BLAD] Drugi czlon komendy musi byc cyfra! (np. p 1)");
                    System.out.println("(Wcisnij ENTER...)");
                    scanner.nextLine();
                } catch (IllegalArgumentException e) {
                    System.out.println("\n[BLAD] " + e.getMessage());
                    System.out.println("(Wcisnij ENTER...)");
                    scanner.nextLine();
                } catch (Exception e) {
                    System.out.println("\n[BLAD NIEZNANY] " + e.getMessage());
                    scanner.nextLine();
                }
            }

            if (enemy.getHp() > 0) {
                System.out.println("\n=================================================");
                System.out.println("=== TURA PRZECIWNIKA: " + enemy.getName().toUpperCase() + " ===");
                System.out.println("=================================================");

                enemy.executeMove(player);
                player.resetBlockOnTurnEnd();
                enemy.prepareNextMove();

                turnCounter++;
                System.out.println("\n(Wcisnij ENTER, aby rozpoczac Runde " + turnCounter + "...)");
                scanner.nextLine();
            }
        }

        System.out.println("\n=================================================");
        System.out.println("                KONIEC GRY                      ");
        System.out.println("=================================================");
        if (player.getHp() <= 0) {
            System.out.println("Przegrales! " + enemy.getName() + " okazal sie silniejszy.");
        } else {
            System.out.println("GRATULACJE! Pokonales "+ enemy.getName() + "!");
        }

        System.out.println("\n(Wcisnij ENTER, aby powrocic do Menu Glownego...)");
        scanner.nextLine();
    }
}
