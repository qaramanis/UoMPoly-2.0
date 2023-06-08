import javax.swing.*;
import java.awt.Color;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

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
        if(!(prop instanceof Room)) colorPanel.setVisible(false);
        mortgageValueLabel.setText(prop.getMortgageValue() + "");

        add(panel1);
        panel1.setAlignmentX(Component.LEFT_ALIGNMENT);
        setSize(600, 300);
        setVisible(true);
        mortgageBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (prop.isMortgaged) {
                    prop.unmortgageProperty();
                } else {
                    prop.mortgageProperty();
                }
                updateMortgageInfo();
                mBoard.updatePlayer(prop.owner);
            }
        });
        buyDeskBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ((Room) prop).buildDesk();
                updateNumberOfDesks();
                mBoard.updatePlayer(prop.owner);
            }
        });
        sellDeskBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ((Room) prop).sellDesk();
                updateNumberOfDesks();
                mBoard.updatePlayer(prop.owner);
            }
        });
        buyBoardBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                ((Room) prop).buildBoard();
                updateHasBoard();
                mBoard.updatePlayer(prop.owner);
            }
        });
        sellBoardBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                ((Room) prop).sellBoard();
                updateHasBoard();
                mBoard.updatePlayer(prop.owner);
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

}
