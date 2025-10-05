package caseStudies.ParkingLot.Vehicles;

public enum VehicleType {
  CAR(2),
  BIKE(1),
  TRUCK(4);

  private final int spaceRequired;

  VehicleType(int spaceRequired) {
    this.spaceRequired = spaceRequired;
  }

  public int getSpaceRequired() {
    return spaceRequired;
  }
}
