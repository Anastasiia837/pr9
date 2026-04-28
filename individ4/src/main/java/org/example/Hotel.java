package org.example;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.LinkedList;

public class Hotel {
    private final int capacity; // Місткість готелю
    private final LinkedList<Booking> guests = new LinkedList<>(); // Список поточних гостей
    private static final DateTimeFormatter TIME_FORMAT =
            DateTimeFormatter.ofPattern("HH:mm:ss.SSS");

    public Hotel(int capacity) {
        this.capacity = capacity;
    }

    // Метод для заселення гостя
    public synchronized boolean checkIn(Booking booking) {
        if (guests.size() < capacity) {
            guests.add(booking);
            printLog("заселено", booking);
            return true;
        }
        printLog("немає місця", booking);
        return false;
    }

    // Метод для виселення гостя
    public synchronized void checkOut(Booking booking) {
        guests.remove(booking);
        printLog("виселився", booking);
        notifyAll(); // Повідомляємо потоки, що звільнилося місце
    }

    // Пошук гостя за прізвищем
    public synchronized Booking findBySurname(String surname) {
        return guests.stream()
                .filter(g -> g.getSurname().equalsIgnoreCase(surname))
                .findFirst()
                .orElse(null);
    }

    // Допоміжний метод для виводу логів у консоль
    private void printLog(String action, Booking booking) {
        int free = capacity - guests.size();
        System.out.printf("[%s] %-20s | %s | вільних місць: %d%n",
                LocalTime.now().format(TIME_FORMAT),
                action,
                booking,
                free);
    }
}

