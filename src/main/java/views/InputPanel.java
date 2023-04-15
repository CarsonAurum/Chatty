package views;

import javax.swing.*;
import java.awt.*;

/**
 * The bottom input panel containing controls for sending messages to a chat.
 */
public class InputPanel extends JPanel {

    /**
     * The textual input area.
     */
    private final JTextField inputArea;

    /**
     * The "Send" message button.
     */
    private final JButton sendButton;

    /**
     * Construct a new input panel.
     */
    public InputPanel() {
        super();
        this.setLayout(new BorderLayout());
        this.inputArea = new JTextField();
        this.sendButton = new JButton("Send");
        this.add(inputArea, BorderLayout.CENTER);
        this.add(sendButton, BorderLayout.EAST);
    }

    /**
     * Access the textual input area within this panel.
     *
     * @return The text input field within this panel.
     */
    public JTextField getInputField() { return this.inputArea; }

    /**
     * Access the send message button within this panel.
     *
     * @return The "Send" button.
     */
    public JButton getSendButton() { return this.sendButton; }
}
