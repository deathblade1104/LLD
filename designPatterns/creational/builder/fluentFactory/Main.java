package designPatterns.creational.builder.fluentFactory;

public class Main {
  public static void main(String[] args) {
    PizzaBuilder vegBuilder = PizzaBuilderFactory.getBuilder("VEG")
        .size(PizzaSize.LARGE)
        .base(PizzaBase.CHEESE_BURST)
        .cheese()
        .mushroom()
        .addTopping("Olives");

    Pizza vegPizza = vegBuilder.build();

    PizzaBuilder nonVegBuilder = PizzaBuilderFactory.getBuilder("NON_VEG")
        .size(PizzaSize.MEDIUM)
        .base(PizzaBase.THIN_CRUST)
        .cheese()
        .pepperoni()
        .addTopping("Jalapeno");

    Pizza nonVegPizza = nonVegBuilder.build();

    System.out.println(vegPizza);
    System.out.println(nonVegPizza);
  }
}
