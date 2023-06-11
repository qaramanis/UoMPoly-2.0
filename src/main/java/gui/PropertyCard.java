package gui;

import files.Property;
import files.Room;
import files.Service;
import files.Transport;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import javax.swing.plaf.FontUIResource;
import javax.swing.text.StyleContext;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Locale;

public class PropertyCard extends JPanel {
    private JPanel panel1;
    private JPanel bottomPanel;
    private JPanel centerPanel;
    private JList list1;
    private JLabel propertyNameLabel;
    private JLabel wholeCGroupDisclaimer;
    private JLabel numberOfDesksLabel;
    private JButton buyDeskBtn;
    private JButton sellDeskBtn;
    private JPanel desksWrapper;
    private JPanel boardsWrapper;
    private JButton buyBoardBtn;
    private JButton sellBoardBtn;
    private JLabel mortgageValueLabel;
    private JButton mortgageBtn;
    private JLabel hasBoardLabel;
    private JPanel serviceDisclaimers;
    private JPanel colorPanel;

    private Property prop;
    MainBoard mBoard;
    private DefaultListModel<String> propAttributes;

    public PropertyCard(Property prop, MainBoard mBoard) {
        super();
        this.prop = prop;
        this.mBoard = mBoard;
        propAttributes = new DefaultListModel<String>();

        if (prop instanceof Room r) {
            propAttributes.addElement("Τιμή: " + r.getCost());
            propAttributes.addElement("Ενοίκιο: " + r.getRent());
            propAttributes.addElement("Ενοίκιο με 1 έδρανο: " + r.getRentWithOneDesk());
            propAttributes.addElement("Ενοίκιο με 2 έδρανα: " + r.getRentWithTwoDesks());
            propAttributes.addElement("Ενοίκιο με 3 έδρανα: " + r.getRentWithThreeDesks());
            propAttributes.addElement("Ενοίκιο με 4 έδρανα: " + r.getRentWithFourDesks());
            propAttributes.addElement("Ενοίκιο με πίνακα: " + r.getRentWithBoard());
            numberOfDesksLabel.setText(Integer.toString(r.getNumberOfDesks()));
            serviceDisclaimers.setVisible(false);
        }
        if (prop instanceof Transport transp) {
            propAttributes.addElement("Τιμή: " + transp.getCost());
            propAttributes.addElement("Ενοίκιο: " + transp.rentWithOneTransportProperty);
            propAttributes.addElement("Ενοίκιο αν κατέχεις 2 ανελκυστήρες: " + transp.rentWithTwoTransportProperties);
            propAttributes.addElement("Ενοίκιο αν κατέχεις 3 ανελκυστήρες: " + transp.rentWithThreeTransportProperties);
            propAttributes.addElement("Ενοίκιο αν κατέχεις 4 ανελκυστήρες: " + transp.rentWithFourTransportProperties);
            wholeCGroupDisclaimer.setVisible(false);
            serviceDisclaimers.setVisible(false);
            desksWrapper.setVisible(false);
            boardsWrapper.setVisible(false);
        }
        if (prop instanceof Service serv) {
            propAttributes.addElement("Τιμή: " + serv.getCost());
            list1.setVisible(false);
            wholeCGroupDisclaimer.setVisible(false);
            desksWrapper.setVisible(false);
            boardsWrapper.setVisible(false);
        }

        list1.setModel(propAttributes);

        updateMortgageInfo();
        updateHasBoard();
        propertyNameLabel.setText(prop.getBlockTitle());
        Color myColor = Color.decode(getColorOfProp());
        colorPanel.setBackground(myColor);
        if (!(prop instanceof Room)) colorPanel.setVisible(false);
        mortgageValueLabel.setText(prop.getMortgageValue() + "");

        add(panel1);
        panel1.setAlignmentX(Component.LEFT_ALIGNMENT);
        setSize(600, 300);
        setVisible(true);
        mortgageBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (prop.isMortgaged()) {
                    prop.unmortgageProperty();
                } else {
                    prop.mortgageProperty();
                }
                updateMortgageInfo();
                mBoard.updatePlayer(prop.getOwner());
            }
        });
        buyDeskBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ((Room) prop).buildDesk();
                updateNumberOfDesks();
                mBoard.updatePlayer(prop.getOwner());
            }
        });
        sellDeskBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ((Room) prop).sellDesk();
                updateNumberOfDesks();
                mBoard.updatePlayer(prop.getOwner());
            }
        });
        buyBoardBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                ((Room) prop).buildBoard();
                updateHasBoard();
                mBoard.updatePlayer(prop.getOwner());
            }
        });
        sellBoardBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                ((Room) prop).sellBoard();
                updateHasBoard();
                mBoard.updatePlayer(prop.getOwner());
            }
        });
    }

    private void updateMortgageInfo() {
        if (prop.isMortgaged()) {
            mortgageBtn.setText("Άρση Υποθήκευσης");
        } else {
            mortgageBtn.setText("Υποθήκευση Ιδιοκτησίας");
        }
    }

    private String getColorOfProp() {
        if (prop instanceof Room r) {
            switch (r.getColor()) {
                case "brown":
                    return "#5A2E23";
                case "light_blue":
                    return "#95B8CB";
                case "magenta":
                    return "#952C55";
                case "orange":
                    return "#BB6626";
                case "red":
                    return "#AB3028";
                case "yellow":
                    return "#D5C119";
                case "green":
                    return "#00882D";
                case "dark_blue":
                    return "#0051A9";

            }
        }
        return "#000000";
    }

    private void updateNumberOfDesks() {
        if (prop instanceof Room r) numberOfDesksLabel.setText(r.getNumberOfDesks() + "");
    }

    private void updateHasBoard() {
        if (prop instanceof Room r) {
            String hasBoardText = "Όχι";
            if (r.hasBoard()) hasBoardText = "Ναι";
            hasBoardLabel.setText(hasBoardText);
        }
    }

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
        panel1 = new JPanel();
        panel1.setLayout(new com.intellij.uiDesigner.core.GridLayoutManager(2, 1, new Insets(10, 10, 10, 10), -1, -1));
        panel1.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(new Color(-14145496)), null, TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.DEFAULT_POSITION, null, null));
        bottomPanel = new JPanel();
        bottomPanel.setLayout(new com.intellij.uiDesigner.core.GridLayoutManager(1, 2, new Insets(0, 0, 0, 0), -1, -1));
        panel1.add(bottomPanel, new com.intellij.uiDesigner.core.GridConstraints(1, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_SOUTH, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        final JPanel panel2 = new JPanel();
        panel2.setLayout(new FlowLayout(FlowLayout.LEFT, 5, 5));
        bottomPanel.add(panel2, new com.intellij.uiDesigner.core.GridConstraints(0, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_BOTH, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        final JLabel label1 = new JLabel();
        Font label1Font = this.$$$getFont$$$("Inter", Font.PLAIN, 16, label1.getFont());
        if (label1Font != null) label1.setFont(label1Font);
        label1.setText("Αξία Υποθήκευσης: ");
        panel2.add(label1);
        mortgageValueLabel = new JLabel();
        Font mortgageValueLabelFont = this.$$$getFont$$$("Inter", Font.PLAIN, 16, mortgageValueLabel.getFont());
        if (mortgageValueLabelFont != null) mortgageValueLabel.setFont(mortgageValueLabelFont);
        mortgageValueLabel.setText("");
        panel2.add(mortgageValueLabel);
        final JPanel panel3 = new JPanel();
        panel3.setLayout(new com.intellij.uiDesigner.core.GridLayoutManager(1, 1, new Insets(0, 0, 0, 0), -1, -1));
        bottomPanel.add(panel3, new com.intellij.uiDesigner.core.GridConstraints(0, 1, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_BOTH, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        mortgageBtn = new JButton();
        mortgageBtn.setBackground(new Color(-1));
        Font mortgageBtnFont = this.$$$getFont$$$("Inter", Font.PLAIN, 14, mortgageBtn.getFont());
        if (mortgageBtnFont != null) mortgageBtn.setFont(mortgageBtnFont);
        mortgageBtn.setForeground(new Color(-16777216));
        mortgageBtn.setText("Υποθήκευση Ιδιοκτησίας");
        panel3.add(mortgageBtn, new com.intellij.uiDesigner.core.GridConstraints(0, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        centerPanel = new JPanel();
        centerPanel.setLayout(new com.intellij.uiDesigner.core.GridLayoutManager(7, 2, new Insets(0, 0, 0, 0), -1, -1));
        panel1.add(centerPanel, new com.intellij.uiDesigner.core.GridConstraints(0, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_BOTH, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        list1 = new JList();
        list1.setBackground(new Color(-1));
        Font list1Font = this.$$$getFont$$$("Inter", Font.PLAIN, 14, list1.getFont());
        if (list1Font != null) list1.setFont(list1Font);
        list1.setForeground(new Color(-16777216));
        final DefaultListModel defaultListModel1 = new DefaultListModel();
        defaultListModel1.addElement("Τιμή:");
        defaultListModel1.addElement("Ενοίκιο:");
        defaultListModel1.addElement("Ενοίκιο με 1 έδρανο:");
        defaultListModel1.addElement("Ενοίκιο με 2 έδρανα:");
        defaultListModel1.addElement("Ενοίκιο με 3 έδρανα:");
        defaultListModel1.addElement("Ενοίκιο με 4 έδρανα:");
        defaultListModel1.addElement("Ενοίκιο με πίνακα:");
        list1.setModel(defaultListModel1);
        centerPanel.add(list1, new com.intellij.uiDesigner.core.GridConstraints(1, 0, 1, 2, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_BOTH, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, null, new Dimension(150, 50), null, 0, false));
        final JPanel panel4 = new JPanel();
        panel4.setLayout(new com.intellij.uiDesigner.core.GridLayoutManager(1, 2, new Insets(0, 0, 0, 0), -1, -1));
        centerPanel.add(panel4, new com.intellij.uiDesigner.core.GridConstraints(0, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST, com.intellij.uiDesigner.core.GridConstraints.FILL_VERTICAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        final JLabel label2 = new JLabel();
        Font label2Font = this.$$$getFont$$$("Inter", -1, 16, label2.getFont());
        if (label2Font != null) label2.setFont(label2Font);
        label2.setText("Ιδιοκτησία:");
        panel4.add(label2, new com.intellij.uiDesigner.core.GridConstraints(0, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        propertyNameLabel = new JLabel();
        Font propertyNameLabelFont = this.$$$getFont$$$("Inter Medium", -1, 16, propertyNameLabel.getFont());
        if (propertyNameLabelFont != null) propertyNameLabel.setFont(propertyNameLabelFont);
        propertyNameLabel.setText("");
        panel4.add(propertyNameLabel, new com.intellij.uiDesigner.core.GridConstraints(0, 1, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        wholeCGroupDisclaimer = new JLabel();
        Font wholeCGroupDisclaimerFont = this.$$$getFont$$$("Inter", Font.PLAIN, 14, wholeCGroupDisclaimer.getFont());
        if (wholeCGroupDisclaimerFont != null) wholeCGroupDisclaimer.setFont(wholeCGroupDisclaimerFont);
        wholeCGroupDisclaimer.setHorizontalAlignment(2);
        wholeCGroupDisclaimer.setHorizontalTextPosition(2);
        wholeCGroupDisclaimer.setText("Αν σου ανήκουν όλες οι ιδιοκτησίες της χρωματικής ομάδας  συλλέγεις το διπλάσιο ενοίκιο");
        centerPanel.add(wholeCGroupDisclaimer, new com.intellij.uiDesigner.core.GridConstraints(4, 0, 1, 2, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        desksWrapper = new JPanel();
        desksWrapper.setLayout(new com.intellij.uiDesigner.core.GridLayoutManager(1, 2, new Insets(0, 0, 0, 0), -1, -1));
        centerPanel.add(desksWrapper, new com.intellij.uiDesigner.core.GridConstraints(5, 0, 1, 2, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_BOTH, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        final JPanel panel5 = new JPanel();
        panel5.setLayout(new FlowLayout(FlowLayout.LEFT, 5, 5));
        desksWrapper.add(panel5, new com.intellij.uiDesigner.core.GridConstraints(0, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_BOTH, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        final JLabel label3 = new JLabel();
        Font label3Font = this.$$$getFont$$$("Inter", Font.PLAIN, 16, label3.getFont());
        if (label3Font != null) label3.setFont(label3Font);
        label3.setText("Αριθμός Εδράνων: ");
        panel5.add(label3);
        numberOfDesksLabel = new JLabel();
        Font numberOfDesksLabelFont = this.$$$getFont$$$("Inter", Font.PLAIN, 16, numberOfDesksLabel.getFont());
        if (numberOfDesksLabelFont != null) numberOfDesksLabel.setFont(numberOfDesksLabelFont);
        numberOfDesksLabel.setText("");
        panel5.add(numberOfDesksLabel);
        final JPanel panel6 = new JPanel();
        panel6.setLayout(new com.intellij.uiDesigner.core.GridLayoutManager(1, 2, new Insets(0, 0, 0, 0), -1, -1));
        desksWrapper.add(panel6, new com.intellij.uiDesigner.core.GridConstraints(0, 1, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_BOTH, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        buyDeskBtn = new JButton();
        buyDeskBtn.setBackground(new Color(-1));
        Font buyDeskBtnFont = this.$$$getFont$$$("Inter", Font.PLAIN, 14, buyDeskBtn.getFont());
        if (buyDeskBtnFont != null) buyDeskBtn.setFont(buyDeskBtnFont);
        buyDeskBtn.setForeground(new Color(-16777216));
        buyDeskBtn.setText("Αγορά Εδράνου");
        panel6.add(buyDeskBtn, new com.intellij.uiDesigner.core.GridConstraints(0, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        sellDeskBtn = new JButton();
        sellDeskBtn.setBackground(new Color(-1));
        Font sellDeskBtnFont = this.$$$getFont$$$("Inter", Font.PLAIN, 14, sellDeskBtn.getFont());
        if (sellDeskBtnFont != null) sellDeskBtn.setFont(sellDeskBtnFont);
        sellDeskBtn.setForeground(new Color(-16777216));
        sellDeskBtn.setText("Πώληση Εδράνου");
        panel6.add(sellDeskBtn, new com.intellij.uiDesigner.core.GridConstraints(0, 1, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        boardsWrapper = new JPanel();
        boardsWrapper.setLayout(new com.intellij.uiDesigner.core.GridLayoutManager(1, 2, new Insets(0, 0, 0, 0), -1, -1));
        centerPanel.add(boardsWrapper, new com.intellij.uiDesigner.core.GridConstraints(6, 0, 1, 2, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_BOTH, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        final JPanel panel7 = new JPanel();
        panel7.setLayout(new FlowLayout(FlowLayout.LEFT, 5, 5));
        boardsWrapper.add(panel7, new com.intellij.uiDesigner.core.GridConstraints(0, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_BOTH, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        final JLabel label4 = new JLabel();
        Font label4Font = this.$$$getFont$$$("Inter", Font.PLAIN, 16, label4.getFont());
        if (label4Font != null) label4.setFont(label4Font);
        label4.setText("Υπάρχει πίνακας: ");
        panel7.add(label4);
        hasBoardLabel = new JLabel();
        Font hasBoardLabelFont = this.$$$getFont$$$("Inter", Font.PLAIN, 16, hasBoardLabel.getFont());
        if (hasBoardLabelFont != null) hasBoardLabel.setFont(hasBoardLabelFont);
        hasBoardLabel.setText("");
        panel7.add(hasBoardLabel);
        final JPanel panel8 = new JPanel();
        panel8.setLayout(new com.intellij.uiDesigner.core.GridLayoutManager(1, 2, new Insets(0, 0, 0, 0), -1, -1));
        boardsWrapper.add(panel8, new com.intellij.uiDesigner.core.GridConstraints(0, 1, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_BOTH, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        buyBoardBtn = new JButton();
        buyBoardBtn.setBackground(new Color(-1));
        Font buyBoardBtnFont = this.$$$getFont$$$("Inter", Font.PLAIN, 14, buyBoardBtn.getFont());
        if (buyBoardBtnFont != null) buyBoardBtn.setFont(buyBoardBtnFont);
        buyBoardBtn.setForeground(new Color(-16777216));
        buyBoardBtn.setText("Αγορά Πίνακα");
        panel8.add(buyBoardBtn, new com.intellij.uiDesigner.core.GridConstraints(0, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        sellBoardBtn = new JButton();
        sellBoardBtn.setBackground(new Color(-1));
        Font sellBoardBtnFont = this.$$$getFont$$$("Inter", Font.PLAIN, 14, sellBoardBtn.getFont());
        if (sellBoardBtnFont != null) sellBoardBtn.setFont(sellBoardBtnFont);
        sellBoardBtn.setForeground(new Color(-16777216));
        sellBoardBtn.setText("Πώληση Πίνακα");
        panel8.add(sellBoardBtn, new com.intellij.uiDesigner.core.GridConstraints(0, 1, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        serviceDisclaimers = new JPanel();
        serviceDisclaimers.setLayout(new com.intellij.uiDesigner.core.GridLayoutManager(2, 1, new Insets(0, 0, 0, 0), -1, -1));
        centerPanel.add(serviceDisclaimers, new com.intellij.uiDesigner.core.GridConstraints(2, 0, 2, 2, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_BOTH, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        final JLabel label5 = new JLabel();
        Font label5Font = this.$$$getFont$$$("Inter", Font.PLAIN, 14, label5.getFont());
        if (label5Font != null) label5.setFont(label5Font);
        label5.setHorizontalAlignment(2);
        label5.setHorizontalTextPosition(2);
        label5.setText("<html>Αν έχεις στην κατοχή σου μία μόνο υπηρεσία, το ενοίκιο είναι 4 φορές <br/> ο αριθμός που έφεραν τα ζάρια.</html>");
        serviceDisclaimers.add(label5, new com.intellij.uiDesigner.core.GridConstraints(0, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JLabel label6 = new JLabel();
        Font label6Font = this.$$$getFont$$$("Inter", Font.PLAIN, 14, label6.getFont());
        if (label6Font != null) label6.setFont(label6Font);
        label6.setHorizontalAlignment(2);
        label6.setHorizontalTextPosition(2);
        label6.setText("<html>Αν έχεις στην κατοχή σου μία μόνο υπηρεσία, το ενοίκιο είναι 10 φορές <br/> ο αριθμός που έφεραν τα ζάρια.</html>");
        serviceDisclaimers.add(label6, new com.intellij.uiDesigner.core.GridConstraints(1, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        colorPanel = new JPanel();
        colorPanel.setLayout(new com.intellij.uiDesigner.core.GridLayoutManager(1, 1, new Insets(0, 0, 0, 0), -1, -1));
        colorPanel.setBackground(new Color(-1763048));
        centerPanel.add(colorPanel, new com.intellij.uiDesigner.core.GridConstraints(0, 1, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_BOTH, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, new Dimension(40, -1), null, null, 0, false));
    }

    /**
     * @noinspection ALL
     */
    private Font $$$getFont$$$(String fontName, int style, int size, Font currentFont) {
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
        return panel1;
    }
}
