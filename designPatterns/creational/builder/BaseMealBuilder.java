package designPatterns.creational.builder;

public abstract class BaseMealBuilder {
  protected Meal meal;

  void addStarter() {
    return;
  }

  void addMainCourse() {
    return;
  }

  void addDessert() {
    return;
  }

  void addDrink() {
    return;
  }

  Meal build() {
    this.addStarter();
    this.addMainCourse();
    this.addDessert();
    this.addDrink();
    return this.meal;
  }
}
