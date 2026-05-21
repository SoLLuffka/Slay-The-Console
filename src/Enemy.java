public class Enemy extends Entity {
    private String name;
    private int damage;

    public Enemy(int hp, int maxHp, int block, String name, int damage) {
        super(hp, maxHp, block);
        this.name = name;
        this.damage = damage;
    }
}
