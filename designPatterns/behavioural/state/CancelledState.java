package designPatterns.behavioural.state;

public class CancelledState implements OrderState {
  @Override
  public void next(Order order) {
    System.out.println("Order canceled. Cannot proceed further.");
  }

  @Override
  public void cancel(Order order) {
    System.out.println("Order already canceled.");
  }

  @Override
  public String getStatus() {
    return "CANCELED";
  }
}
