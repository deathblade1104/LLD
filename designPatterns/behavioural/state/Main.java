package designPatterns.behavioural.state;

public class Main {
  public static void main(String[] args) {
    Order order = new Order();

    System.out.println(order.getStatus()); // NEW
    order.next(); // Paying
    System.out.println(order.getStatus()); // PAID
    order.next(); // Shipping
    System.out.println(order.getStatus()); // SHIPPED
    order.next(); // Delivering
    System.out.println(order.getStatus()); // DELIVERED

    order.cancel(); // Cannot cancel
  }
}
