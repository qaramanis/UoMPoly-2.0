import java.util.ArrayList;

public class Jail extends Block{
    private static ArrayList<Player> playersInJail = new ArrayList<>();

    public Jail (int position, String title){
        super(position, title);

    }

    public static boolean isInJail(Player player){
        boolean result = false;
        for(Player p : playersInJail){
            if(player.equals(p))
                result = true;
        }
        return result;
    }

    public static void sendToJail(Player player){
        playersInJail.add(player);
        GameBoard.movePlayer(player,10, false); //jail is on no. 10
        GameBoard.setTurnActive(false);
    }

    public static void removeFromJail(Player player){
        playersInJail.remove(player);
    }

}
