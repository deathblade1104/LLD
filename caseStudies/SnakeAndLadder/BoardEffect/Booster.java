package caseStudies.SnakeAndLadder.BoardEffect;

public class Booster implements BoardEffect {
  private final int from, boost;

  public Booster(int from, int boost) {
    this.from = from;
    this.boost = boost;
  }

  @Override
  public boolean appliesAt(int position) {
    return position == from;
  }

  @Override
  public int apply(int position, int boardSize) {
    int next = position + boost;
    return Math.min(next, boardSize);
  }

  @Override
  public String description() {
    return "Booster " + from + "→+" + boost;
  }

  @Override
  public int fromTile() {
    return from;
  }
}