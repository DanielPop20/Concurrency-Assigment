package org.example;

public class FestivalStatisticsThread extends Thread {
    private FestivalGate gate;
    private volatile boolean running = true;

    public FestivalStatisticsThread(FestivalGate gate) {
        this.gate = gate;
    }

    public void stopRunning() {
        this.running = false;
    }

    @Override
    public void run() {
        while (running) {
            try {
                Thread.sleep(5000); // asteapta 5 sec inainte de a genera statisticile
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            if (!gate.getTickets().isEmpty()) {
                generateStatistics();
            }
        }
        System.out.println("FestivalStatisticsThread has stopped.");
    }

    private void generateStatistics() {
        int full = 0, fullVip = 0, freePass = 0, oneDay = 0, oneDayVip = 0;

        for (TicketType ticket : gate.getTickets()) {
            switch (ticket) {
                case FULL -> full++;
                case FULL_VIP -> fullVip++;
                case FREE_PASS -> freePass++;
                case ONE_DAY -> oneDay++;
                case ONE_DAY_VIP -> oneDayVip++;
            }
        }

        System.out.println("Total people entered: " + gate.getTickets().size());
        System.out.println("Full tickets: " + full);
        System.out.println("Full VIP tickets: " + fullVip);
        System.out.println("Free passes: " + freePass);
        System.out.println("One-day tickets: " + oneDay);
        System.out.println("One-day VIP tickets: " + oneDayVip);
    }
}


