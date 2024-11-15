package com.example.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import javax.persistence.*;
import javax.validation.constraints.*;
import java.util.Date;
import java.util.List;
import java.util.Random;

@Entity
@Table(name = "reservations")
public class Reservation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(nullable = false)
    private String source;

    @NotNull
    @Column(nullable = false)
    private String destination;

    @NotNull
    @Column(nullable = false)
    private Date journeyDate;

    @NotNull
    @Column(nullable = false)
    private int numTickets;

    @OneToMany(mappedBy = "reservation", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference 
    private List<Passenger> passengers;

    @NotNull
    @Size(min = 10, max = 10)
    private String prn;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "train_id", nullable = false)
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private Train train;

    public Reservation() {
        this.prn = generatePrn(); // Auto-generate PRN when creating a Reservation
    }

    // PRN Generation
    private String generatePrn() {
        Random random = new Random();
        StringBuilder prnBuilder = new StringBuilder(10);
        for (int i = 0; i < 10; i++) {
            prnBuilder.append(random.nextInt(10)); 
        }
        return prnBuilder.toString();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public Date getJourneyDate() {
        return journeyDate;
    }

    public void setJourneyDate(Date journeyDate) {
        this.journeyDate = journeyDate;
    }

    public int getNumTickets() {
        return numTickets;
    }

    public void setNumTickets(int numTickets) {
        this.numTickets = numTickets;
    }

    public List<Passenger> getPassengers() {
        return passengers;
    }

    public void setPassengers(List<Passenger> passengers) {
        this.passengers = passengers;
    }

    public String getPrn() {
        return prn;
    }

    public void setPrn(String prn) {
        this.prn = prn;
    }

    public Train getTrain() {
        return train;
    }

    public void setTrain(Train train) {
        this.train = train;
    }

    @Override
    public String toString() {
        return "Reservation [id=" + id + ", source=" + source + ", destination=" + destination + ", journeyDate="
                + journeyDate + ", numTickets=" + numTickets + ", passengers=" + passengers + ", prn=" + prn
                + ", train=" + train + "]";
    }
}
