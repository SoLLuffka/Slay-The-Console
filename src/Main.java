public class Main {
    public static void main(String[] args) {
        Player player = new Player(80, 80, 0, 3, 5);
        Enemy enemy = new Enemy(50, 50, 15, "Mask of Legendary Samurai", 15);

        player.takeDamage(8);

        Card c = new AttackCard("Uderzenie mieczem", 1, 6);
        c.use(player, enemy);
    }
}