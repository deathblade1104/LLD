package designPatterns.behavioural.observer.weather;

import java.util.ArrayList;
import java.util.List;

public class WeatherStation implements Subject {
  private int currTemp;
  private final List<Observer> observers;

  public WeatherStation() {
    observers = new ArrayList<>();
    currTemp = -1;
  }

  @Override
  public void addObserver(Observer observer) {
    observers.add(observer);
  }

  @Override
  public void removeObserver(Observer observer) {
    observers.remove(observer);
  }

  @Override
  public void notifyObservers(String message) {
    for (Observer obs : observers) {
      obs.update(message);
    }
  }

  public void updateTemp(int newTemp) {
    if (currTemp != newTemp) {
      currTemp = newTemp;
      notifyObservers("\uD83C\uDF21\uFE0F Temperature changed to: " + newTemp + "°C");
    }
  }
}
