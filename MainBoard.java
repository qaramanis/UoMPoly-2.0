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

    private Player currentPlayer;

    private void createUIComponents() {
        // TODO: place custom component creation code here
    }

    private enum ScreenState {
        NO_BTNS,
        UNOWNED_PROPERTY,
        JAILED
    }

    private ScreenState currentState = ScreenState.NO_BTNS;

    public MainBoard(){
        updateScreenState();

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
                new BuyPropertyScreen(currentPlayer);
            }
        });
    }

    public void updateScreenState(){
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
                bottomButtons.setVisible(false);
                break;
        }
    }

    public void updatePos(Block currBlock){
        currentPos.setText(currBlock.blockPosition + 1 + " - " + currBlock.getBlockTitle());
    }

    public void updatePlayer(Player currPlayer){
        currentPlayer = currPlayer;
        playerNumber.setText(Integer.toString(currPlayer.getPlayerID()));
        updateBalance(currPlayer.getBalance());
        updatePos(currPlayer.currentBlock);
    }

    public void updateBalance(int balance){
        currentBalance.setText(Integer.toString(balance));
    }

    public void updateInfo(Player currPlayer){
        updatePlayer(currPlayer);
        Block currBlock = currentPlayer.currentBlock;
        if(currBlock instanceof Property){
            if(((Property)currBlock).getOwner() == null){
                currentState = ScreenState.UNOWNED_PROPERTY;
                updateScreenState();
            }
        }


    }


    

}
