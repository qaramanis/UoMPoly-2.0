public class Transport extends Property {
    public double calculateRent(Player player) {
        int numberOfTransportProperties = 0;
        for (Property p : player.properties) {
            if (p instanceof Transport)
                numberOfTransportProperties++;
        }

        int rentWithOneTransportProperty = 25;
        int rentWithTwoTransportProperties = 50;
        int rentWithThreeTransportProperties = 100;
        int rentWithFourTransportProperties = 200;

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