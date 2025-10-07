package caseStudies.RideHailingSystem.Cars;

public class SimpleCarFactory {
  public static Car createCar(CarType type, String number, String model) {
    return switch (type) {
      case MINI -> new MiniCar(number, model);
      case SEDAN -> new SedanCar(number, model);
      case SUV -> new SUVCar(number, model);
    };
  }
}