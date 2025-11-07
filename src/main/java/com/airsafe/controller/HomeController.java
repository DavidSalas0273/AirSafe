package com.airsafe.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {

    @GetMapping("/")
    public ResponseEntity<String> status() {
        return ResponseEntity.ok("AirSafe backend is running. Open the React dashboard at http://localhost:5173 to use the UI.");
    }
}
