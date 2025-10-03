abstract class Vehicle {
  protected VehicleType type;

  public VehicleType getType() {
    return this.type;
  }
}

class Car extends Vehicle {
  public Car() {
    this.type = VehicleType.Car;
  }

}

class Bike extends Vehicle {
  public Bike() {
    this.type = VehicleType.Bike;
  }
}

class Truck extends Vehicle {
  public Truck() {
    this.type = VehicleType.Truck;
  }
}

class VehicleCreator {

  public Vehicle createVehicle(VehicleType type) {
    if (type.equals(VehicleType.Car))
      return new Car();
    else if (type.equals(VehicleType.Bike))
      return new Bike();
    else
      return new Truck();
  }
}

public class Main {
  public static void main(String[] args) {
    VehicleCreator creator = new VehicleCreator();

    Vehicle car = creator.createVehicle(VehicleType.Car);
    Vehicle bike = creator.createVehicle(VehicleType.Bike);

    System.out.println(car.getType());
    System.out.println(bike.getType());
  }
}