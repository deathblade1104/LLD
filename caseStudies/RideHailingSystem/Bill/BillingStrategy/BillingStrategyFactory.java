package caseStudies.RideHailingSystem.Bill.BillingStrategy;

public class BillingStrategyFactory {
  public static BillingStrategy select(boolean surge) {
    if (surge)
      return new SurgeBillingStrategy(1.5);
    return new DistanceBillingStrategy();
  }
}