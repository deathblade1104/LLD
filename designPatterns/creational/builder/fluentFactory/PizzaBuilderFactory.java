package designPatterns.creational.builder.fluentFactory;

public class PizzaBuilderFactory {
  public static PizzaBuilder getBuilder(String type) {
    return switch (type) {
      case "VEG" -> new VegPizzaBuilder();
      case "NON_VEG" -> new NonVegPizzaBuilder();
      default -> throw new IllegalArgumentException("Unknown pizza type");
    };
  }
}
