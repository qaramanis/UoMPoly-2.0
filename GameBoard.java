import javax.swing.*;
import java.lang.reflect.Array;
import java.util.*;
public class GameBoard implements Runnable {
    private int numberOfPlayers;
    static ArrayList<Player> players = new ArrayList<Player>();
    Thread gameThread;
    private static Player currentPlayer;
    private static Block[] blockTable = new Block[40];

    private MainBoard mainGUI;

    public GameBoard(int numberOfPlayers, MainBoard mainGUI) {
        this.mainGUI = mainGUI;
        this.numberOfPlayers = numberOfPlayers;

        createBlocks();

        for (int i = 0; i < numberOfPlayers; i++) {
            Player p = new Player(1500, blockTable[0], i + 1);
            players.add(p);
        }

        currentPlayer = players.get(0);
        ((Property) blockTable[1]).setOwner(currentPlayer);
        ((Property) blockTable[3]).setOwner(currentPlayer);
        ((Property) blockTable[5]).setOwner(currentPlayer);
        ((Property) blockTable[12]).setOwner(currentPlayer);
        currentPlayer.currentBlock = blockTable[1];
    }

    public static Player getCurrentPlayer() {

        return currentPlayer;
    }

    public void startGameThread() {
        gameLoop();
        gameThread = new Thread(this);
        gameThread.start();
    }

    @Override
    public void run() {
        int[] exclude;
        mainGUI.updateInfo(currentPlayer);
        do {

            gameLoop();
        } while (numberOfPlayers > 1);
    }

    public void gameLoop(){

        mainGUI.updateInfo(currentPlayer);
        int[] excludeChance;
        int[] excludeDecision;
        int dice[] = currentPlayer.rollTheDice();
        int diceSum = dice[0] + dice[1];
        if(Jail.isInJail(currentPlayer)){
            if(dice[0] != dice[1]) {
                JOptionPane.showMessageDialog(null, "Παραμένεις στην φυλακή 😎😎", "Φυλακή", JOptionPane.INFORMATION_MESSAGE);
//                updateTurn();
            }else
                Jail.removeFromJail(currentPlayer);
        }else{
            movePlayer(currentPlayer, diceSum, true);
        }

        Block currentBlock = currentPlayer.currentBlock;
        if (currentBlock instanceof Property){
            if (!((Property) currentBlock).owner.equals(currentPlayer) && ((Property) currentBlock).owner != null){
                int rent = ((Property) currentBlock).calculateRent();
                currentPlayer.payRent(((Property) currentBlock).owner, rent);
            }
        }else if(currentBlock instanceof Action currAction){
            currAction.executeAction(currentPlayer);
        }


//        for(Player player : players) {
//
//        int[] dice = currentPlayer.rollTheDice();
//        if (checkIfDoubles(dice))
//            currentPlayer.numOfDoublesInARow++;
//        if (checkIfThreeDoublesInARow(currentPlayer.numOfDoublesInARow))
//            Jail.sendToJail(currentPlayer);
//        int diceSum = dice[0] + dice[1];
//        if (Jail.isInJail(currentPlayer))
//            break;
//        else {
//            boolean isRelative = true;
//            movePlayer(currentPlayer, diceSum, isRelative);
//            if (currentPlayer.currentBlock instanceof Room) {
//                if (currentPlayer.ownsProperty(currentPlayer.currentBlock)
//                        && canBuildDesk((Room) currentPlayer.currentBlock, currentPlayer)) {
//                    //ask player if he wants to build desk
//                    //use buildDesk accordingly
//                } else if (!currentPlayer.ownsProperty(currentPlayer.currentBlock)) {
//                    boolean ownerExists = false;
//                    for (Player p : players) {
//                        if (p.equals(currentPlayer))
//                            continue;
//                        else {
//                            if (p.ownsProperty(currentPlayer.currentBlock)) {
//                                if (currentPlayer.balance >= ((Room) currentPlayer.currentBlock).calculateRent()) {
//                                    currentPlayer.balance -= ((Room) currentPlayer.currentBlock).calculateRent();
//                                    checkBankruptcy(currentPlayer.getBalance());
//                                    p.balance += ((Room) currentPlayer.currentBlock).calculateRent();
//                                } else {
//                                    players.remove(currentPlayer);
//                                }
//                                ownerExists = true;
//                                break;
//                            }
//                        }
//                    }
//                    if (!ownerExists) {
//                        //ask player if he wants to buy Room
//
//                    }
//                }
//            } else if (currentPlayer.currentBlock instanceof Service) {
//                if (!currentPlayer.ownsProperty(currentPlayer.currentBlock)) {
//                    boolean ownerExists = false;
//                    for (Player p : players) {
//                        if (p.equals(currentPlayer))
//                            continue;
//                        else {
//                            if (p.ownsProperty(currentPlayer.currentBlock)) {
//                                if (currentPlayer.balance >= ((Service) currentPlayer.currentBlock).calculateRent(p, diceSum)) {
//                                    currentPlayer.balance -= ((Service) currentPlayer.currentBlock).calculateRent(p, diceSum);
//                                    p.balance += ((Room) currentPlayer.currentBlock).calculateRent();
//                                } else {
//                                    players.remove(currentPlayer);
//                                }
//                                ownerExists = true;
//                                break;
//                            }
//                        }
//                    }
//                    if (!ownerExists) {
//                        //ask player if he wants to buy Transport
//                        //use buyProperty accordingly
//                    }
//                }
//            } else if (currentPlayer.currentBlock instanceof Transport) {
//                if (!currentPlayer.ownsProperty(currentPlayer.currentBlock)) {
//                    boolean ownerExists = false;
//                    for (Player p : players) {
//                        if (p.equals(currentPlayer))
//                            continue;
//                        else {
//                            if (p.ownsProperty(currentPlayer.currentBlock)) {
//                                if (currentPlayer.balance >= ((Transport) currentPlayer.currentBlock).calculateRent(p)) {
//                                    currentPlayer.balance -= ((Transport) currentPlayer.currentBlock).calculateRent(p);
//                                    p.balance += ((Room) currentPlayer.currentBlock).calculateRent();
//                                } else {
//                                    players.remove(currentPlayer);
//                                }
//                                ownerExists = true;
//                                break;
//                            }
//                        }
//                    }
//                    if (!ownerExists) {
//                        //ask player if he wants to buy Transport
//                        if (currentPlayer.balance >= ((Property) currentPlayer.currentBlock).cost) {
//                            currentPlayer.BuyProperty((Property) currentPlayer.currentBlock);
//                        }
//                    }
//                }
//            } else if (currentPlayer.currentBlock instanceof Tax) {
//                ((Tax) currentPlayer.currentBlock).payTax(currentPlayer);
//            } else if (currentPlayer.currentBlock instanceof Action) {
//
//            }
//        }
//
//        //needs break in case of currentPlayer bankruptcy
//        }
        updateTurn();
        }

