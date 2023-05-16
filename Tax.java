public class Tax extends Block {
    public double taxValue;

    public Tax(double taxValue) {
        this.taxValue = taxValue;
    }

    public void payTax(Player player) {
        if (player.balance >= this.taxValue) {
            player.balance -= this.taxValue;
        } else
            GameBoard.players.remove(player);
    }


}
