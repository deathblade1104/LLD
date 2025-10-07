package designPatterns.behavioural.observer.weather;

public class WebDashboard implements Observer {
  @Override
  public void update(String msg) {
    System.out.println("\uD83D\uDCBB Web Dashboard: " + msg);
  }
}
