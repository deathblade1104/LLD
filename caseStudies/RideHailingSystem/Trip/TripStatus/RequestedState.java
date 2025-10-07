package caseStudies.RideHailingSystem.Trip.TripStatus;

import caseStudies.RideHailingSystem.Trip.Trip;


public class RequestedState implements TripState {
  @Override
  public void next(Trip trip) {
    trip.setState(new DriverEnRouteState());
  }

  @Override
  public void cancel(Trip trip) {
    trip.setState(new CancelledState());
  }

  @Override
  public TripStatus getStatus() {
    return TripStatus.REQUESTED;
  }
}