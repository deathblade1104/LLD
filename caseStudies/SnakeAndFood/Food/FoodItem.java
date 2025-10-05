package caseStudies.SnakeAndFood.Food;

import caseStudies.SnakeAndFood.Position;

public abstract class FoodItem {
  protected Position position;
  protected int points;

  public FoodItem(Position pos) {
    this.position = pos;
  }

  public Position getPosition() {
    return this.position;
  }

  public int getPoints() {
    return points;
  }
}