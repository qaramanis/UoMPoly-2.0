import javax.swing.*;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainBoard extends JFrame {
    private JPanel container;
    private JPanel top;
    private JPanel middle;
    private JPanel bottom;
    private JPanel topLeft;
    private JPanel topMiddle;
    private JLabel playerNumber;
    private JLabel currentBalance;
    private JPanel topRight;
    private JButton propertyBtn;
    private JPanel dice;
    private JLabel diceNumbers;
    private JPanel bottomButtons;
    private JPanel tooltips;
    private JPanel tooltipLeft;
    private JLabel currentPos;
    private JPanel tooltipRight;
    private JLabel tooltipRightLabel;
    private JButton jailCardBtn;
    private JButton jailPayBtn;
    private JButton buyPropBtn;
    private JButton endTurnBtn;
    private JLabel boardLabel;
    private JPanel topMiddle2;
    private JLabel outOfJailCardsLabel;

    private Player currentPlayer;
    private int[] currentDice;

    private GameBoard gBoard;

    private void createUIComponents() {
        // TODO: place custom component creation code here
    }

    private enum ScreenState {
        NO_BTNS,
        DEFAULT,
        UNOWNED_PROPERTY,
        JAILED
    }

    private ScreenState currentState = ScreenState.DEFAULT;

    public MainBoard(){
        handleScreenState();

        setContentPane(container);
        setTitle("UoMPoly");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        pack();
        setPreferredSize(new Dimension(1380, 1080));
        setLocationRelativeTo(null);
        setVisible(true);

        MainBoard mBoard = this;
        propertyBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new PropertiesPane(currentPlayer, mBoard);
            }
        });
        buyPropBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new BuyPropertyScreen(currentPlayer, mBoard);
            }
        });
        endTurnBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                gBoard.setTurnActive(false);
            }
        });
        endTurnBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                gBoard.setTurnActive(false);
            }
        });
        jailCardBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                if(currentPlayer.getOutOfJailCards() > 0){
                    currentPlayer.decreaseGetOutOfJailCards();
                    Jail.removeFromJail(currentPlayer);
                }
            }
        });
        jailPayBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                if (currentPlayer.getBalance() >= 50) {
                    Jail.removeFromJail(currentPlayer);
                    currentPlayer.decreaseBalance(50);
                    GameBoard.movePlayer(currentPlayer, currentDice[0] + currentDice[1], true);
                    updateInfo(currentPlayer);
                } else {
                    JOptionPane.showMessageDialog(null, "Τα λεφτά σου δεν επαρκούν για να βγεις από την φυλακή!", "Φυλακή", JOptionPane.INFORMATION_MESSAGE);
                }
            }
        });
    }

    public void handleScreenState(){
        buyPropBtn.setVisible(false);
        endTurnBtn.setVisible(false);
        jailPayBtn.setVisible(false);
        jailCardBtn.setVisible(false);

        switch (currentState){
            case NO_BTNS:
                bottomButtons.setVisible(false);
                break;
            case UNOWNED_PROPERTY:
                buyPropBtn.setVisible(true);
                endTurnBtn.setVisible(true);
                bottomButtons.setVisible(true);
                break;
            case JAILED:
                jailPayBtn.setVisible(true);
                jailCardBtn.setVisible(true);
                jailCardBtn.setEnabled(false);
                bottomButtons.setVisible(true);
                if(currentPlayer.getOutOfJailCards() > 0) jailCardBtn.setEnabled(true);
            default:
                bottomButtons.setVisible(true);
                endTurnBtn.setVisible(true);
                break;
        }
    }

    public void updatePos(){
        int pos = currentPlayer.getCurrentBlock().getBlockPosition();
        String posTitle = currentPlayer.getCurrentBlock().getBlockTitle();

        if(pos == 10 && !Jail.isInJail(currentPlayer)) posTitle = currentPlayer.getCurrentBlock().getBlockTitle() + " (Μόνο για επίσκεψη)";

        currentPos.setText((pos + 1) + " - " + posTitle);
        updateScreenState();
        updateBoardImage();

    }

    public void updatePlayer(Player currPlayer){
        currentPlayer = currPlayer;
        playerNumber.setText(Integer.toString(currPlayer.getPlayerID()));
        outOfJailCardsLabel.setText(currentPlayer.getOutOfJailCards() + "");
        updateBalance(currPlayer.getBalance());
        updatePos();
    }

    public void updateBalance(int balance){
        currentBalance.setText(Integer.toString(balance));
    }

    public void updateInfo(Player currPlayer){
        updatePlayer(currPlayer);
        updateScreenState();
    }

    public void updateEndTurnEnabled(boolean val){
        endTurnBtn.setEnabled(val);
    }

    public void updateDice(int[] dice, boolean loading){
         if(loading)
             diceNumbers.setText(". . .");
         else {
             currentDice = dice;
             diceNumbers.setText(dice[0] + " - " + dice[1]);
         }

    }

    public void updateBoardImage(){
        int currentPos = currentPlayer.getCurrentBlock().getBlockPosition();
        if(0 <= currentPos && currentPos <= 10) boardLabel.setIcon(new ImageIcon("./resources/0-10Board.png"));
        if(11 <= currentPos && currentPos <= 20) boardLabel.setIcon(new ImageIcon("./resources/11-20Board.png"));
        if(21 <= currentPos && currentPos <= 30) boardLabel.setIcon(new ImageIcon("./resources/21-30Board.png"));
        if(31 <= currentPos && currentPos <= 39) boardLabel.setIcon(new ImageIcon("./resources/31-39Board.png"));
    }

    public void updateScreenState(){
        Block currBlock = currentPlayer.getCurrentBlock();
        if(currBlock instanceof Property prop){
            if(prop.getOwner() == null){
                currentState = ScreenState.UNOWNED_PROPERTY;
            }else{
                currentState = ScreenState.DEFAULT;
            }
        }
        else if(Jail.isInJail(currentPlayer)) {
            currentState = ScreenState.JAILED;

        }else currentState = ScreenState.DEFAULT;

        handleScreenState();
    }

    public void setgBoard(GameBoard gBoard){
        this.gBoard = gBoard;
    }



}
