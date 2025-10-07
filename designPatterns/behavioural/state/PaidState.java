package designPatterns.behavioural.state;

public class PaidState implements OrderState {

  @Override
  public void next(Order order) {
    System.out.println("Order shipped.");
    order.setState(new ShippedState());
  }

  @Override
  public void cancel(Order order) {
    System.out.println("Order cannot be canceled after payment.");
  }

  @Override
  public String getStatus() {
    return "PAID";
  }
}
