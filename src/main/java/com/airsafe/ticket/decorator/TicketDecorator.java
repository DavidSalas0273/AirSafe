package com.airsafe.ticket.decorator;

public abstract class TicketDecorator implements Ticket {

    private final Ticket delegate;

    protected TicketDecorator(Ticket delegate) {
        this.delegate = delegate;
    }

    protected Ticket delegate() {
        return delegate;
    }

    @Override
    public double cost() {
        return delegate.cost();
    }

    @Override
    public String description() {
        return delegate.description();
    }
}
