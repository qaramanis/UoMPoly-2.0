public class Start extends Block{

    public Start(int pos, String title){
        super(pos, title);
    }
    protected final int startPayment = 200;

    public void receiveStartPayment(Player player){
         player.balance += startPayment;
     }

}
