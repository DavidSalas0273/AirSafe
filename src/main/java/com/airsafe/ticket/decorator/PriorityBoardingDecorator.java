package com.airsafe.ticket.decorator;

public class PriorityBoardingDecorator extends TicketDecorator {

    public PriorityBoardingDecorator(Ticket delegate) {
        super(delegate);
    }

    @Override
    public double cost() {
        return delegate().cost() + 25;
    }

    @Override
    public String description() {
        return delegate().description() + " + Priority Boarding";
    }
}
