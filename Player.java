import javax.swing.JOptionPane;
import java.awt.*;
import java.util.*;

public class Player {
    public int balance;
    public ArrayList<Property> properties = new ArrayList<>();
    public Block currentBlock;
    public int outOfJailCards;
    public int numOfDoublesInARow;

    private int playerID;
    public int getPlayerID() {
        return playerID;
    }

    public int getBalance(){
        return balance;
    }

    public Player(int balance, Block currentBlock, int playerID){
        this.balance = balance;
        this.currentBlock = currentBlock;
        this.playerID = playerID;
    }
    public void exitPrisonWithDice() {
        Jail.playersInJail.remove(this);
    }

    public void exitPrisonWithCard() {
        Jail.playersInJail.remove(this);
        this.outOfJailCards--;
    }

    public void exitPrisonWithFine() {
        int fine = 100;
        Jail.playersInJail.remove(this);
        this.balance -= fine;
    }

    public int[] rollTheDice() {
        int[] dice = new int[2];
        dice[0] = new Random().nextInt(6) + 1;
        dice[1] = new Random().nextInt(6) + 1;

        return dice;
    }

    public void BuyProperty() {

        if(currentBlock instanceof Property prop){
            prop.setOwner(this);
            this.balance -= prop.cost;
        }

    }

    public void MortgageProperty(Property property) {
        if (property instanceof Room) {
            //+check if it has rooms then notify about error
        } else {
            this.balance += property.mortgageValue;
            properties.remove(property);
        }
    }

    public  void receiveStartPayment() {
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

    public void payRent(Player owner, int rent){
        this.balance -= rent;
        owner.balance += rent;
        JOptionPane.showMessageDialog(null, "Πλήρωσες ενοίκιο " + rent  + "€ στον Παίκτη " + owner.getPlayerID() + ".\n", "Πλήρωσες Ενοίκιο", JOptionPane.INFORMATION_MESSAGE);
    }

}