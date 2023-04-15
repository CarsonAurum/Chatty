package views;

import models.*;
import javax.swing.*;
import java.awt.*;

/**
 * A view responsible displaying a single message in the appropriate layout.
 */
public class MessagePanel extends JPanel {

    /**
     * Construct a new panel to display a server-wide announcement.
     *
     * <p> The message printed within this view will be centered horizontally.
     *
     * @param serverMsg The server-wide announcement to print within this view.
     */
    public MessagePanel(String serverMsg) {
        super();
        this.setLayout(new BorderLayout());
        var label = new JLabel(serverMsg);
        label.setHorizontalAlignment(JLabel.CENTER);
        this.add(label, BorderLayout.CENTER);
    }

    /**
     * Construct a new panel to display a chat message.
     *
     * <p> If the message is from the user, it will be right-aligned, otherwise it will be left-aligned.
     *
     * @param msg The message to print within this view.
     * @param isSender A flag to determine the orientation of text within this view.
     */
    public MessagePanel(Message msg, boolean isSender) {
        super();
        this.setLayout(new BorderLayout());
        this.add(new JLabel(msg.toString()), isSender ? BorderLayout.EAST : BorderLayout.WEST);
    }
}
