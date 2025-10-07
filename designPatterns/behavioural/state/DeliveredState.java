package designPatterns.behavioural.state;

public class DeliveredState implements OrderState {
  @Override
  public void next(Order order) {
    System.out.println("Order already delivered. No next state.");
  }

  @Override
  public void cancel(Order order) {
    System.out.println("Cannot cancel, order already delivered.");
  }

  @Override
  public String getStatus() {
    return "DELIVERED";
  }
}
