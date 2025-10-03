
package designPatterns.creational.factory;

abstract class Vehicle {
  protected VehicleType type;

  public VehicleType getType() {
    return type;
  }
}

class Car extends Vehicle {
  public Car() {
    this.type = VehicleType.CAR;
  }
}

class Bike extends Vehicle {
  public Bike() {
    this.type = VehicleType.BIKE;
  }
}

class Truck extends Vehicle {
  public Truck() {
    this.type = VehicleType.TRUCK;
  }
}

abstract class VehicleFactory {
  abstract Vehicle createVehicle();
}

class CarFactory extends VehicleFactory {
  @Override
  public Vehicle createVehicle() {
    return new Car();
  }
}

class BikeFactory extends VehicleFactory {
  @Override
  public Vehicle createVehicle() {
    return new Bike();
  }
}

class TruckFactory extends VehicleFactory {
  @Override
  public Vehicle createVehicle() {
    return new Truck();
  }
}

public class Main {
  public static void main(String[] args) {
    VehicleFactory carFactory = new CarFactory();
    VehicleFactory bikeFactory = new BikeFactory();
    VehicleFactory truckFactory = new TruckFactory();

    Vehicle car = carFactory.createVehicle();
    Vehicle bike = bikeFactory.createVehicle();
    Vehicle truck = truckFactory.createVehicle();

    System.out.println("Created vehicle: " + car.getType());
    System.out.println("Created vehicle: " + bike.getType());
    System.out.println("Created vehicle: " + truck.getType());
  }
}
