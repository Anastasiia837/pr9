package org.example;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.LinkedList;

public class Hotel {
    private final int capacity;
    private final LinkedList<Booking> guests = new LinkedList<>();
    private static final DateTimeFormatter TIME_FORMAT =
            DateTimeFormatter.ofPattern("HH:mm:ss.SSS");

    public Hotel(int capacity) {
        this.capacity = capacity;
    }

    public synchronized boolean checkIn(Booking booking) {
        if (guests.size() < capacity) {
            guests.add(booking);
            printLog("✔ ЗАСЕЛЕНО", booking);
            return true;
        }
        printLog("✘ НЕМАЄ МІСЦЬ", booking);
        return false;
    }

    public synchronized void checkOut(Booking booking) {
        guests.remove(booking);
        printLog("← ВИСЕЛЕНО", booking);
        notifyAll();
    }

    public synchronized Booking findBySurname(String surname) {
        return guests.stream()
                .filter(g -> g.getSurname().equalsIgnoreCase(surname))
                .findFirst()
                .orElse(null);
    }

    private void printLog(String action, Booking booking) {
        int free = capacity - guests.size();
        System.out.printf("[%s] %-20s | %s | вільних місць: %d%n",
                LocalTime.now().format(TIME_FORMAT),
                action,
                booking,
                free);
    }

}


