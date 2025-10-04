package designPatterns.structural.decorator;

public class FoamDecorator extends BeverageDecorator {

  public FoamDecorator(Beverage beverage) {
    super(beverage);
  }

  @Override
  public double cost() {
    return 0.2 + beverage.cost();
  }

  @Override
  public String description() {
    return beverage.description() + ", Foam";
  }
}
