import java.util.*;
public class Action extends Block {

    private String type;

    public Action (String type, int position, String title){
        super(position, title);
        this.type = type;
    }

    public void executeAction(int number, Player player) {
        int currentPosition = player.currentBlock.blockPosition;
        if (this.type.equals("chance")) {
            switch (number) {
                case 0:
                    GameBoard.movePlayer(player, 39, false);
                    break;
                case 1:
                    if (currentPosition > 5)
                        player.receiveStartPayment();
                    GameBoard.movePlayer(player, 5, false);
                    break;
                case 2:
                    player.balance += 150;
                    break;
                case 3:
                    if (currentPosition > 24)
                        player.receiveStartPayment();
                    GameBoard.movePlayer(player, 24, false);
                    break;
                case 4:
                    if (Math.abs(28 - currentPosition) > Math.abs(12 - currentPosition))
                        GameBoard.movePlayer(player, 28, false);
                    else
                        GameBoard.movePlayer(player,12,false);
                    if (((Service) player.currentBlock).getOwner() != player) {
                        int[] dice = player.rollTheDice();
                        ((Service) player.currentBlock).getOwner().balance += 2 * (dice[0] + dice[1]);
                    } else {
                        //ask player if he wants to purchase
                        player.BuyProperty((Service) player.currentBlock);
                    }
                    break;
                case 5:
                    Jail.sendToJail(player);
                    break;
                case 6:
                    GameBoard.movePlayer(player, 0, false);
                    player.receiveStartPayment();
                    break;
                case 7:
                    player.balance += 100;
                    break;
                case 8:
                    int desks = 0;
                    int boards = 0;
                    for (Property p : player.properties) {
                        if (p instanceof Room) {
                            if (((Room) p).hasBoard)
                                boards += 1;
                            else
                                desks += ((Room) p).numberOfDesks;
                        }
                    }
                    int payment = 25 * desks + 100 * boards;
                    break;
                case 9:
                    if (Jail.isInJail(player))
                        player.exitPrisonWithCard();
                    else
                        player.outOfJailCards++;
                    break;
                case 10:
                    if (currentPosition > 11)
                        player.receiveStartPayment();
                    GameBoard.movePlayer(player, 11, false);
                    break;
                case 11:
                    for (Player p : GameBoard.players) {
                        p.balance += 50;
                        player.balance -= 50;
                    }
                    break;
                case 12:
                    GameBoard.movePlayer(player, -3, true);
                    break;
                case 13:
                    player.balance -= 50;
                    break;
                case 14:
                    int closest = Math.abs( 5 - currentPosition);
                    int temp;
                    int tempUsed = 5;
                    for(int i=0; i<3; i++){
                        temp = switch (i) {
                            case 0 -> 15;
                            case 1 -> 25;
                            default -> 35;
                        };
                        if(Math.abs(temp - currentPosition) < closest) {
                            closest = Math.abs(temp - currentPosition);
                            tempUsed = temp;
                        }
                    }
                    GameBoard.movePlayer(player,tempUsed,false);
                default:
                    player.balance -= 80;
            }
        }
        else if(this.type.equals("decision")){
            switch (number) {
                case 0 -> {
                    if (Jail.isInJail(player))
                        player.exitPrisonWithCard();
                    else
                        player.outOfJailCards++;
                }
                case 1, 4 -> player.balance -= 50;
                case 2 -> player.balance += 150;
                case 3, 6 -> player.balance -= 100;
                case 5 -> player.balance += 20;
                case 7 -> Jail.sendToJail(player);
                case 8 -> player.balance += 30;
                case 9 -> {
                    for (Player p : GameBoard.players)
                        player.balance += 10;
                }
                case 10 -> player.balance += 50;
                case 11 -> player.balance += 100;
                case 12 -> player.balance += 10;
                case 13 -> {
                    int desks = 0;
                    int boards = 0;
                    for (Property p : player.properties) {
                        if (p instanceof Room) {
                            if (((Room) p).hasBoard)
                                boards += 1;
                            else
                                desks += ((Room) p).numberOfDesks;
                        }
                    }
                    int payment = 40 * desks + 115 * boards;
                }
                case 14 -> {
                    GameBoard.movePlayer(player,0, false);
                    player.receiveStartPayment();
                }
                default -> player.balance += 200;
            }
        }else if (this.type.equals("go_to_jail")){
            Jail.sendToJail(player);
        }
    }

    public int getRandomWithExclusion(Random rnd, int start, int end, int... exclude){
        int random = start + rnd.nextInt(16 - exclude.length);
        for(int ex : exclude){
            if(random < ex){
                break;
            }
            random ++;
        }
        return random;
    }

    public int[] addElement(int[] a, int e){
        a = Arrays.copyOf(a, a.length + 1);
        a[a.length - 1] = e;
        return a;
    }
}
