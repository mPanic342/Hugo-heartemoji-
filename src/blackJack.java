import javax.swing.*;
import java.awt.*;

public class blackJack { // Changed class name to start with uppercase
    public void blackJackMenu() {
        JFrame menuFrame = new JFrame("Menu");
        menuFrame.setSize(700, 500);
        menuFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel panel = new JPanel(new BorderLayout());

        JButton startButton = new JButton("Start Game");
        startButton.setPreferredSize(new Dimension(100, 50));
        startButton.setFocusable(false);
        panel.add(startButton, BorderLayout.SOUTH);

        menuFrame.add(panel);
        menuFrame.setVisible(true);
    }
}
