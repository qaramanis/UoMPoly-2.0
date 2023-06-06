public class Transport extends Property {

    public int rentWithOneTransportProperty = 25;
    public int rentWithTwoTransportProperties = 50;
    public int rentWithThreeTransportProperties = 100;
    public int rentWithFourTransportProperties = 200;

    public Transport(int position, String title, int cost, int mortgageValue, Player owner){
        super(position, title, cost, mortgageValue, owner);
    }
    public double calculateRent(Player player) {
        int numberOfTransportProperties = 0;
        for (Property p : player.properties) {
            if (p instanceof Transport)
                numberOfTransportProperties++;
        }



        switch (numberOfTransportProperties) {
            case 1:
                return rentWithTwoTransportProperties;
            case 2:
                return rentWithThreeTransportProperties;
            case 3:
                return rentWithFourTransportProperties;
            default:
                return rentWithOneTransportProperty;
        }
    }
}