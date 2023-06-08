import javax.swing.*;
import java.util.*;
public class Action extends Block {

    private static ArrayList<Integer> excludedChance = new ArrayList<Integer>();

    private static ArrayList<Integer> excludedDecision = new ArrayList<Integer>();

    private String type;

    public Action (String type, int position, String title){
        super(position, title);
        this.type = type;
    }
    public String getType() {
        return type;
    }

    public void executeAction(Player player, MainBoard mBoard) {
        Random rnd = new Random();
        if (type.equals("chance")) {
            if (excludedChance.size() == 16)
                excludedChance.clear();
            int random = getRandomWithExclusion(rnd, excludedChance);
            excludedChance.add(random);
            generateAction(random, player, mBoard);
            mBoard.updateInfo(player);
        }else if (type.equals("decision")){
            if (excludedDecision.size() == 16)
                excludedDecision.clear();
            int random = getRandomWithExclusion(rnd, excludedDecision);
            excludedDecision.add(random);
            generateAction(random, player, mBoard);
            mBoard.updateInfo(player);
        }else if (this.type.equals("go_to_jail")){
            JOptionPane.showMessageDialog(null, "Πήγαινε κατευθείαν στη φυλακή.\n" +
                    "Δεν περνάς από την αφετηρία δεν παίρνεις 200€.", "Εντολή", JOptionPane.INFORMATION_MESSAGE);
            Jail.sendToJail(player);
            GameBoard.setTurnActive(false);
            mBoard.updatePos();
        }
    }


