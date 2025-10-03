package designPatterns.creational.builder;

public class Main {

  public static void main(String[] args) {
    VeganMealBuilder builder1 = new VeganMealBuilder();
    Meal meal1 = builder1.build();
    System.out.println("Vegan Meal ");
    meal1.printMeal();

    HealthyMealBuilder builder2 = new HealthyMealBuilder();
    Meal meal2 = builder2.build();
    System.out.println("Healthy Meal ");
    meal2.printMeal();

    MiniMealBuilder builder3 = new MiniMealBuilder();
    Meal meal3 = builder3.build();
    System.out.println("Mini Meal ");
    meal3.printMeal();

    return;
  }
}
