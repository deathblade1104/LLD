package caseStudies.ParkingLot.ParkingLotManager;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

import caseStudies.ParkingLot.Drivers.Driver;

public class Waitlist {
  private final List<WaitlistObserver> observers = new ArrayList<>();
  private final Deque<Driver> queue = new ArrayDeque<>();

  public void subscribe(WaitlistObserver observer) {
    observers.add(observer);
  }

  public void unsubscribe(WaitlistObserver observer) {
    observers.remove(observer);
  }

  public void enqueue(Driver driver) {
    queue.addLast(driver);
  }

  public Driver peek() {
    return queue.peekFirst();
  }

  public Driver pop() {
    return queue.pollFirst();
  }

  public boolean isEmpty() {
    return queue.isEmpty();
  }

  public void notifyAvailable(Driver driver) {
    for (WaitlistObserver obs : observers) {
      obs.onSpotAvailable(driver);
    }
  }
}
