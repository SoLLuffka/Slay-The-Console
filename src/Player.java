import java.util.ArrayList;
import  java.util.List;

 class Player extends Entity {
    private int money;
    protected int energy;
    private List<Card> deck;
    public Player(int hp, int maxHp, int money, int energy, int block) {
        super(hp, maxHp, block);
        this.money = money;
        this.energy = energy;

        this.deck = new ArrayList<>();
    }

    public void addCardToDeck(Card card) {
        this.deck.add(card);
    }

    public void showDeck() {
        for (int i = 0; i < this.deck.size(); i++) {
            System.out.println((i + 1) + ". " + this.deck.get(i).getName() + " (Koszt energii: " + this.deck.get(i).getCost() + ")");
        }
    }

    // Zarządzanie energią
    public int getEnergy() {
        return this.energy;
    }
    public void setEnergy(int energy) {
        this.energy = energy;
    }

    // Rozmiar talii (potrzebne, by wiedzieć, ile gracz ma opcji do wyboru)
    public int getDeckSize() {
        return this.deck.size();
    }

    // Wyciąganie konkretnej karty z listy po numerze (indeksie)
    public Card getCardFromDeck(int index) {
        if (index >= 0 && index < this.deck.size()) {
            return this.deck.get(index);
        }
        return null; // Zwraca null (nic), jeśli ktoś wpisze zły numer
    }

    // Zerowanie bloku na koniec tury (mechanika Slay the Spire)
    public void resetBlockOnTurnEnd() {
        this.block = 0;
    }
}
