package models;

import java.util.UUID;

/**
 * A model representing a user of this application.
 *
 * <p> Each user is associated with a ChatView via a ChatViewController.
 */
public class Person {

    /**
     * A unique identifier for this object.
     */
    private final UUID id;

    /**
     * The human-readable name for this object.
     */
    private final String name;

    /**
     * A constructor for a new person that generates a new unique ID.
     *
     * @param name The human-readable name to associate with this object.
     */
    public Person(String name) {
        this.id = UUID.randomUUID();
        this.name = name;
    }

    /**
     * Get the human-readable name for this object.
     *
     * @return This person's human-readable name.
     */
    public String getName() { return this.name; }

    /**
     * Convert this Person into a string.
     *
     * <p> This is currently done via the given human-readable name.
     *
     * @return This person's human-readable representation.
     */
    @Override
    public String toString() { return this.getName(); }

    /**
     * Determine if a given object is equal to this one.
     *
     * <p> If the given object is also a Person instance, they are compared by ID.
     *
     * @param obj The object to compare with this one.
     * @return `true` if the given object is equal, `false` otherwise.
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null) return false;
        if (this.getClass() != obj.getClass()) return false;
        var other = (Person)obj;
        return this.id == other.id;

    }
}
