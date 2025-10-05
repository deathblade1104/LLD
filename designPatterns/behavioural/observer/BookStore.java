package designPatterns.behavioural.observer;

import java.util.ArrayList;
import java.util.List;

public class BookStore implements IStore {
  private List<ICustomer> customers = new ArrayList<ICustomer>();
  private int stockQuantity = 0;

  @Override
  public void addCustomer(ICustomer c) {
    customers.add(c);
  }

  @Override
  public void removeCustomer(ICustomer c) {
    customers.remove(c);
  }

  @Override
  public void notifyCustomers() {
    for (ICustomer customer : customers) {
      customer.update(stockQuantity);
    }
  }

  public void updateQuantity(int quantity) {
    this.stockQuantity = quantity;
    notifyCustomers();
  }
}
