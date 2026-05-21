public class DefenseCard extends Card {
    private int block;

    public DefenseCard(String name, int cost, int block) {
        super(name, cost);
        this.block = block;
    }

    @Override
    public void use(Player p, Enemy e) {
        if(p.energy >= this.cost) {
            p.getBlock(this.block);
            p.energy -= this.cost;
        } else System.out.println("Gracz posiada za malo energi aby uzyc tej karty.");
    }

    @Override
    public void printHelp() {
        System.out.println("[ZBADANIE] Karta: " + this.name + " | Koszt: " + this.cost + "E | Typ: Obrona | Efekt: Dodaje " + this.block + " punktów bloku.");
    }
}
