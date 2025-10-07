package designPatterns.behavioural.observer.weather;

public class MobileDisplay implements Observer {
  @Override
  public void update(String msg) {
    System.out.println("\uD83D\uDCF1 Mobile Display: " + msg);
  }
}
