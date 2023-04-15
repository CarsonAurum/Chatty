package models;

import java.util.*;

/**
 * A model representing a group text message where multiple participants contribute to a shared message history.
 */
public class GroupChat {

    /**
     * The list of participants within this chat currently.
     */
    private final List<Person> participants;

    /**
     * The full list of messages sent within this chat (at any point during the application's lifetime).
     */
    private final List<Message> history;

    /**
     * An trivial constructor to configure properties.
     */
    public GroupChat() {
        this.participants = new ArrayList<>();
        this.history = new ArrayList<>();
    }

    /**
     * Access a count of messages within this chat's history.
     *
     * @return An integer representing the number of entries in this chat's message list.
     */
    public int getMessageCount() { return this.history.size(); }

    /**
     * Access a count of participants currently active in the chat.
     *
     * @return An integer representing the number of chat participants.
     */
    public int getParticipantCount() { return this.participants.size(); }

    /**
     * Add a new person to this chat.
     *
     * @param person The person to add to this chat.
     */
    public void addParticipant(Person person) { this.participants.add(person); }

    /**
     * Add a new message to this chat.
     *
     * @param msg The message to add to this chat.
     */
    public void addMessage(Message msg) { this.history.add(msg); }

    /**
     * Add a new message to the chat, receiving a copy of the newly created message.
     *
     * @param sender The Person responsible for sending this message.
     * @param message The content of this message.
     * @return The newly created message.
     */
    public Message addMessage(Person sender, String message) {
        var msg = new Message(sender, message);
        this.addMessage(msg);
        return msg;
    }

    /**
     * Remove an active participant from the chat.
     *
     * @param person The person to remove from chat.
     */
    public void removeParticipant(Person person) { this.participants.remove(person); }

    /**
     * Get an immutable array of participants active in this chat.
     *
     * @return An array of all currently active participants in this chat.
     */
    public Person[] getParticipants() {
        Person[] participantArray = new Person[this.participants.size()];
        participantArray = this.participants.toArray(participantArray);
        return participantArray;
    }

    /**
     * Get an immutable array of message's from this chat's history.
     *
     * @return An array of all messages sent in this chat.
     */
    public Message[] getHistory() {
        Message[] historyArray = new Message[this.history.size()];
        historyArray = this.history.toArray(historyArray);
        return historyArray;
    }
}
