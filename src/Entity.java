public abstract class Entity {
    protected int hp;
    protected int maxHp;
    protected int block;

    public Entity(int hp, int maxHp, int block) {
        this.hp = hp;
        this.maxHp = maxHp;
        this.block = block;
    }

    public void takeDamage(int amount) {
        try {
            int damageToHp = Math.max(0, amount - this.block); // Obliczanie ile HP straci gracz wliczajac w to punkty bloku
            this.block = Math.max(0, this.block - amount); // Obliczanie koncowych punktow bloku

            this.hp -= damageToHp; // Zadawanie obrazen

            if (this.hp <= 0) {
                this.hp = 0;
                throw new Exception("Gracz nie żyje!");
            }
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        } finally {
            System.out.println("Po skończonej turze graczu zostało: " + this.hp + "HP oraz " + this.block + " punktów bloku.");
        }
    }

    public void getBlock(int amount) {
        this.block += amount;
    }
}
