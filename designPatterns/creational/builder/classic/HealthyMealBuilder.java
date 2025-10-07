package designPatterns.creational.builder.classic;

import designPatterns.creational.builder.classic.enums.Dessert;
import designPatterns.creational.builder.classic.enums.Drink;
import designPatterns.creational.builder.classic.enums.Main;
import designPatterns.creational.builder.classic.enums.Starter;

public class HealthyMealBuilder extends BaseMealBuilder {

  public HealthyMealBuilder() {
    this.meal = new Meal();
  }

  @Override
  void addStarter() {
    this.meal.setStarter(Starter.SALAD);
  }

  @Override
  void addMainCourse() {
    this.meal.setMain(Main.GRILLED_CHICKEN);
  }

  @Override
  void addDessert() {
    this.meal.setDessert(Dessert.FRUIT_SALAD);
  }

  @Override
  void addDrink() {
    meal.setDrink(Drink.WATER);
  }
}
