package com.tekchand.testapp.ui.main.tab1;

import androidx.annotation.NonNull;

import java.util.Objects;

/**
 * @author Tek Chand
 * Human class represents the information of a human such as name, location, email.
 */
public class Human {

    private static int count = 0;
    private int id;
    private String name;
    private String location;
    private String email;

    /**
     * Increase the count by one when a different person added.
     * @return count unique Id of a person
     */
    private int autoId(){
        count = count +1;
        return count;
    }

    /**
     * @param name name of a person
     * @param location location of the person where he/she lives
     * @param email email of the person
     */

    public Human(String name, String location, String email) {
        this.id = autoId();
        this.name = name;
        this.location = location;
        this.email = email;
    }

    /**
     * Get Id of a person
     * @return id unique Id of a person
     */
    public int getId() {
        return id;
    }

    /**
     * Sets Id of a person
     * @param id id of a person
     */
    public void setId(@NonNull final int  id) {
        this.id = id;
    }

    /**
     * Get name of a person
     * @return name name of a person
     */
    public String getName() {
        return name;
    }
    /**
     * Set name of a person
     * @param name name of a person
     */
    public void setName(@NonNull final String name) {
        this.name = name;
    }

    /**
     * Gets location
     * @return location of a person
     */
    public String getLocation() {
        return location;
    }

    /**
     * Sets location of a person
     * @param location location of a person
     */
    public void setLocation(@NonNull final String location) {
        this.location = location;
    }

    /**
     * Gets email
     * @return email of a person
     */
    public String getEmail() {
        return email;
    }
    /**
     * Sets email of a person
     * @param email email of a person
     */
    public void setEmail(@NonNull final String email) {
        this.email = email;
    }

    @Override
    public boolean equals(@NonNull final Object o) {
        if (this == o) return true;
        if (!(o instanceof Human)) return false;
        Human human = (Human) o;
        return id == human.id &&
                Objects.equals(name, human.name) &&
                Objects.equals(location, human.location) &&
                Objects.equals(email, human.email);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, location, email);
    }

    @Override
    public String toString() {
        return "Human{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", location='" + location + '\'' +
                ", addr='" + email + '\'' +
                '}';
    }
}
