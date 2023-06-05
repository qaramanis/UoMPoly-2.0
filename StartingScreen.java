import javax.swing.*;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Insets;

public class StartingScreen extends JFrame {
    private JButton startBtn;
    private JSpinner spinner1;
    private JPanel mainPanel;

    public StartingScreen(){
        setContentPane(mainPanel);
        setTitle("Έναρξη Παιχνιδιού");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(600, 400);
        setVisible(true);
    }


    private void createUIComponents() {
        spinner1 = new JSpinner(new SpinnerNumberModel(2, 2, 6, 1));
    }

}
