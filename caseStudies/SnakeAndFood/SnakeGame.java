package caseStudies.SnakeAndFood;

import java.util.Deque;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

import caseStudies.SnakeAndFood.Movement.HumanDirections;
import caseStudies.SnakeAndFood.Movement.HumanMovementStrategy;
import caseStudies.SnakeAndFood.Movement.MovementStrategy;

public class SnakeGame {
  private GameBoard board;
  public Deque<Position> snake;
  private Map<Position, Boolean> snakeMap;
  private Position[] foodPositions;
  private int foodIndex;
  private MovementStrategy movementStrategy;

  public SnakeGame(int width, int height, Position[] foodPosArr) {
    this.board = GameBoard.getInstance(width, height);
    this.foodPositions = foodPosArr;
    this.foodIndex = 0;
    this.snake = new LinkedList<>();
    this.snakeMap = new HashMap<>();
    Position initialPos = new Position(0, 0);
    this.snake.offerFirst(initialPos);
    this.snakeMap.put(initialPos, true);
    this.movementStrategy = new HumanMovementStrategy();
  }

  public void setMovementStrategy(MovementStrategy strategy) {
    this.movementStrategy = strategy;
  }

  public int move(HumanDirections direction) {

    Position currentHead = this.snake.peekFirst();

    Position newHead = this.movementStrategy.getNextPosition(currentHead, direction);
    int newHeadRow = newHead.row();
    int newHeadColumn = newHead.column();

    boolean crossesBoundary = newHeadRow < 0 || newHeadRow >= this.board.getHeight() ||
        newHeadColumn < 0 || newHeadColumn >= this.board.getWidth();

    Position currentTail = this.snake.peekLast();

    boolean bitesItself = this.snakeMap.containsKey(newHead) &&
        !(newHead.row() == currentTail.row() &&
            newHead.column() == currentTail.column());

    if (crossesBoundary || bitesItself) {
      return -1;
    }

    boolean ateFood = (this.foodIndex < this.foodPositions.length) &&
        (this.foodPositions[this.foodIndex].row() == newHeadRow) &&
        (this.foodPositions[this.foodIndex].column() == newHeadColumn);

    if (ateFood) {
      this.foodIndex++;
    } else {
      this.snake.pollLast();
      this.snakeMap.remove(currentTail);
    }

    this.snake.addFirst(newHead);
    this.snakeMap.put(newHead, true);
    int score = this.snake.size() - 1;
    return score;
  }
}
