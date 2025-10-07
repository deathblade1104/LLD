package caseStudies.RideHailingSystem.Bill.BillingStrategy;

import caseStudies.RideHailingSystem.Bill.Bill;
import caseStudies.RideHailingSystem.Trip.Trip;

public interface BillingStrategy {
  Bill generateBill(Trip trip);
}