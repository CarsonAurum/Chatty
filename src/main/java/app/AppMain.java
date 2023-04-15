package app;
import javax.swing.*;

/**
 * The main class responsible for running the application.
 */
public class AppMain {
    /**
     * The main function:
     *      (1) First, this function requests a valid (non empty/null) name from the client to be used for the first
     *          user.
     *      (2) Register the new person with the server (which creates the client's window to bootstrap the app).
     *
     * @param args Ignored within this program.
     */
    public static void main(String[] args) {
        var firstUserName = JOptionPane.showInputDialog(
                null,
                "Please enter a name for the new user.",
                "New User Name",
                JOptionPane.QUESTION_MESSAGE
        );
        Server.getInstance().addPerson(firstUserName);
    }
}
