package models;

/**
 * A model representing a message sent by a Person.
 *
 * @param sender  The sender of this message.
 * @param content The content sent in this message.
 */
public record Message(Person sender, String content) {

    /**
     * Converting this message to a string puts it in the appropriate format for display.
     *
     * @return sender.toString(): this.content()
     */
    @Override
    public String toString() {
        return sender.toString() + ": " + this.content();
    }
}
