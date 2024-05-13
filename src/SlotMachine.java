import javax.swing.*;
import java.awt.*;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import java.util.Random;

public class SlotMachine {
    private JFrame menuFrame;
    private static final String[] SYMBOLS = {"Cherry", "Apple", "Orange", "Banana", "Lemon", "Grapes", "Watermelon"};
    private Random random;

    public SlotMachine(JFrame menuFrame) {
        this.menuFrame = menuFrame;
        random = new Random();
    }
    
    public void slotMachineMenu() {
        JPanel panel = new JPanel(new BorderLayout());

        JPanel slotPanel = new JPanel(new GridLayout(3, 3));
        panel.add(slotPanel, BorderLayout.CENTER);

        for (int i = 0; i < 9; i++) {
            JLabel square = new JLabel(getRandomSymbol(), SwingConstants.CENTER);
            slotPanel.add(square);
        }

        JButton startButton = new JButton("Spin");
        startButton.setPreferredSize(new Dimension(100, 50));
        startButton.setFocusable(false);
        startButton.addActionListener(e -> spin());
        panel.add(startButton, BorderLayout.SOUTH);

        JTextField balanceField = new JTextField("Balance: $0");
        balanceField.setEditable(false);
        balanceField.setHorizontalAlignment(JTextField.RIGHT);
        panel.add(balanceField, BorderLayout.NORTH);

        updateBalance(balanceField);
        // stolen 
        menuFrame.getContentPane().removeAll();
        menuFrame.getContentPane().add(panel);
        menuFrame.revalidate();
        menuFrame.repaint();
    }

    private void spin() {
        int spinCost = 10;

        /// make table to with 9 values to simluate the rows, read rows and then update the table on screen and check values
        
        
    }

    private String getRandomSymbol() {
        return SYMBOLS[random.nextInt(SYMBOLS.length)];
    }

    private void updateBalance(JTextField balanceField) {
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
        frame.setSize(400, 400);

        SlotMachine slotMachine = new SlotMachine(frame);
        slotMachine.slotMachineMenu();

        frame.setVisible(true);
    }
}
