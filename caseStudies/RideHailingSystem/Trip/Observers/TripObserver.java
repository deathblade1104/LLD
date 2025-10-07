package caseStudies.RideHailingSystem.Trip.Observers;

import caseStudies.RideHailingSystem.Trip.Trip;

public interface TripObserver {
  void update(Trip trip, String eventMessage);
}