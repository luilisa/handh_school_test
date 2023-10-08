import java.util.Arrays;
import java.util.Random;
import java.util.stream.IntStream;

abstract class Entity {
    String name;
    int attackPoints;
    int defensePoints;
    int healthPoints;
    int[] damage;
    boolean isAlive;

    public Entity(String name, int attackPoints, int defensePoints,
                  int healthPoints, int minDamage, int maxDamage) throws EntityException {
        if ((1 <= attackPoints && 30 >= attackPoints)
                && (1 <= defensePoints && 30 >= defensePoints)
                && (healthPoints > 0) && (minDamage > 0)
                && (maxDamage > 0) && (minDamage < maxDamage)) {
            this.name = name;
            this.attackPoints = attackPoints;
            this.defensePoints = defensePoints;
            this.healthPoints = healthPoints;
            this.damage = IntStream.range(minDamage, maxDamage + 1).toArray();
            this.isAlive = true;
        }
        else {
            throw new EntityException();
        }
    }

    public int attackModifier(Entity entity) {
        System.out.println("Модификатор атаки: " + (this.attackPoints - entity.defensePoints + 1));
        return this.attackPoints - entity.defensePoints + 1;
    }

    public boolean diceRoll(int n) {
        Random random = new Random();
        boolean success = false;
        int number;
        int i = 0;
        if (n < 1) {
            number = random.nextInt(6) + 1;
            System.out.println("Брошенный в " + (i + 1) + " раз кубик. Выпало число: " + number);
            if ( number == 5 || number == 6) {
                success = true;
            }
        }
        else {
            while (!success && i < n) {
                number = random.nextInt(6) + 1;
                System.out.println("Брошенный в " + (i + 1) + " раз кубик. Выпало число: " + number);
                if (number == 5 || number == 6) {
                    success = true;
                }
                i++;
            }
        }
        System.out.println("Выпало 5 или 6: " + success);
        if (!success) {
            System.out.println("Атака прошла мимо!");
        }
        return success;
    }

    public void attackEntity(Entity entity) {
        if (entity.isAlive && this.isAlive) {
            System.out.println("\n" + this.name + " проводит атаку против " + entity.name);
            int attackModifier = attackModifier(entity);
            boolean success = diceRoll(attackModifier);
            if (success) {
                Random random = new Random();
                int index = random.nextInt(this.damage.length);
                System.out.println("Количество урона: " + this.damage[index]);
                entity.healthPoints -= this.damage[index];

                if (entity.healthPoints <= 0) {
                    entity.isAlive = false;
                    entity.healthPoints = 0;
                    System.out.println("Персонаж " + entity.name + " побежден!");
                }
                else {
                    System.out.println("Количество оставшегося у " + entity.name + " здоровья: " + entity.healthPoints);
                }
            }

        } else {
            if (!entity.isAlive) {
                System.out.println("Персонаж " + entity.name + " мертв!");
            }
            if (!this.isAlive) {
                System.out.println("Персонаж " + this.name + " мертв!");
            }
        }

    }

    @Override
    public String toString() {
        return "Entity with name=" + name +
                ", attackPoints=" + attackPoints +
                ", defensePoints=" + defensePoints +
                ", healthPoints=" + healthPoints +
                ", damage=" + Arrays.toString(damage) +
                ", isAlive=" + isAlive +
                '}';
    }
}
