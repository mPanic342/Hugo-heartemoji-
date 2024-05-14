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

public class SlotMachine {
    private JFrame menuFrame;
    private static final String[] SYMBOLS = {"Cherry", "Apple", "Orange", "Banana", "Lemon", "Grapes", "Watermelon"};
    private Random random;
    private List<String> spinResult;
    private List<JLabel> symbolLabels;
    private JTextField balanceField; 

    public SlotMachine(JFrame menuFrame) {
        this.menuFrame = menuFrame;
        random = new Random();
        spinResult = new ArrayList<>();
        symbolLabels = new ArrayList<>();
    }

    public void slotMachineMenu() {
        JPanel panel = new JPanel(new BorderLayout());

        JPanel slotPanel = new JPanel(new GridLayout(3, 3));
        panel.add(slotPanel, BorderLayout.CENTER);

        for (int i = 0; i < 9; i++) {
            JLabel square = new JLabel(getRandomSymbol(), SwingConstants.CENTER);
            slotPanel.add(square);
            symbolLabels.add(square);
        }

        JButton startButton = new JButton("Spin");
        startButton.setFocusable(false);
        startButton.addActionListener(e -> spin());

        JButton exitButton = new JButton("Exit");
        exitButton.setFocusable(false);
        exitButton.addActionListener(e -> backToMainMenu());

        JPanel buttonPanel = new JPanel(new BorderLayout());


        exitButton.setPreferredSize(new Dimension(60, 50)); 
        startButton.setPreferredSize(new Dimension(240, 50));

        buttonPanel.add(exitButton, BorderLayout.WEST);
        buttonPanel.add(startButton, BorderLayout.CENTER);

        panel.add(buttonPanel, BorderLayout.SOUTH);


        balanceField = new JTextField("Balance: $0");
        balanceField.setEditable(false);
        balanceField.setHorizontalAlignment(JTextField.RIGHT);
        panel.add(balanceField, BorderLayout.NORTH);

        updateBalanceField();

        menuFrame.getContentPane().removeAll();
        menuFrame.getContentPane().add(panel);
        menuFrame.revalidate();
        menuFrame.repaint();
    }

    private void spin() {
        int spinCost = -10;
        updateBalance(spinCost);

        spinResult.clear();

        for (int i = 0; i < 9; i++) {
            spinResult.add(getRandomSymbol());
        }

        for (int i = 0; i < spinResult.size(); i++) {
            JLabel label = symbolLabels.get(i);
            label.setText(spinResult.get(i));
            label.setOpaque(true);
            label.setBackground(null);
        }

        for (int i = 0; i <= spinResult.size() - 3; i++) {
            if (i % 3 == 0 &&
                spinResult.get(i).equals(spinResult.get(i + 1)) && 
                spinResult.get(i + 1).equals(spinResult.get(i + 2))) {

                for (int j = i; j <= i + 2; j++) {
                    JLabel label = symbolLabels.get(j);
                    label.setOpaque(true);
                    label.setBackground(Color.YELLOW);
                }

                updateBalance(100);
            }
        }

        updateBalanceField(); 
    }
    
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

    private String getRandomSymbol() {
        return SYMBOLS[random.nextInt(SYMBOLS.length)];
    }

    private void updateBalance(int amount) {
        JSONParser jsonParser = new JSONParser();

        try (FileReader reader = new FileReader("data/config.json")) {
            Object obj = jsonParser.parse(reader);

            JSONArray balanceArray = (JSONArray) obj;
            JSONObject balanceObject = (JSONObject) balanceArray.get(0);

            String dollars = (String) balanceObject.get("Dollars");
            int currentBalance = Integer.parseInt(dollars);

            int newBalance = currentBalance + amount;

            balanceObject.put("Dollars", Integer.toString(newBalance));

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

    public static void main(String[] args) {
        JFrame frame = new JFrame("Slot Machine");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(700, 500);

        SlotMachine slotMachine = new SlotMachine(frame);
        slotMachine.slotMachineMenu();

        frame.setVisible(true);
    }
    
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
