package com.airsafe.boarding.template;

import java.util.List;

public class InternationalBoardingProcess extends BoardingProcess {
    @Override
    protected String documentCheck() {
        return "Validate passport and visa";
    }

    @Override
    protected String securityScreening() {
        return "Enhanced security scan + interview";
    }

    @Override
    protected List<String> optionalSteps() {
        return List.of(
                "Check health certificates",
                "Coordinate customs paperwork"
        );
    }
}
