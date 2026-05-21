import java.util.ArrayList;
import java.util.Collections;
import  java.util.List;

 class Player extends Entity {
    private int money;
    protected int energy;
     private List<Card> drawPile;
     private List<Card> hand;
     private List<Card> discardPile;
    public Player(int hp, int maxHp, int money, int energy, int block) {
        super(hp, maxHp, block);
        this.money = money;
        this.energy = energy;

        this.drawPile = new ArrayList<>();
        this.hand = new ArrayList<>();
        this.discardPile = new ArrayList<>();
    }

    public void addCardToDeck(Card card) {
        this.drawPile.add(card);
    }

    public void shuffleDeck() {
        Collections.shuffle(this.drawPile);
        System.out.println("[SYSTEM] Talia startowa została przetasowana!");
    }

    public void drawCards(int amount) {
        for (int i = 0; i < amount; i++) {
            // Jeśli stos dobierania jest pusty, przetasuj odrzucone z powrotem
            if (this.drawPile.isEmpty()) {
                if (this.discardPile.isEmpty()) {
                    break; // Nie ma czego dobierać (np. wyczerpano wszystkie karty)
                }
                this.drawPile.addAll(this.discardPile);
                this.discardPile.clear();
                Collections.shuffle(this.drawPile); // Tasowanie!
                System.out.println("\n[SYSTEM] Przetasowano stos odrzuconych do stosu dobierania!");
            }

            // Pobierz pierwszą kartę z góry stosu i przenieś na rękę
            Card drawnCard = this.drawPile.remove(0);
            this.hand.add(drawnCard);
        }
    }

    // Zrzucanie całej ręki na koniec tury (jak w oryginalnej grze)
    public void discardHand() {
        this.discardPile.addAll(this.hand);
        this.hand.clear();
    }

    // Przeniesienie konkretnej zagranej karty na stos odrzuconych
    public void moveCardToDiscard(Card card) {
        this.hand.remove(card);
        this.discardPile.add(card);
    }

    // Zarządzanie energią
    public int getEnergy() { return this.energy; }
    public void setEnergy(int energy) { this.energy = energy; }
    public int getHandSize() { return this.hand.size(); }
    public int getDrawPileSize() { return this.drawPile.size(); }
    public int getDiscardPileSize() { return this.discardPile.size(); }

    // Wyciąganie konkretnej karty z listy po numerze (indeksie)
    public Card getCardFromHand(int index) {
        if (index >= 0 && index < this.hand.size()) {
            return this.hand.get(index);
        }
        return null;
    }

    // Zerowanie bloku na koniec tury (mechanika Slay the Spire)
    public void resetBlockOnTurnEnd() {
        this.block = 0;
    }
}
