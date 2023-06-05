import java.util.*;
public class GameBoard{
    private int numberOfPlayers;
    static ArrayList<Player> players = new ArrayList();
    static Player currentPlayer = new Player();
    private Block[] blockTable = new Block[40];

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