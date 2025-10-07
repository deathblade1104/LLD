package caseStudies.RideHailingSystem.Trip.Observers;

import java.util.ArrayList;
import java.util.List;

import caseStudies.RideHailingSystem.Trip.Trip;

public class TripEventPublisher {
  private final List<TripObserver> observers = new ArrayList<>();

  public void subscribe(TripObserver obs) {
    observers.add(obs);
  }

  public void unsubscribe(TripObserver obs) {
    observers.remove(obs);
  }

  public void publish(Trip trip, String message) {
    for (TripObserver obs : observers)
      obs.update(trip, message);
  }
}