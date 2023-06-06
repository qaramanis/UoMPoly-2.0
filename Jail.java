import java.util.ArrayList;

public class Jail extends Block{
    static ArrayList<Player> playersInJail = new ArrayList<>();

    public Jail (ArrayList<Player> playersInJail, int position){
        super(position);
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
        player.currentBlock.blockPosition = 30; //jail is on no. 30
    }


}
