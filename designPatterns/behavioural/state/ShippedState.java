package designPatterns.behavioural.state;

public class ShippedState implements OrderState {

  @Override
  public void next(Order order) {
    System.out.println("Order delivered.");
    order.setState(new DeliveredState());
  }

  @Override
  public void cancel(Order order) {
    System.out.println("Cannot cancel, already shipped.");
  }

  @Override
  public String getStatus() {
    return "SHIPPED";
  }
}
