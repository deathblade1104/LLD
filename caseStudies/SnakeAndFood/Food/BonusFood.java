package caseStudies.SnakeAndFood.Food;

import caseStudies.SnakeAndFood.Position;

public class BonusFood extends FoodItem {
  public BonusFood(Position pos) {
    super(pos);
    this.points = 3;
  }
}