package views;

import models.*;

import javax.swing.*;
import java.awt.*;
import java.util.Arrays;

/**
 * A general view combining all components into a functioning GroupChat messenger window.
 */
public class ChatView extends JFrame {

    /**
     * The toolbar that remains pinned above the chatPane.
     */
    private final ToolbarPanel toolbar;

    /**
     * The area in which chats appear.
     */
    private final JPanel chatPane;

    /**
     * The textual input controls that remain below the chat pane.
     */
    private final InputPanel inputPane;

    /**
     * Construct a new ChatView for the given sender to view and edit the given chat.
     *
     * <p> Any messages that have previously been sent in the given chat will be published to the new window retroactively.
     *
     * @param sender The sender to associate with this view.
     * @param chat The chat to associate with this view.
     */
    public ChatView(Person sender, GroupChat chat) {
        super(sender.getName());

        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setLayout(new BorderLayout());
        this.setSize(400, 400);

        this.toolbar = new ToolbarPanel(chat.getParticipantCount());
        this.add(this.toolbar, BorderLayout.NORTH);

        this.chatPane = new JPanel();
        this.chatPane.setLayout(new BoxLayout(this.chatPane, BoxLayout.Y_AXIS));
        if (chat.getMessageCount() != 0) {
            Arrays.stream(chat.getHistory()).forEachOrdered(m -> this.displayNewChat(m, false) );
        }
        this.add(new JScrollPane(this.chatPane), BorderLayout.CENTER);

        this.inputPane = new InputPanel();
        this.add(this.inputPane, BorderLayout.SOUTH);

        this.setVisible(true);
    }

    /**
     * Post a server notification to this view.
     *
     * <p> These messages appear centered in the chat pane.
     *
     * @param serverMessage The message to post within the view.
     */
    public void displayServerMessage(String serverMessage) {
        this.chatPane.add(new MessagePanel(serverMessage));
        this.updateChat();
    }

    /**
     * Post a new chat message to this window.
     *
     * @param msg The message to post in the chat pane.
     * @param isSender A boolean to determine if the message originated from this view.
     */
    public void displayNewChat(Message msg, boolean isSender) {
        this.chatPane.add(new MessagePanel(msg, isSender));
        this.updateChat();
    }

    /**
     * Update the participant count tracker to the new count within this window.
     *
     * @param newCount The new count to post within this view.
     */
    public void updateParticipantCount(int newCount) {
        this.toolbar.updateParticipantCount(newCount);
    }

    /**
     * Access the button intended to add a new user to the Server.
     *
     * @return The "New User" button.
     */
    public JButton getAddUserButton() {
        return this.toolbar.getAddUserButton();
    }

    /**
     * Access the field intended to accept input from the user.
     *
     * @return The text input field.
     */
    public JTextField getInputField() {
        return this.inputPane.getInputField();
    }

    /**
     * Access the button intended to send a message to the chat.
     *
     * @return The "Send" button.
     */
    public JButton getSendButton() {
        return this.inputPane.getSendButton();
    }

    /**
     * An internal function to simplify updating the chat pane within this window.
     */
    private void updateChat() {
        this.chatPane.revalidate();
        this.chatPane.repaint();
    }
}
