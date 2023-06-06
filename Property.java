abstract class Property extends Block{
    public int cost;
    static int mortgageValue;
    public static Player owner;

    public Property(int position, String title, int cost, int mortgageValue, Player owner){
        super(position, title);
        this.cost = cost;
        this.mortgageValue = mortgageValue;
        this.owner = owner;
    }

    public Player getOwner() {
        return owner;
    }
}
