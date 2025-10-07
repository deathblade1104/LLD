package designPatterns.creational.builder.classic;

import designPatterns.creational.builder.classic.enums.Dessert;
import designPatterns.creational.builder.classic.enums.Drink;
import designPatterns.creational.builder.classic.enums.Main;
import designPatterns.creational.builder.classic.enums.Starter;

public class VeganMealBuilder extends BaseMealBuilder {

  public VeganMealBuilder() {
    this.meal = new Meal();
  }

  @Override
  void addStarter() {
    this.meal.setStarter(Starter.SALAD);
  }

  @Override
  void addMainCourse() {
    this.meal.setMain(Main.VEGGIE_STIR_FRY);
  }

  @Override
  void addDessert() {
    this.meal.setDessert(Dessert.VEGAN_PUDDING);
  }

  @Override
  void addDrink() {
    this.meal.setDrink(Drink.VEGAN_SHAKE);
  }
}
