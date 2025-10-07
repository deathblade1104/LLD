package designPatterns.creational.builder.classic;

import designPatterns.creational.builder.enums.Dessert;
import designPatterns.creational.builder.enums.Drink;
import designPatterns.creational.builder.enums.Main;
import designPatterns.creational.builder.enums.Starter;

public class Meal {

  private Starter starter = null;
  private Main main = null;
  private Dessert dessert = null;
  private Drink drink = null;

  Starter getStarter() {
    return this.starter;
  }

  Main getMain() {
    return this.main;
  }

  Dessert getDessert() {
    return this.dessert;
  }

  Drink getDrink() {
    return this.drink;
  }

  void setStarter(Starter starter) {
    this.starter = starter;
  }

  void setMain(Main main) {
    this.main = main;
  }

  void setDessert(Dessert dessert) {
    this.dessert = dessert;
  }

  void setDrink(Drink drink) {
    this.drink = drink;
  }

  void printMeal() {
    System.out.println("Starter : " + this.getStarter());
    System.out.println("Main Course : " + this.getMain());
    System.out.println("Dessert : " + this.getDessert());
    System.out.println("Drink : " + this.getDrink());
  }
}
