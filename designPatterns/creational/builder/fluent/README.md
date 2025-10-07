# Fluent Builder (Pizza)

Simple fluent builder demonstrating method chaining without a factory.

## UML
```mermaid
classDiagram
  class Pizza {
    -size: PizzaSize
    -base: PizzaBase
    -cheese: boolean
    -pepperoni: boolean
    -mushroom: boolean
  }

  class PizzaBuilder {
    <<interface>>
    +size(PizzaSize): PizzaBuilder
    +base(PizzaBase): PizzaBuilder
    +cheese(): PizzaBuilder
    +pepperoni(): PizzaBuilder
    +mushroom(): PizzaBuilder
    +addTopping(String): PizzaBuilder
    +build(): Pizza
  }

  PizzaBuilder <|.. SimplePizzaBuilder
  SimplePizzaBuilder --> Pizza : builds
```

## Entities
- `Pizza`: Immutable product with size, base, and toppings
- `PizzaBuilder` (interface): Fluent contract
- `SimplePizzaBuilder`: Concrete fluent builder

## Run
```bash
javac -d . designPatterns/creational/builder/fluent/*.java
java -cp . designPatterns.creational.builder.fluent.Main
```


