package org.prac92;

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
            Author a1 = new Author("Leo", "Tolstoy");
            Author a2 = new Author("Arthur", "Conan Doyle");

            Book b1 = new Book("War and Peace", Arrays.asList(a1), 1869, 1);
            Book b2 = new Book("The Hound of the Baskervilles", Arrays.asList(a2), 1902, 1);

            Reader r1 = new Reader("Andrii", "Bondarenko", "R-101");
            Reader r2 = new Reader("Kateryna", "Tkachenko", "R-102");

            Library lib = new Library("Sunrise Library");
            lib.addBook(b1);
            lib.addBook(b2);
            lib.registerReader(r1);
            lib.registerReader(r2);

            r1.borrow(b1);
            r2.borrow(b2);

            String file = "library_v2.bin";
            serialize(lib, file);

            Library restored = deserialize(file);
            System.out.println(restored);
        }
    }
