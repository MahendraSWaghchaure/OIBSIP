package com.example.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.example.services.TrainService;
import java.util.Date;

@RestController
@RequestMapping("/api/trains")
@CrossOrigin(origins = "*")
public class TrainController {
    @Autowired
    private TrainService trainService;
    
    @GetMapping
    public ResponseEntity<?> getAllTrains(
        @RequestParam(required = false) String source,
        @RequestParam(required = false) String destination,
        @RequestParam(required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") Date date
    ) {
        return ResponseEntity.ok(trainService.searchTrains(source, destination, date));
    }
    
    @GetMapping("/{trainNumber}/seats")
    public ResponseEntity<?> getAvailableSeats(
        @PathVariable String trainNumber,
        @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") Date date
    ) {
        return ResponseEntity.ok(trainService.getAvailableSeats(trainNumber, date));
    }
}
