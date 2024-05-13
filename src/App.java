import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import java.io.FileNotFoundException;
import org.json.simple.parser.ParseException;
import java.io.FileReader;
import java.io.IOException;

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

        JSONParser jsonParser = new JSONParser();

        try (FileReader reader = new FileReader("data/config.json")) {
            Object obj = jsonParser.parse(reader);
            
            JSONArray balanceArray = (JSONArray) obj;
            JSONObject balanceObject = (JSONObject) balanceArray.get(0);
        
            String dollars = (String) balanceObject.get("Dollars");
            System.out.println("Balance: $" + dollars);

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }

        startButton.addActionListener(new StartButtonListener());

        menuFrame.add(panel);
        menuFrame.setVisible(true);
    }
}

class StartButtonListener implements ActionListener {
    @Override
    public void actionPerformed(ActionEvent e) {
        System.out.println("Black Jack CLICKED");
        // Assuming blackJack is a class that contains the logic for the Blackjack game
        blackJack game = new blackJack(); 
        game.blackJackMenu();
    }
}
