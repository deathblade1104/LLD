package designPatterns.structural.decorator;

public class Main {
  public static void main(String[] args) {
    Beverage beverage = new FoamDecorator(
        new CreamDecorator(new EspressoDecorator(new LightRoast())));
    System.out.println(beverage.description());
    System.out.println(beverage.cost());
  }
}
