package designPatterns.behavioural.observer;

public interface IStore {
  void addCustomer(ICustomer c);

  void removeCustomer(ICustomer c);

  void notifyCustomers();
}
