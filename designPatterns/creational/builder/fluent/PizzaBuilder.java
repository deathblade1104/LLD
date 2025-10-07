package designPatterns.creational.builder.fluent;

public interface PizzaBuilder {
  PizzaBuilder size(PizzaSize size);

  PizzaBuilder base(PizzaBase base);

  PizzaBuilder cheese();

  PizzaBuilder pepperoni();

  PizzaBuilder mushroom();

  PizzaBuilder addTopping(String topping);

  Pizza build();
}
