package org.prac93;


import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;
import java.util.ArrayList;
import java.util.List;

public class Book implements Externalizable {
    private String title;
    private List<Author> authors = new ArrayList<>();
    private int year;
    private int editionNumber;

    public Book() {}

    public Book(String title, List<Author> authors, int year, int editionNumber) {
        this.title = title;
        this.authors = new ArrayList<>(authors);
        this.year = year;
        this.editionNumber = editionNumber;
    }

    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }

    public List<Author> getAuthors() { return authors; }
    public void setAuthors(List<Author> authors) { this.authors = new ArrayList<>(authors); }

    public int getYear() { return year; }
    public void setYear(int year) { this.year = year; }

    public int getEditionNumber() { return editionNumber; }
    public void setEditionNumber(int editionNumber) { this.editionNumber = editionNumber; }

    @Override
    public void writeExternal(ObjectOutput out) throws IOException {
        out.writeUTF(title);
        out.writeInt(year);
        out.writeInt(editionNumber);
        out.writeInt(authors.size());
        for (Author a : authors) {
            a.writeExternal(out);
        }
    }

    @Override
    public void readExternal(ObjectInput in) throws IOException {
        title = in.readUTF();
        year = in.readInt();
        editionNumber = in.readInt();
        int count = in.readInt();
        authors = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            Author a = new Author();
            a.readExternal(in);
            authors.add(a);
        }
    }

    @Override
    public String toString() {
        return "Book{" + "title='" + title + '\'' + ", authors=" + authors +
                ", year=" + year + ", editionNumber=" + editionNumber + '}';
    }
}