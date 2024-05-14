import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.net.MalformedURLException;


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

        // slots button with action listener to tell when it is clicked
        JButton startSlotsButton = new JButton("Start Slots");
        startSlotsButton.setPreferredSize(new Dimension(150, 50));
        startSlotsButton.setFocusable(false);
        buttonPanel.add(startSlotsButton);
        startSlotsButton.addActionListener(new StartSlotsButtonListener(menuFrame));

        // magicball button with action listener to tell when it is clicked
        JButton startMagicBallButton = new JButton("Start Magic 8-Ball");
        startMagicBallButton.setPreferredSize(new Dimension(200, 50));
        startMagicBallButton.setFocusable(false);
        buttonPanel.add(startMagicBallButton);
        startMagicBallButton.addActionListener(new StartMagicBallButtonListener(menuFrame));

        panel.add(buttonPanel, BorderLayout.SOUTH);

        menuFrame.add(panel);
        menuFrame.setVisible(true);
    }
}


// stolen since i had no idea how to do this 
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

// stolen since i had no idea how to do this 
class StartMagicBallButtonListener implements ActionListener {
    private JFrame menuFrame;
    
    public StartMagicBallButtonListener(JFrame menuFrame) {
        this.menuFrame = menuFrame;
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {

        try {
            MagicBall guessObject = new MagicBall();

            guessObject.guess();
        } catch (MalformedURLException ex) {

            ex.printStackTrace(); 
        }
    }
}
