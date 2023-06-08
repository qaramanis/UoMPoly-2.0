package gui;

import files.*;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import javax.swing.plaf.FontUIResource;
import javax.swing.text.StyleContext;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Locale;

public class BuyPropertyScreen extends JFrame {
    private JPanel panel1;
    private JPanel bottomPanel;
    private JButton cancelBtn;
    private JPanel centerPanel;
    private JList list1;
    private JLabel propertyNameLabel;
    private JButton buyBtn;
    private JPanel serviceDisclaimers;
    private DefaultListModel<String> propAttributes;
    private MainBoard mBoard;

    public BuyPropertyScreen(Player pl, MainBoard mBoard) {
        Property prop = (Property) pl.getCurrentBlock();
        propAttributes = new DefaultListModel<String>();
        serviceDisclaimers.setVisible(false);

        if (prop instanceof Room) {
            propAttributes.addElement("Τιμή: " + prop.getCost());
            propAttributes.addElement("Ενοίκιο: " + ((Room) prop).getRent());
            propAttributes.addElement("Ενοίκιο με 1 έδρανο: " + ((Room) prop).getRentWithOneDesk());
            propAttributes.addElement("Ενοίκιο με 2 έδρανα: " + ((Room) prop).getRentWithTwoDesks());
            propAttributes.addElement("Ενοίκιο με 3 έδρανα: " + ((Room) prop).getRentWithThreeDesks());
            propAttributes.addElement("Ενοίκιο με 4 έδρανα: " + ((Room) prop).getRentWithFourDesks());
            propAttributes.addElement("Ενοίκιο με πίνακα: " + ((Room) prop).getRentWithBoard());
        }
        if (prop instanceof Transport) {
            propAttributes.addElement("Τιμή: " + prop.getCost());
            propAttributes.addElement("Ενοίκιο: " + ((Transport) prop).rentWithOneTransportProperty);
            propAttributes.addElement("Ενοίκιο αν κατέχεις 2 ανελκυστήρες: " + ((Transport) prop).rentWithTwoTransportProperties);
            propAttributes.addElement("Ενοίκιο αν κατέχεις 3 ανελκυστήρες: " + ((Transport) prop).rentWithThreeTransportProperties);
            propAttributes.addElement("Ενοίκιο αν κατέχεις 4 ανελκυστήρες: " + ((Transport) prop).rentWithFourTransportProperties);
        }
        if (prop instanceof Service serv) {
            propAttributes.addElement("Τιμή: " + serv.getCost());
            list1.setVisible(false);
            serviceDisclaimers.setVisible(true);
        }

        list1.setModel(propAttributes);

        propertyNameLabel.setText(prop.getBlockTitle() + " - " + prop.getBlockPosition());
        setContentPane(panel1);
        setTitle("Αγορά Ιδιοκτησίας");
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setSize(600, 900);
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
        cancelBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                dispose();
            }
        });
        buyBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                pl.BuyProperty();
                mBoard.updateInfo(pl);
                dispose();
            }
        });
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
        panel1.setLayout(new com.intellij.uiDesigner.core.GridLayoutManager(1, 1, new Insets(10, 10, 10, 10), -1, -1));
        final JPanel panel2 = new JPanel();
        panel2.setLayout(new com.intellij.uiDesigner.core.GridLayoutManager(2, 1, new Insets(10, 10, 10, 10), -1, -1));
        panel1.add(panel2, new com.intellij.uiDesigner.core.GridConstraints(0, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, null, new Dimension(400, -1), null, 0, false));
        panel2.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(new Color(-14145496)), null, TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.DEFAULT_POSITION, null, null));
        bottomPanel = new JPanel();
        bottomPanel.setLayout(new com.intellij.uiDesigner.core.GridLayoutManager(1, 2, new Insets(0, 0, 0, 0), -1, -1));
        panel2.add(bottomPanel, new com.intellij.uiDesigner.core.GridConstraints(1, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_SOUTH, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        buyBtn = new JButton();
        buyBtn.setBackground(new Color(-1));
        Font buyBtnFont = this.$$$getFonts$$$("Inter", Font.PLAIN, 14, buyBtn.getFont());
        if (buyBtnFont != null) buyBtn.setFont(buyBtnFont);
        buyBtn.setForeground(new Color(-16777216));
        buyBtn.setText("Αγορά");
        bottomPanel.add(buyBtn, new com.intellij.uiDesigner.core.GridConstraints(0, 1, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        cancelBtn = new JButton();
        cancelBtn.setBackground(new Color(-1));
        Font cancelBtnFont = this.$$$getFonts$$$("Inter", Font.PLAIN, 14, cancelBtn.getFont());
        if (cancelBtnFont != null) cancelBtn.setFont(cancelBtnFont);
        cancelBtn.setForeground(new Color(-16777216));
        cancelBtn.setText("Άκυρο");
        bottomPanel.add(cancelBtn, new com.intellij.uiDesigner.core.GridConstraints(0, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        centerPanel = new JPanel();
        centerPanel.setLayout(new com.intellij.uiDesigner.core.GridLayoutManager(3, 1, new Insets(0, 0, 0, 0), -1, -1));
        panel2.add(centerPanel, new com.intellij.uiDesigner.core.GridConstraints(0, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_BOTH, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        list1 = new JList();
        list1.setBackground(new Color(-1));
        Font list1Font = this.$$$getFonts$$$("Inter", Font.PLAIN, 14, list1.getFont());
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
        centerPanel.add(list1, new com.intellij.uiDesigner.core.GridConstraints(1, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_BOTH, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, null, new Dimension(150, 50), null, 0, false));
        final JPanel panel3 = new JPanel();
        panel3.setLayout(new com.intellij.uiDesigner.core.GridLayoutManager(1, 2, new Insets(0, 0, 0, 0), -1, -1));
        centerPanel.add(panel3, new com.intellij.uiDesigner.core.GridConstraints(0, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST, com.intellij.uiDesigner.core.GridConstraints.FILL_VERTICAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        final JLabel label1 = new JLabel();
        Font label1Font = this.$$$getFonts$$$("Inter", -1, 16, label1.getFont());
        if (label1Font != null) label1.setFont(label1Font);
        label1.setText("Ιδιοκτησία:");
        panel3.add(label1, new com.intellij.uiDesigner.core.GridConstraints(0, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        propertyNameLabel = new JLabel();
        Font propertyNameLabelFont = this.$$$getFonts$$$("Inter Medium", -1, 16, propertyNameLabel.getFont());
        if (propertyNameLabelFont != null) propertyNameLabel.setFont(propertyNameLabelFont);
        propertyNameLabel.setText("");
        panel3.add(propertyNameLabel, new com.intellij.uiDesigner.core.GridConstraints(0, 1, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        serviceDisclaimers = new JPanel();
        serviceDisclaimers.setLayout(new com.intellij.uiDesigner.core.GridLayoutManager(2, 1, new Insets(0, 0, 0, 0), -1, -1));
        centerPanel.add(serviceDisclaimers, new com.intellij.uiDesigner.core.GridConstraints(2, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_BOTH, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        final JLabel label2 = new JLabel();
        Font label2Font = this.$$$getFonts$$$("Inter", Font.PLAIN, 14, label2.getFont());
        if (label2Font != null) label2.setFont(label2Font);
        label2.setHorizontalAlignment(2);
        label2.setHorizontalTextPosition(2);
        label2.setText("<html>Αν έχεις στην κατοχή σου μία μόνο υπηρεσία, το ενοίκιο είναι 4 φορές <br/> ο αριθμός που έφεραν τα ζάρια.</html>");
        serviceDisclaimers.add(label2, new com.intellij.uiDesigner.core.GridConstraints(0, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JLabel label3 = new JLabel();
        Font label3Font = this.$$$getFonts$$$("Inter", Font.PLAIN, 14, label3.getFont());
        if (label3Font != null) label3.setFont(label3Font);
        label3.setHorizontalAlignment(2);
        label3.setHorizontalTextPosition(2);
        label3.setText("<html>Αν έχεις στην κατοχή σου μία μόνο υπηρεσία, το ενοίκιο είναι 10 φορές <br/> ο αριθμός που έφεραν τα ζάρια.</html>");
        serviceDisclaimers.add(label3, new com.intellij.uiDesigner.core.GridConstraints(1, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
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
        return panel1;
    }
}