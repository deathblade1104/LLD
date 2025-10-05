package caseStudies.SnakeAndFood.Food;

import caseStudies.SnakeAndFood.Position;

public class FoodFactory {
  public static FoodItem createFood(Position position, String type) {
    if ("bonus".equals(type)) {
      return new BonusFood(position);
    }
    return new NormalFood(position);
  }
}
