package caseStudies.SnakeAndLadder.BoardEffect;

public class Ladder implements BoardEffect {
  private final int from, to;

  public Ladder(int from, int to) {
    if (to <= from)
      throw new IllegalArgumentException("Ladder must go up: " + from + " -> " + to);
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
    return "Ladder " + from + "→" + to;
  }

  @Override
  public int fromTile() {
    return from;
  }
}