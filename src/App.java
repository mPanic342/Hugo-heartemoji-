import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class App {
    public static void main(String args[]) {
        JFrame menuFrame = new JFrame("Menu");
        menuFrame.setSize(700, 500);
        menuFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel panel = new JPanel(new BorderLayout());

        JButton startButton = new JButton("Start App");
        startButton.setPreferredSize(new Dimension(100, 50));
        startButton.setFocusable(false);
        panel.add(startButton, BorderLayout.SOUTH);

        startButton.addActionListener(new StartButtonListener(menuFrame));

        menuFrame.add(panel);
        menuFrame.setVisible(true);
    }
}

class StartButtonListener implements ActionListener {
    private JFrame menuFrame;
    
    public StartButtonListener(JFrame menuFrame) {
        this.menuFrame = menuFrame;
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        SlotMachine game = new SlotMachine(menuFrame);
        game.slotMachineMenu();
    }
}