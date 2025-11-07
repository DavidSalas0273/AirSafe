package com.airsafe.ticket.decorator;

public class ExtraLuggageDecorator extends TicketDecorator {

    public ExtraLuggageDecorator(Ticket delegate) {
        super(delegate);
    }

    @Override
    public double cost() {
        return delegate().cost() + 40;
    }

    @Override
    public String description() {
        return delegate().description() + " + Extra Luggage";
    }
}
