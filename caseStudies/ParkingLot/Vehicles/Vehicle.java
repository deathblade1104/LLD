package caseStudies.ParkingLot.Vehicles;

public abstract class Vehicle {
  protected VehicleType type;
  protected int licenseNumber;

  public Vehicle(VehicleType type, int licenseNumber) {
    this.type = type;
    this.licenseNumber = licenseNumber;
  }

  public VehicleType getType() {
    return type;
  }

  public int getLicenseNumber() {
    return licenseNumber;
  }

  public int getSpace() {
    return this.type.getSpaceRequired();
  }
}
