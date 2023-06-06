import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Insets;

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

    private GameBoard board;

    public MainBoard(){


        setContentPane(container);
        setTitle("UoMPoly");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(1180, 1080);
        setVisible(true);

    }

    public void updatePos(Block currBlock){
        currentPos.setText(currBlock.blockPosition + " - " + currBlock.getBlockTitle());
    }





}
