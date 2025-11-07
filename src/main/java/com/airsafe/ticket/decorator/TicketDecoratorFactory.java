package com.airsafe.ticket.decorator;

import java.util.List;

import org.springframework.stereotype.Component;

@Component
public class TicketDecoratorFactory {

    public Ticket applyExtras(Ticket baseTicket, List<String> extras) {
        Ticket ticket = baseTicket;
        for (String extra : extras) {
            ticket = switch (extra.toUpperCase()) {
                case "PRIORITY" -> new PriorityBoardingDecorator(ticket);
                case "WIFI" -> new WifiDecorator(ticket);
                case "LUGGAGE" -> new ExtraLuggageDecorator(ticket);
                default -> ticket;
            };
        }
        return ticket;
    }
}
