import javax.swing.JOptionPane;

public class Room extends Property {
    private int rent;
    private int rentWithOneDesk;
    private int rentWithTwoDesks;
    private int rentWithThreeDesks;
    private int rentWithFourDesks;
    private int rentWithBoard;
    private String color;

    public int numberOfDesks = 0;
    private int structureCost;
    public boolean hasBoard;
    public Room(int cost, String title,  Player owner, int position, int rent, int rentWithOneDesk, int rentWithTwoDesks, int rentWithThreeDesks, int rentWithFourDesks, int rentWithBoard, String color, int structureCost){
      
        super(position, title, cost, owner);
        this.rent = rent;
        this.rentWithOneDesk = rentWithOneDesk;
        this.rentWithTwoDesks = rentWithTwoDesks;
        this.rentWithThreeDesks = rentWithThreeDesks;
        this.rentWithFourDesks = rentWithFourDesks;
        this.rentWithBoard = rentWithBoard;
        this.color = color;
        this.structureCost = structureCost;
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

    public boolean canBuildDesk() {
        return GameBoard.checkIfPlayerOwnsColorGroup(this, this.owner) && this.numberOfDesks < 4 && this.owner.balance >= structureCost && this.owner.currentBlock.equals(this);
    }

    public boolean canBuildBoard() {
        return this.numberOfDesks == 4 && this.owner.balance >= structureCost && this.owner.currentBlock.equals(this);
    }

    public void buildDesk() {
        if (canBuildDesk()){
            this.owner.balance -= structureCost;
            numberOfDesks++;
        }else JOptionPane.showMessageDialog(null, "Δεν μπορείς να χτίσεις έδρανο.", "Ωχ! Κάτι πήγε στραβά.", JOptionPane.INFORMATION_MESSAGE);
    }

    public void sellDesk() {
        if (this.numberOfDesks > 0 && !this.hasBoard) {
            this.numberOfDesks--;
            this.owner.balance += (structureCost / 2);
        }else JOptionPane.showMessageDialog(null, "Δεν μπορείς να πουλήσεις έδρανο.", "Ωχ! Κάτι πήγε στραβά.", JOptionPane.INFORMATION_MESSAGE);
    }

    public void buildBoard() {
        if (canBuildBoard()){
            this.owner.balance -= structureCost;
            hasBoard = true;
        }else JOptionPane.showMessageDialog(null, "Δεν μπορείς να χτίσεις πίνακα.", "Ωχ! Κάτι πήγε στραβά.", JOptionPane.INFORMATION_MESSAGE);
    }

    public void sellBoard() {
        if (this.hasBoard) {
            this.hasBoard = false;
            this.owner.balance += (structureCost/2);
        }else JOptionPane.showMessageDialog(null, "Δεν μπορείς να πουλήσεις πίνακα.", "Ωχ! Κάτι πήγε στραβά.", JOptionPane.INFORMATION_MESSAGE);
    }

    @Override
    public void mortgageProperty(){
        if(numberOfDesks == 0 && !hasBoard && !this.isMortgaged){
            this.isMortgaged = true;
            this.owner.balance += mortgageValue;
        }
    }

    @Override
    public void unmortgageProperty(){
        int unmortgageCost = (int)(mortgageValue + (mortgageValue * 0.1));
        if(this.isMortgaged && this.owner.balance >= unmortgageCost){
            this.isMortgaged = false;
            this.owner.balance -= unmortgageCost;
        }
    }

    public int getRent() {
        return rent;
    }

    public int getRentWithOneDesk() {
        return rentWithOneDesk;
    }

    public int getRentWithTwoDesks() {
        return rentWithTwoDesks;
    }

    public int getRentWithThreeDesks() {
        return rentWithThreeDesks;
    }

    public int getRentWithFourDesks() {
        return rentWithFourDesks;
    }

    public int getRentWithBoard() {
        return rentWithBoard;
    }

    public int getNumberOfDesks() {
        return numberOfDesks;
    }

    public int getstructureCost() {
        return structureCost;
    }

    public boolean isHasBoard() {
        return hasBoard;
    }

    public int getBoardCost() {
        return structureCost;
    }
}


