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
    private JButton jailDiceBtn;
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

    private Player currentPlayer;

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
    }

    public void handleScreenState(){
        buyPropBtn.setVisible(false);
        endTurnBtn.setVisible(false);
        jailPayBtn.setVisible(false);
        jailCardBtn.setVisible(false);
        jailDiceBtn.setVisible(false);

        switch (currentState){
            case NO_BTNS:
                bottomButtons.setVisible(false);
                break;
            case UNOWNED_PROPERTY:
                buyPropBtn.setVisible(true);
                endTurnBtn.setVisible(true);
                bottomButtons.setVisible(true);
                break;
            default:
                bottomButtons.setVisible(true);
                endTurnBtn.setVisible(true);
                break;
        }
    }

    public void updatePos(){
        currentPos.setText(currentPlayer.currentBlock.blockPosition + 1 + " - " + currentPlayer.currentBlock.getBlockTitle());
        updateScreenState();
        updateBoardImage();
    }

    public void updatePlayer(Player currPlayer){
        currentPlayer = currPlayer;
        playerNumber.setText(Integer.toString(currPlayer.getPlayerID()));
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

    public void updateDice(int[] dice){
        diceNumbers.setText(dice[0] + " - " + dice[1]);
    }

    public void updateBoardImage(){
        int currentPos = currentPlayer.currentBlock.blockPosition;
        if(0 <= currentPos && currentPos <= 10) boardLabel.setIcon(new ImageIcon("./resources/0-10Board.png"));
        if(11 <= currentPos && currentPos <= 20) boardLabel.setIcon(new ImageIcon("./resources/11-20Board.png"));
        if(21 <= currentPos && currentPos <= 30) boardLabel.setIcon(new ImageIcon("./resources/21-30Board.png"));
        if(31 <= currentPos && currentPos <= 39) boardLabel.setIcon(new ImageIcon("./resources/31-39Board.png"));
    }

    public void updateScreenState(){
        Block currBlock = currentPlayer.currentBlock;
        if(currBlock instanceof Property prop){
            if(prop.getOwner() == null){
                currentState = ScreenState.UNOWNED_PROPERTY;
                handleScreenState();
            }else{
                currentState = ScreenState.DEFAULT;
                handleScreenState();
            }
        }
    }


    public void setgBoard(GameBoard gBoard){
        this.gBoard = gBoard;
    }



}
