package org.prac93;

import java.io.*;
import java.util.Arrays;

public class LibraryDriver {
    public static void serialize(Library library, String file) throws IOException {
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(file))) {
            out.writeObject(library);
        }
    }

    public static Library deserialize(String file) throws IOException, ClassNotFoundException {
        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(file))) {
            return (Library) in.readObject();
        }
    }

    public static void main(String[] args) throws Exception {
        Author a1 = new Author("George", "Orwell");
        Author a2 = new Author("Aldous", "Huxley");
        Book b1 = new Book("1984", Arrays.asList(a1), 1949, 1);
        Book b2 = new Book("Brave New World", Arrays.asList(a2), 1932, 1);

        Reader r1 = new Reader("Ivan", "Petrenko", "R-001");
        Reader r2 = new Reader("Olena", "Koval", "R-002");

        Library lib = new Library("Central Library");
        lib.addBook(b1);
        lib.addBook(b2);
        lib.registerReader(r1);
        lib.registerReader(r2);

        r1.borrow(b1);
        r2.borrow(b2);

        String file = "library_v3.bin";
        serialize(lib, file);

        Library restored = deserialize(file);
        System.out.println(restored);
    }
}