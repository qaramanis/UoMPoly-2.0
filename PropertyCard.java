import javax.swing.*;
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

    private Property prop;
    MainBoard mBoard;
    private DefaultListModel<String> propAttributes;
    public PropertyCard(Property prop, MainBoard mBoard){
        super();
        this.prop = prop;
        this.mBoard = mBoard;
        propAttributes = new DefaultListModel<String>();

        if(prop instanceof Room r){
            propAttributes.addElement("Τιμή: " + r.cost);
            propAttributes.addElement("Ενοίκιο: " + r.getRent());
            propAttributes.addElement("Ενοίκιο με 1 έδρανο: " + r.getRentWithOneDesk());
            propAttributes.addElement("Ενοίκιο με 2 έδρανα: " + r.getRentWithTwoDesks());
            propAttributes.addElement("Ενοίκιο με 3 έδρανα: " + r.getRentWithThreeDesks());
            propAttributes.addElement("Ενοίκιο με 4 έδρανα: " + r.getRentWithFourDesks());
            propAttributes.addElement("Ενοίκιο με πίνακα: " + r.getRentWithBoard());
            numberOfDesksLabel.setText(Integer.toString(r.numberOfDesks));
            serviceDisclaimers.setVisible(false);
        }
        if(prop instanceof Transport transp){
            propAttributes.addElement("Τιμή: " + transp.cost);
            propAttributes.addElement("Ενοίκιο: " + transp.rentWithOneTransportProperty);
            propAttributes.addElement("Ενοίκιο αν κατέχεις 2 ανελκυστήρες: " + transp.rentWithTwoTransportProperties);
            propAttributes.addElement("Ενοίκιο αν κατέχεις 3 ανελκυστήρες: " + transp.rentWithThreeTransportProperties);
            propAttributes.addElement("Ενοίκιο αν κατέχεις 4 ανελκυστήρες: " + transp.rentWithFourTransportProperties);
            wholeCGroupDisclaimer.setVisible(false);
            serviceDisclaimers.setVisible(false);
            desksWrapper.setVisible(false);
            boardsWrapper.setVisible(false);
        }
        if(prop instanceof Service serv){
            propAttributes.addElement("Τιμή: " + serv.cost);
            list1.setVisible(false);
            wholeCGroupDisclaimer.setVisible(false);
            desksWrapper.setVisible(false);
            boardsWrapper.setVisible(false);
        }

        list1.setModel(propAttributes);

        updateMortgageInfo();
        updateHasBoard();
        propertyNameLabel.setText(prop.getBlockTitle());
        mortgageValueLabel.setText(prop.mortgageValue + "");

        add(panel1);
        panel1.setAlignmentX(Component.LEFT_ALIGNMENT);
        setSize(600, 300);
        setVisible(true);
        mortgageBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(prop.isMortgaged){
                    prop.unmortgageProperty();
                }else {
                    prop.mortgageProperty();
                }
                updateMortgageInfo();
                mBoard.updatePlayer(prop.owner);
            }
        });
        buyDeskBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ((Room)prop).buildDesk();
                updateNumberOfDesks();
                mBoard.updatePlayer(prop.owner);
            }
        });
        sellDeskBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ((Room)prop).sellDesk();
                updateNumberOfDesks();
                mBoard.updatePlayer(prop.owner);
            }
        });
        buyBoardBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                ((Room)prop).buildBoard();
                updateHasBoard();
                mBoard.updatePlayer(prop.owner);
            }
        });
        sellBoardBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                ((Room)prop).sellBoard();
                updateHasBoard();
                mBoard.updatePlayer(prop.owner);
            }
        });
    }

    private void updateMortgageInfo(){
        if(prop.isMortgaged()){
            mortgageBtn.setText("Άρση Υποθήκευσης");
        }else {
            mortgageBtn.setText("Υποθήκευση Ιδιοκτησίας");
        }
    }

    private void updateNumberOfDesks(){
      if(prop instanceof  Room r) numberOfDesksLabel.setText(r.numberOfDesks + "");
    }
    private void updateHasBoard(){
        if(prop instanceof  Room r) {
            String hasBoardText = "Όχι";
            if (r.hasBoard) hasBoardText = "Ναι";
            hasBoardLabel.setText(hasBoardText);
        }
    }

    private void createUIComponents() {
        // TODO: place custom component creation code here
    }

}
