import javax.swing.JOptionPane;
import java.util.Random;
public class MagicBall {
    public void guess(){
        String message = "Do you have any questions for the magical and mysterious 8-ball that will forever control your life?";
        String title = "Input question here";
        String input = (JOptionPane.showInputDialog(message, title));
        if (input != null && !input.trim().isEmpty()) {
            Random random = new Random();
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
            
        int randomIndex = random.nextInt(responses.length);
        String randomResponse = responses[randomIndex];
            
        JOptionPane.showMessageDialog(null, randomResponse, "MAGIC 8-BALL RESPONSE", JOptionPane.INFORMATION_MESSAGE);
            
        }
    };
    }
