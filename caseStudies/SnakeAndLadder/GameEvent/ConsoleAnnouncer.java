package caseStudies.SnakeAndLadder.GameEvent;

public class ConsoleAnnouncer implements GameEventObserver {
  @Override
  public void onEvent(String message) {
    System.out.println(message);
  }
}
