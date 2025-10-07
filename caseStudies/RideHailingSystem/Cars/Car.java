package caseStudies.RideHailingSystem.Cars;

public abstract class Car {
  private static int idCounter = 1;
  protected final int id;
  protected final String carNumber;
  protected final String model;
  protected final CarType carType;

  protected Car(String carNumber, String model, CarType carType) {
    this.id = idCounter++;
    this.carNumber = carNumber;
    this.model = model;
    this.carType = carType;
  }

  public int getId() {
    return id;
  }

  public String getCarNumber() {
    return carNumber;
  }

  public String getModel() {
    return model;
  }

  public CarType getCarType() {
    return carType;
  }
}
