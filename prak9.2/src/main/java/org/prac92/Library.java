package org.prac92;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Library implements Serializable {
    private static final long serialVersionUID = 1L;

    private String name;

    private transient List<Book> books = new ArrayList<>();
    private transient List<Reader> readers = new ArrayList<>();

    public Library() {}

    public Library(String name) { this.name = name; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public List<Book> getBooks() { return books; }
    public List<Reader> getReaders() { return readers; }

    public void addBook(Book book) { books.add(book); }
    public void registerReader(Reader reader) { readers.add(reader); }

    private void writeObject(ObjectOutputStream out) throws IOException {
        out.defaultWriteObject();
        out.writeInt(books.size());
        for (Book b : books) {
            out.writeUTF(b.getTitle());
            out.writeInt(b.getYear());
            out.writeInt(b.getEditionNumber());
            out.writeInt(b.getAuthors().size());
            for (Author a : b.getAuthors()) {
                out.writeUTF(a.getFirstName());
                out.writeUTF(a.getLastName());
            }
        }
        out.writeInt(readers.size());
        for (Reader r : readers) {
            out.writeUTF(r.getFirstName());
            out.writeUTF(r.getLastName());
            out.writeUTF(r.getRegistrationNumber());
            out.writeInt(r.getBorrowedBooks().size());
            for (Book b : r.getBorrowedBooks()) {
                out.writeUTF(b.getTitle());
                out.writeInt(b.getYear());
                out.writeInt(b.getEditionNumber());
                out.writeInt(b.getAuthors().size());
                for (Author a : b.getAuthors()) {
                    out.writeUTF(a.getFirstName());
                    out.writeUTF(a.getLastName());
                }
            }
        }
    }

    private void readObject(ObjectInputStream in) throws IOException, ClassNotFoundException {
        in.defaultReadObject();

        books = new ArrayList<>();
        int bookCount = in.readInt();
        for (int i = 0; i < bookCount; i++) {
            String title = in.readUTF();
            int year = in.readInt();
            int edition = in.readInt();
            int authorCount = in.readInt();
            List<Author> authors = new ArrayList<>();
            for (int j = 0; j < authorCount; j++) {
                authors.add(new Author(in.readUTF(), in.readUTF()));
            }
            books.add(new Book(title, authors, year, edition));
        }

        readers = new ArrayList<>();
        int readerCount = in.readInt();
        for (int i = 0; i < readerCount; i++) {
            String fn = in.readUTF();
            String ln = in.readUTF();
            String reg = in.readUTF();
            Reader r = new Reader(fn, ln, reg);

            int borrowedCount = in.readInt();
            for (int j = 0; j < borrowedCount; j++) {
                String title = in.readUTF();
                int year = in.readInt();
                int edition = in.readInt();
                int authorCount = in.readInt();
                List<Author> authors = new ArrayList<>();
                for (int k = 0; k < authorCount; k++) {
                    authors.add(new Author(in.readUTF(), in.readUTF()));
                }
                r.borrow(new Book(title, authors, year, edition));
            }
            readers.add(r);
        }
    }

    @Override
    public String toString() {
        return "Library{" + "name='" + name + '\'' +
                ", books=" + books + ", readers=" + readers + '}';
    }
}