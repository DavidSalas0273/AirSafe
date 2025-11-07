package com.airsafe.ticket.decorator;

public class BasicTicket implements Ticket {

    private final String flightCode;
    private final double basePrice;

    public BasicTicket(String flightCode, double basePrice) {
        this.flightCode = flightCode;
        this.basePrice = basePrice;
    }

    @Override
    public double cost() {
        return basePrice;
    }

    @Override
    public String description() {
        return "Ticket for " + flightCode;
    }
}
