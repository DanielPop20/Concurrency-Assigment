package org.example;

import java.util.Random;

import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class FestivalSimulation {
    public static void main(String[] args) {
        FestivalGate gate = new FestivalGate();

        // crearea thread ului pt statistici
        FestivalStatisticsThread statisticsThread = new FestivalStatisticsThread(gate);
        statisticsThread.start();

        // utilizare ExecutorService pentru a gestiona thread urile participanților
        ExecutorService executor = Executors.newFixedThreadPool(10);

        // crearea thread urilor pentru participanți
        for (int i = 0; i < 100; i++) {
            TicketType ticketType = FestivalAttendeeThread.getRandomTicketType();
            FestivalAttendeeThread attendee = new FestivalAttendeeThread(ticketType, gate);
            executor.submit(attendee);

            try {
                // pauza mica intre crearea thread urilor pentru a simula sosirea participanților la poarta
                Thread.sleep(new Random().nextInt(100));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        // oprirea ExecutorService dupa ce toate thread urile participanților au fost create
        executor.shutdown();
        try {
            // Așteapta pana cand toate thread urile participanților au terminat execuția
            executor.awaitTermination(1, TimeUnit.MINUTES);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // oprirea thread ului de statistici dupa 30sec
        try {
            Thread.sleep(30000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        statisticsThread.stopRunning();
    }
}


