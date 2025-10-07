package caseStudies.SnakeAndLadder.GameBoard;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import caseStudies.SnakeAndLadder.BoardEffect.BoardEffect;

public class GameBoard {
  private final int rows;
  private final int cols;
  private final int size;
  private final List<BoardEffect> effects;

  GameBoard(int rows, int cols, List<BoardEffect> effects) {
    int total = rows * cols;
    if (total > 1000)
      throw new IllegalArgumentException("Board cannot exceed 1000 cells");
    int root = (int) Math.round(Math.sqrt(total));
    if (root * root != total)
      throw new IllegalArgumentException("Board must be a perfect square");

    this.rows = rows;
    this.cols = cols;
    this.size = total;
    this.effects = new ArrayList<>(effects);

    validateBounds();
    validateConflicts();
  }

  private void validateBounds() {
    for (BoardEffect e : effects) {
      int from = e.fromTile();
      if (from < 1 || from > size) {
        throw new IllegalArgumentException("Effect " + e.description() + " out of bounds for board size " + size);
      }
    }
  }

  private void validateConflicts() {
    // No two effects should start from the same tile
    Set<Integer> seen = new HashSet<>();
    for (BoardEffect e : effects) {
      if (!seen.add(e.fromTile())) {
        throw new IllegalArgumentException("Conflicting effects at tile " + e.fromTile());
      }
    }
  }

  public int getSize() {
    return size;
  }

  /** Apply at most one effect (the one whose fromTile matches) */
  public int applyEffects(int position) {
    for (BoardEffect e : effects) {
      if (e.appliesAt(position))
        return e.apply(position, size);
    }
    return position;
  }
}