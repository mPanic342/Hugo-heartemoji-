import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class App {
    public static void main(String args[]) {
        JFrame menuFrame = new JFrame("Menu");
        menuFrame.setSize(700, 500);
        menuFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel panel = new JPanel(new BorderLayout());

        // load and displays the image
        ImageIcon imageIcon = new ImageIcon("data/image.png");
        JLabel imageLabel = new JLabel(imageIcon);
        panel.add(imageLabel, BorderLayout.CENTER);

        // panel for menu buttons
        JPanel buttonPanel = new JPanel(new FlowLayout());

        JButton startSlotsButton = new JButton("Start Slots");
        startSlotsButton.setPreferredSize(new Dimension(150, 50));
        startSlotsButton.setFocusable(false);
        buttonPanel.add(startSlotsButton);
        startSlotsButton.addActionListener(new StartSlotsButtonListener(menuFrame));

        JButton startMagic8BallButton = new JButton("Start Magic 8-Ball");
        startMagic8BallButton.setPreferredSize(new Dimension(200, 50));
        startMagic8BallButton.setFocusable(false);
        buttonPanel.add(startMagic8BallButton);
        startMagic8BallButton.addActionListener(new StartMagic8BallButtonListener(menuFrame));

        panel.add(buttonPanel, BorderLayout.SOUTH);

        menuFrame.add(panel);
        menuFrame.setVisible(true);
    }
}

class StartSlotsButtonListener implements ActionListener {
    private JFrame menuFrame;
    
    public StartSlotsButtonListener(JFrame menuFrame) {
        this.menuFrame = menuFrame;
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        SlotMachine game = new SlotMachine(menuFrame);
        game.slotMachineMenu();
    }
}

class StartMagic8BallButtonListener implements ActionListener {
    private JFrame menuFrame;
    
    public StartMagic8BallButtonListener(JFrame menuFrame) {
        this.menuFrame = menuFrame;
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {

        System.out.println("Magic 8-Ball functionality will be implemented here.");
    }
}
