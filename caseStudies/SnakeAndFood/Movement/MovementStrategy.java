package caseStudies.SnakeAndFood.Movement;

import caseStudies.SnakeAndFood.Position;

public interface MovementStrategy {
  Position getNextPosition(Position currentHead, HumanDirections direction);
}
