import javax.swing.*;
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class PropertiesPane extends JFrame {
    private JButton backBtn;
    private JLabel playerIdLabel;
    private JPanel container;
    private JScrollPane scrollPane;
    private JPanel scrollContainer;

    private ArrayList<Property> properties;

    public PropertiesPane(Player player, MainBoard mBoard){
        properties = player.properties;
        scrollContainer.setLayout(new BoxLayout(scrollContainer, BoxLayout.Y_AXIS));

        for(Property p :properties){
            PropertyCard pCard = new PropertyCard(p, mBoard);
            scrollContainer.add(pCard);
        }

        playerIdLabel.setText(Integer.toString(player.getPlayerID()));
        setContentPane(container);
        setTitle("Ιδιοκτησίες");
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setSize(600, 900);
        pack();
        setVisible(true);
        backBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
    }


}
