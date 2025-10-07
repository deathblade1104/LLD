package caseStudies.RideHailingSystem.Trip;

import java.util.Date;

import caseStudies.RideHailingSystem.Bill.Bill;
import caseStudies.RideHailingSystem.Cars.CarType;
import caseStudies.RideHailingSystem.Trip.Observers.TripEventPublisher;
import caseStudies.RideHailingSystem.Trip.Observers.TripObserver;
import caseStudies.RideHailingSystem.Trip.TripStatus.RequestedState;
import caseStudies.RideHailingSystem.Trip.TripStatus.TripState;
import caseStudies.RideHailingSystem.Trip.TripStatus.TripStatus;
import caseStudies.RideHailingSystem.Users.Driver;
import caseStudies.RideHailingSystem.Users.Rider;
import caseStudies.RideHailingSystem.Utils.Location;

public class Trip {
  private final Rider rider;
  private Driver driver; // assigned later
  private final Location from;
  private final Location to;
  private final CarType requestedCarType;

  private TripState state;
  private final TripEventPublisher publisher = new TripEventPublisher();

  private Date createdAt = new Date();
  private Date driverAssignedAt;
  private Date startedAt;
  private Date endedAt;
  private double distanceKm;
  private Bill bill;

  public Trip(Rider rider, Location from, Location to, CarType requested) {
    this.rider = rider;
    this.from = from;
    this.to = to;
    this.requestedCarType = requested;
    this.state = new RequestedState();
  }

  // Observer hooks
  public void addObserver(TripObserver obs) {
    publisher.subscribe(obs);
  }

  public void publish(String msg) {
    publisher.publish(this, msg);
  }

  public void setState(TripState newState) {
    this.state = newState;
    publish("Trip state → " + newState.getStatus());
  }

  // Transition helpers
  public void assignDriver(Driver driver) {
    this.driver = driver;
    this.driverAssignedAt = new Date();
    publish("Driver assigned: " + driver.getName() + " (" + driver.getCar().getCarType() + ")");
  }

  public void markStarted() {
    this.startedAt = new Date();
  }

  public void markEnded() {
    this.endedAt = new Date();
  }

  // Accessors
  public TripState getState() {
    return state;
  }

  public TripStatus getStatus() {
    return state.getStatus();
  }

  public Rider getRider() {
    return rider;
  }

  public Driver getDriver() {
    return driver;
  }

  public Location getFrom() {
    return from;
  }

  public Location getTo() {
    return to;
  }

  public CarType getRequestedCarType() {
    return requestedCarType;
  }

  public double getDistanceKm() {
    return distanceKm;
  }

  public void setDistanceKm(double d) {
    this.distanceKm = d;
  }

  public Bill getBill() {
    return bill;
  }

  public void setBill(Bill bill) {
    this.bill = bill;
  }

  // Commands
  public void next() {
    state.next(this);
  }

  public void cancel() {
    state.cancel(this);
  }
}
