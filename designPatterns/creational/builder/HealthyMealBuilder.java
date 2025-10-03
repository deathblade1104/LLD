
package designPatterns.creational.builder;

import designPatterns.creational.builder.enums.Dessert;
import designPatterns.creational.builder.enums.Drink;
import designPatterns.creational.builder.enums.Main;
import designPatterns.creational.builder.enums.Starter;

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
