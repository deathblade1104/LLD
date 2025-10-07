package caseStudies.RideHailingSystem;

import caseStudies.RideHailingSystem.Cars.CarType;
import caseStudies.RideHailingSystem.Cars.SimpleCarFactory;
import caseStudies.RideHailingSystem.MatchingStrategy.NearestDriverStrategy;
import caseStudies.RideHailingSystem.Trip.Trip;
import caseStudies.RideHailingSystem.Users.Driver;
import caseStudies.RideHailingSystem.Users.Rider;
import caseStudies.RideHailingSystem.Utils.Location;

public class RideHailingSystemMain {
  public static void main(String[] args) {
    // Setup drivers
    Driver d1 = new Driver("9991112222", "Ramesh",
        SimpleCarFactory.createCar(CarType.MINI, "KA01AB1234", "Alto"),
        new Location(12.97, 77.59), 4.6);

    Driver d2 = new Driver("9991113333", "Suresh",
        SimpleCarFactory.createCar(CarType.SEDAN, "KA02CD5678", "City"),
        new Location(12.98, 77.60), 4.8);

    Driver d3 = new Driver("9991114444", "Mahesh",
        SimpleCarFactory.createCar(CarType.SUV, "KA03EF9012", "XUV"),
        new Location(12.95, 77.61), 4.5);

    Rider r1 = new Rider("8887776666", "Shahbaz");

    // Register
    RideHailingManagerService mgr = RideHailingManagerService.getInstance();
    mgr.registerDriver(d1);
    mgr.registerDriver(d2);
    mgr.registerDriver(d3);
    mgr.registerRider(r1);

    // Choose matching strategy
    mgr.setMatchingStrategy(new NearestDriverStrategy());
    // mgr.setMatchingStrategy(new HighestRatedDriverStrategy());

    // Request a ride
    Location pickup = new Location(12.965, 77.600);
    Location drop = new Location(12.990, 77.620);

    Trip trip = mgr.requestRide(r1, pickup, drop, CarType.SEDAN, true);

    System.out.println("Final Trip Status: " + trip.getStatus());
    System.out.println("Assigned Driver: " + (trip.getDriver() != null ? trip.getDriver().getName() : "None"));
    System.out.println("Total Fare: " + (trip.getBill() != null ? trip.getBill().total() : 0.0));
  }
}
