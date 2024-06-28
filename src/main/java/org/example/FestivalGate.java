package org.example;

import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

public class FestivalGate {
    private Queue<TicketType> ticketQueue = new ConcurrentLinkedQueue<>();

    public void addTicket(TicketType ticket) {
        ticketQueue.add(ticket);
    }

    public Queue<TicketType> getTickets() {
        return ticketQueue;
    }
}
