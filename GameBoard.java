import java.util.*;
public class GameBoard{
    private int numberOfPlayers;
    static ArrayList<Player> players = new ArrayList();
    static Player currentPlayer = new Player();
    private Block[] blockTable = new Block[40];

    public static void GameBoard(){
        do {
            for(Player player : players){
                currentPlayer = player;
                int[] dice = currentPlayer.rollTheDice();
                if(checkIfDoubles(dice))
                    currentPlayer.numOfDoublesInARow ++;
                if(checkIfThreeDoublesInARow(currentPlayer.numOfDoublesInARow))
                    Jail.sendToJail(currentPlayer);
                int diceSum = dice[0] + dice[1];
                if(Jail.isInJail(currentPlayer))
                    break;
                else{
                    boolean isRelative = true;
                    movePlayer(currentPlayer, diceSum, isRelative);
                    if(currentPlayer.currentBlock instanceof Room){
                        if (currentPlayer.ownsProperty(currentPlayer.currentBlock)
                                && canBuildDesk((Room)currentPlayer.currentBlock, currentPlayer)){
                            //ask player if he wants to build desk
                            //use buildDesk accordingly
                        } else if (!currentPlayer.ownsProperty(currentPlayer.currentBlock)){
                            boolean ownerExists = false;
                            for (Player p : players){
                                if (p.equals(currentPlayer))
                                    continue;
                                else{
                                    if (p.ownsProperty(currentPlayer.currentBlock)) {
                                        if (currentPlayer.balance >= ((Room) currentPlayer.currentBlock).calculateRent()){
                                            currentPlayer.balance -= ((Room) currentPlayer.currentBlock).calculateRent();
                                            p.balance += ((Room) currentPlayer.currentBlock).calculateRent();
                                        }else {
                                            GameBoard.players.remove(player);
                                        }
                                        ownerExists = true;
                                        break;
                                    }
                                }
                            }
                            if (ownerExists == false){
                                //ask player if he wants to buy Room
                                //use buyProperty accordingly
                            }
                        }
                    }else if (currentPlayer.currentBlock instanceof Service){
                        if (!currentPlayer.ownsProperty(currentPlayer.currentBlock)){
                            boolean ownerExists = false;
                            for (Player p : players){
                                if (p.equals(currentPlayer))
                                    continue;
                                else{
                                    if (p.ownsProperty(currentPlayer.currentBlock)) {
                                        if (currentPlayer.balance >= ((Service) currentPlayer.currentBlock).calculateRent(p,diceSum)){
                                            currentPlayer.balance -= ((Service) currentPlayer.currentBlock).calculateRent(p,diceSum);
                                            p.balance += ((Room) currentPlayer.currentBlock).calculateRent();
                                        }else {
                                            GameBoard.players.remove(player);
                                        }
                                        ownerExists = true;
                                        break;
                                    }
                                }
                            }
                            if (ownerExists == false){
                                //ask player if he wants to buy Transport
                                //use buyProperty accordingly
                            }
                        }
                    }else if (currentPlayer.currentBlock instanceof Transport){
                        if (!currentPlayer.ownsProperty(currentPlayer.currentBlock)){
                            boolean ownerExists = false;
                            for (Player p : players){
                                if (p.equals(currentPlayer))
                                    continue;
                                else{
                                    if (p.ownsProperty(currentPlayer.currentBlock)) {
                                        if (currentPlayer.balance >= ((Transport) currentPlayer.currentBlock).calculateRent(p)){
                                            currentPlayer.balance -= ((Transport) currentPlayer.currentBlock).calculateRent(p);
                                            p.balance += ((Room) currentPlayer.currentBlock).calculateRent();
                                        }else {
                                            GameBoard.players.remove(player);
                                        }
                                        ownerExists = true;
                                        break;
                                    }
                                }
                            }
                            if (ownerExists == false){
                                //ask player if he wants to buy Transport
                                //use buyProperty accordingly
                            }
                        }
                    }else if (currentPlayer.currentBlock instanceof Tax){
                        //gui interaction to choose between 10% of balance or 200$
                        //use payTax accordingly
                    }else if (currentPlayer.currentBlock instanceof Action){
                        //needs connection to database in order to identify Decision or Chance
                    }
                }

                //needs break in case of currentPlayer bankruptcy
            }
        }while(true);
    }

    static boolean checkIfDoubles(int[] dice){
        boolean result;
        if(dice[0] == dice[1])
            result = true;
        else
            result = false;
        return result;
    }

    static boolean checkIfThreeDoublesInARow(int numOfDoublesInARow){
        boolean result = false;
        if(currentPlayer.numOfDoublesInARow == 3){
            result = true;
            if(Jail.isInJail(currentPlayer))
                currentPlayer.exitPrisonWithFine();
            else
                Jail.sendToJail(currentPlayer);
        }
        return  result;
    }

    static void movePlayer(Player player, int number, boolean isRelative){
        if (isRelative){
            player.currentBlock.blockPosition += number;
            if (player.currentBlock.blockPosition >= 40)
                player.currentBlock.blockPosition -= 40;
            //needs database input for block number
        }else{
            //same here, needs database input
        }
    }

    static boolean canBuildDesk(Room room, Player player){
        boolean result;
        if (player.ownsProperty(room)
            && checkIfPlayerOwnsColorGroup(room,player)
            && room.numberOfDesks < 4) {
                result = true;
        }
        else {
            result = false;
        }
        return result;
    }

    static boolean canBuildBoard(Room room, Player player){
        boolean result;
        if (player.ownsProperty(room) && room.numberOfDesks == 4)
            result = true;
        else
            result = false;
        return result;
    }


    static boolean checkIfPlayerOwnsColorGroup(Room room, Player player){
        boolean result = true;
        for (Room r : sameColorRoom(room.getColor())){
            if (!player.ownsProperty(r)){
                result = false;
            }
        }
        return result;
    }

    static ArrayList<Room> sameColorRoom(String color){
        ArrayList<Room> rooms = new ArrayList<>();
        //need connection to database to scan and get all the same-color rooms

        return rooms;
    }
}