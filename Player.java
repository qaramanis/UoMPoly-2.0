import java.awt.*;
import java.util.*;

public class Player {
    public int balance;
    public ArrayList<Property> properties = new ArrayList<>();
    public Block currentBlock;
    private int outOfJailCards;
    public int numOfDoublesInARow;

    public void exitPrisonWithDice() {
        Jail.playersInJail.remove(GameBoard.currentPlayer);
    }

    public void exitPrisonWithCard() {
        Jail.playersInJail.remove(GameBoard.currentPlayer);
        this.outOfJailCards--;
    }

    public void exitPrisonWithFine() {
        int fine = 100;
        Jail.playersInJail.remove(GameBoard.currentPlayer);
        this.balance -= fine;
    }

    public int[] rollTheDice() {
        int[] dice = new int[2];
        dice[0] = (int) (Math.random() * 6) + 1;
        dice[1] = (int) (Math.random() * 6) + 1;
        return dice;
    }

    public void BuyProperty(Property property) {
        this.properties.add(property);
        this.balance -= property.cost;
    }

    public void MortgageProperty(Property property) {
        if (property instanceof Room) {
            //+check if it has rooms then notify about error
        } else {
            this.balance += property.mortgageValue;
            properties.remove(property);
        }
    }

    public void receiveStartPayment(Player player) {
        int startPayment = 100;
        this.balance += startPayment;
    }

    public boolean ownsProperty(Block propertyBlock) {
        boolean result = false;
        for (Property p : this.properties) {
            if (p.equals(propertyBlock)) {
                result = true;
                break;
            }
        }
        return result;
    }

}