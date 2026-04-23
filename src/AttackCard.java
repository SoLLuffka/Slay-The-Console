public class AttackCard extends Card {
    private int damage;

    public AttackCard(String name, int cost, int damage) {
        super(name, cost);
        this.damage = damage;
    }

    @Override
    public void use(Player p, Enemy e) {
        if(p.energy >= this.cost) {
            e.takeDamage(this.damage);
            p.energy -= this.cost;
        } else System.out.println("Gracz posiada za malo energi aby uzyc tej karty.");
    }
}
