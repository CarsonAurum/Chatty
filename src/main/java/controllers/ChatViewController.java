package controllers;

import app.Server;
import views.*;
import models.*;

import javax.swing.*;
import java.awt.event.*;

/**
 * The View Controller responsible for handling UI actions and relaying updates to the model.
 */
public class ChatViewController {
    /**
     * The view controlled by this class.
     */
    private final ChatView view;

    /**
     * The sender for this controller's view.
     */
    private final Person senderModel;

    /**
     * Create a new controller (and view) for the given person within the given chat.
     *
     * @param sender The person whose view should be created.
     * @param chat The chat to link with this view.
     */
    public ChatViewController(Person sender, GroupChat chat) {
        this.senderModel = sender;
        this.view = new ChatView(senderModel, chat);
        configureListeners();
    }

    /**
     * An internal function to configure all listeners on the view controlled by this class.
     */
    private void configureListeners() {
        this.view.addWindowListener(new WindowAdapter() {

            /**
             * Using this implementation of the windowOpened handler, once a new chat view is established, the keyboard
             * will take focus.
             * @param e The specific details of this event are ignored in this handler.
             */
            @Override
            public void windowOpened(WindowEvent e) {
                view.getInputField().requestFocusInWindow();
            }

            /**
             * Using this implementation of the windowClosed handler, once a chat view is closed, the model is updated
             * to reflect the loss of this participant in the chat.
             *
             * If, once this window is closed, there are no more windows within the chat server, the whole application
             * will be closed.
             *
             * @param e The specific details of this event are ignored in this handler.
             */
            @Override
            public void windowClosed(WindowEvent e) {
                Server.getInstance().removePerson(senderModel);
                if (Server.getInstance().getWindowCount() == 0)
                    System.exit(0);
            }

            /**
             * Using this implementation of the windowActivated handler, each time a client is reactivated by the OS,
             * the keyboard will become the focused element. This helps streamline returning to other chat windows after
             * pressing the "New User" button
             *
             * @param e The specific details of this event are ignored in this handler.
             */
            @Override
            public void windowActivated(WindowEvent e) {
                view.getInputField().requestFocusInWindow();
            }
        });

        /*
         * The action listener associated with the "Add User" button requests a new username with a JOptionPane.
         * Once a valid name has been given (nonnull/not empty), the server creates a new person and registers a new
         * window for the user.
         */
        this.view.getAddUserButton().addActionListener(e -> {
            var newUserName = JOptionPane.showInputDialog(
                    this.view,
                    "Please enter a name for the new user.",
                    "New User Name",
                    JOptionPane.QUESTION_MESSAGE
            );
            Server.getInstance().addPerson(newUserName);
        });

        /*
         * The action listener associated with the send button dispatches the current contents of the TextField to the
         * server to notify other windows of the event & trigger UI updates.
         */
        this.view.getSendButton().addActionListener(e -> {
            Server.getInstance().dispatchMessage(senderModel, this.view.getInputField().getText());
            this.view.getInputField().setText("");
        });

        /*
         * The key listener associated with the keyboard dispatches a button press to the send button to enable ergonomic
         * chatting.
         */
        this.view.getInputField().addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER)
                    view.getSendButton().doClick();
            }
        });
    }

    /**
     * The view controller's hook to notify a view of a new message that should be rendered to the frame.
     *
     * @param msg The message to render within the view.
     */
    public void displayNewMessage(Message msg, boolean isSender) {
        this.view.displayNewChat(msg, isSender);
    }

    /**
     * The view controller's hook to notify a view of a new server announcement that should be rendered to the frame.
     *
     * @param serverMessage The server announcement to render within the view.
     */
    public void displayServerMessage(String serverMessage) {
        this.view.displayServerMessage(serverMessage);
    }

    /**
     * The view controller's hook to notify a view of a change in th participant count that should be updated in the frame.
     *
     * @param newCount The new participant count to display.
     */
    public void updateParticipantCount(int newCount) {
        this.view.updateParticipantCount(newCount);
    }
}
