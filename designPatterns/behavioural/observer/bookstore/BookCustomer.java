package designPatterns.behavioural.observer.bookstore;

public class BookCustomer implements ICustomer {

  private int observedStockQuantity;

  public BookCustomer(IStore store) {
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

  public int getObservedStockQuantity() {
    return observedStockQuantity;
  }
}
