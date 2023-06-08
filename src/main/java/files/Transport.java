package src.main.java.files;

public class Transport extends Property {

    public int rentWithOneTransportProperty = 25;
    public int rentWithTwoTransportProperties = 50;
    public int rentWithThreeTransportProperties = 100;
    public int rentWithFourTransportProperties = 200;

    public Transport(int position, String title, int cost, Player owner){
        super(position, title, cost, owner);
    }
    public int calculateRent() {
        int numberOfTransportProperties = 0;
        for (Property p : owner.getProperties()) {
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