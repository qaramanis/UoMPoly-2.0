public class Room extends Property {
    private int rent;
    private int rentWithOneDesk;
    private int rentWithTwoDesks;
    private int rentWithThreeDesks;
    private int rentWithFourDesks;
    private int rentWithBoard;
    private String color;
    public int numberOfDesks;
    private int deskCost;
    public boolean hasBoard;
    private int boardCost;

    public Room(int cost, int mortgageValue, Player owner, int position, int rent, int rentWithOneDesk, int rentWithTwoDesks, int rentWithThreeDesks, int rentWithFourDesks, int rentWithBoard, String color, int deskCost, int boardCost){
        super(cost, mortgageValue, position, owner);
        this.rent = rent;
        this.rentWithOneDesk = rentWithOneDesk;
        this.rentWithTwoDesks = rentWithTwoDesks;
        this.rentWithThreeDesks = rentWithThreeDesks;
        this.rentWithFourDesks = rentWithFourDesks;
        this.rentWithBoard = rentWithBoard;
        this.color = color;
        this.deskCost = deskCost;
        this.boardCost = boardCost;
    }

    public String getColor() {
        return this.color;
    }

    public double calculateRent() {
        double return_value = 0;
        if (this.hasBoard) {
            return_value = this.rentWithBoard;
        } else {
            switch (this.numberOfDesks) {
                case 1:
                    return_value = this.rentWithOneDesk;
                    break;
                case 2:
                    return_value = this.rentWithTwoDesks;
                    break;
                case 3:
                    return_value = this.rentWithThreeDesks;
                    break;
                case 4:
                    return_value = this.rentWithFourDesks;
                    break;
                default:
                    return_value = this.rent;
            }
        }
        return return_value;
    }

    public void buildDesk() {
        if (this.numberOfDesks < 4)
            this.numberOfDesks++;
    }

    public void sellDesk() {
        if (this.numberOfDesks > 0) {
            this.numberOfDesks--;
            owner.balance = +deskCost;
        }
    }

    public void buildBoard() {
        if (!this.hasBoard)
            this.hasBoard = true;
    }

    public void sellBoard(Player owner) {
        if (this.hasBoard) {
            this.hasBoard = false;

            owner.balance = +boardCost;
        }
    }
}


