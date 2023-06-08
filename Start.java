import javax.swing.JOptionPane;

public class Start extends Block{

    protected static final int startPayment = 200;

    public Start(int pos, String title){
        super(pos, title);
    }

    public static void receiveStartPayment(Player player){
         player.increaseBalance(startPayment);
        JOptionPane.showMessageDialog(null, "Πέρασες από την αφετηρία,\n έλαβες " + startPayment + "€", "Αφετηρία", JOptionPane.INFORMATION_MESSAGE);
     }

}