    static boolean checkIfDoubles(int[] dice) {
        boolean result;
        if (dice[0] == dice[1])
            result = true;
        else
            result = false;
        return result;
    }

    static boolean checkIfThreeDoublesInARow(int numOfDoublesInARow) {
        boolean result = false;
        if (currentPlayer.numOfDoublesInARow == 3) {
            result = true;
            if (Jail.isInJail(currentPlayer))
                currentPlayer.exitPrisonWithFine();
            else
                Jail.sendToJail(currentPlayer);
        }
        return result;
    }

    static void movePlayer(Player player, int number, boolean isRelative) {
        int currentPos = player.currentBlock.blockPosition;
        if (isRelative) {
            player.currentBlock = blockTable[(currentPos + number) % 40];
        } else {
            player.currentBlock = blockTable[number];
        }
    }


    public static boolean checkIfPlayerOwnsColorGroup(Room room, Player player) {
        for (Room r : sameColorRoom(room.getColor())) {
            if (r.getOwner() == null || !r.getOwner().equals(player)) {
                return false;
            }
        }

        return true;
    }

    public static ArrayList<Room> sameColorRoom(String color) {
        ArrayList<Room> rooms = new ArrayList<>();
        for(Block b : blockTable){
            if(b instanceof Room r){
                if(r.getColor().equals(color)) rooms.add(r);
            }
        }

        return rooms;
    }


    public boolean checkBankruptcy(int balance) {
        boolean result = balance < 0;
        return result;
    }


    public void updateTurn() {
        if (currentPlayer.getPlayerID() == numberOfPlayers)
            currentPlayer = players.get(0);
        else
            currentPlayer = players.get(currentPlayer.getPlayerID());
    }