    public void generateAction(int number, Player player, MainBoard mBoard) {
        int currentPosition = player.getCurrentBlock().getBlockPosition();
        if (this.type.equals("chance")) {
            switch (number) {
                case 0 -> {
                    JOptionPane.showMessageDialog(null, "Προχώρησε στο ΑΜΦ. 14", "Εντολή", JOptionPane.INFORMATION_MESSAGE);
                    GameBoard.movePlayer(player, 39, false);
                }
                case 1 -> {
                    JOptionPane.showMessageDialog(null, "Πήγαινε στην ΑΙΘ. 2.\n" +
                            "Αν περάσεις από την αφετηρία πάρε 200€.", "Εντολή", JOptionPane.INFORMATION_MESSAGE);
                    if (currentPosition > 3)
                        Start.receiveStartPayment(player);
                    GameBoard.movePlayer(player, 3, false);
                }
                case 2 -> {
                    JOptionPane.showMessageDialog(null, "Το κατασκευαστικό σου δάνειο τελείωσε. Πάρε 150€.", "Εντολή", JOptionPane.INFORMATION_MESSAGE);
                    player.increaseBalance(150);
                }
                case 3 -> {
                    JOptionPane.showMessageDialog(null, "Προχώρησε στην Αιθ. Τηλεκπαίδευσης.\n" +
                            "Αν περάσεις από την αφετηρία πάρε 200€.", "Εντολή", JOptionPane.INFORMATION_MESSAGE);
                    if (currentPosition > 23)
                        Start.receiveStartPayment(player);
                    GameBoard.movePlayer(player, 23, false);
                }
                case 4 -> {
                    JOptionPane.showMessageDialog(null, """
                            Προχώρησε στην πιο κοντινή Υπηρεσία [μία από τις δύο θέσεις (θέση 13 (Κυλικείο) ή 28(Φωτοτυπείο))].
                            Αν δεν ανήκει σε κανέναν , μπορείς να την αγοράσεις από την τράπεζα.
                            Αν ανήκει σε κάποιον, ρίξε τα ζάρια και πλήρωσε στον ιδιοκτήτη το διπλάσιο από αυτό που έφερες.""", "Εντολή", JOptionPane.INFORMATION_MESSAGE);
                    if (Math.abs(27 - currentPosition) < Math.abs(12 - currentPosition))
                        GameBoard.movePlayer(player, 27, false);
                    else
                        GameBoard.movePlayer(player, 12, false);
                    mBoard.updatePos();
                    if (((Service) player.getCurrentBlock()).getOwner() != null && ((Service) player.getCurrentBlock()).getOwner().equals(player)) {
                        int[] dice = player.rollTheDice();
                        int rent = ((Service) player.getCurrentBlock()).calculateRent(dice);
                        player.payRent(((Service) player.getCurrentBlock()).owner, rent);
                    }
                }
                case 5 -> {
                    JOptionPane.showMessageDialog(null, "Πήγαινε κατευθείαν στη φυλακή.\n" +
                            "Δεν περνάς από την αφετηρία δεν παίρνεις 200€.", "Εντολή", JOptionPane.INFORMATION_MESSAGE);
                    Jail.sendToJail(player);
                    mBoard.updatePos();
                }
                case 6 -> {
                    JOptionPane.showMessageDialog(null, "Προχώρησε στην αφετηρία. Πάρε 200€.", "Εντολή", JOptionPane.INFORMATION_MESSAGE);
                    GameBoard.movePlayer(player, 0, false);
                    Start.receiveStartPayment(player);
                }
                case 7 -> {
                    JOptionPane.showMessageDialog(null, "Κέρδισες υποτροφία από το πανεπιστήμιο. Πάρε 100€.", "Εντολή", JOptionPane.INFORMATION_MESSAGE);
                    player.increaseBalance(100);
                }
                case 8 -> {
                    JOptionPane.showMessageDialog(null, """
                            Κάνε γενικές επισκευές σε όλες τις ιδιοκτησίες σου:\s
                             i. Για κάθε έδρανο πλήρωσε 25€ και
                            ii. Για κάθε πίνακα πλήρωσε 100€.""", "Εντολή", JOptionPane.INFORMATION_MESSAGE);
                    int desks = 0;
                    int boards = 0;
                    for (Property p : player.getProperties()) {
                        if (p instanceof Room room) {
                            if (room.hasBoard())
                                boards += 1;
                            else
                                desks += room.getNumberOfDesks();
                        }
                    }
                    int payment = 25 * desks + 100 * boards;
                    if (player.getBalance() >= payment) {
                        player.decreaseBalance(payment);
                    } else {
                        GameBoard.players.remove(player);
                        JOptionPane.showMessageDialog(null, "Τα λεφτά σου δεν επαρκούν για να πληρώσεις για τις επισκευές!\n Βγαίνεις από το παιχνίδι.", "Xρεωκοπία! :(", JOptionPane.INFORMATION_MESSAGE);
                        GameBoard.setTurnActive(false);
                    }
                }
                case 9 -> {
                    JOptionPane.showMessageDialog(null, "Βγες από την φυλακή ή κράτα αυτή τη κάρτα μέχρι να σου χρειαστεί.", "Εντολή", JOptionPane.INFORMATION_MESSAGE);
                    player.increaseGetOutOfJailCards();
                    mBoard.updatePlayer(player);
                }
                case 10 -> {
                    JOptionPane.showMessageDialog(null, "Πήγαινε στην ΑΙΘ. 6. Αν περάσεις από την αφετηρία πάρε 200€.", "Εντολή", JOptionPane.INFORMATION_MESSAGE);
                    if (currentPosition > 11)
                        Start.receiveStartPayment(player);
                    GameBoard.movePlayer(player, 11, false);
                }
                case 11 -> {
                    JOptionPane.showMessageDialog(null, "Έχεις εκλεγεί πρόεδρος του διοικητικού συμβουλίου.\n" +
                            "Δώσε σε κάθε παίκτη 50€.", "Εντολή", JOptionPane.INFORMATION_MESSAGE);
                    int total = 50 * GameBoard.players.size() - 1;
                    if (player.getBalance() >= total) {
                        player.decreaseBalance(total);
                        for (Player p : GameBoard.players) {
                            if (!p.equals(player))
                                p.increaseBalance(50);
                        }
                    } else {
                        GameBoard.players.remove(player);
                        JOptionPane.showMessageDialog(null, "Τα λεφτά σου δεν επαρκούν για να πληρώσεις τους άλλους παίκτες!\n Βγαίνεις από το παιχνίδι.", "Xρεωκοπία! :(", JOptionPane.INFORMATION_MESSAGE);
                        GameBoard.setTurnActive(false);
                    }
                }
                case 12 -> {
                    JOptionPane.showMessageDialog(null, "Πήγαινε πίσω 3 τετράγωνα.", "Εντολή", JOptionPane.INFORMATION_MESSAGE);
                    GameBoard.movePlayer(player, -3, true);
                }
                case 13 -> {
                    JOptionPane.showMessageDialog(null, "Πρόστιμο καταστροφής πανεπιστημιακής περιουσίας. Πλήρωσε 50€.", "Εντολή", JOptionPane.INFORMATION_MESSAGE);
                    if (player.getBalance() >= 50) {
                        player.decreaseBalance(50);
                    } else {
                        GameBoard.players.remove(player);
                        JOptionPane.showMessageDialog(null, "Τα λεφτά σου δεν επαρκούν για να πληρώσεις το πρόστιμο!\n Βγαίνεις από το παιχνίδι.", "Xρεωκοπία! :(", JOptionPane.INFORMATION_MESSAGE);
                        GameBoard.setTurnActive(false);
                    }
                }
                case 14 -> {
                    JOptionPane.showMessageDialog(null, """
                            Προχώρησε στους πιο κοντινούς Ανελκυστήρες σε μία από τις τέσσερις θέσεις (θέση 6, 16, 26 ή 36).
                            Αν δεν ανήκει σε κανέναν, μπορείς να τους αγοράσεις από την τράπεζα.
                            Αν ανήκει σε κάποιον, πλήρωσε στον ιδιοκτήτη το διπλάσιο ενοίκιο από αυτό που δικαιούται.""", "Εντολή", JOptionPane.INFORMATION_MESSAGE);
                    int minDistance = Math.abs(5 - currentPosition);
                    int tempPos;
                    int closestPos = 5;
                    for (int i = 0; i < 3; i++) {
                        tempPos = switch (i) {
                            case 0 -> 15;
                            case 1 -> 25;
                            default -> 35;
                        };
                        if (Math.abs(tempPos - currentPosition) < minDistance) {
                            minDistance = Math.abs(tempPos - currentPosition);
                            closestPos = tempPos;
                        }
                    }
                    GameBoard.movePlayer(player, closestPos, false);
                    Transport currentAsTransp = (Transport) player.getCurrentBlock();
                    if (currentAsTransp.owner != null && !currentAsTransp.owner.equals(player)) {
                        int rent = currentAsTransp.calculateRent();
                        player.payRent(currentAsTransp.owner, rent);
                    }
                }
                case 15 -> {
                    JOptionPane.showMessageDialog(null, "Έρανος για συνεισφορά στα παιδικά χωριά SOS. Πλήρωσε 80€.", "Εντολή", JOptionPane.INFORMATION_MESSAGE);
                    if (player.getBalance() >= 80) {
                        player.decreaseBalance(80);
                    } else {
                        GameBoard.players.remove(player);
                        JOptionPane.showMessageDialog(null, "Τα λεφτά σου δεν επαρκούν για να συνεισφέρεις στον έρανο!\n Βγαίνεις από το παιχνίδι.", "Xρεωκοπία! :(", JOptionPane.INFORMATION_MESSAGE);
                        GameBoard.setTurnActive(false);
                    }
                }
            }
        }
        else if(this.type.equals("decision")){
            switch (number) {
                case 0 :
                    JOptionPane.showMessageDialog(null, "Βγες από την φυλακή ή κράτα αυτή τη κάρτα μέχρι να σου χρειαστεί.", "Απόφαση", JOptionPane.INFORMATION_MESSAGE);
                    player.increaseGetOutOfJailCards();
                    mBoard.updatePlayer(player);
                case 1:
                    JOptionPane.showMessageDialog(null, "Έξοδα καθαριότητας. Πλήρωσε 50€.", "Απόφαση", JOptionPane.INFORMATION_MESSAGE);
                    if (player.getBalance() >= 50) {
                        player.decreaseBalance(50);
                    } else {
                        GameBoard.players.remove(player);
                        JOptionPane.showMessageDialog(null, "Τα λεφτά σου δεν επαρκούν για να καλύψεις τα έξοδα!\n Βγαίνεις από το παιχνίδι.", "Xρεωκοπία! :(", JOptionPane.INFORMATION_MESSAGE);
                        GameBoard.setTurnActive(false);
                    }
                    break;
                case 2 :
                    JOptionPane.showMessageDialog(null, "Η κάρτα σίτισης σου λήγει. Πλήρωσε 150€.", "Απόφαση", JOptionPane.INFORMATION_MESSAGE);
                    if (player.getBalance() >= 150) {
                        player.decreaseBalance(150);
                    } else {
                        GameBoard.players.remove(player);
                        JOptionPane.showMessageDialog(null, "Τα λεφτά σου δεν επαρκούν για να ανανεώσεις την κάρτα σίτισης!\n Βγαίνεις από το παιχνίδι.", "Xρεωκοπία! :(", JOptionPane.INFORMATION_MESSAGE);
                        GameBoard.setTurnActive(false);
                    }
                    break;
                case 3:
                    JOptionPane.showMessageDialog(null, "Η ασφάλεια πυρός λήγει. Πλήρωσε 100€.", "Απόφαση", JOptionPane.INFORMATION_MESSAGE);
                    if (player.getBalance() >= 100) {
                        player.decreaseBalance(100);
                    } else {
                        GameBoard.players.remove(player);
                        JOptionPane.showMessageDialog(null, "Τα λεφτά σου δεν επαρκούν για να ανανεώσεις την ασφάλεια πυρός!\n Βγαίνεις από το παιχνίδι.", "Xρεωκοπία! :(", JOptionPane.INFORMATION_MESSAGE);
                        GameBoard.setTurnActive(false);
                    }
                    break;
                case 4:
                    JOptionPane.showMessageDialog(null, "Ανανέωση διδάκτρων. Πλήρωσε 50€.", "Απόφαση", JOptionPane.INFORMATION_MESSAGE);
                    if (player.getBalance() >= 50) {
                        player.decreaseBalance(50);
                    } else {
                        GameBoard.players.remove(player);
                        JOptionPane.showMessageDialog(null, "Τα λεφτά σου δεν επαρκούν για να ανανεώσεις τα δίδακτρα!\n Βγαίνεις από το παιχνίδι.", "Xρεωκοπία! :(", JOptionPane.INFORMATION_MESSAGE);
                        GameBoard.setTurnActive(false);
                    }
                    break;
                case 5:
                    JOptionPane.showMessageDialog(null, "Επιστροφή φόρου. Πάρε 20€.", "Απόφαση", JOptionPane.INFORMATION_MESSAGE);
                    player.increaseBalance(20);
                    break;
                case 6:
                    JOptionPane.showMessageDialog(null, "Έξοδα ανακατασκευής κυλικείου. Πλήρωσε 100€.", "Απόφαση", JOptionPane.INFORMATION_MESSAGE);
                    if (player.getBalance() >= 100) {
                        player.decreaseBalance(100);
                    } else {
                        GameBoard.players.remove(player);
                        JOptionPane.showMessageDialog(null, "Τα λεφτά σου δεν επαρκούν για να πληρώσεις!\n Βγαίνεις από το παιχνίδι.", "Xρεωκοπία! :(", JOptionPane.INFORMATION_MESSAGE);
                        GameBoard.setTurnActive(false);
                    }
                    break;
                case 7 :
                    JOptionPane.showMessageDialog(null, "Πήγαινε κατευθείαν στη φυλακή. Δεν περνάς από την αφετηρία δεν παίρνεις 200€.", "Απόφαση", JOptionPane.INFORMATION_MESSAGE);
                    Jail.sendToJail(player);
                    GameBoard.setTurnActive(false);
                    break;
                case 8 :
                    JOptionPane.showMessageDialog(null, "Πάρε 30€, ως αμοιβή συμβούλου.", "Απόφαση", JOptionPane.INFORMATION_MESSAGE);
                    player.increaseBalance(30);
                    break;
                case 9 :
                    JOptionPane.showMessageDialog(null, "Ορκίζεσαι στο πτυχίο σου. Πάρε 10€ από κάθε παίκτη.", "Απόφαση", JOptionPane.INFORMATION_MESSAGE);
                    for (Player p : GameBoard.players) {
                        if(!p.equals(player)) {
                            p.decreaseBalance(10);
                            player.increaseBalance(10);
                        }
                    }
                    break;
                case 10 :
                    JOptionPane.showMessageDialog(null, "Έγινες δεκτός στην πανεπιστημιακή λέσχη. Πάρε 50€.", "Απόφαση", JOptionPane.INFORMATION_MESSAGE);
                    player.increaseBalance(50);
                    break;
                case 11 :
                    JOptionPane.showMessageDialog(null, "Εκλέχτηκες κοσμήτορας. Πάρε 100€.", "Απόφαση", JOptionPane.INFORMATION_MESSAGE);
                    player.increaseBalance(100);
                    break;
                case 12 :
                    JOptionPane.showMessageDialog(null, "Κέρδισες το δεύτερο βραβείο στο διαγωνισμό Κυβερνοασφάλειας. Πάρε 10€.", "Απόφαση", JOptionPane.INFORMATION_MESSAGE);
                    player.increaseBalance(10);
                    break;
                case 13 :
                    JOptionPane.showMessageDialog(null, """
                            Κάνε γενικές επισκευές σε όλες τις ιδιοκτησίες σου:\s
                             i. Για κάθε έδρανο πλήρωσε 40€ και
                            ii. Γγια κάθε πίνακα πλήρωσε 115€.
                            """, "Απόφαση", JOptionPane.INFORMATION_MESSAGE);
                    int desks = 0;
                    int boards = 0;
                    for (Property p : player.getProperties()) {
                        if (p instanceof Room room) {
                            if (room.hasBoard())
                                boards += 1;
                            else
                                desks += room.getNumberOfDesks();
                        }
                    }
                    int payment = 40 * desks + 115 * boards;
                    if (player.getBalance() >= payment) {
                        player.decreaseBalance(payment);
                    } else {
                        GameBoard.players.remove(player);
                        JOptionPane.showMessageDialog(null, "Τα λεφτά σου δεν επαρκούν για να πληρώσεις για τις επισκευές!\n Βγαίνεις από το παιχνίδι.", "Xρεωκοπία! :(", JOptionPane.INFORMATION_MESSAGE);
                        GameBoard.setTurnActive(false);
                    }
                    break;
                case 14 :
                    JOptionPane.showMessageDialog(null, "Πήγαινε στην αφετηρία. Πάρε 200€.", "Απόφαση", JOptionPane.INFORMATION_MESSAGE);
                    GameBoard.movePlayer(player,0, false);
                    Start.receiveStartPayment(player);
                    break;
                case 15:
                    JOptionPane.showMessageDialog(null, "Τραπεζικό λάθος υπέρ σου. Πάρε 200€.", "Απόφαση", JOptionPane.INFORMATION_MESSAGE);
                    player.increaseBalance(200);
                    break;
            }
        }
    }

    public int getRandomWithExclusion(Random rnd, ArrayList<Integer> exclude){
        int random = rnd.nextInt(16 - exclude.size());
        for(int ex : exclude){
            if(random < ex){
                break;
            }
            random ++;
        }
        return random;
    }

}