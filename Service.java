public class Service extends Property {

    public Service(int position, String title, int cost, Player owner){
        super(position, title, cost, owner);
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