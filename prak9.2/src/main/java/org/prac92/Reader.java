package org.prac92;


import java.util.ArrayList;
import java.util.List;

public class Reader {
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
    public String toString() {
        return "Reader{" + firstName + " " + lastName +
                ", reg='" + registrationNumber + '\'' +
                ", borrowedBooks=" + borrowedBooks + '}';
    }
}