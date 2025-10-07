package designPatterns.creational.builder.fluent;

public class Main {
  public static void main(String[] args) {
    Pizza myPizza = new SimplePizzaBuilder()
        .size(PizzaSize.LARGE)
        .base(PizzaBase.CHEESE_BURST)
        .cheese()
        .mushroom()
        .addTopping("Olives")
        .build();

    System.out.println(myPizza);
  }
}
