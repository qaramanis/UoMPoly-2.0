import javax.swing.JOptionPane;

public class Tax extends Block {
    private int taxValue;

    public Tax(int position, String title, int taxValue) {
        super(position, title);
        this.taxValue = taxValue;
    }

    public void payTax(Player player) {
        if (player.getBalance() >= this.taxValue) {
            player.decreaseBalance(this.taxValue);
            JOptionPane.showMessageDialog(null, "Πλήρωσες φόρο: " + this.taxValue + "€", "Φόρος", JOptionPane.INFORMATION_MESSAGE);
        } else {
            GameBoard.players.remove(player);
            JOptionPane.showMessageDialog(null, "Τα λεφτά σου δεν επαρκούν για να πληρώσεις τον φόρο!\n Βγαίνεις από το παιχνίδι.", "Xρεωκοπία! :(", JOptionPane.INFORMATION_MESSAGE);
            GameBoard.setTurnActive(false);
        }
    }


}
