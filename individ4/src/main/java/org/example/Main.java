package org.example;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Введіть місткість готелю: ");
        int capacity = scanner.nextInt();
        Hotel hotel = new Hotel(capacity);

        List<Booking> bookings = new ArrayList<>();

        System.out.println("\nМЕНЮ");
        System.out.println("1. Додати гостя");
        System.out.println("2. Почати заселення");
        System.out.println();

        while (true) {
            System.out.print("Оберіть дію: ");
            int choice = scanner.nextInt();

            if (choice == 1) {
                System.out.print("Ім'я гостя: ");
                String name = scanner.next();
                System.out.print("Час проживання (мс): ");
                long stayTime = scanner.nextLong();

                Booking booking = new Booking(name, stayTime, hotel);
                bookings.add(booking);
                System.out.println("Гіст додано\n");

            } else if (choice == 2) {
                if (bookings.isEmpty()) {
                    System.out.println("Спочатку додайте гостей!\n");
                    continue;
                }
                break;
            } else {
                System.out.println("Невірний вибір\n");
            }
        }

        System.out.println("\nГотель відкривається (місткість: " + capacity + ")\n");

        for (Booking booking : bookings) {
            booking.start();
            Thread.sleep(500);
        }

        System.out.print("\nШукати гостя (прізвище): ");
        String searchName = scanner.next();
        Booking found = hotel.findBySurname(searchName);
        System.out.println("Пошук «" + searchName + "»: " +
                (found != null ? "проживає" : "не знайдений"));

        for (Booking booking : bookings) {
            booking.join();
        }

        System.out.println("\nУсі гості виселилися. Готель забрали за долги");
        scanner.close();
    }
}