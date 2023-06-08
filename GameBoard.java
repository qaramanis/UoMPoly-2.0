import javax.swing.*;
import java.lang.reflect.Array;
import java.util.*;

public class GameBoard implements Runnable {
    static ArrayList<Player> players = new ArrayList<Player>();
    private static int numberOfPlayers;
    private static Player currentPlayer;
    private static Block[] blockTable = new Block[40];
    private static boolean turnActive = true;
    Thread gameThread;
    private MainBoard mainGUI;

    public GameBoard(int numberOfPlayers, MainBoard mainGUI) {
        this.mainGUI = mainGUI;
        mainGUI.setgBoard(this);
        this.numberOfPlayers = numberOfPlayers;

        createBlocks();

        for (int i = 0; i < numberOfPlayers; i++) {
            Player p = new Player(1500, blockTable[0], i + 1);
            players.add(p);
        }

        currentPlayer = players.get(0);
    }

    static boolean checkIfDoubles(int[] dice) {
        return dice[0] == dice[1];
    }

    static boolean checkIfThreeDoublesInARow() {
        return currentPlayer.getNumOfDoublesInARow() == 3;
    }

    static void movePlayer(Player player, int number, boolean isRelative) {
        int currentPos = player.getCurrentBlock().getBlockPosition();
        if (isRelative) {
            player.setCurrentBlock(blockTable[(currentPos + number) % 40]);
        } else {
            player.setCurrentBlock(blockTable[number]);
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
        for (Block b : blockTable) {
            if (b instanceof Room r) {
                if (r.getColor().equals(color)) rooms.add(r);
            }
        }

        return rooms;
    }

    public static void updateTurn() {
        if (currentPlayer.getPlayerID() == players.size())
            currentPlayer = players.get(0);
        else
            currentPlayer = players.get(currentPlayer.getPlayerID());
    }

    public static void setTurnActive(boolean val) {
        turnActive = val;
    }

    public void startGameThread() {
        gameThread = new Thread(this);
        gameThread.start();
    }

    @Override
    public void run() {
        mainGUI.updateInfo(currentPlayer);
        try {
            gameLoop();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

    }

    public void gameLoop() throws InterruptedException {
        while (players.size() > 1) {
            mainGUI.updateEndTurnEnabled(false);
            mainGUI.updateInfo(currentPlayer);
            mainGUI.updateDice(new int[2], true);
            setTurnActive(true);

            if (checkBankruptcy()) {
                players.remove(currentPlayer);
                JOptionPane.showMessageDialog(null, "Έχεις χρεωκοπήσει!\n Βγαίνεις από το παιχνίδι.", "Xρεωκοπία! :(", JOptionPane.INFORMATION_MESSAGE);
                setTurnActive(false);
                updateTurn();
                continue;
            }

            gameThread.sleep(1000);

            int dice[] = currentPlayer.rollTheDice();
            mainGUI.updateDice(dice, false);
            int diceSum = dice[0] + dice[1];

            if (Jail.isInJail(currentPlayer)) {
                currentPlayer.increaseTurnsInJail();
                if (currentPlayer.getTurnsInJail() == 3) {
                    if (currentPlayer.getBalance() >= 50) {
                        Jail.removeFromJail(currentPlayer);
                        currentPlayer.decreaseBalance(50);
                        movePlayer(currentPlayer, diceSum, true);
                        mainGUI.updatePos();
                    } else {
                        players.remove(currentPlayer);
                        JOptionPane.showMessageDialog(null, "Τα λεφτά σου δεν επαρκούν για να πληρώσεις!\n Βγαίνεις από το παιχνίδι.", "Xρεωκοπία! :(", JOptionPane.INFORMATION_MESSAGE);
                        GameBoard.setTurnActive(false);
                    }
                }
                if (!checkIfDoubles(dice) && currentPlayer.getTurnsInJail() > 0) {
                    JOptionPane.showMessageDialog(null, "Παραμένεις στην φυλακή", "Φυλακή", JOptionPane.INFORMATION_MESSAGE);
                } else {
                    Jail.removeFromJail(currentPlayer);
                    movePlayer(currentPlayer, diceSum, true);
                }
            } else {
                currentPlayer.resetTurnsInJail();
                if (checkIfDoubles(dice)) currentPlayer.increaseNumOfDoublesInARow();
                else currentPlayer.resetNumOfDoublesInARow();
                if (checkIfThreeDoublesInARow()) {
                    JOptionPane.showMessageDialog(null, "Πήγαινε κατευθείαν στη φυλακή. Δεν περνάς από την αφετηρία δεν παίρνεις 200€.", "Απόφαση", JOptionPane.INFORMATION_MESSAGE);
                    Jail.sendToJail(currentPlayer);
                    setTurnActive(false);
                    updateTurn();
                    continue;
                }
                int tempPos = currentPlayer.getCurrentBlock().getBlockPosition();
                movePlayer(currentPlayer, diceSum, true);
                int currentPos = currentPlayer.getCurrentBlock().getBlockPosition();
                if (currentPlayer.getCurrentBlock() instanceof Start || (currentPos - tempPos) < 0) {
                    Start.receiveStartPayment(currentPlayer);
                    mainGUI.updateInfo(currentPlayer);
                }
                mainGUI.updatePos();

            }

            Block currentBlock = currentPlayer.getCurrentBlock();

            if (currentBlock instanceof Action currAction) {
                currAction.executeAction(currentPlayer, mainGUI);
                mainGUI.updateInfo(currentPlayer);
            }

            currentBlock = currentPlayer.getCurrentBlock();

            if (currentBlock instanceof Property prop) {
                if (prop.owner != null && !prop.owner.equals(currentPlayer)) {
                    int rent = prop.calculateRent();
                    currentPlayer.payRent(prop.owner, rent);
                    mainGUI.updateInfo(currentPlayer);
                }
            }



            if (currentBlock instanceof Tax tax) {
                tax.payTax(currentPlayer);
                mainGUI.updateInfo(currentPlayer);
            }

            mainGUI.updateEndTurnEnabled(true);

            while (turnActive) {
                gameThread.sleep(1000);
            }
            ;

            if (!checkIfDoubles(dice)) updateTurn();
        }
        JOptionPane.showMessageDialog(null, "Συγχαρητήρια Παίκτη " + currentPlayer.getPlayerID() + ", κερδίσατε το παιχνίδι!", "Νίκη", JOptionPane.INFORMATION_MESSAGE);
        mainGUI.dispose();
        System.exit(0);
    }

    public boolean checkBankruptcy() {
        return currentPlayer.getBalance() < 0;
    }

    public void createBlocks() {
        blockTable[0] = new Start(0, "ΑΦΕΤΗΡΙΑ");
        blockTable[1] = new Room(60, "ΑΙΘ. 1", null, 1, 2, 10, 30, 90, 160, 250, "brown", 50);
        blockTable[2] = new Action("decision", 2, "ΑΠΟΦΑΣΗ");
        blockTable[3] = new Room(60, "AΙΘ. 2", null, 3, 4, 20, 60, 180, 320, 450, "brown", 50);
        blockTable[4] = new Tax(4, "ΦΟΡΟΣ - ΔΙΔΑΚΤΡΑ Α", 100);
        blockTable[5] = new Transport(5, "ΚΤΙΡΙΟ Α - ΑΝΕΛΚΥΣΤΗΡΕΣ", 200, null);
        blockTable[6] = new Room(100, "ΑΙΘ. 3", null, 6, 6, 30, 90, 270, 400, 550, "light_blue", 50);
        blockTable[7] = new Action("chance", 7, "ΕΝΤΟΛΗ");
        blockTable[8] = new Room(100, "ΑΙΘ. 4", null, 8, 6, 30, 90, 270, 400, 550, "light_blue", 50);
        blockTable[9] = new Room(120, "ΑΙΘ. 5", null, 9, 8, 40, 100, 300, 450, 600, "light_blue", 50);
        blockTable[10] = new Jail(10, "ΦΥΛΑΚΗ");
        blockTable[11] = new Room(140, "ΑΙΘ. 6", null, 11, 10, 50, 150, 450, 625, 750, "magenta", 100);
        blockTable[12] = new Service(12, "ΚΥΛΙΚΕΙΟ", 150, null);
        blockTable[13] = new Room(140, "ΑΙΘ. 7", null, 13, 10, 50, 150, 450, 625, 750, "magenta", 100);
        blockTable[14] = new Room(160, "ΑΙΘ. 8", null, 14, 12, 60, 180, 500, 700, 900, "magenta", 100);
        blockTable[15] = new Transport(15, "ΚΤΙΡΙΟ Β - ΑΝΕΛΚΥΣΤΗΡΕΣ", 200, null);
        blockTable[16] = new Room(180, "ΑΙΘ. 9", null, 16, 14, 70, 200, 550, 750, 950, "orange", 100);
        blockTable[17] = new Action("decision", 17, "ΑΠΟΦΑΣΗ");
        blockTable[18] = new Room(180, "ΑΙΘ. 10", null, 18, 14, 70, 200, 550, 750, 950, "orange", 100);
        blockTable[19] = new Room(200, "ΑΙΘ. 11", null, 19, 16, 80, 220, 600, 800, 1000, "orange", 100);
        blockTable[20] = new freeParking(20, "ΦΟΥΑΓΙΕ");
        blockTable[21] = new Room(220, "ΑΙΘ. ΣΥΝΕΔΡΙΩΝ", null, 21, 18, 90, 250, 700, 875, 1050, "red", 150);
        blockTable[22] = new Action("chance", 22, "ΕΝΤΟΛΗ");
        blockTable[23] = new Room(220, "ΑΙΘ. ΤΗΛΕΚΠΑΙΔΕΥΣΗΣ", null, 23, 18, 90, 250, 700, 875, 1050, "red", 150);
        blockTable[24] = new Room(240, "Κ.Υ.Δ.", null, 24, 20, 100, 300, 750, 925, 1100, "red", 150);
        blockTable[25] = new Transport(25, "ΚΤΙΡΙΟ Γ - ΑΝΕΛΚΥΣΤΗΡΕΣ", 200, null);
        blockTable[26] = new Room(260, "ΑΜΦ. 8", null, 26, 22, 110, 330, 800, 975, 1150, "yellow", 150);
        blockTable[27] = new Service(27, "ΦΩΤΠΤΥΠΕΙΟ", 150, null);
        blockTable[28] = new Room(260, "ΑΜΦ. 9", null, 28, 22, 110, 330, 800, 975, 1150, "yellow", 150);
        blockTable[29] = new Room(280, "ΑΜΦ. 10", null, 29, 24, 120, 360, 850, 1025, 1200, "yellow", 150);
        blockTable[30] = new Action("go_to_jail", 30, "ΠΗΓΑΙΝΕ ΣΤΗΝ ΦΥΛΑΚΗ");
        blockTable[31] = new Room(300, "ΑΜΦ. 3", null, 31, 26, 120, 390, 900, 1100, 1275, "green", 200);
        blockTable[32] = new Room(300, "ΑΜΦ. 2", null, 32, 26, 120, 390, 900, 1100, 1275, "green", 200);
        blockTable[33] = new Action("decision", 33, "ΑΠΟΦΑΣΗ");
        blockTable[34] = new Room(320, "ΑΜΦ. 1", null, 34, 28, 150, 450, 1000, 12000, 1400, "green", 200);
        blockTable[35] = new Transport(35, "ΚΤΙΡΙΟ Δ - ΑΝΕΛΚΥΣΤΗΡΕΣ", 200, null);
        blockTable[36] = new Action("chance", 36, "ΕΝΤΟΛΗ");
        blockTable[37] = new Room(350, "ΑΜΦ. 13", null, 37, 35, 175, 500, 1100, 1300, 1500, "dark_blue", 200);
        blockTable[38] = new Tax(38, "ΦΟΡΟΣ - ΔΙΔΑΚΤΡΑ Β", 100);
        blockTable[39] = new Room(400, "ΑΜΦ. 14", null, 39, 50, 200, 600, 1400, 1700, 2000, "dark_blue", 200);
    }

}