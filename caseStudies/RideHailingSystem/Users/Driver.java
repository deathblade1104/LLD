package caseStudies.RideHailingSystem.Users;

import caseStudies.RideHailingSystem.Cars.Car;
import caseStudies.RideHailingSystem.Utils.Location;

public class Driver extends User {
  private static int idCounter = 1;
  private final int id;
  private final Car car;
  private Location location;
  private double rating;
  private boolean available = true;

  public Driver(String phoneNumber, String name, Car car, Location location, double rating) {
    super(phoneNumber, name);
    this.id = idCounter++;
    this.car = car;
    this.location = location;
    this.rating = rating;
  }

  public int getId() {
    return id;
  }

  public Car getCar() {
    return car;
  }

  public Location getLocation() {
    return location;
  }

  public void setLocation(Location location) {
    this.location = location;
  }

  public double getRating() {
    return rating;
  }

  public void setRating(double rating) {
    this.rating = rating;
  }

  public boolean isAvailable() {
    return available;
  }

  public void setAvailable(boolean available) {
    this.available = available;
  }
}