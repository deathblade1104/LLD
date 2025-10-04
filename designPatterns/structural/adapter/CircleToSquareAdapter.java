package designPatterns.structural.adapter;

public class CircleToSquareAdapter extends Square {
  private double len;

  public CircleToSquareAdapter(Circle circle) {
    this.len = 2.0 * circle.getRadius();
  }

  @Override
  public double getSideLength() {
    return this.len;
  }
}
