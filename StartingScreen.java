import javax.swing.*;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class StartingScreen extends JFrame {
    private JButton startBtn;
    private JSpinner spinner1;
    private JPanel mainPanel;

    public StartingScreen(){
        setContentPane(mainPanel);
        setTitle("Έναρξη Παιχνιδιού");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        pack();
        setLocationRelativeTo(null);
        setSize(600, 400);
        setVisible(true);
        startBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                int numOfPlayers = (int)spinner1.getValue();
                GameBoard board = new GameBoard(numOfPlayers, new MainBoard());
                board.startGameThread();

            }
        });
    }


    private void createUIComponents() {
        spinner1 = new JSpinner(new SpinnerNumberModel(2, 2, 6, 1));
    }

}
