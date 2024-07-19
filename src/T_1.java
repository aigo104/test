import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

class Property {
    String name;
    int price;
    Player owner;

    public Property(String name, int price) {
        this.name = name;
        this.price = price;
        this.owner = null;
    }
}

class Player {
    String name;
    int position;
    int money;

    public Player(String name, int money) {
        this.name = name;
        this.position = 0;
        this.money = money;
    }

    public void move(int steps, int boardSize) {
        this.position = (this.position + steps) % boardSize;
    }

    public void buyProperty(Property property) {
        if (this.money >= property.price) {
            this.money -= property.price;
            property.owner = this;
            System.out.println(this.name + " bought " + property.name);
        } else {
            System.out.println(this.name + " doesn't have enough money to buy " + property.name);
        }
    }

    public void payRent(Property property) {
        if (property.owner != null && property.owner != this) {
            int rent = property.price / 10;
            this.money -= rent;
            property.owner.money += rent;
            System.out.println(this.name + " paid " + rent + " rent to " + property.owner.name);
        }
    }
}

public class T_1 {
    static ArrayList<Property> board = new ArrayList<>();
    static ArrayList<Player> players = new ArrayList<>();
    static Random random = new Random();
    static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        initializeBoard();
        initializePlayers();

        while (true) {
            for (Player player : players) {
                takeTurn(player);
                if (player.money <= 0) {
                    System.out.println(player.name + " is bankrupt!");
                    return;
                }
            }
        }
    }

    public static void initializeBoard() {
        board.add(new Property("Start", 0));
        board.add(new Property("Mediterranean Avenue", 60));
        board.add(new Property("Baltic Avenue", 60));
        board.add(new Property("Oriental Avenue", 100));
        board.add(new Property("Vermont Avenue", 100));
        board.add(new Property("Connecticut Avenue", 120));
        // 添加更多地块...
    }

    public static void initializePlayers() {
        System.out.print("Enter number of players: ");
        int numPlayers = scanner.nextInt();
        scanner.nextLine(); // 消耗换行符

        for (int i = 1; i <= numPlayers; i++) {
            System.out.print("Enter name for player " + i + ": ");
            String name = scanner.nextLine();
            players.add(new Player(name, 1500));
        }
    }

    public static void takeTurn(Player player) {
        System.out.println(player.name + "'s turn. Press Enter to roll the dice...");
        scanner.nextLine();
        int diceRoll = random.nextInt(6) + 1;
        System.out.println(player.name + " rolled a " + diceRoll);
        player.move(diceRoll, board.size());

        Property landedProperty = board.get(player.position);
        System.out.println(player.name + " landed on " + landedProperty.name);

        if (landedProperty.owner == null) {
            System.out.println(landedProperty.name + " is available for purchase for " + landedProperty.price);
            System.out.print("Do you want to buy it? (yes/no): ");
            String response = scanner.nextLine();
            if (response.equalsIgnoreCase("yes")) {
                player.buyProperty(landedProperty);
            }
        } else if (landedProperty.owner != player) {
            player.payRent(landedProperty);
        }
    }
}
