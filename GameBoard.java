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
        blockTable[0] = new Start(0, "test");
        blockTable[1] = new Room(150, "Αιθ. 5", 80, null, 1, 10, 20, 30, 40, 50, 60, "red", 10, 20);
        blockTable[2] = new Room(110, "Αιθ. 8", 90, null, 2, 10, 20, 30, 40, 50, 60, "blue", 10, 20);
        for (int i = 0; i < numberOfPlayers; i++) {
            Player p = new Player(1500, blockTable[0], i + 1);
            players.add(p);
        }
        currentPlayer = players.get(0);
        ((Property)blockTable[1]).setOwner(currentPlayer);
        ((Property)blockTable[2]).setOwner(currentPlayer);
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

        do {
            mainGUI.updateInfo(currentPlayer);
            gameLoop();
        } while (numberOfPlayers > 1);
    }

    public void gameLoop(){

//        mainGUI.updateInfo(currentPlayer);
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
//        updateTurn();
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
            if (!r.getOwner().equals(player)) {
                return false;
            }
        }

        return true;
    }

    static ArrayList<Room> sameColorRoom(String color) {
        ArrayList<Room> rooms = new ArrayList<>();
        //need connection to database to scan and get all the same-color rooms

        return rooms;
    }

    public int getRandomWithExclusion(Random rnd, int start, int end, int... exclude) {
        int random = start + rnd.nextInt(16 - exclude.length);
        for (int ex : exclude) {
            if (random < ex) {
                break;
            }
            random++;
        }
        return random;
    }

    public int[] addElement(int[] a, int e) {
        a = Arrays.copyOf(a, a.length + 1);
        a[a.length - 1] = e;
        return a;
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
}
