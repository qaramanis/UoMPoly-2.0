import java.util.ArrayList;

public class Jail extends Block{
    static ArrayList<Player> playersInJail = new ArrayList<>();

    public Jail (ArrayList<Player> playersInJail, int position, String title){
        super(position, title);
        this.playersInJail = playersInJail;
    }

    static boolean isInJail(Player player){
        boolean result = false;
        for(Player p : playersInJail){
            if(player.equals(p))
                result = true;
        }
        return result;
    }

    static void sendToJail(Player player){
        playersInJail.add(player);
        GameBoard.movePlayer(player,30, false); //jail is on no. 30
    }

    static void removeFromJail(Player player){
        playersInJail.remove(player);
    }

}
