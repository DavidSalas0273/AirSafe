package com.airsafe.boarding.template;

import java.util.ArrayList;
import java.util.List;

public abstract class BoardingProcess {

    public final List<String> performBoarding(String flightCode) {
        List<String> steps = new ArrayList<>();
        steps.add("Open gate for flight " + flightCode);
        steps.add(documentCheck());
        steps.add(securityScreening());
        steps.addAll(optionalSteps());
        steps.add("Final call and door closure");
        return steps;
    }

    protected abstract String documentCheck();

    protected abstract String securityScreening();

    protected List<String> optionalSteps() {
        return List.of();
    }
}
