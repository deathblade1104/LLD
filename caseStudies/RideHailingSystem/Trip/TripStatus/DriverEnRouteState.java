package caseStudies.RideHailingSystem.Trip.TripStatus;

import caseStudies.RideHailingSystem.Trip.Trip;


public class DriverEnRouteState implements TripState {
  @Override
  public void next(Trip trip) {
    trip.markStarted();
    trip.setState(new StartedState());
  }

  @Override
  public void cancel(Trip trip) {
    trip.setState(new CancelledState());
  }

  @Override
  public TripStatus getStatus() {
    return TripStatus.DRIVER_EN_ROUTE;
  }
}