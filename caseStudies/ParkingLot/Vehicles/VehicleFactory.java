package caseStudies.ParkingLot.Vehicles;

public class VehicleFactory {

  public static Vehicle createVehicle(VehicleType type, int licenseNumber) {

    switch (type) {
      case CAR:
        return new Car(licenseNumber);
      case BIKE:
        return new Bike(licenseNumber);
      case TRUCK:
        return new Truck(licenseNumber);
      default:
        throw new IllegalArgumentException("Unknown vehicle type: " + type);
    }
  }
}
