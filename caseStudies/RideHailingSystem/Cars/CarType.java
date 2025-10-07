package caseStudies.RideHailingSystem.Cars;

public enum CarType {
  MINI(8.0, 30.0),
  SEDAN(11.0, 40.0),
  SUV(14.0, 50.0);

  private final double perKm;
  private final double baseFare;

  CarType(double perKm, double baseFare) {
    this.perKm = perKm;
    this.baseFare = baseFare;
  }

  public double perKm() {
    return perKm;
  }

  public double baseFare() {
    return baseFare;
  }
}
