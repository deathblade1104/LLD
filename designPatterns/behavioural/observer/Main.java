package designPatterns.behavioural.observer;

public class Main {
  public static void main(String[] args) {
    BookStore store = new BookStore();

    // Create email notifiers (observers) for two customers
    ICustomer customer1 = new BookCustomer(store);
    ICustomer customer2 = new BookCustomer(store);

    System.out.println("Setting stock to 0.");
    store.updateQuantity(0);

    System.out.println("\nSetting stock to 5.");
    store.updateQuantity(5);

    store.removeCustomer(customer1);
  
    System.out.println("\nSetting stock to 2.");
    store.updateQuantity(2);
  }
}
