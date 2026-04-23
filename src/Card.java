public abstract class Card {
    protected String name;
    protected int cost;

    public Card(String name, int cost) {
        this.name = name;
        this.cost = cost;
    }

    public abstract void use(Player p, Enemy e);

    public String getName() { return this.name; }
}
