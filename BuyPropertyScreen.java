import javax.swing.*;

public class BuyPropertyScreen extends JFrame {
    private JPanel panel1;
    private JPanel bottomPanel;
    private JButton cancelBtn;
    private JPanel centerPanel;
    private JList list1;
    private JLabel propertyNameLabel;
    private JButton buyBtn;
    private DefaultListModel<String> propAttributes;
    public BuyPropertyScreen(Player pl){
        Property prop = (Property)pl.currentBlock;
        propAttributes = new DefaultListModel<String>();

        if(prop instanceof Room){
            propAttributes.addElement("Τιμή: " + prop.cost);
            propAttributes.addElement("Ενοίκιο: " + ((Room) prop).getRent());
            propAttributes.addElement("Ενοίκιο με 1 έδρανο: " + ((Room) prop).getRentWithOneDesk());
            propAttributes.addElement("Ενοίκιο με 2 έδρανα: " + ((Room) prop).getRentWithTwoDesks());
            propAttributes.addElement("Ενοίκιο με 3 έδρανα: " + ((Room) prop).getRentWithThreeDesks());
            propAttributes.addElement("Ενοίκιο με 4 έδρανα: " + ((Room) prop).getRentWithFourDesks());
            propAttributes.addElement("Ενοίκιο με πίνακα: " + ((Room) prop).getRentWithBoard());
        }
        if(prop instanceof Transport){
            propAttributes.addElement("Τιμή: " + prop.cost);
            propAttributes.addElement("Ενοίκιο: " + ((Transport) prop).rentWithOneTransportProperty);
            propAttributes.addElement("Ενοίκιο αν κατέχεις 2 ανελκυστήρες: " + ((Transport) prop).rentWithTwoTransportProperties);
            propAttributes.addElement("Ενοίκιο αν κατέχεις 3 ανελκυστήρες: " + ((Transport) prop).rentWithThreeTransportProperties);
            propAttributes.addElement("Ενοίκιο αν κατέχεις 4 ανελκυστήρες: " + ((Transport) prop).rentWithFourTransportProperties);
        }

        list1.setModel(propAttributes);

        propertyNameLabel.setText(prop.getBlockTitle() + " - " + prop.blockPosition);
        setContentPane(panel1);
        setTitle("Αγορά Ιδιοκτησίας");
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setSize(600, 900);
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }
}
