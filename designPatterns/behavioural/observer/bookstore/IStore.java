package designPatterns.behavioural.observer.bookstore;

public interface IStore {
  void addCustomer(ICustomer c);

  void removeCustomer(ICustomer c);

  void notifyCustomers();
}
