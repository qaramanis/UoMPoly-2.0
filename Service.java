public class Service extends Property {

    public Service(int position, String title, int cost, Player owner){
        super(position, title, cost, owner);
    }
  
    public int calculateRent(int[] dice) {
        int numberOfServiceProperties = 0;
        int diceTotal = dice[0] + dice[1];
        for (Property p : this.owner.getProperties()) {
            if (p instanceof Service)
                numberOfServiceProperties++;
        }

        int multiplierForOneProperty = 4;
        int multiplierForTwoProperties = 10;

        if (numberOfServiceProperties == 1)
            return diceTotal * multiplierForOneProperty;
        else
            return diceTotal * multiplierForTwoProperties;
    }
}