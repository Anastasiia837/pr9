package org.example;

public class Booking extends Thread {
    private final String surname;
    private final long stayMillis;
    private final Hotel hotel;
    private BookingStatus status = BookingStatus.WAITING;

    public Booking(String surname, long stayMillis, Hotel hotel) {
        super("Потік-" + surname);
        this.surname = surname;
        this.stayMillis = stayMillis;
        this.hotel = hotel;
    }

    public String getSurname() {
        return surname;
    }

    public BookingStatus getStatus() {
        return status;
    }

    @Override
    public void run() {
        synchronized (hotel) {
            while (!hotel.checkIn(this)) {
                status = BookingStatus.WAITING;
                System.out.println("  ⏳ " + surname + " чекає на вільне місце...");
                try {
                    hotel.wait();
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                    return;
                }
            }
        }

        status = BookingStatus.STAYING;
        try {
            Thread.sleep(stayMillis);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        status = BookingStatus.CHECKED_OUT;
        hotel.checkOut(this);
    }

    @Override
    public String toString() {
        return String.format("%-12s (%4d мс)", surname, stayMillis);
    }
}


