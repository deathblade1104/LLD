package designPatterns.creational.simpleFactory;

public class Main {
  public static void main(String[] args) {
    VehicleCreator creator = new VehicleCreator();

    Vehicle car = creator.createVehicle(VehicleType.CAR);
    Vehicle bike = creator.createVehicle(VehicleType.BIKE);

    System.out.println(car.getType());
    System.out.println(bike.getType());
  }
}