package gui;

import files.*;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import javax.swing.plaf.FontUIResource;
import javax.swing.text.StyleContext;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Locale;

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

    {
// GUI initializer generated by IntelliJ IDEA GUI Designer
// >>> IMPORTANT!! <<<
// DO NOT EDIT OR ADD ANY CODE HERE!
        $$$setupUI$$$();
    }

    /**
     * Method generated by IntelliJ IDEA GUI Designer
     * >>> IMPORTANT!! <<<
     * DO NOT edit this method OR call it in your code!
     *
     * @noinspection ALL
     */
    private void $$$setupUI$$$() {
        container = new JPanel();
        container.setLayout(new com.intellij.uiDesigner.core.GridLayoutManager(4, 1, new Insets(0, 0, 0, 0), -1, -1));
        container.setEnabled(true);
        top = new JPanel();
        top.setLayout(new com.intellij.uiDesigner.core.GridLayoutManager(1, 3, new Insets(0, 60, 0, 60), -1, -1));
        container.add(top, new com.intellij.uiDesigner.core.GridConstraints(0, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_NORTH, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, null, new Dimension(-1, 100), new Dimension(-1, 100), 0, false));
        top.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.black), null, TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.DEFAULT_POSITION, null, null));
        topLeft = new JPanel();
        topLeft.setLayout(new com.intellij.uiDesigner.core.GridLayoutManager(1, 2, new Insets(0, 0, 0, 0), -1, -1));
        top.add(topLeft, new com.intellij.uiDesigner.core.GridConstraints(0, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_BOTH, 1, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        final JLabel label1 = new JLabel();
        Font label1Font = this.$$$getFonts$$$("Inter", -1, 14, label1.getFont());
        if (label1Font != null) label1.setFont(label1Font);
        label1.setText("Παίκτης: ");
        topLeft.add(label1, new com.intellij.uiDesigner.core.GridConstraints(0, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        playerNumber = new JLabel();
        Font playerNumberFont = this.$$$getFonts$$$("Inter", -1, 16, playerNumber.getFont());
        if (playerNumberFont != null) playerNumber.setFont(playerNumberFont);
        playerNumber.setText("");
        topLeft.add(playerNumber, new com.intellij.uiDesigner.core.GridConstraints(0, 1, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        topMiddle = new JPanel();
        topMiddle.setLayout(new com.intellij.uiDesigner.core.GridLayoutManager(1, 3, new Insets(0, 15, 0, 0), -1, -1));
        top.add(topMiddle, new com.intellij.uiDesigner.core.GridConstraints(0, 1, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST, com.intellij.uiDesigner.core.GridConstraints.FILL_VERTICAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        final JLabel label2 = new JLabel();
        Font label2Font = this.$$$getFonts$$$("Inter", -1, 14, label2.getFont());
        if (label2Font != null) label2.setFont(label2Font);
        label2.setText("Πορτοφόλι: ");
        topMiddle.add(label2, new com.intellij.uiDesigner.core.GridConstraints(0, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        currentBalance = new JLabel();
        Font currentBalanceFont = this.$$$getFonts$$$("Inter", -1, 16, currentBalance.getFont());
        if (currentBalanceFont != null) currentBalance.setFont(currentBalanceFont);
        currentBalance.setText("");
        topMiddle.add(currentBalance, new com.intellij.uiDesigner.core.GridConstraints(0, 1, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        topMiddle2 = new JPanel();
        topMiddle2.setLayout(new com.intellij.uiDesigner.core.GridLayoutManager(1, 2, new Insets(0, 15, 0, 0), -1, -1));
        topMiddle.add(topMiddle2, new com.intellij.uiDesigner.core.GridConstraints(0, 2, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST, com.intellij.uiDesigner.core.GridConstraints.FILL_VERTICAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        final JLabel label3 = new JLabel();
        Font label3Font = this.$$$getFonts$$$("Inter", -1, 14, label3.getFont());
        if (label3Font != null) label3.setFont(label3Font);
        label3.setText("Κάρτες Βγες από την φυλακή: ");
        topMiddle2.add(label3, new com.intellij.uiDesigner.core.GridConstraints(0, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        outOfJailCardsLabel = new JLabel();
        Font outOfJailCardsLabelFont = this.$$$getFonts$$$("Inter", -1, 16, outOfJailCardsLabel.getFont());
        if (outOfJailCardsLabelFont != null) outOfJailCardsLabel.setFont(outOfJailCardsLabelFont);
        outOfJailCardsLabel.setText("");
        topMiddle2.add(outOfJailCardsLabel, new com.intellij.uiDesigner.core.GridConstraints(0, 1, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        topRight = new JPanel();
        topRight.setLayout(new com.intellij.uiDesigner.core.GridLayoutManager(1, 1, new Insets(0, 0, 0, 0), -1, -1));
        top.add(topRight, new com.intellij.uiDesigner.core.GridConstraints(0, 2, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_EAST, com.intellij.uiDesigner.core.GridConstraints.FILL_VERTICAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        propertyBtn = new JButton();
        propertyBtn.setBackground(new Color(-1));
        Font propertyBtnFont = this.$$$getFonts$$$("Inter", Font.PLAIN, 14, propertyBtn.getFont());
        if (propertyBtnFont != null) propertyBtn.setFont(propertyBtnFont);
        propertyBtn.setForeground(new Color(-16777216));
        propertyBtn.setText("Οι ιδιοκτησίες μου");
        topRight.add(propertyBtn, new com.intellij.uiDesigner.core.GridConstraints(0, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        middle = new JPanel();
        middle.setLayout(new GridBagLayout());
        container.add(middle, new com.intellij.uiDesigner.core.GridConstraints(1, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_BOTH, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, new Dimension(600, 600), new Dimension(800, 800), null, 0, true));
        boardLabel = new JLabel();
        boardLabel.setEnabled(true);
        boardLabel.setHorizontalAlignment(0);
        boardLabel.setHorizontalTextPosition(0);
        boardLabel.setIcon(new ImageIcon(getClass().getResource("/resources/0-10Board.png")));
        boardLabel.setText("");
        GridBagConstraints gbc;
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        gbc.fill = GridBagConstraints.BOTH;
        middle.add(boardLabel, gbc);
        bottom = new JPanel();
        bottom.setLayout(new com.intellij.uiDesigner.core.GridLayoutManager(1, 2, new Insets(15, 60, 15, 60), -1, -1));
        container.add(bottom, new com.intellij.uiDesigner.core.GridConstraints(3, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, null, null, new Dimension(-1, 100), 0, false));
        bottom.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.black), null, TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.DEFAULT_POSITION, null, null));
        dice = new JPanel();
        dice.setLayout(new com.intellij.uiDesigner.core.GridLayoutManager(1, 2, new Insets(0, 0, 0, 0), -1, -1));
        bottom.add(dice, new com.intellij.uiDesigner.core.GridConstraints(0, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST, com.intellij.uiDesigner.core.GridConstraints.FILL_VERTICAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        final JLabel label4 = new JLabel();
        Font label4Font = this.$$$getFonts$$$("Inter Medium", -1, 16, label4.getFont());
        if (label4Font != null) label4.setFont(label4Font);
        label4.setText("Ζάρια: ");
        dice.add(label4, new com.intellij.uiDesigner.core.GridConstraints(0, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        diceNumbers = new JLabel();
        Font diceNumbersFont = this.$$$getFonts$$$("Inter", -1, 16, diceNumbers.getFont());
        if (diceNumbersFont != null) diceNumbers.setFont(diceNumbersFont);
        diceNumbers.setText("");
        dice.add(diceNumbers, new com.intellij.uiDesigner.core.GridConstraints(0, 1, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        bottomButtons = new JPanel();
        bottomButtons.setLayout(new com.intellij.uiDesigner.core.GridLayoutManager(1, 4, new Insets(0, 0, 0, 0), -1, -1));
        bottom.add(bottomButtons, new com.intellij.uiDesigner.core.GridConstraints(0, 1, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_BOTH, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        buyPropBtn = new JButton();
        buyPropBtn.setBackground(new Color(-1));
        buyPropBtn.setEnabled(true);
        Font buyPropBtnFont = this.$$$getFonts$$$("Inter", Font.PLAIN, 14, buyPropBtn.getFont());
        if (buyPropBtnFont != null) buyPropBtn.setFont(buyPropBtnFont);
        buyPropBtn.setForeground(new Color(-16777216));
        buyPropBtn.setHideActionText(false);
        buyPropBtn.setText("Αγορά Ιδιοκτησίας");
        bottomButtons.add(buyPropBtn, new com.intellij.uiDesigner.core.GridConstraints(0, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        jailPayBtn = new JButton();
        jailPayBtn.setBackground(new Color(-1));
        jailPayBtn.setEnabled(true);
        Font jailPayBtnFont = this.$$$getFonts$$$("Inter", Font.PLAIN, 14, jailPayBtn.getFont());
        if (jailPayBtnFont != null) jailPayBtn.setFont(jailPayBtnFont);
        jailPayBtn.setForeground(new Color(-16777216));
        jailPayBtn.setHideActionText(false);
        jailPayBtn.setText("Πληρωμή αντιτίμου");
        bottomButtons.add(jailPayBtn, new com.intellij.uiDesigner.core.GridConstraints(0, 2, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        jailCardBtn = new JButton();
        jailCardBtn.setBackground(new Color(-1));
        jailCardBtn.setEnabled(true);
        Font jailCardBtnFont = this.$$$getFonts$$$("Inter", Font.PLAIN, 14, jailCardBtn.getFont());
        if (jailCardBtnFont != null) jailCardBtn.setFont(jailCardBtnFont);
        jailCardBtn.setForeground(new Color(-16777216));
        jailCardBtn.setHideActionText(false);
        jailCardBtn.setText("Χρήση κάρτας");
        bottomButtons.add(jailCardBtn, new com.intellij.uiDesigner.core.GridConstraints(0, 3, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        endTurnBtn = new JButton();
        endTurnBtn.setBackground(new Color(-1));
        endTurnBtn.setEnabled(true);
        Font endTurnBtnFont = this.$$$getFonts$$$("Inter", Font.PLAIN, 14, endTurnBtn.getFont());
        if (endTurnBtnFont != null) endTurnBtn.setFont(endTurnBtnFont);
        endTurnBtn.setForeground(new Color(-16777216));
        endTurnBtn.setHideActionText(false);
        endTurnBtn.setText("Τέλος Σειράς");
        bottomButtons.add(endTurnBtn, new com.intellij.uiDesigner.core.GridConstraints(0, 1, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        tooltips = new JPanel();
        tooltips.setLayout(new com.intellij.uiDesigner.core.GridLayoutManager(1, 2, new Insets(15, 60, 15, 60), -1, -1));
        container.add(tooltips, new com.intellij.uiDesigner.core.GridConstraints(2, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_BOTH, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        tooltipLeft = new JPanel();
        tooltipLeft.setLayout(new FlowLayout(FlowLayout.CENTER, 0, 5));
        tooltips.add(tooltipLeft, new com.intellij.uiDesigner.core.GridConstraints(0, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST, com.intellij.uiDesigner.core.GridConstraints.FILL_VERTICAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        final JLabel label5 = new JLabel();
        Font label5Font = this.$$$getFonts$$$("Inter Medium", -1, 22, label5.getFont());
        if (label5Font != null) label5.setFont(label5Font);
        label5.setText("Θέση: ");
        tooltipLeft.add(label5);
        currentPos = new JLabel();
        Font currentPosFont = this.$$$getFonts$$$("Inter", -1, 16, currentPos.getFont());
        if (currentPosFont != null) currentPos.setFont(currentPosFont);
        currentPos.setText("");
        tooltipLeft.add(currentPos);
        tooltipRight = new JPanel();
        tooltipRight.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
        tooltips.add(tooltipRight, new com.intellij.uiDesigner.core.GridConstraints(0, 1, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_BOTH, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        tooltipRightLabel = new JLabel();
        tooltipRightLabel.setText("");
        tooltipRight.add(tooltipRightLabel);
    }

    /**
     * @noinspection ALL
     */
    private Font $$$getFonts$$$(String fontName, int style, int size, Font currentFont) {
        if (currentFont == null) return null;
        String resultName;
        if (fontName == null) {
            resultName = currentFont.getName();
        } else {
            Font testFont = new Font(fontName, Font.PLAIN, 10);
            if (testFont.canDisplay('a') && testFont.canDisplay('1')) {
                resultName = fontName;
            } else {
                resultName = currentFont.getName();
            }
        }
        Font font = new Font(resultName, style >= 0 ? style : currentFont.getStyle(), size >= 0 ? size : currentFont.getSize());
        boolean isMac = System.getProperty("os.name", "").toLowerCase(Locale.ENGLISH).startsWith("mac");
        Font fontWithFallback = isMac ? new Font(font.getFamily(), font.getStyle(), font.getSize()) : new StyleContext().getFont(font.getFamily(), font.getStyle(), font.getSize());
        return fontWithFallback instanceof FontUIResource ? fontWithFallback : new FontUIResource(fontWithFallback);
    }

    /**
     * @noinspection ALL
     */
    public JComponent $$$getRootComponent$$$() {
        return container;
    }

    private enum ScreenState {
        NO_BTNS,
        DEFAULT,
        UNOWNED_PROPERTY,
        JAILED
    }

    private ScreenState currentState = ScreenState.DEFAULT;

    public MainBoard() {
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
                if (currentPlayer.getOutOfJailCards() > 0) {
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

    public void handleScreenState() {
        buyPropBtn.setVisible(false);
        endTurnBtn.setVisible(false);
        jailPayBtn.setVisible(false);
        jailCardBtn.setVisible(false);

        switch (currentState) {
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
                if (currentPlayer.getOutOfJailCards() > 0) jailCardBtn.setEnabled(true);
            default:
                bottomButtons.setVisible(true);
                endTurnBtn.setVisible(true);
                break;
        }
    }

    public void updatePos() {
        int pos = currentPlayer.getCurrentBlock().getBlockPosition();
        String posTitle = currentPlayer.getCurrentBlock().getBlockTitle();

        if (pos == 10 && !Jail.isInJail(currentPlayer))
            posTitle = currentPlayer.getCurrentBlock().getBlockTitle() + " (Μόνο για επίσκεψη)";

        currentPos.setText((pos + 1) + " - " + posTitle);
        updateScreenState();
        updateBoardImage();

    }

    public void updatePlayer(Player currPlayer) {
        currentPlayer = currPlayer;
        playerNumber.setText(Integer.toString(currPlayer.getPlayerID()));
        outOfJailCardsLabel.setText(currentPlayer.getOutOfJailCards() + "");
        updateBalance(currPlayer.getBalance());
        updatePos();
    }

    public void updateBalance(int balance) {
        currentBalance.setText(Integer.toString(balance));
    }

    public void updateInfo(Player currPlayer) {
        updatePlayer(currPlayer);
        updateScreenState();
    }

    public void updateEndTurnEnabled(boolean val) {
        endTurnBtn.setEnabled(val);
    }

    public void updateDice(int[] dice, boolean loading) {
        if (loading)
            diceNumbers.setText(". . .");
        else {
            currentDice = dice;
            diceNumbers.setText(dice[0] + " - " + dice[1]);
        }

    }

    public void updateBoardImage() {
        int currentPos = currentPlayer.getCurrentBlock().getBlockPosition();
        if (0 <= currentPos && currentPos <= 10) boardLabel.setIcon(new ImageIcon(getClass().getResource("/0-10Board.png")));
        if (11 <= currentPos && currentPos <= 20) boardLabel.setIcon(new ImageIcon(getClass().getResource("/11-20Board.png")));
        if (21 <= currentPos && currentPos <= 30) boardLabel.setIcon(new ImageIcon(getClass().getResource("/21-30Board.png")));
        if (31 <= currentPos && currentPos <= 39) boardLabel.setIcon(new ImageIcon(getClass().getResource("/31-39Board.png")));
    }

    public void updateScreenState() {
        Block currBlock = currentPlayer.getCurrentBlock();
        if (currBlock instanceof Property prop) {
            if (prop.getOwner() == null) {
                currentState = ScreenState.UNOWNED_PROPERTY;
            } else {
                currentState = ScreenState.DEFAULT;
            }
        } else if (Jail.isInJail(currentPlayer)) {
            currentState = ScreenState.JAILED;

        } else currentState = ScreenState.DEFAULT;

        handleScreenState();
    }

    public void setgBoard(GameBoard gBoard) {
        this.gBoard = gBoard;
    }


}