package com.airsafe.service.ticket;

import org.springframework.stereotype.Service;

import com.airsafe.ticket.decorator.BasicTicket;
import com.airsafe.ticket.decorator.Ticket;

@Service
public class TicketService {

    public Ticket issue(String flightCode, double basePrice) {
        return new BasicTicket(flightCode, basePrice);
    }
}
