package designPatterns.behavioural.observer.weather;

public class AlertSystem implements Observer {
  @Override
  public void update(String msg) {
    System.out.println("\uD83D\uDEA8 Alert System: " + msg);
  }
}
