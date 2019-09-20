package com.tekchand.testapp.ui.main;

import androidx.annotation.NonNull;

import java.util.Objects;

public class Human {
    private static int count = 0;
    private int id;
    private String name;
    private String location;
    private String email;

    private int autoId(){
        count = count +1;
        return count;
    }

    public Human(String name, String location, String email) {
        this.id = autoId();
        this.name = name;
        this.location = location;
        this.email = email;
    }

    public int getId() {
        return id;
    }

    public void setId(@NonNull final int  id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(@NonNull final String name) {
        this.name = name;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(@NonNull final String location) {
        this.location = location;
    }

    public String getEmail() {
        return email;
    }

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
