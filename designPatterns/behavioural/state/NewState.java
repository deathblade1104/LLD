package designPatterns.behavioural.state;

public class NewState implements OrderState {

  @Override
  public void next(Order order) {
    System.out.println("Order paid successfully.");
    order.setState(new PaidState());
  }

  @Override
  public void cancel(Order order) {
    System.out.println("Order canceled.");
    order.setState(new CancelledState());
  }

  @Override
  public String getStatus() {
    return "NEW";
  }
}
