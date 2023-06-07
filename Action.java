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

    public void executeAction(Player player) {
        Random rnd = new Random();
        if (type.equals("chance")) {
            if (excludedChance.size() == 16)
                excludedChance.clear();
            int random = getRandomWithExclusion(rnd, excludedChance);
            excludedChance.add(random);
            generateAction(random, player);
        }else if (type.equals("decision")){
            if (excludedDecision.size() == 16)
                excludedDecision.clear();
            int random = getRandomWithExclusion(rnd, excludedDecision);
            excludedDecision.add(random);
            generateAction(random, player);
        }else if (this.type.equals("go_to_jail")){
            Jail.sendToJail(player);
        }
    }


    public void generateAction(int number, Player player) {
        int currentPosition = player.currentBlock.blockPosition;
        if (this.type.equals("chance")) {
            switch (number) {
                case 0:
                    JOptionPane.showMessageDialog(null, "Προχώρησε στο ΑΜΦ. 14", "Επιλογή", JOptionPane.INFORMATION_MESSAGE);
                    GameBoard.movePlayer(player, 39, false);
                    break;
                case 1:
                    JOptionPane.showMessageDialog(null, "Πήγαινε στην ΑΙΘ. 2.\n" +
                            "Αν περάσεις από την αφετηρία πάρε 200$.", "Επιλογή", JOptionPane.INFORMATION_MESSAGE);
                    if (currentPosition > 4)
                        player.receiveStartPayment();
                    GameBoard.movePlayer(player, 4, false);
                    break;
                case 2:
                    JOptionPane.showMessageDialog(null, "Το κατασκευαστικό σου δάνειο τελείωσε. Πάρε 150$.", "Επιλογή", JOptionPane.INFORMATION_MESSAGE);
                    player.balance += 150;
                    break;
                case 3:
                    JOptionPane.showMessageDialog(null, "Προχώρησε στη θέση24.\n" +
                            "Αν περάσειςαπό την αφετηρία πάρε 200$.", "Επιλογή", JOptionPane.INFORMATION_MESSAGE);
                    if (currentPosition > 24)
                        player.receiveStartPayment();
                    GameBoard.movePlayer(player, 24, false);
                    break;
                case 4:
                    JOptionPane.showMessageDialog(null, """
                            Προχώρησε στην πιο κοντινή Υπηρεσία [μία από τις δύο θέσεις (θέση 12 (βιβλιοθήκη) ή 28(γυμναστήριο))].
                            Αν δεν ανήκει σε κανέναν , μπορείς να την αγοράσεις από την τράπεζα.
                            Αν ανήκει σε κάποιον, ρίξε τα ζάρια και πλήρωσε στον ιδιοκτήτη το διπλάσιο από αυτό που έφερες.""", "Επιλογή", JOptionPane.INFORMATION_MESSAGE);
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
                    JOptionPane.showMessageDialog(null, "Πήγαινε κατευθείαν στη φυλακή.\n" +
                            "Δεν περνάς από την αφετηρία δεν παίρνεις 200$.", "Επιλογή", JOptionPane.INFORMATION_MESSAGE);
                    Jail.sendToJail(player);
                    break;
                case 6:
                    JOptionPane.showMessageDialog(null, "Προχώρησε στην αφετηρία. Πάρε 200$.", "Επιλογή", JOptionPane.INFORMATION_MESSAGE);
                    GameBoard.movePlayer(player, 0, false);
                    player.receiveStartPayment();
                    break;
                case 7:
                    JOptionPane.showMessageDialog(null, "Κέρδισες υποτροφία από το πανεπιστήμιο. Πάρε 100$. ", "Επιλογή", JOptionPane.INFORMATION_MESSAGE);
                    player.balance += 100;
                    break;
                case 8:
                    JOptionPane.showMessageDialog(null, """
                            Κάνε γενικές επισκευές σε όλες τις ιδιοκτησίες σου:\s
                             i. Για κάθε έδρανο πλήρωσε 25$ και
                            ii. Για κάθε πίνακα πλήρωσε 100$.""", "Επιλογή", JOptionPane.INFORMATION_MESSAGE);
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
                    JOptionPane.showMessageDialog(null, "Βγες από την φυλακή ή κράτα αυτή τη κάρτα μέχρι να σου χρειαστεί.", "Επιλογή", JOptionPane.INFORMATION_MESSAGE);
                    if (Jail.isInJail(player))
                        player.exitPrisonWithCard();
                    else
                        player.outOfJailCards++;
                    break;
                case 10:
                    JOptionPane.showMessageDialog(null, "Πήγαινε στην ΑΙΘ, 6. Αν περάσειςαπό την αφετηρία πάρε 200$.", "Επιλογή", JOptionPane.INFORMATION_MESSAGE);
                    if (currentPosition > 11)
                        player.receiveStartPayment();
                    GameBoard.movePlayer(player, 11, false);
                    break;
                case 11:
                    JOptionPane.showMessageDialog(null, "Έχεις εκλεγεί πρόεδρος του διοικητικού συμβουλίου.\n" +
                            "Δώσε σε κάθε παίκτη 50$.", "Επιλογή", JOptionPane.INFORMATION_MESSAGE);
                    for (Player p : GameBoard.players) {
                        p.balance += 50;
                        player.balance -= 50;
                    }
                    break;
                case 12:
                    JOptionPane.showMessageDialog(null, "Πήγαινε πίσω 3 τετράγωνα.", "Επιλογή", JOptionPane.INFORMATION_MESSAGE);
                    GameBoard.movePlayer(player, -3, true);
                    break;
                case 13:
                    JOptionPane.showMessageDialog(null, "Πρόστιμο καταστροφής πανεπιστημιακής περιουσίας . Πλήρωσε 50$.", "Επιλογή", JOptionPane.INFORMATION_MESSAGE);
                    player.balance -= 50;
                    break;
                case 14:
                    JOptionPane.showMessageDialog(null, """
                            Προχώρησε στην πιο κοντινή Γραμματεία [μία από τις τέσσερις θέσεις (θέση 5, 15, 25 ή 35).
                            Αν δεν ανήκει σε κανέναν, μπορείς να την αγοράσεις από την τράπεζα.
                            Αν ανήκει σε κάποιον, πλήρωσε στον ιδιοκτήτη το διπλάσιο ενοίκιο από αυτό που δικαιούται.""", "Επιλογή", JOptionPane.INFORMATION_MESSAGE);
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
                    JOptionPane.showMessageDialog(null, "Έρανος για συνεισφορά στα παιδικά χωριά SOS. Πλήρωσε 80$.", "Επιλογή", JOptionPane.INFORMATION_MESSAGE);
                    player.balance -= 80;
            }
        }
        else if(this.type.equals("decision")){
            switch (number) {
                case 0 :
                    JOptionPane.showMessageDialog(null, "Βγες από την φυλακή ή κράτα αυτή τη κάρτα μέχρι να σου χρειαστεί.", "Απόφαση", JOptionPane.INFORMATION_MESSAGE);

                    if (Jail.isInJail(player))
                        player.exitPrisonWithCard();
                    else
                        player.outOfJailCards++;
                case 1:
                    JOptionPane.showMessageDialog(null, "Έξοδα καθαριότητας. Πλήρωσε 50$.", "Απόφαση", JOptionPane.INFORMATION_MESSAGE);
                    player.balance -= 50;
                    break;
                case 2 :
                    JOptionPane.showMessageDialog(null, "Η κάρτα σίτισης σου λήγει. Πλήρωσε 150$.", "Απόφαση", JOptionPane.INFORMATION_MESSAGE);
                    player.balance += 150;
                    break;
                case 3:
                    JOptionPane.showMessageDialog(null, "4.\tΗ ασφάλεια πυρός λήγει. Πλήρωσε 100$.", "Απόφαση", JOptionPane.INFORMATION_MESSAGE);
                    player.balance -= 100;
                    break;
                case 4:
                    JOptionPane.showMessageDialog(null, "Ανανέωση διδάκτρων. Πλήρωσε 50$.", "Απόφαση", JOptionPane.INFORMATION_MESSAGE);
                    player.balance -= 50;
                    break;
                case 5:
                    JOptionPane.showMessageDialog(null, "Επιστροφή φόρου. Πάρε 20$.", "Απόφαση", JOptionPane.INFORMATION_MESSAGE);
                    player.balance += 20;
                    break;
                case 6:
                    JOptionPane.showMessageDialog(null, "Έξοδα ανακατασκευής κυλικείου. Πλήρωσε 100$.", "Απόφαση", JOptionPane.INFORMATION_MESSAGE);
                    player.balance -= 100;
                    break;
                case 7 :
                    JOptionPane.showMessageDialog(null, "Πήγαινε κατευθείαν στη φυλακή. Δεν περνάς από την αφετηρία δεν παίρνεις 200$.", "Απόφαση", JOptionPane.INFORMATION_MESSAGE);
                    Jail.sendToJail(player);
                    break;
                case 8 :
                    JOptionPane.showMessageDialog(null, "Πάρε 30$, ως αμοιβή συμβούλου.", "Απόφαση", JOptionPane.INFORMATION_MESSAGE);
                    player.balance += 30;
                    break;
                case 9 :
                    JOptionPane.showMessageDialog(null, "Ορκίζεσαι στο πτυχίο σου. Πάρε 10$ από κάθε παίκτη.", "Απόφαση", JOptionPane.INFORMATION_MESSAGE);
                    for (Player p : GameBoard.players)
                        player.balance += 10;
                    break;
                case 10 :
                    JOptionPane.showMessageDialog(null, "Έγινες δεκτός στην πανεπιστημιακή λέσχη. Πάρε 50$.", "Απόφαση", JOptionPane.INFORMATION_MESSAGE);
                    player.balance += 50;
                    break;
                case 11 :
                    JOptionPane.showMessageDialog(null, "Εκλέχτηκες κοσμήτορας. Πάρε 100$.", "Απόφαση", JOptionPane.INFORMATION_MESSAGE);
                    player.balance += 100;
                    break;
                case 12 :
                    JOptionPane.showMessageDialog(null, "Κέρδισες το δεύτερο βραβείο στο διαγωνισμό Κυβερνοασφάλειας. Πάρε 10$.", "Απόφαση", JOptionPane.INFORMATION_MESSAGE);
                    player.balance += 10;
                    break;
                case 13 :
                    JOptionPane.showMessageDialog(null, """
                            14.Κάνε γενικές επισκευές σε όλες τις ιδιοκτησίες σου:\s
                             i. Για κάθε έδρανο πλήρωσε 40$ και
                            ii. Γγια κάθε πίνακα πλήρωσε 115$.
                            """, "Απόφαση", JOptionPane.INFORMATION_MESSAGE);
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
                    break;
                case 14 :
                    JOptionPane.showMessageDialog(null, "Πήγαινε στην αφετηρία. Πάρε 200$.", "Απόφαση", JOptionPane.INFORMATION_MESSAGE);
                    GameBoard.movePlayer(player,0, false);
                    player.receiveStartPayment();
                    break;
                default :
                    JOptionPane.showMessageDialog(null, "Τραπεζικό λάθος υπέρ σου. Πάρε 200$.", "Απόφαση", JOptionPane.INFORMATION_MESSAGE);
                    player.balance += 200;
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