package designPatterns.creational.builder.fluentFactory;

import java.util.List;

public class Pizza {
  private final PizzaSize size;
  private final PizzaBase base;
  private final boolean cheese;
  private final boolean pepperoni;
  private final boolean mushroom;
  private final List<String> extraToppings;

  Pizza(PizzaSize size, PizzaBase base, boolean cheese, boolean pepperoni, boolean mushroom, List<String> toppings) {
    this.size = size;
    this.base = base;
    this.cheese = cheese;
    this.pepperoni = pepperoni;
    this.mushroom = mushroom;
    this.extraToppings = toppings;
  }

  @Override
  public String toString() {
    return "Pizza{" +
        "size=" + size +
        ", base=" + base +
        ", cheese=" + cheese +
        ", pepperoni=" + pepperoni +
        ", mushroom=" + mushroom +
        ", extraToppings=" + extraToppings +
        '}';
  }
}
