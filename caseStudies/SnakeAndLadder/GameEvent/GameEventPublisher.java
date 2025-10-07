package caseStudies.SnakeAndLadder.GameEvent;

import java.util.ArrayList;
import java.util.List;

public class GameEventPublisher {
  private final List<GameEventObserver> observers = new ArrayList<>();

  public void subscribe(GameEventObserver obs) {
    observers.add(obs);
  }

  public void unsubscribe(GameEventObserver obs) {
    observers.remove(obs);
  }

  public void publish(String message) {
    for (GameEventObserver obs : observers)
      obs.onEvent(message);
  }
}