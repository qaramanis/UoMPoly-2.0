import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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
    public BuyPropertyScreen(Player pl, MainBoard mBoard){
        Property prop = (Property)pl.getCurrentBlock();
        propAttributes = new DefaultListModel<String>();
        serviceDisclaimers.setVisible(false);

        if(prop instanceof Room){
            propAttributes.addElement("Τιμή: " + prop.getCost());
            propAttributes.addElement("Ενοίκιο: " + ((Room) prop).getRent());
            propAttributes.addElement("Ενοίκιο με 1 έδρανο: " + ((Room) prop).getRentWithOneDesk());
            propAttributes.addElement("Ενοίκιο με 2 έδρανα: " + ((Room) prop).getRentWithTwoDesks());
            propAttributes.addElement("Ενοίκιο με 3 έδρανα: " + ((Room) prop).getRentWithThreeDesks());
            propAttributes.addElement("Ενοίκιο με 4 έδρανα: " + ((Room) prop).getRentWithFourDesks());
            propAttributes.addElement("Ενοίκιο με πίνακα: " + ((Room) prop).getRentWithBoard());
        }
        if(prop instanceof Transport){
            propAttributes.addElement("Τιμή: " + prop.getCost());
            propAttributes.addElement("Ενοίκιο: " + ((Transport) prop).rentWithOneTransportProperty);
            propAttributes.addElement("Ενοίκιο αν κατέχεις 2 ανελκυστήρες: " + ((Transport) prop).rentWithTwoTransportProperties);
            propAttributes.addElement("Ενοίκιο αν κατέχεις 3 ανελκυστήρες: " + ((Transport) prop).rentWithThreeTransportProperties);
            propAttributes.addElement("Ενοίκιο αν κατέχεις 4 ανελκυστήρες: " + ((Transport) prop).rentWithFourTransportProperties);
        }
        if(prop instanceof Service serv){
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
}
