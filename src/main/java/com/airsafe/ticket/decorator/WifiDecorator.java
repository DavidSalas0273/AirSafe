package com.airsafe.ticket.decorator;

public class WifiDecorator extends TicketDecorator {

    public WifiDecorator(Ticket delegate) {
        super(delegate);
    }

    @Override
    public double cost() {
        return delegate().cost() + 15;
    }

    @Override
    public String description() {
        return delegate().description() + " + In-flight WiFi";
    }
}
