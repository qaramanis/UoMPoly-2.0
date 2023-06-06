public class Service extends Property {

    private int rent;
    public Service(int position,int cost, int mortgageValue, Player owner, int rent){
        super(position, cost, mortgageValue, owner);
        this.rent = rent;
    }
    public double calculateRent(Player player, int dice) {
        int numberOfServiceProperties = 0;
        double return_value;
        for (Property p : player.properties) {
            if (p instanceof Service)
                numberOfServiceProperties++;
        }

        int multiplierForOneProperty = 4;
        int multiplierForTwoProperties = 10;

        if (numberOfServiceProperties == 1)
            return dice * multiplierForOneProperty;
        else
            return dice * multiplierForTwoProperties;
    }
}