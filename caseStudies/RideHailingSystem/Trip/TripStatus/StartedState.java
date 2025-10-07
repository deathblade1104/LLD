package caseStudies.RideHailingSystem.Trip.TripStatus;

import caseStudies.RideHailingSystem.Trip.Trip;

public class StartedState implements TripState {
  @Override
  public void next(Trip trip) {
    trip.markEnded();
    trip.setState(new CompletedState());
  }

  @Override
  public void cancel(Trip trip) {
    trip.setState(new CancelledState());
  }

  @Override
  public TripStatus getStatus() {
    return TripStatus.STARTED;
  }
}
