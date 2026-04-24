package org.prac93;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

public class Author implements Externalizable {
    private String firstName;
    private String lastName;

    public Author() {}

    public Author(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public String getFirstName() { return firstName; }
    public void setFirstName(String firstName) { this.firstName = firstName; }

    public String getLastName() { return lastName; }
    public void setLastName(String lastName) { this.lastName = lastName; }

    @Override
    public void writeExternal(ObjectOutput out) throws IOException {
        out.writeUTF(firstName);
        out.writeUTF(lastName);
    }

    @Override
    public void readExternal(ObjectInput in) throws IOException {
        firstName = in.readUTF();
        lastName = in.readUTF();
    }

    @Override
    public String toString() {
        return firstName + " " + lastName;
    }
}