import javax.swing.JOptionPane;
import java.util.Random;
// class name must be same as class file
public class MagicBall {
// method for storing guessing variables
    public void guess(){
        // declaring variables
        String message = "Do you have any questions for the magical and mysterious 8-ball that will forever control your life?";
        String title = "MAGIC 8-BALL";
        String input =  (JOptionPane.showInputDialog(message, title));
        // declaring word bank
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
        
    };
}
