package designPatterns.structural.decorator;

public class EspressoDecorator extends BeverageDecorator {

  public EspressoDecorator(Beverage beverage) {
    super(beverage);
  }

  @Override
  public double cost() {
    return 0.5 + beverage.cost();
  }

  @Override
  public String description() {
    return beverage.description() + ", Espresso";
  }
}