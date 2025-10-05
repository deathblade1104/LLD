package designPatterns.behavioural.observer;

public class BookCustomer implements ICustomer {

  private int observedStockQuantity;
  private IStore store;

  public BookCustomer(IStore store) {
    this.store = store;
    store.addCustomer(this);
  }

  @Override
  public void update(int stockQuantity) {
    this.observedStockQuantity = stockQuantity;
    if (stockQuantity > 0) {
      System.out.println(
          "Hello, A book you are interested in is back in stock!");
    }
  }
}