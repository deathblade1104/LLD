package caseStudies.SnakeAndLadder.GameBoard;

import java.util.ArrayList;
import java.util.List;

import caseStudies.SnakeAndLadder.BoardEffect.BoardEffect;

public class GameBoardBuilder {
  private int rows;
  private int cols;
  private final List<BoardEffect> effects = new ArrayList<>();

  public GameBoardBuilder withSize(int rows, int cols) {
    this.rows = rows;
    this.cols = cols;
    return this;
  }

  public GameBoardBuilder addEffect(BoardEffect effect) {
    effects.add(effect);
    return this;
  }

  public GameBoard build() {
    if (rows <= 0 || cols <= 0)
      throw new IllegalStateException("Board size not set");
    return new GameBoard(rows, cols, effects);
  }
}