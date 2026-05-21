import java.util.Random;
public class Enemy extends Entity {
    private String name;
    private int baseDamage;
    private String nextMoveType;
    private int nextMoveValue;
    private Random random;

    public Enemy(int hp, int maxHp, int block, String name, int damage) {
        super(hp, maxHp, block);
        this.name = name;
        this.baseDamage = damage;
        this.random = new Random();

        this.prepareNextMove();
    }

    public void prepareNextMove() {
        int roll = random.nextInt(100);

        if (roll < 60) {
            // 60% szans na Atak
            this.nextMoveType = "Attack";
            // Obrażenia to bazowe obrażenia +/- losowa odchyłka (np. od -5 do +5)
            this.nextMoveValue = random.nextInt(14) + 12; // <12 - 25>
        } else {
            // 40% szans na Obronę
            this.nextMoveType = "Defend";
            // Przeciwnik nałoży na siebie od 5 do 15 punktów pancerza
            this.nextMoveValue = random.nextInt(11) + 5; // <5 - 15>
        }
    }

    public void executeMove(Player player) {
        if (this.nextMoveType.equals("Attack")) {
            System.out.println("Przeciwnik atakuje za " + this.nextMoveValue + " obrażeń!");
            player.takeDamage(this.nextMoveValue);
        } else if (this.nextMoveType.equals("Defend")) {
            System.out.println("Przeciwnik przyjmuje pozycję obronną, zyskując " + this.nextMoveValue + " bloku!");
            this.getBlock(this.nextMoveValue);
        }
    }

    public String getIntention() {
        if (this.nextMoveType.equals("Attack")) {
            return "Attack -> " + this.nextMoveValue + "HP";
        } else {
            return "Defend -> " + this.nextMoveValue + "BLK";
        }
    }

    public String getName() {
        return this.name;
    }

    public void resetBlockOnTurnEnd() {
        this.block = 0;
    }
}
