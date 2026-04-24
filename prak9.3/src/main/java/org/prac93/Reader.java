package org.prac93;


import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;
import java.util.ArrayList;
import java.util.List;

public class Reader implements Externalizable {
    private String firstName;
    private String lastName;
    private String registrationNumber;
    private List<Book> borrowedBooks = new ArrayList<>();

    public Reader() {}

    public Reader(String firstName, String lastName, String registrationNumber) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.registrationNumber = registrationNumber;
    }

    public String getFirstName() { return firstName; }
    public void setFirstName(String firstName) { this.firstName = firstName; }

    public String getLastName() { return lastName; }
    public void setLastName(String lastName) { this.lastName = lastName; }

    public String getRegistrationNumber() { return registrationNumber; }
    public void setRegistrationNumber(String registrationNumber) { this.registrationNumber = registrationNumber; }

    public List<Book> getBorrowedBooks() { return borrowedBooks; }

    public void borrow(Book book) { borrowedBooks.add(book); }
    public void returnBook(Book book) { borrowedBooks.remove(book); }

    @Override
    public void writeExternal(ObjectOutput out) throws IOException {
        out.writeUTF(firstName);
        out.writeUTF(lastName);
        out.writeUTF(registrationNumber);
        out.writeInt(borrowedBooks.size());
        for (Book b : borrowedBooks) {
            b.writeExternal(out);
        }
    }

    @Override
    public void readExternal(ObjectInput in) throws IOException {
        firstName = in.readUTF();
        lastName = in.readUTF();
        registrationNumber = in.readUTF();
        int count = in.readInt();
        borrowedBooks = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            Book b = new Book();
            b.readExternal(in);
            borrowedBooks.add(b);
        }
    }

    @Override
    public String toString() {
        return "Reader{" + firstName + " " + lastName +
                ", reg='" + registrationNumber + '\'' +
                ", borrowedBooks=" + borrowedBooks + '}';
    }
}