package caseStudies.ParkingLot.Drivers;

import caseStudies.ParkingLot.Vehicles.Vehicle;

public class Driver {
  private final String name;
  private final String phoneNumber;
  private final Vehicle vehicle; 

  public Driver(String name, String phoneNumber, Vehicle vehicle) {
    this.name = name;
    this.phoneNumber = phoneNumber;
    this.vehicle = vehicle;
  }

  public String getName() {
    return name;
  }

  public String getPhoneNumber() {
    return phoneNumber;
  }

  public Vehicle getVehicle() {
    return vehicle;
  }
}
