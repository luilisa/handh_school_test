import java.util.Arrays;

public class Player extends Entity{
    int maxHP;
    int healPots;

    public Player(String name, int attackPoints, int defensePoints, int healthPoints, int minDamage, int maxDamage) throws EntityException {
        super(name, attackPoints, defensePoints, healthPoints, minDamage, maxDamage);
        maxHP = healthPoints;
        healPots = 4;
    }

    public void heal() {
        if (healPots > 0) {
            if (healthPoints < maxHP) {
                healPots -= 1;
                if (healthPoints <= maxHP * 0.7) {
                    healthPoints += maxHP * 0.3;
                } else {
                    healthPoints = maxHP;
                }
                System.out.println("\nИгрок восстановил здоровье. Текущее значение: " + healthPoints);
            }
            else {
                System.out.println("\nУ игрока достаточно здоровья! Текущее значение: " + healthPoints);
            }
        }
        else {
            System.out.println("\nИсцеляющие зелья закончились!");
        }
    }

    @Override
    public String toString() {
        return "Player with name=" + name +
                ", attackPoints=" + attackPoints +
                ", defensePoints=" + defensePoints +
                ", healthPoints=" + healthPoints +
                ", damage=" + Arrays.toString(damage) +
                ", maxHP=" + maxHP +
                ", healPots=" + healPots +
                ", isAlive=" + isAlive +
                '}';
    }
}
