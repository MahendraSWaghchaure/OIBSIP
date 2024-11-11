//package com.example.services;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//import com.example.entities.Train;
//import com.example.entities.Seat;
//import com.example.repositories.TrainRepository;
//import com.example.repositories.SeatRepository;
//import java.util.Date;
//import java.util.List;
//
//@Service
//public class TrainService {
//    @Autowired
//    private TrainRepository trainRepository;
//    
//    @Autowired
//    private SeatRepository seatRepository;
//    
//    public List<Train> searchTrains(String source, String destination, Date date) {
//        // Implement search logic
//        return trainRepository.findAll();
//    }
//    
//    public List<Seat> getAvailableSeats(String trainNumber, Date date) {
//        Train train = trainRepository.findByTrainNumber(trainNumber)
//            .orElseThrow(() -> new RuntimeException("Train not found"));
//        return seatRepository.findAvailableSeats(train.getId(), train.getTotalSeats());
//    }
//}


package com.example.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.entities.Train;
import com.example.entities.Seat;
import com.example.repositories.TrainRepository;
import com.example.repositories.SeatRepository;
import java.util.Date;
import java.util.List;

@Service
public class TrainService {
    @Autowired
    private TrainRepository trainRepository;
    
    @Autowired
    private SeatRepository seatRepository;
    
    public List<Train> searchTrains(String source, String destination, Date date) {
        // Implement search logic
        return trainRepository.findAll();
    }
    
    public List<Seat> getAvailableSeats(String trainNumber, Date date) {
        Train train = trainRepository.findByTrainNumber(trainNumber)
            .orElseThrow(() -> new RuntimeException("Train not found"));
        
        // Call findAvailableSeats with only trainId
        return seatRepository.findAvailableSeats(train.getId());
    }
}