package com.airsafe.boarding.template;

import java.util.List;

public class DomesticBoardingProcess extends BoardingProcess {
    @Override
    protected String documentCheck() {
        return "Verify government ID";
    }

    @Override
    protected String securityScreening() {
        return "Standard metal detector screening";
    }

    @Override
    protected List<String> optionalSteps() {
        return List.of("Random carry-on inspection batch");
    }
}
