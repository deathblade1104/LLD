package caseStudies.RideHailingSystem;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import caseStudies.RideHailingSystem.Bill.Bill;
import caseStudies.RideHailingSystem.Bill.BillingStrategy.BillingStrategy;
import caseStudies.RideHailingSystem.Bill.BillingStrategy.BillingStrategyFactory;
import caseStudies.RideHailingSystem.Cars.CarType;
import caseStudies.RideHailingSystem.MatchingStrategy.NearestDriverStrategy;
import caseStudies.RideHailingSystem.MatchingStrategy.RideMatchingStrategy;
import caseStudies.RideHailingSystem.Trip.Trip;
import caseStudies.RideHailingSystem.Trip.Observers.DriverNotifier;
import caseStudies.RideHailingSystem.Trip.Observers.RiderNotifier;
import caseStudies.RideHailingSystem.Trip.TripStatus.CancelledState;
import caseStudies.RideHailingSystem.Users.Driver;
import caseStudies.RideHailingSystem.Users.Rider;
import caseStudies.RideHailingSystem.Utils.Location;

public class RideHailingManagerService {
  private static RideHailingManagerService instance;

  public static RideHailingManagerService getInstance() {
    if (instance == null)
      instance = new RideHailingManagerService();
    return instance;
  }

  private RideHailingManagerService() {
  }

  private final List<Rider> riders = new ArrayList<>();
  private final List<Driver> drivers = new ArrayList<>();
  private RideMatchingStrategy matchingStrategy = new NearestDriverStrategy();

  public void registerRider(Rider r) {
    riders.add(r);
  }

  public void registerDriver(Driver d) {
    drivers.add(d);
  }

  public void setMatchingStrategy(RideMatchingStrategy s) {
    this.matchingStrategy = s;
  }

  public Trip requestRide(Rider rider, Location from, Location to, CarType requestedType, boolean surgeOn) {
    Trip trip = new Trip(rider, from, to, requestedType);
    trip.addObserver(new RiderNotifier());
    trip.addObserver(new DriverNotifier());

    // Match driver
    Driver chosen = matchingStrategy.match(rider, from, requestedType, drivers);

    if (chosen == null) {
      trip.publish("No drivers available for " + requestedType);
      trip.setState(new CancelledState());
      return trip;
    }

    // Assign and simulate lifecycle
    trip.assignDriver(chosen);
    chosen.setAvailable(false);

    trip.next(); // REQUESTED -> DRIVER_EN_ROUTE
    simulateDriverArrival(trip);

    trip.next(); // DRIVER_EN_ROUTE -> STARTED
    simulateRide(trip);

    trip.next(); // STARTED -> COMPLETED

    // Billing
    BillingStrategy billing = BillingStrategyFactory.select(surgeOn);
    Bill bill = billing.generateBill(trip);
    trip.setBill(bill);
    trip.publish("Bill generated: " + bill);

    // Release driver
    chosen.setAvailable(true);
    chosen.setLocation(to);
    return trip;
  }

  private void simulateDriverArrival(Trip trip) {
    double dist = Math.max(0.3, Location.distanceKm(trip.getFrom(), trip.getDriver().getLocation()));
    trip.publish("Driver en route, distance ~" + String.format("%.2f", dist) + " km");
  }

  private void simulateRide(Trip trip) {
    double km = Math.max(1.2, Location.distanceKm(trip.getFrom(), trip.getTo()));
    trip.setDistanceKm(km);
    trip.publish("Ride started. Estimated distance: " + String.format("%.2f", km) + " km");
    trip.setDistanceKm(km * (0.95 + new Random().nextDouble() * 0.2));
    trip.publish("Ride completed. Actual distance: " + String.format("%.2f", trip.getDistanceKm()) + " km");
  }
}