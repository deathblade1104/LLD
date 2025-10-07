package designPatterns.behavioural.state;

public class Order {
  private OrderState state;

  public Order() {
    this.state = new NewState();
  }

  public void setState(OrderState state) {
    this.state = state;
  }

  public void next() {
    state.next(this);
  }

  public void cancel() {
    state.cancel(this);
  }

  public String getStatus() {
    return state.getStatus();
  }
}
