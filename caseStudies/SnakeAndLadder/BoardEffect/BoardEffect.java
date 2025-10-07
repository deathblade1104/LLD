package caseStudies.SnakeAndLadder.BoardEffect;

public interface BoardEffect {
  boolean appliesAt(int position);

  int apply(int position, int boardSize);

  String description();

  int fromTile();
}
