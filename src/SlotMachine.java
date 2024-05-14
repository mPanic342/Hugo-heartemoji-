// importing libraries
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

// main class
public class SlotMachine {
    private JFrame menuFrame;
    // SYMBOLS array for all the available slot symbols 
    private static final String[] SYMBOLS = {"Cherry", "Apple", "Orange", "Banana", "Lemon", "Grapes", "Watermelon"};
    private Random random;
    // vars
    private List<String> spinResult;
    private List<JLabel> symbolLabels;
    private JTextField balanceField; 

    // intialzing variables
    public SlotMachine(JFrame menuFrame) {
        this.menuFrame = menuFrame;
        random = new Random();
        spinResult = new ArrayList<>();
        symbolLabels = new ArrayList<>();
    }

    // slot machine menu
    public void slotMachineMenu() {
        JPanel panel = new JPanel(new BorderLayout());

        // creates 3x3 grid for slot machine
        JPanel slotPanel = new JPanel(new GridLayout(3, 3));
        panel.add(slotPanel, BorderLayout.CENTER);

        // randomly selects a symbol (this is dont so its not boring and ugly when slots button is pressed)
        for (int i = 0; i < 9; i++) {
            JLabel square = new JLabel(getRandomSymbol(), SwingConstants.CENTER);
            slotPanel.add(square);
            symbolLabels.add(square);
        }

        // creates the spin button with listener to method
        JButton spinButton = new JButton("Spin");
        spinButton.setFocusable(false);
        spinButton.addActionListener(e -> spin());

        // creates the exit button with listener to method
        JButton exitButton = new JButton("Exit");
        exitButton.setFocusable(false);
        exitButton.addActionListener(e -> backToMainMenu());

        JPanel buttonPanel = new JPanel(new BorderLayout());

        // sets the dimensions of exit and spin button
        exitButton.setPreferredSize(new Dimension(60, 50)); 
        spinButton.setPreferredSize(new Dimension(240, 50));

        // adds the exit and spin button
        buttonPanel.add(exitButton, BorderLayout.WEST);
        buttonPanel.add(spinButton, BorderLayout.CENTER);

        panel.add(buttonPanel, BorderLayout.SOUTH);

        // creates balance field adds it
        balanceField = new JTextField("Balance: $0");
        balanceField.setEditable(false);
        balanceField.setHorizontalAlignment(JTextField.RIGHT);
        panel.add(balanceField, BorderLayout.NORTH);

        // updates the aforementioned balance field
        updateBalanceField();

        // i stole this, it just refreshes the frame
        menuFrame.getContentPane().removeAll();
        menuFrame.getContentPane().add(panel);
        menuFrame.revalidate();
        menuFrame.repaint();
    }

    private void spin() {
        // 5 dollar spin var
        int spinCost = -5;
        // calls method and removes 5 from balance
        updateBalance(spinCost);

        // clears the array list
        spinResult.clear();

        // gets nine random symbols and adds to the array list
        for (int i = 0; i < 9; i++) {
            spinResult.add(getRandomSymbol());
        }

        // iterates through each array item and puts its vaule into its coresponding grid
        for (int i = 0; i < spinResult.size(); i++) {
            JLabel label = symbolLabels.get(i);
            label.setText(spinResult.get(i));
            label.setOpaque(true);
            // removes colour
            label.setBackground(null);
        }

        // checks if there is a match in the spin
        for (int i = 0; i <= spinResult.size() - 3; i++) {
            // checks if it starts in the first column
            if (i % 3 == 0 &&
                spinResult.get(i).equals(spinResult.get(i + 1)) && 
                spinResult.get(i + 1).equals(spinResult.get(i + 2))) {

                // changes winning row to yellow
                for (int j = i; j <= i + 2; j++) {
                    JLabel label = symbolLabels.get(j);
                    label.setOpaque(true);
                    label.setBackground(Color.YELLOW);
                }

                // updates the json balance with 100 more coins
                updateBalance(100);
            }
        }

        // updates the displayed balance in aoo
        updateBalanceField(); 
    }
    
    // recreates java.app menu to pretend like its going back 
    private void backToMainMenu() {
        JFrame mainMenuFrame = new JFrame("Menu");
        mainMenuFrame.setSize(700, 500);
        mainMenuFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel panel = new JPanel(new BorderLayout());

        JButton startButton = new JButton("Start App");
        startButton.setPreferredSize(new Dimension(100, 50));
        startButton.setFocusable(false);
        panel.add(startButton, BorderLayout.SOUTH);

        startButton.addActionListener(new StartButtonListener(mainMenuFrame));

        mainMenuFrame.add(panel);
        mainMenuFrame.setVisible(true);

        menuFrame.dispose();
    }

    // selects random symbol from array 
    private String getRandomSymbol() {
        return SYMBOLS[random.nextInt(SYMBOLS.length)];
    }

    // stolen from stackoverflow and slightly edited
    private void updateBalance(int amount) {
        JSONParser jsonParser = new JSONParser();

        // tries to load config file
        try (FileReader reader = new FileReader("data/config.json")) {
            // parses file
            Object obj = jsonParser.parse(reader);

            // gets balance
            JSONArray balanceArray = (JSONArray) obj;
            JSONObject balanceObject = (JSONObject) balanceArray.get(0);

            // updating balance
            String dollars = (String) balanceObject.get("Dollars");
            int currentBalance = Integer.parseInt(dollars);

            int newBalance = currentBalance + amount;

            balanceObject.put("Dollars", Integer.toString(newBalance));

            // error catching
            try (FileWriter file = new FileWriter("data/config.json")) {
                file.write(balanceArray.toJSONString());
            } catch (IOException e) {
                e.printStackTrace();
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();

        } catch (IOException e) {
            e.printStackTrace();

        } catch (ParseException e) {
            e.printStackTrace();

        }
    }

    // same thing as last one except it doesnt edit balance, just sets value
    private void updateBalanceField() {
        JSONParser jsonParser = new JSONParser();

        try (FileReader reader = new FileReader("data/config.json")) {
            Object obj = jsonParser.parse(reader);

            JSONArray balanceArray = (JSONArray) obj;
            JSONObject balanceObject = (JSONObject) balanceArray.get(0);

            String dollars = (String) balanceObject.get("Dollars");
            balanceField.setText("Balance: $" + dollars);

        } catch (FileNotFoundException e) {
            e.printStackTrace();

        } catch (IOException e) {
            e.printStackTrace();

        } catch (ParseException e) {
            e.printStackTrace();

        }
    }

    // frame for this file
    public static void main(String[] args) {
        JFrame frame = new JFrame("Slot Machine");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(700, 500);

        SlotMachine slotMachine = new SlotMachine(frame);
        slotMachine.slotMachineMenu();

        frame.setVisible(true);
    }
    
    // button listener to start slots
    static class StartButtonListener implements ActionListener {
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
}
