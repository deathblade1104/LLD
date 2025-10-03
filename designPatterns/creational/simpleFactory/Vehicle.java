package designPatterns.creational.simpleFactory;

public abstract class Vehicle {
  protected VehicleType type;

  public VehicleType getType() {
    return this.type;
  }
}