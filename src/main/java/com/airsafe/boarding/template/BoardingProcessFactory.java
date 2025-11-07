package com.airsafe.boarding.template;

import org.springframework.stereotype.Component;

@Component
public class BoardingProcessFactory {

    public BoardingProcess select(String flightType) {
        return switch (flightType.toUpperCase()) {
            case "INTERNATIONAL" -> new InternationalBoardingProcess();
            default -> new DomesticBoardingProcess();
        };
    }
}
