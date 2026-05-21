import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        boolean running = true;

        // Pętla menu głównego - działa dopóki gracz nie wybierze opcji Zamknij
        while (running) {
            System.out.println("\n=================================================");
            System.out.println("        SLAY THE CONSOLE - MENU GLOWNE           ");
            System.out.println("=================================================");
            System.out.println("1. Graj");
            System.out.println("2. Credits");
            System.out.println("3. Zamknij");
            System.out.println("=================================================");
            System.out.print("Wybierz opcje (1-3): ");

            String choice = scanner.nextLine().trim();

            // Używamy instrukcji switch z prezentacji z wykładów
            switch (choice) {
                case "1":
                    // Tworzymy obiekt nowej klasy i odpalamy grę
                    Game game = new Game();
                    game.start(scanner);
                    break;
                case "2":
                    // Wyświetlenie sekcji Credits
                    System.out.println("\n=================================================");
                    System.out.println("                    CREDITS                      ");
                    System.out.println("=================================================");
                    System.out.println("Autor projektu: [Wpisz tutaj swoje imię i nazwisko]");
                    System.out.println("Kierunek: Informatyka / Studia");
                    System.out.println("Rok akademicki: 2025/2026");
                    System.out.println("Wsparcie deweloperskie: AI Sidekick");
                    System.out.println("=================================================");
                    System.out.println("(Wcisnij ENTER, aby powrocic do menu...)");
                    scanner.nextLine();
                    break;
                case "3":
                    // Zamknięcie gry
                    System.out.println("\nDziekujemy za gre! Zamykanie aplikacji...");
                    running = false;
                    break;
                default:
                    System.out.println("\n[BLAD] Niepoprawna opcja! Wybierz 1, 2 lub 3.");
                    System.out.println("(Wcisnij ENTER...)");
                    scanner.nextLine();
                    break;
            }
        }

        scanner.close(); // Całkowite zamknięcie skanera na koniec programu
    }
}