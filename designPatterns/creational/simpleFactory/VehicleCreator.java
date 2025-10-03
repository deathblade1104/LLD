package designPatterns.creational.simpleFactory;

public class VehicleCreator {

  public Vehicle createVehicle(VehicleType type) {
    if (type.equals(VehicleType.CAR))
      return new Car();
    else if (type.equals(VehicleType.BIKE))
      return new Bike();
    else
      return new Truck();
  }
}