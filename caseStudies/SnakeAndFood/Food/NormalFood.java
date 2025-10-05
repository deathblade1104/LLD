package caseStudies.SnakeAndFood.Food;

import caseStudies.SnakeAndFood.Position;

public class NormalFood extends FoodItem {
  public NormalFood(Position pos) {
    super(pos);
    this.points = 1;
  }
}