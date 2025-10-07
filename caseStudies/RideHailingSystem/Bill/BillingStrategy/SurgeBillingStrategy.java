package caseStudies.RideHailingSystem.Bill.BillingStrategy;

import caseStudies.RideHailingSystem.Bill.Bill;
import caseStudies.RideHailingSystem.Cars.CarType;
import caseStudies.RideHailingSystem.Trip.Trip;

public class SurgeBillingStrategy implements BillingStrategy {
  private final double surgeMultiplier;

  public SurgeBillingStrategy(double surgeMultiplier) {
    this.surgeMultiplier = surgeMultiplier;
  }

  @Override
  public Bill generateBill(Trip trip) {
    CarType type = trip.getDriver().getCar().getCarType();
    double km = trip.getDistanceKm();
    return new Bill.Builder()
        .baseFare(type.baseFare())
        .distanceKm(km)
        .perKm(type.perKm())
        .surge(surgeMultiplier)
        .taxes(0.18 * (type.baseFare() + km * type.perKm() * surgeMultiplier))
        .build();
  }
}
