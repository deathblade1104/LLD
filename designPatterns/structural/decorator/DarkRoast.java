package designPatterns.structural.decorator;

public class DarkRoast extends Beverage {
  @Override
  public double cost() {
    return 3.45;
  }

  @Override
  public String description() {
    return "Dark Roast";
  }
}
