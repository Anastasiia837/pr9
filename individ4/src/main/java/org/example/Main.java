package org.example;

public class Main {
    static void main(String[] args) throws InterruptedException {
        Hotel hotel = new Hotel(2);

        Booking ivanov = new Booking("Іванов", 3000, hotel);
        Booking petrenko = new Booking("Петренко", 4000, hotel);
        Booking shevchenko = new Booking("Шевченко", 2000, hotel);
        Booking kovalenko = new Booking("Коваленко", 1500, hotel);

        System.out.println("Готель відкривається (місткість: 2)\n");

        ivanov.start();
        Thread.sleep(500);

        petrenko.start();
        Thread.sleep(500);

        shevchenko.start();
        Thread.sleep(500);

        kovalenko.start();
        Thread.sleep(1500);

        Booking found = hotel.findBySurname("Іванов");
        System.out.println("\nПошук «Іванов»: " +
                (found != null ? "проживає" : "не знайдений"));

        ivanov.join();
        petrenko.join();
        shevchenko.join();
        kovalenko.join();

        System.out.println("\nУсі гості виселилися. Готель закривається.");
    }
}