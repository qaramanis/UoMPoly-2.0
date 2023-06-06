import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Insets;
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
    private JPanel rightButton;
    private JPanel middleButton;
    private JPanel LeftButton;
    private JButton diceBtn;
    private JPanel tooltips;
    private JPanel tooltipLeft;
    private JLabel currentPos;
    private JPanel tooltipRight;
    private JLabel tooltipRightLabel;

    private Player currentPlayer;

    public MainBoard(){


        setContentPane(container);
        setTitle("UoMPoly");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(1280, 1080);
        setVisible(true);

        MainBoard mBoard = this;
        propertyBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new PropertiesPane(currentPlayer, mBoard);
            }
        });
    }

    public void updatePos(Block currBlock){
        currentPos.setText(currBlock.blockPosition + " - " + currBlock.getBlockTitle());
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
    }





}
