import javax.swing.*;
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
    private JLabel numberOfBoardsLabel;
    private JButton buyBoardBtn;
    private JButton sellBoardBtn;
    private JLabel mortgageValueLabel;
    private JButton mortgageBtn;

    private Property prop;
    MainBoard mBoard;
    private DefaultListModel<String> propAttributes;
    public PropertyCard(Property prop, MainBoard mBoard){
        super();
        this.prop = prop;
        this.mBoard = mBoard;
        propAttributes = new DefaultListModel<String>();

        if(prop instanceof Room){
            Room r = (Room)prop;
            propAttributes.addElement("Τιμή: " + r.cost);
            propAttributes.addElement("Ενοίκιο: " + r.getRent());
            propAttributes.addElement("Ενοίκιο με 1 έδρανο: " + r.getRentWithOneDesk());
            propAttributes.addElement("Ενοίκιο με 2 έδρανα: " + r.getRentWithTwoDesks());
            propAttributes.addElement("Ενοίκιο με 3 έδρανα: " + r.getRentWithThreeDesks());
            propAttributes.addElement("Ενοίκιο με 4 έδρανα: " + r.getRentWithFourDesks());
            propAttributes.addElement("Ενοίκιο με πίνακα: " + r.getRentWithBoard());
            numberOfDesksLabel.setText(Integer.toString(r.numberOfDesks));
        }
        if(prop instanceof Transport){
            propAttributes.addElement("Τιμή: " + prop.cost);
            propAttributes.addElement("Ενοίκιο: " + ((Transport) prop).rentWithOneTransportProperty);
            propAttributes.addElement("Ενοίκιο αν κατέχεις 2 ανελκυστήρες: " + ((Transport) prop).rentWithTwoTransportProperties);
            propAttributes.addElement("Ενοίκιο αν κατέχεις 3 ανελκυστήρες: " + ((Transport) prop).rentWithThreeTransportProperties);
            propAttributes.addElement("Ενοίκιο αν κατέχεις 4 ανελκυστήρες: " + ((Transport) prop).rentWithFourTransportProperties);
            desksWrapper.setVisible(false);
            boardsWrapper.setVisible(false);
        }

        list1.setModel(propAttributes);

        updateMortgageInfo();
        propertyNameLabel.setText(prop.getBlockTitle());

        add(panel1);
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
        numberOfDesksLabel.setText(((Room)prop).numberOfDesks + "");
    }

    private void createUIComponents() {
        // TODO: place custom component creation code here
    }

}
