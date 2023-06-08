import javax.swing.JOptionPane;
import java.awt.*;
import java.util.*;

public class Player {
    private int balance;
    private ArrayList<Property> properties = new ArrayList<>();
    private Block currentBlock;
    private int outOfJailCards;
    private int numOfDoublesInARow;
    private int turnsInJail;
    private int playerID;

    public Player(int balance, Block currentBlock, int playerID){
        this.balance = balance;
        this.currentBlock = currentBlock;
        this.playerID = playerID;
    }

    public int[] rollTheDice() {
        int[] dice = new int[2];
        dice[0] = new Random().nextInt(6) + 1;
        dice[1] = new Random().nextInt(6) + 1;

        return dice;
    }

    public void BuyProperty() {
        if(currentBlock instanceof Property prop){
            if (balance >= prop.getCost()) {
                decreaseBalance(prop.getCost());
                prop.setOwner(this);
            } else {
                JOptionPane.showMessageDialog(null, "Τα λεφτά σου δεν επαρκούν για να αγοράσεις την ιδιοκτησία!", "Τράπεζα", JOptionPane.INFORMATION_MESSAGE);
            }
        }
    }

    public void payRent(Player owner, int rent){
        if (this.balance >= rent) {
            this.balance -= rent;
            owner.balance += rent;
            JOptionPane.showMessageDialog(null, "Πλήρωσες ενοίκιο " + rent  + "€ στον Παίκτη " + owner.getPlayerID() + ".\n", "Πλήρωσες Ενοίκιο", JOptionPane.INFORMATION_MESSAGE);
        } else {
            GameBoard.players.remove(this);
            JOptionPane.showMessageDialog(null, "Τα λεφτά σου δεν επαρκούν για να πληρώσεις το ενοίκιο!\n Βγαίνεις από το παιχνίδι.", "Xρεωκοπία! :(", JOptionPane.INFORMATION_MESSAGE);
            GameBoard.setTurnActive(false);
        }
    }

    public int getPlayerID() {
        return playerID;
    }

    public int getBalance() {
        return balance;
    }

    public void increaseBalance(int val) {
       balance += val;
    }

    public void decreaseBalance(int val) {
        balance -= val;
    }

    public ArrayList<Property> getProperties(){
        return properties;
    }

    public void addProperty(Property property){
        properties.add(property);
    }

    public Block getCurrentBlock(){
        return currentBlock;
    }

    public void setCurrentBlock(Block block){
        currentBlock = block;
    }

    public int getOutOfJailCards() {
        return outOfJailCards;
    }

    public void increaseGetOutOfJailCards() {
        outOfJailCards++;
    }

    public void decreaseGetOutOfJailCards() {
        outOfJailCards--;
    }

    public int getNumOfDoublesInARow() {
        return numOfDoublesInARow;
    }

    public void increaseNumOfDoublesInARow() {
        numOfDoublesInARow++;
    }

    public void resetNumOfDoublesInARow() {
        numOfDoublesInARow = 0;
    }

    public int getTurnsInJail() {
        return turnsInJail;
    }

    public void increaseTurnsInJail() {
        turnsInJail++;
    }

    public void resetTurnsInJail() {
        turnsInJail = 0;
    }
}
