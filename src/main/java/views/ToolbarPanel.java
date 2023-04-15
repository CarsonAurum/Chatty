package views;

import javax.swing.*;
import java.awt.*;

/**
 * A toolbar panel containing miscellaneous controls relating to the Server, rather than the specific view in which
 * this toolbar appears.
 */
public class ToolbarPanel extends JPanel {

    /**
     * A label to display the number of active users in the chat shown within this panel's accompanying ChatView.
     */
    private final JLabel userCountLabel;

    /**
     * A button to enable the creation of new users on the server.
     */
    private final JButton addUserButton;

    /**
     * Construct a new toolbar.
     *
     * @param chatCount The number of chat participants to display on creation of this view.
     */
    public ToolbarPanel(int chatCount) {
        super();
        this.setLayout(new BorderLayout());
        this.addUserButton = new JButton("New User");
        this.add(this.addUserButton, BorderLayout.EAST);
        this.userCountLabel = new JLabel(getUserCountText(chatCount));
        this.add(this.userCountLabel, BorderLayout.WEST);
    }

    /**
     * An internal function to create the final user count string to be printed within this panel.
     *
     * @param count The number of chat participants to display within the computed String.
     * @return A human-readable string showing the current participant count given.
     */
    private String getUserCountText(int count) {
        return "Current User Count: " + count;
    }

    /**
     * Update the number of participants printed within this panel to a new value.
     *
     * @param newCount The new number of chat participants to display.
     */
    public void updateParticipantCount(int newCount) {
        this.userCountLabel.setText(this.getUserCountText(newCount));
        this.userCountLabel.revalidate();
        this.userCountLabel.repaint();
    }

    /**
     * Get the button intended to add new users to the server.
     *
     * @return The "New User" button.
     */
    public JButton getAddUserButton() {
        return this.addUserButton;
    }
}
