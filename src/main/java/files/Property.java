package src.main.java.files;
import javax.swing.JOptionPane;

abstract public class Property extends Block{
    private int cost;
    private int mortgageValue;
    protected Player owner;
    protected boolean isMortgaged = false;

    public Property(int position, String title, int cost, Player owner){
        super(position, title);
        this.cost = cost;
    
        this.mortgageValue = cost / 2;

        this.owner = owner;
    }

    public Player getOwner() {
        return owner;
    }

    public void setOwner(Player p){
        this.owner = p;
        p.addProperty(this);
    }

    public int calculateRent(){
        return 0;
    }

    public boolean isMortgaged() {
        return isMortgaged;
    }

    public void mortgageProperty(){
        if(!this.isMortgaged){
            this.isMortgaged = true;
            this.owner.increaseBalance(getMortgageValue());
        }
    }

    public void unmortgageProperty(){
        int unmortgageCost = (int)(getMortgageValue() + (getMortgageValue() * 0.1));
        if(this.isMortgaged){
            if(this.owner.getBalance() >= unmortgageCost) {
                this.isMortgaged = false;
                this.owner.decreaseBalance(unmortgageCost);
            }
            else
                JOptionPane.showMessageDialog(null, "Τα λεφτά σου δεν επαρκούν για να κάνεις άρση της υποθήκεθσης!", "Υποθήκη", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    public int getCost() {
        return cost;
    }

    public int getMortgageValue() {
        return mortgageValue;
    }
}
