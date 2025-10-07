package designPatterns.creational.builder.classic;

import designPatterns.creational.builder.enums.Dessert;
import designPatterns.creational.builder.enums.Main;

public class MiniMealBuilder extends BaseMealBuilder {

  public MiniMealBuilder() {
    this.meal = new Meal();
  }

  @Override
  void addMainCourse() {
    this.meal.setMain(Main.PIZZA);
  }

  @Override
  void addDessert() {
    this.meal.setDessert(Dessert.CAKE_SLICE);
  }
}
