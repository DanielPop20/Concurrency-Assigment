package org.example;

import java.util.Random;

public class FestivalAttendeeThread extends Thread {
    private TicketType ticketType;
    private FestivalGate gate;

    public FestivalAttendeeThread(TicketType ticketType, FestivalGate gate) {
        this.ticketType = ticketType;
        this.gate = gate;
    }

    @Override
    public void run() {
        gate.addTicket(ticketType);
        try {
            // sleep pt a simula timpul necesar pt validarea biletului
            Thread.sleep(new Random().nextInt(500));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static TicketType getRandomTicketType() {
        TicketType[] types = TicketType.values();
        return types[new Random().nextInt(types.length)];
    }
}
