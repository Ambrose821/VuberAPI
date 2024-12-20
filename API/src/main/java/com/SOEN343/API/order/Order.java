package com.SOEN343.API.order;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import com.SOEN343.API.Coordinates.Coordinate;
import com.SOEN343.API.parcel.Parcel;
import com.SOEN343.API.user.User;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.*;

// database entity for order class
@Entity
@Table(name = "orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @JsonBackReference
    @ManyToOne
    @OnDelete(action = OnDeleteAction.CASCADE)
    private User user;

    @JsonBackReference
    @ManyToOne(optional = true)
    @JoinColumn(name = "assigned_user_id", nullable = true)
    private User assignedUser;

    @JsonManagedReference
    @OneToMany(cascade = CascadeType.ALL)
    private Set<Parcel> parcels;

    @Column
    private String status;

    @Column
    private String chargeId;

    @Column(nullable = true)
    private Double total;

    // location (from and to)

    @Column(name = "origin")
    private String origin;

    @Column(name = "destination")
    private String destination;

    @Column
    private String pickupInstructions;

    @Column
    private String dropoffInstructions;

    // private List<Coordinate> prevCoordinates = new ArrayList<>();

    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "lat", column = @Column(name = "origin_x_coord")),
            @AttributeOverride(name = "lng", column = @Column(name = "origin_y_coord"))
    })
    private Coordinate originCoords;

    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "lat", column = @Column(name = "destination_x_coord")),
            @AttributeOverride(name = "lng", column = @Column(name = "destination_y_coord"))
    })
    private Coordinate destinationCoords;

    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "lat", column = @Column(name = "current_x_coord")),
            @AttributeOverride(name = "lng", column = @Column(name = "current_y_coord"))
    })
    private Coordinate currentCoordinates;

    @ElementCollection
    private List<Coordinate> prevCoordinates = new ArrayList<>();

    public Order() {

    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getChargeId() {
        return chargeId;
    }

    public void setChargeId(String chargeId) {
        this.chargeId = chargeId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public String getOrigin() {
        return origin;
    }

    public void setOrigin(String origin) {
        this.origin = origin;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public Set<Parcel> getParcels() {
        return parcels;
    }

    public void setParcels(Set<Parcel> parcels) {
        this.parcels = parcels;
    }

    public Coordinate getOriginCoords() {
        return originCoords;
    }

    public void setOriginCoords(Coordinate originCoords) {
        this.originCoords = originCoords;
    }

    public Coordinate getDestinationCoords() {
        return destinationCoords;
    }

    public void setDestinationCoords(Coordinate destinationCoords) {
        this.destinationCoords = destinationCoords;
    }

    public Coordinate getCurrentCoordinates() {
        return currentCoordinates;
    }

    public void setCurrentCoordinates(Coordinate currentCoordinates) {
        this.currentCoordinates = currentCoordinates;
    }

    public List<Coordinate> getPrevCoordinates() {
        return prevCoordinates;
    }

    public void setPrevCoordinates(List<Coordinate> prevCoordinates) {
        this.prevCoordinates = prevCoordinates;
    }

    public String getPickupInstructions() {
        return pickupInstructions;
    }

    public void setPickupInstructions(String pickupInstructions) {
        this.pickupInstructions = pickupInstructions;
    }

    public String getDropoffInstructions() {
        return dropoffInstructions;
    }

    public void setDropoffInstructions(String dropoffInstructions) {
        this.dropoffInstructions = dropoffInstructions;
    }

    public User getAssignedUser() {
        return assignedUser;
    }

    public void setAssignedUser(User assignedUser) {
        this.assignedUser = assignedUser;
    }

}
