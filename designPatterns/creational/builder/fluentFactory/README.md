# Fluent Builder + Factory (Pizza)

Fluent builders (`VegPizzaBuilder`, `NonVegPizzaBuilder`) are selected using `PizzaBuilderFactory`.

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

  PizzaBuilder <|.. VegPizzaBuilder
  PizzaBuilder <|.. NonVegPizzaBuilder
  class PizzaBuilderFactory {
    +getBuilder(String): PizzaBuilder
  }
  PizzaBuilderFactory ..> PizzaBuilder : creates
  VegPizzaBuilder --> Pizza : builds
  NonVegPizzaBuilder --> Pizza : builds
```

## Entities
- `Pizza`: Immutable product
- `PizzaBuilder` (interface): Fluent contract
- `VegPizzaBuilder`: Veg constraints
- `NonVegPizzaBuilder`: Non-veg options
- `PizzaBuilderFactory`: Selects builder type

## Run
```bash
javac -d . designPatterns/creational/builder/fluentFactory/*.java
java -cp . designPatterns.creational.builder.fluentFactory.Main
```


