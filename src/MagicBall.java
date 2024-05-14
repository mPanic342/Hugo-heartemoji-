// importing libraries
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import java.awt.Image;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Random;

public class MagicBall {
    public void guess() throws MalformedURLException{ // main method
       
        ImageIcon originalIcon = new ImageIcon(new URL("https://upload.wikimedia.org/wikipedia/commons/9/90/Magic8ball.jpg")); // retrieving image url
        Image image = originalIcon.getImage().getScaledInstance(32, 32, Image.SCALE_DEFAULT); // scaling image
        ImageIcon resizedIcon = new ImageIcon(image);

        // vars for inputdialog prompt
        String message = "Do you have any questions for the magical and mysterious 8-ball that will forever control your life?";
        String title = "Input question here";
        // input dialog to get String input from user
        String input = (JOptionPane.showInputDialog(message, title));

        if (input != null && !input.trim().isEmpty()) {
            Random random = new Random();
            // array of responses for magicball
            String[] responses = {
                "It is certain.",
                "It is decidedly so.",
                "Without a doubt.",
                "Yes - definitely.",
                "You may rely on it.",
                "As I see it, yes.",
                "Most likely.",
                "Outlook good.",
                "Yes.",
                "Signs point to yes.",
                "Reply hazy, try again.",
                "Ask again later.",
                "Better not to tell you now.",
                "Cannot predict now.",
                "Concentrate and ask again.",
                "Don't count on it.",
                "My reply is no.",
                "My sources say no.",
                "Outlook not so good.",
                "Very doubtful."
            };

        // picks random response from total array
        int randomIndex = random.nextInt(responses.length);
        // gets string from array item
        String randomResponse = responses[randomIndex];
        
        // message dialog to show the response
        JOptionPane.showMessageDialog(null, randomResponse, "MAGIC 8-BALL RESPONSE", JOptionPane.INFORMATION_MESSAGE, resizedIcon);
            
        }
    };
    }