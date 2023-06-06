public class Tax extends Block {
    private double taxValue;

    public Tax(int position, double taxValue) {
        super(position);
        this.taxValue = taxValue;
    }

    public void payTax(Player player) {
        if (player.balance >= this.taxValue) {
            player.balance -= this.taxValue;
        } else
            GameBoard.players.remove(player);
    }


}
