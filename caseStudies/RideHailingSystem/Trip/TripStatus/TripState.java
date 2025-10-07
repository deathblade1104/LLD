package caseStudies.RideHailingSystem.Trip.TripStatus;

import caseStudies.RideHailingSystem.Trip.Trip;

public interface TripState {
  void next(Trip trip);

  void cancel(Trip trip);

  TripStatus getStatus();
}