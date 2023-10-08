public class Demo {

    public static void main(String[] args) {

        try {
            Monster boar = new Monster("Кабанчик", 1, 1, 10, 1, 5);
            Monster dragon = new Monster("Дракон",30, 30, 200, 30, 50);
            Player player = new Player("Игрок",20, 20, 100, 10, 16);
            Player player2 = new Player("Игрок 2",30, 30, 100, 50, 60);

            System.out.println(boar.toString());
            System.out.println(dragon.toString());
            System.out.println(player.toString());
            System.out.println(player2.toString());
            System.out.println();

            //игрок убивает кабанчика
            while (boar.isAlive && player.isAlive) {
                player.attackEntity(boar);
                boar.attackEntity(player);
            }


            //дракон убивает игрока
            while(dragon.isAlive && player.isAlive) {
                if (player.healthPoints < 70) {
                    player.heal();
                }
                player.attackEntity(dragon);
                dragon.attackEntity(player);
            }

            while(dragon.isAlive && player2.isAlive) {
                if (player2.healthPoints < 70) {
                    player2.heal();
                }
                player2.attackEntity(dragon);
                dragon.attackEntity(player2);
            }

            System.out.println();
            System.out.println(boar.toString());
            System.out.println(dragon.toString());
            System.out.println(player.toString());
            System.out.println(player2.toString());
        }

        catch (Exception exception) {
            System.out.println(exception.toString());
        }

    }
}