    public void createBlocks(){
        blockTable[0] = new Start(0, "ΑΦΕΤΗΡΙΑ");
        blockTable[1] = new Room(60,"ΑΙΘ. 1", null,1,2,10,30,90,160,250, "brown", 50);
        blockTable[2] = new Action("chance", 2,"ΑΠΟΦΑΣΗ");
        blockTable[3] = new Room(60,"AΙΘ. 2",null, 3, 4,20,60,180,320,450, "brown", 50);
        blockTable[4] = new Tax(4,"ΦΟΡΟΣ - ΔΙΔΑΚΤΡΑ Α", 100);
        blockTable[5] = new Transport(5, "ΚΤΙΡΙΟ Α - ΑΝΕΛΚΥΣΤΗΡΕΣ", 200, null);
        blockTable[6] = new Room(100, "ΑΙΘ. 3", null, 6, 6,30,90,270,400,550,"light_blue",50);
        blockTable[7] = new Action("chance", 7, "ΕΝΤΟΛΗ");
        blockTable[8] = new Room(100, "ΑΙΘ. 4", null, 6, 6,30,90,270,400,550,"light_blue",50);
        blockTable[9] = new Room(120, "ΑΙΘ. 5", null, 9, 8,40,100,300,450,600,"light_blue", 50);
        blockTable[10] = new Jail(null, 10, "ΦΥΛΑΚΗ");
        blockTable[11] = new Room(140, "ΑΙΘ. 6",null, 11, 10,50,150,450,625,750,"magenta", 100);
        blockTable[12] = new Service(12, "ΚΥΛΙΚΕΙΟ", 150, null);
        blockTable[13] = new Room(140, "ΑΙΘ. 7",null, 11, 10,50,150,450,625,750,"magenta", 100);
        blockTable[14] = new Room(160, "ΑΙΘ. 8", null, 14, 12,60,180,500,700,900,"magenta", 100);
        blockTable[15] = new Transport(15, "ΚΤΙΡΙΟ Β - ΑΝΕΛΚΥΣΤΗΡΕΣ", 200, null);
        blockTable[16] = new Room(180, "ΑΙΘ. 9", null, 16, 14,70,200,550,750,950,"orange", 100);
        blockTable[17] = new Action("chance", 17,"ΑΠΟΦΑΣΗ");
        blockTable[18] = new Room(180, "ΑΙΘ. 10", null, 18, 14,70,200,550,750,950,"orange", 100);
        blockTable[19] = new Room(200,"ΑΙΘ. 11", null, 19,16,80,220,600,800,1000, "orange", 100);
        blockTable[20] = new freeParking(20, "ΦΟΥΑΓΙΕ");
        blockTable[21] = new Room(220, "ΑΙΘ. ΣΥΝΕΔΡΙΩΝ", null, 21, 18,90,250,700,875,1050, "red", 150);
        blockTable[22] = new Action("chance", 22, "ΕΝΤΟΛΗ");
        blockTable[23] = new Room(220, "ΑΙΘ. ΤΗΛΕΚΠΑΙΔΕΥΣΗΣ", null, 23, 18,90,250,700,875,1050, "red", 150);
        blockTable[24] = new Room(240, "Κ.Υ.Δ.", null, 24, 20,100,300,750,925,1100, "red", 150);
        blockTable[25] = new Transport(25, "ΚΤΙΡΙΟ Γ - ΑΝΕΛΚΥΣΤΗΡΕΣ", 200, null);
        blockTable[26] = new Room(260, "ΑΜΦ. 8", null,26,22,110,330,800,975,1150, "yellow", 150);
        blockTable[27] = new Service(27, "ΦΩΤΠΤΥΠΕΙΟ", 150, null);
        blockTable[28] = new Room(260, "ΑΜΦ. 9", null,28,22,110,330,800,975,1150, "yellow", 150);
        blockTable[29] = new Room(280, "ΑΜΦ. 10", null, 24, 24,120,360,850,1025,1200,"yellow", 150);
        blockTable[30] = new Action("go_to_jail",30, "ΠΗΓΑΙΝΕ ΣΤΗΝ ΦΥΛΑΚΗ");
        blockTable[31] = new Room(300, "ΑΜΦ. 3", null, 31,26,120,390,900,1100,1275, "green",200);
        blockTable[32] = new Action("chance", 32,"ΑΠΟΦΑΣΗ");
        blockTable[33] = new Room(300, "ΑΜΦ. 2", null, 33,26,120,390,900,1100,1275, "green",200);
        blockTable[34] = new Room(320, "ΑΜΦ. 1", null, 34,28,150,450,1000,12000,1400 ,"green",200);
        blockTable[35] = new Transport(15, "ΚΤΙΡΙΟ Δ - ΑΝΕΛΚΥΣΤΗΡΕΣ", 200, null);
        blockTable[36] = new Action("chance", 36, "ΕΝΤΟΛΗ");
        blockTable[37] = new Room(350, "ΑΜΦ. 13", null, 37, 35,175,500,1100,1300,1500, "dark_blue", 200);
        blockTable[38] = new Tax(38,"ΦΟΡΟΣ - ΔΙΔΑΚΤΡΑ Β", 100);
        blockTable[39] = new Room(400, "ΑΜΦ. 14", null, 39, 50,200,600,1400,1700,2000,"dark_blue",200);
    }

}