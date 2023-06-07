abstract class Property extends Block{
    public int cost;
    public int mortgageValue;
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
        p.properties.add(this);
    }

    public int calculateRent(){
        return 0;
    }

    public boolean isMortgaged() {
        return isMortgaged;
    }

    public void mortgageProperty(){

    }

    public void unmortgageProperty(){
    }
}
