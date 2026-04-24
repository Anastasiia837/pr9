package org.prac93;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;
import java.util.ArrayList;
import java.util.List;

public class Library implements Externalizable {
    private String name;
    private List<Book> books = new ArrayList<>();
    private List<Reader> readers = new ArrayList<>();

    public Library() {}

    public Library(String name) { this.name = name; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public List<Book> getBooks() { return books; }
    public List<Reader> getReaders() { return readers; }

    public void addBook(Book book) { books.add(book); }
    public void registerReader(Reader reader) { readers.add(reader); }

    @Override
    public void writeExternal(ObjectOutput out) throws IOException {
        out.writeUTF(name);
        out.writeInt(books.size());
        for (Book b : books) {
            b.writeExternal(out);
        }
        out.writeInt(readers.size());
        for (Reader r : readers) {
            r.writeExternal(out);
        }
    }

    @Override
    public void readExternal(ObjectInput in) throws IOException {
        name = in.readUTF();
        int bookCount = in.readInt();
        books = new ArrayList<>();
        for (int i = 0; i < bookCount; i++) {
            Book b = new Book();
            b.readExternal(in);
            books.add(b);
        }
        int readerCount = in.readInt();
        readers = new ArrayList<>();
        for (int i = 0; i < readerCount; i++) {
            Reader r = new Reader();
            r.readExternal(in);
            readers.add(r);
        }
    }

    @Override
    public String toString() {
        return "Library{" + "name='" + name + '\'' +
                ", books=" + books + ", readers=" + readers + '}';
    }
}