package caseStudies.SnakeAndFood.Movement;

import caseStudies.SnakeAndFood.Position;

public class HumanMovementStrategy implements MovementStrategy {
  @Override
  public Position getNextPosition(Position currentHead, HumanDirections direction) {
    int row = currentHead.row();
    int col = currentHead.column();
    return new Position(direction.getRowDelta() + row, direction.getColumnDelta() + col);
  }
}