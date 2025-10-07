package caseStudies.SnakeAndLadder.BoardEffect;

public class Snake implements BoardEffect {
  private final int from, to;

  public Snake(int from, int to) {
    if (from <= to)
      throw new IllegalArgumentException("Snake must go down: " + from + " -> " + to);
    this.from = from;
    this.to = to;
  }

  @Override
  public boolean appliesAt(int position) {
    return position == from;
  }

  @Override
  public int apply(int position, int boardSize) {
    return to;
  }

  @Override
  public String description() {
    return "Snake " + from + "→" + to;
  }

  @Override
  public int fromTile() {
    return from;
  }
}