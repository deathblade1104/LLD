package caseStudies.SnakeAndLadder.GameEvent;

public interface GameEventObserver {
  void onEvent(String message);
}