package app;

import controllers.*;
import models.*;
import java.util.*;

/**
 * A singleton object containing a single source of truth for all views within this application.
 */
public class Server {
    /**
     * Following the singleton pattern, this is the only instance of this object within the application.
     */
    private static Server instance;

    /**
     * An internal constructor to configure properties (trivial).
     */
    private Server() {
        this.windows = new HashMap<>();
        this.chat = new GroupChat();
    }

    /**
     * Access the singleton instance of this class. If there is not yet one, it will be instantiated.
     *
     * @return The singleton instance of this class.
     */
    public static Server getInstance() {
        if (instance == null)
            instance = new Server();
        return instance;
    }

    /**
     * The internal map relating each Person to the controller associated with their view.
     */
    private final Map<Person, ChatViewController> windows;

    /**
     * The group chat instance unifying all windows within this application.
     */
    private final GroupChat chat;

    /**
     * Creates a new person with the given name, adds them to the chat, and creates an associated window.
     *
     * @param name The name for the new user.
     */
    public void addPerson(String name) {
        var person = new Person(name);
        this.chat.addParticipant(person);
        this.windows.put(person, new ChatViewController(person, chat));
        Arrays.stream(this.chat.getParticipants()).forEach(this::updateCountFor);
        this.windows.values().forEach(c ->
            c.displayServerMessage(name + " has joined the chat!"));
    }

    /**
     * Removes a person from the chat, and destroys their associated window.
     *
     * @param person The person to remove from the application.
     */
    public void removePerson(Person person) {
        chat.removeParticipant(person);
        windows.remove(person);
        Arrays.stream(this.chat.getParticipants()).forEach(this::updateCountFor);
        this.windows.values().forEach(c ->
                c.displayServerMessage(person.getName() + " has left the chat!"));
    }

    /**
     * Dispatch a new message from the given sender with the given content to the server chat. This will trigger all
     * windows associated with this chat to be updated with the new message.
     *
     * @param sender The person who sent this message.
     * @param content The content typed by the user in the text panel within the UI.
     */
    public void dispatchMessage(Person sender, String content) {
        var msg = this.chat.addMessage(sender, content);

        Arrays.stream(this.chat.getParticipants()).forEach(p ->
            this.windows.get(p).displayNewMessage(msg, p.equals(msg.sender())));
    }

    /**
     * Determine how many windows remain within the server.
     *
     * @return A count of active remaining view controllers registered to this server.
     */
    public int getWindowCount() {
        return this.windows.size();
    }

    /**
     * Internal helper consumer for updating the participant count within a person's view in a concise manner
     * (with stream support).
     *
     * @param p The person whose view should have its participant count updated.
     */
    private void updateCountFor(Person p) {
        this.windows.get(p).updateParticipantCount(this.chat.getParticipantCount());
    }
}
