package designPatterns.creational.builder.fluentFactory;

import java.util.ArrayList;
import java.util.List;

public class VegPizzaBuilder implements PizzaBuilder {
  private PizzaSize size;
  private PizzaBase base;
  private boolean cheese = false;
  private boolean pepperoni = false;
  private boolean mushroom = false;
  private List<String> toppings = new ArrayList<>();

  @Override
  public PizzaBuilder size(PizzaSize size) {
    this.size = size;
    return this;
  }

  @Override
  public PizzaBuilder base(PizzaBase base) {
    this.base = base;
    return this;
  }

  @Override
  public PizzaBuilder cheese() {
    this.cheese = true;
    return this;
  }

  @Override
  public PizzaBuilder pepperoni() {
    this.pepperoni = false;
    return this;
  }

  @Override
  public PizzaBuilder mushroom() {
    this.mushroom = true;
    return this;
  }

  @Override
  public PizzaBuilder addTopping(String topping) {
    this.toppings.add(topping);
    return this;
  }

  @Override
  public Pizza build() {
    return new Pizza(size, base, cheese, pepperoni, mushroom, toppings);
  }
}
