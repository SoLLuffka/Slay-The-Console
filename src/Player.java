import java.util.ArrayList;
import  java.util.List;

 class Player extends Entity {
    private int money;
    protected int energy;

    public Player(int hp, int maxHp, int money, int energy, int block) {
        super(hp, maxHp, block);
        this.money = money;
        this.energy = energy;

        this.deck = new ArrayList<>();
    }

    private List<Card> deck;
}
